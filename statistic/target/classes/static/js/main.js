$(document).ready(function(){
    assignButtons()
    $("#Help").text();
    loadDataL(0);
    loadPagesL();
    loadDataR(0);
    loadPagesR();
    loadDataC(0);
    loadPagesC();
    loadDataM(0);
    loadPagesM();
    loadDataP();

});
var is_loaded=false;
function assignButtons() {
    $("#submitButton").click(function (e) {

        if (($("#slugInput").val() != "") ) {
            $("#Help").text("Please wait until post load");
            var slug=$("#slugInput").val()

            var xhr = new XMLHttpRequest();


            xhr.open('POST', 'post?slug='+slug, false);
            xhr.send();
            if (xhr.status != 200) {
                alert( xhr.status + ': ' + xhr.statusText );
            } else {

                setTimeout(function () {

                    window.location = "/";}, 3000);

            }


        }
    });
}
function loadDataL(page) {
    $("#data").empty();

    $.getJSON('/liker?page='+page, function(data) {
        var i;

        for (i = 0; i < data.length; i++) {
            $('#data').append(
                $('<li>')
                    .append(data[i].name+' '+data[i].lastname+"   ")
                    .append($('<img>').attr("src",data[i].avatarURL))


            )



        }
    });
}
function loadPagesL() {
    $("#pagesL").empty();

    $.getJSON('/count?l=0', function(data) {
        var pageCount = (data.count / data.pageSize) +
            (data.count % data.pageSize > 0 ? 1 : 0);
        var i;

        for (i = 1; i <= pageCount; i++) {
            $('#pagesL').append(
                $('<li>').attr('class', 'page-item').append(
                    $('<a>').attr('class', 'page-link').attr('id', i )
                        .append( i))
            );
        }
    });

    $("#pagesL").on("click", ".page-link", function(event) {
        loadDataL(event.target.id);
    });
}
function loadDataR(page) {
    $("#dataR").empty();

    $.getJSON('/repost?page='+page, function(data) {
        var i;

        for (i = 0; i < data.length; i++) {
            $('#dataR').append(
                $('<li>')
                    .append(data[i].name+' '+data[i].lastname+"   ")
                    .append($('<img>').attr("src",data[i].avatarURL))


            )



        }
    });
}
function loadPagesR() {
    $("#pagesR").empty();

    $.getJSON('/count?l=2', function(data) {
        var pageCount = (data.count / data.pageSize) +
            (data.count % data.pageSize > 0 ? 1 : 0);
        var i;

        for (i = 1; i <= pageCount; i++) {
            $('#pagesR').append(
                $('<li>').attr('class', 'page-item').append(
                    $('<a>').attr('class', 'page-link').attr('id', i )
                        .append( i))
            );
        }
    });

    $("#pagesR").on("click", ".page-link", function(event) {
        loadDataR(event.target.id);
    });
}
function loadDataC(page) {
    $("#dataC").empty();

    $.getJSON('/comment?page='+page, function(data) {
        var i;

        for (i = 0; i < data.length; i++) {
            $('#dataC').append(
                $('<li>')
                    .append(data[i].name+' '+data[i].lastname+"   ")
                    .append($('<img>').attr("src",data[i].avatarURL))


            )



        }
    });
}
function loadPagesC() {
    $("#pagesC").empty();

    $.getJSON('/count?l=1', function(data) {
        var pageCount = (data.count / data.pageSize) +
            (data.count % data.pageSize > 0 ? 1 : 0);
        var i;

        for (i = 1; i <= pageCount; i++) {
            $('#pagesC').append(
                $('<li>').attr('class', 'page-item').append(
                    $('<a>').attr('class', 'page-link').attr('id', i )
                        .append( i))
            );
        }
    });

    $("#pagesC").on("click", ".page-link", function(event) {
        loadDataC(event.target.id);
    });
}
function loadDataM(page) {
    $("#dataM").empty();

    $.getJSON('/mention?page='+page, function(data) {
        var i;

        for (i = 0; i < data.length; i++) {
            $('#dataM').append(
                $('<li>')
                    .append(data[i].name+' '+data[i].lastname+"   ")
                    .append($('<img>').attr("src",data[i].avatarURL))


            )



        }
    });
}
function loadPagesM() {
    $("#pagesM").empty();

    $.getJSON('/count?l=3', function(data) {
        var pageCount = (data.count / data.pageSize) +
            (data.count % data.pageSize > 0 ? 1 : 0);
        var i;

        for (i = 1; i <= pageCount; i++) {
            $('#pagesM').append(
                $('<li>').attr('class', 'page-item').append(
                    $('<a>').attr('class', 'page-link').attr('id', i )
                        .append( i))
            );
        }
    });

    $("#pagesM").on("click", ".page-link", function(event) {
        loadDataM(event.target.id);
    });
}
function loadDataP() {


    $.getJSON('/getpost', function(data) {
        if (data.id!=null&&data.slug!=null) {
            $("#l").text("Likes count " + data.likes_count);
            $("#c").text("Comments count " + data.comments_count);
            $("#r").text("Reposts count " + data.reposts_count);
            $("#m").text("Mentions count " + data.mentions_count);
            $("#b").text("Bookmarks count " + data.bookmarks_count);
            $("#v").text("Views count " + data.views_count);
        }
    })

}