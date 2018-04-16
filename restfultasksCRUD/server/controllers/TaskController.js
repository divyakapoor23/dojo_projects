let mongoose = require("mongoose");  
let Task = mongoose.model("Task");

class TaskController{
    all(req, res) { 
        Task.find({}, (err, tasks)=> {
            if(err) {
                console.log("Error loading all tasks");
                res.json({errors: err});
            }
            else {
                
                res.json(tasks);
            }
        })
    }
    create(req, res) {
        let task = new Task(req.body);
        console.log(task);
        task.save(function(err) {
            if(err) {
                console.log("not saving!");
                res.json({errors: err});
            }
            else {
                console.log("saving!");
                res.json(task);        
            }
        }) 
    }
    show(req, res) {
        Task.find({_id: req.params.id}, (err, task)=> {
            if(err) {
                console.log("Error finding task");
                res.json({errors: err})
            }
            else {
                res.json(task);            
            }
        })
    }
    edit(req, res) {         
        Task.update({_id: req.params.id}, req.body, function(err) {
            if(err) {
                res.json({message: "Error updating task", error: err})
            }
            else {
                res.json("Task successfully update");                  
            }
        });
    }
    delete(req, res) {
        Task.remove({_id: req.params.id}, (err)=> {
            if(err) {
                res.json({message: "Error finding task to delete", error: err})
            }
            else {            
                res.json("Task successfully delete");                   
            }
        })
    }
}
module.exports = new TaskController(); 
