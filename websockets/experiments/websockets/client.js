$( document ).ready(function() {
    var socket = io('http://ws.auditude.com');
    $('#messageForm').submit(function() {
        socket.emit('chat message', $('#m').val());
        $('#m').val('');
        return false;
    });

    var ws = new WebSocket('ws://ws.auditude.com:8080');
    $('#wsMessageForm').submit(function() {
       ws.send($('#wsm').val());
        $('#wsm').val('');
        return false;
    });
});

