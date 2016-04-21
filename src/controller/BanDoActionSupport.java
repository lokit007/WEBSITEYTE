package controller;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.NhaCungCap;
import model.bo.BanDoBO;

public class BanDoActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private List<NhaCungCap> list = new ArrayList<NhaCungCap>();
	private String status = "false";
	
	public String execute(){
		BanDoBO banDoBO = new BanDoBO();
		this.list = banDoBO.getListNCC("all");
		banDoBO.closeConnect();
		return "thanh-cong";
	}

	public List<NhaCungCap> getList() {
		return list;
	}

	public void setList(List<NhaCungCap> list) {
		this.list = list;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
