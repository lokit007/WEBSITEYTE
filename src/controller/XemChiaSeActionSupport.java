package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BaiViet;
import model.bo.ChiaSeBO;
import model.bo.ValidateBO;

public class XemChiaSeActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private BaiViet baiViet;
	private List<BaiViet> list;
	private String idBaiViet;
	
	public String execute(){
		if(ValidateBO.CheckEmpty(this.idBaiViet)) return "that-bai";
		else {
			ChiaSeBO chiaSeBO = new ChiaSeBO();
			this.baiViet = chiaSeBO.getChiaSe(idBaiViet);
			if(this.baiViet==null) return "that-bai";
			else 
				this.list = chiaSeBO.getListChiaSe(baiViet.getTenTacGia(), "", "0");
			chiaSeBO.closeConnect();
		}
		return "thanh-cong";
	}

	public BaiViet getBaiViet() {
		return baiViet;
	}

	public void setBaiViet(BaiViet baiViet) {
		this.baiViet = baiViet;
	}

	public List<BaiViet> getList() {
		return list;
	}

	public void setList(List<BaiViet> list) {
		this.list = list;
	}

	public String getIdBaiViet() {
		return idBaiViet;
	}

	public void setIdBaiViet(String idBaiViet) {
		this.idBaiViet = idBaiViet;
	}
	
}
