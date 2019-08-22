function validateFloat(float) {
        var valid = false;
        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/;
        if (regex.test(float)) {
            valid = true;
        }
        return valid;
    }
function validateInt(int) {
    var valid = false;
    if (int > 0) {
        valid = true;
    }
    return valid;
}
function edit() {
    var isValid = true;
    var edit = {
        id : $('#id').val(),
        date : $('#date').val(),
        amount : $('#amount').val(),
        cost : $('#cost').val(),
        clientId : $("#selectClient :selected").val(),
        itemId : $("#selectItem :selected").val()
    };
    if (validateInt(edit.amount) === false) {
        isValid = false;
    }
    if (validateFloat(edit.cost) === false) {
        isValid = false;
    }
    if (isValid === true) {
        $.ajax({
            url: "/order/edit",
            type: 'POST',
            processData: false,
            data: JSON.stringify(edit),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                if (data.stat === 1) {
                    window.location.replace("/order");
                }
            }
        })
    } else {
        window.alert('Your data is invalid. Please enter correct data');
    }
}
