package model.bean;

public class BaiViet {
	private int idBaiViet;
	private DanhMuc danhMuc;
	private String tenBaiViet;
	private TaiKhoan taiKhoan;
	private String tenTacGia;
	private String moTa;
	private String noiDung;
	private String tinhTrang;
	private String ngayDang;
	private String anhMoTa;
	private int luocXem;
	
	public BaiViet(int idBaiViet, DanhMuc danhMuc, String tenBaiViet, TaiKhoan taiKhoan, String tenTacGia, String moTa,
			String noiDung, String tinhTrang, String ngayDang, String anhMoTa, int luocXem) {
		super();
		this.idBaiViet = idBaiViet;
		this.danhMuc = danhMuc;
		this.tenBaiViet = tenBaiViet;
		this.taiKhoan = taiKhoan;
		this.tenTacGia = tenTacGia;
		this.moTa = moTa;
		this.noiDung = noiDung;
		this.tinhTrang = tinhTrang;
		this.ngayDang = ngayDang;
		this.anhMoTa = anhMoTa;
		this.luocXem = luocXem;
	}

	public BaiViet() {
		super();
	}

	public int getIdBaiViet() {
		return idBaiViet;
	}

	public void setIdBaiViet(int idBaiViet) {
		this.idBaiViet = idBaiViet;
	}

	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}

	public String getTenBaiViet() {
		return tenBaiViet;
	}

	public void setTenBaiViet(String tenBaiViet) {
		this.tenBaiViet = tenBaiViet;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getTenTacGia() {
		return tenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getNgayDang() {
		return ngayDang;
	}

	public void setNgayDang(String ngayDang) {
		this.ngayDang = ngayDang;
	}

	public String getAnhMoTa() {
		return anhMoTa;
	}

	public void setAnhMoTa(String anhMoTa) {
		this.anhMoTa = anhMoTa;
	}

	public int getLuocXem() {
		return luocXem;
	}

	public void setLuocXem(int luocXem) {
		this.luocXem = luocXem;
	}
	
}
