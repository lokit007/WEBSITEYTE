package model.bean;

public class TaiKhoan {
	private String idTaiKhoan;
	private String matKhau;
	private String hoTen;
	private String diaChi;
	private String dienThoai;
	private String email;
	private String tinhTrang;
	private String ngayThamGia;
	private String loaiTaiKhoan;
	
	public TaiKhoan(String idTaiKhoan, String matKhau, String hoTen, String diaChi, String dienThoai,
			String email, String tinhTrang, String ngayThamGia) {
		super();
		this.idTaiKhoan = idTaiKhoan;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.email = email;
		this.tinhTrang = tinhTrang;
		this.ngayThamGia = ngayThamGia;
	}

	public TaiKhoan() {
		super();
	}

	public String getIdTaiKhoan() {
		return idTaiKhoan;
	}

	public void setIdTaiKhoan(String idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
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

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getNgayThamGia() {
		return ngayThamGia;
	}

	public void setNgayThamGia(String ngayThamGia) {
		this.ngayThamGia = ngayThamGia;
	}

	public String getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}

	public void setLoaiTaiKhoan(String loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}
	
}
