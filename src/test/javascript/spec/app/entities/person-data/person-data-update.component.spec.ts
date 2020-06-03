import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AnimalCertificationTestModule } from '../../../test.module';
import { PersonDataUpdateComponent } from 'app/entities/person-data/personal-data-update.component';
import { PersonDataService } from 'app/entities/person-data/personal-data.service';
import { PersonData } from 'app/shared/model/personal-data.model';

describe('Component Tests', () => {
  describe('PersonalData Management Update Component', () => {
    let comp: PersonDataUpdateComponent;
    let fixture: ComponentFixture<PersonDataUpdateComponent>;
    let service: PersonDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [PersonDataUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PersonDataUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersonDataUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersonDataService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PersonData(123);
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
        const entity = new PersonData();
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
