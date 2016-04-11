<%@page import="model.dao.FormatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<title>Admin - Quản lý danh mục y tế</title>
<script src="../js/sorttable.js"></script>
<script src="../js/menuPage.js"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<link rel="stylesheet" href="../css/star-rating.min.css">
<script src="../js/star-rating.min.js"></script>
<style type="text/css">
	.rating-xs {
	    font-size: 1.1em;
	}
	th.lable-col {
	    width: 130px;
	}
</style>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-user-md"></i> Nhà cung cấp dịch vụ</label>
	<button class="btn btn-default btn-xs" id="btn-them" onclick="history.go(-1);" ><i class="fa fa-undo"></i> Quay lại</button>
	<div id="div-content">
		<s:if test="hasActionErrors()">
			<s:div cssClass="bg-danger" cssStyle="padding: 5px;">
				<s:actionerror/>
			</s:div>
		</s:if>
		<s:if test="hasActionMessage()"> 
			<s:div cssClass="bg-success" cssStyle="padding: 5px;">
				<s:actionmessage/>
			</s:div>
		</s:if>
		<fieldset>
			<legend>Thông tin nhà cung cấp</legend>
			<table class="table table-striped table-bordered">
				<tr>
					<th class="lable-col">Tài khoản</th>
					<td> <s:property value="nhaCungCap.taiKhoan.idTaiKhoan"/> </td>
					<th class="lable-col">Được đánh giá</th>
					<td>
						<input id="rating-input" value="${ nhaCungCap.danhGia}"
									type="number" class="rating" data-size="xs"
									data-rtl="false" data-default-caption="{rating} Sao"
									data-star-captions="{}">
					</td>
				</tr>
				<tr>
					<th class="lable-col">Địa chỉ</th>
					<td colspan="3"> <s:property value="nhaCungCap.taiKhoan.diaChi"/> </td>
				</tr>
				<tr>
					<th class="lable-col">Điện thoại</th>
					<td> <s:property value="nhaCungCap.taiKhoan.dienThoai"/> </td>
					<th class="lable-col">Email</th>
					<td> <s:property value="nhaCungCap.taiKhoan.email"/> </td>
				</tr>
				<tr>
					<th class="lable-col">Ngày tham gia</th>
					<td> <s:property value="nhaCungCap.taiKhoan.ngayThamGia"/> </td>
					<th class="lable-col">Tình trạng</th>
					<td> <s:property value="nhaCungCap.taiKhoan.tinhTrang"/> </td>
				</tr>
			</table>
			<s:div cssStyle="text-align : right;">
				<s:if test="nhaCungCap.taiKhoan.tinhTrang=='TK mới'">
					<button id="btn-dangky" class="btn btn-sm btn-warning">
						<i class="fa fa-exclamation-triangle"></i>
						Báo cáo vi phạm</button>
				</s:if>
				<s:elseif test="nhaCungCap.taiKhoan.tinhTrang=='Vi phạm'">
					<button id="btn-dangky" class="btn btn-sm btn-primary">
						<i class="fa fa-check-circle-o"></i>
						Đảm bảo yêu cầu</button>
				</s:elseif>
				<button id="btn-info" class="btn btn-sm btn-info">
					<i class="fa fa-pencil-square-o"></i>
					Chỉnh sửa thông tin</button>
				<button id="btn-info" class="btn btn-sm btn-danger">
					<i class="fa fa-times"></i>
					Xóa nhà cung cấp</button>
			</s:div>
		</fieldset>
		<hr>
		<s:property value="nhaCungCap.gioiThieu" escape="false"/>
	</div>
	<!-- dialog thông báo lỗi -->
		<div class="modal fade" id="loi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; margin-top: 15%;">
			<div class="modal-dialog">
				 <div class="modal-content" style=" width: 60%; margin-left: auto; margin-right: auto;">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel"><img alt="Lỗi" src="images/Logo.png" class="imgLoi"> Lỗi cơ sở dữ liệu !!!</h4>
				      </div>
				      <div class="modal-body">
				          Lỗi cập nhật cơ sở dữ liệu! Vui lòng thực hiện thao tác khác.
				      </div>
				      <div class="modal-footer">
				        <button data-dismiss="modal" class="btn btn-primary"> OK </button>
				      </div>
				 </div>
			</div>
		</div>
		<!-- dialog cập nhật danh mục -->
		<div class="modal fade" id="capnhat-dm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; margin-top: 10%;">
			<div class="modal-dialog">
				 <div class="modal-content" style=" width: 80%; margin-left: auto; margin-right: auto;">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel">Cập nhật danh mục <label id="id-dmx"></label></h4>
				      </div>
				      <div class="modal-body">
				          <form action="capnhat-danhmuc.action" method="post" id="fcapnhat">
				          		<input type="hidden" name="idDanhMuc" id="id-dm">
				          		<label>Tên danh mục</label>
				          		<input type="text" name="tenDanhMuc" id="tenDanhMuc" placeholder="Tên danh mục" class="form-control">
				          		<br>
				          		<label>Hiển thị trên : </label>
				          		<s:checkboxlist name="hienThi" id="hienThi" list="{'Dịch vụ', 'Tư vấn', 'Chia sẻ'}" value="{'Dịch vụ', 'Chia sẻ'}"></s:checkboxlist>
				          		<br>
				          		<i>Chú ý : Tên danh mục y tế phải có ý nghĩa thực tiễn, 
				          					ngắn gọn đầy đủ ý nghĩa trong y học.
				          					Tên thường có độ dài từ 15 đến 40 ký tự.</i>
				          		<hr>
				          		<button data-dismiss="modal" class="btn btn-default btn-sm" style="float: right;">Hủy bỏ</button>
				          		<button type="submit" name="btnSubmit" value="Cập nhật" class="btn btn-primary btn-sm" style="float: right; margin-right: 10px">Cập nhật</button>
				          		<div style="clear: both;"></div>
				          </form>
				      </div>
				 </div>
			</div>
		</div>
		<!-- dialog thêm mới danh mục -->
		<div class="modal fade" id="them-dm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; margin-top: 5%;">
			<div class="modal-dialog">
				 <div class="modal-content" style=" width: 80%; margin-left: auto; margin-right: auto;">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel">Thêm mới danh mục</h4>
				      </div>
				      <div class="modal-body">
				          <form action="them-danhmuc.action" method="post" id="fthem">
				          		<label>Tên danh mục mới</label>
				          		<input type="text" name="tenDanhMuc" class="form-control" placeholder="Tên danh mục">
				          		<br>
				          		<label>Hiển thị trên : </label>
				          		<s:checkboxlist name="hienThi" list="{'Dịch vụ', 'Tư vấn', 'Chia sẻ'}"></s:checkboxlist>
				          		<br>
				          		<i>Chú ý : Tên danh mục y tế phải có ý nghĩa thực tiễn, 
				          					ngắn gọn đầy đủ ý nghĩa trong y học.
				          					Tên thường có độ dài từ 15 đến 40 ký tự.</i>
				          		<hr>
				          		<button data-dismiss="modal" class="btn btn-default btn-sm" style="float: right;">Hủy bỏ</button>
				          		<button type="submit" name="btnSubmit" value="Thêm mới" class="btn btn-primary btn-sm" style="float: right; margin-right: 10px">Thêm mới</button>
				          		<div style="clear: both;"></div>
				          </form>
				          <i class="fa fa-spinner fa-3x fa-pulse" id="while-submit" style="display: none; margin-top: -10%;"></i>
				      </div>
				 </div>
			</div>
		</div>	
		<i class="fa fa-spinner fa-3x fa-pulse" id="while-load" style="display: ; margin-left: 50%; margin-top: -10%;"></i>
</body>
<script type="text/javascript">
	function ThemMoi() {
		$("#them-dm").modal("show");
	}
	function CapNhat(id) {
		$.ajax({
			url : "show-danhmuc.action",
			type : "post",
			data : {
				idDanhMuc : id
			},
			beforeSend : function(){
			     $("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				$("#capnhat-dm").html(result);
				$("#capnhat-dm").modal("show");
				$("#while-load").attr("style", "display: none;");
			},
			error : function(xhr, status, error) {
				$("#loi").modal("show");
				$("#while-load").attr("style", "display: none;");
			}
		});
	}
	function XoaDanhMuc(id) {
		if (confirm("Bạn muốn xoá danh mục " + id + " này không???") == true) {
			$.ajax({
				url : "xoa-danhmuc.action",
				type : "post",
				data : {
					idDanhMuc : id
				},
				beforeSend : function(){
				     $("#while-load").attr("style", "display: inline-block;");
				},
				success : function(result) {
					window.location.reload(true);
					$("#while-load").attr("style", "display: none;");
				},
				error : function(xhr, status, error) {
					$("#loi").modal("show");
					$("#while-load").attr("style", "display: none;");
				}
			});
		}
	}
	function CapNhatNguoiDung(id, e) {
		var hoi = "";
		if(e=='TK mới') hoi = "Bạn muốn khôi phục lại tài khoản ";
		else hoi = "Bạn muốn khóa tài khoản ";
		if (confirm(hoi + id + " này không???") == true) {
			$.ajax({
				url : "khoa-taikhoan.action",
				type : "post",
				data : {
					idTaiKhoan : id,
					tinhTrang : e
				},
				beforeSend : function(){
				     $("#while-load").attr("style", "display: inline-block;");
				},
				success : function(result) {
					window.location.reload(true);
					$("#while-load").attr("style", "display: none;");
				},
				error : function(xhr, status, error) {
					$("#loi").modal("show");
					$("#while-load").attr("style", "display: none;");
				}
			});
		}
	}
	
</script>
</html>