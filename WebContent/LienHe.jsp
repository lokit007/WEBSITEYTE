<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<title>Liên hệ Online</title>
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<s:div id="div-content">
		<!-- ajax load menubar -->
		<s:div id="div-content-right">
			<!-- bắt đầu nội dung hiển thị -->	
			<s:div cssClass="div-content-home">
				<s:div cssClass="div-content-home-title">
					<s:a href="#" cssClass="lb-title-home">
						<i class="fa fa-hand-o-right"></i> Liên hệ Online
					</s:a>
				</s:div>
				<s:div id="f-timkiem-chitiet">
					<input class="f-timkiem-chitiet-control" type="text" placeholder="Tìm kiếm yêu cầu về dịch vụ">
					<s:select cssClass="f-timkiem-chitiet-control" list="{'Lĩnh vực 1', 'Lĩnh vực 2', 'Lĩnh vực 3'}" value="Dịch vụ 1"></s:select>
					<input type="submit" value="Tìm kiếm">
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:div cssClass="div-show-content-item-1">
						<span class="name-tuvan"><i class="fa fa-user-secret"></i> BV
							Quang Thắng</span>
						<br>
						<a href="tel://01202756752"><i class="fa fa-phone"></i>
							0120.2756.752</a>
						<br>
						<a href="mailto:somedayiws@gmail.com"><i class="fa fa-envelope"></i>
							somedayiws@gmail.com</a>
						<s:div>
							<a href="ymsgr:sendim?lokit007&amp;m=Xin chào"
								title="Tư vấn hỗ trợ 1"><img
								src="http://opi.yahoo.com/online?u=lokit007&amp;m=g&amp;t=2&amp;l=us"
								alt="BV Quang Thắng"> </a>
							<div id="SkypeButton_Call_hongcuong205@gmail.com_1" class="skype">
								<script type="text/javascript">
									Skype
											.ui({
												"name" : "dropdown",
												"element" : "SkypeButton_Call_hongcuong205@gmail.com_1",
												"participants" : [ "hongcuong205@gmail.com" ],
												"imageSize" : 24
											});
								</script>
							</div>
							<s:div cssClass="clear"></s:div>
						</s:div>
					</s:div>
					<s:div cssClass="div-show-content-item-1">
						<span class="name-tuvan"><i class="fa fa-user-secret"></i> BV
							Quang Thắng</span>
						<br>
						<a href="tel://01202756752"><i class="fa fa-phone"></i>
							0120.2756.752</a>
						<br>
						<a href="mailto:somedayiws@gmail.com"><i class="fa fa-envelope"></i>
							somedayiws@gmail.com</a>
						<a href="ymsgr:sendim?lokit007&amp;m=Xin chào"
							title="Tư vấn hỗ trợ 1"><img
							src="http://opi.yahoo.com/online?u=lokit007&amp;m=g&amp;t=2&amp;l=us"
							alt="BV Quang Thắng"> </a>
						<div id="SkypeButton_Call_ho.nhan92_1" class="skype">
							<script type="text/javascript">
								Skype.ui({
									"name" : "dropdown",
									"element" : "SkypeButton_Call_ho.nhan92_1",
									"participants" : [ "ho.nhan92" ],
									"imageSize" : 24
								});
							</script>
						</div>
						<s:div cssClass="clear"></s:div>
					</s:div>	
					
					<s:div id="continue" cssClass="clear continue">
						<button class="btn btn-primary" onclick="XemThem();">Xem thêm ...</button>
					</s:div>
				</s:div>
			</s:div>
			<!-- kết thúc content -->
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:include value="files/Footer.jsp"></s:include>
</body>
<script type="text/javascript">
	function XemThem(){
		var them = "<div class='div-content-home-main-tin'><a href='#'><img src='images/bridge.jpg' class='img-thumbnail'><div><label>Chương trình sức khỏe cộng đồng</label><p>Để chủ động trong công tác phòng chống dịch, đặc biệt đối với các dịch bệnh mới nổi, Trung tâm Y tế dự phòng...</p></div></a></div>";
		$("#continue").before(them);
	}
</script>
</html>
