<%@page import="model.dao.FormatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<link rel="stylesheet" href="css/HeThongStyle.css">
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="../js/sorttable.js" type="text/javascript"></script>
<script src="js/nghiepvuhethong.js" type="text/javascript"></script>
<title>Admin - Trang Quản Trị</title>
<sx:head />
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-cogs"></i> Quản lý thông
		tin hệ thống</label>
	<div id="div-content">
		<s:tabbedPanel id="test" selectedTab="%{ #session.selectTab}">
			<s:div id="one" label="Hệ thống" theme="ajax">
  				<form id="f-thongtin" action="capnhat-thongtin.action" method="post">
  					<s:label value="Tên website trên hệ thống"></s:label>
		  			<s:textfield name="tenHeThong" cssClass="form-control" value="%{ taiNguyen.tenHeThong}"></s:textfield>
  					<br><s:label value="Bản quyền"></s:label>
  					<s:textfield name="banQuyen" cssClass="form-control" value="%{ taiNguyen.banQuyen}"></s:textfield>
  					<br><s:label value="Địa chỉ trụ sở"></s:label>
  					<s:textfield name="diaChi" cssClass="form-control" value="%{ taiNguyen.diaChi}"></s:textfield>
  					<br><s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
		  					<s:label value="Điện thoại"></s:label>
		  					<s:textfield name="dienThoai" cssClass="form-control" value="%{ taiNguyen.dienThoai}"></s:textfield>
		  				</s:div>
		  				<s:div cssClass="div-col-50">
		  					<s:label value="Email"></s:label>
		  					<s:textfield name="email" cssClass="form-control" value="%{ taiNguyen.email}"></s:textfield>
		  				</s:div>
		  				<s:div cssClass="clear"></s:div>
		  			</s:div>
		  			<br><s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
		  					<s:label value="Fax"></s:label>
		  					<s:textfield name="fax" cssClass="form-control" value="%{ taiNguyen.fax}"></s:textfield>
		  				</s:div>
		  				<s:div cssClass="div-col-50">
		  					<s:label value="Tổng số lượng truy cập"></s:label>
		  					<s:textfield name="soLuongTruyCap" readonly="true" cssClass="form-control" value="%{ #session.counter.view}"></s:textfield>
		  				</s:div>
		  				<s:div cssClass="clear"></s:div>
		  			</s:div>
  					<br><s:submit value="Cập nhật thông tin hệ thống"
						cssClass="btn btn-primary form-control"></s:submit>
  				</form>
			</s:div>
			<s:div id="two" label="Biên soạn quy định" theme="ajax">
   				<form id="f-noiquy">
   					<s:label value="Quy định đăng ký thành viên"></s:label>
					<s:textarea id="noiQuyThanhVien" name="noiQuyThanhVien" cssClass="ckeditor" value="%{ taiNguyen.noiQuyThanhVien}"></s:textarea>
					<br>
					<s:label value="Quy định phát hành dịch vụ"></s:label>
					<s:textarea id="quyDinhDangDichVu" name="quyDinhDangDichVu" cssClass="ckeditor" value="%{ taiNguyen.quyDinhDangDichVu}"></s:textarea>
					<br>
					<s:label value="Quy định đăng tải nhu cầu"></s:label>
					<s:textarea id="quyDinhDangNhuCau" name="quyDinhDangNhuCau" cssClass="ckeditor" value="%{ taiNguyen.quyDinhDangNhuCau}"></s:textarea>
					<br><s:submit value="Cập nhật nội quy"
						cssClass="btn btn-primary form-control"></s:submit>
   				</form>
			</s:div>
			<s:div id="three" label="Các trang liên kết" theme="ajax">
				<s:div>
					<table class="table sortable table-hover table-responsive">
						<thead>
							<tr>
								<th>Website</th>
								<th>Địa chỉ website</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="taiNguyen.lienKet">
							<tr>
								<td> <s:property value="key"/> </td>
								<td><a href="${ value}" target="blank"><s:property value="value"/></a></td>
								<td style="width: 180px; text-align: center; word-spacing: 5px; ">
									<i class="fa fa-trash-o i-delete" onclick="XoaLienKet('${ key}');" aria-hidden="true" title="Xóa liên kết"> Xóa </i>
								</td>
							</tr>
						</s:iterator>
							<tr id="row-add-lienket">
								<td colspan="9"><i class="fa fa-pencil" style="cursor: pointer;" aria-hidden="true" onclick="ThemLienKet(1);"> Thêm liên kết mới</i></td>
							</tr>
							<tr id="row-action-add-lienket" style="display: none;">
								<form id="f-add-lienket">
								<td>
									<input type="text" name="tenWebsite" style="width: 100%;" placeholder="Tên trang liên kết ..."/>
								</td>
								<td>
									<input type="text" name="diaChiWeb" style="width: 100%;" placeholder="Địa chỉ website ..."/>
								</td>
								<td style="width: 180px; text-align: center;">
									<i class="fa fa-share-square-o i-add" aria-hidden="true" title="Thêm mới" style="margin-right: 5px;" onclick="$('#f-add-lienket').submit();"> Thêm </i>
									<i class="fa fa-trash-o i-delete" aria-hidden="true" title="Hủy thao tác" onclick="ThemLienKet(0);"> Hủy </i>
								</td>
								</form>
							</tr>
						</tbody>
					</table>
				</s:div>
			</s:div>
			<s:div id="four" label="Phân quyền quản trị" theme="ajax">
    			<s:if test="hasActionErrors()">
					<s:div cssClass="bg-danger" cssStyle="padding: 5px;">
						<s:actionerror/>
					</s:div>
				</s:if>
				<s:if test="hasActionMessage()"> 
					<s:div cssClass="bg-success" cssStyle="padding: 5px;">
						<s:actionmessage/>
					</s:div>
				</s:if>
				<s:div>
					<table class="table sortable table-hover table-responsive">
						<thead>
							<tr>
								<th>Quản trị</th>
								<th title="Quản lý danh mục dịch vụ"><i class="fa fa-briefcase"></i></th>
								<th title="Quản lý dịch vụ"><i class="fa fa-heartbeat"></i></th>
								<th title="Quản lý nhu cầu"><i class="fa fa-compass"></i></th>
								<th title="Quản lý bài viết chia sẻ"><i class="fa fa-share-alt"></i></th>
								<th title="Quản lý nhà cung cấp"><i class="fa fa-user-md"></i></th>
								<th title="Quản lý tài nguyên hệ thống"><i class="fa fa-database"></i></th>
								<th title="Thống kê báo cáo"><i class="fa fa-bar-chart"></i></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="listQuanTri">
							<tr>
								<td>[<s:property value="taiKhoan.idTaiKhoan"/>] <s:property value="taiKhoan.hoTen"/> </td>
								<td><s:checkbox name="danhMuc" onchange="CapNhatQuyenHan('%{ taiKhoan.idTaiKhoan}', 'DanhMuc', this);" value="%{ danhMuc}" title="Cho phép/Ngừng quản lý danh mục"></s:checkbox></td>
								<td><s:checkbox name="dichVu" onchange="CapNhatQuyenHan('%{ taiKhoan.idTaiKhoan}', 'DichVu', this);" value="%{ dichVu}" title="Cho phép/Ngừng quản lý dịch vụ"></s:checkbox></td>
								<td><s:checkbox name="nhuCau" onchange="CapNhatQuyenHan('%{ taiKhoan.idTaiKhoan}', 'NhuCau', this);" value="%{ nhuCau}" title="Cho phép/Ngừng quản lý nhu cầu"></s:checkbox></td>
								<td><s:checkbox name="chiaSe" onchange="CapNhatQuyenHan('%{ taiKhoan.idTaiKhoan}', 'ChiaSe', this);" value="%{ chiaSe}" title="Cho phép/Ngừng quản lý bài viết chia sẻ"></s:checkbox></td>
								<td><s:checkbox name="nhaCungCap" onchange="CapNhatQuyenHan('%{ taiKhoan.idTaiKhoan}', 'NhaCungCap', this);" value="%{ nhaCungCap}" title="Cho phép/Ngừng quản lý nhà cung cấp"></s:checkbox></td>
								<td><s:checkbox name="taiNguyen" onchange="CapNhatQuyenHan('%{ taiKhoan.idTaiKhoan}', 'TaiNguyen', this);" value="%{ taiNguyen}" title="Cho phép/Ngừng quản lý tài nguyên hệ thống"></s:checkbox></td>
								<td><s:checkbox name="thongke" onchange="CapNhatQuyenHan('%{ taiKhoan.idTaiKhoan}', 'ThongKe', this);" value="%{ thongKe}" title="Cho phép/Ngừng quản lý thông kê báo cáo"></s:checkbox></td>
								<td style="cursor: pointer; width: 40px; text-align: center;">
									<i class="fa fa-trash-o i-delete" aria-hidden="true" onclick="XoaQuanTriVien('${ taiKhoan.idTaiKhoan}')"></i>
								</td>
							</tr>
						</s:iterator>
							<tr id="row-add">
								<td colspan="9"><i class="fa fa-pencil" style="cursor: pointer;" aria-hidden="true" onclick="ThemQuanTri(1);"> Thêm quản lý hệ thống</i></td>
							</tr>
							<form id="f-add-quantri">
							<tr id="row-action-add" style="display: none;">
								<td>
									<s:select name="taiKhoan" list="listTaiKhoan"
										listKey="IdTaiKhoan" listValue="hoTen"
										headerKey="" headerValue="-- Chọn quản trị viên --"
										cssStyle="width: 100%;"></s:select>
								</td>
								<td><s:checkbox name="danhMuc" value="false" title="Cho phép/Ngừng quản lý danh mục"></s:checkbox></td>
								<td><s:checkbox name="dichVu" value="false" title="Cho phép/Ngừng quản lý dịch vụ"></s:checkbox></td>
								<td><s:checkbox name="nhuCau" value="false" title="Cho phép/Ngừng quản lý nhu cầu"></s:checkbox></td>
								<td><s:checkbox name="chiaSe" value="false" title="Cho phép/Ngừng quản lý bài viết chia sẻ"></s:checkbox></td>
								<td><s:checkbox name="nhaCungCap" value="false" title="Cho phép/Ngừng quản lý nhà cung cấp"></s:checkbox></td>
								<td><s:checkbox name="taiNguyen" value="false" title="Cho phép/Ngừng quản lý tài nguyên hệ thống"></s:checkbox></td>
								<td><s:checkbox name="thongKe" value="false" title="Cho phép/Ngừng quản lý thông kê báo cáo"></s:checkbox></td>
								<td style="width: 55px; text-align: center;">
									<i class="fa fa-share-square-o i-add" aria-hidden="true" title="Cập nhật" onclick="$('#f-add-quantri').submit();"></i>
									<i class="fa fa-trash-o i-delete" aria-hidden="true" title="Hủy" onclick="ThemQuanTri(0);"></i>
								</td>
							</tr>
							</form>
						</tbody>
					</table>
				</s:div>
			</s:div>
		</s:tabbedPanel>
	</div>
</body>
</html>
