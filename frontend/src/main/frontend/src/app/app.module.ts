import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { CloudInstanceComponent } from './cloud-instance/cloud-instance.component';
import { CloudInstancesTableComponent } from './cloud-instances-table/cloud-instances-table.component';
import { CloudsService } from './clouds.service';

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    CloudInstanceComponent,
    CloudInstancesTableComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [CloudsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
