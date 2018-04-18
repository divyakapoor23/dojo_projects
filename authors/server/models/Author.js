let mongoose = require('mongoose');


mongoose.model("Author", new mongoose.Schema({
    name: {
        type:String, 
        require: [true, "Author name is required"],
        minlength: [3, "Author name must be at least 3 characters long"],
        maxlength: [255, "Author name cannot be more than 255 characters long"], 
        // unique: [true, "This author already exists"]
        }
    }, {timestamps: true} 
));