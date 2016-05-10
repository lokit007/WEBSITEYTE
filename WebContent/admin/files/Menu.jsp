<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:url id="path" />
<s:if test="#session.admin==null">
	<script type="text/javascript">
		window.location.href = "dang-nhap.action";
	</script>
</s:if>
<s:elseif test="#path.indexOf('danh-muc')>-1 and !#session.admin.danhMuc">
	<script type="text/javascript">
		window.location.href = "trang-chu.action";
	</script>
</s:elseif>
<s:elseif test="#path.indexOf('dich-vu')>-1 and !#session.admin.dichVu">
	<script type="text/javascript">
		window.location.href = "trang-chu.action";
	</script>
</s:elseif>
<s:elseif test="#path.indexOf('nhu-cau')>-1 and !#session.admin.nhuCau">
	<script type="text/javascript">
		window.location.href = "trang-chu.action";
	</script>
</s:elseif>
<s:elseif test="#path.indexOf('chia-se')>-1 and !#session.admin.chiaSe">
	<script type="text/javascript">
		window.location.href = "trang-chu.action";
	</script>
</s:elseif>
<s:elseif test="#path.indexOf('thanh-vien-nhacungcap')>-1 and !#session.admin.nhaCungCap">
	<script type="text/javascript">
		window.location.href = "trang-chu.action";
	</script>
</s:elseif>
<s:elseif test="#path.indexOf('he-thong')>-1 and !#session.admin.taiNguyen">
	<script type="text/javascript">
		window.location.href = "trang-chu.action";
	</script>
</s:elseif>
<s:elseif test="#path.indexOf('thong-ke')>-1 and !#session.admin.thongKe">
	<script type="text/javascript">
		window.location.href = "trang-chu.action";
	</script>
</s:elseif>

<%
	String path = request.getServletPath();
%>

<%-- <s:if test="#path=='/WEBSITEYTE/admin/thong-ke.action'">
	ádsadsad
</s:if> --%>
<div id="div-menu">
		<a href="../trang-chu.action" target="blank" title="Đến trang Client - Dịch vụ y tế cộng đồng">
			<img alt="Đến trang client" src="../images/logo-yte.png" id="img-logo"></a>
		<ul> 
			<li class="#path.indexOf('danh-muc')>-1" <%= path.equals("/admin/TrangChu.jsp")?"class='active'":"" %>><a href="trang-chu.action"><i class="fa fa-home"></i> Trang chủ </a></li>
			<s:if test="#session.admin.danhMuc">
				<li <%= path.equals("/admin/DanhMuc.jsp")?"class='active'":"" %>><a href="danh-muc.action"><i class="fa fa-briefcase"></i> Danh mục dịch vụ </a></li>
			</s:if>
			<s:if test="#session.admin.dichVu">
				<li <%= path.equals("/admin/DichVu.jsp")?"class='active'":"" %>><a href="dich-vu.action"><i class="fa fa-heartbeat"></i> Dịch vụ y tế</a></li>
			</s:if>
			<s:if test="#session.admin.nhuCau">
				<li <%= path.equals("/admin/NhuCau.jsp")?"class='active'":"" %>><a href="nhu-cau.action"><i class="fa fa-compass"></i> Nhu cầu y tế</a></li>
			</s:if>
			<li <%= path.equals("/admin/QuangCao.jsp")?"class='active'":"" %>><a href="quang-cao.action"><i class="fa fa-contao"></i> Quản lý quản cáo</a></li>
			<s:if test="#session.admin.chiaSe">
				<li <%= path.equals("/admin/ChiaSe.jsp")?"class='active'":"" %>><a href="chia-se.action"><i class="fa fa-share-alt"></i> Chia sẻ y tế</a></li>
			</s:if>
			<s:if test="#session.admin.taiKhoan.loaiTaiKhoan=='root'">
				<li id="qltv" <%= path.equals("/admin/ThanhVienQuanTri.jsp")?"class='active'":
								path.equals("/admin/ThanhVienNCC.jsp")?"class='active'":
								path.equals("/admin/ThanhVienNguoiDung.jsp")?"class='active'":""%>
					onclick="ShowSubMenu(1);" >
					<a style="cursor: pointer;"><i class="fa fa-users"></i> Quản lý thành viên <i id="icon-caret" class="fa fa-caret-square-o-down" style="float: right; margin : 3px;" aria-hidden="true"></i></a>
					<ul id="dataMenu">
						<li <%= path.equals("/admin/ThanhVienQuanTri.jsp")?"class='active'":"" %>><a href="thanh-vien-quantri.action"><i class="fa fa-user-secret"></i> Thành viên quản trị</a></li>
						<s:if test="#session.admin.nhaCungCap">
							<li <%= path.equals("/admin/ThanhVienNCC.jsp")?"class='active'":"" %>><a href="thanh-vien-nhacungcap.action"><i class="fa fa-user-md"></i> Nhà cung cấp</a></li>
						</s:if>
						<li <%= path.equals("/admin/ThanhVienNguoiDung.jsp")?"class='active'":"" %>><a href="thanh-vien-nguoidung.action"><i class="fa fa-user"></i> Người dùng thường</a></li>
					</ul>
				</li>
			</s:if>
			<s:elseif test="#session.admin.nhaCungCap">
				<li <%= path.equals("/admin/ThanhVienNCC.jsp")?"class='active'":"" %>><a href="thanh-vien-nhacungcap.action"><i class="fa fa-user-md"></i> Nhà cung cấp</a></li>
			</s:elseif>
			<s:if test="#session.admin.taiNguyen">
				<li <%= path.equals("/admin/HeThong.jsp")?"class='active'":"" %>><a href="he-thong.action"><i class="fa fa-database"></i> Quản lý tài nguyên</a></li>
			</s:if>
			<s:if test="#session.admin.thongKe">
				<li <%= path.equals("/admin/ThongKe.jsp")?"class='active'":"" %>><a href="thong-ke.action"><i class="fa fa-bar-chart"></i> Thống kê hệ thống</a></li>
			</s:if>
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
	<div id="div-state"></div>
	<script type="text/javascript" src="js/menustate.js"></script>

<div class="modal fade bs-example-modal-sm" id="success" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body bg-success">
      	<i class="fa fa-times" style="float: right; cursor: pointer;" title="Đóng" aria-hidden="true" data-dismiss="modal"></i>
        <p id="messuccess"></p>
      </div>
    </div>
  </div>
</div>

<div class="modal fade bs-example-modal-sm" id="error" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body bg-danger">
      	<i class="fa fa-times" style="float: right; cursor: pointer;" title="Đóng" aria-hidden="true" data-dismiss="modal"></i>
        <p id="meserror"></p>
      </div>
    </div>
  </div>
</div>
	