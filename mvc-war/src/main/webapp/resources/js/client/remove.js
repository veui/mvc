function remove(id) {
    fetch("/client/delete/" + id, {
        method: "DELETE",
    }).then(response => response.json())
        .then(response => {
            if (response.message === 'OK') {
                window.location.href = "/client";
            }
            if (response.message === 'Client not found') {
                window.alert("Client not found");
                window.location.href = "/client";
            }
        }).catch(error => {
            console.log(error)
        })
}