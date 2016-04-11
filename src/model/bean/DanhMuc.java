package model.bean;

public class DanhMuc {
	private int idDanhMuc;
	private String tenDanhMuc;
	private String hienThi;
	private int soBaiViet = 0;
	
	public DanhMuc(int idDanhMuc, String tenDanhMuc, String hienThi, int soBaiViet) {
		super();
		this.idDanhMuc = idDanhMuc;
		this.tenDanhMuc = tenDanhMuc;
		this.hienThi = hienThi;
		this.soBaiViet = soBaiViet;
	}

	public DanhMuc() {
		super();
	}

	public int getIdDanhMuc() {
		return idDanhMuc;
	}

	public void setIdDanhMuc(int idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public String getHienThi() {
		return hienThi;
	}

	public void setHienThi(String hienThi) {
		this.hienThi = hienThi;
	}

	public int getSoBaiViet() {
		return soBaiViet;
	}

	public void setSoBaiViet(int soBaiViet) {
		this.soBaiViet = soBaiViet;
	}
	
}
