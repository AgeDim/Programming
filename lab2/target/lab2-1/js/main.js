function draw_shot(data) {
    const circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
    circle.setAttribute('r', '3');
    let rvalue = Number(data.rval);
    let xvalue = Number(data.xval);
    let yvalue = Number(data.yval);
    circle.setAttribute('cx', 200+150 / rvalue * xvalue);
    circle.setAttribute('cy', 200-150 / rvalue * yvalue);
    if (data.result === "true") {
        circle.setAttribute('fill', 'LIME');
    } else {
        circle.setAttribute('fill', 'RED');
    }
    document.getElementById('svg').appendChild(circle);
}

let r = null;
let x = -10;

function mouse_shot(event) {
    let r_val = null;
    let cordX;
    let cordY;
    let x_val;
    let y_val;
    const radios = document.getElementsByName('rval');
    for (let i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            r_val = radios[i].value;
        }
    }
    if (r_val !== null) {
        cordX = event.pageX - $('#svg').offset().left;
        cordY = event.pageY - $('#svg').offset().top;
        x_val = (cordX - 200) / 150 * r_val;
        y_val = (200 - cordY) / 150 * r_val;

        request(x_val, y_val, r_val);
    } else $('#error_r').html("Не введен R.\n")
}

function add_row(data) {
    let xv = data.xval;
    let yv = data.yval;
    let exec = data.executeTime;
    let row = $('<tr>');
    $('<td>').html(parseFloat(xv).toFixed(3)).appendTo(row);
    $('<td>').html(parseFloat(yv).toFixed(3)).appendTo(row);
    $('<td>').html(data.rval).appendTo(row);
    $('<td>').html(data.currentTime).appendTo(row);
    $('<td>').html(parseFloat(exec).toFixed(7)).appendTo(row);
    $('<td>').html(data.result).appendTo(row);
    $(row).insertAfter($('#result-table tr:last').eq(0));
}

$('#RESET').on('click', reset);

function chooseX(element) {
    x = element.value;
    [...document.getElementsByClassName("x-radio")].forEach(function (btn) {
        btn.style.transform = "";
        btn.style.boxShadow = "";
    });
    element.style.transform = "scale(1.3)";
    element.style.boxShadow = "0 0 50px lightgreen";
}


function reset() {
    [...document.getElementsByClassName("x-radio")].forEach(function (btn) {
        btn.style.transform = "";
    });
}

$(function () {
    function isNumeric(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

    function validateX() {
        if (x !== -10) {
            $('.xbox-label').removeClass('box-error');
            return true;
        } else {
            $('.xbox-label').addClass('box-error');
            return false;
        }
    }

    function validateY() {
        const Y_MIN = -3;
        const Y_MAX = 3;

        let yField = $('#y-textinput');
        if (yField.val().indexOf(',') !== -1) {
            yField.addClass('box-error');
            return false;
        }
        let numY = yField.val().replace(',', '.');
        if (isNumeric(numY) && numY >= Y_MIN && numY <= Y_MAX) {
            yField.removeClass('text-error');
            return true;
        } else {
            yField.addClass('text-error');
            return false;
        }
    }


    function validateR() {
        const radios = document.getElementsByName('rval');

        for (let i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
                r = radios[i].value;
                return true;
            }
        }
        return false;
    }

    function validateForm() {
        return validateX() & validateY() & validateR();
    }

    $('#input-form').on('submit', function (event) {
        event.preventDefault();
        if (!validateForm()) return;
        $.ajax({
            url: './controller',
            type: 'POST',
            cache: false,
            dataType: "json",
            data:
                "xval=" + x +
                "&yval=" + document.getElementById('y-textinput').value +
                "&rval=" + r +
                "&timezone=" + new Date().getTimezoneOffset(),
            success: function (data) {
                console.log(data);
                add_row(data);
                draw_shot(data);
            }
        });
    });
});

function request(x, y, r) {
    $.ajax({
        url: './controller',
        type: 'POST',
        cache: false,
        dataType: "json",
        data:
            "xval=" + x +
            "&yval=" + y +
            "&rval=" + r +
            "&timezone=" + new Date().getTimezoneOffset(),
        success: function (data) {
            console.log(data);
            add_row(data);
            draw_shot(data);
        }
    })
}
