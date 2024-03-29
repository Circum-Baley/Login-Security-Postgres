$(document).ready(function() {
	$.ajax({
		url: "/api-profile/list",
		method: "GET",
		dataType: "json",
		success: function(data) {
			var tabla = $("#table-profiles-list");

			data.forEach(function(profile) {
				var user = profile.user; // Obtener el usuario relacionado con el perfil
				var devices = profile.devices.map(function(device) {
					var deviceLink = generateDeviceLink(device.id);
					return '<a href="' + deviceLink + '">' + device.name + '</a>';
				}).join(', ');

				var row = $("<tr>").appendTo(tabla);
				$("<td>").addClass("text-center").text(profile.id).appendTo(row); // Mostrar ID del perfil
				$("<td>").text(profile.name).appendTo(row); // Mostrar nombre del perfil
				$("<td>").text(profile.lastName).appendTo(row); // Mostrar apellido del perfil
				$("<td>").text(profile.birthDate).appendTo(row); // Mostrar fecha de nacimiento del perfil
				$("<td>").addClass("text-center").html(devices).appendTo(row); // Mostrar dispositivos

				var userLink = generateUserLink(user.id); // Generar enlace al usuario
				$("<td>").html('<a href="' + userLink + '">' + user.username + '</a>').appendTo(row); // Mostrar enlace de usuario
			});
		},
		error: function() {
			console.error("Error al obtener los datos de perfiles");
		}
	});

	function generateDeviceLink(deviceId) {
		// Aquí generamos un enlace al dispositivo específico usando su ID
		return "/api-device/device/" + encodeURIComponent(deviceId);
	}

	function generateUserLink(userId) {
		// Aquí generamos un enlace al usuario específico usando su ID
		return "/api-user/" + encodeURIComponent(userId);
	}
});
