package model.bean.delete;

public class QuangCao {
	private int idQuangCao;
	private String loGoQuangCao;
	private String lienKet;
	private ViTriQuangCao viTriQuangCao;
	private DonViQuangCao khachHang;
	private String tinhTrang;
	private String ngayDangKy;
	private String ngayKetThuc;
	private int chiPhiQuangCao;
	
	public QuangCao(int idQuangCao, String loGoQuangCao, String lienKet, ViTriQuangCao viTriQuangCao,
			DonViQuangCao khachHang, String tinhTrang, String ngayDangKy, String ngayKetThuc, int chiPhiQuangCao) {
		super();
		this.idQuangCao = idQuangCao;
		this.loGoQuangCao = loGoQuangCao;
		this.lienKet = lienKet;
		this.viTriQuangCao = viTriQuangCao;
		this.khachHang = khachHang;
		this.tinhTrang = tinhTrang;
		this.ngayDangKy = ngayDangKy;
		this.ngayKetThuc = ngayKetThuc;
		this.chiPhiQuangCao = chiPhiQuangCao;
	}

	public QuangCao() {
		super();
	}

	public int getIdQuangCao() {
		return idQuangCao;
	}

	public void setIdQuangCao(int idQuangCao) {
		this.idQuangCao = idQuangCao;
	}

	public String getLoGoQuangCao() {
		return loGoQuangCao;
	}

	public void setLoGoQuangCao(String loGoQuangCao) {
		this.loGoQuangCao = loGoQuangCao;
	}

	public String getLienKet() {
		return lienKet;
	}

	public void setLienKet(String lienKet) {
		this.lienKet = lienKet;
	}

	public ViTriQuangCao getViTriQuangCao() {
		return viTriQuangCao;
	}

	public void setViTriQuangCao(ViTriQuangCao viTriQuangCao) {
		this.viTriQuangCao = viTriQuangCao;
	}

	public DonViQuangCao getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(DonViQuangCao khachHang) {
		this.khachHang = khachHang;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(String ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	public String getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(String ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public int getChiPhiQuangCao() {
		return chiPhiQuangCao;
	}

	public void setChiPhiQuangCao(int chiPhiQuangCao) {
		this.chiPhiQuangCao = chiPhiQuangCao;
	}
	
}
