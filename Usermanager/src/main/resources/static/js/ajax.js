$(document).ready(function () {
    //Create account
    $('#register-btn').on('click', function () {
        let firstname = $('#register #firstName').val();
        let lastname = $('#register #lastName').val();
        let username = $('#register #username').val();
        let password = $('#register #password').val();

        console.log(firstname + lastname + username + password);
        if (!firstname || !lastname || !username || !password) {
            return;
        }

        let settings = {
            "url": "http://localhost:8080/api/v1/user/register/",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "firstname": firstname,
                "surname": lastname,
                "username": username,
                "password": password
            }),
        };

        $.ajax(settings)
            .done(function (response) {
                location = "http://localhost:8080/register?success";
            })
            .fail(function (jqXHR, textStatus) {
                location = "http://localhost:8080/register?error";
            });
    });

    //Change password
    $('#change-password-btn').on('click', function () {
        let id = $('#current-password').attr('data-userID');
        let currentPassword = $('#current-password').val();
        let newPassword = $('#new-password').val();
        let secondNewPassword = $('#2nd-new-password').val()

        if (!id || !currentPassword || !newPassword) {
            return;
        }

        if (newPassword !== secondNewPassword) {
            $('#pwd-msg').html('Passwörter stimmen nicht überein.')
            return;
        }

        let settings = {
            "url": "http://localhost:8080/api/v1/user/edit/",
            "method": "PUT",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "id": id,
                "currentPassword": currentPassword,
                "newPassword": newPassword
            }),
        };

        $.ajax(settings)
            .done(function (response) {
                $('#pwd-msg').html('Passwort geändert.')
            })
            .fail(function () {
                $('#pwd-msg').html('Passwort ist nicht korrekt.')
            });
    });
    //Delete account
    $('#delete-account-btn').on('click', function () {
        let id = $('#password').attr('data-userID');
        let currentPassword = $('#password').val();

        console.log(id + currentPassword);
        if (!id || !currentPassword) {
            return;
        }
        let settings = {
            "url": "http://localhost:8080/api/v1/user/delete/",
            "method": "DELETE",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "id": id,
                "currentPassword": currentPassword
            }),
        };

        $.ajax(settings)
            .done(function (response) {
                window.location = "http://localhost:8080/logout"
            })
            .fail(function () {
                $('#delete-msg').html('Passwort ist nicht korrekt.')
            });
    });
});

