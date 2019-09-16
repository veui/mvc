function redirect_add() {
    fetch("/department/add", {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) window.location.href = "/department/add"
    }).catch(error => console.log(error))
}
function redirect_edit(id) {
    fetch("/department/edit/" + id, {
        method: "GET"
    }).then(response => {
        if (response.status === 200) window.location.href = "/department/edit/" + id
    }).catch(error => console.log(error))
}
function redirect_delete(id) {
    fetch("/department/delete/" + id, {
        method: 'DELETE'
    }).then(response => response.json())
        .then(response => {
            if (response.message === 'OK') window.location.href = "/department";
            if (response.message === 'Department not found') {
                window.alert("Department not found");
                window.location.href = '/department';
            }
        }).catch(error => console.log(error))
}
function redirect_find(id) {
    fetch('/department/find/' + id, {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) {
            window.location.href = '/department/find/' + id;
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