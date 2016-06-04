package controller.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.KhachHang;
import model.bean.QuangCao;
import model.bo.QuangCaoBO;

/**
 * QuanCaoActionSupport.java
 *
 * Version 1.0
 *
 * Date: 08-05-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 08-05-2016        	NhanHV          Create
 */

public class QuanCaoActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<QuangCao> listHT;
	private List<QuangCao> listCho;
	private List<KhachHang> listKH;
	private List<QuangCao> listDangKy;
	private int page;
	
	/**
	 * Lấy danh sách quảng cáo trên hệ thống
	 * @param
	 * @return String result
	 */
	
	public String execute(){
		String result = "thanh-cong";
		QuangCaoBO quangCaoBO = new QuangCaoBO();
		this.listHT = quangCaoBO.getDanhSachHienThi();
		this.listCho = quangCaoBO.getDanhSachCho(page);
		this.listDangKy = quangCaoBO.getDanhSachDangKy(page);
		this.listKH = quangCaoBO.getDanhSachKhachHang(page);
		quangCaoBO.closeConnect();
		return result;
	}

	public List<QuangCao> getListHT() {
		return listHT;
	}

	public void setListHT(List<QuangCao> listHT) {
		this.listHT = listHT;
	}

	public List<QuangCao> getListCho() {
		return listCho;
	}

	public void setListCho(List<QuangCao> listCho) {
		this.listCho = listCho;
	}

	public List<KhachHang> getListKH() {
		return listKH;
	}

	public void setListKH(List<KhachHang> listKH) {
		this.listKH = listKH;
	}

	public List<QuangCao> getListDangKy() {
		return listDangKy;
	}

	public void setListDangKy(List<QuangCao> listDangKy) {
		this.listDangKy = listDangKy;
	}
	
}
