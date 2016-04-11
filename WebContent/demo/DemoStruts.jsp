<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo struts 2</title>
<!-- sử dụng tag ajax -->
<sx:head/>
<style type="text/css">
.rating>span:hover:before {
	content: "\2605";
	position: absolute;
}

.rating {
	unicode-bidi: bidi-override;
	direction: rtl;
	font-size: 2em;
}

.rating>span:hover:before, .rating>span:hover ~ span:before {
	content: "\2605";
	position: absolute;
	color: yellow;
}

.rating {
	unicode-bidi: bidi-override;
	direction: rtl;
}

.rating>span {
	display: inline-block;
	position: relative;
	width: 1.1em;
}

.rating>span:hover:before, .rating>span:hover ~ span:before {
	content: "\2605";
	position: absolute;
}
</style>
<%@include file="../files/ThuVien.jsp" %>
</head>
<body>
		<sx:tree id="books" label="Books" title="test"> 
	         <sx:treenode label="Programing books" title="test"/>
	         <sx:treenode label="Java" title="test" />
	         <sx:treenode id="Thread-Books" label="Core-Java" >
		         <sx:treenode id="Thread-Books" label="Java in Action" />
		         <sx:treenode id="Thread-Books" label="Core-Java Essentials" />
		         <sx:treenode id="Thread-Books" label="Head first Java" />
		         <sx:treenode id="Thread-Books" label="Multi-threading" />
		         <sx:treenode id="Thread-Books" label="Networking" />
	         </sx:treenode>
      	</sx:tree>
	<div class="rating">
		<span id="5">☆</span><span id="4">☆</span><span id="3">☆</span><span id="2">☆</span><span id="1">☆</span>
		<p id="ketqua"></p><p id="ketquaclick"></p>
	</div>
	<%= request.getRequestURI() %>
	<s:property value="#request.requestURI" />
	<script type="text/javascript">
		$('span').hover(function(){
			$('#ketqua').text(5-$(this).index());
			$(this).css('color','red');
			for(i=1; i<$(this).index()+1; i++){
				$('#'+i).css('color','blue');
			}
		});
		$('span').click(function(){
		    $('#ketquaclick').text(5-$(this).index());
		});
	</script>
</body>
</html>