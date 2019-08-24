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
        $.ajax({
            url: "/order/add",
            type: 'POST',
            processData: false,
            data: JSON.stringify(add),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                if (data.stat === 1) {
                    window.location.replace("/order");
                }
            }
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
