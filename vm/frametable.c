#include <types.h>
#include <kern/errno.h>
#include <lib.h>
#include <thread.h>
#include <addrspace.h>
#include <vm.h>

/* Place your frametable data-structures here
 * You probably also want to write a frametable initialisation
 * function and call it from vm_bootstrap
 */
struct frame_table_entry *frame_table = NULL;
static struct frame_table_entry *top_free = NULL;
struct page_table_entry **page_table = NULL;


void table_init(void);
static struct spinlock stealmem_lock = SPINLOCK_INITIALIZER;

/* Note that this function returns a VIRTUAL address, not a physical
 * address
 * WARNING: this function gets called very early, before
 * vm_bootstrap().  You may wish to modify main.c to call your
 * frame table initialisation function, or check to see if the
 * frame table has been initialised and call ram_stealmem() otherwise.
 */

void table_init(void){
        //Compute somewhere free in RAM to put it and just use it in that location.
        paddr_t top_of_ram = ram_getsize();
        size_t nFrame_pages =  top_of_ram / PAGE_SIZE;
        paddr_t location = top_of_ram - (nFrame_pages * sizeof(struct frame_table_entry));
        frame_table = (struct frame_table_entry *) PADDR_TO_KVADDR(location);

        // number of page table is 2 times more than number of frame pages
        size_t nPage_table = nFrame_pages * 2;

        //allocate the space for page table
        location -= nPage_table * sizeof(struct page_table_entry *);
        page_table = (struct page_table_entry **) PADDR_TO_KVADDR(location);
        //init page_table
        for (size_t i = 0; i < nPage_table; i++) {
            page_table[i] = NULL;
        }



        //allocate frame_table and page_table space
        for (size_t i = location / PAGE_SIZE; i < nFrame_pages; i++) {
                frame_table[i].ref = 1;
                frame_table[i].next_empty = NULL;
        }


        //allocate kernel space
        size_t top_used = (int)(ram_getfirstfree()/ PAGE_SIZE);
        for (size_t i = 0; i < top_used; i++) {
                frame_table[i].ref = 1;
                frame_table[i].next_empty = NULL;
        }

        //store the top used frame in top_free
        top_free = &(frame_table[top_used]);

        //allocate free memory
        for (size_t i = top_used; i < location / PAGE_SIZE; i++) {
                frame_table[i].ref = 0;
                frame_table[i].next_empty = &(frame_table[i + 1]);
        }

        //allocate the last entry as NULL
        frame_table[(location / PAGE_SIZE) - 1].next_empty = NULL;
}




vaddr_t alloc_kpages(unsigned int npages)
{
        paddr_t addr;
        if(frame_table == NULL){


            spinlock_acquire(&stealmem_lock);
            addr = ram_stealmem(npages);
            spinlock_release(&stealmem_lock);

            if(addr == 0)return 0;


            return PADDR_TO_KVADDR(addr);
        }
        else{

            spinlock_acquire(&stealmem_lock);

            addr = PADDR_TO_KVADDR((top_free - frame_table) * PAGE_SIZE);
            top_free->ref = 1;
            top_free = top_free->next_empty;

            spinlock_release(&stealmem_lock);

           return addr;
        }
}

void free_kpages(vaddr_t addr)
{
        //convert vaddr to paddr
        paddr_t paddr = KVADDR_TO_PADDR(addr);
        //get index of frame table
        int index = paddr / PAGE_SIZE;

        spinlock_acquire(&stealmem_lock);

        frame_table[index].ref = 0;
        frame_table[index].next_empty = top_free;
        top_free = &frame_table[index];

        spinlock_release(&stealmem_lock);
}
