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