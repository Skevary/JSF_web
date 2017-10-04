function init_notify(title, message, severity) {
    let notify_type = (severity === '0' ? 'success' : 'danger');

    let notify = $.notify({
            //Option
            icon: random_icon(),
            title:  title ,
            message: message,
        }, {
            // Settings
            icon_type: 'image',
            type: notify_type,
            animate: {
                enter: 'animated rollIn',
                exit: 'animated rollOut'
            },
            spacing: 15,
            placement: {
                from: "bottom",
                align: "right"
            },
            delay: 3500,
            template:
                '<div data-notify="container" class="notify span4 alert-{0}" role="alert">' +
                '<button type="button" class="close" data-notify="dismiss">&times;</button>' +
                '<img class="pull-left gap-right" data-notify="icon"/>' +
                '<h4>{1}</h4>' +
                ' <p>{2}</p> ' +
                '</div>'
        }
    );
}

function random_icon(){
    let imgCount = 6;
    let randomCount = Math.round(Math.random() * (imgCount - 1)) + 1;
    let images = [];
        images[1] = '/resources/img/icon/bubblegum.png',
        images[2] = '/resources/img/icon/finn.png',
        images[3] = '/resources/img/icon/jake.png',
        images[4] = '/resources/img/icon/ice_king.png',
        images[5] = '/resources/img/icon/lumpy_space_princess.png',
        images[6] = '/resources/img/icon/marceline.png';

    return  images[randomCount];
}