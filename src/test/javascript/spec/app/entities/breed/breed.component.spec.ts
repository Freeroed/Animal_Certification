import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AnimalCertificationTestModule } from '../../../test.module';
import { BreedComponent } from 'app/entities/breed/breed.component';
import { BreedService } from 'app/entities/breed/breed.service';
import { Breed } from 'app/shared/model/breed.model';

describe('Component Tests', () => {
  describe('Breed Management Component', () => {
    let comp: BreedComponent;
    let fixture: ComponentFixture<BreedComponent>;
    let service: BreedService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [BreedComponent],
      })
        .overrideTemplate(BreedComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BreedComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BreedService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Breed(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.breeds && comp.breeds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
