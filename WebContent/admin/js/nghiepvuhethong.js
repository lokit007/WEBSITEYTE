function ShowHide(e) {
	var style = $(e).next().attr("style");
	if (style == "display: none;") {
		$(e).next().attr("style", "display: inherit;");
		$(e).prev().attr("class", "fa fa-minus-square-o");
	} else {
		$(e).next().attr("style", "display: none;");
		$(e).prev().attr("class", "fa fa-plus-square-o");
	}
}
function ThemQuanTri(i) {
	if (i == 0) {
		$("#row-add").attr("style", "display: table-row;");
		$("#row-action-add").attr("style", "display: none;");
	} else {
		$("#row-add").attr("style", "display: none;");
		$("#row-action-add").attr("style", "display: table-row;");
	}
}
function ThemLienKet(i) {
	if (i == 0) {
		$("#row-add-lienket").attr("style", "display: table-row;");
		$("#row-action-add-lienket").attr("style", "display: none;");
	} else {
		$("#row-add-lienket").attr("style", "display: none;");
		$("#row-action-add-lienket").attr("style", "display: table-row;");
	}
}
function CapNhatQuyenHan(id, col, o) {
	$.ajax({
		url : "capnhat-quyenhan.action",
		type : "post",
		data : {
			taiKhoan : id,
			nameCol : col,
			valCheck : $(o).is(":checked")
		},
		beforeSend : function() {
			$("#while-load").attr("style", "display: inline-block;");
		},
		success : function(result) {
			$("#while-load").attr("style", "display: none;");
			if (result.indexOf("thất bại") > -1) {
				window.location.reload(true);
			}
		},
		error : function(xhr, status, error) {
			$("#while-load").attr("style", "display: none;");
		}
	});
}
function XoaQuanTriVien(id) {
	if (confirm("Xóa quản trị viên " + id + " này không???") == true) {
		$.ajax({
			url : "xoa-capquyen.action",
			type : "post",
			data : {
				taiKhoan : id
			},
			beforeSend : function() {
				$("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				$("#while-load").attr("style", "display: none;");
				if (result.indexOf("thất bại") > -1) {
					ShowMessage('error', 'Xin lỗi! Không thể xóa quản trị viên này.');
				} else {
					window.location.reload(true);
				}
			},
			error : function(xhr, status, error) {
				$("#while-load").attr("style", "display: none;");
			}
		});
	}
}
function XoaLienKet(id) {
	if (confirm("Bạn muốn xóa liên kết đến " + id + " không???") == true) {
		$.ajax({
			url : "xoa-lienket.action",
			type : "post",
			data : {
				tenWebsite : id
			},
			beforeSend : function() {
				$("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				$("#while-load").attr("style", "display: none;");
				if (result.indexOf("thất bại") > -1) {
					ShowMessage('error', 'Lỗi! Không xóa được liên kết!');
				} else {
					window.location.reload(true);
				}
			},
			error : function(xhr, status, error) {
				$("#while-load").attr("style", "display: none;");
			}
		});
	}
}
$(document)
		.ready(
				function() {
					/* form cập nhật thông tin */
					$("#f-thongtin")
							.validate(
									{
										rules : {
											dienThoai : {
												digits : true,
												minlength : 10
											},
											fax : {
												digits : true
											},
											email : {
												email : true
											}
										},
										messages : {
											dienThoai : {
												digits : "Không phải là số điện thoại!",
												minlength : "Không phải là số điện thoại!"
											},
											fax : {
												digits : "Không phải là số fax!"
											},
											email : {
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
																ShowMessage('error', 'Cập nhật thông tin thất bại.');
															} else {
																ShowMessage('success', 'Cập nhật thành công.');
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															ShowMessage('error', 'Lỗi cập nhật thông tin hệ thống.');
														}
													});
										}
									});
					// cập nhật nội quy
					var frm = $('#f-noiquy');
					frm
							.submit(function(ev) {
								$
										.ajax({
											type : "POST",
											url : 'capnhat-noiquy.action',
											data : {
												noiQuyThanhVien : CKEDITOR.instances.noiQuyThanhVien
														.getData(),
												quyDinhDangDichVu : CKEDITOR.instances.quyDinhDangDichVu
														.getData(),
												quyDinhDangNhuCau : CKEDITOR.instances.quyDinhDangNhuCau
														.getData()
											},
											success : function(data) {
												if (data.indexOf("thất bại") > -1) {
													ShowMessage('error', 'Cập nhật nội quy thất bại.');
													location.reload(true);
												} else {
													ShowMessage('success', 'Cập nhật thành công.');
												}
											}
										});
								ev.preventDefault();
							});
					// thêm quản trị hệ thống
					AjaxUpdateData('f-add-quantri', 'capquyen-quantri.action',
							'Thêm quản trị thành công!',
							'Thêm quản trị thất bại!');
					// thêm liên kết mới
					AjaxUpdateData('f-add-lienket', 'them-lienket.action',
							'Thêm liên kết thành công!',
							'Thêm liên kết thất bại!');
				});
// xử lý ajax cập nhật dữ liệu
function AjaxUpdateData(idForm, url, megOk, MegEr) {
	var form = $('#' + idForm);
	form.submit(function(ev) {
		$.ajax({
			type : "POST",
			url : url,
			data : form.serialize(),
			success : function(data) {
				if (data.indexOf("thất bại") > -1) {
					ShowMessage('error', MegEr);
				} else {
					ShowMessage('success', megOk);
					location.reload(true);
				}
			}
		});
		ev.preventDefault();
	});
}