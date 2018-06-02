#include <types.h>
#include <kern/errno.h>
#include <lib.h>
#include <thread.h>
#include <addrspace.h>
#include <vm.h>
#include <machine/tlb.h>

/* Place your page table functions here */


#define PAGE_BITS 12;

static struct lock *page_table_lock;

size_t nPages = (ram_getsize()/PAGE_SIZE) * 2;

uint32_t hpt_hash(struct addrspace *as, vaddr_t faultaddr)
{
        uint32_t index;

        index = (((uint32_t )as) ^ (faultaddr >> PAGE_BITS)) % nPages;
        return index;
}


bool look_up(vaddr_t addr,struct addrspace *as){

    uint32_t index = hpt_hash(as, faultaddress);

    struct page_table_entry *entry = page_table[index];

    while(entry != NULL){
      if(entry->v_addr == addr && entry->pid == (uint32_t) as){
            return ture;
        }
      entry = entry->next;
    }
    return false;
}

void vm_bootstrap(void)
{
        /* Initialise VM sub-system.  You probably want to initialise your
           frame table here as well.
        */
        table_init();
        //page_table_lock = lock_create("page_table_lock");
}

int
vm_fault(int faulttype, vaddr_t faultaddress)
{

        if(faulttype == VM_FAULT_READONLY){
                return EFAULT;
        }

        //No process;
        if(curproc == NULL){
          return EFAULT;
        }

        struct addrspace *as = proc_getas();

        lock_acquire(page_table_lock);
        bool found = look_up(faultaddress,as);

        lock_release(page_table_lock);

        if(found == false) {

            struct region region = NULL;
            for (region = as->first_region; region; region = region->next) {
                if (region->vbase <= faultaddress && (region->vbase + region->size) > faultaddress) {
                   break;
                 }
            }

            if(region == NULL){
                return EFAULT;
            }

            vaddr_t( vaddr = alloc_kpages(1);
            uint32_t i = hpt_hash(as, faultaddress);
            //set the menmory before vaddr to 0 with size of PAGE_SIZE
            memset((void*) vaddr,0,PAGE_SIZE);

            //
            struct page_table_entry *newPage = kmalloc(sizeof(struct page_table_entry));
            newPage->pid = (uint32_t) as;
            newPage->v_addr = faultaddress;
            lock_acquire(page_table_lock);
            newPage->next_empty = page_table[i];
            page_table[i] = newPage;
            lock_release(page_table_lock);

          }

            //
            int spl = splhigh();
            uint32_t ehi = vaddr & TLBHI_VPAGE;
            uint32_t elo = KVADDR_TO_PADDR(vaddr) | TLBLO_VALID;
            tlb_random(ehi, elo | TLBLO_VALID | TLBLO_DIRTY);
            splx(spl);

            return 0;
}


/*
 *
 * SMP-specific functions.  Unused in our configuration.
 */

void
vm_tlbshootdown(const struct tlbshootdown *ts)
{
        (void)ts;
        panic("vm tried to do tlb shootdown?!\n");
}
