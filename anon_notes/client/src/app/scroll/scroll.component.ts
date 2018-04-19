import { Component, OnInit, Input, Output } from '@angular/core';
import { NoteService } from '../note.service';

@Component({
  selector: 'app-scroll',
  templateUrl: './scroll.component.html',
  styleUrls: ['./scroll.component.css']
})
export class ScrollComponent implements OnInit {
  @Input() myNotes: any;
  constructor(private noteServ:NoteService) { 

  }

  ngOnInit() {
    
  }


}
