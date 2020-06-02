import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AnimalCertificationTestModule } from '../../../test.module';
import { AnimalTypeUpdateComponent } from 'app/entities/animal-type/animal-type-update.component';
import { AnimalTypeService } from 'app/entities/animal-type/animal-type.service';
import { AnimalType } from 'app/shared/model/animal-type.model';

describe('Component Tests', () => {
  describe('AnimalType Management Update Component', () => {
    let comp: AnimalTypeUpdateComponent;
    let fixture: ComponentFixture<AnimalTypeUpdateComponent>;
    let service: AnimalTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [AnimalTypeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AnimalTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AnimalTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AnimalTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AnimalType(123);
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
        const entity = new AnimalType();
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
