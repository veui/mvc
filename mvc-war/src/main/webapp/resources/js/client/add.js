function add() {
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
        $.ajax({
            url: "/client/add",
            type: 'POST',
            processData: false,
            data: JSON.stringify(add),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                if (data.stat === 1) {
                    window.location.replace("/client");
                }
            }
        });
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
