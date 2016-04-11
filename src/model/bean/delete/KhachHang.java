package model.bean.delete;

import model.bean.TaiKhoan;

public class KhachHang {
	private String idKhachHang;
	private TaiKhoan taiKhoan;
	private String idFaceBook;
	private String idGoogle;
	private String idBaoHiem;
	private String idBenhAn;
	private String loaiKhachHang;
	public KhachHang(String idKhachHang, TaiKhoan taiKhoan, String idFaceBook, String idGoogle, String idBaoHiem,
			String idBenhAn, String loaiKhachHang) {
		super();
		this.idKhachHang = idKhachHang;
		this.taiKhoan = taiKhoan;
		this.idFaceBook = idFaceBook;
		this.idGoogle = idGoogle;
		this.idBaoHiem = idBaoHiem;
		this.idBenhAn = idBenhAn;
		this.loaiKhachHang = loaiKhachHang;
	}
	public KhachHang() {
		super();
	}
	public String getIdKhachHang() {
		return idKhachHang;
	}
	public void setIdKhachHang(String idKhachHang) {
		this.idKhachHang = idKhachHang;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getIdFaceBook() {
		return idFaceBook;
	}
	public void setIdFaceBook(String idFaceBook) {
		this.idFaceBook = idFaceBook;
	}
	public String getIdGoogle() {
		return idGoogle;
	}
	public void setIdGoogle(String idGoogle) {
		this.idGoogle = idGoogle;
	}
	public String getIdBaoHiem() {
		return idBaoHiem;
	}
	public void setIdBaoHiem(String idBaoHiem) {
		this.idBaoHiem = idBaoHiem;
	}
	public String getIdBenhAn() {
		return idBenhAn;
	}
	public void setIdBenhAn(String idBenhAn) {
		this.idBenhAn = idBenhAn;
	}
	public String getLoaiKhachHang() {
		return loaiKhachHang;
	}
	public void setLoaiKhachHang(String loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}
	
}
