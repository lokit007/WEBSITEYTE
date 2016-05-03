package model.bean;

public class QuangCao {
	private int idQuangCao;
	private KhachHang khachHang;
	private ViTriQuangCao viTri;
	private String logoQuangBa;
	private String linkQuangBa;
	private String ngayBatDau;
	private String ngayKetThuc;
	private float chiPhiQuangCao;
	private int tinhTrang;
	
	public QuangCao() {
		super();
	}

	public int getIdQuangCao() {
		return idQuangCao;
	}

	public void setIdQuangCao(int idQuangCao) {
		this.idQuangCao = idQuangCao;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public ViTriQuangCao getViTri() {
		return viTri;
	}

	public void setViTri(ViTriQuangCao viTri) {
		this.viTri = viTri;
	}

	public String getLogoQuangBa() {
		return logoQuangBa;
	}

	public void setLogoQuangBa(String logoQuangBa) {
		this.logoQuangBa = logoQuangBa;
	}

	public String getLinkQuangBa() {
		return linkQuangBa;
	}

	public void setLinkQuangBa(String linkQuangBa) {
		this.linkQuangBa = linkQuangBa;
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

	public float getChiPhiQuangCao() {
		return chiPhiQuangCao;
	}

	public void setChiPhiQuangCao(float chiPhiQuangCao) {
		this.chiPhiQuangCao = chiPhiQuangCao;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
}
