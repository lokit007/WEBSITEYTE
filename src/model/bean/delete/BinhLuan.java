package model.bean.delete;

import model.bean.TaiKhoan;

public class BinhLuan {
	private int idBinhLuan;
	private int idBaiViet;
	private TaiKhoan taiKhoan;
	private String tenNguoiBinhLuan;
	private String noiDungBinhLuan;
	private String ngayBinhLuan;
	
	public BinhLuan(int idBinhLuan, int idBaiViet, TaiKhoan taiKhoan, String tenNguoiBinhLuan, String noiDungBinhLuan,
			String ngayBinhLuan) {
		super();
		this.idBinhLuan = idBinhLuan;
		this.idBaiViet = idBaiViet;
		this.taiKhoan = taiKhoan;
		this.tenNguoiBinhLuan = tenNguoiBinhLuan;
		this.noiDungBinhLuan = noiDungBinhLuan;
		this.ngayBinhLuan = ngayBinhLuan;
	}

	public BinhLuan() {
		super();
	}

	public int getIdBinhLuan() {
		return idBinhLuan;
	}

	public void setIdBinhLuan(int idBinhLuan) {
		this.idBinhLuan = idBinhLuan;
	}

	public int getIdBaiViet() {
		return idBaiViet;
	}

	public void setIdBaiViet(int idBaiViet) {
		this.idBaiViet = idBaiViet;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getTenNguoiBinhLuan() {
		return tenNguoiBinhLuan;
	}

	public void setTenNguoiBinhLuan(String tenNguoiBinhLuan) {
		this.tenNguoiBinhLuan = tenNguoiBinhLuan;
	}

	public String getNoiDungBinhLuan() {
		return noiDungBinhLuan;
	}

	public void setNoiDungBinhLuan(String noiDungBinhLuan) {
		this.noiDungBinhLuan = noiDungBinhLuan;
	}

	public String getNgayBinhLuan() {
		return ngayBinhLuan;
	}

	public void setNgayBinhLuan(String ngayBinhLuan) {
		this.ngayBinhLuan = ngayBinhLuan;
	}
	
}
