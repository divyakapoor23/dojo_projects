import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { AuthorService } from '../../services/author.service';

@Component({
  selector: 'app-new-auth',
  templateUrl: './new-auth.component.html',
  styleUrls: ['./new-auth.component.css']
})
export class NewAuthComponent implements OnInit {
  private newAuth: any;
  private errors: any;
  private success: any;
  constructor(private route:ActivatedRoute, private router: Router, private authServ: AuthorService) { }

  ngOnInit() {
    this.newAuth = { name: "" };
  }

  create() { 
    this.authServ.create(this.newAuth, (data)=>{ 
      if(data.errors) {
        this.errors = data.errors;
        this.success = null;
      }
      else {
        this.errors = null;
        this.newAuth = { name: "" };
        this.success = "New Author created!";
      }
    });
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    this.success = null;
    this.errors = null;
    this.newAuth = null;
  }
}
