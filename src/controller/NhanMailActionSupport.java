package controller;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.ValidateBO;

/**
 * NhanMailActionSupport.java
 *
 * Version 1.0
 *
 * Date: 10-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 10-04-2016        	NhanHV          Create
 */

public class NhanMailActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String loaiTin;
	private String email;
	
	/**
	 * Cập nhật email người đăng ký nhận thông tin từ hệ thống
	 * @param id dịch vụ
	 * @return String result
	 */
	
	public String DangKyMail(){
		if(ValidateBO.DangKyMail(this.loaiTin, this.email)) return "thanh-cong";
		else {
			addActionError("Đăng ký thất bại!");
			return "that-bai";
		}
	}

	public String getLoaiTin() {
		return loaiTin;
	}

	public void setLoaiTin(String loaiTin) {
		this.loaiTin = loaiTin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
