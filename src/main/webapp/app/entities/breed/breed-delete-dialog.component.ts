import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBreed } from 'app/shared/model/breed.model';
import { BreedService } from './breed.service';

@Component({
  templateUrl: './breed-delete-dialog.component.html',
})
export class BreedDeleteDialogComponent {
  breed?: IBreed;

  constructor(protected breedService: BreedService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.breedService.delete(id).subscribe(() => {
      this.eventManager.broadcast('breedListModification');
      this.activeModal.close();
    });
  }
}
