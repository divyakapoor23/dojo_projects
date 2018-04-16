import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  private newtask: any;
  private alltasks: any;
  private showtask:any;
  private edittask:any;  
  constructor(private taskServ: TaskService) {
    
  }
  ngOnInit() {
    this.newtask = {
      title: "",
      description: "",
      completed: false
    };
  }

  create() { 
    this.taskServ.create(this.newtask, (data)=>{ 
      this.newtask = { title: "", description: "", completed: false }; //resets new task
    });
  }
  all() {
    this.taskServ.getTasks(data=>{
      this.alltasks = data;
    });
  }
  show(id) {
    this.taskServ.show(id, (data)=>{
      this.showtask = data[0];
    });
  }
  editform(id) {
    this.taskServ.show(id, (data)=>{
      this.edittask = data[0];
      
    });
  }
  edit() {
    this.taskServ.edit(this.edittask, (data)=>{ 
      this.show(this.edittask._id);
      this.all();
      this.edittask = null;
    });
  }
  delete(id) {
    this.taskServ.delete(id, (data)=>{ 
      this.all();
    });
  }

}
