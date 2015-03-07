$(document).ready(readyHandler);

function readyHandler() {
    preventDefault();
    stopPropagation();
    stopImmediatePropagation();
    
    document.loginForm.username.focus();
}   // end function readyHandler() {}


