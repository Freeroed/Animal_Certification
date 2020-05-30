import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCretificationSharedModule } from 'app/shared/shared.module';
import { BorderCrossingPointComponent } from './border-crossing-point.component';
import { BorderCrossingPointDetailComponent } from './border-crossing-point-detail.component';
import { BorderCrossingPointUpdateComponent } from './border-crossing-point-update.component';
import { BorderCrossingPointDeleteDialogComponent } from './border-crossing-point-delete-dialog.component';
import { borderCrossingPointRoute } from './border-crossing-point.route';

@NgModule({
  imports: [AnimalCretificationSharedModule, RouterModule.forChild(borderCrossingPointRoute)],
  declarations: [
    BorderCrossingPointComponent,
    BorderCrossingPointDetailComponent,
    BorderCrossingPointUpdateComponent,
    BorderCrossingPointDeleteDialogComponent,
  ],
  entryComponents: [BorderCrossingPointDeleteDialogComponent],
})
export class AnimalCretificationBorderCrossingPointModule {}
