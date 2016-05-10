<%@page import="controller.SessionCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<img src="http://banners.wunderground.com/banner/gizmotimetemp_both/language/www/global/stations/48852.gif"
	alt="Du bao thoi tiet - Co do Hue"
	title="Dự báo thời tiết - Cố đô Huế" height="41" width="100%">
<span class="luocxem"><i class="fa fa-globe"></i> Lược truy cập <span class="sluong"> 
<s:property value="#session.counter.view"/> </span></span>
<span class="luocxem"><i class="fa fa-users"></i> Online <span class="sluong"> 
<s:property value="#session.counter.getOnline()"/> </span></span>