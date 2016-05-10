function ThemMoi() {
	$("#them-dm").modal("show");
}
function CapNhat(id) {
	$.ajax({
		url : "show-quantri.action",
		type : "post",
		data : {
			idTaiKhoan : id
		},
		beforeSend : function() {
			$("#while-load").attr("style", "display: inline-block;");
		},
		success : function(result) {
			$("#capnhat-dm").html(result);
			$("#capnhat-dm").modal("show");
			$("#while-load").attr("style", "display: none;");
		},
		error : function(xhr, status, error) {
			$("#loi").modal("show");
			$("#while-load").attr("style", "display: none;");
		}
	});
}
function XoaTaiKhoan(id) {
	if (confirm("Bạn muốn xoá tàik khoản " + id + " này không???") == true) {
		$.ajax({
			url : "xoa-quantrivien.action",
			type : "post",
			data : {
				idTaiKhoan : id
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
	if(e=='TK mới') hoi = "Bạn muốn khôi phục lại tài khoản ";
	else hoi = "Bạn muốn khóa tài khoản ";
	if (confirm(hoi + id + " này không???") == true) {
		$.ajax({
			url : "khoa-taikhoan.action",
			type : "post",
			data : {
				idTaiKhoan : id,
				tinhTrang : e
			},
			beforeSend : function(){
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
