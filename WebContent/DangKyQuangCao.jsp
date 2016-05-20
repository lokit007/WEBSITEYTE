<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<link rel="stylesheet" href="css/datepicker.css">
<script src="js/jquery.MultiFile.js" type="text/javascript"></script>
<script src="ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/validate-form.js" type="text/javascript"></script>
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
<title>Đăng ký phát hành dịch vụ</title>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->
			<s:if test="hasActionErrors()">
				<s:div id="showError">
			    	<s:actionerror/>
			    	<button class="btn btn-danger btn-xs" onclick="XoaPhanTu('showError');" style="float: right; margin-top: 5px;">Ok</button>
			    </s:div>
			</s:if>
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Đăng ký quảng cáo online 
					</s:a>
				</s:div>
				<form action="dangky-quangcao.action" enctype="multipart/form-data"
					method="post" id="formQuangCao">
					<s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
							<br>
							<s:label value="Liên hệ quản trị viên"></s:label>
							<br><br>
							<s:div cssClass="lienhe">
								<span class="name-tuvan"><i class="fa fa-user-secret"></i> Nguyễn Hồng Cương </span>
								<br>
								<a href="tel://01202756752"><i class="fa fa-phone"></i> 0120.2756.752</a> |
								<a href="mailto:hongcuong@gmail.com"><i class="fa fa-envelope"></i> hongcuong@gmail.com</a>
								<br>
								<s:div>
									<a href="ymsgr:sendim?hongcuong&amp;m=Xin chào"
										title="hongcuong"><img
										src="http://opi.yahoo.com/online?u=hongcuong&amp;m=g&amp;t=2&amp;l=us"
										alt="hongcuong"> </a>
									<div id="SkypeButton_Call_tranvietnghia.bkdn_1" class="skype">
										<script type="text/javascript">
											Skype.ui({
												"name" : "dropdown",
												"element" : "SkypeButton_Call_tranvietnghia.bkdn_1",
												"participants" : [ "tranvietnghia.bkdn" ],
												"imageSize" : 24
											});
										</script>
									</div>
									<s:div cssClass="clear"></s:div>
								</s:div>
							</s:div>
							<s:div id="banggia">
								<img src="http://placehold.it/300x100">
								<br><br><br><s:label value="Bản giá quảng cáo"></s:label>
								<table class="table table-bordered table-striped table-condensed">
									<tr>
										<th>Vị trí</th>
										<th>Giá thành(VND)/Tháng</th>
									</tr>
									<tr>
										<td>TopMenu</td>
										<td>10000</td>
									</tr>
									<tr>
										<td>MenuBar</td>
										<td>33000</td>
									</tr>
									<tr>
										<td>Content</td>
										<td>25000</td>
									</tr>
									<tr>
										<td>FooterBar</td>
										<td>30000</td>
									</tr>
								</table>
							</s:div>
						</s:div>
						<s:div cssClass="div-col-50">
							<br>
							<s:label value="Vị trí quảng cáo"></s:label> (<span class="sp-quantrong">*</span>)
							<s:select list="{'TopMenu', 'MenuBar', 'Content', 'FooterBar'}" 
								cssClass="form-control"
								name="viTri"
								onchange="HienThiThongTin(this);"
								></s:select>
							<s:div id="thongtin-vitri">
								TopMenu là vị trí đầu tiên trên thẻ header
								Kích thước ảnh 350x100.
							</s:div>
							<br>
							<s:label value="Logo quảng bá của bạn"></s:label> (<span class="sp-quantrong">*</span>)
							<s:file id="photoInput" name="userImage"></s:file>
							<img id="img-view" style="max-height: 100px; max-width: 300px; margin-top: 5px;">
							<br>
							<s:label value="Địa chỉ cần quảnh bá"></s:label> (<span class="sp-quantrong">*</span>)
							<s:textfield name="linkQuangBa" cssClass="form-control"></s:textfield>
							<br>
							<s:label value="Cá nhân/Tổ chức"></s:label> (<span class="sp-quantrong">*</span>)
							<s:textfield name="hoTen" cssClass="form-control"></s:textfield>
							<br>
							<s:label value="Địa chỉ khách hàng"></s:label> (<span class="sp-quantrong">*</span>)
							<s:textfield name="diaChi" cssClass="form-control"></s:textfield>
							<br>
							<s:label value="Email liên hệ"></s:label> (<span class="sp-quantrong">*</span>)
							<s:textfield name="email" cssClass="form-control"></s:textfield>
							<br>
							<s:submit value="Đăng ký quảng cáo lên hệ thống"
							cssClass="btn btn-primary form-control"></s:submit>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</s:div>
				</form>
			</s:div>
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>
<script type="text/javascript">
	function XoaPhanTu(idelement){
		$("#"+idelement).remove();
	}
	var width = 350;
	var height = 100;
	var vitri = 'TopMenu';
	function HienThiThongTin(e){
		vitri = $(e).val();
		if(vitri=='TopMenu'){
			$("#thongtin-vitri").html("Vị trí TopMenu là vị trí ở góc bên phải của header. Có kích thướng ảnh là 350x100. Vị trí này luôn hiển thị ở các trang của hệ thống.");
			width = 350;
			height = 100;
		} else if(vitri=='MenuBar'){
			$("#thongtin-vitri").html("Vị trí MenuBar một hình ảnh ở giữa menubar. Có kích thướng ảnh là 300x250. Vị trí này luôn hiển thị ở các trang của hệ thống.");
			width = 300;
			height = 250;
		} else if(vitri=='Content'){
			$("#thongtin-vitri").html("Vị trí Content là vị trí ở chính giữa nội dung chính của trang chủ và mốt số trang khác. Có kích thướng ảnh là 970x90. Tại vị trí này thì thường được người dùng chú ý hơn.");
			width = 970;
			height = 90;
		} else {
			$("#thongtin-vitri").html("Vị trí FooterBar là các quảng cáo ở cuối nội dung của trang. Có kích thướng ảnh là 234x60. Vị trí này luôn hiển thị ở các trang của hệ thống.");
			width = 234;
			height = 60;
		}
	}
	var _URL = window.URL;
	$("#photoInput").change(function (e) {
	    var file, img;
	    if ((file = this.files[0])) {
	        img = new Image();
	        img.onload = function () {
	            if((this.width/this.height)==(width/height))
	            	$("#img-view").attr('src', _URL.createObjectURL(file));
	            else {
	            	$("#photoInput").val("");
	            	alert("Kích thước file không phù hợp!\nCần chọn image có khích thước "+width+"x"+height+". Hoặc có tỷ lệ tương đương.");
	            }
	        };
	        img.src = _URL.createObjectURL(file);
	    }
	});
</script>
</html>
