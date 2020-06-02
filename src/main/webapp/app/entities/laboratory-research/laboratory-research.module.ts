import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCertificationSharedModule } from 'app/shared/shared.module';
import { LaboratoryResearchComponent } from './laboratory-research.component';
import { LaboratoryResearchDetailComponent } from './laboratory-research-detail.component';
import { LaboratoryResearchUpdateComponent } from './laboratory-research-update.component';
import { LaboratoryResearchDeleteDialogComponent } from './laboratory-research-delete-dialog.component';
import { laboratoryResearchRoute } from './laboratory-research.route';

@NgModule({
  imports: [AnimalCertificationSharedModule, RouterModule.forChild(laboratoryResearchRoute)],
  declarations: [
    LaboratoryResearchComponent,
    LaboratoryResearchDetailComponent,
    LaboratoryResearchUpdateComponent,
    LaboratoryResearchDeleteDialogComponent,
  ],
  entryComponents: [LaboratoryResearchDeleteDialogComponent],
})
export class AnimalCertificationLaboratoryResearchModule {}
