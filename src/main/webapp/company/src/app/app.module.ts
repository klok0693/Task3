import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { EmployeeComponent }  from './employee/employee.component';
import { HomeComponent} from "./home/home.component";

import { routing }  from './app.routing';
import { LoginComponent } from './login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import { UserComponent } from './user/user.component';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {Interceptor} from "./login/Interceptor";

@NgModule({
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    }
  ],
  imports: [CommonModule, BrowserModule, HttpClientModule, FormsModule, ReactiveFormsModule, routing],
  declarations: [EmployeeComponent, AppComponent, HomeComponent, LoginComponent, UserComponent],
  bootstrap: [AppComponent, EmployeeComponent, HomeComponent, LoginComponent]
})
export class AppModule { }
