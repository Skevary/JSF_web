$(function () {
    let inputEl = document.getElementById('control_block:number_generation');

    inputEl.oninput = function (e) {
        let inputVal = e.target.value;

        //give you NaN if not a number
        let numberVal = parseInt(inputVal);

        if (!numberVal || numberVal < 0) {
            e.target.value = 0;
        } else if (numberVal > 999) {
            e.target.value = 999;
        }
    };
});