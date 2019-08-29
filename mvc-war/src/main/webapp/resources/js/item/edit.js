 function edit() {
    function validateFloat(float) {
        var valid = false;
        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/;
        if (regex.test(float)) {
            valid = true;
        }
        return valid;
    }

    function validateString(username) {
        return /^[0-9a-zA-Z_.-]+$/.test(username);
    }

    let isValid = true;
    const edit = {
        id: $(id).val(),
        item: $('#item').val(),
        price: $('#price').val(),
        specialtyId: $('#optionDp').val()
    };
    if (validateString(edit.item) === false) {
        isValid = false;
    }
    if (validateFloat(edit.price) === false) {
        isValid = false;
    }
    if (isValid === true) {
        $.ajax({
            url: "/item/edit",
            type: 'POST',
            processData: false,
            data: JSON.stringify(edit),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                if (data.stat === 1) {
                    window.location.replace("/item");
                }
            }
        })
        } else {window.alert('Your data is invalid. Please enter correct data');
    }
}
