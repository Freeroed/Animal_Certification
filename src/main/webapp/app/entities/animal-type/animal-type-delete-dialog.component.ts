import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAnimalType } from 'app/shared/model/animal-type.model';
import { AnimalTypeService } from './animal-type.service';

@Component({
  templateUrl: './animal-type-delete-dialog.component.html',
})
export class AnimalTypeDeleteDialogComponent {
  animalType?: IAnimalType;

  constructor(
    protected animalTypeService: AnimalTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.animalTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('animalTypeListModification');
      this.activeModal.close();
    });
  }
}
