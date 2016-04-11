package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BinhLuan;
import model.bean.DichVu;
import model.bo.BinhLuanBO;
import model.bo.DichVuBO;

public class XemNhuCauActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String idNhuCau;
	private DichVu nhuCau;
	private List<DichVu> list;
	private List<BinhLuan> listBinhLuan;
	
	public String execute(){
		DichVuBO dichVuBO = new DichVuBO();
		BinhLuanBO binhLuanBO = new BinhLuanBO();
		this.nhuCau = dichVuBO.getDichVu(this.idNhuCau, 1);
		if(this.nhuCau!=null){
			this.list = dichVuBO.getListNhuCau(nhuCau.getDienThoaiLienHe(), 
					nhuCau.getBaiViet().getDanhMuc().getIdDanhMuc()+"", "0");
			this.listBinhLuan = binhLuanBO.getListBinhLuan(nhuCau.getBaiViet().getIdBaiViet()+"");
		}
		dichVuBO.closeConnect();
		binhLuanBO.closeConnect();
		if(this.nhuCau==null) return "that-bai";
		return "thanh-cong";
	}

	public String getIdNhuCau() {
		return idNhuCau;
	}

	public void setIdNhuCau(String idNhuCau) {
		this.idNhuCau = idNhuCau;
	}

	public DichVu getNhuCau() {
		return nhuCau;
	}

	public void setNhuCau(DichVu nhuCau) {
		this.nhuCau = nhuCau;
	}

	public List<DichVu> getList() {
		return list;
	}

	public void setList(List<DichVu> list) {
		this.list = list;
	}

	public List<BinhLuan> getListBinhLuan() {
		return listBinhLuan;
	}

	public void setListBinhLuan(List<BinhLuan> listBinhLuan) {
		this.listBinhLuan = listBinhLuan;
	}
	
}
