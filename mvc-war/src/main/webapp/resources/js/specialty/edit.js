function edit() {
    function validateString(username) {
        return /^[0-9a-zA-Z_ .-]+$/.test(username);
    }
    var isValid = true;
    var edit = {
        id : $('#id').val(),
        title : $('#title').val(),
        departmentId : $('#optionDp').val(),
        parentId: $('#selectParent :selected').val()
    };
    if (edit.id === edit.parentId) {
        isValid = false;
        $("#parentIdNotEqualToId").html("Id and parent id should not be equal");
    }
    if (validateString(edit.title) === false) {
        isValid = false;
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
        window.alert('Your data is invalid. Please enter correct data');
    }
}
