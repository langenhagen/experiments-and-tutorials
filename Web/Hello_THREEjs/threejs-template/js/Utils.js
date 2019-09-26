fromFile = function(path) {
    return $.ajax({
        url: path,
        async: false
    }).responseText;
}

cl = function( string) {
    console.log(string);
}