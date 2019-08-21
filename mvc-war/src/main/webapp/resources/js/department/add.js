function validateString(username) {
        return /^[0-9a-zA-Z_.-]+$/.test(username);
    }
function add() {
    var isValid = true;
    var add = {
        title : $('#title').val()
    };
    if (validateString(add.title) === false) {
        isValid = false;
    }
    if (isValid === true) {
        $.ajax({
            url: "/department/add",
            type: 'POST',
            processData: false,
            data: JSON.stringify(add),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                if (data.stat === 1) {
                    window.location.replace("/department")
                }
            }
        });
    } else {
        window.alert('Your data is invalid. Please enter correct data');
    }
}
