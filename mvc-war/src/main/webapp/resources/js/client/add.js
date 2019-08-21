 function validateInt(int) {
        var valid = false;
        if (typeof int === 'number' && int > 0) {
            valid = true;
        }
        return valid;
    }

function validateString(username) {
    return /^[0-9a-zA-Z_.-]+$/.test(username);
}
function add() {
    var add = {
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
