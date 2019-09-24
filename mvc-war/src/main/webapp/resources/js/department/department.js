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

function add() {
    let isValid = true;
    let errorMsg = {
        message: ''
    };
    const add = {
        title : $('#title').val()
    };
    if (validateString(add.title) === false) {
        isValid = false;
        errorMsg.message = `${add.title} is not valid title.\n Please use a-z, A-Z or digits.`;
    }
    if (isValid === true) {
        fetch("/department/add", {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(add)
        }).then(response => {
            return response.json();
        }).then(response => {
            console.log(response);
            if (response.message === 'OK') {
                window.location.replace("/department");
            }
            if (response.message === 'Department title is not unique') {
                $("#department-non-unique-message").html("Department title must be unique");
            }
        })
    } else {
        $("#department-not-valid").html(errorMsg.message);
    }
}

function edit() {
    let isValid = true;
    let errorMsg = {
        message: ''
    };
    const edit = {
        id : $('#id').val(),
        title : $('#title').val()
    };
    if (validateString(edit.title) === false) {
        isValid = false;
        errorMsg.message = `${edit.title} is not valid title.\n Please use a-z, A-Z or digits.`;
    }
    if (isValid === true) {
        fetch("/department/edit", {
            method: 'put',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(edit)
        }).then(response => {
            return response.json();
        }).then(response => {
            console.log(response);
            if (response.message === 'OK') {
                window.location.replace("/department");
            }
            if (response.message === 'Department title is not unique') {
                $("#department-non-unique-message").html("Department title must be unique");
            }
        })
    } else {
        $("#department-not-valid").html(errorMsg.message);
    }
}

function validateString(username) {
    return /^[0-9a-zA-Z_ .-]+$/.test(username);
}
