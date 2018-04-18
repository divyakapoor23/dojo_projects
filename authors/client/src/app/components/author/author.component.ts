import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { AuthorService } from '../../services/author.service';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {
  private authors: any;
  constructor(private route:ActivatedRoute, private router: Router, private authServ: AuthorService) { }

  ngOnInit() {
    this.all();
  }
  all() {
    this.authServ.all(data=>{
      this.authors = data;
    });
  }
  delete(id) {
    this.authServ.delete(id, (data)=>{ 
      this.all();
    });
  }
}
