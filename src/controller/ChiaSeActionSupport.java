package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BaiViet;
import model.bean.DanhMuc;
import model.bo.ChiaSeBO;
import model.bo.DanhMucBO;

public class ChiaSeActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String txtFind;
	private String idDanhMuc;
	private String viTri = "0";
	private List<DanhMuc> danhMuc;
	private List<BaiViet> list;
	
	public String execute(){
		ChiaSeBO baiVietBO = new ChiaSeBO();
		DanhMucBO danhMucBO = new DanhMucBO();
		list = baiVietBO.getListChiaSe(txtFind, idDanhMuc, viTri);
		danhMuc = danhMucBO.getListDanhMucDichVu();
		danhMucBO.closeConnect();
		baiVietBO.closeConnect();
		return "thanh-cong";
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

	public List<BaiViet> getList() {
		return list;
	}

	public void setList(List<BaiViet> list) {
		this.list = list;
	}

	public List<DanhMuc> getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(List<DanhMuc> danhMuc) {
		this.danhMuc = danhMuc;
	}
	
}
