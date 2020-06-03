import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnimalCertificationTestModule } from '../../../test.module';
import { PersonDataDetailComponent } from 'app/entities/person-data/personal-data-detail.component';
import { PersonData } from 'app/shared/model/personal-data.model';

describe('Component Tests', () => {
  describe('PersonalData Management Detail Component', () => {
    let comp: PersonDataDetailComponent;
    let fixture: ComponentFixture<PersonDataDetailComponent>;
    const route = ({ data: of({ personData: new PersonData(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [PersonDataDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PersonDataDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PersonDataDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load personData on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.personData).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
