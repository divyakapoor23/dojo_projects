let Note = require("mongoose").model("Note");

class NoteController{
    all(req, res) { 
        Note.find({}, (err, notes)=> {
            if(err) {
                res.json({errors: err});
            }
            else {
                
                res.json(notes);
            }
        })
    }
    create(req, res) {
        let note = new Note(req.body);
        note.save(function(err) {
            if(err) {
                res.json({errors: err});
            }
            else {
                res.json(note);        
            }
        }) 
    }
    show(req, res) {
        Note.find({_id: req.params.id}, (err, note)=> {
            if(err) {
                res.json({errors: err})
            }
            else {
                res.json(note);            
            }
        })
    }
    update(req, res) {   
        // .update() function deprecated, not validating automatically, not changing updatedAt 
        Note.update({_id: req.params.id}, req.body, function(err) {
            if(err) {
                res.json({errors: err})
            }
            else {
                res.json({success: "Note successfully updated"});                  
            }
        });

        // Note.findOne({_id: req.params.id}, (err, note)=> {
        //     if(err) {
        //         res.json({errors: "Failed to find note"});                    
        //     }
        //     else {
        //         note.content = req.body.content || note.content; //never let something be null, keep the old info if the form is empty
        //         // note.updatedAt = new Date();
        //         note.save (err=> {
        //             if(err) {
        //                 res.json({errors: err});
        //             }
        //             else {
        //                 res.json(note);
        //             }
        //         })
        //     }
        // })
    }
    destroy(req, res) {
        Note.remove({_id: req.params.id}, (err)=> {
            if(err) {
                res.json({errors: "Error finding Note to delete", err})
            }
            else {            
                res.json("Note successfully deleted");                   
            }
        })
    }
}
module.exports = new NoteController(); 
