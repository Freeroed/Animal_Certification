import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCertificationSharedModule } from 'app/shared/shared.module';
import { DocumentTypeComponent } from './document-type.component';
import { DocumentTypeDetailComponent } from './document-type-detail.component';
import { DocumentTypeUpdateComponent } from './document-type-update.component';
import { DocumentTypeDeleteDialogComponent } from './document-type-delete-dialog.component';
import { documentTypeRoute } from './document-type.route';

@NgModule({
  imports: [AnimalCertificationSharedModule, RouterModule.forChild(documentTypeRoute)],
  declarations: [DocumentTypeComponent, DocumentTypeDetailComponent, DocumentTypeUpdateComponent, DocumentTypeDeleteDialogComponent],
  entryComponents: [DocumentTypeDeleteDialogComponent],
})
export class AnimalCertificationDocumentTypeModule {}
