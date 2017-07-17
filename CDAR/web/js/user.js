/**
 * Created by island on 2017/7/17.
 */
function logIn() {

}

function signIn() {

}

function quitLog() {
    
}

function showLoginView(){
    $('#searchView').css("display", "none");
    $('#signinView').css("display", "none");
    $('#loginView').fadeIn();
}

function closeLoginView(){
    $('#loginView').css("display", "none");
    $('#searchView').fadeIn();
}

function showSigninView(){
    $('#searchView').css("display", "none");
    $('#loginView').css("display", "none");
    $('#signinView').fadeIn();
}

function closeSigninView(){
    $('#signinView').css("display", "none");
    $('#searchView').fadeIn();
}

function showPassword() {
    if ($('#signInPassword').css("display") == "inline-block") {
        var password = $('#signInPassword').val();
        $('#eyeButton').css("background-image", "url(../images/openEye.png)");

        $('#signInPassword').css("display", "none");
        $('#signInText').css("display", "inline-block");
        $('#signInText').val(password);
    }
    else if ($('#signInText').css("display") == "inline-block") {
        var password = $('#signInText').val();
        $('#eyeButton').css("background-image", "url(../images/closeEye.png)");
        $('#signInText').css("display", "none");
        $('#signInPassword').css("display", "inline-block");
        $('#signInPassword').val(password);
    }
}