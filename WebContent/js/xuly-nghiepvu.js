// Hien thi thong bao
function ShowMessage(model, mes) {
	$('#' + model).modal('show');
	$('#mes' + model).html(mes);
}
// cap nhat trang thai cho bang nao do
function CapNhatTrangThai(id, name, chan) {
	$.ajax({
		url : 'cap-nhat.action',
		type : "post",
		data : {
			idKey : id,
			nameTable : name,
			chanState : chan
		},
		beforeSubmit : function() {
			$("#loadWhile").attr("style", "display: inherit;");
		},
		success : function(data, textStatus, jqXHR) {
			$("#loadWhile").attr("style", "display: none;");
			if (data.indexOf("thất bại") > -1) {
				ShowMessage('error', 'Lỗi xử lý dữ liệu trên server!');
			} else {
				ShowMessage('success', 'Đăng tải thành công.');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$("#loadWhile").attr("style", "display: none;");
			ShowMessage('error', 'Lỗi hệ thông! Vui lòng quay lại sau.');
		}
	});
}
// xem dịch vụ
function DangKy() {
	$.ajax({
		url : "check-dangky-dv.action",
		type : "POST",
		data : {
			idDichVu : $("#idDichVuDK").val(),
			email : $("#emailDK").val()
		},
		beforeSubmit : function() {
			$("#loadWhile").attr("style", "display: inherit;");
		},
		success : function(data, textStatus, jqXHR) {
			$("#loadWhile").attr("style", "display: none;");
			if (data.indexOf("thất bại") > -1) {
				alert("Bạn đã đăng ký dịch vụ này!");
			} else {
				$('#myModal').modal('show');
			}
		}
	});
}
$(document)
		.ready(
				function() {
					/* form đăng ký dịch vụ */
					$("#f-dangky-dichvu")
							.validate(
									{
										rules : {
											hoTen : "required",
											dienThoai : {
												required : true,
												digits : true
											},
											email : {
												required : true,
												email : true
											}
										},
										messages : {
											hoTen : "Nhập họ tên/tổ chức!",
											dienThoai : {
												required : "Nhập điện thoại liên hệ!",
												digits : "Số điện thoại không đúng!"
											},
											email : {
												required : "Nhập email liên hệ!",
												email : "Không đúng định dạng email!"
											}
										},
										submitHandler : function(form) {
											var postData = $(form)
													.serializeArray();
											var formURL = $(form)
													.attr("action");
											$
													.ajax({
														url : formURL,
														type : "POST",
														data : postData,
														beforeSubmit : function() {
															$("#loadWhile")
																	.attr(
																			"style",
																			"display: inherit;");
														},
														success : function(
																data,
																textStatus,
																jqXHR) {
															if (data
																	.indexOf("thất bại") > -1) {
																$("#loiDangKy")
																		.attr(
																				"style",
																				"display: inherit;");
															} else {
																$("#loiDangKy")
																		.attr(
																				"style",
																				"display: none;");
																$('#myModal')
																		.modal(
																				'hide');
																$(
																		'#divContentHTML')
																		.html(
																				'Chúc mừng!Bạn đã đăng ký dịch vụ thành công. Vui lòng chờ xác nhận từ nhà cung cấp. Cám ơn!');
																$('#thong-bao')
																		.popover(
																				'show');
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															$("#loiDangKy")
																	.attr(
																			"style",
																			"display: none;");
															$('#myModal')
																	.modal(
																			'hide');
															$('#divContentHTML')
																	.html(
																			'Lỗi hệ thống!Vui lòng thực hiện thác tác lại sau. Cám ơn!');
															$('#thong-bao')
																	.popover(
																			'show');
														}
													});
										}
									});
					/* form lien he */
					$("#f-lienhe-mail")
							.validate(
									{
										rules : {
											tieuDe : {
												required : true
											},
											noiDung : {
												required : true
											}
										},
										messages : {
											tieuDe : "Bạn chưa nhập tiêu đề!",
											noiDung : {
												required : "Bạn chưa nhập nội dung!",
											}
										},
										submitHandler : function(form) {
											var postData = $(form)
													.serializeArray();
											var formURL = $(form)
													.attr("action");
											$
													.ajax({
														url : formURL,
														type : "POST",
														data : postData,
														beforeSubmit : function() {
															$("#loadWhile")
																	.attr(
																			"style",
																			"display: inherit;");
														},
														success : function(
																data,
																textStatus,
																jqXHR) {
															if (data
																	.indexOf("thất bại") > -1) {
																$("#loilienhe")
																		.attr(
																				"style",
																				"display: inherit;");
															} else {
																$("#loilienhe")
																		.attr(
																				"style",
																				"display: none;");
																$('#formLienHe')
																		.modal(
																				'hide');
																alert("Đã gửi đến nhà cung cấp.");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															$("#loiDangKy")
																	.attr(
																			"style",
																			"display: none;");
															$('#formLienHe')
																	.modal(
																			'hide');
															alert("Lỗi");
														}
													});
										}
									});
					/* form báo lỗi */
					$("#f-dichvu-span")
							.validate(
									{
										rules : {
											hoTen : "required",
											vanDe : {
												required : true
											},
											email : {
												required : true,
												email : true
											}
										},
										messages : {
											hoTen : "Nhập họ tên/tổ chức!",
											vanDe : {
												required : "Chưa chọn báo lỗi!"
											},
											email : {
												required : "Nhập email liên hệ!",
												email : "Không đúng định dạng email!"
											}
										},
										submitHandler : function(form) {
											var postData = $(form)
													.serializeArray();
											var formURL = $(form)
													.attr("action");
											$
													.ajax({
														url : formURL,
														type : "POST",
														data : postData,
														beforeSubmit : function() {
															$("#loadWhile")
																	.attr(
																			"style",
																			"display: inherit;");
														},
														success : function(
																data,
																textStatus,
																jqXHR) {
															$("#loadWhile")
																	.attr(
																			"style",
																			"display: none;");
															$('#baospan')
																	.modal(
																			'hide');
															if (data
																	.indexOf("thất bại") > -1) {
																alert("Lỗi xử lý dữ liệu trên server!");
															} else {
																alert("Báo span dịch vụ thành công.");
																$("#btn-vipham")
																		.removeAttr(
																				"onclick");
																$("#btn-dangky")
																		.remove();
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															$('#baospan')
																	.modal(
																			'hide');
															alert("Lỗi");
														}
													});
										}
									});
				});
$(document).ready(function() {
	$("#f-dangky-nhucau").validate({
		rules : {
			giaDichVu : {
				required : true
			}
		},
		messages : {
			giaDichVu : {
				required : "Bạn chưa báo giá! VD: Miễn phí , 5tr/tháng, ..."
			}
		},
		submitHandler : function(form) {
			var postData = $(form).serializeArray();
		    var formURL = $(form).attr("action");
		    $.ajax({
		        url : formURL,
		        type: "POST",
		        data : postData,
		        beforeSubmit : function (){
		        	$("#loadWhile").attr("style", "display: inherit;");
		        },
		        success:function(data, textStatus, jqXHR) 
		        {
		            if(data.indexOf("thất bại")>-1){
		            	$("#loiDangKy").attr("style", "display: inherit;");
		            } else {
		            	$("#loiDangKy").attr("style", "display: none;");
		            	$('#myModal').modal('hide');
		            	alert("Báo giá nhu cầu thành công.");
		            }
		        },
		        error: function(jqXHR, textStatus, errorThrown) 
		        {
		        	$("#loiDangKy").attr("style", "display: none;");
		        	$('#myModal').modal('hide');
		            alert("Lỗi");    
		        }
		   });
		}
	});
});