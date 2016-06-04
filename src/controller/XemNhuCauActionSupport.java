package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BinhLuan;
import model.bean.DangKyDichVu;
import model.bean.DichVu;
import model.bo.BinhLuanBO;
import model.bo.DichVuBO;

/**
 * XemNhuCauActionSupport.java
 *
 * Version 1.0
 *
 * Date: 24-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 24-04-2016        	NhanHV          Create
 */

public class XemNhuCauActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String idNhuCau;
	private DichVu nhuCau;
	private List<DichVu> list;
	private List<BinhLuan> listBinhLuan;
	private List<DangKyDichVu> listDangKy;
	
	/**
	 * Lấy thông tin nhu cầu cần xem chi tiết
	 * @param id nhu cầu cần xem
	 * @return String result
	 */
	
	public String execute(){
		DichVuBO dichVuBO = new DichVuBO();
		BinhLuanBO binhLuanBO = new BinhLuanBO();
		this.nhuCau = dichVuBO.getNhuCau(this.idNhuCau, 1);
		if(this.nhuCau!=null){
			this.list = dichVuBO.getListNhuCau(nhuCau.getDienThoaiLienHe(), 
					nhuCau.getBaiViet().getDanhMuc().getIdDanhMuc()+"", "0");
			this.listBinhLuan = binhLuanBO.getListBinhLuan(nhuCau.getBaiViet().getIdBaiViet()+"");
			this.listDangKy = dichVuBO.getDanhSachDangKy(this.nhuCau.getIdDichVu());
		}
		dichVuBO.closeConnect();
		binhLuanBO.closeConnect();
		if(this.nhuCau==null) return "that-bai";
		return "thanh-cong";
	}

	public String getIdNhuCau() {
		return idNhuCau;
	}

	public void setIdNhuCau(String idNhuCau) {
		this.idNhuCau = idNhuCau;
	}

	public DichVu getNhuCau() {
		return nhuCau;
	}

	public void setNhuCau(DichVu nhuCau) {
		this.nhuCau = nhuCau;
	}

	public List<DichVu> getList() {
		return list;
	}

	public void setList(List<DichVu> list) {
		this.list = list;
	}

	public List<BinhLuan> getListBinhLuan() {
		return listBinhLuan;
	}

	public void setListBinhLuan(List<BinhLuan> listBinhLuan) {
		this.listBinhLuan = listBinhLuan;
	}

	public List<DangKyDichVu> getListDangKy() {
		return listDangKy;
	}

	public void setListDangKy(List<DangKyDichVu> listDangKy) {
		this.listDangKy = listDangKy;
	}
	
}
