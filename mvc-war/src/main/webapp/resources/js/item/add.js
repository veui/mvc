function validateFloat(float) {
        var valid = false;
        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/;
        if (regex.test(float)) {
            valid = true;
        }
        return valid;
}
function validateString(username) {
    return /^[0-9a-zA-Z_ .-]+$/.test(username);
}
function add() {
    var isValid = true;
    var add = {
        item : $('#item').val(),
        price : $('#price').val(),
        specialtyId : $("#selectId :selected").val()
    };
    if (validateString(add.item) === false) {
        isValid = false;
    }
    if (validateFloat(add.price) === false) {
        isValid = false;
    }
    if (isValid === true) {
        fetch("/item/add", {
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
                window.location.replace("/item");
            }
            if (response.message === 'Item is not unique') {
                $("#item-non-unique-message").html("This item is unavailable.");
            }
        }).catch (error => {
            console.log(error)
        })
    } else {
        window.alert('Your data is invalid. Please enter correct data');
    }
}
