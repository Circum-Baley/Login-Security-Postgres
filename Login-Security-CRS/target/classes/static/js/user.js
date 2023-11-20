$(document).ready(
	function() {
		$.ajax({
			url: "/api-user/list",
			method: "GET",
			dataType: "json",
			success: function(data) {
				var tabla = $("#table-users-list");
				data
					.forEach(function(user) {
						var vehicles = user.vehicles.map(
							function(vehicle) {
								var vehicleLink = generateDeviceLink(vehicle.id);
								return '<a href="' + vehicleLink + '">'
									+ vehicle.patent
									+ '</a>';
							})
							.join(' , ');
						var row = $("<tr>").appendTo(tabla);
						$("<td>").addClass("text-center").text(user.id).appendTo(row);
						$("<td>").text(user.username).appendTo(row);
						$("<td>").text(user.password).appendTo(row);
						$("<td>").addClass("text-center").html(vehicles).appendTo(row);
					});

				function generateDeviceLink(
					vehicleId) {
					// Aquí generamos un enlace al perfil específico usando su ID - call it + generateDeviceLink(device.profile.id)
					return "/api-vehicle/vehicle/"
						+ encodeURIComponent(vehicleId);
				}
			},
			error: function() {
				console
					.error("Error al obtener los datos de dispositivos");
			}
		});
	});
$(document).ready(function() {
	$.ajax({
		url: "/api-user/UserTotalCount",
		method: "GET",
		dataType: "json",
		success: function(data) {
			$("#UserTotalCount").text(data); // Actualizar un elemento HTML con el total de objetos
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.error("Error al obtener el total de objetos:", textStatus, errorThrown);
			// Aquí puedes mostrar un mensaje de error al usuario si es necesario
		}
	});
});
