<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="js/jquery.lockfixed.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/back-to-top.js"></script>
<script type="text/javascript" src="bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="font-awesome-4.4.0/css/font-awesome.min.css">
<script src="js/jquery.validate.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/clientStyle.css">
<script type="text/javascript">
	//Slide notify in homepage
	$(function animate() {
		$(".div-slide .div-show-content-item:first").each(function() {
			$(this).delay(5000);
			$(this).animate({
				color : 'gray'
			}, 2000, function() {
				$(this).insertAfter(".div-slide .div-show-content-item:last");
				$(this).fadeIn();
				$(this).css({
					color : 'none'
				});
				setTimeout(function() {
					animate()
				}, 2000);
			});
		});
	});
	$(document).ready(function(){
		$(window).scroll(function(){
		    // Nếu cuộn được hơn 150px rồi
		        if($(this).scrollTop()>100){
			      	$("#div-header-menu").attr("class", "menu-fix");
		        } else {
		        	$("#div-header-menu").removeAttr("class");
		        }
		        if($(this).scrollTop()>320){
			      	$("#btn-xuly").attr("class", "view-dangky");
		        } else {
		        	$("#btn-xuly").removeAttr("class");
		        }
		    });
	});
	var refreshId = setInterval(function(){
	    $("#load-60").load('dataThongTin.jsp');
	}, 60000);
	$(function(){
		$.ajax({
			url : 'menu-bar.action',
			type : 'POST',
			success:function(data, textStatus, jqXHR){
				$("#div-content-right").before(data);
			}
		});
	});
	function showSearch(e, b){
		if(b==1){
			$("#f-timkiem-chitiet").attr("style", "display: inherit;");
			$(e).attr("onclick","showSearch(this, 0);");
		} else {
			$("#f-timkiem-chitiet").attr("style", "display: none;");
			$(e).attr("onclick","showSearch(this, 1);");
		}
	}
	function showLogin(e){
		$("#"+e).modal("hide");
		$("#dangNhapModal").modal("show");
	}
</script>
<style type="text/css">
.div-show-content-item {
	transition: all 2s 0.5s ease-in-out;
	-moz-transition: all 2s 0.5s ease-in-out;
	-webkit-transition: all 2s 0.5s ease-in-out;
}
</style>
<script type="text/javascript" src="https://secure.skypeassets.com/i/scom/js/skype-uri.js"></script>