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
</head>
<body>
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
				          <form action="capnhat-quantri.action" method="post" id="fcapnhat">
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
				        <h4 class="modal-title" id="myModalLabel">Thêm mới quản trị viên</h4>
				      </div>
				      <div class="modal-body">
				          <form action="them-quantrivien.action" method="post" id="fthem">
				          		<label>Tài khoản</label>
				          		<input type="text" name="idTaiKhoan" class="form-control" placeholder="Tài khoản">
				          		<br><label>Cá nhân/Tổ chức</label>
				          		<input type="text" name="hoTen" class="form-control" placeholder="Xưng danh đầy đủ">
				          		<br><label>Địa chỉ</label>
				          		<input type="text" name="diaChi" class="form-control" placeholder="Địa chỉ liên hệ">
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
	function ThemMoi() {
		$("#them-dm").modal("show");
	}
	function CapNhat(id) {
		$.ajax({
			url : "show-quantri.action",
			type : "post",
			data : {
				idTaiKhoan : id
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
	function XoaTaiKhoan(id) {
		if (confirm("Bạn muốn xoá tàik khoản " + id + " này không???") == true) {
			$.ajax({
				url : "xoa-quantrivien.action",
				type : "post",
				data : {
					idTaiKhoan : id
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
	
	$(document).ready(function(){
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
			        type: "POST",
			        data : postData,
			        beforeSubmit : function (){
			        	$("#loadWhile").attr("style", "display: inherit;");
			        },
			      	success:function(data, textStatus, jqXHR) 
			        {
			            if(data.indexOf("thất bại")>-1){
			            	$("#loiDangKy").attr("style", "display: inherit;");
				       } else {
				    	    window.location.reload(true);
			            	$("#loiDangKy").attr("style", "display: none;");
			            	$('#them-dm').modal('hide');
					      	alert("Đăng ký dịch vụ thành công.");
			            }
			        },
			        error: function(jqXHR, textStatus, errorThrown) 
			        {
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
			        type: "POST",
			        data : postData,
			        beforeSubmit : function (){
			        	$("#loadWhile").attr("style", "display: inherit;");
			        },
			      	success:function(data, textStatus, jqXHR) 
			        {
			            if(data.indexOf("thất bại")>-1){
			            	$("#loiDangKy").attr("style", "display: inherit;");
				       } else {
				    	    window.location.reload(true);
			            	$("#loiDangKy").attr("style", "display: none;");
			            	$('#them-dm').modal('hide');
					      	alert("Đăng ký dịch vụ thành công.");
			            }
			        },
			        error: function(jqXHR, textStatus, errorThrown) 
			        {
			        	$("#loiDangKy").attr("style", "display: none;");
				       	$('#them-dm').modal('hide');
			            alert("Lỗi");    
			        }
			   });
			}
		});
	});
</script>
</html>