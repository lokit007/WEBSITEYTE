<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<link rel="stylesheet" href="css/bandoStyle.css">
<script src="js/bando.js" type="text/javascript"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDanVTriVyYbvbkp7c8RPD7O1SOuKo8aK4&libraries=places" async defer></script>
<sx:head />
<title>Bản đồ y tế</title>

</head>
<body onload="getLocation();">
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Các trung tâm y tế Huế
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<input id="origin-input" class="controls" type="text"
						placeholder="Địa điểm A ...">
				
					<input id="destination-input" class="controls" type="text"
						placeholder="Đến địa điểm B ...">
					<div id="text">
						<div class="tree-node">
							<i class="fa fa-minus-square-o" aria-hidden="true"></i>
							<label class="lable-root" onclick="ShowHide(this);">Bản đồ y tế Huế</label>
							<ul>
								<li>
									<i class="fa fa-minus-square-o" aria-hidden="true"></i>
									<label class="lable-root" onclick="ShowHide(this);">Bệnh viện</label>
									<ul>
										<s:iterator value="list">
										<s:if test="loaiNCC=='Bệnh viện'">
											<li onclick="XemMap('${ taiKhoan.location }');">
												<span class="ic-chil">-</span>
												<s:property value="taiKhoan.hoTen"/>
												<s:hidden cssClass="dataMarKer" value="icon-bv-20.png*%{ taiKhoan.hoTen }*%{ taiKhoan.diaChi }*%{ taiKhoan.location }"></s:hidden>
											</li>
										</s:if>
										</s:iterator>
									</ul>
								</li>
								<li>
									<i class="fa fa-minus-square-o" aria-hidden="true"></i>
									<label class="lable-root" onclick="ShowHide(this);">Trung tâm y tế</label>
									<ul>
										<s:iterator value="list">
										<s:if test="loaiNCC=='Trung tâm y tế'">
											<li onclick="XemMap('${ taiKhoan.location }');">
												<span class="ic-chil">-</span>
												<s:property value="taiKhoan.hoTen"/>
												<s:hidden cssClass="dataMarKer" value="icon-tt-20.png*%{ taiKhoan.hoTen }*%{ taiKhoan.diaChi }*%{ taiKhoan.location }"></s:hidden>
											</li>
										</s:if>
										</s:iterator>
									</ul>
								</li>
								<li>
									<i class="fa fa-minus-square-o" aria-hidden="true"></i> 
									<label class="lable-root" onclick="ShowHide(this);">Trạm xá</label>
									<ul>
										<s:iterator value="list">
										<s:if test="loaiNCC=='Trạm xá'">
											<li onclick="XemMap('${ taiKhoan.location }');">
												<span class="ic-chil">-</span>
												<s:property value="taiKhoan.hoTen"/>
												<s:hidden cssClass="dataMarKer" value="icon-tx-20.png*%{ taiKhoan.hoTen }*%{ taiKhoan.diaChi }*%{ taiKhoan.location }"></s:hidden>
											</li>
										</s:if>
										</s:iterator>
									</ul>
								</li>
								<li>
									<i class="fa fa-minus-square-o" aria-hidden="true"></i> 
									<label class="lable-root" onclick="ShowHide(this);">Y tế tư nhân</label>
									<ul>
										<s:iterator value="list">
										<s:if test="loaiNCC=='Tư nhân'">
											<li onclick="XemMap('${ taiKhoan.location }');">
												<span class="ic-chil">-</span>
												<s:property value="taiKhoan.hoTen"/>
												<s:hidden cssClass="dataMarKer" value="icon-tn-20.png*%{ taiKhoan.hoTen }*%{ taiKhoan.diaChi }*%{ taiKhoan.location }"></s:hidden>
											</li>
										</s:if>
										</s:iterator>
									</ul>
								</li>
							</ul>
						</div>
						<div class="tree-node">
							<i class="fa fa-minus-square-o" aria-hidden="true"></i>
							<label class="lable-root" onclick="ShowHide(this);">Chú thích</label>
							<ul>
								<li>
									<span class="ic-chil">-</span>
									<img alt="Bệnh viện" src="admin/images/icon-bv-20.png"> Bệnh viện
								</li>
								<li>
									<span class="ic-chil">-</span>
									<img alt="Bệnh viện" src="admin/images/icon-tt-20.png"> Trung tâm y tế
								</li>
								<li>
									<span class="ic-chil">-</span>
									<img alt="Bệnh viện" src="admin/images/icon-tx-20.png"> Trạm xá
								</li>
								<li>
									<span class="ic-chil">-</span>
									<img alt="Bệnh viện" src="admin/images/icon-tn-20.png"> Tư nhân
								</li>
							</ul>
						</div>
					</div>
					<div id="map"></div>
					<div class="clear"></div>
				</s:div>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>
</html>
