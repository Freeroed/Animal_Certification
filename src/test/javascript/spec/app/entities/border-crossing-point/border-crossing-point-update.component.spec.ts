import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AnimalCretificationTestModule } from '../../../test.module';
import { BorderCrossingPointUpdateComponent } from 'app/entities/border-crossing-point/border-crossing-point-update.component';
import { BorderCrossingPointService } from 'app/entities/border-crossing-point/border-crossing-point.service';
import { BorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';

describe('Component Tests', () => {
  describe('BorderCrossingPoint Management Update Component', () => {
    let comp: BorderCrossingPointUpdateComponent;
    let fixture: ComponentFixture<BorderCrossingPointUpdateComponent>;
    let service: BorderCrossingPointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCretificationTestModule],
        declarations: [BorderCrossingPointUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BorderCrossingPointUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BorderCrossingPointUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BorderCrossingPointService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BorderCrossingPoint(123);
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
        const entity = new BorderCrossingPoint();
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
