var origin_place_id = null;
var destination_place_id = null;
var map = null;
var infowindow;
function errorHandler(err) {
	if (err.code == 1) {
		alert("Error: Access is denied!");
	} else if (err.code == 2) {
		alert("Error: Position is unavailable!");
	}
}
function getLocation() {
	if (navigator.geolocation) {
		var options = {
			timeout : 60000
		};
		navigator.geolocation.getCurrentPosition(initMap, errorHandler, options);
	} else {
		alert("Sorry, browser does not support geolocation!");
	}
}
function initMap(position) {
	var travel_mode = google.maps.TravelMode.WALKING;
	var latitude = position.coords.latitude;
	var longitude = position.coords.longitude;
	var pyrmont = new google.maps.LatLng(latitude, longitude);
	// khởi tạo bản đồ vào div#map
	map = new google.maps.Map(document.getElementById('map'), {
		mapTypeId : google.maps.MapTypeId.TERRAIN,
		mapTypeControl : false,
		center : pyrmont,
		zoom : 16
	});
	infowindow = new google.maps.InfoWindow();
	// Tạo make hiển thị địa điểm của bạn
	var marker1 = new google.maps.Marker({
	     position: pyrmont,
	     icon: "http://localhost:8080/WEBSITEYTE/images/myhere.png",
	     animation: google.maps.Animation.DROP
	});
	infowindow.setContent("Bạn đang ở đây!");
	infowindow.open(map, marker1);
	google.maps.event.addListener(marker1, 'click', function() {
		infowindow.close();
		infowindow.setContent("Bạn đang ở đây!");
		infowindow.open(map, this);
	});
	marker1.setMap(map);
	//marker data
	$('.dataMarKer').each(function(index){
		var str = $(this).val().split('*');
	    addMarKerData(str[0], str[1], str[2], str[3]);
	});
	
	var directionsService = new google.maps.DirectionsService;
	var directionsDisplay = new google.maps.DirectionsRenderer;
	directionsDisplay.setMap(map);

	var origin_input = document.getElementById('origin-input');
	var destination_input = document.getElementById('destination-input');

	map.controls[google.maps.ControlPosition.TOP_LEFT].push(origin_input);
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(destination_input);

	var origin_autocomplete = new google.maps.places.Autocomplete(origin_input);
	origin_autocomplete.bindTo('bounds', map);
	var destination_autocomplete = new google.maps.places.Autocomplete(destination_input);
	destination_autocomplete.bindTo('bounds', map);

	// Sets a listener on a radio button to change the filter type on Places
	var marker = new google.maps.Marker({
		map : map,
		animation:google.maps.Animation.BOUNCE
	});

	function expandViewportToFitPlace(map, place) {
		if (place.geometry.viewport) {
			map.fitBounds(place.geometry.viewport);
		} else {
			map.setCenter(place.geometry.location);
			map.setZoom(14);
		}
	}

	origin_autocomplete
			.addListener(
					'place_changed',
					function() {
						var place = origin_autocomplete.getPlace();
						if (!place.geometry) {
								window.alert("Autocomplete's returned place contains no geometry");
						return;
						}
						expandViewportToFitPlace(map, place);

						marker.setPlace({
							placeId : place.place_id,
							location : place.geometry.location
						});
						marker.setVisible(true);
						// If the place has a geometry, store its place ID and route if we have
						// the other place ID
						origin_place_id = place.place_id;
						route(origin_place_id, destination_place_id, travel_mode, directionsService, directionsDisplay);
					});

	destination_autocomplete
			.addListener('place_changed',
					function() {
						var place = destination_autocomplete.getPlace();
						if (!place.geometry) {
							window.alert("Autocomplete's returned place contains no geometry");
							return;
						}
						expandViewportToFitPlace(map, place);
						marker.setPlace({
							placeId : place.place_id,
							location : place.geometry.location
						});
						marker.setVisible(true);
						// If the place has a geometry, store its place ID and route if we have
						// the other place ID
						destination_place_id = place.place_id;
						route(origin_place_id, destination_place_id, travel_mode, directionsService, directionsDisplay);
					});

	function route(origin_place_id, destination_place_id, travel_mode, directionsService, directionsDisplay) {
		if (!origin_place_id || !destination_place_id) {
			return;
		}
		directionsService.route({
			origin : {
				'placeId' : origin_place_id
			},
			destination : {
				'placeId' : destination_place_id
			},
			travelMode : travel_mode
		}, function(response, status) {
			if (status === google.maps.DirectionsStatus.OK) {
				directionsDisplay.setDirections(response);
			} else {
				window.alert('Directions request failed due to ' + status);
			}
		});
	}
}

function XemMap(local){
	var l = local.split(", ");
	var p = new google.maps.LatLng(l[0], l[1]);
	map.setCenter(p);
}

function addMarKerData(icon, name, address, local){
	var l = local.split(", ");
	var p = new google.maps.LatLng(l[0], l[1]);
	var infowindow = new google.maps.InfoWindow({
	    content: "<div><label>"+name+"</label><p>"+address+"</p></div>"
	  });
	var marker = new google.maps.Marker({
		position: p,
	    icon: "http://localhost:8080/WEBSITEYTE/admin/images/"+icon,
	    map: map,
	    title: name,
	    animation: google.maps.Animation.DROP
	});
	marker.addListener('click', function() {
	    infowindow.open(map, marker);
	});
}

function ShowHide(e){
	var style = $(e).next().attr("style");
	if(style=="display: none;") {
		$(e).next().attr("style", "display: inherit;");
		$(e).prev().attr("class", "fa fa-minus-square-o");
	} else {
		$(e).next().attr("style", "display: none;");
		$(e).prev().attr("class", "fa fa-plus-square-o");	
	}
}