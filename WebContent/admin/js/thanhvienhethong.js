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
