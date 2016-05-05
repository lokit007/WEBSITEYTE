<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<link rel="stylesheet" href="../css/datepicker.css">
<script type="text/javascript" src="../js/jquery.MultiFile.js"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="../js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/loadlocaltion.js" type="text/javascript"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDanVTriVyYbvbkp7c8RPD7O1SOuKo8aK4&libraries=places&callback=initAutocomplete" async defer></script>

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
<title>Đăng ký phát hành dịch vụ</title>
</head>
<body onload="getLocation();">
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-user-md"></i> Thêm mới nhà cung cấp</label>
	<button class="btn btn-default btn-xs" id="btn-them" onclick="history.go(-1);" ><i class="fa fa-undo"></i> Quay lại</button>
	<s:div id="div-content">
			<!-- bắt đầu nội dung hiển thị -->
			<s:if test="hasActionErrors()">
				<s:div id="showError">
			    	<s:actionerror/>
			    	<button class="btn btn-danger btn-xs" onclick="XoaPhanTu();" style="float: right; margin-top: 5px;">Ok</button>
			    </s:div>
			</s:if>
			<s:div cssClass="div-content-home">
				<form action="them-nhacungcap.action" enctype="multipart/form-data"
					method="post" id="formThemNCC">
					<br>
					<s:label value="Tài khoản" ></s:label><span class="sp-quantrong"> * </span>
					<s:textfield name="taiKhoan" cssClass="form-control"></s:textfield>
					<br>
					<s:label value="Tổ chức/Cá nhân"></s:label><span class="sp-quantrong"> * </span>
					<s:textfield name="hoTen" cssClass="form-control"></s:textfield>
					<br>
					<s:label value="Địa chỉ"></s:label><span class="sp-quantrong"> * </span>
					<s:textfield id="diaChi" name="diaChi" cssClass="form-control"></s:textfield>
					<s:hidden id="location" name="location"></s:hidden>
					<br>
					<s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
							<s:label value="Điện thoại"></s:label><span class="sp-quantrong"> * </span>
							<s:textfield name="dienThoai" onchange="ChanDienThoai(this);" cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="div-col-50">
							<s:label value="Email"></s:label><span class="sp-quantrong"> * </span>
							<s:textfield name="email" onchange="ChanEmail(this);" cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</s:div>
					<br>
					<s:label value="Giới thiệu nhà cung cấp"></s:label><span class="sp-quantrong"> * </span>
					<s:textarea name="gioiThieu" cssClass="ckeditor"></s:textarea>
					<br>
					<s:label value="Chứng chỉ hành nghề"></s:label><span class="sp-quantrong"> * </span>
					<s:textfield name="chungChi" cssClass="form-control"></s:textfield>
					<br>
					<s:label value="Ảnh chụp 2 mặt chứng chỉ hành nghề"></s:label><span class="sp-quantrong"> * </span>
					<s:file name="userImage" cssClass="multi with-preview"></s:file>
					<br>
					<s:checkbox id="check" name="check" onclick="Xuly(1);"></s:checkbox><label>Đăng ký hổ trợ online cho bệnh nhân</label>
					<fieldset id="khung-hotro" style="display: none;">
						<legend>Đăng ký hổ trợ online</legend>
						<s:div cssClass="div-col-100">
							<s:div cssClass="div-col-50">
								<s:label value="Thời gian hổ trợ"></s:label><span class="sp-quantrong"> * </span>
								<s:select name="thoiGian" list="{ 'Trong giờ hành chính', 'Ngoài giờ hành chính', 'Thứ 7, chủ nhật hằng tuần', 'Liên hệ mọi lúc'}"
									 headerKey="" headerValue="--- Chọn Thời Gian Phù Hợp ---" cssClass="form-control" >
								</s:select>
							</s:div>
							<s:div cssClass="div-col-50">
								<s:label value="Danh mục tư vấn"></s:label><span class="sp-quantrong"> * </span>
								<s:select name="danhMuc" list="{ 1, 2, 3}"
									 headerKey="" headerValue="--- Chọn Danh Mục ---" cssClass="form-control" >
								</s:select>
							</s:div>
							<s:div cssClass="clear"></s:div>
						</s:div>
						<s:div cssClass="div-col-100">
							<s:div cssClass="div-col-50">
								<s:label value="Điện thoại"></s:label><span class="sp-quantrong"> * </span>
								<s:textfield id="dienThoaiLH" name="dienThoaiLH" cssClass="form-control"></s:textfield>
							</s:div>
							<s:div cssClass="div-col-50">
								<s:label value="Email"></s:label><span class="sp-quantrong"> * </span>
								<s:textfield id="emailLH" name="emailLH" cssClass="form-control"></s:textfield>
							</s:div>
							<s:div cssClass="clear"></s:div>
						</s:div>
						<s:div cssClass="div-col-100">
							<s:div cssClass="div-col-50">
								<s:label value="Nick yahoo chat"></s:label>
								<s:textfield name="nickYahoo" cssClass="form-control"></s:textfield>
							</s:div>
							<s:div cssClass="div-col-50">
								<s:label value="Nick skype chat"></s:label>
								<s:textfield name="nickSkype" cssClass="form-control"></s:textfield>
							</s:div>
							<s:div cssClass="clear"></s:div>
						</s:div>
					</fieldset>
					<br><br>
					<s:submit value="Thêm nhà cung cấp mới"
						cssClass="btn btn-primary form-control"></s:submit>
					<br>
				</form>
			</s:div>
			<!-- kết thúc content -->
		<s:div cssClass="clear"></s:div>
	</s:div>
</body>
<script type="text/javascript">
	function Xuly(e) {
		if(e==1){
			$("#khung-hotro").attr('style', 'display: inherit;');
			$('#check').attr('onclick', 'Xuly(0);');
		} else {
			$("#khung-hotro").attr('style', 'display: none;');
			$('#check').attr('onclick', 'Xuly(1);');
		}
	};
	function ChanDienThoai(e) {
		$("#dienThoaiLH").val($(e).val());
	};
	function ChanEmail(e) {
		$("#emailLH").val($(e).val());
	};
	$(document).ready(function() {
		$("#formThemNCC").validate({
			rules : {
				taiKhoan : {
					required : true
				},
				hoTen : {
					required : true
				},
				diaChi : {
					required : true	
				},
				email : {
					required : true,
					email : 5
				},
				dienThoai : {
					required : true
				},
				gioiThieu : {
					required : true
				},
				chungChi : {
					required : true
				},
				thoiGian : {
					required : true
				},
				danhMuc : {
					required : true
				},
				emailLH : {
					required : true,
					email : 5
				},
				dienThoaiLH : {
					required : true
				}
			},
			messages : {
				taiKhoan : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				},
				hoTen : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				},
				diaChi : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"	
				},
				email : {
					required : "Bạn chưa nhập dữ liệu cho trường này!",
					email : "Không phải định dạng email!"
				},
				dienThoai : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				},
				gioiThieu : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				},
				chungChi : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				},
				thoiGian : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				},
				danhMuc : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				},
				emailLH : {
					required : "Bạn chưa nhập dữ liệu cho trường này!",
					email : "Không phải định dạng email!"
				},
				dienThoaiLH : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
	function XoaPhanTu(){
		$("#showError").remove();
	}
</script>
</html>
