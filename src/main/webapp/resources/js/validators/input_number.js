$(function () {
    let inputEl = document.getElementById('dialog_block:input_data_number');

    inputEl.oninput = function (e) {
        let inputVal = e.target.value;

        //give you NaN if not a number
        let numberVal = parseInt(inputVal);

        if (!numberVal || numberVal < 0) {
            e.target.value = 0;
        } else if (numberVal > 200) {
            e.target.value = 200;
        }
    };
});