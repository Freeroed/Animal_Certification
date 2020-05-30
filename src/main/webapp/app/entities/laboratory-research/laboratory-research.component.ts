import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILaboratoryResearch } from 'app/shared/model/laboratory-research.model';
import { LaboratoryResearchService } from './laboratory-research.service';
import { LaboratoryResearchDeleteDialogComponent } from './laboratory-research-delete-dialog.component';

@Component({
  selector: 'jhi-laboratory-research',
  templateUrl: './laboratory-research.component.html',
})
export class LaboratoryResearchComponent implements OnInit, OnDestroy {
  laboratoryResearches?: ILaboratoryResearch[];
  eventSubscriber?: Subscription;

  constructor(
    protected laboratoryResearchService: LaboratoryResearchService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.laboratoryResearchService
      .query()
      .subscribe((res: HttpResponse<ILaboratoryResearch[]>) => (this.laboratoryResearches = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLaboratoryResearches();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILaboratoryResearch): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLaboratoryResearches(): void {
    this.eventSubscriber = this.eventManager.subscribe('laboratoryResearchListModification', () => this.loadAll());
  }

  delete(laboratoryResearch: ILaboratoryResearch): void {
    const modalRef = this.modalService.open(LaboratoryResearchDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.laboratoryResearch = laboratoryResearch;
  }
}
