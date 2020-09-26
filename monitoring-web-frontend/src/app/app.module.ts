import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { TaharokatChart } from './taharokat/chart/taharokat-chart';
import {MaterialModule} from './material.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxChartsModule }from '@swimlane/ngx-charts';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import {
  HttpClientModule
} from '@angular/common/http';
import {AppComponent} from "./app.component";
import {RouterModule} from "@angular/router";
import {TaharokatList} from "./taharokat/list/taharokat-list";
import {RestService} from "./rest-service";
import {TaharokatService} from "./taharokat/taharokat.service";


let appRoutes=[
  {path: 'list',component: TaharokatList},
  {path: 'chart',component: TaharokatChart},
  {path: '',component: TaharokatList}
  ];

@NgModule({
  declarations: [
    AppComponent,
    TaharokatList,
    TaharokatChart,
  ],
  imports: [
    BrowserModule,

    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgxChartsModule,
    MaterialModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [RestService, TaharokatService],
  bootstrap: [AppComponent]
})
export class AppModule { }
