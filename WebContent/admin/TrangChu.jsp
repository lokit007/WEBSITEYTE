<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<title>Admin - Trang Quản Trị</title>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-cogs"></i> Quản lý hệ thống dịch vụ y tế - Sở y tế Huế</label>
	<div id="div-content">
			<!-- Dịch vụ mới -->
			<h4>Các dịch vụ cần xử lý</h4>
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
				<s:iterator value="listDV">
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
							<a href="#" onclick="XoaDichVu('${ idDichVu }');" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			<!-- Nhà cung cấp mới -->
			<hr>
			<h4>Xét duyệt, xử lý nhà cung cấp</h4>
			<table class="table sortable table-hover table-responsive">
				<thead>
					<tr>
						<th class="tieude">Tài khoản</th>
						<th>Địa chỉ</th>
						<th class="ngaygio">Điện thoại</th>
						<th class="ngaygio">Tình trạng</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="listNCC">
					<tr>
						<td> <s:property value="taiKhoan.idTaiKhoan"/> </td>
						<td> <s:property value="taiKhoan.diaChi"/> </td>
						<td class="td-10"> <s:property value="taiKhoan.dienThoai"/> </td>
						<td class="td-10"> <s:property value="taiKhoan.tinhTrang"/> </td>
						<td class="td-15">
							<s:if test="taiKhoan.tinhTrang=='TK mới'">
								<a href="#" title="Báo cáo vi phạm" onclick="CapNhatNguoiDung('${ taiKhoan.idTaiKhoan }', 'Vi phạm');" class="btn-check btn-thaotac"><i class="fa fa-exclamation-triangle"></i></a>
							</s:if>
							<s:elseif test="taiKhoan.tinhTrang=='Vi phạm'">
								<a href="#" title="Khôi phục tài khoản" onclick="CapNhatNguoiDung('${ taiKhoan.idTaiKhoan }', 'TK mới');" class="btn-check btn-thaotac"><i class="fa fa-reply"></i></a>
							</s:elseif>										
							<a href="chi-tiet-nhacungcap.action?idNhaCungCap=${ idNhaCungCap }" title="Chi tiết nhà cung cấp" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
							<a href="#" onclick="XoaNhaCungCap('${ idNhaCungCap }');" title="Xóa nhà cung cấp" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			<!-- Nhu cầu mới -->
			<hr>
			<h4>Nhu cầu mới</h4>
			<table class="table sortable table-hover table-responsive">
				<thead>
					<tr>
						<th class="tieude">Nhu cầu</th>
						<th>Chi tiết nhu cầu</th>
						<th>Ngày đăng</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="listNC">
					<tr>
						<td>
							<s:property value="baiViet.tenBaiViet"/>
							<s:if test="baiViet.tinhTrang=='Mới đăng'">
								<img alt="Dịch vụ mới" src="images/new-icon.png">
							</s:if>
						</td>
						<s:if test="baiViet.moTa.length()>170">
							<td><s:property value="baiViet.moTa.substring(0,169)"/> ...</td>
						</s:if>
						<s:else>
							<td><s:property value="baiViet.moTa"/></td>
						</s:else>
						<td class="td-15"> <s:property value="baiViet.ngayDang"/> </td>
						<td class="td-15">
							<a href="chi-tiet-nhu-cau.action?idDichVu=${ idDichVu }" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
							<a href="#" onclick="XoaNhuCau('${ idDichVu }');" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
	</div>
</body>
</html>