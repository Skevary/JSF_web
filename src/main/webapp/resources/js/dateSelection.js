(function ($) {

    let settings = { format: 'DD-MM-YYYY', useCurrent: false };

    function pick(selector, options) { $(selector).datetimepicker(options); }

    jQuery.fn.dateSelection = function () {
       return $('input[date-picker]').each(function () {

            let attr_date_picker = this.getAttribute('date-picker');
            let attr_date_format = this.getAttribute('date-format');

            if (attr_date_picker === 'true')
                attr_date_format !== null ? pick(this, {format: attr_date_format, useCurrent: false} ) : pick(this, settings);

        });
    };

    // Support ajax
    if (typeof jsf !== 'undefined')
    jsf.ajax.addOnEvent(function () {
        $(this).dateSelection();
    });

})(jQuery);
