import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AnimalCretificationSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [AnimalCretificationSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class AnimalCretificationHomeModule {}
