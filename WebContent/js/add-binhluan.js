function ShowTraLoi(ma, bang, e) {
	if ($("#login").val() == "") {
		if (confirm("Bạn chưa đăng nhập hệ thống! Hãy đăng nhập để có nhiều quyền lợi hơn") == true) {
			$("#dangNhapModal").modal("show");
		}
	} else {
		$(e)
				.parent()
				.append(
						"<div class='div-traloi'><textarea class='txtHoi' placeholder='Trả Lời của bạn'></textarea><div class='btn-binhluan'><input type='submit' value='Trả lời' onclick=\"traLoi('"
								+ ma
								+ "', '"
								+ bang
								+ "', this);\"></div></div>");
		$(e).removeAttr("onclick");
	}
}
function traLoi(ma, bang, e) {
	if ($("#login").val() == "") {
		if (confirm("Bạn chưa đăng nhập hệ thống! Hãy đăng nhập để có nhiều quyền lợi hơn") == true) {
			$("#dangNhapModal").modal("show");
		}
	} else {
		var xoa = $(e).parent().parent();
		$
				.ajax({
					url : "binh-luan.action",
					type : "POST",
					data : {
						id : ma,
						table : bang,
						noiDung : $(xoa).children(".txtHoi").val()
					},
					beforeSubmit : function() {
						$("#loadmore").attr("style", "display: inherit;");
					},
					success : function(data, textStatus, jqXHR) {
						$(xoa)
								.parent()
								.append(
										data
												.replace("list-binhluan-item",
														"div-traloi")
												.replace(
														"<button type=\"button\" class=\"btn btn-link\" onclick=\"ShowTraLoi(this);\">Trả lời</button>",
														""));
						$(xoa).remove();
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$("#loadmore").attr("style", "display: none;");
						alert("Lỗi");
					}
				});
	}
}
function addBinhLuan(ma, bang) {
	if ($("#login").val() == "") {
		if (confirm("Bạn chưa đăng nhập hệ thống! Hãy đăng nhập để có nhiều quyền lợi hơn") == true) {
			$("#dangNhapModal").modal("show");
		}
	} else {
		$.ajax({
			url : "binh-luan.action",
			type : "POST",
			data : {
				id : ma,
				table : bang,
				noiDung : $("#txtHoi").val()
			},
			beforeSubmit : function() {
				$("#loadmore").attr("style", "display: inherit;");
			},
			success : function(data, textStatus, jqXHR) {
				$("#loadmore").attr("style", "display: none;");
				$(".list-binhluan").prepend(data);
				$("#txtHoi").val("");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#loadmore").attr("style", "display: none;");
				alert("Lỗi");
			}
		});
	}
}