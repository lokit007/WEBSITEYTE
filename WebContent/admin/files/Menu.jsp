<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="#session.admin==null">
	<script type="text/javascript">
		window.location.href = "dang-nhap.action";
	</script>
</s:if>
<%
	String path = request.getServletPath();
%>
<div id="div-menu">
		<a href="../trang-chu.action" target="blank" title="Đến trang Client - Dịch vụ y tế cộng đồng">
			<img alt="Đến trang client" src="../images/logo-yte.png" id="img-logo"></a>
		<ul> 
			<li <%= path.equals("/admin/TrangChu.jsp")?"class='active'":"" %>><a href="trang-chu.action"><i class="fa fa-home"></i> Trang chủ</a></li>
			<li <%= path.equals("/admin/DanhMuc.jsp")?"class='active'":"" %>><a href="danh-muc.action"><i class="fa fa-briefcase"></i> Danh mục dịch vụ</a></li>
			<li <%= path.equals("/admin/DichVu.jsp")?"class='active'":"" %>><a href="dich-vu.action"><i class="fa fa-heartbeat"></i> Dịch vụ y tế</a></li>
			<li <%= path.equals("/admin/NhuCau.jsp")?"class='active'":"" %>><a href="nhu-cau.action"><i class="fa fa-compass"></i> Nhu cầu y tế</a></li>
			<li <%= path.equals("/admin/HoTro.jsp")?"class='active'":"" %>><a href="HoTro.jsp"><i class="fa fa-contao"></i> Hổ trợ trực tuyến</a></li>
			<li <%= path.equals("/admin/ChiaSe.jsp")?"class='active'":"" %>><a href="chia-se.action"><i class="fa fa-share-alt"></i> Chia sẻ y tế</a></li>
			<li id="qltv" <%= path.equals("/admin/ThanhVienQuanTri.jsp")?"class='active'":
							path.equals("/admin/ThanhVienNCC.jsp")?"class='active'":
							path.equals("/admin/ThanhVienNguoiDung.jsp")?"class='active'":""%>>
				<a href="#" data-toggle="dropdown"><i class="fa fa-users"></i> Quản lý thành viên</a>
				<ul id="dataMenu">
					<li <%= path.equals("/admin/ChiaSe.jsp")?"class='active'":"" %>><a href="thanh-vien-quantri.action"><i class="fa fa-user-secret"></i> Thành viên quản trị</a></li>
					<li <%= path.equals("/admin/ChiaSe.jsp")?"class='active'":"" %>><a href="thanh-vien-nhacungcap.action"><i class="fa fa-user-md"></i> Nhà cung cấp</a></li>
					<li <%= path.equals("/admin/ChiaSe.jsp")?"class='active'":"" %>><a href="thanh-vien-nguoidung.action"><i class="fa fa-user"></i> Người dùng thường</a></li>
				</ul>
			</li>
			<li <%= path.equals("/admin/HeThong.jsp")?"class='active'":"" %>><a href="he-thong.action"><i class="fa fa-database"></i> Quản lý tài nguyên</a></li>
			<li <%= path.equals("/admin/ThongKe.jsp")?"class='active'":"" %>><a href="ThongKe.jsp"><i class="fa fa-bar-chart"></i> Thống kê hệ thống</a></li>
		</ul>
		<s:div cssClass="div-show-content" id="load-60">
			<img
				src="http://banners.wunderground.com/banner/gizmotimetemp_both/ 
						language/www/global/stations/48852.gif"
				alt="Du bao thoi tiet - Co do Hue"
				title="Dự báo thời tiết - Cố đô Huế" height="41" width="100%">
			<span class="luocxem"><i class="fa fa-globe"></i> Lược truy
				cập <span class="sluong"> <s:property value="#session.counter.view"/> </span></span>
			<span class="luocxem"><i class="fa fa-users"></i> Online <span
				class="sluong"> <s:property value="#session.counter.getOnline()"/> </span></span>
		</s:div>
	</div>
	<div id="div-state">
		<button id="icon-menu" onclick="show(1);">Menu</button>
		<script type="text/javascript">
			function show(e){
				if(e == 1){
					$('#div-menu').css('display', 'inline');
					$('#icon-menu').attr('onclick', 'show(0);');
				} else {
					$('#div-menu').css('display', 'none');
					$('#icon-menu').attr('onclick', 'show(1);')
				}
			}
			function ShowView(e, o){
				if(e == 1){
					$('#dataChange').css('display', 'inline');
					$(o).attr('onclick', 'show(0, this);');
				} else {
					$('#dataChange').css('display', 'none');
					$(o).attr('onclick', 'show(1, this);');
				}
			}
		</script>
		<ul>
			<li><a href="#" onclick="ShowView(1, this);" title="Danh sách dịch vụ cần xử lý"><i class="fa fa-heartbeat fa-2x"></i><span>1</span></a></li>
			<li><a href="#" title="Danh sách nhu cầu cần xử lý"><i class="fa fa-contao fa-2x"></i><span>10</span></a></li>
			<li><a href="#" title="Danh bài viết chia sẻ mới"><i class="fa fa-share-alt fa-2x"></i><span>110</span></a></li>
			<li><a href="#" title="Nhà cung cấp mới"><i class="fa fa-users fa-2x"></i><span>10</span></a></li>
		</ul>
		<s:div cssClass="list-view" id="dataChange">
			<table class="table sortable table-hover table-responsive">
				<thead>
					<tr>
						<th class="tieude">
							tên dịch vụ mới
						</th>
						<th>Nhà cung cấp</th>
						<th>chi tiết</th>
					</tr>
				</thead>
			</table>
		</s:div>
	</div>

	