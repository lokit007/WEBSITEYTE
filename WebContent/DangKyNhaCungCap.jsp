<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<title>Dịch vụ y tế</title>
<script type="text/javascript" src="js/jquery.MultiFile.js"></script>
<script src="ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="css/datepicker.css">
<script src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#ngayBatDau').datepicker({
			format : "dd-mm-yyyy",
			autoclose : true,
			todayBtn : true
		});
		$('#ngayKetThuc').datepicker({
			format : "dd-mm-yyyy",
			autoclose : true,
			todayBtn : true
		});
	});
</script>
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
						<i class="fa fa-hand-o-right"></i> Đăng ký nhà cung cấp <s:property value="#session.user.hoTen"/>
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home">
				<form action="dangky-ncc.action" enctype="multipart/form-data"
					method="post" id="formThemNCC">
					<br>
					<s:label value="Tài khoản" for="taiKhoan"></s:label>
					<s:textfield name="taiKhoan" value="%{ #session.user.idTaiKhoan}" readonly="true" cssClass="form-control"></s:textfield>
					<br>
					<s:label value="Giới thiệu nhà cung cấp" for="gioiThieu"></s:label><span class="sp-quantrong"> * </span>
					<s:textarea name="gioiThieu" cssClass="ckeditor"></s:textarea>
					<br>
					<!-- 
						Chứng chỉ hành nghề y, dược tư nhân có các loại sau:
						1. Chứng chỉ hành nghề y:
						    a) Chứng chỉ hành nghề khám, chữa bệnh;
						    b) Chứng chỉ hành nghề dịch vụ y tế;
						2. Chứng chỉ hành nghề y dược học cổ truyền:
							a) Chứng chỉ hành nghề khám, chữa bệnh bằng y dược học cổ truyền;
							b) Chứng chỉ hành nghề  thuốc y học cổ truyền;
						3. Chứng chỉ hành nghề dược;
						4. Chứng chỉ hành nghề vắc xin, sinh phẩm y tế.
					 -->
					
					<s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
							<s:label value="Chứng chỉ hành nghề"></s:label><span class="sp-quantrong"> * </span>
							<s:select list="{'Chứng chỉ hành nghề khám bệnh, chữa bệnh', 'Chứng chỉ hành nghề dược', 'Văn bằng y tế'}"
								headerValue="----- Chọn loại chứng chỉ hành nghê -----" headerKey=""
								name="chungChi" cssClass="form-control"></s:select>
						</s:div>
						<s:div cssClass="div-col-50">
							<s:label value="Loại nhà cung cấp"></s:label><span class="sp-quantrong"> * </span>
							<s:select list="{'Bệnh viện', 'Trung tâm y tế', 'Trạm xá', 'Y tế tư nhân'}"
								name="loaiNCC" headerKey="" headerValue="--- Chọn thể loại NCC ---"
								cssClass="form-control"
							></s:select>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</s:div>
					<br>
					<s:label value="Ảnh chụp 2 mặt chứng chỉ hành nghề" for="userImage"></s:label><span class="sp-quantrong"> * </span>
					<s:file name="userImage" cssClass="multi with-preview"></s:file>
					<br>
					<s:checkbox id="check" name="check" onclick="Xuly(1);"></s:checkbox> <label>Đăng ký hổ trợ online cho bệnh nhân</label>
					<fieldset id="khung-hotro" style="display: none;">
						<legend>Đăng ký hổ trợ online</legend>
						<s:div cssClass="div-col-100">
							<s:div cssClass="div-col-50">
								<s:label value="Thời gian hổ trợ" for="thoiGian"></s:label><span class="sp-quantrong"> * </span>
								<s:select name="thoiGian" list="{ 'Trong giờ hành chính', 'Ngoài giờ hành chính', 'Thứ 7, chủ nhật hằng tuần', 'Liên hệ mọi lúc'}"
									 headerKey="" headerValue="--- Chọn Thời Gian Phù Hợp ---" cssClass="form-control" >
								</s:select>
							</s:div>
							<s:div cssClass="div-col-50">
								<s:label value="Danh mục tư vấn" for="danhMuc"></s:label><span class="sp-quantrong"> * </span>
								<s:select name="danhMuc" list="{ 1, 2, 3}"
									 headerKey="" headerValue="--- Chọn Danh Mục ---" cssClass="form-control" >
								</s:select>
							</s:div>
							<s:div cssClass="clear"></s:div>
						</s:div>
						<s:div cssClass="div-col-100">
							<s:div cssClass="div-col-50">
								<s:label value="Điện thoại" for="dienThoaiLH"></s:label><span class="sp-quantrong"> * </span>
								<s:textfield id="dienThoaiLH" value="%{ #session.user.dienThoai}" name="dienThoaiLH" cssClass="form-control"></s:textfield>
							</s:div>
							<s:div cssClass="div-col-50">
								<s:label value="Email" for="emailLH"></s:label><span class="sp-quantrong"> * </span>
								<s:textfield id="emailLH" value="%{ #session.user.email}" name="emailLH" cssClass="form-control"></s:textfield>
							</s:div>
							<s:div cssClass="clear"></s:div>
						</s:div>
						<s:div cssClass="div-col-100">
							<s:div cssClass="div-col-50">
								<s:label value="Nick yahoo chat" for="nickYahoo"></s:label>
								<s:textfield name="nickYahoo" cssClass="form-control"></s:textfield>
							</s:div>
							<s:div cssClass="div-col-50">
								<s:label value="Nick skype chat" for="nickSkype"></s:label>
								<s:textfield name="nickSkype" cssClass="form-control"></s:textfield>
							</s:div>
							<s:div cssClass="clear"></s:div>
						</s:div>
					</fieldset>
					<br><br>
					<s:submit value="Đăng ký nhà cung cấp"
						cssClass="btn btn-primary form-control"></s:submit>
					<br>
				</form>
			</s:div>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>

<script type="text/javascript">
	function Xuly(e) {
		if(e==1){
			$("#khung-hotro").attr('style', 'display: inherit;');
			$('#check').attr('onclick', 'Xuly(0);');
		} else {
			$("#khung-hotro").attr('style', 'display: none;');
			$('#check').attr('onclick', 'Xuly(1);');
		}
	};
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
							$("#loiDangKy").attr("style", "display: inherit;");
						} else {
							$("#loiDangKy").attr("style", "display: none;");
							$('#myModal').modal('hide');
							alert("Đăng ký dịch vụ thành công.");
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
