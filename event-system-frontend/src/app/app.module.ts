import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AddPurchaseComponent } from './add-purchase/add-purchase.component';
import { ConfigurationComponent } from './configuration/configuration.component';
import {HttpClientModule} from "@angular/common/http";
import { LandingPageComponent } from './landing-page/landing-page.component';
import { DashboardComponent} from "./dashboard/dashboard.component";

@NgModule({
  declarations: [
    AppComponent,
    AddPurchaseComponent,
    ConfigurationComponent,
    LandingPageComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
