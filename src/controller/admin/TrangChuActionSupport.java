package controller.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DichVu;
import model.bean.NhaCungCap;
import model.bo.DichVuBO;
import model.bo.TaiKhoanBO;

public class TrangChuActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<DichVu> listDV;
	private List<NhaCungCap> listNCC;
	private List<DichVu> listNC;
	
	public String execute(){
		DichVuBO dichVuBO = new DichVuBO();
		TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
		this.listDV = dichVuBO.getDichVuDangKy();
		this.listNC = dichVuBO.getNhuCauDangKy();
		this.listNCC = taiKhoanBO.getNCCMoi();
		dichVuBO.closeConnect();
		taiKhoanBO.closeConnect();
		return "thanh-cong";
	}

	public List<DichVu> getListDV() {
		return listDV;
	}

	public void setListDV(List<DichVu> listDV) {
		this.listDV = listDV;
	}

	public List<NhaCungCap> getListNCC() {
		return listNCC;
	}

	public void setListNCC(List<NhaCungCap> listNCC) {
		this.listNCC = listNCC;
	}

	public List<DichVu> getListNC() {
		return listNC;
	}

	public void setListNC(List<DichVu> listNC) {
		this.listNC = listNC;
	}
	
}
