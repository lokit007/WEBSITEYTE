package controller;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.LoiBaiDangBO;
import model.bo.ValidateBO;

/**
 * LoiBaiDangAcctionSupport.java
 *
 * Version 1.0
 *
 * Date: 25-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 25-04-2016        	NhanHV          Create
 */

public class LoiBaiDangAcctionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String idKey;
	private String nameTable;
	private String hoTen;
	private String email;
	private String vanDe;
	private String tinNhan;
	
	/**
	 * Cập nhật thông tin báo lỗi của người dùng
	 * @param thông tin báo lỗi
	 * @return String result
	 */
	
	public String execute(){
		if(ValidateBO.CheckEmpty(nameTable) || ValidateBO.CheckEmpty(hoTen)
				|| ValidateBO.CheckEmpty(email) || ValidateBO.CheckEmpty(vanDe))
			addActionError("Dữ liệu không tồn tại");
		else{
			LoiBaiDangBO loiBaiDangBO = new LoiBaiDangBO();
			if(!loiBaiDangBO.clientBaoLoi(hoTen, email, nameTable, idKey, vanDe, tinNhan)){
				addActionError("Lỗi kết nối csdl");
			}
			loiBaiDangBO.closeConnect();
		}
		return "thanh-cong";
	}

	public String getIdKey() {
		return idKey;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVanDe() {
		return vanDe;
	}

	public void setVanDe(String vanDe) {
		this.vanDe = vanDe;
	}

	public String getTinNhan() {
		return tinNhan;
	}

	public void setTinNhan(String tinNhan) {
		this.tinNhan = tinNhan;
	}
	
}
