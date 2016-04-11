package model.bean;

public class TraLoi {
	private int id;
	private int key;
	private TaiKhoan taiKhoan;
	private String noiDung;
	private String ngayDang;
	
	public TraLoi(int id, int key, TaiKhoan taiKhoan, String noiDung, String ngayDang) {
		super();
		this.id = id;
		this.key = key;
		this.taiKhoan = taiKhoan;
		this.noiDung = noiDung;
		this.ngayDang = ngayDang;
	}

	public TraLoi() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getNgayDang() {
		return ngayDang;
	}

	public void setNgayDang(String ngayDang) {
		this.ngayDang = ngayDang;
	}
}
