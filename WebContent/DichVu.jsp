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
						<i class="fa fa-hand-o-right"></i> Dịch vụ hot
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:iterator value="listHot">
						<s:div cssClass="div-content-home-main-dv">
							<s:a href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}">
								<img alt="" src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
								<s:div cssClass="div-btn">
									<span class="spdk">Đăng ký</span>
									<span class="spct">Chi tiết</span>
								</s:div>
								<s:div>
									<s:label cssClass="lb-link-title" value="%{ baiViet.tenBaiViet}"></s:label>
									<p><s:property value="baiViet.moTa"/> </p>
								</s:div>
							</s:a>
						</s:div>
					</s:iterator>
					<s:div cssClass="clear"></s:div>
				</s:div>
			</s:div>
			<s:div cssClass="div-quangcao">
				<img src="http://placehold.it/970x90">
			</s:div>
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a cssClass="lb-title-home">
						<i title="Tìm kiếm nâng cao" class="fa fa-search-plus" onclick="showSearch(this, 1);"></i> Các dịch vụ khác
					</s:a>
				</s:div>
				<form id="f-timkiem-chitiet" action="dich-vu.action" method="post" style="display: none;">
					<input class="f-timkiem-chitiet-control" id="txtFind" name="txtFind" value="${ txtFind }" type="text" placeholder="Tìm kiếm dịch vụ">
					<s:select cssClass="f-timkiem-chitiet-control" id="idDanhMuc" name="idDanhMuc" value="idDanhMuc"
						list="danhMuc" listKey="idDanhMuc" listValue="tenDanhMuc"
						headerKey="" headerValue="---------- Tất cả ----------" >
					</s:select>
					<input type="submit" value="Tìm kiếm">
				</form>
				<s:div cssClass="div-content-home-main">
					<s:iterator value="list" >
						<s:div cssClass="div-content-home-main-tin">
							<s:a href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}">
								<img alt="" src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
								<s:div>
									<s:label value="%{ baiViet.tenBaiViet}"></s:label>
									<span title="Thông tin dịch vụ : Nhà cung cấp - Hạn đăng ký">
										<i class="fa fa-user-md" title="Nhà cung cấp dịch vụ"></i> <s:property value="baiViet.tenTacGia" /> 
										| <i class="fa fa-calendar-times-o" title="Hết hạn ngày"></i> <s:property value="ngayKetThuc" /></span>
									<p><s:property value="baiViet.moTa"/></p>
								</s:div>
							</s:a>
						</s:div>
					</s:iterator>
					
					<s:div id="continue" cssClass="clear continue">
						<button class="btn btn-primary" onclick="XemThem();"><i style="display: none;" class="fa fa-spinner fa-spin" id="loadmore"></i> Xem thêm ...</button>
					</s:div>
				</s:div>
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
	function XemThem(){
		$.ajax({
			url : "dich-vu-them.action",
	        type: "POST",
	        data : {
	        	txtFind : $('#txtFind').val(),
	        	idDangMuc : $('#idDangMuc').val(),
	        	viTri : $(".div-content-home-main-tin").size()
	        },
	        beforeSubmit : function (){
	        	$("#loadmore").attr("style", "display: inherit;");
	        },
	        success:function(data, textStatus, jqXHR) 
	        {
	        	$("#loadmore").attr("style", "display: none;");
	        	$("#continue").before(data);
	        },
	        error: function(jqXHR, textStatus, errorThrown) 
	        {
	        	$("#loadmore").attr("style", "display: none;");
	            alert("Lỗi");    
	        }
		});
	}
</script>
</html>
