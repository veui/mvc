function redirect_add() {
    fetch("/item/add", {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) window.location.href = '/item/add'
    }).catch(error => console.log(error))
}
function redirect_edit(id) {
    console.log(id);
    fetch("/item/edit/" + id, {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) window.location.href = '/item/edit/' + id;
    }).catch(error => console.log(error))
}
function redirect_delete(id) {
    fetch("/item/delete/" + id, {
        method: 'DELETE'
    }).then(response => response.json())
        .then(response => {
            if (response.message === 'OK') window.location.href = '/item';
            if (response.message === 'Item not found') {
                window.alert('Item not found');
                window.location.href = '/item'
            }
        }).catch(error => console.log(error))
}
function redirect_find_spec(id) {
    fetch('/specialty/find/' + id, {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) {
            window.location.href = '/specialty/find/' + id;
        }
    }).catch(error => console.log(error))
}

function edit() {
    var base = formIdForItemEdit($("#selectId").val());
    clearData();
    let isValid = true;
    let error = [];
    const edit = {
        id: $(id).val(),
        item: $('#item').val(),
        price: $('#price').val(),
        specialtyId: base
    };
    if (validateString(edit.item) === false) {
        isValid = false;
        error.push('item');
    }
    if (validateFloat(edit.price) === false) {
        isValid = false;
        error.push('price');
    }
    if (isValid === true) {
        fetch("/item/edit", {
            method: 'PUT',
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
                window.location.replace("/item");
            }
            if (response.message === 'Item is not unique') {
                $("#item-non-unique-message").html("This item is unavailable.");
            }
        }).catch (error => {
            console.log(error)
        })
    } else {
        error.forEach(element => {
            if (element === 'item') {
                $("#item-not-valid").html(`${edit.item} is not valid. \n Please use 0-9a-zA-Z_ .-`);
            }
            if (element === 'price') {
                $("#price-not-valid").html(`${edit.price} is not valid.\n Please use digits`);
            }
        })
    }
}

function add() {
    var base = formIdForItemAdd($("#selectId").val());

    clearData();
    let isValid = true;
    let error = [];
    const add = {
        item : $('#item').val(),
        price : $('#price').val(),
        specialtyId : base
    };
    if (validateString(add.item) === false) {
        isValid = false;
        error.push('item');
    }
    if (validateFloat(add.price) === false) {
        isValid = false;
        error.push('price');
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
        error.forEach(element => {
            if (element === 'item') {
                $("#item-not-valid").html(`${add.item} is not valid. \n Please use 0-9a-zA-Z_ .-`);
            }
            if (element === 'price') {
                $("#price-not-valid").html(`${add.price} is not valid.\n 
                You can write 2 digits after dot(.)`);
            }
        })
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
function validateString(username) {
    return /^[0-9a-zA-Z_ .-]+$/.test(username);
}

function clearData() {
    $("#item-not-valid").html('');
    $("#price-not-valid").html('');
}
