<%@page import="model.dao.FormatData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="files/ThuVienAdmin.jsp"></s:include>
<title>Admin-Thống kê báo cáo</title>
<link rel="stylesheet" href="../css/datepicker.css">
<script src="../js/sorttable.js"></script>
<script src="../js/menuPage.js"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../js/bootstrap-datepicker.js" type="text/javascript"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.charts.load('current', {
		packages : [ 'corechart', 'bar' ]
	});
	google.charts.setOnLoadCallback(drawMultSeries);
	
	function drawMultSeries() {
		var data1 = new google.visualization.DataTable();
		data1.addColumn('string', 'Danh Mục');
		data1.addColumn('number', 'DV Từ thiện');
		data1.addColumn('number', 'DV Công');
		data1.addColumn('number', 'DV Tư nhân');
		data1.addColumn('number', 'Nhu cầu dịch vụ');
		
		var arrData = $("#data1 tbody").children();
		var i;
		for(i=0; i<arrData.length; i++){
			var dt = $(arrData[i]).children();
			data1.addRow([$(dt[0]).children().val(),
			             parseInt($(dt[1]).children().val()),
			             parseInt($(dt[2]).children().val()),
			             parseInt($(dt[3]).children().val()),
			             parseInt($(dt[4]).children().val())]);
		}
		var options = {
			title : 'Biển đồ thống kê số lượng dịch vụ và nhu cầu trong thực tế',
			chartArea : {
				width : '70%',
				height : '80%'
			},
			hAxis : {
				title : 'Số lượng dịch vụ/nhu cầu đã đăng',
				minValue : 0
			},
			vAxis : {
				title : 'Danh Mục'
			}
		};
		var chart = new google.visualization.BarChart(document
				.getElementById('chart_div_1'));
		chart.draw(data1, options);
	}
</script>
<script type="text/javascript">
	google.charts.setOnLoadCallback(drawChart2);
	function drawChart2() {
		var data1 = new google.visualization.DataTable();
		data1.addColumn('string', 'Danh Mục');
		data1.addColumn('number', 'Số Lượng');
		
		var arrData = $("#data2 tbody").children();
		var i;
		for(i=0; i<arrData.length; i++){
			var dt = $(arrData[i]).children();
			data1.addRow([$(dt[0]).children().val(),
			             parseInt($(dt[1]).children().val())]);
		}
		
		var options = {
			title : 'Phần trăm số lượng đăng tải dịch vụ trên hệ thống',
			is3D : true
		};
		var chart = new google.visualization.PieChart(document
				.getElementById('chart_div_2'));
		chart.draw(data1, options);
	}
</script>
<script type="text/javascript">
	google.charts.setOnLoadCallback(drawMultSeries);
	function drawMultSeries() {
		var data1 = new google.visualization.DataTable();
		data1.addColumn('string', 'Danh Mục');
		data1.addColumn('number', 'Số dịch vụ đăng');
		data1.addColumn('number', 'Số nhu cầu dịch vụ khác');
		data1.addColumn('number', 'Bài viết đã chia sẻ');
		
		var arrData = $("#data3 tbody").children();
		var i;
		for(i=0; i<arrData.length; i++){
			var dt = $(arrData[i]).children();
			data1.addRow([$(dt[0]).children().val(),
			             parseInt($(dt[1]).children().val()),
			             parseInt($(dt[2]).children().val()),
			             parseInt($(dt[3]).children().val())]);
		}
		var options = {
			title : 'Biển đồ thống kê các nhà cung cấp dịch vụ tích cực',
			chartArea : {
				width : '70%',
				height : '80%'
			},
			hAxis : {
				title : 'Số lượng đã đăng tải thành công',
				minValue : 0
			},
			vAxis : {
				title : 'Nhà cung cấp'
			}
		};
		var chart = new google.visualization.BarChart(document
				.getElementById('chart_div_3'));
		chart.draw(data1, options);
	}
</script>
<script type="text/javascript">
	google.charts.setOnLoadCallback(drawBasic);
	function drawBasic() {
		var data = new google.visualization.DataTable();
		var data1 = new google.visualization.DataTable();
		data1.addColumn('string', 'Danh Mục');
		data1.addColumn('number', 'Số lượng truy cập');
		
		var arrData = $("#data4 tbody").children();
		var i;
		for(i=0; i<arrData.length; i++){
			var dt = $(arrData[i]).children();
			data1.addRow([$(dt[0]).children().val(),
			             parseInt($(dt[2]).children().val())]);
		}
		var options = {
			title : 'Top các dịch vụ được xem nhiều',
			hAxis : {
				title : 'Dịch vụ',
				viewWindow : {
					min : [ 7, 30, 0 ],
					max : [ 17, 30, 0 ]
				}
			},
			vAxis : {
				title : 'Số lượng truy cập'
			}
		};
		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart_div_4'));
		chart.draw(data1, options);
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tuNgay').datepicker({
			format : "dd-mm-yyyy",
			autoclose : true,
			todayBtn : true
		});
		$('#denNgay').datepicker({
			format : "dd-mm-yyyy",
			autoclose : true,
			todayBtn : true
		});
	});
</script>
<sx:head />
</head>
<body>
	<s:include value="files/Menu.jsp"></s:include>
	<label id="lb-title"><i class="fa fa-bar-chart"></i> Thống kê báo cáo</label>
	<div id="div-content">
		<fieldset>
			<legend>Thiết lập thống kê</legend>
			<form action="thong-ke.action" method="post" style="text-align: center;">
				<s:label value="Từ ngày "></s:label>
				<s:textfield name="tuNgay" id="tuNgay"></s:textfield>
				<s:label value="đến ngày "></s:label>
				<s:textfield name="denNgay" id="denNgay"></s:textfield>
				<button type="submit">Thống kê</button>
			</form>
		</fieldset>
		<br>
		<s:tabbedPanel id="test" selectedTab="%{ #session.selectTab}">
			<s:div id="one" label="Danh Mục Dịch Vụ" theme="ajax">
				<div id="chart_div_1" style="width: 100%; height: 480px;"></div>
				<table id="data1">
					<s:iterator value="listDM">
						<tr>
							<td><s:hidden value="%{ tenDanhMuc}"></s:hidden></td>
							<td><s:hidden value="%{ soDVTT}"></s:hidden></td>
							<td><s:hidden value="%{ soDVC}"></s:hidden></td>
							<td><s:hidden value="%{ soDVTN}"></s:hidden></td>
							<td><s:hidden value="%{ soNCDV}"></s:hidden></td>
						</tr>
					</s:iterator>
				</table>
			</s:div>
			<s:div id="two" label="Thống kê dịch vụ" theme="ajax">
				<div id="chart_div_2" style="width: 100%; height: 480px;"></div>
				<table id="data2">
					<s:iterator value="listDV">
						<tr>
							<td><s:hidden value="%{ key}"></s:hidden></td>
							<td><s:hidden value="%{ value}"></s:hidden></td>
						</tr>
					</s:iterator>
				</table>
			</s:div>
			<s:div id="three" label="Nhà cung cấp tiềm năng" theme="ajax">
				<div id="chart_div_3" style="width: 100%; height: 480px;"></div>
				<table id="data3">
					<s:iterator value="listTK">
						<tr>
							<td><s:hidden value="%{ tenNhaCungCap}"></s:hidden></td>
							<td><s:hidden value="%{ soDichVu}"></s:hidden></td>
							<td><s:hidden value="%{ soNhuCau}"></s:hidden></td>
							<td><s:hidden value="%{ soBaiViet}"></s:hidden></td>
						</tr>
					</s:iterator>
				</table>
			</s:div>
			<s:div id="four" label="Bài viết được quan tâm" theme="ajax">
  				<div id="chart_div_4" style="width: 100%; height: 480px;"></div>
  				<table id="data4">
					<s:iterator value="listBV">
						<tr>
							<td><s:hidden value="%{ tenBaiViet}"></s:hidden></td>
							<td><s:hidden value="%{ hinhAnh}"></s:hidden></td>
							<td><s:hidden value="%{ luocXem}"></s:hidden></td>
						</tr>
					</s:iterator>
				</table>
			</s:div>
		</s:tabbedPanel>
	</div>
</body>

</html>
