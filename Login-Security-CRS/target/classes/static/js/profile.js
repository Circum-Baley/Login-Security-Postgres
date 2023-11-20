$(document).ready(function() {
	$.ajax({
		url: "/api-profile/list", // Cambiar la URL al punto final de perfiles
		method: "GET",
		dataType: "json",
		success: function(data) {
			var tabla = $("#table-profiles-list");
			data.forEach(function(profile) {
				var devices = profile.devices.map(function(device) {
					var deviceLink = generateDeviceLink(device.id);
					return '<a href="' + deviceLink + '">' + device.name + '</a>';
				}).join(', ');
				tabla.append("<tr><td>" + profile.id + "</td><td>" + profile.name + "</td><td>" + profile.lastName + "</td><td>" + devices + "</td></tr>");
			});
		},
		error: function() {
			console.error("Error al obtener los datos de perfiles");
		}
	});

	function generateDeviceLink(deviceId) {
		return "/api-device/device/" + encodeURIComponent(deviceId);
	}
});
$(document).ready(
	function() {
		$.ajax({
			url: "/api-profile/list",
			method: "GET",
			dataType: "json",
			success: function(data) {
				var tabla = $("#tabla-profiles-list");
				data.forEach(function(profile) {
						var user = profile.user; // Obtener el usuario relacionado con el perfil
						var devices = profile.devices.map(
							function(device) {
								var deviceLink = generateDeviceLink(device.id);
								return '<a href="' + deviceLink + '">'
									+ device.name + '</a>';
							})
							.join(', ');

						var row = $("<tr>").appendTo(tabla);
						$("<td>").addClass("text-center").text(profile.id).appendTo(row); // Mostrar ID del perfil
						$("<td>").addClass("text-left").text(profile.name).appendTo(row); // Mostrar nombre del perfil
						$("<td>").addClass("text-left").text(profile.lastName).appendTo(row); // Mostrar apellido del perfil
						$("<td>").addClass("text-center").text(profile.birthDate).appendTo(row); // Mostrar fecha de nacimiento del perfil
						$("<td>").addClass("text-center").html(devices).appendTo(row); // Mostrar dispositivos

						var userLink = generateUserLink(user.id); // Generar enlace al usuario
						$("<td>").addClass("text-center").html('<a href="/Profile.html" class="user-link" data-user-id="' + user.id + '">' + user.username + '</a>').appendTo(row); // Mostrar enlace de usuario
					});
				function generateDeviceLink(deviceId) {
					// Aquí generamos un enlace al dispositivo específico usando su ID
					return "/api-device/" + encodeURIComponent(deviceId);
				}

				function generateUserLink(userId) {
					// Aquí generamos un enlace al usuario específico usando su ID
					return "/api-user/" + encodeURIComponent(userId);
				}
			},
			error: function() {
				console.error("Error al obtener los datos de perfiles");
			}
		});
	});