<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="js/jquery.lockfixed.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/back-to-top.js"></script>
<script type="text/javascript" src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="font-awesome-4.4.0/css/font-awesome.min.css">
<script src="js/jquery.validate.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/clientStyle.css">
<script type="text/javascript">
	//Slide notify in homepage
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
				/* $("#div-content-right").before(data); */
				$("#div-content-right").after(data);
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
	function ShowMenu(b){
		if(b==1){
			$("#menu-data").attr("style", "display: inherit;");
			$("#f-timkiem").attr("style", "display: none;");
			$("#f-timkiem-mb").attr("style", "display: none;");
			$("#icon-menu").attr("onclick","ShowMenu(0);");
			$("#icon-search").attr("onclick","ShowMenu(4);");
			$("#icon-search").attr("class","fa fa-search");
			$("#icon-menu").attr("class","fa fa-bars fa-rotate-90");
		} else if(b==0) {
			$("#menu-data").attr("style", "display: none;");
			$("#f-timkiem-mb").attr("style", "display: none;");
			$("#icon-menu").attr("onclick","ShowMenu(1);");
			$("#icon-search").attr("onclick","ShowMenu(4);");
			$("#icon-search").attr("class","fa fa-search");
			$("#icon-menu").attr("class","fa fa-bars");
		} else if(b==4) {
			$("#menu-data").attr("style", "display: none;");
			$("#f-timkiem-mb").attr("style", "display: inherit;");
			$("#icon-search").attr("onclick","ShowMenu(3);");
			$("#icon-menu").attr("onclick","ShowMenu(1);");
			$("#icon-search").attr("class","fa fa-times");
			$("#icon-menu").attr("class","fa fa-bars");
		} else {
			$("#menu-data").attr("style", "display: none;");
			$("#f-timkiem-mb").attr("style", "display: none;");
			$("#icon-search").attr("onclick","ShowMenu(4);");
			$("#icon-menu").attr("onclick","ShowMenu(1);");
			$("#icon-search").attr("class","fa fa-search");
			$("#icon-menu").attr("class","fa fa-bars");
		}
	}
	function showLogin(e){
		$("#"+e).modal("hide");
		$("#dangNhapModal").modal("show");
	}
	// Hien thi thong bao
	function ShowMessage(model, mes) {
		$('#' + model).modal('show');
		$('#mes' + model).html(mes);
	}
	$(document).ready(function() {
		$("#fm-dangnhap").validate({
			rules : {
				taiKhoan : {
					required : true
				},
				matKhau : {
					required : true
				}
			},
			messages : {
				taiKhoan : {
					required : "Chưa nhập tài khoản"
				},
				matKhau : {
					required : "Chưa nhập mật khẩu"
				}
			},
			submitHandler : function(form) {
				var postData = $(form).serializeArray();
			    var formURL = $(form).attr("action");
			    $.ajax({
			        url : formURL,
			        type: "POST",
			        data : postData,
			        beforeSubmit : function (){
			        	$("#loadWhile").attr("style", "display: inherit;");
			        },
			        success:function(data, textStatus, jqXHR) 
			        {
			            if(data.indexOf("thất bại")>-1){
			            	$("#loiDangNhap").remove();
			            	$("#fm-dangnhap").prepend("<p class='bg-danger' id='loiDangNhap'>Tài khoản hoặc mật khẩu không chính xác! Vui lòng đăng nhập lại.</p>");
			            } else {
			            	window.location.reload(true);
			            }
			        },
			        error: function(jqXHR, textStatus, errorThrown) 
			        {
			        	$("#loiDangKy").attr("style", "display: none;");
			        	$('#dangNhapModal').modal('hide');
			        	ShowMessage('error', 'Lỗi hệ thông! Vui lòng quay lại sau.');   
			        }
			   });
			}
		});
	});
	$(document).ready(function() {
		$("#nhanEMail").validate({
			rules : {
				email : {
					required : true,
					email : true
				}
			},
			messages : {
				email : {
					required : "Chưa nhập email!",
					email : "Mail không đúng"
				}
			},
			submitHandler : function(form) {
				var postData = $(form).serializeArray();
			    var formURL = $(form).attr("action");
			    $.ajax({
			        url : formURL,
			        type: "POST",
			        data : postData,
			        beforeSubmit : function (){
			        	$("#loadWhile").attr("style", "display: inherit;");
			        },
			        success:function(data, textStatus, jqXHR) 
			        {
			        	$("#loadWhile").attr("style", "display: none;");
			            if(data.indexOf("thất bại")>-1){
			            	ShowMessage('error', 'Đăng ký nhận mail thất bại!');
			            } else {
			            	ShowMessage('success', 'Đăng ký nhận mail thành công.');
			            }
			        },
			        error: function(jqXHR, textStatus, errorThrown) 
			        {
			        	$("#loadWhile").attr("style", "display: none;");
			        	ShowMessage('error', 'Lỗi hệ thông! Vui lòng quay lại sau.');
			        }
			   });
			}
		});
	});
</script>
<script type="text/javascript" src="https://secure.skypeassets.com/i/scom/js/skype-uri.js"></script>
