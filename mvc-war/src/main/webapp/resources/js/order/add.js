function add() {
    var isValid = true;
    var add = {
        date : $('#date').val(),
        amount : $('#amount').val(),
        cost : $('#cost').val(),
        clientId : $("#selectClient").val(),
        itemId : $("#selectItem :selected").val()
    };
    if (validateDatalist(add.clientId) === false) {
        isValid = false;
    }
    if (validateInt(add.amount) === false) {
        console.log("amount");
        isValid = false;
    }
    if (validateFloat(add.cost) === false) {
        console.log("cost");
        isValid = false;
    }
    if (isValid === true) {
        fetch("/order/add",{
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
                window.location.replace("/order");
            }
            if (response.message === 'Order is not unique') {
                window.alert("Order is not unique");
            }
        }).catch (error => {
            console.log(error)
        });
    } else {
        window.alert('Your data is invalid. Please enter correct data');
    }

    function validateDatalist(int) {
        var obj = $("#datalist").find("option[value='" + int + "']");

        if(obj != null && obj.length > 0) {
            return true;
        }
        else {
            alert("Client data is invalid. Please try to input correct data from list");
            return false;
        }
    }

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
}
