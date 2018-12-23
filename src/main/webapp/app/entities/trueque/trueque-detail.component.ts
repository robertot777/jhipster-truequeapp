import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrueque } from 'app/shared/model/trueque.model';

@Component({
    selector: 'jhi-trueque-detail',
    templateUrl: './trueque-detail.component.html'
})
export class TruequeDetailComponent implements OnInit {
    trueque: ITrueque;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ trueque }) => {
            this.trueque = trueque;
        });
    }

    previousState() {
        window.history.back();
    }
}
