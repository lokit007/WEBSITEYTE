<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head theme="ajax" debug="true"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<title>Dịch vụ y tế</title>
<link rel="stylesheet" href="css/star-rating.min.css">
<script src="js/star-rating.min.js"></script>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Trang cá nhân <s:property value="#session.user.hoTen"/>
					</s:a>
				</s:div>
				<div class="jumbotron" style="padding: 10px 30px;" id="div-dangky-ncc">
				  <h1>Chào <s:property value="#session.user.hoTen"/>! </h1>
				  <p>Hiện tại tài khoản của bạn là tài khoản thông thường. Nếu bạn là các tổ chức y tế, bác sỹ, y tá ... hoặc là các 
				  nhân tổ chức hoạt động trong lĩnh vực y tế. Hãy đăng ký tài khoản nhà cung cấp để có thể quảng bá các dịch vụ y
				  tế của mình một cách hiệu quả nhất đến bệnh nhân. Sở y tế đảm bảo quyền và lợi ích về dịch vụ của bạn.</p>
				  <p><a class="btn btn-primary btn-lg" href="dang-ky-ncc.action" role="button">Đăng ký nhà cung cấp</a></p>
				</div>
				<s:div cssClass="div-content-home-main">
				<form id="f-capnhat-thongtin" action="capnhat-canhan.action" method="post">
					<fieldset>
			  			<legend>Thông tin cá nhân</legend>
			  			<s:hidden name="idTaiKhoan" value="%{ #session.user.idTaiKhoan}"></s:hidden>
			  			<s:label value="Cá nhân/Tổ chức : " for="hoTen"></s:label><span class="sp-quantrong"> * </span>
				  		<s:textfield name="hoTen" value="%{ #session.user.hoTen}" cssClass="form-control"></s:textfield>
				  		<br><s:label value="Địa chỉ : " for="diaChi"></s:label><span class="sp-quantrong"> * </span>
				  		<s:textfield name="diaChi" value="%{ #session.user.diaChi}" cssClass="form-control"></s:textfield>
				  		<s:div cssClass="div-col-100">
					  		<s:div cssClass="div-col-50">
						  		<br><s:label value="Điện thoại liên hệ : " for="dienThoai"></s:label><span class="sp-quantrong"> * </span>
						  		<s:textfield name="dienThoai" value="%{ #session.user.dienThoai}" cssClass="form-control"></s:textfield>
					  		</s:div>
					  		<s:div cssClass="div-col-50">
						  		<br><s:label value="Email : " for="email"></s:label><span class="sp-quantrong"> * </span>
						  		<s:textfield name="email" value="%{ #session.user.email}" cssClass="form-control"></s:textfield>
					  		</s:div>
					  		<s:div cssClass="clear"></s:div>
				  		</s:div>
				  		<s:div cssClass="div-col-100">
					  		<s:div cssClass="div-col-50">
						  		<br><s:label value="Mật khẩu mới : " for="matKhauMoi"></s:label>
						  		<s:textfield id="matKhauMoi" name="matKhauMoi" cssClass="form-control"></s:textfield>
					  		</s:div>
					  		<s:div cssClass="div-col-50">
						  		<br><s:label value="Xác nhận mật khẩu mới : " for="matKhauXacNhan"></s:label>
						  		<s:textfield name="matKhauXacNhan" cssClass="form-control"></s:textfield>
					  		</s:div>
					  		<s:div cssClass="clear"></s:div>
				  		</s:div>
				  		<br><s:submit value="Cập nhật thông tin" cssClass="form-control btn btn-success"></s:submit>
			  		</fieldset>
			  	</form>
				</s:div>
			</s:div>
			<s:div cssClass="div-quangcao">
				<img src="http://placehold.it/970x90">
			</s:div>
			<s:div cssClass="div-content-home">
				<s:tabbedPanel id="tabContainer">
					<s:div label="Dịch vụ Sử dụng" id="tab1" theme="ajax">
						<table class="table sortable table-hover table-responsive">
							<thead>
								<tr>
									<th class="tieude" colspan="2">Dịch vụ</th>
									<th class="ngaygio">Ngày đăng ký</th>
									<th>Tình trạng xử lý</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="listDV">
								<tr>
									<td>
										<s:if test="loaiHinhDichVu=='Từ thiện'">
											<img alt="Dịch vụ từ thiện" src="admin/images/tuthien-32.png">
										</s:if>
										<s:elseif test="loaiHinhDichVu=='Dịch vụ công'">
											<img alt="Dịch vụ từ thiện" src="admin/images/dvcong-32.png">
										</s:elseif>
										<s:else>
											<img alt="Dịch vụ từ thiện" src="admin/images/dvtu-32.png">
										</s:else>
									</td>
									<td>
										<s:property value="baiViet.tenBaiViet"/>
										<s:if test="baiViet.tinhTrang=='Mới đăng'">
											<img alt="Dịch vụ mới" src="images/new-icon.png">
										</s:if>
									</td>
									<td class="td-15"> <s:property value="ngayBatDau"/> </td>
									<td class="td-15">
										<a href="chi-tiet-dich-vu.action?idDichVu=${ idDichVu }" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
										<a href="thong-tin-dich-vu.action?idDichVu=${ idDichVu }" class="btn-update btn-thaotac"><i class="fa fa-pencil-square-o"></i></a>
										<a href="#" onclick="XoaDichVu('${ idDichVu }');" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
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
									<th class="tieude" colspan="2">Nhu cầu đăng tải</th>
									<th class="ngaygio">Ngày đăng</th>
									<th>Tình trạng xử lý</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="listNC">
								<tr>
									<td>
										<s:if test="loaiHinhDichVu=='Từ thiện'">
											<img alt="Dịch vụ từ thiện" src="admin/images/tuthien-32.png">
										</s:if>
										<s:elseif test="loaiHinhDichVu=='Dịch vụ công'">
											<img alt="Dịch vụ từ thiện" src="admin/images/dvcong-32.png">
										</s:elseif>
										<s:else>
											<img alt="Dịch vụ từ thiện" src="admin/images/dvtu-32.png">
										</s:else>
									</td>
									<td>
										<s:property value="baiViet.tenBaiViet"/>
										<s:if test="baiViet.tinhTrang=='Mới đăng'">
											<img alt="Dịch vụ mới" src="images/new-icon.png">
										</s:if>
									</td>
									<td class="td-15"> <s:property value="baiViet.ngayDang"/> </td>
									<td class="td-15">
										<a href="chi-tiet-dich-vu.action?idDichVu=${ idDichVu }" class="btn-check btn-thaotac"><i class="fa fa-check"></i></a>
										<a href="thong-tin-dich-vu.action?idDichVu=${ idDichVu }" class="btn-update btn-thaotac"><i class="fa fa-pencil-square-o"></i></a>
										<a href="#" onclick="XoaDichVu('${ idDichVu }');" class="btn-delete btn-thaotac"><i class="fa fa-times"></i></a>
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>	
					</s:div>
				</s:tabbedPanel>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:div cssClass="div-quangcao">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
		<img src="http://placehold.it/234x60">
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#f-capnhat-thongtin").validate({
			rules : {
				hoTen : {
					required : true
				},
				dienThoai : {
					required : true,
					digits : true
				},
				diaChi : {
					required : true
				},
				email : {
					required : true,
					email : true
				},
				matKhauXacNhan : {
					equalTo : '#matKhauMoi'
				}
			},
			messages : {
				hoTen : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				},
				dienThoai : {
					required : "Bạn chưa nhập dữ liệu cho trường này!",
					digits : "Không phải số điện thoại!"
				},
				diaChi : {
					required : "Bạn chưa nhập dữ liệu cho trường này!"
				},
				email : {
					required : "Bạn chưa nhập dữ liệu cho trường này!",
					email : "Không phải định dạng email!"
				},
				matKhauXacNhan : {
					equalTo : 'Mật khẩu xác nhận không chính xác!'
				}
			},
			submitHandler : function(form) {
				var postData = $(form).serializeArray();
				var formURL = $(form).attr("action");
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					beforeSubmit : function() {
						$("#loadWhile").attr("style", "display: inherit;");
					},
					success : function(data, textStatus, jqXHR) {
						if (data.indexOf("thất bại") > -1) {
							alert(data);
						} else {
							alert(data);
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$("#loiDangKy").attr("style", "display: none;");
						$('#myModal').modal('hide');
						alert("Lỗi");
					}
				});
			}
		});
	});
</script>
</html>
