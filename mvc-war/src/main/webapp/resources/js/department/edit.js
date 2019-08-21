function validateString(username) {
    return /^[0-9a-zA-Z_.-]+$/.test(username);
}
function edit() {
    var isValid = true;
    var edit = {
        id : $('#id').val(),
        title : $('#title').val()
    };
    if (validateString(edit.title) === false) {
        isValid = false;
    }
    if (isValid === true) {
        $.ajax({
            url: "/department/edit",
            type: 'POST',
            processData: false,
            data: JSON.stringify(edit),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                if (data.stat === 1) {
                    window.location.assign("/department");
                }
            }
        })
    } else {
        window.alert('Your data is invalid. Please enter correct data');
    }
}
