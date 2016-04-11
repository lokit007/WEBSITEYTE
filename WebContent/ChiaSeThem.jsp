<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:iterator value="list">
	<s:div cssClass="div-content-home-main-tin">
		<s:a href="bai-viet.action?idBaiViet=%{ idBaiViet}">
			<img alt="" src="images/${ anhMoTa }" class="img-thumbnail">
			<s:div>
				<s:label value="%{ tenBaiViet}"></s:label>
				<span title="Thông tin dịch vụ : Tác giả - Hạn đăng ký - Lượt xem">
					<i class="fa fa-user-md" title="Tác giả"></i> <s:property
						value="tenTacGia" /> | <i class="fa fa-calendar-times-o"
					title="Ngày đăng"></i> <s:property value="ngayDang" /> | <i
					class="fa fa-globe"></i> <s:property value="luocXem" />
				</span>
				<p>
					<s:property value="moTa" />
				</p>
			</s:div>
		</s:a>
	</s:div>
</s:iterator>