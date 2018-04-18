import { Injectable } from '@angular/core';
import {HttpClient } from "@angular/common/http";

@Injectable()
export class AuthorService {

  constructor(private http: HttpClient) { }

  all(cb) {
    this.http.get("/authors")
    .subscribe(data=>cb(data));
  }
  show(id, cb) {
    this.http.get("/authors/"+id)
    .subscribe(data=>cb(data));
  }
  create(author, cb) { 
    this.http.post("/authors", author) 
    .subscribe(data=>cb(data)); 
  } 
  edit(author, cb) { 
    // console.log("hello2");
    this.http.put("/authors/"+author._id, author) 
    .subscribe(data=>cb(data)); 
  } 
  delete(id, cb) {
    this.http.delete("/authors/"+id)
    .subscribe(data=>cb(data));
  }
}
