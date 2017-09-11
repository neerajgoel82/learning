$( document ).ready(function() {
    var peer = new Peer( {key: '6wbnz6iesn86ko6r'});
    peer.on('connection', function(conn) {
        console.log("Connection received") ;
        conn.on('data', function(data) {
            console.log("Data received:", data) ;
        });
    });

    peer.on('open', function(id) {
        console.log('Peer 1 ID is: ' + id);
        var peer2 = new Peer( {key: '6wbnz6iesn86ko6r'});
        peer2.on('open', function(id2){
            console.log('Peer 2 ID is: ' + id2);
        });
        var conn = peer2.connect(id);
        conn.on('open', function(){
            conn.send('hi!');
        });
    });



});

