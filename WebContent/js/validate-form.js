//trang ca nhan
$(document).ready(function() {
	$("#f-capnhat-thongtin").validate({
		rules : {
			hoTen : {
				required : true
			},
			dienThoai : {
				required : true,
				digits : true,
				minlength : 10
			},
			diaChi : {
				required : true
			},
			email : {
				required : true,
				email : true
			},
			matKhauXacNhan : {
				equalTo : '#matKhauMoi'
			}
		},
		messages : {
			hoTen : {
				required : "Bạn chưa nhập dữ liệu cho trường này!"
			},
			dienThoai : {
				required : "Bạn chưa nhập dữ liệu cho trường này!",
				digits : "Không phải số điện thoại!",
				minlength : "Không phải số điện thoại!"
			},
			diaChi : {
				required : "Bạn chưa nhập dữ liệu cho trường này!"
			},
			email : {
				required : "Bạn chưa nhập dữ liệu cho trường này!",
				email : "Không phải định dạng email!"
			},
			matKhauXacNhan : {
				equalTo : 'Mật khẩu xác nhận không chính xác!'
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
						alert(data);
					} else {
						alert(data);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#loiDangKy").attr("style", "display: none;");
					$('#myModal').modal('hide');
					alert("Lỗi");
				}
			});
		}
	});
});
//dang dich vụ
$(document).ready(function() {
	$("#formDichVu").validate({
		rules : {
			tenDichVu : {
				required : true
			},
			moTa : {
				required : true
			},
			danhMuc : {
				required : true	
			},
			loaiHinh : {
				required : true
			},
			noiDung : {
				required : true
			},
			nhaCungCap : {
				required : true
			},
			diaDiem : {
				required : true
			},
			dienThoai : {
				required : true,
				minlength : 10,
				digits : true
			},
			email : {
				required : true,
				email : true
			},
			ngayBatDau : "required",
			ngayKetThuc : "required"
		},
		messages : {
			tenDichVu : {
				required : "Chưa nhập đầy đủ thông tin dịch vụ!"
			},
			moTa : {
				required : "Chưa nhập đầy đủ thông tin dịch vụ!"
			},
			danhMuc : {
				required : "Chưa chọn danh mục dịch vụ!"	
			},
			loaiHinh : {
				required : "Chưa chọn loại hình dịch vụ!"
			},
			noiDung : {
				required : "Chưa nhập đầy đủ thông tin dịch vụ!"
			},
			nhaCungCap : {
				required : "Chưa nhập đầy đủ thông tin dịch vụ!"
			},
			diaDiem : {
				required : "Chưa nhập đầy đủ thông tin dịch vụ!"
			},
			dienThoai : {
				required : "Chưa nhập đầy đủ thông tin dịch vụ!",
				minlength : "Không phải số điện thoại!",
				digits : "Không phải số điện thoại!"
			},
			email : {
				required : "Chưa nhập đầy đủ thông tin dịch vụ!",
				email : "Không phải định dạng email!"
			},
			ngayBatDau : "Chưa nhập đầy đủ thông tin dịch vụ!",
			ngayKetThuc : "Chưa nhập đầy đủ thông tin dịch vụ!"
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});
// dang nhu cau
$(document).ready(function() {
	$("#formNhuCau").validate({
		rules : {
			tenNhuCau : {
				required : true
			},
			moTa : {
				required : true
			},
			danhMuc : "required",
			nhaCungCap : {
				required : true
			},
			dienThoai : {
				required : true,
				minlength : 10,
				digits : true
			},
			email : {
				required : true,
				email : true
			},
			ngayBatDau : "required",
			ngayKetThuc : "required"
		},
		messages : {
			tenNhuCau : {
				required : "Chưa nhập đầy đủ thông tin nhu cầu dịch vụ!"
			},
			moTa : {
				required : "Chưa nhập đầy đủ thông tin nhu cầu dịch vụ!"
			},
			danhMuc : "Chưa nhập đầy đủ thông tin nhu cầu dịch vụ!",
			nhaCungCap : {
				required : "Chưa nhập đầy đủ thông tin nhu cầu dịch vụ!"
			},
			dienThoai : {
				required : "Chưa nhập đầy đủ thông tin nhu cầu dịch vụ!",
				minlength : "Không phải số điện thoại!",
				digits : "Không phải số điện thoại!"
			},
			email : {
				required : "Chưa nhập đầy đủ thông tin nhu cầu dịch vụ!",
				email : "Không phải định dạng email!"
			},
			ngayBatDau : "Chưa nhập đầy đủ thông tin nhu cầu dịch vụ!",
			ngayKetThuc : "Chưa nhập đầy đủ thông tin nhu cầu dịch vụ!"
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});

//dang ký quan cao
$(document).ready(function() {
	$("#formQuangCao").validate({
		rules : {
			userImage : {
				required : true
			},
			linkQuangBa : {
				required : true,
				url : true
			},
			hoTen : {
				required : true
			},
			email : {
				required : true,
				email : 5
			},
			diaChi : {
				required : true
			}
		},
		messages : {
			userImage : {
				required : "Bạn chưa nhập dữ liệu!"
			},
			linkQuangBa : {
				required : "Bạn chưa nhập dữ liệu!",
				url : true
			},
			hoTen : {
				required : "Bạn chưa nhập dữ liệu!"
			},
			email : {
				required : "Bạn chưa nhập dữ liệu!",
				email : 5
			},
			diaChi : {
				required : "Bạn chưa nhập dữ liệu!"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});

