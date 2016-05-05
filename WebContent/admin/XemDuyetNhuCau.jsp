<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../js/add-binhluan.js" type="text/javascript"></script>
<script src="js/xulynghiepvu.js" type="text/javascript"></script>
<title>Dịch vụ y tế</title>
<script type="text/javascript">
	function ShowTraLoi(e) {
		$(e).parent().append("<div class='div-traloi'><form action=''><textarea class='txtHoi' placeholder='Trả Lời của bạn'></textarea><div class='btn-binhluan'><input type='submit' value='Trả lời'></div></form></div>");
		$(e).removeAttr("onclick");
	}
</script>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-compass"></i> <s:property value="nhuCau.baiViet.tenBaiViet"/></label>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:div cssClass="div-content-home">
				<s:div id="div-info">
					<img src="../images/${ nhuCau.baiViet.anhMoTa}" >
					<s:div cssClass="div-col-50">
						<p>
							<s:property value="nhuCau.baiViet.moTa" />
						</p>
						<span>Ngày bắt đầu đăng ký : <s:property value="nhuCau.ngayBatDau"/> </span>
						<br><span>Ngày kết thúc dịch vụ : <s:property value="nhuCau.ngayKetThuc"/></span>
						<p id="loi-tua">Liên hệ cho tôi sớm nhất có thể, tôi rất mong sự giúp đỡ của các bạn trong cuộc sống này. Chân thành cám ơn!</p>
						<s:div id="btn-xuly">
							<s:if test="nhuCau.baiViet.tinhTrang=='Mới đăng'">
								<button id="btn-dangky" class="btn btn-primary" onclick="CapPhepPhatHanh('${ nhuCau.baiViet.idBaiViet}', 'BAIVIET', 'Đăng bài');">Đăng nhu cầu</button>
							</s:if>
							<button id="btn-info" class="btn btn-warning" onclick="DichVuViPham(${ nhuCau.idDichVu});">Nhu cầu không đảm bảo</button>
						</s:div>
					</s:div>
					<s:div cssClass="clear"></s:div>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:property escape="false" value="nhuCau.baiViet.NoiDung" />				
				</s:div>
			</s:div>
			<br>
			<s:if test="listBinhLuan.size()>0">
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-main">
					<span id="bl-nhan">Hỏi đáp về nhu cầu</span>
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
			<br>
			</s:if>
			<s:if test="listBaoGia.size()>0">
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-main">
					<span id="bl-nhan">Danh sách đã báo giá [<s:property value="listBaoGia.size()"/>] </span>
					<s:div cssClass="f-binhluan">
						<s:div cssClass="list-binhluan">
							<s:iterator value="listBaoGia">
								<s:div cssClass="list-binhluan-item">
									<s:a href="#" title="Xóa yêu cầu này" cssStyle="float: right;">
										<i class="fa fa-times" aria-hidden="true"></i></s:a>
									<s:label value="%{ taiKhoan.hoTen}"></s:label> 
									[<i><s:property value="ngayDangKy"/></i>]
									<s:if test="tinhTrang=='Đăng ký'">
										<img alt="Dịch vụ mới" src="images/new-icon.png">
									</s:if>
									<br>Tin nhắn : <s:property value="tinNhan"/>
								</s:div>
							</s:iterator>
						</s:div>
					</s:div>
				</s:div>
			</s:div>
			</s:if>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
</body>
</html>
