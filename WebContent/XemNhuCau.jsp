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
<title>Thông tin nhu cầu y tế</title>
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
						<i class="fa fa-hand-o-right"></i> <s:property value="nhuCau.baiViet.tenBaiViet" />
					</s:a>
				</s:div>
				<s:div id="div-info">
					<img src="images/${ nhuCau.baiViet.anhMoTa}" >
					<s:div cssClass="div-col-50">
						<p><s:property value="nhuCau.baiViet.moTa"/> </p>
						<span>Thời gian mong muốn : Bắt đầu từ <s:property value="nhuCau.ngayBatDau"/> đến <s:property value="nhuCau.ngayKetThuc"/>  </span>
						<br><span>Địa chỉ mong muốn : <s:property value="nhuCau.diaChiTrienKhai" default="Trao đổi thỏa thuận 2 bên" /> </span>
						<p id="loi-tua">Liên hệ cho tôi sớm nhất có thể, tôi rất mong sự giúp đỡ của các bạn trong cuộc sống này. Chân thành cám ơn!</p>
						<s:div id="btn-xuly">
							<button id="btn-dangky" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Báo giá dịch vụ</button>
							<button id="btn-info" class="btn btn-default" data-toggle="modal" data-target="#formLienHe">Liên hệ</button>
							<button type="button" class="btn btn-default" onclick="window.print();">
							  <span class="glyphicon glyphicon-print"></span> Print
							</button>
						</s:div>
					</s:div>
					<s:div cssClass="clear"></s:div>
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
							<i class="fa fa-ban"></i> Báo cáo vi phạm</button>
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
							<s:submit value="Gửi nhà cung cấp" onclick="addBinhLuan('%{ nhuCau.baiViet.idBaiViet}', 'BINHLUAN');" ></s:submit>
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
				<s:if test="nhuCau.baiViet.taiKhoan.idTaiKhoan==#session.user.idTaiKhoan and listDangKy.size()>0">
					<br>
					<s:div cssClass="div-content-home">
						<s:div cssClass="div-content-home-main">
							<span id="bl-nhan">Danh sách đã báo giá [<s:property value="listDangKy.size()"/>] </span>
							<s:div cssClass="f-binhluan">
								<s:div cssClass="list-binhluan">
									<s:iterator value="listDangKy">
									<s:if test="tinhTrang!='Hủy bỏ'">
										<s:div cssClass="list-binhluan-item">
											<i onclick="CapNhatTrangThai('${ idDangKy }', 'DANGKYDICHVU', 'Hủy bỏ');" title="Từ chối/Hủy báo giá này" style="float: right; cursor: pointer;" class="fa fa-times" aria-hidden="true"></i>
											<s:if test="tinhTrang=='Đăng ký'">
											<i onclick="CapNhatTrangThai('${ idDangKy }', 'DANGKYDICHVU', 'Chấp nhận');" title="Chấp nhận báo giá này" style="margin-right: 10px; float: right; cursor: pointer;" class="fa fa-check" aria-hidden="true"></i>
											</s:if>
											<s:label value="%{ taiKhoan.hoTen}"></s:label> 
											[<i><s:property value="ngayDangKy"/></i>]
											<s:if test="tinhTrang=='Đăng ký'">
												<img alt="Dịch vụ mới" src="admin/images/new-icon.png">
											</s:if>
											<br>Tin nhắn : <s:property value="tinNhan"/>
										</s:div>
									</s:if>
									</s:iterator>
								</s:div>
							</s:div>
						</s:div>
					</s:div>
				</s:if>
			</s:div>
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a href="#" cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Nhu cầu dịch vụ khác
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:iterator value="list">
						<s:div cssClass="div-content-home-main-tin">
							<s:a href="chi-tiet-nhu-cau.action?idNhuCau=%{ idDichVu}">
								<img alt="" src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
								<s:div>
									<s:label cssClass="lb-link-title" value="%{ baiViet.tenBaiViet}"></s:label>
									<span title="Địa chỉ nơi nhận việc">
										<i class="fa fa-map-marker"></i>  
										<s:property value="diaChiTrienKhai" />
									</span>
									<p><s:property value="baiViet.moTa"/></p>
								</s:div>
							</s:a>
						</s:div>
					</s:iterator>
					
					<s:div id="continue" cssClass="clear continue">
						<button class="btn btn-primary" onclick="XemThem('${ nhuCau.dienThoaiLienHe }', '${ nhuCau.baiViet.danhMuc.idDanhMuc }');">Xem thêm ...</button>
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
				        <h4 class="modal-title">Báo giá dịch vụ</h4>
			      </div>
			      <s:if test="#session.user.loaiTaiKhoan=='Nhà cung cấp'">
				      <form action="dang-ky-nhu-cau.action" id="f-dangky-nhucau" method="post">
				      		<s:hidden name="idNhuCau" value="%{ idNhuCau}"></s:hidden>
					      <div class="modal-body">
					      		<s:div id="loiDangKy" cssStyle="display: none;">Báo giá thất bại!</s:div>
					       		<br><s:label>Giá gói dịch vụ <span class="sp-quantrong">*</span> </s:label>
					       		<s:textfield cssClass="form-control" name="giaDichVu"></s:textfield>
					       		<br><s:label>Lời nhắn</s:label>
					       		<s:textarea cssClass="form-control" name="tinNhan" cssStyle="height: 80px;"></s:textarea>
					      </div>
					      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
						        <button type="submit" class="btn btn-primary">Báo giá</button>
					      </div>
				      </form>
			      </s:if>
			      <s:else>
			      	<div class="modal-body">
						Bạn cần <label style="cursor: pointer;" onclick="$('#myModal').modal('hide'); showLogin('formLienHe');">đăng nhập tài khoản NHÀ CUNG CẤP</label> để thực hiện chức năng này. Cám ơn!
				  	</div>
				  </s:else>
		    </div>
	  </div>
	</div>
	<div class="modal fade" id="formLienHe" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		    <div class="modal-content">
			      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title">Thông tin liên hệ</h4>
			      </div>
			      <div class="modal-body">
			      <s:if test="#session.user.loaiTaiKhoan=='Nhà cung cấp'">
			        	<s:div cssClass="div-show-content-item-1">
						<span class="name-tuvan"><i class="fa fa-user-secret"></i> <s:property value="nhuCau.baiViet.tenTacGia"/> </span>
						<br>
						<a href="tel://${ nhuCau.dienThoaiLienHe }"><i class="fa fa-phone"></i> <s:property value="nhuCau.dienThoaiLienHe"/> </a>
						 | <a href="mailto:${ nhuCau.emailLienHe }"><i class="fa fa-envelope"></i> <s:property value="nhuCau.emailLienHe"/> </a>
						<br><br>
						<s:div id="loilienhe" cssStyle="display: none;">Liên hệ thất bại!</s:div>
						<form action="gui-lien-he.action" id="f-lienhe-mail" method="post">
							<s:hidden name="urlPage" value="http://localhost:8080/WEBSITEYTE/chi-tiet-nhu-cau.action?idNhuCau=%{ idNhuCau}"></s:hidden>
							<s:hidden name="email" value="%{ nhuCau.emailLienHe }"></s:hidden>
							<input name="tieuDe" type="text" placeholder="Tiêu đề ..." class="form-control">
							<br><textarea name="noiDung" rows="3" placeholder="Nội dung liên hệ ..." class="form-control"></textarea>
							<br><s:submit value="Gửi liên hệ"  cssClass="btn btn-primary btn-sm"></s:submit>
						</form>
						<s:div cssClass="clear"></s:div>
					</s:div>	
					</s:if>
					<s:else>
						Bạn cần <label style="cursor: pointer;" onclick="$('#myModal').modal('hide'); showLogin('formLienHe');">đăng nhập tài khoản NHÀ CUNG CẤP</label> để thực hiện chức năng này. Cám ơn!
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
			      		<s:hidden name="idKey" value="%{ idNhuCau}"></s:hidden>
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
