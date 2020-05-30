import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AnimalCretificationTestModule } from '../../../test.module';
import { BorderCrossingPointComponent } from 'app/entities/border-crossing-point/border-crossing-point.component';
import { BorderCrossingPointService } from 'app/entities/border-crossing-point/border-crossing-point.service';
import { BorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';

describe('Component Tests', () => {
  describe('BorderCrossingPoint Management Component', () => {
    let comp: BorderCrossingPointComponent;
    let fixture: ComponentFixture<BorderCrossingPointComponent>;
    let service: BorderCrossingPointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCretificationTestModule],
        declarations: [BorderCrossingPointComponent],
      })
        .overrideTemplate(BorderCrossingPointComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BorderCrossingPointComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BorderCrossingPointService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BorderCrossingPoint(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.borderCrossingPoints && comp.borderCrossingPoints[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
