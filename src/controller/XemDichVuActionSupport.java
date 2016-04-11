package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BinhLuan;
import model.bean.DichVu;
import model.bo.BinhLuanBO;
import model.bo.DichVuBO;

public class XemDichVuActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String idDichVu;
	private DichVu dichVu;
	private List<BinhLuan> listBinhLuan;
	
	public String execute(){
		DichVuBO dichVuBO = new DichVuBO();
		BinhLuanBO binhLuanBO = new BinhLuanBO();
		this.dichVu = dichVuBO.getDichVu(this.idDichVu, 1);
		if(this.dichVu!=null)
			this.listBinhLuan = binhLuanBO.getListBinhLuan(dichVu.getBaiViet().getIdBaiViet()+"");
		dichVuBO.closeConnect();
		binhLuanBO.closeConnect();
		if(this.dichVu==null) return "that-bai";
		return "thanh-cong";
	}

	public String getIdDichVu() {
		return idDichVu;
	}

	public void setIdDichVu(String idDichVu) {
		this.idDichVu = idDichVu;
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public List<BinhLuan> getListBinhLuan() {
		return listBinhLuan;
	}

	public void setListBinhLuan(List<BinhLuan> listBinhLuan) {
		this.listBinhLuan = listBinhLuan;
	}
	
}
