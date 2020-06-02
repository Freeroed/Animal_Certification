import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnimalCertificationTestModule } from '../../../test.module';
import { VaccineDetailComponent } from 'app/entities/vaccine/vaccine-detail.component';
import { Vaccine } from 'app/shared/model/vaccine.model';

describe('Component Tests', () => {
  describe('Vaccine Management Detail Component', () => {
    let comp: VaccineDetailComponent;
    let fixture: ComponentFixture<VaccineDetailComponent>;
    const route = ({ data: of({ vaccine: new Vaccine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [VaccineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VaccineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VaccineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load vaccine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.vaccine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
