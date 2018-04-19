import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";

import { NoteService } from './note.service';

import { AppComponent } from './app.component';
import { ScrollComponent } from './scroll/scroll.component';
import { NewnoteComponent } from './newnote/newnote.component';


@NgModule({
  declarations: [
    AppComponent,
    ScrollComponent,
    NewnoteComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [NoteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
