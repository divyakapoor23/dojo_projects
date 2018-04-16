import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class TaskService {

  constructor(private http: HttpClient) {
    
  }

  getTasks(cb) {
    this.http.get("/tasks")
    .subscribe(data=>cb(data));
  }
  show(id, cb) {
    console.log(id);
    this.http.get("/tasks/"+id)
    .subscribe(data=>cb(data));
  }
  create(task, cb) { 
    this.http.post("/tasks", task) 
    .subscribe(data=>cb(data)); 
  } 
  edit(task, cb) { 
    this.http.put("/tasks/"+task._id, task) 
    .subscribe(data=>cb(data)); 
  } 
  delete(id, cb) {
    console.log(id);
    this.http.delete("/tasks/"+id)
    .subscribe(data=>cb(data));
  }
}
