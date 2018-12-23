/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TruequeAppTestModule } from '../../../test.module';
import { TruequeDeleteDialogComponent } from 'app/entities/trueque/trueque-delete-dialog.component';
import { TruequeService } from 'app/entities/trueque/trueque.service';

describe('Component Tests', () => {
    describe('Trueque Management Delete Component', () => {
        let comp: TruequeDeleteDialogComponent;
        let fixture: ComponentFixture<TruequeDeleteDialogComponent>;
        let service: TruequeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequeAppTestModule],
                declarations: [TruequeDeleteDialogComponent]
            })
                .overrideTemplate(TruequeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TruequeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TruequeService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
