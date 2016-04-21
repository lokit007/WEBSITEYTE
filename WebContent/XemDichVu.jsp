<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/add-binhluan.js" type="text/javascript"></script>
<title>Dịch vụ y tế</title>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> <s:property value="dichVu.baiViet.tenBaiViet"/>
					</s:a>
				</s:div>
				<s:div id="div-info">
					<img src="images/${ dichVu.baiViet.anhMoTa}" >
					<s:div cssClass="div-col-50">
						<p>
							<s:property value="dichVu.baiViet.moTa" />
						</p>
						<span>Ngày bắt đầu đăng ký : <s:property value="dichVu.ngayBatDau"/> </span>
						<br><span>Ngày kết thúc dịch vụ : <s:property value="dichVu.ngayKetThuc"/></span>
						<p id="loi-tua">Hãy để chúng tôi phục vụ bạn, sức khỏe và thành công của bạn là niềm vui lớn đối với những
						thầy thuốc chúng tôi.</p>
						<s:div id="btn-xuly">
							<button id="btn-dangky" class="btn btn-primary" onclick="DangKy();">Đăng ký dịch vụ</button>
							<button id="btn-info" class="btn btn-info" 
								onclick="window.location.href='nha-cung-cap.action?idNhaCungCap=${ dichVu.idNhaCungCap }';">Nhà cung cấp</button>
							<button id="btn-info" class="btn btn-default" data-toggle="modal" data-target="#formLienHe">Liên hệ</button>
							<button type="button" class="btn btn-default" onclick="window.print();">
							  <span class="glyphicon glyphicon-print"></span> Print
							</button>
						</s:div>
					</s:div>
					<s:div cssClass="clear"></s:div>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:property escape="false" value="dichVu.baiViet.NoiDung" />				
				</s:div>
			</s:div>
			<s:div cssClass="div-quangcao">
				<img src="http://placehold.it/970x90">
			</s:div>
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-main">
					<div id="sahred">
						<button type="button" id="btn-vipham" class="btn btn-link" onclick="javascript:$('#baospan').modal('show');">
							<i class="fa fa-ban"></i> Báo cáo dịch vụ không hợp lệ</button>
						Chia sẻ :
						<a href="https://twitter.com/share" class="twitter-share-button">Tweet</a>
						<div id="fb-root"></div>
						<!-- Your share button code -->
						<div class="fb-share-button"
							data-href="http://webvietnhat-demo.jelastic.skali.net/"
							data-layout="button_count">
						</div>
						<g:plusone></g:plusone>
					</div>
					<script src="js/facebook.login.ajax.min.js" type="text/javascript"></script>
					<!-- Google+ -->
					<script type="text/javascript"
						src="https://apis.google.com/js/plusone.js"></script>
					<!-- Twitter -->
					<script>
						!function(d, s, id) {
							var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
									.test(d.location) ? 'http' : 'https';
							if (!d.getElementById(id)) {
								js = d.createElement(s);
								js.id = id;
								js.src = p + '://platform.twitter.com/widgets.js';
								fjs.parentNode.insertBefore(js, fjs);
							}
						}(document, 'script', 'twitter-wjs');
					</script>
					<!-- Facebook -->
					<script>
						(function(d, s, id) {
							$('.fb-share-button').attr("data-href",window.location.href);
							var js, fjs = d.getElementsByTagName(s)[0];
							if (d.getElementById(id))
								return;
							js = d.createElement(s);
							js.id = id;
							js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.4&appId=1671772309710877";
							fjs.parentNode.insertBefore(js, fjs);
						}(document, 'script', 'facebook-jssdk'));
					</script>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<span id="bl-nhan">Hỏi đáp về dịch vụ</span>
					<s:div cssClass="f-binhluan">
						<textarea id="txtHoi" name="noiDung" placeholder="Câu hỏi của bạn"></textarea>
						<s:div cssClass="btn-binhluan">
							<s:submit value="Gửi nhà cung cấp" onclick="addBinhLuan('%{ dichVu.baiViet.idBaiViet}', 'BINHLUAN');" ></s:submit>
						</s:div>
						<s:div cssClass="list-binhluan">
							<s:iterator value="listBinhLuan">
								<s:div cssClass="list-binhluan-item">
									<s:label value="%{ taiKhoan.hoTen}"></s:label>
									<br> <s:property value="noiDung"/>
									<br><button type="button" class="btn btn-link" onclick="ShowTraLoi('${ id }','TRALOI',this);">Trả lời</button> 
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
	<s:div cssClass="div-quangcao">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
	</s:div>
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
					      		<s:hidden id="idDichVuDK" name="idDichVu" value="%{ idDichVu}"></s:hidden>
					        	<s:label>Cá nhân/Tổ chức <span class="sp-quantrong">*</span> </s:label>
					       		<s:textfield cssClass="form-control" name="hoTen" value="%{ #session.user.hoTen }"></s:textfield>
					       		<br><s:label>Email liên hệ <span class="sp-quantrong">*</span> </s:label>
					       		<s:textfield id="emailDK" cssClass="form-control" name="email" value="%{ #session.user.email }"></s:textfield>
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
			      <s:if test="#session.user!=null">
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
					</s:if>
					<s:else>
						Bạn cần <label style="cursor: pointer;" onclick="showLogin('formLienHe');">đăng nhập tài khoản thành viên</label> để thực hiện chức năng này. Cám ơn!
					</s:else>
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
	function DangKy(){
		$.ajax({
			url : "check-dangky-dv.action",
	        type: "POST",
	        data : {
	        	idDichVu : $("#idDichVuDK").val(),
	        	email : $("#emailDK").val()
	        },
	        beforeSubmit : function (){
	        	$("#loadWhile").attr("style", "display: inherit;");
	        },
	        success:function(data, textStatus, jqXHR) 
	        {
	        	$("#loadWhile").attr("style", "display: none;");
	            if(data.indexOf("thất bại")>-1){
	            	alert("Bạn đã đăng ký dịch vụ này!");
	            } else {
	            	$('#myModal').modal('show');
	            }
	        }
		});
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
					digits : "Số điện thoại không đúng!"
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
				        	$("#loadWhile").attr("style", "display: none;");
				        	$('#baospan').modal('hide');
				            if(data.indexOf("thất bại")>-1){
				            	alert("Lỗi xử lý dữ liệu trên server!");
				            } else {
				            	alert("Báo span dịch vụ thành công.");
				            	$("#btn-vipham").removeAttr("onclick");
				            	$("#btn-dangky").remove();
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
