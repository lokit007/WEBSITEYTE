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
				Cập nhật danh mục <s:label id="id-dmx" value="%{ danhMuc.idDanhMuc}"></s:label>
			</h4>
		</s:div>
		<s:div cssClass="modal-body">
			<form action="capnhat-danhmuc.action" method="post" id="fcapnhat">
				<s:hidden name="idDanhMuc" id="id-dm" value="%{ danhMuc.idDanhMuc}"></s:hidden>
				<label>Tên danh mục</label>
				<s:textfield name="tenDanhMuc" cssClass="form-control" value="%{ danhMuc.tenDanhMuc}"></s:textfield>	
				<br>
				<label>Hiển thị trên : </label>
				<s:checkboxlist name="hienThi" id="hienThi"
					list="{'Dịch vụ', 'Tư vấn', 'Chia sẻ'}"
					value="hienThi"></s:checkboxlist>
				<br> <i>Chú ý : Tên danh mục y tế phải có ý nghĩa thực
					tiễn, ngắn gọn đầy đủ ý nghĩa trong y học. Tên thường có độ dài từ
					15 đến 40 ký tự.</i>
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
