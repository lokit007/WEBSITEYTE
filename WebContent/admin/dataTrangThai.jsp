<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<ul>
	<s:if test="#session.admin.dichVu">
		<li id="dichvu" class="btn-open-popover link"><a title="Danh sách dịch vụ cần xử lý"><i class="fa fa-heartbeat fa-2x"></i><s:if test="listDichVu.size()>0 or listDangKyDichVu.size()>0"><span><s:property value="listDichVu.size()+listDangKyDichVu.size()"/> </span></s:if></a></li>
	</s:if>
	<s:if test="#session.admin.nhuCau">
		<li id="nhucau" class="btn-open-popover link"><a title="Danh sách nhu cầu cần xử lý"><i class="fa fa-compass fa-2x"></i><s:if test="listNhuCau.size()>0 or listBaoGiaNhuCau.size()>0"><span><s:property value="listNhuCau.size()+listBaoGiaNhuCau.size()"/></span></s:if></a></li>
	</s:if>
	<s:if test="#session.admin.chiaSe">
		<li id="chiase" class="btn-open-popover link"><a title="Danh bài viết chia sẻ mới"><i class="fa fa-share-alt fa-2x"></i><s:if test="listChiaSe.size()>0"><span><s:property value="listChiaSe.size()"/></span></s:if></a></li>
	</s:if>
	<s:if test="#session.admin.nhaCungCap">
		<li id="nhacungcap" class="btn-open-popover link"><a title="Nhà cung cấp mới"><i class="fa fa-users fa-2x"></i><s:if test="listNhaCungCap.size()>0"><span><s:property value="listNhaCungCap.size()"/></span></s:if></a></li>
	</s:if>
	<s:if test="#session.admin.quangCao">
		<li id="quangcao" onclick="window.location.href='quang-cao.action'" class="link"><a title="Quảng cáo xử lý"><i class="fa fa-contao fa-2x"></i><s:if test="listQuangCao.size()>0"><span><s:property value="listQuangCao.size()"/></span></s:if></a></li>
	</s:if>
	<li>
		<div class="btn-group">
			<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"> <s:property value="#session.admin.taiKhoan.idTaiKhoan"/> <span class="caret"></span></button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="trang-ca-nhan.action">Cá nhân</a></li>
				<li class="divider"></li>
				<li><a href="dang-xuat.action">Đăng xuất</a></li>
			</ul>
		</div>
	</li>
</ul>
<s:if test="#session.admin.dichVu">
<div id="divContentdichvu" style="display: none">
	<s:if test="listDichVu.size()>0">
		<s:label cssClass="lb-item" value="Dịch vụ cần xử lý"></s:label>
		<s:iterator value="listDichVu">
			<s:a cssClass="item" href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}">
				<s:property value="baiViet.tenBaiViet"/>
			</s:a>
		</s:iterator>
	</s:if>
	<s:if test="listDangKyDichVu.size()>0">
		<s:label cssClass="lb-item" value="Các đăng ký dịch vụ"></s:label>
		<s:iterator value="listDangKyDichVu">
			<s:a cssClass="item" href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}">
				[<s:property value="ngayDangKy"/>] <s:property value="taiKhoan.hoTen"/> : <s:property value="tinNhan"/>
			</s:a>
		</s:iterator>
	</s:if>
</div>
</s:if>
<s:if test="#session.admin.nhuCau">
<div id="divContentnhucau" style="display: none">
	<s:if test="listNhuCau.size()>0">
		<s:label cssClass="lb-item" value="Nhu cầu cần xử lý"></s:label>
		<s:iterator value="listNhuCau">
			<s:a cssClass="item" href="chi-tiet-nhu-cau.action?idNhuCau=%{ idDichVu}">
				<s:property value="baiViet.tenBaiViet"/>
			</s:a>
		</s:iterator>
	</s:if>
	<s:if test="listBaoGiaNhuCau.size()>0">
		<s:label cssClass="lb-item" value="Báo giá nhu cầu"></s:label>
		<s:iterator value="listBaoGiaNhuCau">
			<s:a cssClass="item" href="chi-tiet-nhu-cau.action?idNhuCau=%{ idDichVu}">
				<s:property value="taiKhoan.hoTen"/>
				 [<s:property value="ngayDangKy"/>]
			</s:a>
		</s:iterator>
	</s:if>
</div>
</s:if>
<s:if test="#session.admin.chiaSe">
<div id="divContentchiase" style="display: none">
	<s:iterator value="listChiaSe">
		<s:a cssClass="item" href="chi-tiet-chia-se.action?idBaiViet=%{ idBaiViet}">
			[<s:property value="ngayDang"/>] 
			<s:property value="tenBaiViet"/>
		</s:a>
	</s:iterator>
</div>
</s:if>
<s:if test="#session.admin.nhaCungCap">
<div id="divContentnhacungcap" style="display: none">
	<s:iterator value="listNhaCungCap">
		<s:a cssClass="item" href="chi-tiet-nhacungcap.action?idNhaCungCap=%{ idNhaCungCap}">
			[<s:property value="taiKhoan.tinhTrang"/>] 
			<s:property value="taiKhoan.hoTen"/>
		</s:a>
	</s:iterator>
</div>
</s:if>
<s:if test="listDichVu.size()>0 or listDangKyDichVu.size()>0 or listNhuCau.size()>0 or listBaoGiaNhuCau.size()>0 or listChiaSe.size()>0 or listNhaCungCap.size()>0">
	<script type="text/javascript">
		runSourd();
		$(function() {
			InitPopover('dichvu', 'Dịch vụ cần xử lí');
			InitPopover('nhucau', 'Nhu cầu cần xử lí');
			InitPopover('chiase', 'Chia sẻ mới đăng');
			InitPopover('nhacungcap', 'Nhà cung cấp cần xử lý');
		});
	</script>
</s:if>
