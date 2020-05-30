import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'animal',
        loadChildren: () => import('./animal/animal.module').then(m => m.AnimalCretificationAnimalModule),
      },
      {
        path: 'breed',
        loadChildren: () => import('./breed/breed.module').then(m => m.AnimalCretificationBreedModule),
      },
      {
        path: 'animal-type',
        loadChildren: () => import('./animal-type/animal-type.module').then(m => m.AnimalCretificationAnimalTypeModule),
      },
      {
        path: 'vaccine',
        loadChildren: () => import('./vaccine/vaccine.module').then(m => m.AnimalCretificationVaccineModule),
      },
      {
        path: 'laboratory-research',
        loadChildren: () =>
          import('./laboratory-research/laboratory-research.module').then(m => m.AnimalCretificationLaboratoryResearchModule),
      },
      {
        path: 'request',
        loadChildren: () => import('./request/request.module').then(m => m.AnimalCretificationRequestModule),
      },
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.AnimalCretificationRegionModule),
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.AnimalCretificationCountryModule),
      },
      {
        path: 'document-type',
        loadChildren: () => import('./document-type/document-type.module').then(m => m.AnimalCretificationDocumentTypeModule),
      },
      {
        path: 'document',
        loadChildren: () => import('./document/document.module').then(m => m.AnimalCretificationDocumentModule),
      },
      {
        path: 'border-crossing-point',
        loadChildren: () =>
          import('./border-crossing-point/border-crossing-point.module').then(m => m.AnimalCretificationBorderCrossingPointModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AnimalCretificationEntityModule {}
