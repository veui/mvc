function validateString(username) {
    return /^[0-9a-zA-Z_ .-]+$/.test(username);
}
function edit() {
    var isValid = true;
    var edit = {
        id : $('#id').val(),
        title : $('#title').val()
    };
    if (validateString(edit.title) === false) {
        isValid = false;
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
        window.alert('Your data is invalid. Please enter correct data');
    }
}
