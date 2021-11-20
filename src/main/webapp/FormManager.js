var valueR = document.getElementById("valueR");

var errorX = document.getElementById("errorX");
var errorY = document.getElementById("errorY");

var radio_buttons = document.getElementsByClassName("radio_button");

var text_field = document.getElementById("paramX");

var r_but_1_0 = document.getElementById("rBut1_0");
var r_but_1_5 = document.getElementById("rBut1_5");
var r_but_2_0 = document.getElementById("rBut2_0");
var r_but_2_5 = document.getElementById("rBut2_5");
var r_but_3_0 = document.getElementById("rBut3_0");

var r_buttons = document.getElementsByClassName("r_button");

function clickOnCanvas(canvas, event) {
    let rect = canvas.getBoundingClientRect()
    let width = canvas.width;
    let height = canvas.height;
    let x = (event.clientX - rect.left - width / 2) / step - 0.45;
    let y = (height / 2 - event.clientY + rect.top) / step + 0.48;
    let r = valueR.value;
    x = x.toFixed(2).replace(".00", "");
    y = y.toFixed(2).replace(".00", "");

    console.log(x, y, r);
    if (isLegal(x, y, r)) {
        drawPoint(x, y);
        sendRequest(x, y, r);
    }

}

document.querySelector("#butSend").onclick = function (event) {
    event.preventDefault();
    if (!isNumber(text_field.value) || Math.abs(text_field.value) > 5 ||
        text_field.value === "" || !isChecked(radio_buttons)) {
        errorY.hidden = true;
        errorX.hidden = !(!isNumber(text_field.value) || Math.abs(text_field.value) > 5 ||
            text_field.value === "");
        if (!isChecked(radio_buttons)) {
            errorY.hidden = false;
        }
    } else {
        let x = text_field.value;
        let y = $('input[name="y"]:checked').val();
        let r = valueR.value;
        sendRequest(x, y, r);
    }
}
document.querySelector("#clearButton").onclick = function (event) {
    event.preventDefault();
    return new Promise(function (resolve) {
            $.post('servlet', {
                'clear': "true"
            }).done(function (data) {
                    var table = document.getElementById("pointsTable");
                    while (table.rows.length > 1) {
                        table.deleteRow(1);
                    }
                    clearCanvas();
                    drawCanvas();
                }
            ).fail(function (err) {
                alert(err);
            });
        }
    );
}

function sendRequest(xValue, yValue, rValue) {
    return new Promise(function (resolve) {
            $.post('servlet', {
                'x': xValue,
                'y': yValue,
                'r': rValue,
            }).done(function (data) {
                    let table = document.getElementById("pointsTable");
                    while (table.rows.length > 1) {
                        table.deleteRow(1);
                    }
                    console.log(data);
                    let par = data;
                    if (par === "Incorrect coordinates type" || par == null || par === "") {
                        return;
                    }
                    if (par !== "remove") {
                        let result = JSON.parse(par);
                        for (let i in result.response) {
                            let newRow = '<tr>';
                            newRow += '<td>' + result.response[i].xValue + '</td>';
                            newRow += '<td>' + result.response[i].yValue + '</td>';
                            newRow += '<td>' + result.response[i].rValue + '</td>';
                            newRow += '<td>' + result.response[i].result + '</td>';
                            newRow += '<td>' + result.response[i].totalProcessingTime + '</td>';
                            newRow += '</tr>';
                            $('#pointsTable').append(newRow);
                            drawPoint(result.response[i].xValue, result.response[i].yValue);
                        }
                    }
                }
            ).fail(function (err) {
                alert(err);
            });
        }
    );
}

function isChecked(container) {
    for (let i = 0; i < container.length; i++) {
        if (container.item(i).checked) {
            return true;
        }
    }
    return false;
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function isLegal(x, y, r) {
    return (isNumber(x) && isNumber(y) && isNumber(r) &&
        x > -5 && x < 5 && y >= -3 && y <= 5 && r >= 1 && r <= 3);

}

function refresh_button_selection() {
    let index, len;
    for (index = 0, len = r_buttons.length; index < len; ++index) {
        let element = r_buttons[index];
        if (element.classList.contains('selected')) {
            element.classList.remove('selected');
        }
    }
}

function r1_0() {
    valueR.value = "1.0";
    refresh_button_selection();
    r_but_1_0.classList.add('selected');
    clearCanvas();
    drawCanvas();
}

function r1_5() {
    valueR.value = "1.5";
    refresh_button_selection();
    r_but_1_5.classList.add('selected');
    clearCanvas();
    drawCanvas();
}

function r2_0() {
    valueR.value = "2.0";
    refresh_button_selection();
    r_but_2_0.classList.add('selected');
    clearCanvas();
    drawCanvas();
}

function r2_5() {
    valueR.value = "2.5";
    refresh_button_selection();
    r_but_2_5.classList.add('selected');
    clearCanvas();
    drawCanvas();
}

function r3_0() {
    valueR.value = "3.0";
    refresh_button_selection();
    r_but_3_0.classList.add('selected');
    clearCanvas();
    drawCanvas();
}