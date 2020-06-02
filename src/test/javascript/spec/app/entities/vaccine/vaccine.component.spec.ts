import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AnimalCertificationTestModule } from '../../../test.module';
import { VaccineComponent } from 'app/entities/vaccine/vaccine.component';
import { VaccineService } from 'app/entities/vaccine/vaccine.service';
import { Vaccine } from 'app/shared/model/vaccine.model';

describe('Component Tests', () => {
  describe('Vaccine Management Component', () => {
    let comp: VaccineComponent;
    let fixture: ComponentFixture<VaccineComponent>;
    let service: VaccineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [VaccineComponent],
      })
        .overrideTemplate(VaccineComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VaccineComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VaccineService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Vaccine(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.vaccines && comp.vaccines[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
