<%@page import="model.dao.FormatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>

<script src="../js/sorttable.js" type="text/javascript"></script>
<script src="../js/menuPage.js" type="text/javascript"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="js/loadlocaltion.js" type="text/javascript"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDanVTriVyYbvbkp7c8RPD7O1SOuKo8aK4&libraries=places&callback=initAutocomplete" async defer></script>

<title>Admin-Quản lý thành viên quản trị</title>
<style type="text/css">
	.pac-container {
		z-index: 9999;
	}
</style>
</head>
<body onload="getLocation();">
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-user-secret"></i> Ban quản trị hệ thống</label>
	<button class="btn btn-success btn-xs" id="btn-them" onclick="ThemMoi();" ><i class="fa fa-plus-square"></i> Thêm mới</button>
	<div id="div-content">
		<form class="f-timkiem" action="danh-muc.action" method="get">
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
						<th class="ngaygio">Quyền hạn</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="list">
					<tr>
						<td> <s:property value="idTaiKhoan"/> </td>
						<td> <s:property value="diaChi"/> </td>
						<td class="td-10"> <s:property value="dienThoai"/> </td>
						<td class="td-10"> <s:property value="tinhTrang"/> </td>
						<td class="td-10">
							<button type="button" class="btn btn-primary btn-xs"
								onclick="CapNhat('${ idTaiKhoan }');">
								Sửa</button>
							<button type="button" class="btn btn-primary btn-xs"
								onclick="XoaTaiKhoan('${ idTaiKhoan }');">
								Xóa</button>
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
		<!-- dialog cập nhật quan trị -->
		<div class="modal fade" id="capnhat-dm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; margin-top: 10%;"></div>
		<!-- dialog thêm quản trị -->
		<div class="modal fade" id="them-dm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; margin-top: 5%;">
			<div class="modal-dialog">
				 <div class="modal-content" style=" width: 80%; margin-left: auto; margin-right: auto;">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel">Thêm mới quản trị viên</h4>
				      </div>
				      <div class="modal-body">
				          <form action="them-quantrivien.action" method="post" id="fthem">
				          		<label>Tài khoản</label>
				          		<input type="text" name="idTaiKhoan" class="form-control" placeholder="Tài khoản">
				          		<br><label>Cá nhân/Tổ chức</label>
				          		<input type="text" name="hoTen" class="form-control" placeholder="Xưng danh đầy đủ">
				          		<br><label>Địa chỉ</label>
				          		<input type="text" id="diaChi" name="diaChi" class="form-control" placeholder="Địa chỉ liên hệ">
				          		<s:hidden id="location" name="location"></s:hidden>
				          		<br>
				          		<s:div cssClass="div-col-100">
									<s:div cssClass="div-col-50">
				          				<label>Điện thoại</label>
					          			<input type="text" name="dienThoai" class="form-control" placeholder="Điện thoại">
					          		</s:div>
					          		<s:div cssClass="div-col-50">
					          			<label>Email</label>
					          			<input type="text" name="email" class="form-control" placeholder="Email">
				          			</s:div>
				          			<s:div cssClass="clear"></s:div>
				          		</s:div>
				          		<br>
				          		<button data-dismiss="modal" class="btn btn-default btn-sm" style="float: right;">Hủy bỏ</button>
				          		<button type="submit" name="btnSubmit" value="Thêm mới" class="btn btn-primary btn-sm" style="float: right; margin-right: 10px">Thêm mới</button>
				          		<div style="clear: both;"></div>
				          </form>
				      </div>
				 </div>
			</div>
		</div>	
		<i class="fa fa-spinner fa-3x fa-pulse" id="while-load" style="margin-left: 50%; margin-top: -10%;"></i>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$("#fthem").validate({
		rules : {
			idTaiKhoan : {
				required : true
			},
			hoTen : {
				required : true
			},
			diaChi : {
				required : true
			},
			dienThoai : {
				required : true
			},
			email : {
				required : true,
				email : true
			}
		},
		messages : {
			idTaiKhoan : {
				required : "Bạn chưa nhập dữ liệu!"
			},
			hoTen : {
				required : "Bạn chưa nhập dữ liệu!"
			},
			diaChi : {
				required : "Bạn chưa nhập dữ liệu!"
			},
			dienThoai : {
				required : "Bạn chưa nhập dữ liệu!"
			},
			email : {
				required : "Bạn chưa nhập dữ liệu!",
				email : "Không phải định dạng email!"
			}
		},
		submitHandler : function(form) {
			var postData = $(form).serializeArray();
			var formURL = $(form).attr("action");
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				beforeSubmit : function() {
					$("#loadWhile").attr("style", "display: inherit;");
				},
				success : function(data) {
					$('#them-dm').modal('hide');
					$("#loadWhile").attr("style", "display: none;");
					if (data.indexOf("thất bại") > -1) {
						$("#loiDangKy").attr("style", "display: inherit;");
					} else {
						window.location.reload(true);
						$("#loiDangKy").attr("style", "display: none;");
						alert("Đăng ký dịch vụ thành công.");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#loiDangKy").attr("style", "display: none;");
					$('#them-dm').modal('hide');
					alert("Lỗi");
				}
			});
		}
	});
	$("#fcapnhat").validate({
		rules : {
			hoTen : {
				required : true
			},
			diaChi : {
				required : true
			},
			dienThoai : {
				required : true
			},
			email : {
				required : true,
				email : true
			}
		},
		messages : {
			hoTen : {
				required : "Bạn chưa nhập dữ liệu!"
			},
			diaChi : {
				required : "Bạn chưa nhập dữ liệu!"
			},
			dienThoai : {
				required : "Bạn chưa nhập dữ liệu!"
			},
			email : {
				required : "Bạn chưa nhập dữ liệu!",
				email : "Không phải định dạng email!"
			}
		},
		submitHandler : function(form) {
			var postData = $(form).serializeArray();
			var formURL = $(form).attr("action");
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				beforeSubmit : function() {
					$("#loadWhile").attr("style", "display: inherit;");
				},
				success : function(data) {
					if (data.indexOf("thất bại") > -1) {
						$("#loiDangKy").attr("style", "display: inherit;");
					} else {
						window.location.reload(true);
						$("#loiDangKy").attr("style", "display: none;");
						$('#them-dm').modal('hide');
						alert("Đăng ký dịch vụ thành công.");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#loiDangKy").attr("style", "display: none;");
					$('#them-dm').modal('hide');
					alert("Lỗi");
				}
			});
		}
	});
});
</script>
<script src="js/thanhvienhethong.js" type="text/javascript"></script>
</html>