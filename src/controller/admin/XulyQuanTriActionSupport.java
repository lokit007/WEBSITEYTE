package controller.admin;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.TaiKhoan;
import model.bo.TaiKhoanBO;
import model.bo.ValidateBO;

public class XulyQuanTriActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String idTaiKhoan;
	private String tinhTrang;
	private String hoTen;
	private String diaChi;
	private String dienThoai;
	private String email;
	private TaiKhoan taiKhoan;
	
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

	public String ThemQuanTri(){
		String result = "thanh-cong";
		if(ValidateBO.CheckEmpty(idTaiKhoan)||ValidateBO.CheckEmpty(hoTen)
				||ValidateBO.CheckEmpty(diaChi)||ValidateBO.CheckEmpty(dienThoai)
				||ValidateBO.CheckEmpty(email)){
			addActionError("Thêm mới tài khoản quản trị thất bại!");
			result = "that-bai";
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			if(!taiKhoanBO.ThemTaiKhoan(idTaiKhoan, "12345678", hoTen, diaChi, dienThoai, email,"admin")){
				addActionError("Thêm mới tài khoản quản trị thất bại!");
				result = "that-bai";
			}
			taiKhoanBO.closeConnect();
		}
		return result;
	}
	
	public String ThongTinQuanTri(){
		TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
		this.taiKhoan = taiKhoanBO.getTaiKhoan(idTaiKhoan);
		taiKhoanBO.closeConnect();
		return "thanh-cong";
	}
	
	public String CapNhatQuanTri(){
		String result = "thanh-cong";
		if(ValidateBO.CheckEmpty(idTaiKhoan)||ValidateBO.CheckEmpty(hoTen)
				||ValidateBO.CheckEmpty(diaChi)||ValidateBO.CheckEmpty(dienThoai)
				||ValidateBO.CheckEmpty(email)){
			addActionError("Cập nhật tài khoản quản trị thất bại!");
			result = "that-bai";
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			if(!taiKhoanBO.CapNhatQuanTri(idTaiKhoan, hoTen, diaChi, dienThoai, email)){
				addActionError("Cập nhật tài khoản quản trị thất bại!");
				result = "that-bai";
			}
			taiKhoanBO.closeConnect();
		}
		return result;
	}
	
	public String XoaQuanTri(){
		if(ValidateBO.CheckEmpty(idTaiKhoan)){
			addActionError("Tài khoản không tồn tại!");
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			if(!taiKhoanBO.XoaQuanTri(idTaiKhoan)){
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

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
}
