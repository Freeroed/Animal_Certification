import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonData } from 'app/shared/model/person-data.model';
import { PersonDataService } from './person-data.service';

@Component({
  templateUrl: './person-data-delete-dialog.component.html',
})
export class PersonDataDeleteDialogComponent {
  personData?: IPersonData;

  constructor(
    protected personDataService: PersonDataService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.personDataService.delete(id).subscribe(() => {
      this.eventManager.broadcast('personDataListModification');
      this.activeModal.close();
    });
  }
}
