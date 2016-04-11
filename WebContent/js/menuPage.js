
function Chen(p, mp) {
	$("#statePage").html(
			"<input type='text' id='txt-page' name='txt-page' placeholder='Trang "
					+ p + "/" + mp + "'> <button onclick='DieuHuong(" + p + ","
					+ mp + ");'>Chuyển</button>");
	$("#statePage").removeAttr('onclick');
	$("#statePage").removeAttr('title');
	$("#statePage").attr('style', 'padding: 2px;');
	$("#txt-page").focus();
}

function DieuHuong(page, mPage) {
	var url = window.location.href;
	try {
		var goPage = parseInt($("#txt-page").val());
		if (goPage > mPage || goPage < 1 || isNaN(goPage)) {
			alert("Trang đến phải là số nguyên dương và nhở hơn hoặc bằng "
					+ mPage);
			$("#txt-page").val("");
		} else {
			if (url.indexOf("?") != -1) {
				if (url.indexOf("page=") != -1) {
					window.location.href = url.replace("page=" + page, "page="
							+ $("#txt-page").val());
				} else {
					window.location.href = window.location + "&page="
							+ $("#txt-page").val();
				}
			} else {
				window.location.href = window.location + "?page="
						+ $("#txt-page").val();
			}
		}
	} catch (err) {
		alert("Trang đến phải là số nguyên dương và nhở hơn hoặc bằng " + mPage);
	}
}
