let mongoose = require('mongoose');
let ObjectId = mongoose.Schema.Types.ObjectId;

mongoose.model("Note", new mongoose.Schema({
    content:{type:String, required:true, minlength:3, maxlength:255}, 
    }, {timestamps: true} 
));