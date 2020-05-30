import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AnimalCretificationTestModule } from '../../../test.module';
import { LaboratoryResearchComponent } from 'app/entities/laboratory-research/laboratory-research.component';
import { LaboratoryResearchService } from 'app/entities/laboratory-research/laboratory-research.service';
import { LaboratoryResearch } from 'app/shared/model/laboratory-research.model';

describe('Component Tests', () => {
  describe('LaboratoryResearch Management Component', () => {
    let comp: LaboratoryResearchComponent;
    let fixture: ComponentFixture<LaboratoryResearchComponent>;
    let service: LaboratoryResearchService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCretificationTestModule],
        declarations: [LaboratoryResearchComponent],
      })
        .overrideTemplate(LaboratoryResearchComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LaboratoryResearchComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LaboratoryResearchService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LaboratoryResearch(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.laboratoryResearches && comp.laboratoryResearches[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
