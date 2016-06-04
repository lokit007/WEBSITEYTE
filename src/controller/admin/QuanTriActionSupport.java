package controller.admin;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.QuanTriBO;

/**
 * QuanTriActionSupport.java
 *
 * Version 1.0
 *
 * Date: 28-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 28-04-2016        	NhanHV          Create
 */

public class QuanTriActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String taiKhoan;
	private String nameCol;
	private boolean valCheck;
	private boolean danhMuc;
	private boolean dichVu;
	private boolean nhuCau;
	private boolean chiaSe;
	private boolean nhaCungCap;
	private boolean taiNguyen;
	private boolean thongKe;
	private boolean quangCao;
	
	/**
	 * Thêm quản trị viên hệ thống
	 * @param
	 * @return String result
	 */
	
	public String execute(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "four");
		String result = "thanh-cong";
		if(!"".equals(taiKhoan)&&(danhMuc||dichVu||nhuCau||chiaSe||nhaCungCap||taiNguyen||thongKe||quangCao)){
			QuanTriBO quanTriBO = new QuanTriBO();
			if(!quanTriBO.CapQuyenQuanTri(taiKhoan, danhMuc, dichVu, nhuCau, chiaSe, nhaCungCap, taiNguyen, thongKe, quangCao)){
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

	/**
	 * Hủy quyền quản trị viên
	 * @param
	 * @return String result
	 */
	
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
	
	/**
	 * Cập nhật quyền quản lý của quản trị
	 * @param
	 * @return String result
	 */
	
	public String CapNhatQuyenHan(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "four");
		String result = "thanh-cong";
		if(!"".equals(taiKhoan)){
			QuanTriBO quanTriBO = new QuanTriBO();
			if(!quanTriBO.CapNhatQuyenHan(taiKhoan, nameCol, valCheck)){
				result = "that-bai";
				addActionError("Cập nhật quyền quản trị thất bại!");
			}
			quanTriBO.closeConnect();
		} else {
			result = "that-bai";
			addActionError("Cập nhật quyền quản trị thất bại!");
		}
		return result;
	}
	
	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getNameCol() {
		return nameCol;
	}

	public void setNameCol(String nameCol) {
		this.nameCol = nameCol;
	}

	public boolean isValCheck() {
		return valCheck;
	}

	public void setValCheck(boolean valCheck) {
		this.valCheck = valCheck;
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

	public boolean isQuangCao() {
		return quangCao;
	}

	public void setQuangCao(boolean quangCao) {
		this.quangCao = quangCao;
	}
	
}
