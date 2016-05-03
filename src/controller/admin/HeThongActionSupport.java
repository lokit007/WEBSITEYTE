package controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.QuanTri;
import model.bean.TaiKhoan;
import model.bean.TaiNguyen;
import model.bo.HeThongBO;
import model.bo.QuanTriBO;

public class HeThongActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private TaiNguyen taiNguyen;
	private String tenHeThong;
	private String banQuyen;
	private String diaChi;
	private String dienThoai;
	private String fax;
	private String email;
	private String soLuongTruyCap;
	//quy định
	private String noiQuyThanhVien;
	private String quyDinhDangDichVu;
	private String quyDinhDangNhuCau;
	//liên kết
	private String tenWebsite;
	private String diaChiWeb;
	//phân quyền
	private List<QuanTri> listQuanTri;
	private List<TaiKhoan> listTaiKhoan;
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public String execute(){
		String result = "thanh-cong";
		this.taiNguyen = (TaiNguyen)request.getSession().getAttribute("TaiNguyen");
		if(this.taiNguyen==null){
			HeThongBO heThongBO = new HeThongBO();
			this.taiNguyen = heThongBO.getTaiNguyen();
			if(this.taiNguyen!=null)
				request.getSession().setAttribute("TaiNguyen", this.taiNguyen);
			else
				result = "that-bai";
			heThongBO.closeConnect();
		}
		QuanTriBO quanTriBO = new QuanTriBO();
		this.listQuanTri = quanTriBO.getDanhSachQuanTri();
		this.listTaiKhoan = quanTriBO.getTaiKhoanThem();
		quanTriBO.closeConnect();
		return result;
	}
	
	public String CapNhatThongTin(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "one");
		String result = "thanh-cong";
		HeThongBO heThongBO = new HeThongBO();
		this.taiNguyen = (TaiNguyen)request.getSession().getAttribute("TaiNguyen");
		if(this.taiNguyen!=null){
			this.taiNguyen.setTenHeThong(this.tenHeThong);
			this.taiNguyen.setBanQuyen(this.banQuyen);
			this.taiNguyen.setDiaChi(this.diaChi);
			this.taiNguyen.setDienThoai(this.dienThoai);
			this.taiNguyen.setFax(this.fax);
			this.taiNguyen.setEmail(this.email);
			this.taiNguyen.setSoLuongTruyCap("".equals(this.soLuongTruyCap)?"0":this.soLuongTruyCap);
			heThongBO.CapNhatThongTin(this.tenHeThong, this.banQuyen, this.diaChi, this.dienThoai, this.fax, this.email, this.taiNguyen.getSoLuongTruyCap());
			request.getSession().setAttribute("TaiNguyen", this.taiNguyen);
		}
		heThongBO.closeConnect();
		return result;
	}
	
	public String CapNhatNoiQuy(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "two");
		String result = "thanh-cong";
		HeThongBO heThongBO = new HeThongBO();
		this.taiNguyen = (TaiNguyen)request.getSession().getAttribute("TaiNguyen");
		if(this.taiNguyen!=null){
			this.taiNguyen.setNoiQuyThanhVien(this.noiQuyThanhVien);
			this.taiNguyen.setQuyDinhDangDichVu(this.quyDinhDangDichVu);
			this.taiNguyen.setQuyDinhDangNhuCau(this.quyDinhDangNhuCau);
			heThongBO.CapNhatNoiQuy(this.noiQuyThanhVien, this.quyDinhDangDichVu, this.quyDinhDangNhuCau);
			request.getSession().setAttribute("TaiNguyen", this.taiNguyen);
		}
		heThongBO.closeConnect();
		return result;
	}
	
	public String ThemLienKet(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "three");
		String result = "thanh-cong";
		if(!"".equals(tenWebsite)&&!"".equals(diaChiWeb)){
			HeThongBO heThongBO = new HeThongBO();
			if(heThongBO.ThemLienKet(tenWebsite, diaChiWeb)){
				this.taiNguyen = (TaiNguyen)request.getSession().getAttribute("TaiNguyen");
				this.taiNguyen.getLienKet().put(tenWebsite, diaChiWeb);
				request.getSession().setAttribute("TaiNguyen", this.taiNguyen);
			}
			heThongBO.closeConnect();
		}
		return result;
	}
	
	public String CapNhatLienKet(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "three");
		String result = "thanh-cong";
		if(!"".equals(tenWebsite)&&!"".equals(diaChiWeb)){
			HeThongBO heThongBO = new HeThongBO();
			if(heThongBO.CapNhatLienKet(tenWebsite, diaChiWeb)){
				this.taiNguyen = (TaiNguyen)request.getSession().getAttribute("TaiNguyen");
				this.taiNguyen.getLienKet().put(tenWebsite, diaChiWeb);
				request.getSession().setAttribute("TaiNguyen", this.taiNguyen);
			}
			heThongBO.closeConnect();
		}
		return result;
	}
	
	public String XoaLienKet(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "three");
		String result = "thanh-cong";
		if(!"".equals(tenWebsite)){
			HeThongBO heThongBO = new HeThongBO();
			if(heThongBO.XoaLienKet(tenWebsite)){
				this.taiNguyen = (TaiNguyen)request.getSession().getAttribute("TaiNguyen");
				this.taiNguyen.getLienKet().remove(tenWebsite);
				request.getSession().setAttribute("TaiNguyen", this.taiNguyen);
			}
			heThongBO.closeConnect();
		}
		return result;
	}
	
	public String PhanQuyenQuanTri(){
		String result = "thanh-cong";
		QuanTriBO quanTriBO = new QuanTriBO();
		this.listQuanTri = quanTriBO.getDanhSachQuanTri();
		this.listTaiKhoan = quanTriBO.getTaiKhoanThem();
		quanTriBO.closeConnect();
		return result;
	}
	
	public TaiNguyen getTaiNguyen() {
		return taiNguyen;
	}

	public void setTaiNguyen(TaiNguyen taiNguyen) {
		this.taiNguyen = taiNguyen;
	}

	public String getTenHeThong() {
		return tenHeThong;
	}

	public void setTenHeThong(String tenHeThong) {
		this.tenHeThong = tenHeThong;
	}

	public String getBanQuyen() {
		return banQuyen;
	}

	public void setBanQuyen(String banQuyen) {
		this.banQuyen = banQuyen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoLuongTruyCap() {
		return soLuongTruyCap;
	}

	public void setSoLuongTruyCap(String soLuongTruyCap) {
		this.soLuongTruyCap = soLuongTruyCap;
	}

	public String getNoiQuyThanhVien() {
		return noiQuyThanhVien;
	}

	public void setNoiQuyThanhVien(String noiQuyThanhVien) {
		this.noiQuyThanhVien = noiQuyThanhVien;
	}

	public String getQuyDinhDangDichVu() {
		return quyDinhDangDichVu;
	}

	public void setQuyDinhDangDichVu(String quyDinhDangDichVu) {
		this.quyDinhDangDichVu = quyDinhDangDichVu;
	}

	public String getQuyDinhDangNhuCau() {
		return quyDinhDangNhuCau;
	}

	public void setQuyDinhDangNhuCau(String quyDinhDangNhuCau) {
		this.quyDinhDangNhuCau = quyDinhDangNhuCau;
	}

	public String getTenWebsite() {
		return tenWebsite;
	}

	public void setTenWebsite(String tenWebsite) {
		this.tenWebsite = tenWebsite;
	}

	public String getDiaChiWeb() {
		return diaChiWeb;
	}

	public void setDiaChiWeb(String diaChiWeb) {
		this.diaChiWeb = diaChiWeb;
	}

	public List<QuanTri> getListQuanTri() {
		return listQuanTri;
	}

	public void setListQuanTri(List<QuanTri> listQuanTri) {
		this.listQuanTri = listQuanTri;
	}

	public List<TaiKhoan> getListTaiKhoan() {
		return listTaiKhoan;
	}

	public void setListTaiKhoan(List<TaiKhoan> listTaiKhoan) {
		this.listTaiKhoan = listTaiKhoan;
	}
	
}
