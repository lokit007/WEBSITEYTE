<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<link rel="stylesheet" href="css/datepicker.css">
<script src="js/jquery.MultiFile.js" type="text/javascript"></script>
<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/validate-form.js" type="text/javascript"></script>

<title>Đăng bài viết chia sẻ</title>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:if test="hasActionErrors()">
				<s:div id="showError">
			    	<s:actionerror/>
			    	<button class="btn btn-danger btn-xs" onclick="XoaPhanTu('showError');" style="float: right; margin-top: 5px;">Ok</button>
			    </s:div>
			</s:if>
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Đăng chia sẻ mới 
					</s:a>
				</s:div>
				<form action="dang-chia-se.action" enctype="multipart/form-data"
					method="post" id="formChiaSe">
					<br>
					<s:label value="Tiêu đề bài viết"></s:label>
					<s:textfield name="tenBaiViet" cssClass="form-control"></s:textfield>
					<br>
					<s:label value="Phân loại bài viết"></s:label>
					<s:select name="danhMuc" list="list" listValue="tenDanhMuc"
								listKey="idDanhMuc" cssClass="form-control" headerKey=""
								headerValue="--- Chọn Danh Mục Phân Loại ---">
					</s:select>
					<br>
					<s:label value="Mô ngắn bài viết"></s:label>
					<s:textarea name="moTa" cssClass="form-control"></s:textarea>
					<br>
					<s:label value="Nội dung chính bài viết chia sẻ"></s:label>
					<s:textarea name="noiDung" cssClass="ckeditor"></s:textarea>
					<br>
					<s:label value="Hình ảnh minh họa"></s:label>
					<s:file name="userImage" cssClass="multi with-preview"></s:file>
					<br>
					<s:label value="Tác giả"></s:label>
					<s:textfield name="tacGia" cssClass="form-control" value="%{ #session.user.hoTen}"></s:textfield>
					<br>
					<br>
					<s:submit value="Đăng bài viết chia sẻ"
						cssClass="btn btn-primary form-control"></s:submit>
					<br>
				</form>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#formChiaSe").validate({
			rules : {
				tenBaiViet : {
					required : true
				},
				moTa : {
					required : true
				},
				danhMuc : {
					required : true	
				},
				noiDung : {
					required : true
				}
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
				noiDung : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
	function XoaPhanTu(idelement){
		$("#"+idelement).remove();
	}
</script>
</html>
