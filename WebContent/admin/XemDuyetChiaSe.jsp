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
	<label id="lb-title"><i class="fa fa-share-alt"></i> <s:property value="baiViet.tenBaiViet"/></label>
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
						<s:if test="baiViet.tinhTrang=='Mới đăng' or baiViet.tinhTrang=='Vi phạm'">
							<button id="btn-dangky" class="btn btn-primary" onclick="CapPhepPhatHanh('${ baiViet.idBaiViet}', 'BAIVIET', 'Đăng bài', 'Đã đăng tải chia sẻ thành công!', 'Lỗi cập nhật cơ sở dữ liệu! Vui lòng thao tác lại sau.');" >Cho phép đăng</button>
						</s:if>
							<button id="btn-info" class="btn btn-warning" onclick="CapPhepPhatHanh('${ baiViet.idBaiViet}', 'BAIVIET', 'Khóa bài đăng', 'Khóa bài đăng thành công!', 'Lỗi cập nhật cơ sở dữ liệu! Vui lòng thử lại sau.');">Xử lý vi phạm</button>
						</s:div>
					</s:div>
					<s:div cssClass="clear"></s:div>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:property escape="false" value="baiViet.NoiDung" />				
				</s:div>
			</s:div>
			<br>
			<s:if test="listBinhLuan.size()>0">
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
			</s:if>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
</body>
</html>
