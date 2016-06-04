package controller.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DichVu;
import model.bo.DichVuBO;
import model.dao.FormatData;

/**
 * NhuCauActionSupport.java
 *
 * Version 1.0
 *
 * Date: 20-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 20-04-2016        	NhanHV          Create
 */

public class NhuCauActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String txtFind;
	private int page;
	private String menu;
	private List<DichVu> list;
	
	/**
	 * Danh sách nhu cầu trên hệ thống
	 * @param
	 * @return String result
	 */
	
	public String execute(){
		if(page<1) page = 1;
		DichVuBO dichVuBO = new DichVuBO();
		list = dichVuBO.getListNhuCau(FormatData.toUTF8(txtFind), page);
		menu = dichVuBO.getMemu();
		dichVuBO.closeConnect();
		return "thanh-cong";
	}

	public String getTxtFind() {
		return txtFind;
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

	public List<DichVu> getList() {
		return list;
	}

	public void setList(List<DichVu> list) {
		this.list = list;
	}
	
}
