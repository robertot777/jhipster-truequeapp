import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { ITrueque } from 'app/shared/model/trueque.model';
import { TruequeService } from './trueque.service';
import { IProducto } from 'app/shared/model/producto.model';
import { ProductoService } from 'app/entities/producto';

@Component({
    selector: 'jhi-trueque-update',
    templateUrl: './trueque-update.component.html'
})
export class TruequeUpdateComponent implements OnInit {
    trueque: ITrueque;
    isSaving: boolean;

    entregas: IProducto[];

    recibes: IProducto[];
    fechaDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected truequeService: TruequeService,
        protected productoService: ProductoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ trueque }) => {
            this.trueque = trueque;
        });
        this.productoService.query({ filter: 'trueque-is-null' }).subscribe(
            (res: HttpResponse<IProducto[]>) => {
                if (!this.trueque.entregaId) {
                    this.entregas = res.body;
                } else {
                    this.productoService.find(this.trueque.entregaId).subscribe(
                        (subRes: HttpResponse<IProducto>) => {
                            this.entregas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.productoService.query({ filter: 'trueque-is-null' }).subscribe(
            (res: HttpResponse<IProducto[]>) => {
                if (!this.trueque.recibeId) {
                    this.recibes = res.body;
                } else {
                    this.productoService.find(this.trueque.recibeId).subscribe(
                        (subRes: HttpResponse<IProducto>) => {
                            this.recibes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.trueque.id !== undefined) {
            this.subscribeToSaveResponse(this.truequeService.update(this.trueque));
        } else {
            this.subscribeToSaveResponse(this.truequeService.create(this.trueque));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrueque>>) {
        result.subscribe((res: HttpResponse<ITrueque>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProductoById(index: number, item: IProducto) {
        return item.id;
    }
}
