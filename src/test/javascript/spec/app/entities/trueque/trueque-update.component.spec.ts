/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { TruequeAppTestModule } from '../../../test.module';
import { TruequeUpdateComponent } from 'app/entities/trueque/trueque-update.component';
import { TruequeService } from 'app/entities/trueque/trueque.service';
import { Trueque } from 'app/shared/model/trueque.model';

describe('Component Tests', () => {
    describe('Trueque Management Update Component', () => {
        let comp: TruequeUpdateComponent;
        let fixture: ComponentFixture<TruequeUpdateComponent>;
        let service: TruequeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequeAppTestModule],
                declarations: [TruequeUpdateComponent]
            })
                .overrideTemplate(TruequeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TruequeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TruequeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Trueque(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.trueque = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Trueque();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.trueque = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
