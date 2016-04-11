<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<i class="fa fa-spinner fa-3x fa-spin" id="loadWhile"
	style="display: none;"></i>
<s:div id="div-header">
	<s:hidden id="login" value="%{ #session.login }"></s:hidden>
	<s:div id="div-header-logo">
		<s:a href="trang-chu.action" title="Dịch vụ y tế cộng đồng">
		<img alt="Dịch vụ y tế cộng đồng" src="images/logo-yte.png"
			id="img-logo"></s:a>
		<img src="http://placehold.it/350x100" style="float: right; margin: 10px;" >
		<s:div cssClass="clear"></s:div>
	</s:div>
	<%
		String path = request.getServletPath();
	%>
	<s:div id="div-header-menu">
		<ul>
			<li <%=path.equals("/TrangChu.jsp") ? "class='active'" : ""%>><s:a
					href="trang-chu.action">
					<i class="fa fa-home"></i> TRANG CHỦ</s:a></li>
			<li
				<%=path.equals("/DichVu.jsp") ? "class='active'"
							: path.equals("/XemDichVu.jsp") ? "class='active'" : ""%>>
				<s:a href="dich-vu.action">Dịch vụ y tế</s:a>
			</li>
			<li
				<%=path.equals("/NhuCau.jsp") ? "class='active'"
							: path.equals("/XemNhuCau.jsp") ? "class='active'"
							: path.equals("/DangNhuCau.jsp") ? "class='active'" : ""%>><s:a
					href="nhu-cau.action">Nhu cầu dịch vụ</s:a></li>
			<li
				<%=path.equals("/ChiaSe.jsp") ? "class='active'"
							: path.equals("/XemChiaSe.jsp") ? "class='active'" : ""%>><s:a
					href="chia-se.action">Kiến thức y tế</s:a></li>
			<li
				<%=path.equals("/DangDichVu.jsp") ? "class='active'" : ""%>><s:a
					href="dang-dich-vu-moi.action">Đăng ký dịch vụ</s:a></li>
			<li <%=path.equals("/BanDo.jsp") ? "class='active'" : ""%>><s:a
					href="BanDo.jsp">Bản đồ trung tâm y tế</s:a></li>
			<li>
				<form action="dich-vu.action" id="f-timkiem">
					<input class="ct-mail" type="text" name="txtFind" id="txtFind"
						placeholder="Tìm kiếm ...">
					<button class="ct-mail" type="submit">
						<i class="fa fa-search"></i>
					</button>
				</form>
			</li>
		</ul>
	</s:div>
</s:div>
