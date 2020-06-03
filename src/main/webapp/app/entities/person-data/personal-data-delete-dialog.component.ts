import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonalData } from 'app/shared/model/personal-data.model';
import { PersonalDataService } from './personal-data.service';

@Component({
  templateUrl: './personal-data-delete-dialog.component.html',
})
export class PersonalDataDeleteDialogComponent {
  personalData?: IPersonalData;

  constructor(
    protected personalDataService: PersonalDataService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.personalDataService.delete(id).subscribe(() => {
      this.eventManager.broadcast('personDataListModification');
      this.activeModal.close();
    });
  }
}
