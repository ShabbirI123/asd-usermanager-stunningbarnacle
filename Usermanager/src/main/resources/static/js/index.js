$(document).ready(function () {
    //Simple client based login limitation by using localstorage
    if (localStorage.getItem('limit') >= '2') {
        //set date
        let t = new Date();
        t.setSeconds(t.getSeconds() + 60);
        if (!localStorage.getItem('date')) localStorage.setItem('date', t.toString());

        if (new Date(localStorage.getItem('date')) > new Date()) {
            //disable submit btn
            $('#login-btn').css('pointer-events', 'none');
            //Message
            $('#blocked-msg').html("Benutzer bis zum " + localStorage.getItem('date') + " Uhr gesperrt");
        } else {
            localStorage.setItem('limit', '0');
            localStorage.removeItem('date');
        }
    }

    if (location.href === "http://localhost:8080/login?error") {
        if (!localStorage.getItem('limit')) {
            localStorage.setItem('limit', "0");
        } else {
            let limit = parseInt(localStorage.getItem('limit'));
            if (limit < 2) limit++;
            localStorage.setItem('limit', limit.toString());
        }
    }


    function afterInactivityRedirect() {
        alert('Redirect');
        location.reload();
    }

    //setTimeout(afterInactivityRedirect, 60000);

    //Restriction of login attempts
    $('#login-btn').on('click', function () {
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


        if (localStorage.getItem('limit') === '3') {
            $(this).css('pointer-events', 'none');
        }
    });

    $('#logout-btn').on('click', () => {
        localStorage.setItem('limit', "0");
    })
});

