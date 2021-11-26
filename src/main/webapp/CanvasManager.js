let axis_separator_offset = 5;
let step = 50;
let canvas = document.getElementById("canvas"),
    context = canvas.getContext("2d");
canvas.width = 510;
canvas.height = 510;
let width = canvas.width;
let height = canvas.height;
canvas.addEventListener('mousedown', event => clickOnCanvas(canvas, event));

function loadCanvas(){
    pointsXArray = Array.from(document.getElementsByClassName("point_x")).map(v => v.innerHTML);
    pointsYArray = Array.from(document.getElementsByClassName("point_y")).map(v => v.innerHTML);
    pointsRArray = Array.from(document.getElementsByClassName("point_r")).map(v => v.innerHTML);
    drawCanvas();
}

function drawCanvas() {
    let valR = valueR.value * step;
    context.globalAlpha = 1;
    drawRectangle(valR);
    drawTriangle(valR);
    drawCircle(valR);
    drawAXIS();
    drawPoints();
}

function drawTriangle(valR) {
    context.fillStyle = '#FF7400';
    context.beginPath();
    context.moveTo((width / 2) + valR / 2, height / 2);
    context.lineTo(width / 2, height / 2 + valR);
    context.lineTo(width / 2, height / 2);
    context.fill();
}

function drawCircle(valR) {
    context.beginPath();
    context.fillStyle = '#FF7400';
    context.strokeStyle = '#FF7400';
    context.arc(width / 2, height / 2, valR, Math.PI, 3 * Math.PI / 2);
    context.lineTo(width / 2, height / 2)
    context.fill();
    context.stroke();
}

function drawRectangle(valR) {
    context.fillStyle = '#FF7400';
    context.strokeStyle = '#FF7400';
    context.beginPath();
    context.fillRect(width / 2, height / 2, valR / 2, -valR);

}

function drawAXIS() {
    context.strokeStyle = 'black';
    context.fillStyle = 'black';
    context.beginPath();
    context.moveTo(width / 2, 0);
    context.lineTo(width / 2, height);
    context.stroke();
    context.beginPath();
    context.moveTo(0, height / 2);
    context.lineTo(width, height / 2);
    context.stroke();
    context.strokeText("Y", 240, 10);
    context.strokeText("X", 500, height / 2 - 10);
    context.stroke();
    for (let i = -5; i <= 5; i++) {
        context.beginPath();
        let x = width / 2 + step * i;
        context.moveTo(x, height / 2 + axis_separator_offset);
        context.lineTo(x, height / 2 - axis_separator_offset);
        if (i !== 0) {
            context.fillText(i.toString(), x - axis_separator_offset / 2, height / 2 + 3 * axis_separator_offset);
        }
        context.stroke();
    }

    for (let i = -5; i <= 5; i++) {
        context.beginPath();
        let y = height / 2 + step * i;
        context.moveTo(width / 2 + axis_separator_offset, y);
        context.lineTo(width / 2 - axis_separator_offset, y);
        if (i !== 0) {
            context.fillText((-i).toString(), width / 2 + axis_separator_offset, y + axis_separator_offset);
        }
        context.stroke();
    }
}

function clearCanvas() {
    context.save();
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.restore();
    drawPoints();
}

function drawPoints() {
    for (let i = 0; i < pointsXArray.length; i++) {
        if (pointsRArray[i] === valueR.value)
        drawPoint(pointsXArray[i], pointsYArray[i]);
    }
}

function drawPoint(x, y) {
    let canvas = document.getElementById('canvas');
    let ctx = canvas.getContext('2d');
    let pointColor;
    pointColor = '#06266F';
    ctx.beginPath();
    ctx.arc(canvas.width / 2 + x * step, canvas.height / 2 - y * step, axis_separator_offset, 0, Math.PI * 2);
    ctx.fillStyle = pointColor;
    ctx.strokeStyle = pointColor;
    ctx.fill();
    ctx.stroke();
}