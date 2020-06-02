import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnimalCertificationTestModule } from '../../../test.module';
import { AnimalTypeDetailComponent } from 'app/entities/animal-type/animal-type-detail.component';
import { AnimalType } from 'app/shared/model/animal-type.model';

describe('Component Tests', () => {
  describe('AnimalType Management Detail Component', () => {
    let comp: AnimalTypeDetailComponent;
    let fixture: ComponentFixture<AnimalTypeDetailComponent>;
    const route = ({ data: of({ animalType: new AnimalType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [AnimalTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AnimalTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AnimalTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load animalType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.animalType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
