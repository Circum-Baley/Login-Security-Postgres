$(document)
	.ready(
		function() {
			$
				.ajax({
					url: "/api-device/list",
					method: "GET",
					dataType: "json",
					success: function(data) {
						var tabla = $("#tabla-devices-list");
						data
							.forEach(function(device) {
								tabla
									.append("<tr><td>"
										+ device.id
										+ "</td><td>"
										+ device.name
										+ "</td><td>"
										+ device.brand
										+ "</td><td>"
										+ device.model
										+ "</td><td>"
										+ device.serialNumber
										+ "</td><td>"
										+ device.dateAcquisition
										+ "</td><td>"
										+ '<a href="'
										+ generateDeviceLink(device.profile.id)
										+ '">'
										+ device.profile.name
										+ '</a>'
										+ "</td></tr>");
							});
						/*	function generateDeviceLink(
									attributeValue) {
								// Aquí puedes definir cómo generar el enlace para cada atributo
								// Por ejemplo, podrías usar el valor como parte del URL o usar algún identificador único
								return "/api-profile/list?value="
										+ encodeURIComponent(attributeValue);
							}*/
						function generateDeviceLink(
							profileId) {
							// Aquí generamos un enlace al perfil específico usando su ID
							return "/api-profile/"
								+ encodeURIComponent(profileId);
						}
					},
					error: function() {
						console
							.error("Error al obtener los datos de dispositivos");
					}
				});

		});
