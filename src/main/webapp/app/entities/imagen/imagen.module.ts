import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TruequeAppSharedModule } from 'app/shared';
import { TruequeAppAdminModule } from 'app/admin/admin.module';
import {
    ImagenComponent,
    ImagenDetailComponent,
    ImagenUpdateComponent,
    ImagenDeletePopupComponent,
    ImagenDeleteDialogComponent,
    imagenRoute,
    imagenPopupRoute
} from './';

const ENTITY_STATES = [...imagenRoute, ...imagenPopupRoute];

@NgModule({
    imports: [TruequeAppSharedModule, TruequeAppAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ImagenComponent, ImagenDetailComponent, ImagenUpdateComponent, ImagenDeleteDialogComponent, ImagenDeletePopupComponent],
    entryComponents: [ImagenComponent, ImagenUpdateComponent, ImagenDeleteDialogComponent, ImagenDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequeAppImagenModule {}
