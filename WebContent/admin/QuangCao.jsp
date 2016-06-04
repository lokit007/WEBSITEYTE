<%@page import="model.dao.FormatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../ckeditor/ckeditor.js"></script>
<script src="../js/sorttable.js"></script>
<title>Admin-Quảng cáo hệ thống</title>
<style type="text/css">
.tree-node ul {
	border-left: 1px solid gray;
	margin-left: 5px;
	margin-top: -10px;
	padding: 5px 0px 0px 10px;
	list-style: none;
}

span.ic-chil {
	margin-left: -10px;
	width: 10px;
}

.tree-node, .tree-node label {
	cursor: pointer;
}
div#test {
    margin-top: 10px;
    max-height: 560px;
    overflow: auto;
}
div#test form {
    margin: 10px;
}
#cke_1_contents, #cke_2_contents, #cke_3_contents {
	height: 180px !important;
}
.i-add:hover {
    background-color: rgba(158, 158, 158, 0.59);
    padding: 3px 5px;
    border-radius: 10px;
    color: blue;
    cursor: pointer;
}
</style>
<script type="text/javascript">
	function ShowHide(e) {
		var style = $(e).next().attr("style");
		if (style == "display: none;") {
			$(e).next().attr("style", "display: inherit;");
			$(e).prev().attr("class", "fa fa-minus-square-o");
		} else {
			$(e).next().attr("style", "display: none;");
			$(e).prev().attr("class", "fa fa-plus-square-o");
		}
	}
</script>
<sx:head />
<%-- <s:head theme="ajax" debug="true" /> --%>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-cogs"></i> Quản lý quảng cáo trên hệ thống </label>
	<button class="btn btn-success btn-xs" id="btn-them" onclick="ThemMoi();" ><i class="fa fa-plus-square"></i> Thêm mới </button>
	<div id="div-content">
		<s:div id="result"></s:div>
		<s:tabbedPanel id="test" selectedTab="%{ #session.selectTab}">
			<s:div id="one" label="Quảng cáo hiển thị" theme="ajax">
  				<s:div>
  					<table class="table sortable table-hover table-responsive">
						<thead>
							<tr>
								<th>Khách hàng</th>
								<th>Địa chỉ quảng bá</th>
								<th>Vị trí hiển thị</th>
								<th>Có hiệu lực đến</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="listHT">
							<tr>
								<td><s:property value="khachHang.tenKhachHang"/></td>
								<td><s:property value="linkQuangBa"/></td>
								<td><s:property value="viTri.tenViTri"/></td>
								<td><s:property value="ngayKetThuc"/></td>
								<td style="width: 180px; text-align: center; word-spacing: 5px; ">
									<i class="fa fa-trash-o i-delete" onclick="CapNhatQuangCao('${ idQuangCao}', '2', '1');" aria-hidden="true" title="Gỡ bỏ quảng cáo này"> Gỡ bỏ </i>
								</td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
  				</s:div>
			</s:div>
			<s:div id="two" label="Danh sách chờ" theme="ajax">
   				<s:div>
  					<table class="table sortable table-hover table-responsive">
						<thead>
							<tr>
								<th>Khách hàng</th>
								<th>Địa chỉ quảng bá</th>
								<th>Vị trí hiển thị</th>
								<th>Ngày hiển thị</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="listCho">
							<tr>
								<td><s:property value="khachHang.tenKhachHang"/></td>
								<td><s:property value="linkQuangBa"/></td>
								<td><s:property value="viTri.tenViTri"/></td>
								<td><s:property value="ngayBatDau"/></td>
								<td style="width: 180px; text-align: center; word-spacing: 5px; ">
									<i class="fa fa-check-circle-o" onclick="CapNhatQuangCao('${ idQuangCao}', '1', '2');" aria-hidden="true" title="Hiển thị quảng cáo này"> Hiển thị </i>
								</td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
  				</s:div>
			</s:div>
			<s:div id="three" label="Các đăng ký mới" theme="ajax">
   				<s:div>
  					<table class="table sortable table-hover table-responsive">
						<thead>
							<tr>
								<th>Khách hàng</th>
								<th>Địa chỉ quảng bá</th>
								<th>Vị trí hiển thị</th>
								<th>Ngày đăng ký</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="listDangKy">
							<tr>
								<td><s:property value="khachHang.tenKhachHang"/></td>
								<td><s:property value="linkQuangBa"/></td>
								<td><s:property value="viTri.tenViTri"/></td>
								<td><s:property value="ngayBatDau"/></td>
								<td style="width: 180px; text-align: center; word-spacing: 5px; ">
									<i class="fa fa-check-circle-o" onclick="CapNhatQuangCao('${ idQuangCao}', '2', '3');" aria-hidden="true" title="Chấp nhận đăng ký"> </i>
									<i class="fa fa-trash-o i-delete" onclick="XoaQuangCao('${ idQuangCao}');" aria-hidden="true" title="Xóa đăng ký"> </i>
								</td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
  				</s:div>
			</s:div>
			<s:div id="for" label="Khách hàng" theme="ajax">
				<s:div>
					<table class="table sortable table-hover table-responsive">
						<thead>
							<tr>
								<th>Khách hàng</th>
								<th>Địa chỉ</th>
								<th>Liên hệ đến</th>
								<th>Chi Phí Đầu Tư</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="listKH">
							<tr>
								<td><s:property value="tenKhachHang"/></td>
								<td><s:property value="diaChi"/></td>
								<td><s:property value="email"/></td>
								<td><s:property value="chiPhiDauTu"/></td>
								<td style="width: 180px; text-align: center; word-spacing: 5px; ">
									<i class="fa fa-trash-o i-delete" onclick="XoaKhachHang('${ idKhachHang}');" aria-hidden="true" title="Xóa liên kết"> Xóa </i>
								</td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
				</s:div>
			</s:div>
			<s:div id="four" label="Chú thích" theme="ajax">
				<s:div>
					<table class="table sortable table-hover table-responsive">
						<thead>
							<tr>
								<th>Vị trí</th>
								<th>Size logo</th>
								<th>Giá thành/ngày</th>
								<th>Ghi chú</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>TopMenu</td>
								<td>350x100</td>
								<td>5000 VND</td>
								<td>Hiển thị 1 quảng cáo nào đó trên TopMenu</td>
								<td style="width: 180px; text-align: center; word-spacing: 5px; ">
									<i class="fa fa-pencil-square-o" onclick="XoaKhachHang('${ idKhachHang}');" aria-hidden="true" title="Xóa liên kết"> Cập nhật giá </i>
								</td>
							</tr>
							<tr>
								<td>Content</td>
								<td>970x90</td>
								<td>3000 VND</td>
								<td>Hiển thị 1 quảng cáo nào đó trên phần nội dung của trang web</td>
								<td style="width: 180px; text-align: center; word-spacing: 5px; ">
									<i class="fa fa-pencil-square-o" onclick="XoaKhachHang('${ idKhachHang}');" aria-hidden="true" title="Xóa liên kết"> Cập nhật giá </i>
								</td>
							</tr>
							<tr>
								<td>MenuBar</td>
								<td>300x250</td>
								<td>5000 VND</td>
								<td>Hiển thị 1 quảng cáo nào đó trên MenuBar</td>
								<td style="width: 180px; text-align: center; word-spacing: 5px; ">
									<i class="fa fa-pencil-square-o" onclick="XoaKhachHang('${ idKhachHang}');" aria-hidden="true" title="Xóa liên kết"> Cập nhật giá </i>
								</td>
							</tr>
							<tr>
								<td>Botton Content</td>
								<td>234x60</td>
								<td>5000 VND</td>
								<td>Hiển thị danh sách slide quảng cáo phía dưới nội dung trang web</td>
								<td style="width: 180px; text-align: center; word-spacing: 5px; ">
									<i class="fa fa-pencil-square-o" aria-hidden="true" title="Xóa liên kết"> Cập nhật giá </i>
								</td>
							</tr>
						</tbody>
					</table>
					<img alt="Hiển thị quảng cáo trên trang" style="width: 100%;" src="images/TrangChu-Client.png">
				</s:div>			
			</s:div>
		</s:tabbedPanel>
	</div>
	<div class="modal fade" id="them-qc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; margin-top: 5%;">
			<div class="modal-dialog">
				 <div class="modal-content" style=" width: 80%; margin-left: auto; margin-right: auto;">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel">Thêm quảng cáo</h4>
				      </div>
				      <div class="modal-body">
				          <form action="them-danhmuc.action" method="post" id="fthem">
				          <div id="chon-khach">
				          	<div class="select-khach">
				          		<s:select cssClass="form-control" list="{'--- Chọn khách hàng ---','Khách hàng 1', 'Khách hàng 2', 'Khách hàng 3', 'Khách hàng 4'}"></s:select>
				          	</div>
				          	<div class="add-khach">
				          		<button id="btn-add" type="button" onclick="ThemKhachHang(0);" class="btn btn-default btn-sx" title="Thêm khách hàng mới">...</button>
				          	</div>
				          	<div class="clear"></div>
				          </div>
				          <div id="them-khach" style="display: none;">
				          		<input type="text" class="form-control" placeholder="Tên khách hàng ...">
				          		<br><input type="text" class="form-control" placeholder="Địa chỉ liên hệ ...">
				          		<br><input type="text" class="form-control" placeholder="Email liên lạc ...">
				          </div>
				          		<br><s:select cssClass="form-control" list="{'--- Chọn vị trí hiển thị ---', 'Vị trí 1', 'Vị trí 2', 'Vị trí 3'}"></s:select>
				          		<br><input type="text" class="form-control" placeholder="Địa chỉ cần quản bá...">
				          		<br><label>Logo quảng bá</label>
				          		<input type="file" class="form-control" placeholder="Địa chỉ cần quản bá...">
				          		<br><s:select cssClass="form-control" list="{'---Thời gian quảng bá---', '1 tuần', '1 tháng', '1 năm'}"></s:select>
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
</body>
<script type="text/javascript">
function ThemMoi(){
	$("#them-qc").modal("show");
}
function ThemKhachHang(e){
	if(e==1){
		$("#them-khach").attr("style", "display: none;");
		$("#btn-add").attr("onclick", "ThemKhachHang(0);");
		$("#btn-add").html("...");
	} else {
		$("#them-khach").attr("style", "margin-top: 5px; display: inherit;");
		$("#btn-add").attr("onclick", "ThemKhachHang(1);");
		$("#btn-add").html("X");
	}
}
function XoaQuangCao(id){
	if (confirm("Bạn muốn xóa quảng cáo " + id + " này không???") == true) {
		$.ajax({
			url : "xoa-quangcao.action",
			type : "post",
			data : {
				idQuangCao : id
			},
			beforeSend : function(){
			     $("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				$("#while-load").attr("style", "display: none;");
				if(result.indexOf("thất bại")>-1){
	            	/* alert("Lỗi ràng buột cơ sở dữ liệu! Vui lòng kiểm tra lại sau!"); */
	            	$("#result").html("Lỗi ràng buột cơ sở dữ liệu! Vui lòng kiểm tra lại sau!");
	            	$("#result").attr("class", "error");
	            } else {
	            	window.location.reload(true);
	            }
			},
			error : function(xhr, status, error) {
				/* alert("Lỗi trang! Vui long thực hiện lại thao tác sau khi refresh trang."); */
				$("#while-load").attr("style", "display: none;");
				$("#result").html("Lỗi trang! Vui long thực hiện lại thao tác sau khi refresh trang.");
            	$("#result").attr("class", "error");
			}
		});
	}
}
function CapNhatQuangCao(id, state, tab){
		$.ajax({
			url : "capnhat-quangcao.action",
			type : "post",
			data : {
				idQuangCao : id,
				linkQuangCao : state,
				viTri : tab
			},
			beforeSend : function(){
			     $("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				$("#while-load").attr("style", "display: none;");
				if(result.indexOf("thất bại")>-1){
	            	/* alert("Lỗi ràng buột cơ sở dữ liệu! Vui lòng kiểm tra lại sau!"); */
	            	$("#result").html("Lỗi ràng buột cơ sở dữ liệu! Vui lòng kiểm tra lại sau!");
	            	$("#result").attr("class", "error");
	            } else {
	            	window.location.reload(true);
	            }
			},
			error : function(xhr, status, error) {
				/* alert("Lỗi trang! Vui long thực hiện lại thao tác sau khi refresh trang."); */
				$("#while-load").attr("style", "display: none;");
				$("#result").html("Lỗi trang! Vui long thực hiện lại thao tác sau khi refresh trang.");
            	$("#result").attr("class", "error");
			}
		});
}
function XoaKhachHang(id){
	if (confirm("Xóa khách hàng sẽ ảnh hưởng đến độ chính xác của việc thống kê doanh thu quảng cáo!\n"
			+"Bạn muốn tiếp tục xóa khách hàng " + id + " này???") == true) {
		$.ajax({
			url : "xoa-khachhang.action",
			type : "post",
			data : {
				idKhachHang : id
			},
			beforeSend : function(){
			     $("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				$("#while-load").attr("style", "display: none;");
				if(result.indexOf("thất bại")>-1){
	            	/* alert("Lỗi ràng buột cơ sở dữ liệu! Vui lòng kiểm tra lại sau!"); */
	            	$("#result").html("Lỗi ràng buột cơ sở dữ liệu! Vui lòng kiểm tra lại sau!");
	            	$("#result").attr("class", "error");
	            } else {
	            	window.location.reload(true);
	            }
			},
			error : function(xhr, status, error) {
				/* alert("Lỗi trang! Vui long thực hiện lại thao tác sau khi refresh trang."); */
				$("#while-load").attr("style", "display: none;");
				$("#result").html("Lỗi trang! Vui long thực hiện lại thao tác sau khi refresh trang.");
            	$("#result").attr("class", "error");
			}
		});
	}
}
$(document).ready(function() {
	/* form cập nhật thông tin */
	$("#f-thongtin").validate({
		rules : {
			dienThoai : {
				digits : true
			},
			fax : {
				digits : true
			},
			email : {
				email : true
			}
		},
		messages : {
			dienThoai : {
				digits : "Không phải là số điện thoại!"
			},
			fax : {
				digits : "Không phải là số fax!"
			},
			email : {
				email : "Không đúng định dạng email!"
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
			            	alert("Cập nhật thông tin thất bại.");
			            } else {
			            	alert("Cập nhật thành công.");
			            }
			        },
			        error: function(jqXHR, textStatus, errorThrown) 
			        {
			            alert("Lỗi cập nhật thông tin hệ thống.");    
			        }
			   });
		}
	});
	//cập nhật nội quy
	var frm = $('#f-noiquy');
    frm.submit(function (ev) {
        $.ajax({
            type: "POST",
            url: 'capnhat-noiquy.action',
            data: {
            	noiQuyThanhVien : CKEDITOR.instances.noiQuyThanhVien.getData(),
        		quyDinhDangDichVu : CKEDITOR.instances.quyDinhDangDichVu.getData(),
        		quyDinhDangNhuCau : CKEDITOR.instances.quyDinhDangNhuCau.getData()
            },
            success: function (data) {
            	if(data.indexOf("thất bại")>-1){
	            	alert("Cập nhật nội quy thất bại.");
	            	location.reload(true);
	            } else {
	            	alert("Cập nhật thành công.");
	            }
            }
        });
        ev.preventDefault();
    });
  	//thêm quản trị hệ thống
	AjaxUpdateData('f-add-quantri', 'capquyen-quantri.action', 'Thêm quản trị thành công!', 'Thêm quản trị thất bại!');
	//thêm liên kết mới
	AjaxUpdateData('f-add-lienket', 'them-lienket.action', 'Thêm liên kết thành công!', 'Thêm liên kết thất bại!');
});
	//xử lý ajax cập nhật dữ liệu
	function AjaxUpdateData(idForm, url, megOk, MegEr){
		var form = $('#'+idForm);
		form.submit(function (ev) {
	        $.ajax({
	            type: "POST",
	            url: url,
	            data: form.serialize(),
	            success: function (data) {
	            	if(data.indexOf("thất bại")>-1){
		            	alert(MegEr);
		            } else {
		            	alert(megOk);
		            	location.reload(true);
		            }
	            }
	        });
	        ev.preventDefault();
	    });
	}
</script>
</html>
