import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCertificationSharedModule } from 'app/shared/shared.module';
import { PersonalDataComponent } from './personal-data.component';
import { PersonalDataDetailComponent } from './personal-data-detail.component';
import { PersonalDataUpdateComponent } from './personal-data-update.component';
import { PersonalDataDeleteDialogComponent } from './personal-data-delete-dialog.component';
import { personalDataRoute } from './personal-data.route';

@NgModule({
  imports: [AnimalCertificationSharedModule, RouterModule.forChild(personalDataRoute)],
  declarations: [PersonalDataComponent, PersonalDataDetailComponent, PersonalDataUpdateComponent, PersonalDataDeleteDialogComponent],
  entryComponents: [PersonalDataDeleteDialogComponent],
})
export class AnimalCertificationPersonDataModule {}
