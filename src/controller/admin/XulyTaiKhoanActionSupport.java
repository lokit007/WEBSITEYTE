package controller.admin;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.TaiKhoanBO;
import model.bo.ValidateBO;

public class XulyTaiKhoanActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String idTaiKhoan;
	private String tinhTrang;
	
	public String XulyNguoiDung(){
		if(ValidateBO.CheckEmpty(idTaiKhoan)||ValidateBO.CheckEmpty(tinhTrang)){
			addActionError("Tài khoản không tồn tại!");
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			if(!taiKhoanBO.khoaNguoiDung(idTaiKhoan, tinhTrang)){
				addActionError("Lỗi cập nhật dữ liệu hệ thống!");
			}
			taiKhoanBO.closeConnect();
		}
		return "thanh-cong";
	}

	public String getIdTaiKhoan() {
		return idTaiKhoan;
	}

	public void setIdTaiKhoan(String idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
}
