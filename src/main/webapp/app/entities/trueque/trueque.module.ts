import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TruequeAppSharedModule } from 'app/shared';
import {
    TruequeComponent,
    TruequeDetailComponent,
    TruequeUpdateComponent,
    TruequeDeletePopupComponent,
    TruequeDeleteDialogComponent,
    truequeRoute,
    truequePopupRoute
} from './';

const ENTITY_STATES = [...truequeRoute, ...truequePopupRoute];

@NgModule({
    imports: [TruequeAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TruequeComponent,
        TruequeDetailComponent,
        TruequeUpdateComponent,
        TruequeDeleteDialogComponent,
        TruequeDeletePopupComponent
    ],
    entryComponents: [TruequeComponent, TruequeUpdateComponent, TruequeDeleteDialogComponent, TruequeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequeAppTruequeModule {}
