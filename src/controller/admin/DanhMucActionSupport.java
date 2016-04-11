package controller.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DanhMuc;
import model.bo.DanhMucBO;

public class DanhMucActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String txtFind;
	private String page;
	private String menu;
	private List<DanhMuc> list;
	
	public String execute(){
		try{
			int Page = Integer.parseInt(page);
			if(Page<1) page="1";
		} catch(Exception e){
			page="1";
		}
		DanhMucBO danhMucBO = new DanhMucBO();
		this.list = danhMucBO.getDanhSachDanhMuc(txtFind, Integer.parseInt(page));
		this.menu = danhMucBO.getMemu();
		danhMucBO.closeConnect();
		return "thanh-cong";
	}

	public String getTxtFind() {
		return txtFind;
	}

	public void setTxtFind(String txtFind) {
		this.txtFind = txtFind;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public List<DanhMuc> getList() {
		return list;
	}

	public void setList(List<DanhMuc> list) {
		this.list = list;
	}
	
}
