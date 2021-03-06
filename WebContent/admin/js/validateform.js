//validate form dang dich vu
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
// form nhu cau
$(document).ready(function() {
	$("#formNhuCau").validate({
		rules : {
			tenNhuCau : {
				required : true
			},
			moTa : {
				required : true
			},
			danhMuc : {
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
			tenNhuCau : {
				required : "Chưa nhập đầy đủ thông tin dịch vụ!"
			},
			moTa : {
				required : "Chưa nhập đầy đủ thông tin dịch vụ!"
			},
			danhMuc : {
				required : "Chưa chọn danh mục dịch vụ!"
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
// validate tai khoan
