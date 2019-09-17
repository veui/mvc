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
        fetch("/order/edit",{
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
                window.location.replace("/order");
            }
            if (response.message === 'Order is not unique') {
                window.alert("Order is not unique");
            }
        }).catch (error => {
            console.log(error)
        })
    } else {
        window.alert('Your data is invalid. Please enter correct data');
    }
}
