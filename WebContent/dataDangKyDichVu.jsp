<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="modal-body">
	<s:hidden id="idDangKy" name="idDangKy" value="%{ dangKyDichVu.idDangKy}"></s:hidden>
	<s:hidden id="eBenhNhan" name="emailBenhNhan" value="%{ dangKyDichVu.taiKhoan.email}"></s:hidden>
	<s:hidden id="eNhaCungCap" name="emailNhaCungCap" value="%{ #session.user.email}"></s:hidden>
	<s:label value="Cá nhân/Tổ chức : "></s:label> <s:property value="dangKyDichVu.taiKhoan.hoTen" />
	<br>
	<a href="tel://0123"><i class="fa fa-phone"></i> <s:property value="dangKyDichVu.taiKhoan.dienThoai"/> </a>
	| 
	<a href="mailto:mail"><i class="fa fa-envelope"></i> <s:property value="dangKyDichVu.taiKhoan.email" /> </a>
	<br>
	<s:textarea value="%{ dangKyDichVu.tinNhan }" cssClass="form-control" readonly="true" cssStyle="height: 80px;"></s:textarea>
	<br><s:label value="Phản hồi"></s:label>
	<s:textarea cssClass="form-control" name="tinNhan" cssStyle="height: 80px;"></s:textarea>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
	<button type="submit" class="btn btn-primary">Chấp nhận</button>
</div>