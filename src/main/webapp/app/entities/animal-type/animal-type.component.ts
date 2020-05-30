import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAnimalType } from 'app/shared/model/animal-type.model';
import { AnimalTypeService } from './animal-type.service';
import { AnimalTypeDeleteDialogComponent } from './animal-type-delete-dialog.component';

@Component({
  selector: 'jhi-animal-type',
  templateUrl: './animal-type.component.html',
})
export class AnimalTypeComponent implements OnInit, OnDestroy {
  animalTypes?: IAnimalType[];
  eventSubscriber?: Subscription;

  constructor(protected animalTypeService: AnimalTypeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.animalTypeService.query().subscribe((res: HttpResponse<IAnimalType[]>) => (this.animalTypes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAnimalTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAnimalType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAnimalTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('animalTypeListModification', () => this.loadAll());
  }

  delete(animalType: IAnimalType): void {
    const modalRef = this.modalService.open(AnimalTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.animalType = animalType;
  }
}
