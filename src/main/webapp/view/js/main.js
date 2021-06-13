$(document).ready(function () {
    let showAll = false;
    showLogRegMenu();
    findAll(showAll);

    $('#reg').click(function () {
        validateAndReg();
        setTimeout(function () {
            findAll(showAll);
        }, 1000);
    });

    $('#auth').click(function () {
        validateAndAuth();
        setTimeout(function () {
            findAll(showAll);
        }, 1000);
    });

    $('#add').click(function () {
        validateAndAdd();
        setTimeout(function () {
            findAll(showAll);
        }, 1000);
    });

    $('#switch').click(function () {
        showAll = !showAll;
        findAll(showAll);
    });
});

function validateAndAdd() {
    if ($('#description').val() == '') {
        alert('Введите описание');
        return false;
    }
    addTask();
    return false;
}

function addTask() {
    $.get("http://localhost:8080/todo/add.do", {
        description: $('#description').val()
    }).done(function (response) {
        console.log("Response data: " + response);
    }).fail(function (err) {
        alert('Request failed');
        console.log("Request failed: " + err);
    });
}

function findAll(showAll) {
    $.getJSON("http://localhost:8080/todo/find.do"
    ).done(function (response) {
        let rows = [];
        $.each(response, function (key, val) {
            if (showAll == false) {
                if (val.done == false) {
                    rows.push('<tr>' +
                        '<td>' + val.id + '</td>' +
                        '<td>' + val.description + '</td>' +
                        '<td>' + val.user.name + '</td>' +
                        '<td>' + formatDate(new Date(val.created)) + '</td>' +
                        '<td><div class="form-check">' +
                        '<input class="form-check-input" type="checkbox" value="" id="' + val.id + '">' +
                        '</div></td></tr>');
                }
            } else {
                if (val.done == false) {
                    rows.push('<tr>' +
                        '<td>' + val.id + '</td>' +
                        '<td>' + val.description + '</td>' +
                        '<td>' + val.user.name + '</td>' +
                        '<td>' + formatDate(new Date(val.created)) + '</td>' +
                        '<td><div class="form-check">' +
                        '<input class="form-check-input" type="checkbox" value="" id="' + val.id + '">' +
                        '</div></td></tr>');
                } else {
                    rows.push('<tr>' +
                        '<td>' + val.id + '</td>' +
                        '<td>' + val.description + '</td>' +
                        '<td>' + val.user.name + '</td>' +
                        '<td>' + formatDate(new Date(val.created)) + '</td>' +
                        '<td><div class="form-check">' +
                        '<input class="form-check-input" type="checkbox" value="" id="' + val.id + '" checked disabled>' +
                        '</div></td></tr>');
                }
            }
        });
        $('#table').html(rows);
        $('table').find('input').click(function () {
            update($(this).attr("id"), showAll);
        });
    }).fail(function (err) {
        alert('findAll failed');
        console.log("Request failed: " + err);
    });
}

function formatDate(date) {
    let dd = date.getDate();
    if (dd < 10) dd = '0' + dd;
    let mm = date.getMonth() + 1;
    if (mm < 10) mm = '0' + mm;
    let yy = date.getFullYear();
    return dd + '.' + mm + '.' + yy;
}

function update(id, showAll) {
    $.get("http://localhost:8080/todo/update.do", {
        id: id
    }).done(function (response) {
        findAll(showAll);
        console.log("Response data: " + response);
    }).fail(function (err) {
        alert('Request Failed!');
        console.log("Request failed: " + err);
    });
}

function validateAndReg() {
    let name = $('#regName').val();
    let email = $('#regEmail').val();
    let password = $('#regPassword').val();
    if (name === '' || email === '' || password === '') {
        alert('Заполните все поля');
        return false;
    }
    return true;
}

function validateAndAuth() {
    let email = $('#authEmail').val();
    let password = $('#authPassword').val();
    if (email === '' || password === '') {
        alert('Заполните все поля');
        return false;
    }
    return true;
}

function showLogRegMenu() {
        $.get("http://localhost:8080/todo/auth.do"
        ).done(function (response) {
            if (response === null) {
                let string = '<a href="" class="btn btn-outline-primary btn-rounded my-3" data-toggle="modal" data-target="#modalLRForm">Вход/Регистрация</a>';
                $('#logReg').html(string);
            } else {
                let string = '<div class="alert alert-primary" role="alert">\n' +
                    '  Вы вошли как ' + response.name + '. <a href="index.html" onclick="logout()" class="alert-link"> Выход</a>.\n' +
                    '</div>';
                $('#logReg').html(string);
            }
        }).fail(function (err) {
            alert('Request Failed!');
            console.log("Request Failed: " + err);
        });
}

function logout() {
    $.get("http://localhost:8080/todo/logout.do", {
    }).done(function (response) {
        console.log("Response data: " + response);
    }).fail(function (err) {
        alert('Request Failed!');
        console.log("Request failed: " + err);
    });
}