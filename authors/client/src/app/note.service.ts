import { Injectable } from '@angular/core';
import {HttpClient } from "@angular/common/http";

@Injectable()
export class NoteService {

  constructor(private http: HttpClient) { }

  all(cb) {
    this.http.get("/notes")
    .subscribe(data=>cb(data));
  }
  show(id, cb) {
    this.http.get("/notes/"+id)
    .subscribe(data=>cb(data));
  }
  create(note, cb) { 
    this.http.post("/notes", note) 
    .subscribe(data=>cb(data)); 
  } 
  update(note, cb) { 
    this.http.put("/notes/"+note._id, note) 
    .subscribe(data=>cb(data)); 
  } 
  destroy(id, cb) {
    this.http.delete("/notes/"+id)
    .subscribe(data=>cb(data));
  }
}
