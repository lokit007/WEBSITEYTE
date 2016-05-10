/*
 * Trang trang dich vu
 * */
// dieu huong den trang khac
function ChuyenHuong(url) {
	window.location.href = url;
}
// xoa dich vu
function XoaDichVu(id) {
	if (confirm("Bạn muốn xoá dịch vụ " + id + " này không???") == true) {
		$.ajax({
			url : "xoa-dich-vu.action",
			type : "post",
			data : {
				idDichVu : id
			},
			beforeSend : function() {
				$("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				window.location.reload(true);
				$("#while-load").attr("style", "display: none;");
			},
			error : function(xhr, status, error) {
				ShowMessage('error', 'Lỗi hệ thống! Vui lòng quay lại sau!');
				$("#while-load").attr("style", "display: none;");
			}
		});
	}
}
// Hien thi thong bao
function ShowMessage(model, mes) {
	$('#' + model).modal('show');
	$('#mes' + model).html(mes);
}
// Xoa element co id
function XoaPhanTu(id) {
	$("#" + id).remove();
}
// Xu ly dich vu vi pham
function DichVuViPham(id) {
	AjaxXuLyDichVu('vi-pham-dich-vu.action', id,
			'Đã báo lỗi dịch vụ thành công',
			'Lỗi!Báo dịch vụ không thành công.');
}
// Cho phep dang dich vu
function CapPhepDichVu(id) {
	AjaxXuLyDichVu('cap-phep-dich-vu.action', id,
			'Đã cấp phép phát hành dịch vụ thành công!',
			'Lỗi!Cấp phép dịch vụ không thành công.')
}
// Xu ly du lieu tren server
function AjaxXuLyDichVu(url, id, sus, err) {
	$.ajax({
		url : url,
		type : "POST",
		data : {
			idDichVu : id
		},
		beforeSubmit : function() {
			$("#loadWhile").attr("style", "display: inherit;");
		},
		success : function(data, textStatus, jqXHR) {
			$("#loadWhile").attr("style", "display: none;");
			if (data.indexOf("thất bại") > -1) {
				ShowMessage('error', err);
			} else {
				ShowMessage('success', sus);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$("#loadWhile").attr("style", "display: none;");
			ShowMessage('error', 'Lỗi hệ thông! Vui lòng quay lại sau.');
		}
	});
}
// nhu cau
function XoaNhuCau(id) {
	if (confirm("Bạn muốn xoá nhu cầu " + id + " này không???") == true) {
		$.ajax({
			url : "xoa-nhucau.action",
			type : "post",
			data : {
				idNhuCau : id
			},
			beforeSend : function() {
				$("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				window.location.reload(true);
				$("#while-load").attr("style", "display: none;");
			},
			error : function(xhr, status, error) {
				ShowMessage('error', 'Lỗi hệ thông! Vui lòng quay lại sau.');
				$("#while-load").attr("style", "display: none;");
			}
		});
	}
}
function CapPhepPhatHanh(id, name, chan) {
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
function CapPhepPhatHanh(id, name, chan, sus, err) {
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
				ShowMessage('error', err);
			} else {
				ShowMessage('success', sus);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$("#loadWhile").attr("style", "display: none;");
			ShowMessage('error', 'Lỗi hệ thông! Vui lòng quay lại sau.');
		}
	});
}
// chia se
function XoaChiaSe(id) {
	if (confirm("Bạn muốn xoá chia sẻ " + id + " này không???") == true) {
		$.ajax({
			url : "xoa-chiase.action",
			type : "post",
			data : {
				idBaiViet : id
			},
			beforeSend : function() {
				$("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				window.location.reload(true);
				$("#while-load").attr("style", "display: none;");
			},
			error : function(xhr, status, error) {
				ShowMessage('error', 'Lỗi hệ thông! Vui lòng quay lại sau.');
				$("#while-load").attr("style", "display: none;");
			}
		});
	}
}
// nha cung cap
function XoaNhaCungCap(id) {
	if (confirm("Bạn muốn xoá nhà cung cấp " + id + " này không???") == true) {
		$.ajax({
			url : "xoa-nhacungcap.action",
			type : "post",
			data : {
				idNhaCungCap : id
			},
			beforeSend : function() {
				$("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				window.location.reload(true);
				$("#while-load").attr("style", "display: none;");
			},
			error : function(xhr, status, error) {
				$("#loi").modal("show");
				$("#while-load").attr("style", "display: none;");
			}
		});
	}
}
function CapNhatNguoiDung(id, e) {
	var hoi = "";
	if (e == 'TK mới')
		hoi = "Bạn muốn khôi phục lại tài khoản ";
	else
		hoi = "Bạn muốn khóa tài khoản ";
	if (confirm(hoi + id + " này không???") == true) {
		$.ajax({
			url : "khoa-taikhoan.action",
			type : "post",
			data : {
				idTaiKhoan : id,
				tinhTrang : e
			},
			beforeSend : function() {
				$("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				window.location.reload(true);
				$("#while-load").attr("style", "display: none;");
			},
			error : function(xhr, status, error) {
				$("#loi").modal("show");
				$("#while-load").attr("style", "display: none;");
			}
		});
	}
}

function XoaCauHoi(id, e){
	$.ajax({
		url : "xoa-cau-hoi.action",
		type : "post",
		data : {
			id : id,
			nameTable : 'CAUHOI'
		},
		beforeSend : function() {
			$("#while-load").attr("style", "display: inline-block;");
		},
		success : function(result) {
			window.location.reload(true);
		},
		error : function(xhr, status, error) {
			$("#while-load").attr("style", "display: none;");
		}
	});
}

function HuyDangKy(id){
	if (confirm("Bạn có muốn hủy đăng ký " + id + " này không???") == true) {
		$.ajax({
			url : "cap-nhat.action",
			type : "post",
			data : {
				idKey : id,
				nameTable : "DANGKYDICHVU",
				chanState : "Hủy bỏ"
			},
			beforeSend : function() {
				$("#while-load").attr("style", "display: inline-block;");
			},
			success : function(result) {
				window.location.reload(true);
			},
			error : function(xhr, status, error) {
				$("#while-load").attr("style", "display: none;");
			}
		});
	}
}


