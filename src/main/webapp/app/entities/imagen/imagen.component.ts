import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IImagen } from 'app/shared/model/imagen.model';
import { AccountService } from 'app/core';
import { ImagenService } from './imagen.service';

@Component({
    selector: 'jhi-imagen',
    templateUrl: './imagen.component.html'
})
export class ImagenComponent implements OnInit, OnDestroy {
    imagens: IImagen[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected imagenService: ImagenService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.imagenService.query().subscribe(
            (res: HttpResponse<IImagen[]>) => {
                this.imagens = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImagens();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IImagen) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInImagens() {
        this.eventSubscriber = this.eventManager.subscribe('imagenListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
