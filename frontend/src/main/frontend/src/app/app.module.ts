import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { StormpathModule } from 'angular-stormpath';

import { AppComponent } from './app.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { CloudInstancesTableComponent } from './cloud-instances-table/cloud-instances-table.component';
import { CloudsService } from './services/clouds.service';
import { CloudInstanceService} from './services/cloud-instance.service';



@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    CloudInstancesTableComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    StormpathModule
  ],
  providers: [CloudsService, CloudInstanceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
