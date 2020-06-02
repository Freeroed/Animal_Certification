import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnimalCertificationTestModule } from '../../../test.module';
import { BreedDetailComponent } from 'app/entities/breed/breed-detail.component';
import { Breed } from 'app/shared/model/breed.model';

describe('Component Tests', () => {
  describe('Breed Management Detail Component', () => {
    let comp: BreedDetailComponent;
    let fixture: ComponentFixture<BreedDetailComponent>;
    const route = ({ data: of({ breed: new Breed(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [BreedDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BreedDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BreedDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load breed on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.breed).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
