package model.bean;

public class NhaCungCap {
	private int idNhaCungCap;
	private TaiKhoan taiKhoan;
	private String gioiThieu;
	private String chungChi;
	private String fileKem;
	private int danhGia;
	private String loaiNCC;
	
	public NhaCungCap(int idNhaCungCap, TaiKhoan taiKhoan, String gioiThieu, String chungChi, String fileKem,
			int danhGia) {
		super();
		this.idNhaCungCap = idNhaCungCap;
		this.taiKhoan = taiKhoan;
		this.gioiThieu = gioiThieu;
		this.chungChi = chungChi;
		this.fileKem = fileKem;
		this.danhGia = danhGia;
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

	public String getGioiThieu() {
		return gioiThieu;
	}

	public void setGioiThieu(String gioiThieu) {
		this.gioiThieu = gioiThieu;
	}

	public String getChungChi() {
		return chungChi;
	}

	public void setChungChi(String chungChi) {
		this.chungChi = chungChi;
	}

	public String getFileKem() {
		return fileKem;
	}

	public void setFileKem(String fileKem) {
		this.fileKem = fileKem;
	}

	public int getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(int danhGia) {
		this.danhGia = danhGia;
	}

	public String getLoaiNCC() {
		return loaiNCC;
	}

	public void setLoaiNCC(String loaiNCC) {
		this.loaiNCC = loaiNCC;
	}
	
}
