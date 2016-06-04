package controller.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.TaiKhoan;
import model.bean.NhaCungCap;
import model.bo.TaiKhoanBO;
import model.dao.FormatData;

/**
 * ThanhVienActionSupport.java
 *
 * Version 1.0
 *
 * Date: 10-05-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 10-05-2016        	NhanHV          Create
 */

public class ThanhVienActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String txtFind;
	private int page;
	private String menu;
	private List<TaiKhoan> list;
	private List<NhaCungCap> listNCC;
	
	public String execute(){
		return NhaCungCap();
	}

	/**
	 * Danh sách quản trị viên hệ thống
	 * @param
	 * @return String result
	 */
	
	public String QuanTriVien(){
		if(page<1) page = 1;
		TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
		this.list = taiKhoanBO.getDanhSachTaiKhoan("Admin", getTxtFind()==null?"":getTxtFind(), page);
		this.menu = taiKhoanBO.getMemu();
		taiKhoanBO.closeConnect();
		return "thanh-cong";
	}
	
	/**
	 * Danh sách nhà cung cấp của hệ thống
	 * @param
	 * @return String result
	 */
	
	public String NhaCungCap(){
		if(page<1) page = 1;
		TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
		this.listNCC = taiKhoanBO.getDanhSachNCC(getTxtFind()==null?"":getTxtFind(), page);
		this.menu = taiKhoanBO.getMemu();
		taiKhoanBO.closeConnect();
		return "thanh-cong";
	}
	
	/**
	 * Danh sách người dùng hệ thống
	 * @param
	 * @return String result
	 */
	
	public String NguoiDung(){
		if(page<1) page = 1;
		TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
		this.list = taiKhoanBO.getDanhSachTaiKhoan("Người dùng", getTxtFind()==null?"":getTxtFind(), page);
		this.menu = taiKhoanBO.getMemu();
		taiKhoanBO.closeConnect();
		return "thanh-cong";
	}
	
	public String getTxtFind() {
		return FormatData.toUTF8(txtFind);
	}

	public void setTxtFind(String txtFind) {
		this.txtFind = txtFind;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public List<TaiKhoan> getList() {
		return list;
	}

	public void setList(List<TaiKhoan> list) {
		this.list = list;
	}

	public List<NhaCungCap> getListNCC() {
		return listNCC;
	}

	public void setListNCC(List<NhaCungCap> listNCC) {
		this.listNCC = listNCC;
	}
	
}
