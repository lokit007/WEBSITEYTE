package model.bean.delete;

public class TraLoi {
	private int idTraLoi;
	private int idCauHoi;
	private String nguoiTraLoi;
	private String noiDUngTraLoi;
	private String ngayTraLoi;
	
	public TraLoi(int idTraLoi, int idCauHoi, String nguoiTraLoi, String noiDUngTraLoi, String ngayTraLoi) {
		super();
		this.idTraLoi = idTraLoi;
		this.idCauHoi = idCauHoi;
		this.nguoiTraLoi = nguoiTraLoi;
		this.noiDUngTraLoi = noiDUngTraLoi;
		this.ngayTraLoi = ngayTraLoi;
	}

	public TraLoi() {
		super();
	}

	public int getIdTraLoi() {
		return idTraLoi;
	}

	public void setIdTraLoi(int idTraLoi) {
		this.idTraLoi = idTraLoi;
	}

	public int getIdCauHoi() {
		return idCauHoi;
	}

	public void setIdCauHoi(int idCauHoi) {
		this.idCauHoi = idCauHoi;
	}

	public String getNguoiTraLoi() {
		return nguoiTraLoi;
	}

	public void setNguoiTraLoi(String nguoiTraLoi) {
		this.nguoiTraLoi = nguoiTraLoi;
	}

	public String getNoiDUngTraLoi() {
		return noiDUngTraLoi;
	}

	public void setNoiDUngTraLoi(String noiDUngTraLoi) {
		this.noiDUngTraLoi = noiDUngTraLoi;
	}

	public String getNgayTraLoi() {
		return ngayTraLoi;
	}

	public void setNgayTraLoi(String ngayTraLoi) {
		this.ngayTraLoi = ngayTraLoi;
	}
	
}
