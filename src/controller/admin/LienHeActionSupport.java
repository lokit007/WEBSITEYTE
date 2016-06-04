package controller.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.HoTroOnline;
import model.bo.HoTroBO;

/**
 * LienHeActionSupport.java
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

public class LienHeActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String txtFind;
	private int page;
	private String menu;
	private List<HoTroOnline> list;
	private int idHoTro;
	
	/**
	 * Danh sách hổ trợ trên hệ thống
	 * @param
	 * @return String result
	 */
	
	public String execute(){
		if(page<1) page = 1;
		HoTroBO hoTroBO = new HoTroBO();
		this.list = hoTroBO.getListHoTro(txtFind==null?"":txtFind, page);
		this.menu = hoTroBO.getMemu();
		hoTroBO.closeConnect();
		return "thanh-cong";
	}

	/**
	 * Ngừng hổ trợ trực tiếp
	 * @param
	 * @return String result
	 */
	
	public String DungHoTro(){
		if(idHoTro<1){
			addActionError("Xóa danh mục "+idHoTro+" thất bại!");
			return "that-bai";
		} else {
			HoTroBO hoTroBO = new HoTroBO();
			hoTroBO.DungHoTro(idHoTro);
			hoTroBO.closeConnect();
		}
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

	public List<HoTroOnline> getList() {
		return list;
	}

	public void setList(List<HoTroOnline> list) {
		this.list = list;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getIdHoTro() {
		return idHoTro;
	}

	public void setIdHoTro(int idHoTro) {
		this.idHoTro = idHoTro;
	}
	
}
