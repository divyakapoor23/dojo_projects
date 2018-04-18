let AuthorController = require("../controllers/AuthorController.js");
let path = require('path');

module.exports = function(app){
    app.get("/authors", AuthorController.all);
    app.post("/authors", AuthorController.create);
    app.get("/authors/:id", AuthorController.show);
    app.put("/authors/:id", AuthorController.edit);
    app.delete("/authors/:id", AuthorController.delete);  
    app.all("**", (request, response) => { response.sendFile(path.resolve("./client/dist/index.html")) });
    // app.all lets any requests that dont fit the backend routes go to the frontend routes and see if they exist there 
    /* remember that you'll need to require path for this to work! */
}