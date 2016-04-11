<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/add-binhluan.js" type="text/javascript"></script>
<title>Dịch vụ y tế</title>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-briefcase"></i> <s:property value="baiViet.tenBaiViet"/></label>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:div cssClass="div-content-home">
				<s:div id="div-info">
					<img src="../images/${ baiViet.anhMoTa}" >
					<s:div cssClass="div-col-50">
						<p>
							<s:property value="baiViet.moTa" />
						</p>
						<span>Ngày đăng : <s:property value="baiViet.ngayDang"/> </span>
						<br><span>Tác giả : <s:property value="baiViet.tenTacGia"/></span>
						<s:div id="btn-xuly">
							<button id="btn-dangky" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Cấp phép phát hành</button>
							<button id="btn-info" class="btn btn-info">Chi tiết nhà cung cấp</button>
							<button id="btn-info" class="btn btn-warning" data-toggle="modal" data-target="#formLienHe">Dịch vụ không đảm bảo</button>
						</s:div>
					</s:div>
					<s:div cssClass="clear"></s:div>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:property escape="false" value="baiViet.NoiDung" />				
				</s:div>
			</s:div>
			<br><br>
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-main">
					<span id="bl-nhan">Hỏi đáp về dịch vụ</span>
					<s:div cssClass="f-binhluan">
						<s:div cssClass="list-binhluan">
							<s:iterator value="listBinhLuan">
								<s:div cssClass="list-binhluan-item">
									<s:label value="%{ taiKhoan.hoTen}"></s:label>
									<s:a href="#" title="Xóa hỏi đáp" cssStyle="float: right;"><i class="fa fa-times"></i></s:a>
									<s:a href="#" title="Khóa tài khoản này" cssStyle="float: right; margin-right: 10px;"><i class="fa fa-lock"></i></s:a>
									<br> <s:property value="noiDung"/>
									<br>
									<span> <s:property value="ngayDang"/> </span>
									<s:iterator value="listTraLoi">
										<s:div cssClass="div-traloi">
											<s:label value="%{ taiKhoan.hoTen}"></s:label>
											<br> <s:property value="noiDung"/>
											<br> <span> <s:property value="ngayDang"/> </span>
										</s:div>
									</s:iterator>
								</s:div>
							</s:iterator>
						</s:div>
					</s:div>
				</s:div>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<script type="text/javascript">
				function ShowTraLoi(e) {
					$(e).parent().append("<div class='div-traloi'><form action=''><textarea class='txtHoi' placeholder='Trả Lời của bạn'></textarea><div class='btn-binhluan'><input type='submit' value='Trả lời'></div></form></div>");
					$(e).removeAttr("onclick");
				}
	</script>
	
	<s:include value="files/Footer.jsp"></s:include>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		    <div class="modal-content">
			      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title">Đăng ký dịch vụ</h4>
			      </div>
			      <form action="dang-ky-dich-vu.action" id="f-dangky-dichvu" method="post">
			      <div class="modal-body">
			      		<s:div id="loiDangKy" cssStyle="display: none;">Đăng ký dịch vụ thất bại!</s:div>
			      		<s:hidden name="idDichVu" value="%{ idDichVu}"></s:hidden>
			        	<s:label>Cá nhân/Tổ chức <span class="sp-quantrong">*</span> </s:label>
			       		<s:textfield cssClass="form-control" name="hoTen" value="%{ #session.user.hoTen }"></s:textfield>
			       		<br><s:label>Email liên hệ <span class="sp-quantrong">*</span> </s:label>
			       		<s:textfield cssClass="form-control" name="email" value="%{ #session.user.email }"></s:textfield>
			       		<br><s:label>Điện thoại <span class="sp-quantrong">*</span> </s:label>
			       		<s:textfield cssClass="form-control" name="dienThoai" value="%{ #session.user.dienThoai }"></s:textfield>
			       		<br><s:label>Lời nhắn đến nhà cung cấp</s:label>
			       		<s:textarea cssClass="form-control" name="tinNhan" cssStyle="height: 80px;"></s:textarea>
			      </div>
			      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
				        <button type="submit" class="btn btn-primary">Đăng ký</button>
			      </div>
			      </form>
		    </div>
	  </div>
	</div>
	<div class="modal fade" id="formLienHe" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		    <div class="modal-content">
			      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title">Liên hệ nhà cung cấp</h4>
			      </div>
			      <div class="modal-body">
			        	<s:div cssClass="div-show-content-item-1">
							<span class="name-tuvan"><i class="fa fa-user-secret"></i> <s:property value="dichVu.baiViet.tenTacGia" /> </span>
							<br>
							<a href="tel://${ dichVu.dienThoaiLienHe }"><i class="fa fa-phone"></i> <s:property value="dichVu.dienThoaiLienHe"/> </a>
							 | 
							<a href="mailto:${ dichVu.emailLienHe }"><i class="fa fa-envelope"></i> <s:property value="dichVu.emailLienHe" /> </a>
							<br><br>
							<s:div id="loilienhe" cssStyle="display: none;">Liên hệ thất bại!</s:div>
							<form action="gui-lien-he.action" id="f-lienhe-mail" method="post">
								<s:hidden name="urlPage" value="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}"></s:hidden>
								<s:hidden name="email" value="%{ dichVu.emailLienHe }"></s:hidden>
								<input name="tieuDe" type="text" placeholder="Tiêu đề ..." class="form-control">
								<br><textarea name="noiDung" rows="3" placeholder="Nội dung liên hệ ..." class="form-control"></textarea>
								<br><s:submit value="Gửi liên hệ"  cssClass="btn btn-primary btn-sm"></s:submit>
							</form>
							<s:div cssClass="clear"></s:div>
						</s:div>	
			      </div>
		    </div>
	  </div>
	</div>
	<div class="modal fade" id="baospan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		    <div class="modal-content">
			      <div class="modal-header" style="background-color: #FF4700; color: white;">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title">Báo dịch vụ không hợp lệ !!!</h4>
			      </div>
			      <form action="loi-bai-dang.action" id="f-dichvu-span" method="post">
			      <div class="modal-body">
			      		<s:hidden name="idKey" value="%{ idDichVu}"></s:hidden>
			      		<s:hidden name="nameTable" value="DICHVU"></s:hidden>
			        	<s:label value="Cá nhân/Tổ chức"><span class="sp-quantrong">*</span> </s:label>
			       		<s:textfield cssClass="form-control" name="hoTen" value="%{ #session.user.hoTen }"></s:textfield>
			       		<s:div cssClass="div-col-100">
							<s:div cssClass="div-col-50">
					       		<s:label value="Email liên hệ"><span class="sp-quantrong">*</span> </s:label>
					       		<s:textfield cssClass="form-control" name="email" value="%{ #session.user.email }"></s:textfield>
			       			</s:div>
			       			<s:div cssClass="div-col-50">
					       		<s:label value="Vấn đề về dịch vụ"><span class="sp-quantrong">*</span> </s:label>
					       		<s:select list="{'Lừa đảo', 'Không đúng thực tế', 'Không liên lạc được'}"
					       		 name="vanDe" cssClass="form-control"
					       		 headerKey="" headerValue="--- Chọn vấn đề xảy ra ---" ></s:select>
					       	</s:div>
					       	<s:div cssClass="clear"></s:div>
					    </s:div>
			       		<s:label value="Chi tiết lỗi dịch vụ"></s:label>
			       		<s:textarea cssClass="form-control" name="tinNhan" cssStyle="height: 80px;"></s:textarea>
			      </div>
			      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
				        <button type="submit" class="btn btn-danger">Báo lỗi</button>
			      </div>
			      </form>
		    </div>
	  </div>
	</div>
</body>

<script type="text/javascript">
	function HienThi(){
		$(".anView").attr('style', 'display: inherit;');
		$("#btn-dangky").attr('style', 'display: none;');
	}
	function XoaPhanTu(){
		$("#showError").remove();
	}
	$(document).ready(function() {
		/* form đăng ký dịch vụ */
		$("#f-dangky-dichvu").validate({
			rules : {
				hoTen : "required",
				dienThoai : {
					required : true,
					digits : true
				},
				email : {
					required : true,
					email : true
				}
			},
			messages : {
				hoTen : "Nhập họ tên/tổ chức!",
				dienThoai : {
					required : "Nhập điện thoại liên hệ!",
					digits : "Số điện thoại khồn đúng!"
				},
				email : {
					required : "Nhập email liên hệ!",
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
				            	$("#loiDangKy").attr("style", "display: inherit;");
				            } else {
				            	$("#loiDangKy").attr("style", "display: none;");
				            	$('#myModal').modal('hide');
				            	alert("Đăng ký dịch vụ thành công.");
				            }
				        },
				        error: function(jqXHR, textStatus, errorThrown) 
				        {
				        	$("#loiDangKy").attr("style", "display: none;");
				        	$('#myModal').modal('hide');
				            alert("Lỗi");    
				        }
				   });
			}
		});
		/* form lien he */
		$("#f-lienhe-mail").validate({
			rules : {
				tieuDe : {
					required : true
				},
				noiDung : {
					required : true
				}
			},
			messages : {
				tieuDe : "Bạn chưa nhập tiêu đề!",
				noiDung : {
					required : "Bạn chưa nhập nội dung!",
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
				            	$("#loilienhe").attr("style", "display: inherit;");
				            } else {
				            	$("#loilienhe").attr("style", "display: none;");
				            	$('#formLienHe').modal('hide');
				            	alert("Đã gửi đến nhà cung cấp.");
				            }
				        },
				        error: function(jqXHR, textStatus, errorThrown) 
				        {
				        	$("#loiDangKy").attr("style", "display: none;");
				        	$('#formLienHe').modal('hide');
				            alert("Lỗi");    
				        }
				   });
			}
		});
		/* form báo lỗi */
		$("#f-dichvu-span").validate({
			rules : {
				hoTen : "required",
				vanDe : {
					required : true
				},
				email : {
					required : true,
					email : true
				}
			},
			messages : {
				hoTen : "Nhập họ tên/tổ chức!",
				vanDe : {
					required : "Chưa chọn báo lỗi!"
				},
				email : {
					required : "Nhập email liên hệ!",
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
				            	$('#baospan').modal('hide');
				            	alert("Lỗi xử lý dữ liệu trên server!");
				            } else {
				            	$('#baospan').modal('hide');
				            	alert("Báo span dịch vụ thành công.");
				            }
				        },
				        error: function(jqXHR, textStatus, errorThrown) 
				        {
				        	$('#baospan').modal('hide');
				            alert("Lỗi");    
				        }
				   });
			}
		});
	});
</script>
</html>
