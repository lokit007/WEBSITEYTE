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
<script src="js/thanhvienhethong.js" type="text/javascript"></script>
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
	<label id="lb-title"><i class="fa fa-briefcase"></i> <s:property value="dichVu.baiViet.tenBaiViet"/></label>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:div cssClass="div-content-home">
				<s:div id="div-info">
					<img src="../images/${ dichVu.baiViet.anhMoTa}" >
					<s:div>
						<p>
							<s:property value="dichVu.baiViet.moTa" />
						</p>
						<span>Ngày bắt đầu đăng ký : <s:property value="dichVu.ngayBatDau"/> </span>
						<br><span>Ngày kết thúc dịch vụ : <s:property value="dichVu.ngayKetThuc"/></span>
						<p id="loi-tua">Hãy để chúng tôi phục vụ bạn, sức khỏe và thành công của bạn là niềm vui lớn đối với những
						thầy thuốc chúng tôi.</p>
						<s:div id="btn-xuly">
							<s:if test="dichVu.baiViet.tinhTrang=='Mới đăng'">
								<button id="btn-dangky" class="btn btn-primary" onclick="CapPhepDichVu(${ dichVu.idDichVu});">Cấp phép phát hành</button>
							</s:if>
							<s:if test="dichVu.baiViet.tinhTrang=='Vi phạm'">
								<button id="btn-dangky" class="btn btn-primary" onclick="CapPhepDichVu(${ dichVu.idDichVu});">Dịch vụ đảm bảo</button>
							</s:if>
							<button id="btn-info" class="btn btn-info" onclick="ChuyenHuong('chi-tiet-nhacungcap.action?idNhaCungCap=${ dichVu.idNhaCungCap}');">Chi tiết nhà cung cấp</button>
							<button id="btn-info" class="btn btn-warning" onclick="DichVuViPham(${ dichVu.idDichVu});">Dịch vụ không đảm bảo</button>
						</s:div>
					</s:div>
					<s:div cssClass="clear"></s:div>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:property escape="false" value="dichVu.baiViet.NoiDung" />				
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
									<i class="fa fa-times" onclick="XoaCauHoi('${ id }', this);" title="Xóa hỏi đáp" style="float: right; cursor: pointer;"></i>
									<i class="fa fa-lock" onclick="CapNhatNguoiDung('${ taiKhoan.idTaiKhoan }', 'Khóa tài khoản');" title="Khóa tài khoản này" style="float: right; margin-right: 10px; cursor: pointer;"></i>
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
			<s:if test="listDangKy.size()>0">
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-main">
					<span id="bl-nhan">Danh sách bệnh nhân đăng ký [<s:property value="listDangKy.size()"/>] </span>
					<s:div cssClass="f-binhluan">
						<s:div cssClass="list-binhluan">
							<s:iterator value="listDangKy">
								<s:div cssClass="list-binhluan-item">
									<i onclick="HuyDangKy('${ idDangKy }');" title="Hủy đăng ký này" style="float: right; cursor: pointer;" class="fa fa-times" aria-hidden="true"></i>
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
