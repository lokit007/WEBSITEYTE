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
<script src="../js/jquery.MultiFile.js" type="text/javascript"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="../js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/xulynghiepvu.js" type="text/javascript"></script>
<script src="js/loadlocaltion.js" type="text/javascript"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDanVTriVyYbvbkp7c8RPD7O1SOuKo8aK4&libraries=places&callback=initAutocomplete" async defer></script>
<script src="js/validateform.js" type="text/javascript"></script>
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
<title>Admin-Đăng nhu cầu y tế</title>
</head>
<body onload="getLocation();">
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-compass"></i> Đăng nhu cầu y tế</label>
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
				<form action="dang-nhu-cau.action" enctype="multipart/form-data"
					method="post" id="formNhuCau">
					<br>
					<s:label value="Tiêu đề nhu cầu"></s:label>
					<s:textfield name="tenNhuCau" cssClass="form-control"></s:textfield>
					<br>
					<s:label value="Lĩnh vực yêu cầu"></s:label>
					<s:select name="danhMuc" list="list" listValue="tenDanhMuc"
								listKey="idDanhMuc" cssClass="form-control" headerKey=""
								headerValue="--- Chọn lĩnh vực yêu cầu ---">
					</s:select>
					<br>
					<s:label value="Chi tiết nhu cầu"></s:label>
					<s:textarea name="moTa" rows="10" cssClass="form-control"></s:textarea>
					<br>
					<s:label value="Hình ảnh"></s:label>
					<s:file name="userImage" cssClass="multi with-preview"></s:file>
					<br>
					<s:label value="Cá nhân/tổ chức yêu cầu"></s:label>
					<s:textfield name="nhaCungCap" cssClass="form-control" value="%{ #session.admin.taiKhoan.hoTen }"></s:textfield>
					<br>
					<s:label value="Địa điểm mong muốn"></s:label>
					<s:textfield id="diaChi" name="diaDiem" cssClass="form-control"></s:textfield>
					<br>
					<s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
							<s:label value="Điện thoại"></s:label>
							<s:textfield name="dienThoai" maxlength="11" cssClass="form-control" value="%{ #session.admin.taiKhoan.dienThoai }"></s:textfield>
							<br>
							<s:label value="Ngày bắt đầu"></s:label>
							<s:textfield name="ngayBatDau" id="ngayBatDau"
								cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="div-col-50">
							<s:label value="Email"></s:label>
							<s:textfield name="email" cssClass="form-control" value="%{ #session.admin.taiKhoan.email }"></s:textfield>
							<br>
							<s:label value="Ngày kết thúc"></s:label>
							<s:textfield name="ngayKetThuc" id="ngayKetThuc"
								cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</s:div>
					<br>
					<br>
					<s:submit value="Đăng nhu cầu miễn phí"
						cssClass="btn btn-primary form-control"></s:submit>
					<br>
				</form>
			</s:div>
			<!-- kết thúc content -->
		<s:div cssClass="clear"></s:div>
	</s:div>
</body>
</html>
