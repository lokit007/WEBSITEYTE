package controller.admin;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.BinhLuanBO;

public class XulyHoiDapActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nameTable;
	
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
