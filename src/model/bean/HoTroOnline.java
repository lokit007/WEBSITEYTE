package model.bean;

public class HoTroOnline {
	private String idHoTro;
	private String nhaCungCap;
	private String thoiGianHoTro;
	private DanhMuc danhMuc;
	private String emailLienHe;
	private String dienThoaiLienHe;
	private String nickYahooChat;
	private String nickSkypeChat;
	
	public HoTroOnline(String idHoTro, String nhaCungCap, String thoiGianHoTro, DanhMuc danhMuc, String emailLienHe,
			String dienThoaiLienHe, String nickYahooChat, String nickSkypeChat) {
		super();
		this.idHoTro = idHoTro;
		this.nhaCungCap = nhaCungCap;
		this.thoiGianHoTro = thoiGianHoTro;
		this.danhMuc = danhMuc;
		this.emailLienHe = emailLienHe;
		this.dienThoaiLienHe = dienThoaiLienHe;
		this.nickYahooChat = nickYahooChat;
		this.nickSkypeChat = nickSkypeChat;
	}

	public HoTroOnline() {
		super();
	}

	public String getIdHoTro() {
		return idHoTro;
	}

	public void setIdHoTro(String idHoTro) {
		this.idHoTro = idHoTro;
	}

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public String getThoiGianHoTro() {
		return thoiGianHoTro;
	}

	public void setThoiGianHoTro(String thoiGianHoTro) {
		this.thoiGianHoTro = thoiGianHoTro;
	}

	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}

	public String getEmailLienHe() {
		return emailLienHe;
	}

	public void setEmailLienHe(String emailLienHe) {
		this.emailLienHe = emailLienHe;
	}

	public String getDienThoaiLienHe() {
		return dienThoaiLienHe;
	}

	public void setDienThoaiLienHe(String dienThoaiLienHe) {
		this.dienThoaiLienHe = dienThoaiLienHe;
	}

	public String getNickYahooChat() {
		return nickYahooChat;
	}

	public void setNickYahooChat(String nickYahooChat) {
		this.nickYahooChat = nickYahooChat;
	}

	public String getNickSkypeChat() {
		return nickSkypeChat;
	}

	public void setNickSkypeChat(String nickSkypeChat) {
		this.nickSkypeChat = nickSkypeChat;
	}
	
}
