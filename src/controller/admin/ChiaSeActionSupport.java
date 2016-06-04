package controller.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BaiViet;
import model.bo.ChiaSeBO;
import model.dao.FormatData;

/**
 * ChiaSeActionSupport.java
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

public class ChiaSeActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String txtFind;
	private int page;
	private String menu;
	private List<BaiViet> list;
	
	/**
	 * Hiển thị danh sách các chia sẻ trên hệ thống
	 * @param
	 * @return String result
	 */
	
	public String execute(){
		if(page<1) page = 1;
		ChiaSeBO chiaSeBO = new ChiaSeBO();
		list = chiaSeBO.getListChiaSe(FormatData.toUTF8(txtFind), page);
		menu = chiaSeBO.getMemu();
		chiaSeBO.closeConnect();
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

	public List<BaiViet> getList() {
		return list;
	}

	public void setList(List<BaiViet> list) {
		this.list = list;
	}
	
}
