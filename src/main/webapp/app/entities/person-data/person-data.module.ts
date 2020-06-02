import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCertificationSharedModule } from 'app/shared/shared.module';
import { PersonDataComponent } from './person-data.component';
import { PersonDataDetailComponent } from './person-data-detail.component';
import { PersonDataUpdateComponent } from './person-data-update.component';
import { PersonDataDeleteDialogComponent } from './person-data-delete-dialog.component';
import { personDataRoute } from './person-data.route';

@NgModule({
  imports: [AnimalCertificationSharedModule, RouterModule.forChild(personDataRoute)],
  declarations: [PersonDataComponent, PersonDataDetailComponent, PersonDataUpdateComponent, PersonDataDeleteDialogComponent],
  entryComponents: [PersonDataDeleteDialogComponent],
})
export class AnimalCertificationPersonDataModule {}
