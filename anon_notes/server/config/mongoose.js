let mongoose = require("mongoose"),
    path = require("path"),
    fs = require("fs"),
    modelPath = path.join(__dirname,"./../models");

mongoose.connect("mongodb://localhost/anon_notes");
mongoose.Promise = global.Promise;

fs.readdirSync(modelPath).forEach(function(file){
    if(file.indexOf(".js") >= 0){
        require(modelPath+"/"+file);
    }
})