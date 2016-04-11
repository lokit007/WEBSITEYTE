package controller.admin;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.ChiaSeBO;
import model.bo.TaiKhoanBO;
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
			} else {
				TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
				System.out.println("Xem 2 : " + idKey + " - " + nameTable + " - " + chanState);
				if(!taiKhoanBO.CapNhatTrangThai(idKey, chanState)){
					addActionError("Cập nhật thất bại!");
				} else {
					result = "thanh-cong";
				}
				taiKhoanBO.closeConnect();
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
