function redirect_add() {
    fetch("/order/add", {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) window.location.href = "/order/add"
    }).catch(error => console.log(error))
}
function redirect_edit(id) {
    fetch("/order/edit/" + id, {
        method: "GET"
    }).then(response => {
        if (response.status === 200) window.location.href = "/order/edit/" + id
    }).catch(error => console.log(error))
}
function redirect_delete(id) {
    fetch("/order/delete/" + id, {
        method: 'DELETE'
    }).then(response => response.json())
        .then(response => {
            if (response.message === 'OK') window.location.href = "/order";
            if (response.message === 'Order not found') {
                window.alert("Order not found");
                window.location.href = '/order';
            }
        }).catch(error => console.log(error))
}
function redirect_find_item(id) {
    fetch('/item/find/' + id, {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) {
            window.location.href = '/item/find/' + id;
        }
    }).catch(error => console.log(error))
}
function redirect_find_client(id) {
    fetch('/client/find/' + id, {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) {
            window.location.href = '/client/find/' + id;
        }
    }).catch(error => console.log(error))
}

function add() {
    var base = formIdAllForOrderItemAdd($("#selectItem").val());
    var base1 = formIdAllForOrderClientAdd($("#selectClient").val());
    clearData();
    let isValid = true;
    let error = [];
    const add = {
        date : $('#date').val(),
        amount : $('#amount').val(),
        cost : $('#cost').val(),
        clientId : base1,
        itemId : base
    };
    if (validateDate(add.date) === false) {
        isValid = false;
        error.push('date');
    }
    if (validateDatalist(add.clientId) === false) {
        isValid = false;
        error.push('clientId');
    }
    if (validateInt(add.amount) === false) {
        isValid = false;
        error.push('amount');
    }
    if (validateFloat(add.cost) === false) {
        isValid = false;
        error.push('cost');
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
        errorMsg(error, add);
    }
}

function edit() {
    var base = formIdAllForOrderItemEdit($("#selectItem").val());
    var base1 = formIdAllForOrderClientEdit($("#selectClient").val());
    clearData();
    let isValid = true;
    let error = [];
    const edit = {
        id : $('#id').val(),
        date : $('#date').val(),
        amount : $('#amount').val(),
        cost : $('#cost').val(),
        clientId : base1,
        itemId : base
    };
    if (validateDate(edit.date) === false) {
        isValid = false;
        error.push('date');
    }
    if (validateInt(edit.amount) === false) {
        isValid = false;
        error.push('amount')
    }
    if (validateFloat(edit.cost) === false) {
        isValid = false;
        error.push('cost');
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
        errorMsg(error, edit);
    }
}

function validateDate(date) {
    if (date === '') {
        return false;
    }
}

function validateFloat(float) {
    let valid = false;
    const regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/;
    if (regex.test(float)) {
        valid = true;
    }
    return valid;
}
function validateInt(int) {
    let valid = false;
    if (int > 0) {
        valid = true;
    }
    return valid;
}

function validateDatalist(int) {
    const obj = $("#datalist").find("option[value='" + int + "']");
    return obj != null && obj.length > 0;
}

function errorMsg(error, input) {
    error.forEach(element => {
        if (element === 'date') {
            $("#date-not-valid").html(`Date is not valid`);
        }
        if (element === 'clientId') {
            $("#client-not-valid").html(`${input.clientId} not valid`);
        }
        if (element === 'amount') {
            $("#amount-not-valid").html(`${input.amount} is not valid`);
        }
        if (element === 'cost') {
            $("#cost-not-valid").html(`${input.cost} is not valid`);
        }
    })
}

function clearData() {
    $('#date-not-valid').html('');
    $('#amount-not-valid').html('');
    $('#cost-not-valid').html('');
    $("#client-not-valid").html('');
}