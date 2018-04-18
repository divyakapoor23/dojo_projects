import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { AuthorComponent } from './components/author/author.component';
import { NewAuthComponent } from './components/new-auth/new-auth.component';
import { EditAuthComponent } from './components/edit-auth/edit-auth.component';

import { AuthorService } from './services/author.service';


@NgModule({
  declarations: [
    AppComponent,
    AuthorComponent,
    NewAuthComponent,
    EditAuthComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AuthorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
