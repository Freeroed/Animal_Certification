import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAnimal } from 'app/shared/model/animal.model';
import { AnimalService } from './animal.service';
import { AnimalDeleteDialogComponent } from './animal-delete-dialog.component';

@Component({
  selector: 'jhi-animal',
  templateUrl: './animal.component.html',
})
export class AnimalComponent implements OnInit, OnDestroy {
  animals?: IAnimal[];
  eventSubscriber?: Subscription;

  constructor(protected animalService: AnimalService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.animalService.query().subscribe((res: HttpResponse<IAnimal[]>) => (this.animals = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAnimals();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAnimal): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAnimals(): void {
    this.eventSubscriber = this.eventManager.subscribe('animalListModification', () => this.loadAll());
  }

  delete(animal: IAnimal): void {
    const modalRef = this.modalService.open(AnimalDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.animal = animal;
  }
}
