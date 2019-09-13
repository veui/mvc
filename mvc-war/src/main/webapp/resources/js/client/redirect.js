function redirect_edit(id) {
    fetch("/client/edit/" + id, {
        method: "GET"
    }).then(response => {
        if (response.status === 200) {
            window.location.href = "/client/edit/" + id;
        }
    }).catch(error => {
        console.log(error)
    })
}

function redirect_add() {
    fetch("/client/add", {
        method: "GET"
    }).then(response => {
        if (response.status === 200) {
            window.location.href = "/client/add"
        }
    }).catch(error => {
        console.log(error)
    })
}