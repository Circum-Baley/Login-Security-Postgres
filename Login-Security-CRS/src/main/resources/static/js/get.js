$(document).ready(function() {
    $.ajax({
        url: "/datos",  // Ruta del controlador que devuelve los datos
        method: "GET",
        dataType: "json",
        success: function(data) {
            var tabla = $("#tabla-datos");
            data.forEach(function(dato) {
                tabla.append("<tr><td>" + dato.id + "</td><td>" + dato.nombre + "</td></tr>");
                // Agrega más celdas según tus datos
            });
        },
        error: function() {
            console.error("Error al obtener los datos");
        }
    });
});
