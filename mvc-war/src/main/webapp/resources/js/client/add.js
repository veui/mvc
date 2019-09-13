function add() {
    $("#username-non-unique-message").html("");
    $("#email-non-unique-message").html("");

    var isValid = true;
    var add = {
        username : $('#username').val(),
        password : $('#password').val(),
        lastName : $('#lastName').val(),
        firstName : $('#firstName').val(),
        email : $('#email').val(),
        phone : $('#phone').val()
    };
    if (validateString(add.username) === false) {
        console.log('username');
        isValid = false;
    }
    if (validateString(add.password) === false) {
        console.log('password');
        isValid = false;
    }
    if (validateString(add.lastName) === false) {
        console.log('lastname');
        isValid = false;
    }
    if (validateString(add.firstName) === false) {
        console.log('firstname');
        isValid = false;
    }
    if (validateInt(add.phone) === false) {
        console.log('phone');
        isValid = false;
    }
    if (isValid === true) {
        fetch("/client/add", {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(add)
        }).then(response => {
            return response.json();
        }).then(response => {
            console.log(response);
            if (response.message === 'OK') {
                window.location.href = "/client";
            }
            if (response.message === 'Client is not unique') {
                $("#username-non-unique-message").html("This username is unavailable.");
            }
            if (response.message === 'Email is not unique') {
                $("#email-non-unique-message").html("Email is unavailable");
            }
        }).catch (error => {
            console.log(error)
        })
    } else {
        window.alert('Your data is invalid. Please enter correct data');
    }
}
function validateInt(int) {
    return /^([0-9]{3})([0-9]{3})([0-9]{2})([0-9]{2})$/.test(int);
}

function validateString(username) {
    return /^[0-9a-zA-Z_ .-]+$/.test(username);
}
