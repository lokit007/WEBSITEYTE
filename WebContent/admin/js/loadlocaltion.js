/*
 * js load du lieu map.
 * 1. lay dia diem hien tai du vao id=diaChi
 * 2. google map search 
 */
var placeSearch, autocomplete;

function initAutocomplete() {
	autocomplete = new google.maps.places.Autocomplete(
	(document.getElementById('diaChi')), {
		types : [ 'geocode' ]
	});
	autocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {
	var p = autocomplete.getPlace().geometry.location;
	$("#location").val(p.lat()+','+p.lng());
}

function errorHandler(err) {
	if (err.code == 1) {
		alert("Error: Access is denied!");
	} else if (err.code == 2) {
		alert("Error: Position is unavailable!");
	}
}
function getLocation() {
	if (navigator.geolocation) {
		// timeout at 60000 milliseconds (60 seconds)
		var options = {
			timeout : 60000
		};
		navigator.geolocation.getCurrentPosition(XemThu, errorHandler, options);
	} else {
		alert("Sorry, browser does not support geolocation!");
	}
}
function XemThu(position) {
	$
			.ajax({
				url : "https://maps.googleapis.com/maps/api/geocode/json",
				type : "get",
				dateType : "json",
				data : {
					latlng : position.coords.latitude + ','
							+ position.coords.longitude,
					sensor : false,
					language : 'vi'
				},
				success : function(result, textStatus) {
					$("#diaChi").val(result.results[0].formatted_address);
					$("#location").val(position.coords.latitude+','+position.coords.longitude);
				}
			});
}