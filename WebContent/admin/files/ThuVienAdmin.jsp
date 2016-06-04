<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../js/jquery.lockfixed.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/back-to-top.js"></script>
<script type="text/javascript" src="../bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<link rel="stylesheet" href="../bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="../font-awesome-4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/AdminStyle.css">

<script type="text/javascript">
	//Hien thi thong bao
	function ShowMessage(model, mes) {
		$('#' + model).modal('show');
		$('#mes' + model).html(mes);
	}
	//Hien thi menu
	function ShowMenu(e){
		if(e==1){
			$("#icon-menu").attr("class", "fa fa-bars fa-rotate-90");
			$("#icon-menu").attr("onclick", "ShowMenu(0);");
			$("#div-menu").attr("style", "display: inherit;");
		} else {
			$("#icon-menu").attr("class", "fa fa-bars");
			$("#icon-menu").attr("onclick", "ShowMenu(1);");
			$("#div-menu").attr("style", "display: none;");
		}
	}
</script>