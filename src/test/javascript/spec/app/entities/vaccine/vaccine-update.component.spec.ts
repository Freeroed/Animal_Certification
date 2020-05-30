import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AnimalCretificationTestModule } from '../../../test.module';
import { VaccineUpdateComponent } from 'app/entities/vaccine/vaccine-update.component';
import { VaccineService } from 'app/entities/vaccine/vaccine.service';
import { Vaccine } from 'app/shared/model/vaccine.model';

describe('Component Tests', () => {
  describe('Vaccine Management Update Component', () => {
    let comp: VaccineUpdateComponent;
    let fixture: ComponentFixture<VaccineUpdateComponent>;
    let service: VaccineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCretificationTestModule],
        declarations: [VaccineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VaccineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VaccineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VaccineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Vaccine(123);
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
        const entity = new Vaccine();
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
