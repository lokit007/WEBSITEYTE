
function InitDataHeThong(){
	$.ajax({
		url : 'init-data-hethong.action',
		type : "POST",
		success : function(data) {
			window.location.reload(true);
		}
	});
}

$(function() {
	$("#thong-bao").popover({
		title : 'Thông báo',
		content : $('#divContentHTML').html(),
		placement : 'top',
		delay : {
			show : 500,
			hide : 100
		},
		html : true
	});
});

$('body').on('click',function(e) {
	$('.btn-open-popover').each(function() {
		if (!$(this).is(e.target)&& $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
			$(this).popover('hide');
		}
	});
});

$("#fm-dangnhap").validate({
	rules : {
		taiKhoan : {
			required : true
		},
		matKhau : {
			required : true
		}
	},
	messages : {
		taiKhoan : {
			required : "Chưa nhập tài khoản!"
		},
		matKhau : {
			required : "Chưa nhập mật khẩu!"
		}
	},
	submitHandler : function(form) {
		var postData = $(form).serializeArray();
		var formURL = $(form).attr("action");
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			beforeSubmit : function() {
				$("#loadWhile").attr("style", "display: inherit;");
			},
			success : function(data, textStatus, jqXHR) {
				if (data.indexOf("thất bại") > -1) {
					$('#dangNhapModal').modal('hide');
					alert("Lỗi xử lý dữ liệu trên server!");
				} else {
					$('#dangNhapModal').modal('hide');
					window.location.reload(true);
					$('#login').val("true");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$('#dangNhapModal').modal('hide');
				alert("Lỗi");
			}
		});
	}
});

$("#nhanEMail").validate(
		{
			rules : {
				email : {
					required : true,
					email : true
				}
			},
			messages : {
				email : {
					required : "Chưa nhập email!",
					email : "Không phải email"
				}
			},
			submitHandler : function(form) {
				var postData = $(form).serializeArray();
				var formURL = $(form).attr("action");
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {
						if (data.indexOf("thất bại") > -1) {
							$("#email-select .ct-mail").after(
									"Lỗi đăng ký nhận mail!");
						} else {
							$("#email-select .ct-mail").after(
									"Đăng ký nhận mail thành công!");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert("Lỗi");
					}
				});
			}
		});