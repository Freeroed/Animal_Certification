import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AnimalCertificationTestModule } from '../../../test.module';
import { PersonDataComponent } from 'app/entities/person-data/person-data.component';
import { PersonDataService } from 'app/entities/person-data/personal-data.service';
import { PersonData } from 'app/shared/model/personal-data.model';

describe('Component Tests', () => {
  describe('PersonalData Management Component', () => {
    let comp: PersonDataComponent;
    let fixture: ComponentFixture<PersonDataComponent>;
    let service: PersonDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [PersonDataComponent],
      })
        .overrideTemplate(PersonDataComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersonDataComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersonDataService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PersonData(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.personData && comp.personData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
