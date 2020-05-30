import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILaboratoryResearch } from 'app/shared/model/laboratory-research.model';
import { LaboratoryResearchService } from './laboratory-research.service';

@Component({
  templateUrl: './laboratory-research-delete-dialog.component.html',
})
export class LaboratoryResearchDeleteDialogComponent {
  laboratoryResearch?: ILaboratoryResearch;

  constructor(
    protected laboratoryResearchService: LaboratoryResearchService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.laboratoryResearchService.delete(id).subscribe(() => {
      this.eventManager.broadcast('laboratoryResearchListModification');
      this.activeModal.close();
    });
  }
}
