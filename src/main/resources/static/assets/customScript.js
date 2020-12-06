var modal;
var stompClient = null;
$(document).ready(function(){

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            $(".modal").css("display", "none");
        }
    }

    $(document).on("click", ".close", function(e){
        $(".modal").css("display", "none");
    });
    connect();

    //Load details when page is loaded
    $.ajax({
        url         : "/Playlist/all",
        method      : "get",
        contentType : "application/json; charset=utf-8",
        success     : function(data){
            loadData(data);
        }
    });
    $('[data-toggle="tooltip"]').tooltip();
});

function connect() {
    var socket = new SockJS('/playlist-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/getAllPlaylist', function (greeting) {
            loadData(JSON.parse(greeting.body));
        });
    });
}

function newPlaylist(){
    $("#playlistName").val("");
    $("#playlistModal").css("display", "block");
    modal = document.getElementById("playlistModal");
}

function newSong(playlistId){
    $("#songName").val("");
    $("#singer").val("");
    $("#playlistId").val(playlistId);
    $("#songModal").css("display", "block");
    modal = document.getElementById("songModal");
}

function savePlaylist(){
    var playlist    = {};
    playlist["playlistName"]    = $("#playlistName").val();
    if(playlist["playlistName"].trim() == ""){
        alert("Enter a name for playlist");
        return false;
    }
    $.ajax({
        url         : "/Playlist/addPlaylist",
        method      : "post",
        contentType : "application/json; charset=utf-8",
        dataType    : 'json',
        data        : JSON.stringify(playlist),
        success     : function(data){
            if(data=="Duplicate Entry"){
                alert("Playlist name already used");
                $("#playlistName").val("");
            }else{
                $("#playlistModal").css("display", "none");
                stompClient.send("/allPlaylist");
                new PNotify({
                    title: 'SUCCESS',
                    text: 'Saved Playlist Successfully',
                    type: 'success',
                    styling: 'brighttheme'
                });
            }
        }
    });
}

function deletePlaylist(playlistId){
    $.ajax({
        url         : "/Playlist/deletePlaylist?playlistId="+playlistId,
        method      : "post",
        contentType : "application/json; charset=utf-8",
        success     : function(data){
            stompClient.send("/allPlaylist");
            new PNotify({
                title: 'SUCCESS',
                text: 'Deleted Playlist Successfully',
                type: 'success',
                styling: 'brighttheme'
            });
        }
    });
}

function saveSong(){
    var song    = {};
    song["name"]   = $("#songName").val();
    song["singer"] = $("#singer").val();
    song["playlist"] = {"playlistId": $("#playlistId").val()};
    if(song["name"].trim() == "" || song["singer"].trim() == ""){
        alert("Enter song details");
        return false;
    }
    $.ajax({
        url         : "/Playlist/addPlaylistSong",
        method      : "post",
        contentType : "application/json; charset=utf-8",
        dataType    : 'json',
        data        : JSON.stringify(song),
        success     : function(data){
            $("#songModal").css("display", "none");
            stompClient.send("/allPlaylist");
            new PNotify({
                title: 'SUCCESS',
                text: 'Saved Song Successfully',
                type: 'success',
                styling: 'brighttheme'
            });
        }
    });
}

function deleteSong(songId){
    $.ajax({
        url         : "/Playlist/deletePlaylistSong?songId="+songId,
        method      : "post",
        contentType : "application/json; charset=utf-8",
        data        : "songId="+songId,
        success     : function(data){
            stompClient.send("/allPlaylist");
            new PNotify({
                title: 'SUCCESS',
                text: 'Deleted Playlist Successfully',
                type: 'success',
                styling: 'brighttheme'
            });
        }
    });
}

function loadData(playlistJSON){
    var divContent  = "";
    $.each(playlistJSON, function(i, obj){
        divContent  += '<div class="row" style="min-height: 50px;">'+
                            '<div class="col-md-2 col-md-offset-2 cellBorder wordWrap" >'+obj.playlistName+'</div>'+
                            '<div class="col-md-8 col-md-offset-8 centerCellBorder">';
        $.each(obj.songsList, function(index, songObj){
            divContent +=       '<div class="row">'+
                                    '<div class="col-md-5 wordWrap">'+songObj.name+'</div>'+
                                    '<div class="col-md-5 wordWrap">'+songObj.singer+'</div>'+
                                    '<div class="col-md-2"><a href="javascript:void(0);" onclick="deleteSong('+songObj.songId+');" class="delete" data-toggle="tooltip" data-placement="top" title="Delete Song"><i class="fa fa-trash-o" aria-hidden="true"></i></a></div>'+
                                '</div>';
        });
        divContent +=       '</div>'+
                            '<div class="col-md-2 col-md-offset-2 cellBorder">'+
                                '<div class="row">'+
                                    '<div class="col-md-6"><a href="javascript:void(0);" onclick="newSong('+obj.playlistId+');" data-toggle="tooltip" data-placement="top" title="Add Song to Playlist"><i class="fa fa-plus" aria-hidden="true"></i></a></div>'+
                                    '<div class="col-md-6"><a href="javascript:void(0);" onclick="deletePlaylist('+obj.playlistId+');" class="delete" data-toggle="tooltip" data-placement="top" title="Delete Playlist"><i class="fa fa-trash-o" aria-hidden="true"></i></a></div>'+
                                '</div>'+
                            '</div>'+
                        '</div>';
    });
    $("#playlistContent").html(divContent);
}