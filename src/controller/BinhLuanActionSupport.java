package controller;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BinhLuan;
import model.bean.TaiKhoan;
import model.bo.BinhLuanBO;
import model.bo.ValidateBO;

/**
 * BinhLuanActionSupport.java
 *
 * Version 1.0
 *
 * Date: 15-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 15-04-2016        	NhanHV          Create
 */

public class BinhLuanActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String id;
	private String noiDung;
	private String table;
	private BinhLuan binhLuan;
	
	/**
	 * Cập nhật nội dung bình luận, trả lời vào data
	 * @param 
	 * @return String result
	 */
	
	public String execute(){
		if(ValidateBO.CheckEmpty(id)||ValidateBO.CheckEmpty(noiDung)){
			addActionError("Bình luận thất bại");
		} else {
			TaiKhoan user = (TaiKhoan)ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user!=null){
				BinhLuanBO binhLuanBO = new BinhLuanBO();
				if(!binhLuanBO.themBinhLuan(user.getIdTaiKhoan(),id, noiDung, table)){
					addActionError("Bình luận thất bại");
				} else {
					binhLuan = binhLuanBO.getBinhLuan(table, id, noiDung);
				}
				binhLuanBO.closeConnect();
			} else {
				addActionError("Bình luận thất bại");
			}
		}
		return "thanh-cong";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public BinhLuan getBinhLuan() {
		return binhLuan;
	}

	public void setBinhLuan(BinhLuan binhLuan) {
		this.binhLuan = binhLuan;
	}
	
}
