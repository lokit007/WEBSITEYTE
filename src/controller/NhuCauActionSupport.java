package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DanhMuc;
import model.bean.DichVu;
import model.bo.DanhMucBO;
import model.bo.DichVuBO;

public class NhuCauActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<DichVu> listMoi;
	private List<DichVu> list;
	private List<DanhMuc> danhMuc;
	private String txtFind;
	private String idDanhMuc;
	private String viTri = "0";

	public String execute(){
		DichVuBO dichVuBO = new DichVuBO();
		DanhMucBO danhMucBO = new DanhMucBO();
		listMoi = dichVuBO.getNhuCauMoi();
		list = dichVuBO.getListNhuCau(txtFind, idDanhMuc, viTri);
		danhMuc = danhMucBO.getListDanhMucDichVu();
		dichVuBO.closeConnect();
		danhMucBO.closeConnect();
		return "thanh-cong";
	}

	public String LoadThem(){
		DichVuBO dichVuBO = new DichVuBO();
		System.out.println("Xem : " + txtFind + " - " + idDanhMuc + " - " + viTri);
		list = dichVuBO.getListNhuCau(txtFind, idDanhMuc, viTri);
		dichVuBO.closeConnect();
		return "thanh-cong";
	}
	
	public List<DichVu> getListMoi() {
		return listMoi;
	}

	public void setListMoi(List<DichVu> listMoi) {
		this.listMoi = listMoi;
	}

	public List<DichVu> getList() {
		return list;
	}

	public void setList(List<DichVu> list) {
		this.list = list;
	}

	public List<DanhMuc> getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(List<DanhMuc> danhMuc) {
		this.danhMuc = danhMuc;
	}

	public String getTxtFind() {
		return txtFind;
	}

	public void setTxtFind(String txtFind) {
		this.txtFind = txtFind;
	}

	public String getIdDanhMuc() {
		return idDanhMuc;
	}

	public void setIdDanhMuc(String idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}
	
}