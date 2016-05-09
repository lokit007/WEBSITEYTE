<%@page import="model.dao.FormatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<link rel="stylesheet" href="../css/star-rating.min.css">
<script src="../js/sorttable.js" type="text/javascript"></script>
<script src="../js/menuPage.js" type="text/javascript"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../js/star-rating.min.js" type="text/javascript"></script>
<script src="js/xulynghiepvu.js" type="text/javascript"></script>

<title>Admin - Quản lý danh mục y tế</title>
<style type="text/css">
	.rating-xs {
	    font-size: 1.1em;
	}
	th.lable-col {
	    width: 130px;
	}
	.bg-danger, .bg-success {
		margin-left: -5px;
    	padding: 10px;
	}
</style>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-user-md"></i> Nhà cung cấp dịch vụ</label>
	<button class="btn btn-default btn-xs" id="btn-them" onclick="history.go(-1);" ><i class="fa fa-undo"></i> Quay lại</button>
	<div id="div-content">
		<s:if test="hasActionErrors()">
			<s:div cssClass="bg-danger">
				<s:actionerror/>
			</s:div>
		</s:if>
		<s:if test="hasActionMessage()"> 
			<s:div cssClass="bg-success">
				<s:actionmessage/>
			</s:div>
		</s:if>
		<s:if test="nhaCungCap!=null">
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
					<button id="btn-dangky" class="btn btn-sm btn-warning" onclick="CapNhatNguoiDung('${ nhaCungCap.taiKhoan.idTaiKhoan }', 'Vi phạm');">
						<i class="fa fa-exclamation-triangle"></i>
						Báo cáo vi phạm</button>
				</s:if>
				<s:elseif test="nhaCungCap.taiKhoan.tinhTrang=='Vi phạm'">
					<button id="btn-dangky" class="btn btn-sm btn-primary" onclick="CapNhatNguoiDung('${ nhaCungCap.taiKhoan.idTaiKhoan }', 'TK mới');">
						<i class="fa fa-check-circle-o"></i>
						Đảm bảo yêu cầu</button>
				</s:elseif>
				<button id="btn-info" class="btn btn-sm btn-info" onclick="ChuyenHuong('thong-tin-nhacungcap.action?idNhaCungCap=${ nhaCungCap.idNhaCungCap}');">
					<i class="fa fa-pencil-square-o"></i>
					Chỉnh sửa thông tin</button>
				<button id="btn-info" class="btn btn-sm btn-danger" onclick="XoaNhaCungCap('${ nhaCungCap.idNhaCungCap}');">
					<i class="fa fa-times"></i>
					Xóa nhà cung cấp</button>
			</s:div>
		</fieldset>
		<hr>
		<s:property value="nhaCungCap.gioiThieu" escape="false"/>
		</s:if>
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