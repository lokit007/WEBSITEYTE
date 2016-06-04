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
<script src="js/xuly-nghiepvu.js" type="text/javascript"></script>
<title>Thông tin dịch vụ y tế</title>
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
					<s:div>
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
				<s:if test="#session.Content!=null">
					<s:set name="content" value="%{ #session.Content}"></s:set>
					<img onclick="window.location.href='${ content.linkQuangBa}';" 
						src="images/${ content.logoQuangBa }" title="Click để xem chi tiết" 
						style="float: right; margin: 10px; cursor: pointer; max-height: 100px;" >
				</s:if>
				<s:else>
					<img onclick="window.location.href='dang-ky-quang-cao.action';" title="Liên hệ quảng cáo" style="cursor: pointer;" src="http://placehold.it/970x90">
				</s:else>
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
					       		<s:textfield cssClass="form-control" name="dienThoai" maxlength="11" value="%{ #session.user.dienThoai }"></s:textfield>
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
</html>
