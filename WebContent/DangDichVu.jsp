<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
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
			    	<button class="btn btn-danger btn-xs" onclick="XoaPhanTu();" style="float: right; margin-top: 5px;">Ok</button>
			    </s:div>
			</s:if>
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Đăng ký phát hành dịch vụ mới 
					</s:a>
				</s:div>
				<s:if test="#session.user.loaiTaiKhoan=='Nhà cung cấp'">
				<form action="dang-dich-vu.action" enctype="multipart/form-data"
					method="post" id="formDichVu">
					<br>
					<s:label value="Tên dịch vụ" for="tenDichVu"></s:label>
					<s:textfield name="tenDichVu" cssClass="form-control"></s:textfield>
					<br>
					<s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
							<s:label value="Danh mục" for="danhMuc"></s:label>
							<s:select name="danhMuc" list="list" listValue="tenDanhMuc"
								listKey="idDanhMuc" cssClass="form-control" headerKey="-1"
								headerValue="--- Chọn Danh Mục ---">
							</s:select>
						</s:div>
						<s:div cssClass="div-col-50">
							<s:label value="Loại hình dịch vụ" for="loaiHinh"></s:label>
							<s:select name="loaiHinh" list="{ 'Từ thiện', 'Dịch vụ công', 'Dịch vụ tư'}"
								 headerKey="" headerValue="--- Chọn loại hình ---" cssClass="form-control" >
							</s:select>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</s:div>
					<br>
					<s:label value="Mô tả" for="moTa"></s:label>
					<s:textarea name="moTa" cssClass="form-control"></s:textarea>
					<br>
					<s:label value="Nội dung" for="noiDung"></s:label>
					<s:textarea name="noiDung" cssClass="ckeditor"></s:textarea>
					<br>
					<s:label value="Hình ảnh" for="hinhAnh"></s:label>
					<s:file name="userImage" cssClass="multi with-preview"></s:file>
					<br>
					<s:label value="Nhà cung cấp" for="nhaCungCap"></s:label>
					<s:textfield name="nhaCungCap" cssClass="form-control" value="%{ #session.user.hoTen}"></s:textfield>
					<br>
					<s:label value="Địa điểm triển khai dịch vụ" for="diaDiem"></s:label>
					<s:textfield name="diaDiem" cssClass="form-control" value="%{ #session.user.diaChi}"></s:textfield>
					<br>
					<s:div cssClass="div-col-100">
						<s:div cssClass="div-col-50">
							<s:label value="Điện thoại" for="dienThoai"></s:label>
							<s:textfield name="dienThoai" cssClass="form-control" value="%{ #session.user.dienThoai}"></s:textfield>
							<br>
							<s:label value="Ngày bắt đầu" for="ngayBatDau"></s:label>
							<s:textfield name="ngayBatDau" id="ngayBatDau"
								cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="div-col-50">
							<s:label value="Email" for="email"></s:label>
							<s:textfield name="email" cssClass="form-control" value="%{ #session.user.email}"></s:textfield>
							<br>
							<s:label value="Ngày kết thúc" for="ngayKetThuc"></s:label>
							<s:textfield name="ngayKetThuc" id="ngayKetThuc"
								cssClass="form-control"></s:textfield>
						</s:div>
						<s:div cssClass="clear"></s:div>
					</s:div>
					<br>
					<fieldset>
						<legend style="background-color: #D61515; padding: 5px; color: white; font-weight: bold; border-radius: 5px;">
							<input type="checkbox" name="dongY" checked="checked">
							Tôi đồng ý với quy định của sở y tế
						</legend>
						<p style="max-height: 250px;overflow-x: auto;">
							- Luật Khám bệnh, chữa bệnh số 40/2009/QH12 Ngày 23/11/2009 <br>-
							Nghị định số 87/2011/NĐ-CP của Chính phủ ngày 27/09/2011 quy định
							chi tiết và thi hành một số điều của Luật khám bệnh chữa bệnh. <br>-
							Thông tư số 41/2011/TT-BYT ngày 14/11/2011 của Bộ Y tế hướng dẫn
							cấp chứng chỉ hành nghề đối với người hành nghề và cấp giấy phép
							hoạt động đối với cơ sở khám bệnh, chữa bệnh. <br>- Thông tư
							liên tịch số 08/2007/TTLT- BYT- BNV ngày 05 tháng 6 năm 2007 của
							liên Bộ: Bộ Y tế - Bộ Nội vụ hướng dẫn định mức biên chế sự
							nghiệp trong các cơ sở y tế nhà nước. <br>- Quyết định số
							2271/2002/QĐ - BYT ngày 17 tháng 6 năm 2002 của Bộ trưởng Bộ Y tế
							về việc ban hành Tiêu chuẩn thiết kế Trạm y tế cơ sở - Tiêu chuẩn
							ngành. <br>- Thông tư số 03/2012/TT-BTC ngày 08/01/2013 của
							Bộ Tài chính quy định phí thẩm định kinh doanh thương mại có điều
							kiện; thẩm định tiêu chuẩn, điều kiện hành nghề y, dược; lệ phí
							cấp giấy phép xuất, nhập khẩu trang thiết bị y tế, dược phẩm; cấp
							chứng chỉ hành nghề y; cấp giấy phép hoạt động đối với cơ sở
							khám, chữa bệnh.
						</p>
					</fieldset>
					<br>
					<s:submit value="Đăng dịch vụ miễn phí"
						cssClass="btn btn-primary form-control"></s:submit>
				</form>
				</s:if>
				<s:elseif test="#session.user!=null">
					Bạn không phải nhà cung cấp dịch vụ. Xin vui lòng <s:a href="dangky-ncc.action"> đăng ký nhà cung cấp tại đây </s:a>.
				</s:elseif>
				<s:else>
					Bạn không có quyền hạn thực hiện chức năng này. Hãy <label style="cursor: pointer;" onclick="$('#dangNhapModal').modal('show');"> 
					đăng nhập tài khoản nhà cung cấp </label> để thực hiện chức năng đăng ký cấp phát dịch vụ.
				</s:else>
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
	/* 
	$(function() {
		$('.datepicker').datepicker({
			format : "yyyy-mm-dd"
		}).on('changeDate', function(ev) {
			$(this).datepicker('hide');
		});
	}); */
	$(document).ready(function() {
		$("#formDichVu").validate({
			rules : {
				tenDichVu : {
					required : true
				},
				moTa : {
					required : true
				},
				danhMuc : {
					required : true	
				},
				loaiHinh : {
					required : true
				},
				noiDung : {
					required : true
				},
				nhaCungCap : {
					required : true
				},
				diaDiem : {
					required : true
				},
				dienThoai : {
					required : true
				},
				email : {
					required : true,
					email : 5
				},
				ngayBatDau : "required",
				ngayKetThuc : "required"
			},
			messages : {
				tenDichVu : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				moTa : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				danhMuc : {
					required : "Chưa chọn danh mục dịch vụ!"	
				},
				loaiHinh : {
					required : "Chưa chọn loại hình dịch vụ!"
				},
				noiDung : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				nhaCungCap : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				diaDiem : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				dienThoai : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!"
				},
				email : {
					required : "Chưa nhập đầy đủ thông tin dịch vụ!",
					email : "Dữ liệu quá dài!"
				},
				ngayBatDau : "Chưa nhập đầy đủ thông tin dịch vụ!",
				ngayKetThuc : "Chưa nhập đầy đủ thông tin dịch vụ!"
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
	function XoaPhanTu(){
		$("#showError").remove();
	}
</script>
</html>
