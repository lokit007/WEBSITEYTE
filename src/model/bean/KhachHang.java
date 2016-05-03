package model.bean;

public class KhachHang {
	private int idKhachHang;
	private String tenKhachHang;
	private String diaChi;
	private String email;
	private String chiPhiDauTu;
	
	public KhachHang(int idKhachHang, String tenKhachHang, String diaChi, String email) {
		super();
		this.idKhachHang = idKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.diaChi = diaChi;
		this.email = email;
	}

	public KhachHang() {
		super();
	}

	public int getIdKhachHang() {
		return idKhachHang;
	}

	public void setIdKhachHang(int idKhachHang) {
		this.idKhachHang = idKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChiPhiDauTu() {
		return chiPhiDauTu;
	}

	public void setChiPhiDauTu(String chiPhiDauTu) {
		this.chiPhiDauTu = chiPhiDauTu;
	}
	
}
