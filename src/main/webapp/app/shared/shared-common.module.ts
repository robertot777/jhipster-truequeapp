import { NgModule } from '@angular/core';

import { TruequeAppSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [TruequeAppSharedLibsModule],
  declarations: [FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent],
  exports: [TruequeAppSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent]
})
export class TruequeAppSharedCommonModule {}
