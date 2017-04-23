import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { InstancesComponent } from './instances/instances.component';
import { InstanceComponent } from './instance/instance.component';

@NgModule({
  declarations: [
    AppComponent,
    InstancesComponent,
    InstanceComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
