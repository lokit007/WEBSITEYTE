<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<title>Trang chủ</title>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:if test="listDVTT.size()>0">
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a href="#" cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Chương trình sức khỏe cộng đồng
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:subset start="0" count="1" source="listDVTT">
						<s:iterator>
							<s:div cssClass="div-content-home-main-active">
							<s:a href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}">
								<img src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
								<s:div>
									<s:label cssClass="lb-link-active-title" value="%{ baiViet.tenBaiViet}"></s:label>
									<p><s:property value="baiViet.moTa"/></p>
								</s:div>
							</s:a>
							</s:div>
						</s:iterator>
					</s:subset>
					<s:subset start="1" source="listDVTT">
						<s:iterator>
							<s:div cssClass="div-content-home-main-item">
							<s:a href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}">
								<img alt="" src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
								<s:div>
									<s:label cssClass="lb-link-title" value="%{ baiViet.tenBaiViet}"></s:label>
									<s:if test="baiViet.moTa.length()>170">
										<p><s:property value="baiViet.moTa.substring(0,169)"/> ...</p>
									</s:if>
									<s:else>
										<p><s:property value="baiViet.moTa"/></p>
									</s:else>
								</s:div>
							</s:a>
							</s:div>
						</s:iterator>
					</s:subset>
					<s:div cssClass="clear"></s:div>
				</s:div>
			</s:div>
			</s:if>
			<s:if test="listDVC.size()>0">
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a href="#" cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Dịch vụ công
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:iterator value="listDVC">
						<s:div cssClass="div-content-home-main-dv">
						<s:a href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}">
							<img src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
							<s:div>
								<s:label cssClass="lb-link-title" value="%{ baiViet.tenBaiViet}"></s:label>
								<p><s:property value="baiViet.moTa"/></p>
							</s:div>
						</s:a>
						</s:div>
					</s:iterator>
					<s:div cssClass="clear"></s:div>
				</s:div>
			</s:div>
			</s:if>
			<s:if test="listDVTT.size()>0 || listDVC.size()>0">
			<s:div cssClass="div-quangcao">
				<img src="http://placehold.it/970x90">
			</s:div>
			</s:if>
			<s:if test="listDVTN.size()>0">
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a href="#" cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Dịch vụ tư nhân
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:subset start="0" count="1" source="listDVTN">
						<s:iterator>
							<s:div cssClass="div-content-home-main-active">
							<s:a href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}">
								<img src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
								<s:div>
									<s:label cssClass="lb-link-active-title" value="%{ baiViet.tenBaiViet}"></s:label>
									<p><s:property value="baiViet.moTa"/></p>
								</s:div>
							</s:a>
							</s:div>
						</s:iterator>
					</s:subset>
					<s:subset start="1" source="listDVTN">
						<s:iterator>
							<s:div cssClass="div-content-home-main-item">
							<s:a href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}">
								<img alt="" src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
								<s:div>
									<s:label cssClass="lb-link-title" value="%{ baiViet.tenBaiViet}"></s:label>
									<s:if test="baiViet.moTa.length()>170">
										<p><s:property value="baiViet.moTa.substring(0,169)"/> ...</p>
									</s:if>
									<s:else>
										<p><s:property value="baiViet.moTa"/></p>
									</s:else>
								</s:div>
							</s:a>
							</s:div>
						</s:iterator>
					</s:subset>
					<s:div cssClass="clear"></s:div>
				</s:div>
			</s:div>
			</s:if>
			<s:if test="listNCDV.size()>0">
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a href="#" cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Nhu cầu về dịch vụ y tế
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:iterator value="listNCDV">
						<s:div cssClass="div-content-home-main-tin">
							<s:a href="chi-tiet-nhu-cau.action?idNhuCau=%{ idDichVu}">
								<img src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
								<s:div>
									<s:label value="%{ baiViet.tenBaiViet}"></s:label>
									<s:if test="baiViet.moTa.length()>170">
										<p><s:property value="baiViet.moTa.substring(0,169)"/> ...</p>
									</s:if>
									<s:else>
										<p><s:property value="baiViet.moTa"/></p>
									</s:else>
								</s:div>
							</s:a>
						</s:div>
					</s:iterator>
					<s:div cssClass="clear"></s:div>
				</s:div>
			</s:div>
			</s:if>
			<s:if test="listCSYT.size()>0">
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a href="#" cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Chia sẻ kiến thức y tế
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:iterator value="listCSYT">
						<s:div cssClass="div-content-home-main-tin">
							<s:a href="bai-viet.action?idBaiViet=%{ idBaiViet}">
								<img alt="" src="images/${ anhMoTa }" class="img-thumbnail">
								<s:div>
									<s:label value="%{ tenBaiViet}"></s:label>
									<s:if test="baiViet.moTa.length()>170">
										<p><s:property value="moTa.substring(0,169)"/> ...</p>
									</s:if>
									<s:else>
										<p><s:property value="moTa"/></p>
									</s:else>
								</s:div>
							</s:a>
						</s:div>
					</s:iterator>
					<s:div cssClass="clear"></s:div>
				</s:div>
			</s:div>
			</s:if>
			<!-- kết thúc content -->
		</s:div>
		<!-- ajax load menubar here -->
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:div cssClass="div-quangcao-last">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>
</html>