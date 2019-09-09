function edit() {
    function validateString(username) {
        return /^[0-9a-zA-Z_ .-]+$/.test(username);
    }
    var isValid = true;
    var edit = {
        id : $('#id').val(),
        title : $('#title').val(),
        departmentId : $('#optionDp').val()
    };
    if (validateString(edit.title) === false) {
        isValid = false;
    }
    if (isValid === true) {
        fetch("/specialty/edit",{
            method: 'post',
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
