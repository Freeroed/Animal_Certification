import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVaccine } from 'app/shared/model/vaccine.model';
import { VaccineService } from './vaccine.service';
import { VaccineDeleteDialogComponent } from './vaccine-delete-dialog.component';

@Component({
  selector: 'jhi-vaccine',
  templateUrl: './vaccine.component.html',
})
export class VaccineComponent implements OnInit, OnDestroy {
  vaccines?: IVaccine[];
  eventSubscriber?: Subscription;

  constructor(protected vaccineService: VaccineService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.vaccineService.query().subscribe((res: HttpResponse<IVaccine[]>) => (this.vaccines = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVaccines();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVaccine): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVaccines(): void {
    this.eventSubscriber = this.eventManager.subscribe('vaccineListModification', () => this.loadAll());
  }

  delete(vaccine: IVaccine): void {
    const modalRef = this.modalService.open(VaccineDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.vaccine = vaccine;
  }
}
