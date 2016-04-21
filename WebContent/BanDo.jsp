<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<sx:head />
<title>Bản đồ y tế</title>
<style>
div#text {
    width: 25%;
    float: left;
}
#map {
	float: right;
	height: 500px;
	width: 74%;
	border: 1px solid #333;
	margin-top: 0.6em;
}
.fixmap {
    position: fixed;
    top: 40px;
}
.controls {
	margin-top: 5px;
	border: 1px solid transparent;
	border-radius: 2px 0 0 2px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	height: 32px;
	outline: none;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#origin-input, #destination-input {
	background-color: #fff;
	font-family: Roboto;
	font-size: 15px;
	font-weight: 300;
	margin-left: 12px;
	padding: 0 11px 0 13px;
	text-overflow: ellipsis;
	width: 200px;
}

#origin-input:focus, #destination-input:focus {
	border-color: #4d90fe;
}

.hide {
	display: none;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(window).scroll(function(){
	    // Nếu cuộn được hơn 150px rồi
	        if($(this).scrollTop()>100){
		      	$("#div-content-right").attr("class", "fixmap");
	        } else {
	        	$("#div-content-right").removeAttr("class");
	        }
	    });
});
</script>
<script>
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
		navigator.geolocation.getCurrentPosition(initMap,
				errorHandler, options);
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
	     icon: "http://localhost:8080/WEBSITEYTE/admin/images/icon-bv-20.png",
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
	map.controls[google.maps.ControlPosition.TOP_LEFT]
			.push(destination_input);

	var origin_autocomplete = new google.maps.places.Autocomplete(
			origin_input);
	origin_autocomplete.bindTo('bounds', map);
	var destination_autocomplete = new google.maps.places.Autocomplete(
			destination_input);
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
							window
									.alert("Autocomplete's returned place contains no geometry");
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
						route(origin_place_id, destination_place_id,
								travel_mode, directionsService,
								directionsDisplay);
					});

	destination_autocomplete
			.addListener(
					'place_changed',
					function() {
						var place = destination_autocomplete.getPlace();
						if (!place.geometry) {
							window
									.alert("Autocomplete's returned place contains no geometry");
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
						route(origin_place_id, destination_place_id,
								travel_mode, directionsService,
								directionsDisplay);
					});

	function route(origin_place_id, destination_place_id, travel_mode,
			directionsService, directionsDisplay) {
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
</script>
<style type="text/css">
	.tree-node ul {
	    border-left: 1px solid gray;
	    margin-left: 5px;
	    margin-top: -10px;
	    padding: 5px 0px 0px 10px;
	    list-style: none;
	}
	span.ic-chil {
	    margin-left: -10px;
	    width: 10px;
	}
	.tree-node, .tree-node label {
	    cursor: pointer;
	}
</style>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDanVTriVyYbvbkp7c8RPD7O1SOuKo8aK4&libraries=places"
	async defer></script>
</head>
<body onload="getLocation();">
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Các trung tâm y tế Huế
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<input id="origin-input" class="controls" type="text"
						placeholder="Địa điểm A ...">
				
					<input id="destination-input" class="controls" type="text"
						placeholder="Đến địa điểm B ...">
					<div id="text">
						<div class="tree-node">
							<i class="fa fa-minus-square-o" aria-hidden="true"></i>
							<label class="lable-root" onclick="ShowHide(this);">Bản đồ y tế Huế</label>
							<ul>
								<li>
									<i class="fa fa-minus-square-o" aria-hidden="true"></i>
									<label class="lable-root" onclick="ShowHide(this);">Bệnh viện</label>
									<ul>
										<s:iterator value="list">
										<s:if test="loaiNCC=='Bệnh viện'">
											<li onclick="XemMap('${ taiKhoan.location }');">
												<span class="ic-chil">-</span>
												<s:property value="taiKhoan.hoTen"/>
												<s:hidden cssClass="dataMarKer" value="icon-bv-20.png*%{ taiKhoan.hoTen }*%{ taiKhoan.diaChi }*%{ taiKhoan.location }"></s:hidden>
											</li>
										</s:if>
										</s:iterator>
									</ul>
								</li>
								<li>
									<i class="fa fa-minus-square-o" aria-hidden="true"></i>
									<label class="lable-root" onclick="ShowHide(this);">Trung tâm y tế</label>
									<ul>
										<s:iterator value="list">
										<s:if test="loaiNCC=='Trung tâm y tế'">
											<li onclick="XemMap('${ taiKhoan.location }');">
												<span class="ic-chil">-</span>
												<s:property value="taiKhoan.hoTen"/>
												<s:hidden cssClass="dataMarKer" value="icon-tt-20.png*%{ taiKhoan.hoTen }*%{ taiKhoan.diaChi }*%{ taiKhoan.location }"></s:hidden>
											</li>
										</s:if>
										</s:iterator>
									</ul>
								</li>
								<li>
									<i class="fa fa-minus-square-o" aria-hidden="true"></i> 
									<label class="lable-root" onclick="ShowHide(this);">Trạm xá</label>
									<ul>
										<s:iterator value="list">
										<s:if test="loaiNCC=='Trạm xá'">
											<li onclick="XemMap('${ taiKhoan.location }');">
												<span class="ic-chil">-</span>
												<s:property value="taiKhoan.hoTen"/>
												<s:hidden cssClass="dataMarKer" value="icon-tx-20.png*%{ taiKhoan.hoTen }*%{ taiKhoan.diaChi }*%{ taiKhoan.location }"></s:hidden>
											</li>
										</s:if>
										</s:iterator>
									</ul>
								</li>
								<li>
									<i class="fa fa-minus-square-o" aria-hidden="true"></i> 
									<label class="lable-root" onclick="ShowHide(this);">Y tế tư nhân</label>
									<ul>
										<s:iterator value="list">
										<s:if test="loaiNCC=='Tư nhân'">
											<li onclick="XemMap('${ taiKhoan.location }');">
												<span class="ic-chil">-</span>
												<s:property value="taiKhoan.hoTen"/>
												<s:hidden cssClass="dataMarKer" value="icon-tn-20.png*%{ taiKhoan.hoTen }*%{ taiKhoan.diaChi }*%{ taiKhoan.location }"></s:hidden>
											</li>
										</s:if>
										</s:iterator>
									</ul>
								</li>
							</ul>
						</div>
						<div class="tree-node">
							<i class="fa fa-minus-square-o" aria-hidden="true"></i>
							<label class="lable-root" onclick="ShowHide(this);">Chú thích</label>
							<ul>
								<li>
									<span class="ic-chil">-</span>
									<img alt="Bệnh viện" src="admin/images/icon-bv-20.png"> Bệnh viện
								</li>
								<li>
									<span class="ic-chil">-</span>
									<img alt="Bệnh viện" src="admin/images/icon-tt-20.png"> Trung tâm y tế
								</li>
								<li>
									<span class="ic-chil">-</span>
									<img alt="Bệnh viện" src="admin/images/icon-tx-20.png"> Trạm xá
								</li>
								<li>
									<span class="ic-chil">-</span>
									<img alt="Bệnh viện" src="admin/images/icon-tn-20.png"> Tư nhân
								</li>
							</ul>
						</div>
					</div>
					<div id="map"></div>
					<div class="clear"></div>
				</s:div>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:div cssClass="div-quangcao-last">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>
</html>
