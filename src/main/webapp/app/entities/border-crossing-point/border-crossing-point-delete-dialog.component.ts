import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';
import { BorderCrossingPointService } from './border-crossing-point.service';

@Component({
  templateUrl: './border-crossing-point-delete-dialog.component.html',
})
export class BorderCrossingPointDeleteDialogComponent {
  borderCrossingPoint?: IBorderCrossingPoint;

  constructor(
    protected borderCrossingPointService: BorderCrossingPointService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.borderCrossingPointService.delete(id).subscribe(() => {
      this.eventManager.broadcast('borderCrossingPointListModification');
      this.activeModal.close();
    });
  }
}
