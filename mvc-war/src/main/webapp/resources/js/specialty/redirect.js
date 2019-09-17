function redirect_add() {
    fetch("/specialty/add", {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) window.location.href = "/specialty/add"
    }).catch(error => console.log(error))
}
function redirect_edit(id) {
    fetch("/specialty/edit/" + id, {
        method: "GET"
    }).then(response => {
        if (response.status === 200) window.location.href = "/specialty/edit/" + id
    }).catch(error => console.log(error))
}
function redirect_delete(id) {
    fetch("/specialty/delete/" + id, {
        method: 'DELETE'
    }).then(response => response.json())
        .then(response => {
            if (response.message === 'OK') window.location.href = "/specialty";
            if (response.message === 'Specialty not found') {
                window.alert("Specialty not found");
                window.location.href = '/specialty';
            }
        }).catch(error => console.log(error))
}
function redirect_find(id) {
    fetch('/specialty/find/' + id, {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) {
            window.location.href = '/specialty/find/' + id;
        }
    }).catch(error => console.log(error))
}