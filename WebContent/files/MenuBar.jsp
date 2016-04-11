<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:div id="div-content-left">
	<s:div cssClass="div-show">
		<s:label cssClass="div-show-title" value="Bài viết được quan tâm"></s:label>
		<s:div cssClass="div-show-content div-slide">
			<s:iterator value="listBVQT">
				<s:div cssClass="div-show-content-item">
					<s:a href="bai-viet.action?idBaiViet=%{ idBaiViet}"> <s:property value="tenBaiViet" /> </s:a>
					<br>
					<span class="date-time"> <i class="fa fa-calendar"></i>
						<s:property value="ngayDang" /> <i class="fa fa-globe"></i> <s:property value="luocXem" />
					</span>
				</s:div>
			</s:iterator>			
		</s:div>
	</s:div>
	<s:div cssClass="div-quangcao">
		<img src="http://placehold.it/300x250">
	</s:div>
	<s:div cssClass="div-show">
		<s:label cssClass="div-show-title" value="Dịch vụ mới nhất"></s:label>
		<s:div cssClass="div-show-content">
			<s:iterator value="listDVM">
				<s:div cssClass="div-show-content-item-1">
					<s:div cssClass="date-show">
						<span class="ngay-thanh"> <s:property value="ngayBatDau.substring(0,5)"/> </span>
						<br>
						<span class="nam"> <s:property value="ngayBatDau.substring(6,10)"/> </span>
					</s:div>
					<s:a href="chi-tiet-dich-vu.action?idDichVu=%{ idDichVu}"> <s:property value="baiViet.tenBaiViet" /> </s:a>
					<br>
					<span class="date-time"> <i class="fa fa-calendar"></i>
						<s:property value="baiViet.ngayDang" />
					</span>
					<s:div cssClass="clear"></s:div>
				</s:div>
			</s:iterator>
		</s:div>
	</s:div>
	<s:div cssClass="div-show">
		<s:label cssClass="div-show-title" value="Hỗ trợ online"></s:label>
		<s:div cssClass="div-show-content">
			<s:iterator value="listHT">
				<s:div cssClass="div-show-content-item-1">
					<span class="name-tuvan"><i class="fa fa-user-secret"></i> <s:property value="nhaCungCap" /> </span>
					<br>
					<a href="tel://${ dienThoaiLienHe }"><i class="fa fa-phone"></i>
						<s:property value="dienThoaiLienHe"/> </a>
					<br>
					<a href="mailto:${ emailLienHe }"><i class="fa fa-envelope"></i>
						<s:property value="emailLienHe"/> </a>
					<s:div>
						<a href="ymsgr:sendim?${ nickYahooChat }&amp;m=Xin chào"
							title="${ nickYahooChat }"><img
							src="http://opi.yahoo.com/online?u=${ nickYahooChat }&amp;m=g&amp;t=2&amp;l=us"
							alt="${ nickYahooChat }"> </a>
						<div id="SkypeButton_Call_${ nickSkypeChat }_1" class="skype">
							<script type="text/javascript">
								Skype.ui({
									"name" : "dropdown",
									"element" : "SkypeButton_Call_${ nickSkypeChat}_1",
									"participants" : [ "${ nickSkypeChat}" ],
									"imageSize" : 24
								});
							</script>
						</div>
						<s:div cssClass="clear"></s:div>
					</s:div>
				</s:div>
			</s:iterator>
		</s:div>
	</s:div>
	<s:div cssClass="div-show">
		<s:label cssClass="div-show-title" value="Liên kết khác"></s:label>
		<s:div cssClass="div-show-content" cssStyle="text-align: center;">
			<s:a href="#" cssClass="link-lienket">
				<img src="images/cong-thong-tin-bo-y-te.jpg">
			</s:a>
			<s:a href="#" cssClass="link-lienket">
				<img src="images/icon_kcb_byt(1).png">
			</s:a>
			<s:a href="#" cssClass="link-lienket">
				<img src="images/icon_ktbv_ byt(1).png">
			</s:a>
			<s:a href="#" cssClass="link-lienket">
				<img src="images/cuc-quan-ly-duoc.jpg">
			</s:a>
			<s:a href="#" cssClass="link-lienket">
				<img src="images/cuc-kham-chua-benh.jpg">
			</s:a>
		</s:div>
	</s:div>
	<s:div cssClass="div-show">
		<s:label cssClass="div-show-title" value="Thông tin ngoài lề"></s:label>
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
	</s:div>
</s:div>

