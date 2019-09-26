var express = require('express');
var app = express();

app.get('/', function (req, res) {
   res.send('Hello World  from my Node.js Express App!');
})

var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("host: " + host);
  console.log("port: " + port);
  console.log("Hello World! Node.js express server istening at http://%s:%s", host, port)

})