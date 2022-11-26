function afterInactivityRedirect(){
    alert('Redirect');
    location.reload();
}
setTimeout(afterInactivityRedirect, 60000);
