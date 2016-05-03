package model.bean;

public class ViTriQuangCao {
	private String tenViTri;
	private float giaDangTai;
	
	public ViTriQuangCao(String tenViTri, float giaDangTai) {
		super();
		this.tenViTri = tenViTri;
		this.giaDangTai = giaDangTai;
	}

	public String getTenViTri() {
		return tenViTri;
	}

	public void setTenViTri(String tenViTri) {
		this.tenViTri = tenViTri;
	}

	public float getGiaDangTai() {
		return giaDangTai;
	}

	public void setGiaDangTai(float giaDangTai) {
		this.giaDangTai = giaDangTai;
	}
	
}
