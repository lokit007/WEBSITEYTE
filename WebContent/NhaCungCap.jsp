<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<title>Dịch vụ y tế</title>
<link rel="stylesheet" href="css/star-rating.min.css">
<script src="js/star-rating.min.js"></script>
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
						<i class="fa fa-hand-o-right"></i> Giới thiệu <s:property value="nhaCungCap.taiKhoan.hoTen"/>
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:property value="nhaCungCap.gioiThieu" escape="false"/>
				</s:div>
			</s:div>
			<s:div cssClass="div-quangcao">
				<img src="http://placehold.it/970x90">
			</s:div>
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-main">
					<div id="sahred">
						Đánh giá : &nbsp;&nbsp;<input id="rating-input" onchange="DanhGia(this, '${ nhaCungCap.idNhaCungCap}');" value="${ nhaCungCap.danhGia }"
									type="number" class="rating" min=0 max=5 step=1 data-size="xs"
									data-rtl="false" data-default-caption="{rating} Sao"
									data-star-captions="{}">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Chia sẻ : &nbsp;&nbsp;<a href="https://twitter.com/share"
							class="twitter-share-button">Tweet</a>
						<div id="fb-root"></div>
						<!-- Your share button code -->
						<div class="fb-share-button"
							data-href="http://webvietnhat-demo.jelastic.skali.net/"
							data-layout="button_count"></div>
						<g:plusone></g:plusone>
					</div>
					<script src="js/facebook.login.ajax.min.js" type="text/javascript"></script>
					<!-- Google+ -->
					<script type="text/javascript"
						src="https://apis.google.com/js/plusone.js"></script>
					<!-- Twitter -->
					<script>
						!function(d, s, id) {
							var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
									.test(d.location) ? 'http' : 'https';
							if (!d.getElementById(id)) {
								js = d.createElement(s);
								js.id = id;
								js.src = p
										+ '://platform.twitter.com/widgets.js';
								fjs.parentNode.insertBefore(js, fjs);
							}
						}(document, 'script', 'twitter-wjs');
					</script>
					<!-- Facebook -->
					<script>
						(function(d, s, id) {
							$('.fb-share-button').attr("data-href",
									window.location.href);
							var js, fjs = d.getElementsByTagName(s)[0];
							if (d.getElementById(id))
								return;
							js = d.createElement(s);
							js.id = id;
							js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.4&appId=1671772309710877";
							fjs.parentNode.insertBefore(js, fjs);
						}(document, 'script', 'facebook-jssdk'));
					</script>
				</s:div>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>

<script type="text/javascript">
	function HienThi() {
		$(".anView").attr('style', 'display: inherit;');
		$("#btn-dangky").attr('style', 'display: none;');
	}
	function XoaPhanTu() {
		$("#showError").remove();
	}
	function DanhGia(e, f){
		$.ajax({
			url : "danh-gia.action",
			type : "POST",
			data : {
				idNhaCungCap : f,
				danhGia : $(e).val()
			},
			success : function(data, textStatus, jqXHR) {
				if (data.indexOf("thất bại") > -1) {
					$("#loiDangKy").attr("style", "display: inherit;");
				} else {
					$("#loiDangKy").attr("style", "display: none;");
				}
				window.document.reload(true);
			}
		});
	}
	$(document).ready(function() {
		$("#f-dangky-dichvu").validate({
			rules : {
				hoTen : "required",
				dienThoai : {
					required : true,
					digits : true
				},
				email : {
					required : true,
					email : true
				}
			},
			messages : {
				hoTen : "Nhập họ tên/tổ chức!",
				dienThoai : {
					required : "Nhập điện thoại liên hệ!",
					digits : "Số điện thoại khồn đúng!"
				},
				email : {
					required : "Nhập email liên hệ!",
					email : "Không đúng định dạng email!"
				}
			},
			submitHandler : function(form) {
				var postData = $(form).serializeArray();
				var formURL = $(form).attr("action");
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					beforeSubmit : function() {
						$("#loadWhile").attr("style", "display: inherit;");
					},
					success : function(data, textStatus, jqXHR) {
						if (data.indexOf("thất bại") > -1) {
							$("#loiDangKy").attr("style", "display: inherit;");
						} else {
							$("#loiDangKy").attr("style", "display: none;");
							$('#myModal').modal('hide');
							alert("Đăng ký dịch vụ thành công.");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$("#loiDangKy").attr("style", "display: none;");
						$('#myModal').modal('hide');
						alert("Lỗi");
					}
				});
			}
		});
	});
</script>
</html>
