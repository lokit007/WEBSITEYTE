package model.bean;

public class DangKyDichVu {
	private int idDangKy;
	private TaiKhoan taiKhoan;
	private int idDichVu;
	private String ngayDangKy;
	private String tinhTrang;
	private String tinNhan;
	
	public DangKyDichVu(int idDangKy, TaiKhoan taiKhoan, int idDichVu, String ngayDangKy, String tinhTrang) {
		super();
		this.idDangKy = idDangKy;
		this.taiKhoan = taiKhoan;
		this.idDichVu = idDichVu;
		this.ngayDangKy = ngayDangKy;
		this.tinhTrang = tinhTrang;
	}

	public DangKyDichVu() {
		super();
	}

	public int getIdDangKy() {
		return idDangKy;
	}

	public void setIdDangKy(int idDangKy) {
		this.idDangKy = idDangKy;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public int getIdDichVu() {
		return idDichVu;
	}

	public void setIdDichVu(int idDichVu) {
		this.idDichVu = idDichVu;
	}

	public String getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(String ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getTinNhan() {
		return tinNhan;
	}

	public void setTinNhan(String tinNhan) {
		this.tinNhan = tinNhan;
	}
	
}
