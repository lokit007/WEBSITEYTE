package controller;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.ChiaSeBO;
import model.bo.DichVuBO;
import model.bo.ValidateBO;

/**
 * CapNhatActionSupport.java
 *
 * Version 1.0
 *
 * Date: 04-01-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 04-01-2016        	NhanHV          Create
 */

public class CapNhatActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String idKey;
	private String nameTable;
	private String chanState;
	
	/**
	 * Cập nhật tình trạng một số trường dữ liệu của bảng
	 * @param id, bảng, trạng thái cập nhật
	 * @return String result
	 */
	
	public String execute(){
		String result = "that-bai";
		if(ValidateBO.CheckEmpty(idKey)||ValidateBO.CheckEmpty(nameTable)||ValidateBO.CheckEmpty(chanState)){
			addActionError("Bạn không có quyền hạn thực hiện chức năng này!");
		} else {
			if("BAIVIET".equals(nameTable)){
				// Cập nhật trạng thái bài viết chia sẻ
				ChiaSeBO chiaSeBO = new ChiaSeBO();
				if(!chiaSeBO.CapNhatTrangThai(idKey, chanState)){
					addActionError("Cập nhật thất bại!");
				} else {
					result = "thanh-cong";
				}
				chiaSeBO.closeConnect();
			} else if("DANGKYDICHVU".equals(nameTable)) {
				// Cập nhật trạng thái các đăng ký dịch vụ
				DichVuBO dichVuBO = new DichVuBO();
				if(!dichVuBO.CapNhatDichVuDangKy(idKey, chanState)){
					addActionError("Cập nhật thất bại!");
				} else {
					result = "thanh-cong";
				}
				dichVuBO.closeConnect();
			} else if("DICHVU".equals(nameTable)){
				// Cập nhật trạng thái dịch vụ được chọn
				ServletActionContext.getRequest().getSession().setAttribute("selectTab", "tab3");
				DichVuBO dichVuBO = new DichVuBO();
				if(!dichVuBO.capNhatTinhTrang(idKey, chanState)){
					addActionError("Cập nhật thất bại!");
				} else {
					result = "thanh-cong";
				}
				dichVuBO.closeConnect();
			} else {
				addActionError("Bạn không có quyền hạn thực hiện chức năng này!");
			}
		}
		return result;
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

	public String getChanState() {
		return chanState;
	}

	public void setChanState(String chanState) {
		this.chanState = chanState;
	}
}
