<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<script type="text/javascript" src="js/jquery.MultiFile.js"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="css/datepicker.css">
<script src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#ngayBatDau').datepicker({
			format : "dd-mm-yyyy",
			autoclose : true,
			todayBtn : true
		});
		$('#ngayKetThuc').datepicker({
			format : "dd-mm-yyyy",
			autoclose : true,
			todayBtn : true
		});
	});
</script>
<title>Đăng nhập tài khoản</title>
<script>
	var placeSearch, autocomplete;

	function initAutocomplete() {
		// Create the autocomplete object, restricting the search to geographical
		// location types.
		autocomplete = new google.maps.places.Autocomplete(
		/** @type {!HTMLInputElement} */
		(document.getElementById('diaChi')), {
			types : [ 'geocode' ]
		});
		// fill data khi chọn
		autocomplete.addListener('place_changed', fillInAddress);
	}

	function fillInAddress(){
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
			navigator.geolocation.getCurrentPosition(XemThu, errorHandler,
					options);
		} else {
			alert("Sorry, browser does not support geolocation!");
		}
	}
	function XemThu(position) {
		$.ajax({
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
</script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDanVTriVyYbvbkp7c8RPD7O1SOuKo8aK4&libraries=places&callback=initAutocomplete"
	async defer></script>
</head>
<body onload="getLocation();">
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:if test="hasActionErrors()">
				<s:div id="showError">
					<s:actionerror />
					<button class="btn btn-danger btn-xs" onclick="XoaPhanTu();"
						style="float: right; margin-top: 5px;">Ok</button>
				</s:div>
			</s:if>
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-sign-in"></i> Đăng nhập tài khoản thành viên 
					</s:a>
				</s:div>
				<form action="dangnhap-action.action" method="post" id="f-dangnhap">
					<br>
					<s:label value="Tài khoản : " for="taiKhoan"></s:label>
					<s:textfield name="taiKhoan" cssClass="form-control"></s:textfield>
					<br>
					<s:label value="Mật khẩu : " for="matKhau"></s:label>
					<s:password name="matKhau" cssClass="form-control"></s:password>
					<br>
					<button onclick="HienThi();" type="button" id="btn-dangky">Đăng
						ký</button>
					<input type="submit" value="Đăng nhập" />
				</form>
				<br>
				<script type="text/javascript">
					function HienThi() {
						$(".anView").attr('style', 'display: inherit;');
						$("#btn-dangky").attr('style', 'display: none;');
					}
					function XoaPhanTu() {
						$("#showError").remove();
					}
				</script>
				<s:div cssClass="div-content-home-title anView"
					cssStyle="display: none;">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-user-plus"></i> Đăng ký tài khoản thành viên 
					</s:a>
				</s:div>
				<form action="dang-ky-action.action" method="post" id="f-dangky"
					class="anView" style="display: none;">
					<s:div id="info-taikhoan">
						<br>
						<s:label value="Tài khoản : " for="taiKhoan"></s:label>
						<s:textfield name="taiKhoan" cssClass="form-control"></s:textfield>
						<br>
						<s:label value="Mật khẩu : " for="matKhau"></s:label>
						<s:password name="matKhau" cssClass="form-control"></s:password>
						<br>
						<s:label value="Nhập lại mật khẩu : " for="matKhauLai"></s:label>
						<s:password name="matKhauLai" cssClass="form-control"></s:password>
						<br>
						<img src="http://placehold.it/300x100">
					</s:div>
					<s:div id="info-capchart">
						<fieldset>
							<legend
								style="background-color: #D61515; padding: 5px; color: white; font-weight: bold; border-radius: 5px;">
								<input type="checkbox" name="dongY" checked="checked">
								Tôi đồng ý với quy định của sở y tế
							</legend>
							<p style="max-height: 250px; overflow-x: auto;">
								- Luật Khám bệnh, chữa bệnh số 40/2009/QH12 Ngày 23/11/2009 <br>-
								Nghị định số 87/2011/NĐ-CP của Chính phủ ngày 27/09/2011 quy
								định chi tiết và thi hành một số điều của Luật khám bệnh chữa
								bệnh. <br>- Thông tư số 41/2011/TT-BYT ngày 14/11/2011 của
								Bộ Y tế hướng dẫn cấp chứng chỉ hành nghề đối với người hành
								nghề và cấp giấy phép hoạt động đối với cơ sở khám bệnh, chữa
								bệnh. <br>- Thông tư liên tịch số 08/2007/TTLT- BYT- BNV
								ngày 05 tháng 6 năm 2007 của liên Bộ: Bộ Y tế - Bộ Nội vụ hướng
								dẫn định mức biên chế sự nghiệp trong các cơ sở y tế nhà nước. <br>-
								Quyết định số 2271/2002/QĐ - BYT ngày 17 tháng 6 năm 2002 của Bộ
								trưởng Bộ Y tế về việc ban hành Tiêu chuẩn thiết kế Trạm y tế cơ
								sở - Tiêu chuẩn ngành. <br>- Thông tư số 03/2012/TT-BTC
								ngày 08/01/2013 của Bộ Tài chính quy định phí thẩm định kinh
								doanh thương mại có điều kiện; thẩm định tiêu chuẩn, điều kiện
								hành nghề y, dược; lệ phí cấp giấy phép xuất, nhập khẩu trang
								thiết bị y tế, dược phẩm; cấp chứng chỉ hành nghề y; cấp giấy
								phép hoạt động đối với cơ sở khám, chữa bệnh.
							</p>
						</fieldset>
					</s:div>
					<s:div cssClass="clear"></s:div>
					<br>
					<fieldset>
						<legend>Thông tin cá nhân</legend>
						<s:label value="Cá nhân/Tổ chức : " for="hoTen"></s:label>
						<s:textfield name="hoTen" cssClass="form-control"></s:textfield>
						<br>
						<s:label value="Địa chỉ : " for="diaChi"></s:label>
						<s:textfield id="diaChi" name="diaChi" cssClass="form-control"></s:textfield>
						<s:hidden id="location" name="location"></s:hidden>
						<s:div cssClass="clear"></s:div>
						<s:div cssClass="div-col-50">
							<br>
							<s:label value="Điện thoại liên hệ : " for="dienThoai"></s:label>
							<s:textfield name="dienThoai" cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="div-col-50">
							<br>
							<s:label value="Email : " for="email"></s:label>
							<s:textfield name="email" cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</fieldset>
					<br>
					<s:submit value="Đăng ký tài khoản thành viên"
						cssClass="form-control btn btn-success"></s:submit>
				</form>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>
<script type="text/javascript">
	/* 
	$(function() {
		$('.datepicker').datepicker({
			format : "yyyy-mm-dd"
		}).on('changeDate', function(ev) {
			$(this).datepicker('hide');
		});
	}); */
	$(document).ready(function() {
		$("#f-dangky").validate({
			rules : {
				taiKhoan : {
					required : true
				},
				matKhau : {
					required : true
				},
				hoTen : "required",
				matKhauLai : {
					required : true
				},
				diaChi : {
					required : true
				},
				dienThoai : {
					required : true
				},
				email : {
					required : true,
					email : true
				}
			},
			messages : {
				taiKhoan : {
					required : "Bạn chưa nhập tài khoản!"
				},
				matKhau : {
					required : "Bạn chưa nhập mật khẩu!"
				},
				hoTen : "Nhập họ tên/tổ chức!",
				matKhauLai : {
					required : "Chưa xác minh mật khẩu!"
				},
				diaChi : {
					required : "Nhập địa chỉ liên hệ!"
				},
				dienThoai : {
					required : "Nhập điện thoại liên hệ!"
				},
				email : {
					required : "Nhập email liên hệ!",
					email : "Không đúng định dạng email!"
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
		$("#f-dangnhap").validate({
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
					required : "Bạn chưa nhập tài khoản!"
				},
				matKhau : {
					required : "Bạn chưa nhập mật khẩu!"
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>
</html>
