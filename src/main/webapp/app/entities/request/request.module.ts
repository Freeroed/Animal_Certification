import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCretificationSharedModule } from 'app/shared/shared.module';
import { RequestComponent } from './request.component';
import { RequestDetailComponent } from './request-detail.component';
import { RequestUpdateComponent } from './request-update.component';
import { RequestDeleteDialogComponent } from './request-delete-dialog.component';
import { requestRoute } from './request.route';

@NgModule({
  imports: [AnimalCretificationSharedModule, RouterModule.forChild(requestRoute)],
  declarations: [RequestComponent, RequestDetailComponent, RequestUpdateComponent, RequestDeleteDialogComponent],
  entryComponents: [RequestDeleteDialogComponent],
})
export class AnimalCretificationRequestModule {}
