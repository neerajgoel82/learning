var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var WebSocketServer = require('ws').Server
    , wss = new WebSocketServer({ port: 8080 });

app.get('/', function(req, res){
    res.sendFile(__dirname + '/index.html');
});

app.get('/client.js', function(req, res){
    res.sendFile(__dirname + '/client.js');
});

io.on('connection', function(socket){
    console.log('a user connected');

    socket.on('disconnect', function(){
        console.log('user disconnected');
    });

    socket.on('chat message', function(msg){
        console.log('message: ' + msg);
    });
});

wss.on('connection', function connection(ws) {
    console.log('a ws user connected');
    ws.on('message', function incoming(message) {
        console.log('ws message: %s', message);
    });
    ws.on('disconnect', function(){
        console.log('ws user disconnected');
    });

});


http.listen(3000, function(){
    console.log('listening on *:3000');
});