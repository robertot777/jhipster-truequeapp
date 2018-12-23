/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TruequeAppTestModule } from '../../../test.module';
import { TruequeDetailComponent } from 'app/entities/trueque/trueque-detail.component';
import { Trueque } from 'app/shared/model/trueque.model';

describe('Component Tests', () => {
    describe('Trueque Management Detail Component', () => {
        let comp: TruequeDetailComponent;
        let fixture: ComponentFixture<TruequeDetailComponent>;
        const route = ({ data: of({ trueque: new Trueque(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequeAppTestModule],
                declarations: [TruequeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TruequeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TruequeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.trueque).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
