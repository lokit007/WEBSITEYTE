<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head theme="ajax" debug="true"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<link rel="stylesheet" href="css/star-rating.min.css">
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/star-rating.min.js" type="text/javascript"></script>
<script src="js/validate-form.js" type="text/javascript"></script>
<script src="js/xuly-nghiepvu.js" type="text/javascript"></script>
<title>Trang cá nhân</title>

</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:if test="#session.user!=null">
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Trang cá nhân <s:property value="#session.user.hoTen"/>
					</s:a>
				</s:div>
				<s:if test="#session.user.loaiTaiKhoan=='Người dùng'">
				<div class="jumbotron" style="padding: 10px 30px;" id="div-dangky-ncc">
				  <h1>Chào <s:property value="#session.user.hoTen"/>! </h1>
				  <p>Hiện tại tài khoản của bạn là tài khoản thông thường. Nếu bạn là các tổ chức y tế, bác sỹ, y tá ... hoặc là các 
				  nhân tổ chức hoạt động trong lĩnh vực y tế. Hãy đăng ký tài khoản nhà cung cấp để có thể quảng bá các dịch vụ y
				  tế của mình một cách hiệu quả nhất đến bệnh nhân. Sở y tế đảm bảo quyền và lợi ích về dịch vụ của bạn.</p>
				  <p><a class="btn btn-primary btn-lg" href="dang-ky-ncc.action" role="button">Đăng ký nhà cung cấp</a></p>
				</div>
				</s:if>
				<s:div cssClass="div-content-home-main">
				<form id="f-capnhat-thongtin" action="capnhat-canhan.action" method="post">
					<fieldset>
			  			<legend>Thông tin cá nhân</legend>
			  			<s:hidden name="idTaiKhoan" value="%{ #session.user.idTaiKhoan}"></s:hidden>
			  			<s:label value="Cá nhân/Tổ chức : "></s:label><span class="sp-quantrong"> * </span>
				  		<s:textfield name="hoTen" value="%{ #session.user.hoTen}" cssClass="form-control"></s:textfield>
				  		<br><s:label value="Địa chỉ : "></s:label><span class="sp-quantrong"> * </span>
				  		<s:textfield name="diaChi" value="%{ #session.user.diaChi}" cssClass="form-control"></s:textfield>
				  		<s:div cssClass="div-col-100">
					  		<s:div cssClass="div-col-50">
						  		<br><s:label value="Điện thoại liên hệ : "></s:label><span class="sp-quantrong"> * </span>
						  		<s:textfield name="dienThoai" maxlength="11" value="%{ #session.user.dienThoai}" cssClass="form-control"></s:textfield>
					  		</s:div>
					  		<s:div cssClass="div-col-50">
						  		<br><s:label value="Email : "></s:label><span class="sp-quantrong"> * </span>
						  		<s:textfield name="email" value="%{ #session.user.email}" cssClass="form-control"></s:textfield>
					  		</s:div>
					  		<s:div cssClass="clear"></s:div>
				  		</s:div>
				  		<s:div cssClass="div-col-100">
					  		<s:div cssClass="div-col-50">
						  		<br><s:label value="Mật khẩu mới : "></s:label>
						  		<s:textfield id="matKhauMoi" name="matKhauMoi" cssClass="form-control"></s:textfield>
					  		</s:div>
					  		<s:div cssClass="div-col-50">
						  		<br><s:label value="Xác nhận mật khẩu mới : "></s:label>
						  		<s:textfield name="matKhauXacNhan" cssClass="form-control"></s:textfield>
					  		</s:div>
					  		<s:div cssClass="clear"></s:div>
				  		</s:div>
				  		<br><s:submit value="Cập nhật thông tin" cssClass="form-control btn btn-success"></s:submit>
			  		</fieldset>
			  	</form>
				</s:div>
			</s:div>
			</s:if>
			<s:div cssClass="div-quangcao">
				<s:if test="#session.Content!=null">
					<s:set name="content" value="%{ #session.Content}"></s:set>
					<img onclick="window.location.href='${ content.linkQuangBa}';" 
						src="images/${ content.logoQuangBa }" title="Click để xem chi tiết" 
						style="float: right; margin: 10px; cursor: pointer; max-height: 100px;" >
				</s:if>
				<s:else>
					<img onclick="window.location.href='dang-ky-quang-cao.action';" title="Liên hệ quảng cáo" style="cursor: pointer;" src="http://placehold.it/970x90">
				</s:else>
			</s:div>
			<s:div cssClass="div-content-home">
				<s:tabbedPanel id="tabContainer" selectedTab="%{ #session.selectTab}">
					<s:div label="Dịch vụ Sử dụng" id="tab1" theme="ajax">
						<table class="table sortable table-hover table-responsive">
							<thead>
								<tr>
									<th class="tieude">Dịch vụ</th>
									<th class="ngaygio">Ngày đăng ký</th>
									<th>Tình trạng xử lý</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="listDV">
								<tr>
									<td>
										<s:if test="loaiHinhDichVu=='Từ thiện'">
											<img alt="Dịch vụ từ thiện" style="width: 16px; margin-right: 5px;" src="admin/images/tuthien-32.png">
										</s:if>
										<s:elseif test="loaiHinhDichVu=='Dịch vụ công'">
											<img alt="Dịch vụ công" style="width: 16px; margin-right: 5px;" src="admin/images/dvcong-32.png">
										</s:elseif>
										<s:else>
											<img alt="Dịch vụ tư nhân" style="width: 16px; margin-right: 5px;" src="admin/images/dvtu-32.png">
										</s:else>
										<s:property value="baiViet.tenBaiViet"/>
										<s:if test="baiViet.tinhTrang=='Đăng ký'">
											<img alt="Dịch vụ mới đăng ký" src="admin/images/new-icon.png">
										</s:if>
									</td>
									<td class="td-15"> <s:property value="ngayBatDau"/> </td>
									<td class="td-15"> <s:property value="baiViet.tinhTrang"/> </td>
									<td class="td-15">
										<s:if test="baiViet.tinhTrang!='Hủy bỏ'">
											<a href="chi-tiet-dich-vu.action?idDichVu=${ idDichVu }" title="Thông tin dịch vụ" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
											<a style="cursor: pointer;" onclick="CapNhatTrangThai('${ idNhaCungCap }', 'DANGKYDICHVU', 'Hủy bỏ');" title="Hủy đăng ký dịch vụ này" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
										</s:if>
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>	
					</s:div>
					<s:div label="Nhu cầu đã đăng" id="tab2" theme="ajax">
						<table class="table sortable table-hover table-responsive">
							<thead>
								<tr>
									<th class="tieude">Nhu cầu đăng tải</th>
									<th class="ngaygio">Ngày đăng</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="listNC">
								<tr>
									<td>
										<s:if test="loaiHinhDichVu=='Từ thiện'">
											<img alt="Dịch vụ từ thiện" style="width: 16px; margin-right: 5px;" src="admin/images/tuthien-32.png">
										</s:if>
										<s:elseif test="loaiHinhDichVu=='Dịch vụ công'">
											<img alt="Dịch vụ từ thiện" style="width: 16px; margin-right: 5px;" src="admin/images/dvcong-32.png">
										</s:elseif>
										<s:else>
											<img alt="Dịch vụ từ thiện" style="width: 16px; margin-right: 5px;" src="admin/images/dvtu-32.png">
										</s:else>
										<s:property value="baiViet.tenBaiViet"/>
										<s:if test="baiViet.tinhTrang=='Mới đăng'">
											<img alt="Dịch vụ mới" src="admin/images/new-icon.png">
										</s:if>
									</td>
									<td class="td-15"> <s:property value="baiViet.ngayDang"/> </td>
									<td>Có <s:property value="baiViet.luocXem"/> báo giá</td>
									<td class="td-15">
										<s:if test="baiViet.tinhTrang=='Đăng bài'">
											<a href="chi-tiet-nhu-cau.action?idNhuCau=${ idDichVu }" title="Chi tiết nhu cầu đăng" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
										</s:if>
										<a style="cursor: pointer;" onclick="HuyDichVu('${ idDichVu }');" title="Hủy nhu cầu này" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
					</s:div>
					<s:if test="#session.user.loaiTaiKhoan!='Người dùng'">
					<s:div label="Quản lý dịch vụ đăng tải" id="tab3" theme="ajax">
						<table class="table sortable table-hover table-responsive">
							<thead>
								<tr>
									<th class="tieude">Dịch vụ</th>
									<th class="ngaygio">Ngày phát hành</th>
									<th class="ngaygio">Lược xem</th>
									<th class="ngaygio">Lược đăng ký</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="listDVDT">
								<tr>
									<td>
										<s:if test="loaiHinhDichVu=='Từ thiện'">
											<img alt="Dịch vụ từ thiện" style="width: 16px; margin-right: 5px;" src="admin/images/tuthien-32.png">
										</s:if>
										<s:elseif test="loaiHinhDichVu=='Dịch vụ công'">
											<img alt="Dịch vụ từ thiện" style="width: 16px; margin-right: 5px;" src="admin/images/dvcong-32.png">
										</s:elseif>
										<s:else>
											<img alt="Dịch vụ từ thiện" style="width: 16px; margin-right: 5px;" src="admin/images/dvtu-32.png">
										</s:else>
										<s:property value="baiViet.tenBaiViet"/>
										<s:if test="baiViet.tinhTrang=='Mới đăng'">
											<img alt="Dịch vụ mới" src="admin/images/new-icon.png">
										</s:if>
									</td>
									<td class="td-15"> <s:property value="baiViet.ngayDang"/> </td>
									<td> <s:property value="baiViet.luocXem"/> </td>
									<td> <s:property value="baiViet.idBaiViet"/> </td>
									<td class="td-15">
										<s:if test="baiViet.tinhTrang=='Đăng bài'">
											<a href="chi-tiet-dich-vu.action?idDichVu=${ idDichVu }" title="Chi tiết dịch vụ" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
										</s:if>
										<a style="cursor: pointer;" onclick="HuyDichVu('${ idDichVu }');" title="Hủy dịch vụ này" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
					</s:div>
					<s:div label="Các đăng ký mới" id="tab4" theme="ajax">
						<table class="table sortable table-hover table-responsive">
							<thead>
								<tr>
									<th class="tieude">Dịch vụ</th>
									<th class="ngaygio">Khách hàng</th>
									<th class="ngaygio">Giờ đăng ký</th>
									<th class="ngaygio">Tình trạng</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="listDKDV">
								<tr>
									<td> <s:property value="idDichVu"/> </td>
									<td> <s:property value="taiKhoan.hoTen"/> </td>
									<td class="td-15"> <s:property value="ngayDangKy"/> </td>
									<td> <s:property value="tinhTrang"/> </td>
									<td class="td-15">
										<s:if test="tinhTrang=='Đăng ký'">
											<a style="cursor: pointer;" onclick="ChapNhanDangKyDichVu('${ idDangKy}')" title="Chấp nhận đăng ký này" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
										</s:if>
										<a style="cursor: pointer;" onclick="HuyDangKyDichVu('${ idDangKy }', '${ taiKhoan.email}');" title="Hủy đăng ký này" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
					</s:div>
					</s:if>
				</s:tabbedPanel>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
	<div class="modal fade" id="xacnhan-dv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		    <div class="modal-content">
			      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title">Xác nhận dịch vụ đăng ký</h4>
			      </div>
				  <form action="chap-nhan-dang-ky-dich-vu.action" id="f-xacnhan-dk-dv" method="post"></form>
		    </div>
	  </div>
	</div>
</body>
<!-- các dịch vụ cung cấp -->
<script type="text/javascript">
	function HuyDichVu(id){
		$.ajax({
	        url : "cap-nhat.action",
	        type: "POST",
	        data : {
	        	idKey : id,
				nameTable : "DICHVU",
				chanState : "Hủy bài đăng"
	        },
	        beforeSubmit : function (){
	        	$("#loadWhile").attr("style", "display: inherit;");
	        },
	        success:function(data) 
	        {
	        	window.location.reload(true);
	        },
	        error: function(jqXHR, textStatus, errorThrown) 
	        {
	        	$("#loadWhile").attr("style", "display: none;");
	        	$('#divContentHTML').html('Lỗi hệ thống!Vui lòng thực hiện thác tác lại sau. Cám ơn!');
            	$('#thong-bao').popover('show');    
	        }
	   });
	}
</script>
<!-- các đăng ký -->
<script type="text/javascript">
	function ChapNhanDangKyDichVu(id){
		$.ajax({
	        url : "thong-tin-dang-ky-dich-vu.action",
	        type: "POST",
	        data : {
	        	idDangKy : id
	        },
	        beforeSubmit : function (){
	        	$("#loadWhile").attr("style", "display: inherit;");
	        },
	        success:function(data) 
	        {
	        	$("#loadWhile").attr("style", "display: none;");
	        	$("#f-xacnhan-dk-dv").html(data);
	        	$('#xacnhan-dv').modal('show');
	        },
	        error: function(jqXHR, textStatus, errorThrown) 
	        {
	        	$("#loadWhile").attr("style", "display: none;");
	        	$('#xacnhan-dv').modal('hide');
	        	$('#divContentHTML').html('Lỗi hệ thống!Vui lòng thực hiện thác tác lại sau. Cám ơn!');
            	$('#thong-bao').popover('show');    
	        }
	   });
	}
	function HuyDangKyDichVu(id, email){
		$.ajax({
	        url : "huy-dang-ky-dich-vu.action",
	        type: "POST",
	        data : {
	        	idDangKy : id,
	        	emailBenhNhan : email
	        },
	        beforeSubmit : function (){
	        	$("#loadWhile").attr("style", "display: inherit;");
	        },
	        success:function(data) 
	        {
	        	window.location.reload(true);
	        },
	        error: function(jqXHR, textStatus, errorThrown) 
	        {
	        	$("#loadWhile").attr("style", "display: none;");
	        	$('#divContentHTML').html('Lỗi hệ thống!Vui lòng thực hiện thác tác lại sau. Cám ơn!');
            	$('#thong-bao').popover('show');    
	        }
	   });
	}
	$(document).ready(function() {
		/* form đăng ký dịch vụ */
		$("#f-xacnhan-dk-dv").validate({
			rules : {
				idDangKy : {
					required : true
				},
				emailBenhNhan : {
					required : true,
				},
				emailNhaCungCap : {
					required : true,
				}
			},
			messages : {
				idDangKy : {
					required : "Dữ liệu không đầy đủ!"
				},
				emailBenhNhan : {
					required : "Dữ liệu không đầy đủ!"
				},
				emailNhaCungCap : {
					required : "Dữ liệu không đầy đủ!"
				}
			},
			submitHandler : function(form) {
					var postData = $(form).serializeArray();
				    var formURL = $(form).attr("action");
				    $.ajax({
				        url : formURL,
				        type: "POST",
				        data : postData,
				        beforeSubmit : function (){
				        	$("#loadWhile").attr("style", "display: inherit;");
				        },
				        success:function(data, textStatus, jqXHR) 
				        {
							window.location.reload(true);
				        },
				        error: function(jqXHR, textStatus, errorThrown) 
				        {
				        	$("#loiDangKy").attr("style", "display: none;");
				        	$('#xacnhan-dv').modal('hide');
				        	$('#divContentHTML').html('Lỗi hệ thống!Vui lòng thực hiện thác tác lại sau. Cám ơn!');
			            	$('#thong-bao').popover('show');    
				        }
				   });
			}
		});
	});
</script>
</html>
