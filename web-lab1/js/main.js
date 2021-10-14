$('#RESET').on('click', reset);

function chooseX(element) {
    x = element.value;
    [...document.getElementsByClassName("x-radio")].forEach(function (btn) {
        btn.style.transform = "";
    });
    element.style.transform = "scale(1.3)";
}

let x;

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
        if (x) {
            $('.xbox-label').removeClass('box-error');
            return true;
        } else {
            $('.xbox-label').addClass('box-error');
            return false;
        }
    }

    function validateY() {
        const Y_MIN = -5;
        const Y_MAX = 5;

        let yField = $('#y-textinput');
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
        if ($('.r-checkbox') === "Select R coordinate") {
            $('.rbox-label').addClass('box-error');
            return false;
        } else {
            $('.rbox-label').removeClass('box-error');
            return true;
        }
    }

    function switchRAction(eventObj) {
        for (let s of $('.r-checkbox')) {
            if (s !== eventObj.target) {
                s.checked = false;
            }
        }
    }

    for (let rBox of $(`.r-checkbox`)) {
        rBox.onclick = switchRAction;
    }


    function validateForm() {
        return validateX() & validateY() & validateR();
    }

    $('#input-form').on('submit', function (event) {
        event.preventDefault();
        if (!validateForm()) return;
        $.ajax({
            url: 'php/main.php',
            method: `get`,
            dataType: "json",
            data: 'xval=' + x + '&' + $(this).serialize() + '&timezone=' + new Date().getTimezoneOffset(),
            beforeSend: function () {
                $('.button').attr('disabled', 'disabled');
            },
            success: function (data) {
                $('.button').attr('disabled', false);
                if (data.validate) {
                    newRow = '<tr>';
                    newRow += '<td>' + x + '</td>';
                    newRow += '<td>' + data.yval + '</td>';
                    newRow += '<td>' + data.rval + '</td>';
                    newRow += '<td>' + data.curtime + '</td>';
                    newRow += '<td>' + data.exectime + '</td>';
                    newRow += '<td>' + data.hitres + '</td>';
                    $('#result-table').append(newRow);
                }
            }
        });
    });
});