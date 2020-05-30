import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVaccine } from 'app/shared/model/vaccine.model';
import { VaccineService } from './vaccine.service';

@Component({
  templateUrl: './vaccine-delete-dialog.component.html',
})
export class VaccineDeleteDialogComponent {
  vaccine?: IVaccine;

  constructor(protected vaccineService: VaccineService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.vaccineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('vaccineListModification');
      this.activeModal.close();
    });
  }
}
