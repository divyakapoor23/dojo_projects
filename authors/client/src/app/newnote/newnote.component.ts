import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NoteService } from '../note.service';
import { Note } from '../../../../server/models/note';

@Component({
  selector: 'app-newnote',
  templateUrl: './newnote.component.html',
  styleUrls: ['./newnote.component.css']
})
export class NewnoteComponent implements OnInit {
  private newnote: any;
  @Output() myEvent = new EventEmitter();

  constructor(private noteServ:NoteService) { }

  ngOnInit() {
    this.newnote = {
      content: ""
    }
  }
  
  create() { 
    this.noteServ.create(this.newnote, (data)=>{ 
      this.newnote = { name: "" };
      console.log("Success", data);
      this.myEvent.emit(data);
    })
  }
}
