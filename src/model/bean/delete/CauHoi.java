package model.bean.delete;

public class CauHoi {
	private int idCauHoi;
	private String tietDeCauHoi;
	private String noiDungCauHoi;
	private String ngayHoi;
	private String nguoiHoi;
	private String emailNhanTin;
	
	public CauHoi(int idCauHoi, String tietDeCauHoi, String noiDungCauHoi, String ngayHoi, String nguoiHoi,
			String emailNhanTin) {
		super();
		this.idCauHoi = idCauHoi;
		this.tietDeCauHoi = tietDeCauHoi;
		this.noiDungCauHoi = noiDungCauHoi;
		this.ngayHoi = ngayHoi;
		this.nguoiHoi = nguoiHoi;
		this.emailNhanTin = emailNhanTin;
	}

	public CauHoi() {
		super();
	}

	public int getIdCauHoi() {
		return idCauHoi;
	}

	public void setIdCauHoi(int idCauHoi) {
		this.idCauHoi = idCauHoi;
	}

	public String getTietDeCauHoi() {
		return tietDeCauHoi;
	}

	public void setTietDeCauHoi(String tietDeCauHoi) {
		this.tietDeCauHoi = tietDeCauHoi;
	}

	public String getNoiDungCauHoi() {
		return noiDungCauHoi;
	}

	public void setNoiDungCauHoi(String noiDungCauHoi) {
		this.noiDungCauHoi = noiDungCauHoi;
	}

	public String getNgayHoi() {
		return ngayHoi;
	}

	public void setNgayHoi(String ngayHoi) {
		this.ngayHoi = ngayHoi;
	}

	public String getNguoiHoi() {
		return nguoiHoi;
	}

	public void setNguoiHoi(String nguoiHoi) {
		this.nguoiHoi = nguoiHoi;
	}

	public String getEmailNhanTin() {
		return emailNhanTin;
	}

	public void setEmailNhanTin(String emailNhanTin) {
		this.emailNhanTin = emailNhanTin;
	}
	
}
