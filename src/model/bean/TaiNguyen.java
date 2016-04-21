package model.bean;

import java.util.HashMap;

public class TaiNguyen {
	//info symtem
	private String tenHeThong;
	private String banQuyen;
	private String diaChi;
	private String dienThoai;
	private String fax;
	private String email;
	private String soLuongTruyCap;
	//quy định
	private String noiQuyThanhVien;
	private String quyDinhDangDichVu;
	private String quyDinhDangNhuCau;
	//liên kết
	private HashMap<String, String> lienKet = new HashMap<String, String>();
	
	public String getTenHeThong() {
		return tenHeThong;
	}
	
	public void setTenHeThong(String tenHeThong) {
		this.tenHeThong = tenHeThong;
	}
	
	public String getBanQuyen() {
		return banQuyen;
	}
	
	public void setBanQuyen(String banQuyen) {
		this.banQuyen = banQuyen;
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
	
	public String getFax() {
		return fax;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSoLuongTruyCap() {
		return soLuongTruyCap;
	}
	
	public void setSoLuongTruyCap(String soLuongTruyCap) {
		this.soLuongTruyCap = soLuongTruyCap;
	}
	
	public String getNoiQuyThanhVien() {
		return noiQuyThanhVien;
	}
	
	public void setNoiQuyThanhVien(String noiQuyThanhVien) {
		this.noiQuyThanhVien = noiQuyThanhVien;
	}
	
	public String getQuyDinhDangDichVu() {
		return quyDinhDangDichVu;
	}
	
	public void setQuyDinhDangDichVu(String quyDinhDangDichVu) {
		this.quyDinhDangDichVu = quyDinhDangDichVu;
	}
	
	public String getQuyDinhDangNhuCau() {
		return quyDinhDangNhuCau;
	}
	
	public void setQuyDinhDangNhuCau(String quyDinhDangNhuCau) {
		this.quyDinhDangNhuCau = quyDinhDangNhuCau;
	}
	
	public HashMap<String, String> getLienKet() {
		return lienKet;
	}
	
	public void setLienKet(HashMap<String, String> lienKet) {
		this.lienKet = lienKet;
	}
	
}
