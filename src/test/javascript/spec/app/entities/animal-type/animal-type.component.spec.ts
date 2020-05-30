import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AnimalCretificationTestModule } from '../../../test.module';
import { AnimalTypeComponent } from 'app/entities/animal-type/animal-type.component';
import { AnimalTypeService } from 'app/entities/animal-type/animal-type.service';
import { AnimalType } from 'app/shared/model/animal-type.model';

describe('Component Tests', () => {
  describe('AnimalType Management Component', () => {
    let comp: AnimalTypeComponent;
    let fixture: ComponentFixture<AnimalTypeComponent>;
    let service: AnimalTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCretificationTestModule],
        declarations: [AnimalTypeComponent],
      })
        .overrideTemplate(AnimalTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AnimalTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AnimalTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AnimalType(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.animalTypes && comp.animalTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
