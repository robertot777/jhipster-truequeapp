import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { TruequeAppProductoModule } from './producto/producto.module';
import { TruequeAppImagenModule } from './imagen/imagen.module';
import { TruequeAppTruequeModule } from './trueque/trueque.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        TruequeAppProductoModule,
        TruequeAppImagenModule,
        TruequeAppTruequeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequeAppEntityModule {}
