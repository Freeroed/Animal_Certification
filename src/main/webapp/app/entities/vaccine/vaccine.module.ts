import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCertificationSharedModule } from 'app/shared/shared.module';
import { VaccineComponent } from './vaccine.component';
import { VaccineDetailComponent } from './vaccine-detail.component';
import { VaccineUpdateComponent } from './vaccine-update.component';
import { VaccineDeleteDialogComponent } from './vaccine-delete-dialog.component';
import { vaccineRoute } from './vaccine.route';

@NgModule({
  imports: [AnimalCertificationSharedModule, RouterModule.forChild(vaccineRoute)],
  declarations: [VaccineComponent, VaccineDetailComponent, VaccineUpdateComponent, VaccineDeleteDialogComponent],
  entryComponents: [VaccineDeleteDialogComponent],
})
export class AnimalCertificationVaccineModule {}
