package controller.admin;

import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.ThongKeBaiViet;
import model.bean.ThongKeDanhMuc;
import model.bean.ThongKeTaiKhoan;
import model.bo.ThongKeBO;

public class ThongKeBaoCaoActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private List<ThongKeDanhMuc> listDM;
	private List<ThongKeBaiViet> listBV;
	private HashMap<String, Integer> listDV;
	private List<ThongKeTaiKhoan> listTK;
	
	public String execute(){
		ThongKeBO thongKeBO = new ThongKeBO();
		this.listDM = thongKeBO.getThongKeDanhMuc();
		this.listBV = thongKeBO.getThongKeBaiViet();
		this.listDV = thongKeBO.getThongKeDichVu();
		this.listTK = thongKeBO.getThongKeTaiKhoan();
		thongKeBO.closeConnect();
		return "thanh-cong";
	}
	
	public List<ThongKeDanhMuc> getListDM() {
		return listDM;
	}
	public void setListDM(List<ThongKeDanhMuc> listDM) {
		this.listDM = listDM;
	}
	public List<ThongKeBaiViet> getListBV() {
		return listBV;
	}
	public void setListBV(List<ThongKeBaiViet> listBV) {
		this.listBV = listBV;
	}
	public HashMap<String, Integer> getListDV() {
		return listDV;
	}
	public void setListDV(HashMap<String, Integer> listDV) {
		this.listDV = listDV;
	}
	public List<ThongKeTaiKhoan> getListTK() {
		return listTK;
	}
	public void setListTK(List<ThongKeTaiKhoan> listTK) {
		this.listTK = listTK;
	}
	
}
