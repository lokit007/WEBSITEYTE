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
<title>Admin-Cập nhật dịch vụ</title>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-heartbeat"></i> Cập nhật dịch vụ y tế</label>
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
				<form action="cap-nhat-dich-vu.action" enctype="multipart/form-data"
					method="post" id="formDichVu">
					<br>
					<s:hidden name="idDichVu" value="%{ dichVu.idDichVu}"></s:hidden>
					<s:label value="Tên dịch vụ" for="tenDichVu"></s:label>
					<s:textfield name="tenDichVu" cssClass="form-control" value="%{ dichVu.baiViet.tenBaiViet}"></s:textfield>
					<br>
					<s:label value="Danh mục" for="danhMuc"></s:label>
					<s:select name="danhMuc" list="list" listValue="tenDanhMuc"
							listKey="idDanhMuc" cssClass="form-control" headerKey="-1"
							headerValue="--- Chọn Danh Mục ---" 
							value="dichVu.baiViet.danhMuc.idDanhMuc">
					</s:select>
					<br>
					<s:label value="Mô tả" for="moTa"></s:label>
					<s:textarea name="moTa" cssClass="form-control" value="%{ dichVu.baiViet.moTa}"></s:textarea>
					<br>
					<s:label value="Nội dung" for="noiDung"></s:label>
					<s:textarea name="noiDung" cssClass="ckeditor" value="%{ dichVu.baiViet.noiDung}"></s:textarea>
					<br>
					<s:label value="Hình ảnh" for="hinhAnh"></s:label>
					<s:file name="userImage" cssClass="multi with-preview"></s:file>
					<br>
					<s:label value="Nhà cung cấp" for="nhaCungCap"></s:label>
					<%-- <s:textfield name="nhaCungCap" cssClass="form-control" value="%{ dichVu.baiViet.tenTacGia}"></s:textfield> --%>
					<s:select list="listNCC" name="nhaCungCap" cssClass="form-control"
						listKey="idNhaCungCap" listValue="taiKhoan.hoTen"
						headerKey="" headerValue="--- Chọn nhà cung cấp dịch vụ ---"
						value="dichVu.idNhaCungCap" >
					</s:select>
					<br>
					<s:label value="Địa điểm triển khai dịch vụ" for="diaDiem"></s:label>
					<s:textfield id="diaChi" name="diaDiem" cssClass="form-control" value="%{ dichVu.diaChiTrienKhai}"></s:textfield>
					<br>
					<s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
							<s:label value="Ngày bắt đầu" for="ngayBatDau"></s:label>
							<s:textfield name="ngayBatDau" id="ngayBatDau" value="%{ dichVu.ngayBatDau}"
								cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="div-col-50">
							<s:label value="Ngày kết thúc" for="ngayKetThuc"></s:label>
							<s:textfield name="ngayKetThuc" id="ngayKetThuc" value="%{ dichVu.ngayKetThuc}"
								cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</s:div>
					<br>
					<br>
					<s:submit value="Cập nhật dịch vụ"
						cssClass="btn btn-primary form-control"></s:submit>
					<br>
				</form>
			</s:div>
			<!-- kết thúc content -->
		<s:div cssClass="clear"></s:div>
	</s:div>
</body>
</html>
