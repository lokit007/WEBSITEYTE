<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<script src="js/footer.js" type="text/javascript"></script>

	<s:if test="#session.TaiNguyen==null">
		<script type="text/javascript">
			InitDataHeThong();
		</script>
	</s:if>

	<s:div cssClass="div-quangcao-last">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
	</s:div>
	
	<script type="text/javascript">
	$(function animate() {
			$(".div-quangcao-last img:first").each(function() {
				/* héng chạy không mược nơi kiểu cà giật á */
				$(this).delay(5000);
				$(this).animate({
					marginLeft : -$(this).outerWidth(true),
                    opacity: "hide"
				}, 2000, function() {
					$(this).insertAfter(".div-quangcao-last img:last");
					$(this).fadeIn();
					$(this).css({
						'margin-left' : '0px'
					});
					setTimeout(function() {
						animate()
					}, 2000);
				});
			});
		});
	</script>
	
	<s:div id="div-footer">
		<s:label id="footer-title" cssClass="footer-title" value="WESITE DỊCH VỤ Y TẾ CỘNG ĐỒNG"></s:label>
		<s:div cssClass="div-footer-item">
			<s:label cssClass="footer-title" value="Liên Hệ"></s:label>
			<span>Sở y tế Thừa Thiên Huế - Cổng Dịch vụ y tế cộng đồng</span>
			<span>Địa chỉ : <s:property value="#session.TaiNguyen.diaChi"/> </span>
			<span>Phonel : <s:property value="#session.TaiNguyen.dienThoai"/> </span>
			<span>Fax : <s:property value="#session.TaiNguyen.fax"/> </span>
			<span>Email : <s:property value="#session.TaiNguyen.email"/> </span>
		</s:div>
		<s:div cssClass="div-footer-item">
			<s:label cssClass="footer-title" value="Liên Kết"></s:label>
			<s:a href="https://syt.thuathienhue.gov.vn/"><i class="fa fa-hand-o-right"></i> Sở y tế</s:a>
			<s:a href="http://www.thuathienhue.gov.vn/vi-vn/"><i class="fa fa-hand-o-right"></i> Cổng thông tin điện tử</s:a>
			<s:a href="http://www.dav.gov.vn/"><i class="fa fa-hand-o-right"></i> Cục quản lý dược</s:a>
			<s:a href="http://vfa.gov.vn/"><i class="fa fa-hand-o-right"></i> Cục an toàn vệ sinh thực phẩm</s:a>
			<s:a href="http://kcb.vn/"><i class="fa fa-hand-o-right"></i> Cục quản lý khám chữa bệnh</s:a>
		</s:div>
		<s:div cssClass="div-footer-item" cssStyle="max-width: 368px;">
			<s:label cssClass="footer-title" value="Đăng ký nhận bản tin qua mail"></s:label>
			Hãy nhập địa chỉ mail để nhận các bản tin mới nhất từ chúng tôi.
			<form id="nhanEMail" action="nhan-mail.action" method="post">
			<table><tr>
				<td>
					<select class="ct-mail" name="loaiTin">
						<option>Dịch vụ</option>
						<option>Nhu cầu</option>
						<option>Chia sẻ</option>
					</select>
				</td>
				<td><input class="ct-mail" id="email-select" type="text" name="email" placeholder="Email nhận tin..."></td>
				<td><button class="ct-mail" type="submit"><i class="fa fa-envelope-o"></i></button> </td>
			</tr></table>
			</form>
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:div cssClass="banquen">
			<s:property value="#session.TaiNguyen.banQuyen"/>
	</s:div>
	<div class="modal fade bs-example-modal-sm" id="success" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body bg-success">
	      	<i class="fa fa-times" style="float: right; cursor: pointer;" title="Đóng" aria-hidden="true" data-dismiss="modal"></i>
	        <p id="messuccess"></p>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="modal fade bs-example-modal-sm" id="error" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body bg-danger">
	      	<i class="fa fa-times" style="float: right; cursor: pointer;" title="Đóng" aria-hidden="true" data-dismiss="modal"></i>
	        <p id="meserror"></p>
	      </div>
	    </div>
	  </div>
	</div>
	<s:div id="tuychon">
		<s:if test="#session.user==null">
			<s:a href="dang-nhap.action"><i class="fa fa-user-plus fa-2x"></i> Đăng nhập/Đăng ký</s:a>
			<s:a href="dang-nhu-cau-moi.action"><i class="fa fa-paper-plane-o fa-2x"></i> Đăng nhu cầu thuê</s:a>
			<s:a href="#email-select"><i class="fa fa-envelope-square fa-2x"></i> Đăng ký nhận mail</s:a>
		</s:if>
		<s:else>
			<s:a href="trang-ca-nhan.action"><i class="fa fa-user fa-2x"></i> Thông tin cá nhân</s:a>
			<s:a href="dang-nhu-cau-moi.action"><i class="fa fa-paper-plane-o fa-2x"></i> Đăng nhu cầu thuê</s:a>
			<s:a href="#email-select"><i class="fa fa-envelope-square fa-2x"></i> Đăng ký nhận mail</s:a>
			<s:a href="dang-xuat.action"><i class="fa fa-sign-out fa-2x"></i> Đăng xuất hệ thống</s:a>
		</s:else>
	</s:div>
	<i id="thong-bao" class="fa fa-bell-o btn-open-popover" aria-hidden="true"></i>
	<div id="divContentHTML" style="display:none">
		<s:if test="#session.ThongBao!=null">
			<s:property value="%{ #session.ThongBao}"/>
		</s:if>
        <s:else>
        	Không có thông báo nào cả!
        </s:else>
    </div>
	<div class="modal fade" id="dangNhapModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		    <div class="modal-content">
			      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title">Đăng nhập hệ thống</h4>
			      </div>
			      <form action="dang-nhap-action.action" id="fm-dangnhap" method="post">
			      <div class="modal-body">
			      		<br><s:label value="Tài khoản : " for="taiKhoan"></s:label>
				  		<s:textfield name="taiKhoan" cssClass="form-control"></s:textfield>
				  		<br><s:label value="Mật khẩu : " for="matKhau"></s:label>
				  		<s:password name="matKhau" cssClass="form-control"></s:password>
			      </div>
			      <div class="modal-footer">
				        <button type="submit" class="btn btn-primary">Đăng nhập</button>
			      </div>
			      </form>
		    </div>
	  </div>
	</div>
