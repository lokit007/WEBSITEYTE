package controller.admin;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DanhMuc;
import model.bo.DanhMucBO;
import model.bo.ValidateBO;

/**
 * XulyDanhMucActionSupport.java
 *
 * Version 1.0
 *
 * Date: 13-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 13-04-2016        	NhanHV          Create
 */

public class XulyDanhMucActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int idDanhMuc;
	private String tenDanhMuc;
	private List<String> hienThi;
	private DanhMuc danhMuc;
	
	/**
	 * Thêm danh mục mới
	 * @param thông tin danh mục
	 * @return String result
	 */
	
	public String themDanhMuc(){
		String hthi = "";
		String result = "thanh-cong";
		for (String chon : hienThi) {
			hthi += chon + ", ";
		}
		hthi = hthi.substring(0, hthi.length()-2);
		if(ValidateBO.CheckEmpty(tenDanhMuc)||ValidateBO.CheckEmpty(hthi)){
			addActionError("Thêm danh mục thất bại");
			result = "that-bai";
		} else {
			DanhMucBO danhMucBO = new DanhMucBO();
			if(!danhMucBO.themDanhMuc(tenDanhMuc, hthi)){
				addActionError("Thêm danh mục thất bại");
				result = "that-bai";
			} else {
				addActionMessage("Thêm danh mục thành công!");
			}
			danhMucBO.closeConnect();
		}
		return result;
	}

	/**
	 * Lấy thông tin danh mục trên hệ thống
	 * @param id danh mục
	 * @return String result
	 */
	
	public String showDanhMuc(){
		if(idDanhMuc<1)
			addActionError("Danh mục không tồn tại!");
		else {
			DanhMucBO danhMucBO = new DanhMucBO();
			danhMuc = danhMucBO.getDanhMuc(idDanhMuc);
			if(danhMuc!=null){
				hienThi = new ArrayList<String>();
				for (String chon : danhMuc.getHienThi().split(", ")) {
					hienThi.add(chon);
				}
			}
			if(danhMuc==null) addActionError("Danh mục không tồn tại!");
			danhMucBO.closeConnect();
		}
		return "thanh-cong";
	}
	
	/**
	 * Cập nhật thông tin danh mục
	 * @param id danh mục
	 * @return String result
	 */
	
	public String capNhatDanhMuc(){
		String hthi = "";
		String result = "thanh-cong";
		for (String chon : hienThi) {
			hthi += chon + ", ";
		}
		hthi = hthi.substring(0, hthi.length()-2);
		if(ValidateBO.CheckEmpty(tenDanhMuc)||ValidateBO.CheckEmpty(hthi)){
			addActionError("Cập nhật danh mục thất bại");
			result = "that-bai";
		} else {
			DanhMucBO danhMucBO = new DanhMucBO();
			if(!danhMucBO.capNhatDanhMuc(idDanhMuc, tenDanhMuc, hthi)){
				addActionError("Cập nhật danh mục thất bại");
				result = "that-bai";
			} else {
				addActionMessage("Cập nhật danh mục thành công!");
			}
			danhMucBO.closeConnect();
		}
		return result;
	}
	
	/**
	 * Xóa danh mục khỏi hệ thống
	 * @param id danh mục
	 * @return String result
	 */
	
	public String xoaDanhMuc(){
		if(idDanhMuc<1){
			addActionError("Xóa danh mục "+idDanhMuc+" thất bại!");
			return "that-bai";
		} else {
			DanhMucBO danhMucBO = new DanhMucBO();
			danhMucBO.xoaDanhMuc(idDanhMuc+"");
			danhMucBO.closeConnect();
		}
		return "thanh-cong";
	}

	public int getIdDanhMuc() {
		return idDanhMuc;
	}

	public void setIdDanhMuc(int idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public List<String> getHienThi() {
		return hienThi;
	}

	public void setHienThi(List<String> hienThi) {
		this.hienThi = hienThi;
	}

	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}
	
}
