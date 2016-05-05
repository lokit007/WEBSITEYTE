<%@page import="model.dao.FormatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<title>Admin - Quản lý dịch vụ y tế</title>
<script src="../js/sorttable.js" type="text/javascript"></script>
<script src="../js/menuPage.js" type="text/javascript"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="js/xulynghiepvu.js" type="text/javascript"></script>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-share-alt"></i> Quản lý bài viết chia sẻ</label>
	<button class="btn btn-success btn-xs" id="btn-them" onclick="ChuyenHuong('dang-chia-se-moi.action');" ><i class="fa fa-plus-square"></i> Thêm mới</button>
	<div id="div-content">
		<form class="f-timkiem" action="chia-se.action" method="get">
				<label for="txtFind">Tìm kiếm : </label>
				<input type="text" id="txtFind" name="txtFind" placeholder="Nội dung cần tìm ..." value="<%= request.getParameter("txtFind")==null?"":FormatData.toUTF8(request.getParameter("txtFind")) %>">
				<button>Lọc dữ liệu</button>
		</form>
		<div>
			<table class="table sortable table-hover table-responsive">
				<thead>
					<tr>
						<th class="tieude">Tiêu đề bài viết</th>
						<th class="ngaygio">Ngày đăng</th>
						<th>Tác giả</th>
						<th>Danh mục</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="list">
					<tr>
						<td>
							<s:property value="tenBaiViet"/>
							<s:if test="tinhTrang=='Mới đăng'">
								<img alt="Dịch vụ mới" src="images/new-icon.png">
							</s:if>
						</td>
						<td class="td-15"> <s:property value="ngayDang"/> </td>
						<td class="td-20"> <s:property value="tenTacGia"/> </td>
						<td class="td-20"> <s:property value="danhMuc.tenDanhMuc"/> </td>
						<td class="td-15">
							<a href="chi-tiet-chia-se.action?idBaiViet=${ idBaiViet }" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
							<a href="thong-tin-chia-se.action?idBaiViet=${ idBaiViet }" class="btn-update btn-thaotac"><i class="fa fa-pencil-square-o"></i></a>
							<a style="cursor: pointer;" onclick="XoaChiaSe('${ idBaiViet }');" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
		<div id="menuPage">
			<s:property value="menu" escape="false"/>
		</div>
	</div>
</body>
</html>
