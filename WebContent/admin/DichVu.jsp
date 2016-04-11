<%@page import="model.dao.FormatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<title>Admin - Quản lý dịch vụ y tế</title>
<script src="../js/sorttable.js"></script>
<script src="../js/menuPage.js"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-briefcase"></i> Quản lý dịch vụ y tế</label>
	<button class="btn btn-success btn-xs" id="btn-them" onclick="ChuyenHuong('dang-dich-vu-moi.action');" ><i class="fa fa-plus-square"></i> Thêm mới</button>
	<div id="div-content">
		<form class="f-timkiem" action="dich-vu.action" method="get">
				<label for="txtFind">Tìm kiếm : </label>
				<input type="text" id="txtFind" name="txtFind" placeholder="Nội dung cần tìm ..." value="<%= request.getParameter("txtFind")==null?"":FormatData.toUTF8(request.getParameter("txtFind")) %>">
				<button>Lọc dữ liệu</button>
		</form>
		<div>
			<table class="table sortable table-hover table-responsive">
				<thead>
					<tr>
						<th class="tieude" colspan="2">
							<img alt="Dịch vụ từ thiện" src="images/tuthien.png"> DV từ thiện | 
							<img alt="Dịch vụ công" src="images/dvcong.png"> DV công | 
							<img alt="Dịch vụ tư" src="images/dvtu.png"> DV tư nhân
						</th>
						<th class="ngaygio">Ngày bắt đầu</th>
						<th>Nhà cung cấp</th>
						<th>Email liên hệ</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="list">
					<tr>
						<td>
							<s:if test="loaiHinhDichVu=='Từ thiện'">
								<img alt="Dịch vụ từ thiện" src="images/tuthien-32.png">
							</s:if>
							<s:elseif test="loaiHinhDichVu=='Dịch vụ công'">
								<img alt="Dịch vụ từ thiện" src="images/dvcong-32.png">
							</s:elseif>
							<s:else>
								<img alt="Dịch vụ từ thiện" src="images/dvtu-32.png">
							</s:else>
						</td>
						<td>
							<s:property value="baiViet.tenBaiViet"/>
							<s:if test="baiViet.tinhTrang=='Mới đăng'">
								<img alt="Dịch vụ mới" src="images/new-icon.png">
							</s:if>
						</td>
						<td class="td-15"> <s:property value="ngayBatDau"/> </td>
						<td class="td-20"> <s:property value="baiViet.tenTacGia"/> </td>
						<td class="td-20"> <s:property value="emailLienHe"/> </td>
						<td class="td-15">
							<a href="chi-tiet-dich-vu.action?idDichVu=${ idDichVu }" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
							<a href="thong-tin-dich-vu.action?idDichVu=${ idDichVu }" class="btn-update btn-thaotac"><i class="fa fa-pencil-square-o"></i></a>
							<a href="#" onclick="XoaDichVu('${ idDichVu }');" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
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
<script type="text/javascript">
	function ChuyenHuong(url){
		window.location.href = url;
	}
	function XoaDichVu(id) {
		if (confirm("Bạn muốn xoá dịch vụ " + id + " này không???") == true) {
			$.ajax({
				url : "xoa-dichvu.action",
				type : "post",
				data : {
					idDichVu : id
				},
				beforeSend : function(){
				     $("#while-load").attr("style", "display: inline-block;");
				},
				success : function(result) {
					window.location.reload(true);
					$("#while-load").attr("style", "display: none;");
				},
				error : function(xhr, status, error) {
					$("#loi").modal("show");
					$("#while-load").attr("style", "display: none;");
				}
			});
		}
	}
</script>
</html>
