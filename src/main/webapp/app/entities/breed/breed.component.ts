import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBreed } from 'app/shared/model/breed.model';
import { BreedService } from './breed.service';
import { BreedDeleteDialogComponent } from './breed-delete-dialog.component';

@Component({
  selector: 'jhi-breed',
  templateUrl: './breed.component.html',
})
export class BreedComponent implements OnInit, OnDestroy {
  breeds?: IBreed[];
  eventSubscriber?: Subscription;

  constructor(protected breedService: BreedService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.breedService.query().subscribe((res: HttpResponse<IBreed[]>) => (this.breeds = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBreeds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBreed): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBreeds(): void {
    this.eventSubscriber = this.eventManager.subscribe('breedListModification', () => this.loadAll());
  }

  delete(breed: IBreed): void {
    const modalRef = this.modalService.open(BreedDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.breed = breed;
  }
}
