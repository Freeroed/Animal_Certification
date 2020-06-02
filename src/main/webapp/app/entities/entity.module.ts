import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'animal',
        loadChildren: () => import('./animal/animal.module').then(m => m.AnimalCertificationAnimalModule),
      },
      {
        path: 'breed',
        loadChildren: () => import('./breed/breed.module').then(m => m.AnimalCertificationBreedModule),
      },
      {
        path: 'animal-type',
        loadChildren: () => import('./animal-type/animal-type.module').then(m => m.AnimalCertificationAnimalTypeModule),
      },
      {
        path: 'vaccine',
        loadChildren: () => import('./vaccine/vaccine.module').then(m => m.AnimalCertificationVaccineModule),
      },
      {
        path: 'laboratory-research',
        loadChildren: () =>
          import('./laboratory-research/laboratory-research.module').then(m => m.AnimalCertificationLaboratoryResearchModule),
      },
      {
        path: 'request',
        loadChildren: () => import('./request/request.module').then(m => m.AnimalCertificationRequestModule),
      },
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.AnimalCertificationRegionModule),
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.AnimalCertificationCountryModule),
      },
      {
        path: 'document-type',
        loadChildren: () => import('./document-type/document-type.module').then(m => m.AnimalCertificationDocumentTypeModule),
      },
      {
        path: 'document',
        loadChildren: () => import('./document/document.module').then(m => m.AnimalCertificationDocumentModule),
      },
      {
        path: 'border-crossing-point',
        loadChildren: () =>
          import('./border-crossing-point/border-crossing-point.module').then(m => m.AnimalCertificationBorderCrossingPointModule),
      },
      {
        path: 'person-data',
        loadChildren: () => import('./person-data/person-data.module').then(m => m.AnimalCertificationPersonDataModule),
      },
      {
        path: 'address',
        loadChildren: () => import('./address/address.module').then(m => m.AnimalCertificationAddressModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AnimalCertificationEntityModule {}
