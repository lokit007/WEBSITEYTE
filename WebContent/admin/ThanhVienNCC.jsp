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
	    font-size: 1em;
	}
</style>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-user-md"></i> Nhà cung cấp dịch vụ</label>
	<button class="btn btn-success btn-xs" id="btn-them" onclick="ThemMoi();" ><i class="fa fa-plus-square"></i> Thêm mới</button>
	<div id="div-content">
		<form class="f-timkiem" action="thanh-vien-nhacungcap.action" method="get">
				<label for="txtFind">Tìm kiếm : </label>
				<input type="text" id="txtFind" name="txtFind" placeholder="Nội dung cần tìm ..." value="<%= request.getParameter("txtFind")==null?"":FormatData.toUTF8(request.getParameter("txtFind")) %>">
				<button>Lọc dữ liệu</button>
		</form>
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
		<s:div>
			<table class="table sortable table-hover table-responsive">
				<thead>
					<tr>
						<th class="tieude">Tài khoản</th>
						<th>Địa chỉ</th>
						<th class="ngaygio">Điện thoại</th>
						<th class="ngaygio">Đánh giá</th>
						<th class="ngaygio">Tình trạng</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="listNCC">
					<tr>
						<td> <s:property value="taiKhoan.idTaiKhoan"/> </td>
						<td> <s:property value="taiKhoan.diaChi"/> </td>
						<td class="td-10"> <s:property value="taiKhoan.dienThoai"/> </td>
						<td class="td-10">
							<input id="rating-input" value="${ danhGia}"
									type="number" class="rating" data-size="xs"
									data-rtl="false" data-default-caption="{rating} Sao"
									data-star-captions="{}">
						</td>
						<td class="td-10"> <s:property value="taiKhoan.tinhTrang"/> </td>
						<td class="td-20">
							<s:if test="taiKhoan.tinhTrang=='TK mới'">
								<a href="#" title="Báo cáo vi phạm" onclick="CapNhatNguoiDung('${ taiKhoan.idTaiKhoan }', 'Vi phạm');" class="btn-check btn-thaotac"><i class="fa fa-exclamation-triangle"></i></a>
							</s:if>
							<s:elseif test="taiKhoan.tinhTrang=='Vi phạm'">
								<a href="#" title="Khôi phục tài khoản" onclick="CapNhatNguoiDung('${ taiKhoan.idTaiKhoan }', 'TK mới');" class="btn-check btn-thaotac"><i class="fa fa-reply"></i></a>
							</s:elseif>										
							<a href="chi-tiet-nhacungcap.action?idNhaCungCap=${ idNhaCungCap }" title="Chi tiết nhà cung cấp" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
							<a href="thong-tin-nhacungcap.action?idNhaCungCap=${ idNhaCungCap }" title="Chỉnh sửa" class="btn-update btn-thaotac"><i class="fa fa-pencil-square-o"></i></a>
							<a href="#" onclick="XoaNhaCungCap('${ idNhaCungCap }');" title="Xóa nhà cung cấp" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</s:div>
		<div id="menuPage">
			<s:property value="menu" escape="false"/>
		</div>
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
		window.location.href = "them-nhacungcap.action";
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
	function XoaNhaCungCap(id) {
		if (confirm("Bạn muốn xoá nhà cung cấp " + id + " này không???") == true) {
			$.ajax({
				url : "xoa-nhacungcap.action",
				type : "post",
				data : {
					idNhaCungCap : id
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