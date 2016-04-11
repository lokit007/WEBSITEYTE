package model.bean;

public class TuVanOnline {
	private int idTuVan;
	private TaiKhoan taiKhoan;
	private String emailTuVan;
	private String dienThoaiTuVan;
	private String linhVucTuVan;
	private String thoiGianTuVan;
	
	public TuVanOnline(int idTuVan, TaiKhoan taiKhoan, String emailTuVan, String dienThoaiTuVan, String linhVucTuVan,
			String thoiGianTuVan) {
		super();
		this.idTuVan = idTuVan;
		this.taiKhoan = taiKhoan;
		this.emailTuVan = emailTuVan;
		this.dienThoaiTuVan = dienThoaiTuVan;
		this.linhVucTuVan = linhVucTuVan;
		this.thoiGianTuVan = thoiGianTuVan;
	}

	public TuVanOnline() {
		super();
	}

	public int getIdTuVan() {
		return idTuVan;
	}

	public void setIdTuVan(int idTuVan) {
		this.idTuVan = idTuVan;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getEmailTuVan() {
		return emailTuVan;
	}

	public void setEmailTuVan(String emailTuVan) {
		this.emailTuVan = emailTuVan;
	}

	public String getDienThoaiTuVan() {
		return dienThoaiTuVan;
	}

	public void setDienThoaiTuVan(String dienThoaiTuVan) {
		this.dienThoaiTuVan = dienThoaiTuVan;
	}

	public String getLinhVucTuVan() {
		return linhVucTuVan;
	}

	public void setLinhVucTuVan(String linhVucTuVan) {
		this.linhVucTuVan = linhVucTuVan;
	}

	public String getThoiGianTuVan() {
		return thoiGianTuVan;
	}

	public void setThoiGianTuVan(String thoiGianTuVan) {
		this.thoiGianTuVan = thoiGianTuVan;
	}
	
}
