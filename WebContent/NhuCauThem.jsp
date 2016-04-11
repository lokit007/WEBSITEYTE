<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator value="list">
	<s:div cssClass="div-content-home-main-tin">
		<s:a href="chi-tiet-nhu-cau.action?idNhuCau=%{ idDichVu}">
			<img alt="" src="images/${ baiViet.anhMoTa }" class="img-thumbnail">
			<s:div>
				<s:label value="%{ baiViet.tenBaiViet}"></s:label>
				<span title="Thông tin dịch vụ : Nhà cung cấp - Hạn đăng ký">
					<i class="fa fa-user-md" title="Nhà cung cấp dịch vụ"></i> <s:property
						value="baiViet.tenTacGia" /> | <i class="fa fa-calendar-times-o"
					title="Hết hạn ngày"></i> <s:property value="ngayKetThuc" />
				</span>
				<p>
					<s:property value="baiViet.moTa" />
				</p>
			</s:div>
		</s:a>
	</s:div>
</s:iterator>
