<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<title>Admin - Đăng nhập</title>
</head>
<body>
	<form action="login.action" method="post" id="f-dangnhap" >
		<h3>Đăng nhập tài khoản</h3>
		<br><s:label value="Tài khoản : " for="taiKhoan"></s:label>
		<s:textfield name="taiKhoan" cssClass="form-control"></s:textfield>
	  	<br><s:label value="Mật khẩu : " for="matKhau"></s:label>
		<s:password name="matKhau" cssClass="form-control"></s:password>
		<br>
		<input type="submit" value="Đăng nhập" style="width: 100%;"/>
	</form>
</body>
</html>