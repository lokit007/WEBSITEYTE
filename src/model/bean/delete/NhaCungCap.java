package model.bean.delete;

import model.bean.TaiKhoan;

public class NhaCungCap {
	private int idNhaCungCap;
	private TaiKhoan taiKhoan;
	private String loaiNhaCungCap;
	private int tuVanKhachHang;
	private String gioiThieu;
	
	public NhaCungCap(int idNhaCungCap, TaiKhoan taiKhoan, String loaiNhaCungCap, int tuVanKhachHang,
			String gioiThieu) {
		super();
		this.idNhaCungCap = idNhaCungCap;
		this.taiKhoan = taiKhoan;
		this.loaiNhaCungCap = loaiNhaCungCap;
		this.tuVanKhachHang = tuVanKhachHang;
		this.gioiThieu = gioiThieu;
	}

	public NhaCungCap() {
		super();
	}

	public int getIdNhaCungCap() {
		return idNhaCungCap;
	}

	public void setIdNhaCungCap(int idNhaCungCap) {
		this.idNhaCungCap = idNhaCungCap;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getLoaiNhaCungCap() {
		return loaiNhaCungCap;
	}

	public void setLoaiNhaCungCap(String loaiNhaCungCap) {
		this.loaiNhaCungCap = loaiNhaCungCap;
	}

	public int getTuVanKhachHang() {
		return tuVanKhachHang;
	}

	public void setTuVanKhachHang(int tuVanKhachHang) {
		this.tuVanKhachHang = tuVanKhachHang;
	}

	public String getGioiThieu() {
		return gioiThieu;
	}

	public void setGioiThieu(String gioiThieu) {
		this.gioiThieu = gioiThieu;
	}

}
