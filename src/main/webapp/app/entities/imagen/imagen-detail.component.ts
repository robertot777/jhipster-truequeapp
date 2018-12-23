import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IImagen } from 'app/shared/model/imagen.model';

@Component({
    selector: 'jhi-imagen-detail',
    templateUrl: './imagen-detail.component.html'
})
export class ImagenDetailComponent implements OnInit {
    imagen: IImagen;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imagen }) => {
            this.imagen = imagen;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
