import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCertificationSharedModule } from 'app/shared/shared.module';
import { BreedComponent } from './breed.component';
import { BreedDetailComponent } from './breed-detail.component';
import { BreedUpdateComponent } from './breed-update.component';
import { BreedDeleteDialogComponent } from './breed-delete-dialog.component';
import { breedRoute } from './breed.route';

@NgModule({
  imports: [AnimalCertificationSharedModule, RouterModule.forChild(breedRoute)],
  declarations: [BreedComponent, BreedDetailComponent, BreedUpdateComponent, BreedDeleteDialogComponent],
  entryComponents: [BreedDeleteDialogComponent],
})
export class AnimalCertificationBreedModule {}
