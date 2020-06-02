import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AnimalCertificationTestModule } from '../../../test.module';
import { BreedUpdateComponent } from 'app/entities/breed/breed-update.component';
import { BreedService } from 'app/entities/breed/breed.service';
import { Breed } from 'app/shared/model/breed.model';

describe('Component Tests', () => {
  describe('Breed Management Update Component', () => {
    let comp: BreedUpdateComponent;
    let fixture: ComponentFixture<BreedUpdateComponent>;
    let service: BreedService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [BreedUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BreedUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BreedUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BreedService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Breed(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Breed();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
