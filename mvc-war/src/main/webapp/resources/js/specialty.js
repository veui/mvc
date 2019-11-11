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
function redirect_find_department(id) {
    fetch('/department/find/' + id, {
        method: 'GET'
    }).then(response => {
        if (response.status === 200) {
            window.location.href = '/department/find/' + id;
        }
    }).catch(error => console.log(error))
}

function add() {
    var base = formIdForSpecDepartmentAdd($("#selectId").val());
    let isValid = true;
    let errorMsg = {
        message: ''
    };
    const add = {
        title: $('#title').val(),
        departmentId: base,
        parentId: $('#selectParent :selected').val()
    };
    if (validateString(add.title) === false) {
        isValid = false;
        errorMsg.message = `${add.title} is not valid title.\n Please use a-z, A-Z or digits.`;
    }
    if (isValid === true) {
        fetch("/specialty/add", {
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
                window.location.replace("/specialty");
            }
            if (response.message === 'Specialty is not unique') {
                $("#title-non-unique-message").html("This specialty is unavailable.");
            }
        }).catch (error => {
            console.log(error)
        })
    } else {
        $("#title-not-valid").html(errorMsg.message);
    }
}

function edit() {
    var base = formIdForSpecDepartmentEdit($("#selectId").val());
    let isValid = true;
    let errorMsg = {
        message: ''
    };
    const edit = {
        id : $('#id').val(),
        title : $('#title').val(),
        departmentId : base,
        parentId: $('#selectParent :selected').val()
    };
    if (edit.id === edit.parentId) {
        isValid = false;
        $("#parentIdNotEqualToId").html("Id and parent id should not be equal");
    }
    if (validateString(edit.title) === false) {
        isValid = false;
        errorMsg.message = `${edit.title} is not valid title.\n Please use a-z, A-Z or digits.`
    }
    if (isValid === true) {
        fetch("/specialty/edit",{
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
                window.location.replace("/specialty");
            }
            if (response.message === 'Specialty is not unique') {
                $("#title-non-unique-message").html("This specialty is unavailable.");
            }
        }).catch (error => {
            console.log(error)
        })
    } else {
        $("#title-not-valid").html(errorMsg.message);
    }
}

function validateString(username) {
    return /^[0-9a-zA-Z_ .-]+$/.test(username);
}

