<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<title>Admin-Trang cá nhân</title>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-cogs"></i> Quản lý thông tin cá nhân</label>
	<div id="div-content">
		<form id="f-capnhat-thongtin" action="capnhat-canhan.action" method="post">
			<s:hidden name="idTaiKhoan" value="%{ #session.admin.taiKhoan.idTaiKhoan}"></s:hidden>
			<s:label value="Cá nhân/Tổ chức : "></s:label><span class="sp-quantrong"> * </span>
			<s:textfield name="hoTen" value="%{ #session.admin.taiKhoan.hoTen}" cssClass="form-control"></s:textfield>
			<br><s:label value="Địa chỉ : "></s:label><span class="sp-quantrong"> * </span>
			<s:textfield name="diaChi" value="%{ #session.admin.taiKhoan.diaChi}" cssClass="form-control"></s:textfield>
			<s:div cssClass="div-col-100">
				<s:div cssClass="div-col-50">
					<br><s:label value="Điện thoại liên hệ : "></s:label><span class="sp-quantrong"> * </span>
					<s:textfield name="dienThoai" value="%{ #session.admin.taiKhoan.dienThoai}" cssClass="form-control"></s:textfield>
				</s:div>
				<s:div cssClass="div-col-50">
					<br><s:label value="Email : "></s:label><span class="sp-quantrong"> * </span>
					<s:textfield name="email" value="%{ #session.admin.taiKhoan.email}" cssClass="form-control"></s:textfield>
				</s:div>
				<s:div cssClass="clear"></s:div>
			</s:div>
			<s:div cssClass="div-col-100">
				<s:div cssClass="div-col-50">
					<br><s:label value="Mật khẩu mới : "></s:label>
					<s:textfield id="matKhauMoi" name="matKhauMoi" cssClass="form-control"></s:textfield>
				</s:div>
				<s:div cssClass="div-col-50">
					<br><s:label value="Xác nhận mật khẩu mới : "></s:label>
					<s:textfield name="matKhauXacNhan" cssClass="form-control"></s:textfield>
				</s:div>
				<s:div cssClass="clear"></s:div>
			</s:div>
			<br><s:submit value="Cập nhật thông tin" cssClass="form-control btn btn-success"></s:submit>
		</form>
	</div>
</body>
</html>