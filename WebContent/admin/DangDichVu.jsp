<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<script type="text/javascript" src="../js/jquery.MultiFile.js"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="../css/datepicker.css">
<script src="../js/bootstrap-datepicker.js"></script>
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
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-heartbeat"></i> Thêm mới dịch vụ y tế</label>
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
				<form action="dang-dich-vu.action" enctype="multipart/form-data"
					method="post" id="formDichVu">
					<br>
					<s:label value="Tên dịch vụ" for="tenDichVu"></s:label>
					<s:textfield name="tenDichVu" cssClass="form-control"></s:textfield>
					<br>
					<s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
							<s:label value="Danh mục" for="danhMuc"></s:label>
							<s:select name="danhMuc" list="list" listValue="tenDanhMuc"
								listKey="idDanhMuc" cssClass="form-control" headerKey="-1"
								headerValue="--- Chọn Danh Mục ---">
							</s:select>
						</s:div>
						<s:div cssClass="div-col-50">
							<s:label value="Loại hình dịch vụ" for="loaiHinh"></s:label>
							<s:select name="loaiHinh" list="{ 'Từ thiện', 'Dịch vụ công', 'Dịch vụ tư'}"
								 headerKey="" headerValue="--- Chọn loại hình ---" cssClass="form-control" >
							</s:select>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</s:div>
					<br>
					<s:label value="Mô tả" for="moTa"></s:label>
					<s:textarea name="moTa" cssClass="form-control"></s:textarea>
					<br>
					<s:label value="Nội dung" for="noiDung"></s:label>
					<s:textarea name="noiDung" cssClass="ckeditor"></s:textarea>
					<br>
					<s:label value="Hình ảnh" for="hinhAnh"></s:label>
					<s:file name="userImage" cssClass="multi with-preview"></s:file>
					<br>
					<s:label value="Nhà cung cấp" for="nhaCungCap"></s:label>
					<s:textfield name="nhaCungCap" cssClass="form-control"></s:textfield>
					<br>
					<s:label value="Địa điểm triển khai dịch vụ" for="diaDiem"></s:label>
					<s:textfield name="diaDiem" cssClass="form-control"></s:textfield>
					<br>
					<s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
							<s:label value="Điện thoại" for="dienThoai"></s:label>
							<s:textfield name="dienThoai" cssClass="form-control"></s:textfield>
							<br>
							<s:label value="Ngày bắt đầu" for="ngayBatDau"></s:label>
							<s:textfield name="ngayBatDau" id="ngayBatDau"
								cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="div-col-50">
							<s:label value="Email" for="email"></s:label>
							<s:textfield name="email" cssClass="form-control"></s:textfield>
							<br>
							<s:label value="Ngày kết thúc" for="ngayKetThuc"></s:label>
							<s:textfield name="ngayKetThuc" id="ngayKetThuc"
								cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</s:div>
					<br>
					<br>
					<s:submit value="Đăng dịch vụ miễn phí"
						cssClass="btn btn-primary form-control"></s:submit>
					<br>
				</form>
			</s:div>
			<!-- kết thúc content -->
		<s:div cssClass="clear"></s:div>
	</s:div>
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
		$("#formDichVu").validate({
			rules : {
				tenDichVu : {
					required : true
				},
				moTa : {
					required : true
				},
				danhMuc : {
					required : true	
				},
				loaiHinh : {
					required : true
				},
				noiDung : {
					required : true
				},
				nhaCungCap : {
					required : true
				},
				diaDiem : {
					required : true
				},
				dienThoai : {
					required : true
				},
				email : {
					required : true,
					email : 5
				},
				ngayBatDau : "required",
				ngayKetThuc : "required"
			},
			messages : {
				tenDichVu : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				moTa : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				danhMuc : {
					required : "Chưa chọn danh mục dịch vụ!"	
				},
				loaiHinh : {
					required : "Chưa chọn loại hình dịch vụ!"
				},
				noiDung : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				nhaCungCap : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				diaDiem : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				dienThoai : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				email : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!",
					email : "Dữ liệu quá dài!"
				},
				ngayBatDau : "Chưa nhập đầy đủ thông tin dịch vụ!",
				ngayKetThuc : "Chưa nhập đầy đủ thông tin dịch vụ!"
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
