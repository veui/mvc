function edit() {
    function validateString(username) {
        return /^[0-9a-zA-Z_.-]+$/.test(username);
    }
    var isValid = true;
    var edit = {
        id : $('#id').val(),
        title : $('#title').val(),
        departmentId : $('#optionDp').val()
    };
    if (validateString(edit.title) === false) {
        isValid = false;
    }
    if (isValid === true) {
        $.ajax({
            url: "/specialty/edit",
            type: 'POST',
            processData: false,
            data: JSON.stringify(edit),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                if (data.stat === 1) {
                    window.location.assign("/specialty");
                }
            }
        })
    } else {
        window.alert('Your data is invalid. Please enter correct data');
    }
}
