function redirect(id) {
    fetch("/" + id, {
        method: 'get',
    }).then(response => {
        if (response.status === 200) window.location.href = '/' + id;
    }).catch(error => console.log(error))
}

function redirect_item(id) {
    fetch('/item/find/' + id, {
        method: 'get'
    }).then(response => {
        if (response.status === 200) window.location.href = '/item/find/' + id
    }).catch(error => console.log(error))
}

function redirect_specialty(id) {
    fetch('/specialty/find/' + id, {
        method: 'get'
    }).then(response => {
        if (response.status === 200) window.location.href = '/specialty/find/' + id
    }).catch(error => console.log(error))
}

function redirect_order(id) {
    fetch('order/add/' + id, {
        method: 'get'
    }).then(response => {
        if (response.status === 200) window.location.href = 'order/add/' + id
    }).catch(error => console.log(error))
}