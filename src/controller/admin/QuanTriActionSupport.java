package controller.admin;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.QuanTriBO;

public class QuanTriActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String taiKhoan;
	private boolean danhMuc;
	private boolean dichVu;
	private boolean nhuCau;
	private boolean chiaSe;
	private boolean nhaCungCap;
	private boolean taiNguyen;
	private boolean thongKe;
	
	public String execute(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "four");
		String result = "thanh-cong";
		if(!"".equals(taiKhoan)&&(danhMuc||dichVu||nhuCau||chiaSe||nhaCungCap||taiNguyen||thongKe)){
			QuanTriBO quanTriBO = new QuanTriBO();
			if(!quanTriBO.CapQuyenQuanTri(taiKhoan, danhMuc, dichVu, nhuCau, chiaSe, nhaCungCap, taiNguyen, thongKe)){
				result = "that-bai";
				addActionError("Thêm quyền quản trị thất bại!");
			}
			quanTriBO.closeConnect();
		} else {
			result = "that-bai";
			addActionError("Thêm quyền quản trị thất bại!");
		}
		return result;
	}

	public String HuyQuyenQuanTri(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "four");
		String result = "thanh-cong";
		if(!"".equals(taiKhoan)){
			QuanTriBO quanTriBO = new QuanTriBO();
			if(!quanTriBO.HuyQuyenQuanTri(taiKhoan)){
				result = "that-bai";
				addActionError("Hủy quyền quản trị thất bại!");
			}
			quanTriBO.closeConnect();
		} else {
			result = "that-bai";
			addActionError("Hủy quyền quản trị thất bại!");
		}
		return result;
	}
	
	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public boolean isDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(boolean danhMuc) {
		this.danhMuc = danhMuc;
	}

	public boolean isDichVu() {
		return dichVu;
	}

	public void setDichVu(boolean dichVu) {
		this.dichVu = dichVu;
	}

	public boolean isNhuCau() {
		return nhuCau;
	}

	public void setNhuCau(boolean nhuCau) {
		this.nhuCau = nhuCau;
	}

	public boolean isChiaSe() {
		return chiaSe;
	}

	public void setChiaSe(boolean chiaSe) {
		this.chiaSe = chiaSe;
	}

	public boolean isNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(boolean nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public boolean isTaiNguyen() {
		return taiNguyen;
	}

	public void setTaiNguyen(boolean taiNguyen) {
		this.taiNguyen = taiNguyen;
	}

	public boolean isThongKe() {
		return thongKe;
	}

	public void setThongKe(boolean thongKe) {
		this.thongKe = thongKe;
	}
	
}
