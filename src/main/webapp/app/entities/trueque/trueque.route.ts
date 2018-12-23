import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Trueque } from 'app/shared/model/trueque.model';
import { TruequeService } from './trueque.service';
import { TruequeComponent } from './trueque.component';
import { TruequeDetailComponent } from './trueque-detail.component';
import { TruequeUpdateComponent } from './trueque-update.component';
import { TruequeDeletePopupComponent } from './trueque-delete-dialog.component';
import { ITrueque } from 'app/shared/model/trueque.model';

@Injectable({ providedIn: 'root' })
export class TruequeResolve implements Resolve<ITrueque> {
    constructor(private service: TruequeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Trueque> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Trueque>) => response.ok),
                map((trueque: HttpResponse<Trueque>) => trueque.body)
            );
        }
        return of(new Trueque());
    }
}

export const truequeRoute: Routes = [
    {
        path: 'trueque',
        component: TruequeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'truequeApp.trueque.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'trueque/:id/view',
        component: TruequeDetailComponent,
        resolve: {
            trueque: TruequeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequeApp.trueque.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'trueque/new',
        component: TruequeUpdateComponent,
        resolve: {
            trueque: TruequeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequeApp.trueque.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'trueque/:id/edit',
        component: TruequeUpdateComponent,
        resolve: {
            trueque: TruequeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequeApp.trueque.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const truequePopupRoute: Routes = [
    {
        path: 'trueque/:id/delete',
        component: TruequeDeletePopupComponent,
        resolve: {
            trueque: TruequeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequeApp.trueque.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
