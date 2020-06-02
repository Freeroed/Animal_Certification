import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCertificationSharedModule } from 'app/shared/shared.module';
import { AnimalTypeComponent } from './animal-type.component';
import { AnimalTypeDetailComponent } from './animal-type-detail.component';
import { AnimalTypeUpdateComponent } from './animal-type-update.component';
import { AnimalTypeDeleteDialogComponent } from './animal-type-delete-dialog.component';
import { animalTypeRoute } from './animal-type.route';

@NgModule({
  imports: [AnimalCertificationSharedModule, RouterModule.forChild(animalTypeRoute)],
  declarations: [AnimalTypeComponent, AnimalTypeDetailComponent, AnimalTypeUpdateComponent, AnimalTypeDeleteDialogComponent],
  entryComponents: [AnimalTypeDeleteDialogComponent],
})
export class AnimalCertificationAnimalTypeModule {}
