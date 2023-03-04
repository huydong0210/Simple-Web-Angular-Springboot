import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./components/form-login/register/register.component";
import {HomeComponent} from "./components/home/home.component";
import {LoginComponent} from "./components/form-login/login/login.component";

const routes: Routes = [
  {path : 'register', component:RegisterComponent, data :{title: 'Register'}},
  {path : 'home',component : HomeComponent},
  {path : 'login',component :LoginComponent, data: {title: 'login'}},
  {path : '**',component: HomeComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
