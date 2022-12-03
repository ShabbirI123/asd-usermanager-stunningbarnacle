$(document).ready(function () {
    //Simple client based login limitation by using localstorage
   if (location.href === "http://localhost:8080/login?error"){
       if (!localStorage.getItem('limit')){
           localStorage.setItem('limit', "0");
       } else {
           let limit = parseInt(localStorage.getItem('limit'));
           console.log(limit);
           console.log(limit++)
           localStorage.setItem('limit', limit.toString());
       }

       if(localStorage.getItem('limit') === '3'){
           $('#login-btn').css('pointer-events', 'none');
       }
   }


    function afterInactivityRedirect() {
        alert('Redirect');
        location.reload();
    }

    //setTimeout(afterInactivityRedirect, 60000);

    //Restriction of login attempts
    $('#login-btn').on('click',function (){
        let username = $('#username').val();
        let password = $('#password').val();

        console.log('click');

        var settings = {
            "url": "http://localhost:8080/login",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "username": username,
                "password": password
            }),
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
        });


       if(localStorage.getItem('limit') === '3'){
           $(this).css('pointer-events', 'none');
       }
    });

    $('#logout-btn').on('click', () => {
        localStorage.setItem('limit', "0");
    })
});

