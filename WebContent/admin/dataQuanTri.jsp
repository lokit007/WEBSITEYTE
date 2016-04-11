<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:div cssClass="modal-dialog">
	<s:div cssClass="modal-content"
		cssStyle="width: 80%; margin-left: auto; margin-right: auto;">
		<s:div cssClass="modal-header">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">×</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">
				Cập nhật thông tin <s:label id="id-dmx" value="%{ taiKhoan.idTaiKhoan}"></s:label>
			</h4>
		</s:div>
		<s:div cssClass="modal-body">
			<form action="capnhat-quantri.action" method="post" id="fcapnhat">
				<s:hidden name="idTaiKhoan" id="id-dm" value="%{ taiKhoan.idTaiKhoan}"></s:hidden>
				<label>Cá nhân/Tổ chức</label>
				<input type="text" name="hoTen" value="${ taiKhoan.hoTen}" class="form-control" placeholder="Xưng danh đầy đủ">
				<br><label>Địa chỉ</label>
				<input type="text" name="diaChi" value="${ taiKhoan.diaChi}" class="form-control" placeholder="Địa chỉ liên hệ">
				<br>
				<s:div cssClass="div-col-100">
					<s:div cssClass="div-col-50">
				        <label>Điện thoại</label>
					    <input type="text" name="dienThoai" value="${ taiKhoan.dienThoai}" class="form-control" placeholder="Điện thoại">
					</s:div>
					<s:div cssClass="div-col-50">
					    <label>Email</label>
					    <input type="text" name="email" value="${ taiKhoan.email}" class="form-control" placeholder="Email">
				   	</s:div>
				    <s:div cssClass="clear"></s:div>
				</s:div>
				<hr>
				<button data-dismiss="modal" class="btn btn-default btn-sm"
					style="float: right;">Hủy bỏ</button>
				<button type="submit" name="btnSubmit" value="Cập nhật"
					class="btn btn-primary btn-sm"
					style="float: right; margin-right: 10px">Cập nhật</button>
				<div style="clear: both;"></div>
			</form>
		</s:div>
	</s:div>
</s:div>
