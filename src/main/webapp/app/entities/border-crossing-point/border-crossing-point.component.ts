import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';
import { BorderCrossingPointService } from './border-crossing-point.service';
import { BorderCrossingPointDeleteDialogComponent } from './border-crossing-point-delete-dialog.component';

@Component({
  selector: 'jhi-border-crossing-point',
  templateUrl: './border-crossing-point.component.html',
})
export class BorderCrossingPointComponent implements OnInit, OnDestroy {
  borderCrossingPoints?: IBorderCrossingPoint[];
  eventSubscriber?: Subscription;

  constructor(
    protected borderCrossingPointService: BorderCrossingPointService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.borderCrossingPointService
      .query()
      .subscribe((res: HttpResponse<IBorderCrossingPoint[]>) => (this.borderCrossingPoints = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBorderCrossingPoints();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBorderCrossingPoint): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBorderCrossingPoints(): void {
    this.eventSubscriber = this.eventManager.subscribe('borderCrossingPointListModification', () => this.loadAll());
  }

  delete(borderCrossingPoint: IBorderCrossingPoint): void {
    const modalRef = this.modalService.open(BorderCrossingPointDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.borderCrossingPoint = borderCrossingPoint;
  }
}
