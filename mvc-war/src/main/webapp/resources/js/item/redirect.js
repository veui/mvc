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
