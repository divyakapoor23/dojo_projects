import { Component, Input } from '@angular/core';
import { NoteService } from './note.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  private notes: any;
  private reverse: any;
  constructor(private noteServ:NoteService) { 

  }
  ngOnInit(){
    this.all();
  }

   all() {
    this.noteServ.all(data=>{
      this.reverse = data;
      this.notes = this.reverse.reverse();
      console.log(this.notes);
    });
  }

}
