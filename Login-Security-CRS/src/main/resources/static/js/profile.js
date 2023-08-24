$(document).ready(function() {
	$.ajax({
		url: "/api-profile/list", // Cambiar la URL al punto final de perfiles
		method: "GET",
		dataType: "json",
		success: function(data) {
			var tabla = $("#tabla-profiles-list");
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
$(document).ready(function() {
	$.ajax({
		url: "/api-profile/1", // Cambia esto a la URL correcta para obtener el perfil por ID
		method: "GET",
		dataType: "json",
		success: function(data) {
			// Actualizar los elementos HTML con los datos del perfil
			$("#name").attr("placeholder", data.name);
			$("#lastName").attr("placeholder", data.lastName);
			$("#birthDate").attr("placeholder", data.birthDate);
		},
		error: function() {
			console.error("Error al obtener los datos del perfil");
		}
	});
});