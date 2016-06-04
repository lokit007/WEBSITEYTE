package controller.admin;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.TaiKhoanBO;
import model.bo.ValidateBO;

/**
 * XulyTaiKhoanActionSupport.java
 *
 * Version 1.0
 *
 * Date: 27-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 27-04-2016        	NhanHV          Create
 */

public class XulyTaiKhoanActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String idTaiKhoan;
	private String tinhTrang;
	
	/**
	 * Cập nhật trạng thái người dùng
	 * @param id người dùng, trạng thái cập nhật
	 * @return String result
	 */
	
	public String XulyNguoiDung(){
		if(ValidateBO.CheckEmpty(idTaiKhoan)||ValidateBO.CheckEmpty(tinhTrang)){
			addActionError("Tài khoản không tồn tại!");
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			if(!taiKhoanBO.khoaNguoiDung(idTaiKhoan, tinhTrang)){
				addActionError("Lỗi cập nhật dữ liệu hệ thống!");
			}
			taiKhoanBO.closeConnect();
		}
		return "thanh-cong";
	}

	public String getIdTaiKhoan() {
		return idTaiKhoan;
	}

	public void setIdTaiKhoan(String idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
}
