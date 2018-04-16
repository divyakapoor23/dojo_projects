let TaskController = require("../controllers/TaskController.js");

module.exports = function(app){
    app.get("/tasks", TaskController.all);
    app.post("/tasks", TaskController.create);
    app.get("/tasks/:id", TaskController.show);
    app.put("/tasks/:id", TaskController.edit);
    app.delete("/tasks/:id", TaskController.delete);    
}