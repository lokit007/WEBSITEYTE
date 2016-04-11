<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<title>Chia sẻ kiến thức y tế</title>
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
						<i title="Tìm kiếm nâng cao" class="fa fa-search-plus" onclick="showSearch(this, 1);"></i> Các bài viết chia sẻ mới nhất
					</s:a>
				</s:div>
				<form id="f-timkiem-chitiet" action="chia-se.action" method="post" style="display: none;">
					<input class="f-timkiem-chitiet-control" id="txtFind" name="txtFind" value="${ txtFind }" type="text" placeholder="Tìm kiếm bài viết chia sẻ">
					<s:select cssClass="f-timkiem-chitiet-control" id="idDanhMuc" name="idDanhMuc" value="idDanhMuc"
						list="danhMuc" listKey="idDanhMuc" listValue="tenDanhMuc"
						headerKey="" headerValue="---------- Tất cả ----------" >
					</s:select>
					<input type="submit" value="Tìm kiếm">
				</form>
				<s:div cssClass="div-content-home-main">
					<s:iterator value="list" >
						<s:div cssClass="div-content-home-main-tin">
							<s:a href="bai-viet.action?idBaiViet=%{ idBaiViet}">
								<img alt="" src="images/${ anhMoTa }" class="img-thumbnail">
								<s:div>
									<s:label value="%{ tenBaiViet}"></s:label>
									<span title="Thông tin dịch vụ : Tác giả - Hạn đăng ký - Lượt xem">
										<i class="fa fa-user-md" title="Tác giả"></i> <s:property value="tenTacGia" /> 
										| <i class="fa fa-calendar-times-o" title="Ngày đăng"></i> <s:property value="ngayDang" />
										| <i class="fa fa-globe"></i> <s:property value="luocXem" /> </span>
									<p><s:property value="moTa"/></p>
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
		url : "chia-se-them.action",
        type: "POST",
        data : {
        	txtFind : $('#txtFind').val(),
        	idDanhMuc : $('#idDangMuc').val(),
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
