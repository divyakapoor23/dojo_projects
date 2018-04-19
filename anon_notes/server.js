var express = require('express'),
    path = require('path'),
    bodyParser = require('body-parser');

var app = express(); 

app.use(bodyParser.urlencoded({ extended: true })); 
app.use(express.json()); 
app.use(express.static(__dirname+"/client/dist")); 

require("./server/config/mongoose.js");
require("./server/config/routes.js")(app);

app.listen(6789, function() {
    console.log("listening on port 6789");
})