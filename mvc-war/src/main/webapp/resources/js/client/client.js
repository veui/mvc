function redirect_edit(id) {
    fetch("/client/edit/" + id, {
        method: "GET"
    }).then(response => {
        if (response.status === 200) {
            window.location.href = "/client/edit/" + id;
        }
    }).catch(error => {
        console.log(error)
    })
}

function redirect_add() {
    fetch("/client/add", {
        method: "GET"
    }).then(response => {
        if (response.status === 200) {
            window.location.href = "/client/add"
        }
    }).catch(error => {
        console.log(error)
    })
}

function add() {
    clearData();

    let errorMsg = [];
    let isValid = true;
    const add = {
        username : $('#username').val(),
        password : $('#password').val(),
        lastName : $('#lastName').val(),
        firstName : $('#firstName').val(),
        email : $('#email').val(),
        phone : $('#phone').val()
    };
    isValid = validate(add, isValid, errorMsg);
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
        error(errorMsg);
    }
}

function edit() {
    clearData();

    let isValid = true;
    let errorMsg = [];
    const edit = {
        id : $('#id').val(),
        username : $('#username').val(),
        password : $('#password').val(),
        lastName : $('#lastName').val(),
        firstName : $('#firstName').val(),
        email : $('#email').val(),
        phone : $('#phone').val()
    };
    isValid = validate(edit, isValid, errorMsg);
    if (isValid === true) {
        fetch("/client/edit", {
            method: 'put',
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
    } else error(errorMsg);
}

function remove(id) {
    fetch("/client/delete/" + id, {
        method: "DELETE",
    }).then(response => response.json())
        .then(response => {
            if (response.message === 'OK') {
                window.location.href = "/client";
            }
            if (response.message === 'Client not found') {
                window.alert("Client not found");
                window.location.href = "/client";
            }
        }).catch(error => {
        console.log(error)
    })
}

function validateEmail(email) {
    return /^(([a-z]+)?([A-Z]+)?([0-9]+)?([A-Z]+)?([a-z]+)?)(@)([a-z]+)(\.)(com)$/.test(email);
}

function validatePhone(phone) {
    return /^(\+38)?([ -_])?([0-9]{3})([ -_])?([0-9]{3})([ -_]?)([0-9]{4})$/.test(phone);
}

function validateString(username) {
    return /^[0-9a-zA-Z_ .-]+$/.test(username);
}

function error(errorMsg) {
    errorMsg.forEach((element) => {
        if (element === 'username') {
            $("#username-not-valid").html('Username is not valid');
        }
        if (element === 'password') {
            $("#password-not-valid").html('Password is not valid');
        }
        if (element === 'lastname') {
            $("#lastname-not-valid").html('Lastname is not valid');
        }
        if (element === 'firstname') {
            $("#firstname-not-valid").html('Firstname is not valid');
        }
        if (element === 'email') {
            $("#email-not-valid").html('Email is not valid');
        }
        if (element === 'phone') {
            $("#phone-not-valid").html('Phone is not valid');
        }
    });
}

function clearData() {
    $("#username-non-unique-message").html("");
    $("#email-non-unique-message").html("");
    $("#username-not-valid").html("");
    $("#password-not-valid").html("");
    $("#lastname-not-valid").html("");
    $("#firstname-not-valid").html("");
    $("#email-not-valid").html("");
    $("#phone-not-valid").html("");
}

function validate(add, isValid, errorMsg) {
    if (validateString(add.username) === false) {
        isValid = false;
        errorMsg.push('username');
    }
    if (validateString(add.password) === false) {
        isValid = false;
        errorMsg.push('password');
    }
    if (validateString(add.lastName) === false) {
        isValid = false;
        errorMsg.push('lastname');
    }
    if (validateString(add.firstName) === false) {
        isValid = false;
        errorMsg.push('firstname');
    }
    if (validateEmail(add.email) === false) {
        isValid = false;
        errorMsg.push('email');
    }
    if (validatePhone(add.phone) === false) {
        isValid = false;
        errorMsg.push('phone');
    }
    return isValid;
}
