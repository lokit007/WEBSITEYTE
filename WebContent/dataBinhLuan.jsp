<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:div cssClass="list-binhluan-item">
	<s:label value="%{ binhLuan.taiKhoan.hoTen}"></s:label>
	<br> <s:property value="binhLuan.noiDung" />
	<br>
	<button type="button" class="btn btn-link" onclick="ShowTraLoi('${ binhLuan.id}', 'TRALOI', this);">Trả lời</button>
	<span><s:property value="binhLuan.ngayDang" /> </span>
</s:div>
