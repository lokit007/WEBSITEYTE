package model.bean.delete;

public class ViTriQuangCao {
	private int idViTri;
	private String tenViTri;
	private int giaQuangCao;
	
	public ViTriQuangCao(int idViTri, String tenViTri, int giaQuangCao) {
		super();
		this.idViTri = idViTri;
		this.tenViTri = tenViTri;
		this.giaQuangCao = giaQuangCao;
	}

	public ViTriQuangCao() {
		super();
	}

	public int getIdViTri() {
		return idViTri;
	}

	public void setIdViTri(int idViTri) {
		this.idViTri = idViTri;
	}

	public String getTenViTri() {
		return tenViTri;
	}

	public void setTenViTri(String tenViTri) {
		this.tenViTri = tenViTri;
	}

	public int getGiaQuangCao() {
		return giaQuangCao;
	}

	public void setGiaQuangCao(int giaQuangCao) {
		this.giaQuangCao = giaQuangCao;
	}
	
}
