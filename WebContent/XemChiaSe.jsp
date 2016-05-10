<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="files/ThuVien.jsp"></s:include>
<s:head theme="ajax" debug="true"/>
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
						<i class="fa fa-hand-o-right"></i> <s:property value="baiViet.tenBaiViet"/>
					</s:a>
				</s:div>
				<s:div cssClass="div-content-home-main-info">
					<span title="Thông tin bài viết : Tác giả - Ngày đăng - Lượt xem">
								<i class="fa fa-user"></i> <s:property value="baiViet.tenTacGia"/> 
								| <i class="fa fa-calendar-check-o"></i> <s:property value="baiViet.ngayDang"/> 
								| <i class="fa fa-globe"></i> <s:property value="baiViet.luocXem"/> </span>
				</s:div>
				<s:div cssClass="div-content-home-main">
					<s:property value="baiViet.noiDung" escape="false"/>
					<!-- Tab panes -->
					<br><br>
					<s:tabbedPanel id="binhluan">
						<s:div id="facebook" label="Bình luận với facebook" theme="ajax">
							<script>(function(d, s, id) {
								  var js, fjs = d.getElementsByTagName(s)[0];
								  if (d.getElementById(id)) return;
								  js = d.createElement(s); js.id = id;
								  js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.5&appId=390168204513449";
								  fjs.parentNode.insertBefore(js, fjs);
								}(document, 'script', 'facebook-jssdk'));
							</script>
							<div class="fb-comments" id="123" data-href="https://www.facebook.com/BKwood-875608875867712/" data-numposts="5" width="100%"></div>
						</s:div>
						<s:div id="google" label="Bình luận với google" theme="ajax">
							<!-- bình luận google -->
							 <div id="google_comments"><img src="/images/loading8.gif" alt="loading" /> Đang tải...</div>
							 <script src="https://apis.google.com/js/plusone.js" type="text/javascript">{lang: 'vi'}</script>
							 <script>gapi.comments.render('google_comments',{href:window.location.href,width: 680,first_party_property: 'BLOGGER',view_type: 'FILTERED_POSTMOD'});
							       function fix_google()
							       {
							          if($("#google_comments").length>0) $("#google_comments").css({"width":"100%"});
							       }
							</script>
						</s:div>
					</s:tabbedPanel>
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
