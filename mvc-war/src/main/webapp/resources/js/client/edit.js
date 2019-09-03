function validateInt(int) {
    return /^([0-9]{3})([0-9]{3})([0-9]{2})([0-9]{2})$/.test(int);
}

function validateString(username) {
    return /^[0-9a-zA-Z_ .-]+$/.test(username);
}
function edit() {
    $("#username-non-unique-message").html("");
    $("#email-non-unique-message").html("");

    var isValid = true;
    var edit = {
        id : $('#id').val(),
        username : $('#username').val(),
        password : $('#password').val(),
        lastName : $('#lastName').val(),
        firstName : $('#firstName').val(),
        email : $('#email').val(),
        phone : $('#phone').val()
    };
    if (validateString(edit.username) === false) {
        isValid = false;
    }
    if (validateString(edit.password) === false) {
        isValid = false;
    }
    if (validateString(edit.lastName) === false) {
        isValid = false;
    }
    if (validateString(edit.firstName) === false) {
        isValid = false;
    }
    if (validateInt(edit.phone) === false) {
        isValid = false;
    }
    if (isValid === true) {
        fetch("/client/edit", {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(edit)
        }).then(response => {
            return response.json();
        }).then(response => {
            console.log(response);
            if (response.message === 'OK') {
                window.location.replace("/client");
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
