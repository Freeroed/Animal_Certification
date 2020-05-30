import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCretificationSharedModule } from 'app/shared/shared.module';
import { AnimalComponent } from './animal.component';
import { AnimalDetailComponent } from './animal-detail.component';
import { AnimalUpdateComponent } from './animal-update.component';
import { AnimalDeleteDialogComponent } from './animal-delete-dialog.component';
import { animalRoute } from './animal.route';

@NgModule({
  imports: [AnimalCretificationSharedModule, RouterModule.forChild(animalRoute)],
  declarations: [AnimalComponent, AnimalDetailComponent, AnimalUpdateComponent, AnimalDeleteDialogComponent],
  entryComponents: [AnimalDeleteDialogComponent],
})
export class AnimalCretificationAnimalModule {}
