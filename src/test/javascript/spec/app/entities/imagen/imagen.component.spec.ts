/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TruequeAppTestModule } from '../../../test.module';
import { ImagenComponent } from 'app/entities/imagen/imagen.component';
import { ImagenService } from 'app/entities/imagen/imagen.service';
import { Imagen } from 'app/shared/model/imagen.model';

describe('Component Tests', () => {
    describe('Imagen Management Component', () => {
        let comp: ImagenComponent;
        let fixture: ComponentFixture<ImagenComponent>;
        let service: ImagenService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequeAppTestModule],
                declarations: [ImagenComponent],
                providers: []
            })
                .overrideTemplate(ImagenComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImagenComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImagenService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Imagen(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.imagens[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
