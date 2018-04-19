let NoteController = require("../controllers/NoteController.js");
let path = require('path'); //for app.all("**")

module.exports = function(app){
    // to test urls on Postman before going full MEAN
    // app.get("/api/notes", NoteController.all);
    // app.get("/api/notes/:id", NoteController.show);
    // app.post("/api/notes", NoteController.create);
    // app.put("/api/notes/:id", NoteController.update);
    // app.delete("/api/notes/:id", NoteController.destroy); 

    app.get("/notes", NoteController.all);
    app.get("/notes/:id", NoteController.show);
    app.post("/notes", NoteController.create);
    app.put("/notes/:id", NoteController.update);
    app.delete("/notes/:id", NoteController.destroy); 

    app.all("**", (request, response) => { response.sendFile(path.resolve("./client/dist/index.html")) });
    
}