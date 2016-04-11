package model.bean;

public class DichVu {
	private int idDichVu;
	private int idNhaCungCap;
	private BaiViet baiViet;
	private String ngayBatDau;
	private String ngayKetThuc;
	private String dienThoaiLienHe;
	private String emailLienHe;
	private String loaiHinhDichVu;
	private String diaChiTrienKhai;
	
	public DichVu(int idDichVu, BaiViet baiViet, String ngayBatDau, String ngayKetThuc, String dienThoaiLienHe,
			String emailLienHe) {
		super();
		this.idDichVu = idDichVu;
		this.baiViet = baiViet;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.dienThoaiLienHe = dienThoaiLienHe;
		this.emailLienHe = emailLienHe;
	}

	public DichVu() {
		super();
	}

	public int getIdDichVu() {
		return idDichVu;
	}

	public void setIdDichVu(int idDichVu) {
		this.idDichVu = idDichVu;
	}

	public int getIdNhaCungCap() {
		return idNhaCungCap;
	}

	public void setIdNhaCungCap(int idNhaCungCap) {
		this.idNhaCungCap = idNhaCungCap;
	}

	public BaiViet getBaiViet() {
		return baiViet;
	}

	public void setBaiViet(BaiViet baiViet) {
		this.baiViet = baiViet;
	}

	public String getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(String ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public String getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(String ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getDienThoaiLienHe() {
		return dienThoaiLienHe;
	}

	public void setDienThoaiLienHe(String dienThoaiLienHe) {
		this.dienThoaiLienHe = dienThoaiLienHe;
	}

	public String getEmailLienHe() {
		return emailLienHe;
	}

	public void setEmailLienHe(String emailLienHe) {
		this.emailLienHe = emailLienHe;
	}

	public String getLoaiHinhDichVu() {
		return loaiHinhDichVu;
	}

	public void setLoaiHinhDichVu(String loaiHinhDichVu) {
		this.loaiHinhDichVu = loaiHinhDichVu;
	}

	public String getDiaChiTrienKhai() {
		return diaChiTrienKhai;
	}

	public void setDiaChiTrienKhai(String diaChiTrienKhai) {
		this.diaChiTrienKhai = diaChiTrienKhai;
	}
	
}
