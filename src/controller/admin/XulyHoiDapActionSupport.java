package controller.admin;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.BinhLuanBO;

/**
 * XulyHoiDapActionSupport.java
 *
 * Version 1.0
 *
 * Date: 17-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 17-04-2016        	NhanHV          Create
 */

public class XulyHoiDapActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nameTable;
	
	/**
	 * Xóa bình luận
	 * @param id bình luận
	 * @return String result
	 */
	
	public String execute(){
		BinhLuanBO binhLuanBO = new BinhLuanBO();
		if(!binhLuanBO.XoaBinhLuan(id, nameTable)){
			addActionError("Lỗi cập nhật cơ sở dữ liệu");
		}
		binhLuanBO.closeConnect();
		return "thanh-cong";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}
	
}
