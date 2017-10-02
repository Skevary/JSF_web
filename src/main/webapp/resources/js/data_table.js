var table = $(".dataTable").dataTable({
    "processing": true,
    "serverSide": true,
    "ajax": "/pages/secured/getFilteredData",
    "columns": [
        { "data": "id" },
        { "data": "number" },
        { "data": "date" },
        { "data": "text" },
        { "data": "group" }
    ]
} );

jsf.ajax.addOnEvent(function (data) {
    if (data.status === "success") {
          table.api().ajax.reload();
    }
});