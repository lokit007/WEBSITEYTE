package model.bean.delete;

public class DonViQuangCao {
	private int idDonVi;
	private String tenDonVi;
	private String diaChi;
	private String email;
	private String dienThoai;
	
	public DonViQuangCao(int idDonVi, String tenDonVi, String diaChi, String email, String dienThoai) {
		super();
		this.idDonVi = idDonVi;
		this.tenDonVi = tenDonVi;
		this.diaChi = diaChi;
		this.email = email;
		this.dienThoai = dienThoai;
	}

	public DonViQuangCao() {
		super();
	}

	public int getIdDonVi() {
		return idDonVi;
	}

	public void setIdDonVi(int idDonVi) {
		this.idDonVi = idDonVi;
	}

	public String getTenDonVi() {
		return tenDonVi;
	}

	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}
	
}
