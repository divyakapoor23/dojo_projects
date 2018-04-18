let mongoose = require("mongoose");
let Author = mongoose.model("Author");

class AuthorController{
    all(req, res) { 
        Author.find({}, (err, authors)=> {
            if(err) {
                res.json({errors: err});
            }
            else {
                
                res.json(authors);
            }
        })
    }
    create(req, res) {
        let author = new Author(req.body);
        author.save(function(err) {
            if(err) {
                res.json({errors: err});
            }
            else {
                res.json(author);        
            }
        }) 
    }
    show(req, res) {
        Author.find({_id: req.params.id}, (err, author)=> {
            if(err) {
                res.json({errors: err})
            }
            else {
                res.json(author);            
            }
        })
    }
    edit(req, res) { 
        // console.log("hello3", req.body);         
        if(req.body.name.length<3) {
            // console.log("bad length, name: ", req.body.name);
            // console.log(req.body.name.$valid);
        }       
        Author.update({_id: req.params.id}, req.body, function(err) {
            if(err) {
                // console.log("hello4");                
                res.json({error: err})
            }
            else {
                // console.log("hello5");                
                res.json({success: "Author successfully updated"});                  
            }
        });
    }
    delete(req, res) {
        Author.remove({_id: req.params.id}, (err)=> {
            if(err) {
                res.json({message: "Error finding Author to delete", error: err})
            }
            else {            
                res.json("Author successfully deleted");                   
            }
        })
    }
}
module.exports = new AuthorController(); 
