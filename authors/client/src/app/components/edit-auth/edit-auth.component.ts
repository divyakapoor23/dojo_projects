import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { AuthorService } from '../../services/author.service';

@Component({
  selector: 'app-edit-auth',
  templateUrl: './edit-auth.component.html',
  styleUrls: ['./edit-auth.component.css']
})
export class EditAuthComponent implements OnInit {
  private authID: any;
  private editAuth: any;
  private errors: any;
  private success: any;
  constructor(private route:ActivatedRoute, private router: Router, private authServ: AuthorService) { }

  ngOnInit() {
    this.show();
  }
  show() {
    this.route.params
    .subscribe(data=> {
        this.authID = data.id;
        this.authServ.show(this.authID, (data2)=>{
          this.editAuth = data2[0];
        })
      }
    );
  }
  edit() {
    console.log("hello1");
    this.authServ.edit(this.editAuth, (data)=>{ 
      if(data.errors) {
        // console.log("hello err");        
        this.errors = data.errors;
        this.success = null;
      }
      else {
        // console.log("hello 6");
        this.errors = null;
        this.success = "Author edited!";
        this.show();
        
      }
    });
  }
  ngOnDestroy(): void {
    this.success = null;
    this.errors = null;
    this.editAuth = null;
  }
}
