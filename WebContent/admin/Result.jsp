<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="hasActionErrors()">
	thất bại
</s:if>
<s:else>
	thàng công
</s:else>
