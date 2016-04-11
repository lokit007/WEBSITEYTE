<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<title>Dịch vụ y tế</title>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>
<style>
#map {
	height: 500px;
	width: 90%;
	border: 1px solid #333;
	margin-top: 0.6em;
}
.fixmap {
    position: fixed;
    left: 350px;
    top: 40px;
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
	var map;
	var infowindow;
	function errorHandler(err) {
		if (err.code == 1) {
			alert("Error: Access is denied!");
		} else if (err.code == 2) {
			alert("Error: Position is unavailable!");
		}
	}
	function initialize(position) {
		var latitude = position.coords.latitude;
		var longitude = position.coords.longitude;
		var pyrmont = new google.maps.LatLng(latitude, longitude);
		// khởi tạo bản đồ vào div#map
		map = new google.maps.Map(document.getElementById('map'), {
			mapTypeId : google.maps.MapTypeId.TERRAIN,
			// ROADMAP displays the normal, default 2D tiles of Google Maps.
			// SATELLITE displays photographic tiles.
			// HYBRID displays a mix of photographic tiles and a tile layer for prominent features (roads, city names).
			// TERRAIN displays physical relief tiles for displaying elevation and water features (mountains, rivers, etc.).
			center : pyrmont,
			zoom : 14
		// Zoom hiện tại
		});
		// Tạo make hiển thị địa điểm của bạn
		var marker=new google.maps.Marker({
		     position : pyrmont,
		     animation:google.maps.Animation.BOUNCE
		});
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.setContent("Bạn ở đây!");
			infowindow.open(map, this);
		});
		marker.setMap(map);
		// Tạo đối tượng request
		var request = {
			location : pyrmont, // Tìm từ vị trí trung tâm
			radius : 50000, // Bán kính 500m
			types : [ 'hospital' ]
		// Tìm tất cả nhà hàng
		};
		// Hiển thị thông tin về địa điểm
		infowindow = new google.maps.InfoWindow();
		var service = new google.maps.places.PlacesService(map);
		// Thực hiện gọi hàm nearbySearch để tìm những atm lân cận và trả về thực hiện
		// hàm callback
		service.nearbySearch(request, callback);
	}
	function getLocation() {
		if (navigator.geolocation) {
			// timeout at 60000 milliseconds (60 seconds)
			var options = {
				timeout : 60000
			};
			navigator.geolocation.getCurrentPosition(initialize,
					errorHandler, options);
		} else {
			alert("Sorry, browser does not support geolocation!");
		}
	}
	function callback(results, status) {
		if (status == google.maps.places.PlacesServiceStatus.OK) {
			for (var i = 0; i < results.length; i++) {
				// Duyệt từng địa điểm và đánh dấu lên bản đồ
				createMarker(results[i]);
			}
		}
	}
	// Hàm đánh dấu lên bản đồ từ 1 đối tượng địa điểm place
	function createMarker(place) {
		var placeLoc = place.geometry.location;
		var marker = new google.maps.Marker({
			map : map,
			position : place.geometry.location
		});
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.setContent(place.name);
			infowindow.open(map, this);
		});
	}
	google.maps.event.addDomListener(window, 'load', getLocation);
</script>
</head>
<body>
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
					<div id="map"></div>
					<div id="text"></div>
				</s:div>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:div cssClass="div-quangcao">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>
</html>
