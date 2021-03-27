function myFunction() {
    var txt;
    var r = confirm("Press the button!");
    if (r == true) {
        txt = "You chose OK!";
    } else {
        txt = "You Cancelled!";
    }
    document.getElementById("conf").innerHTML = txt;
}

//href="/librarian/deleteBook/${book.id}"