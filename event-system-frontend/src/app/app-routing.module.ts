import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AddPurchaseComponent} from "./add-purchase/add-purchase.component";
import {ConfigurationComponent} from "./configuration/configuration.component";
import { LandingPageComponent } from './landing-page/landing-page.component';
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: LandingPageComponent },
  { path: 'configuration', component: ConfigurationComponent },
  { path: 'add-purchase', component: AddPurchaseComponent },
  { path: 'dashboard', component: DashboardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
