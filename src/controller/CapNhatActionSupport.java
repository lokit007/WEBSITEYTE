package controller;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.ChiaSeBO;
import model.bo.DichVuBO;
import model.bo.ValidateBO;

public class CapNhatActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String idKey;
	private String nameTable;
	private String chanState;
	
	public String execute(){
		String result = "that-bai";
		if(ValidateBO.CheckEmpty(idKey)||ValidateBO.CheckEmpty(nameTable)||ValidateBO.CheckEmpty(chanState)){
			addActionError("Bạn không có quyền hạn thực hiện chức năng này!");
			System.out.println("Xem sao 1");
		} else {
			if("BAIVIET".equals(nameTable)){
				ChiaSeBO chiaSeBO = new ChiaSeBO();
				System.out.println("Xem 1 : " + idKey + " - " + nameTable + " - " + chanState);
				if(!chiaSeBO.CapNhatTrangThai(idKey, chanState)){
					addActionError("Cập nhật thất bại!");
				} else {
					result = "thanh-cong";
				}
				chiaSeBO.closeConnect();
			} else if("DANGKYDICHVU".equals(nameTable)) {
				DichVuBO dichVuBO = new DichVuBO();
				if(!dichVuBO.CapNhatDichVuDangKy(idKey, chanState)){
					System.out.println("Xem sao2");
					addActionError("Cập nhật thất bại!");
				} else {
					System.out.println("Xem sao3");
					result = "thanh-cong";
				}
				dichVuBO.closeConnect();
			} else if("DICHVU".equals(nameTable)){
				ServletActionContext.getRequest().getSession().setAttribute("selectTab", "tab3");
				DichVuBO dichVuBO = new DichVuBO();
				System.out.println("Xem 1 : " + idKey + " - " + nameTable + " - " + chanState);
				if(!dichVuBO.capNhatTinhTrang(idKey, chanState)){
					addActionError("Cập nhật thất bại!");
				} else {
					result = "thanh-cong";
				}
				dichVuBO.closeConnect();
			} else {
				System.out.println("Xem sao 4 : " + nameTable);
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
