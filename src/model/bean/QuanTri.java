package model.bean;

public class QuanTri {
	private TaiKhoan taiKhoan;
	private boolean danhMuc = false;
	private boolean dichVu = false;
	private boolean nhuCau = false;
	private boolean chiaSe = false;
	private boolean nhaCungCap = false;
	private boolean taiNguyen = false;
	private boolean thongKe = false;
	
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public boolean isDanhMuc() {
		return danhMuc;
	}
	public void setDanhMuc(boolean danhMuc) {
		this.danhMuc = danhMuc;
	}
	public boolean isDichVu() {
		return dichVu;
	}
	public void setDichVu(boolean dichVu) {
		this.dichVu = dichVu;
	}
	public boolean isNhuCau() {
		return nhuCau;
	}
	public void setNhuCau(boolean nhuCau) {
		this.nhuCau = nhuCau;
	}
	public boolean isChiaSe() {
		return chiaSe;
	}
	public void setChiaSe(boolean chiaSe) {
		this.chiaSe = chiaSe;
	}
	public boolean isNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(boolean nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public boolean isTaiNguyen() {
		return taiNguyen;
	}
	public void setTaiNguyen(boolean taiNguyen) {
		this.taiNguyen = taiNguyen;
	}
	public boolean isThongKe() {
		return thongKe;
	}
	public void setThongKe(boolean thongKe) {
		this.thongKe = thongKe;
	}
}
