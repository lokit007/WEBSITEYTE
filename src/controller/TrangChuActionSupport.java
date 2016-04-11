package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BaiViet;
import model.bean.DichVu;
import model.bean.HoTroOnline;
import model.bo.ChiaSeBO;
import model.bo.DichVuBO;
import model.bo.HoTroBO;

public class TrangChuActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	//MenuBar
	private List<BaiViet> listBVQT;
	private List<DichVu> listDVM;
	private List<HoTroOnline> listHT;
	//Trang chu
	private List<DichVu> listDVTT;
	private List<DichVu> listDVC;
	private List<DichVu> listDVTN;
	private List<DichVu> listNCDV;
	private List<BaiViet> listCSYT;
	
	public String execute(){
		DichVuBO dichVuBO = new DichVuBO();
		ChiaSeBO chiaSeBO = new ChiaSeBO();
		this.listDVTT = dichVuBO.getListDichVu("Từ thiện");
		this.listDVC = dichVuBO.getListDichVu("Dịch vụ công");
		this.listDVTN = dichVuBO.getListDichVu("Dịch vụ tư");
		this.listNCDV = dichVuBO.getListDichVu("Nhu cầu");
		this.listCSYT = chiaSeBO.getListMoiChiaSe();
		dichVuBO.closeConnect();
		chiaSeBO.closeConnect();
		return "thanh-cong";
	}

	public String LoadMenuBar(){
		DichVuBO dichVuBO = new DichVuBO();
		HoTroBO hoTroBO = new HoTroBO();
		ChiaSeBO chiaSeBO = new ChiaSeBO();
		this.listBVQT = chiaSeBO.getListQuanTam();
		this.listDVM = dichVuBO.getListDichVuMoi();
		this.listHT = hoTroBO.getListHoTro();
		dichVuBO.closeConnect();
		hoTroBO.closeConnect();
		chiaSeBO.closeConnect();
		return "thanh-cong";
	}
	
	public List<BaiViet> getListBVQT() {
		return listBVQT;
	}

	public void setListBVQT(List<BaiViet> listBVQT) {
		this.listBVQT = listBVQT;
	}

	public List<DichVu> getListDVM() {
		return listDVM;
	}

	public void setListDVM(List<DichVu> listDVM) {
		this.listDVM = listDVM;
	}

	public List<HoTroOnline> getListHT() {
		return listHT;
	}

	public void setListHT(List<HoTroOnline> listHT) {
		this.listHT = listHT;
	}

	public List<DichVu> getListDVTT() {
		return listDVTT;
	}

	public void setListDVTT(List<DichVu> listDVTT) {
		this.listDVTT = listDVTT;
	}

	public List<DichVu> getListDVC() {
		return listDVC;
	}

	public void setListDVC(List<DichVu> listDVC) {
		this.listDVC = listDVC;
	}

	public List<DichVu> getListDVTN() {
		return listDVTN;
	}

	public void setListDVTN(List<DichVu> listDVTN) {
		this.listDVTN = listDVTN;
	}

	public List<DichVu> getListNCDV() {
		return listNCDV;
	}

	public void setListNCDV(List<DichVu> listNCDV) {
		this.listNCDV = listNCDV;
	}

	public List<BaiViet> getListCSYT() {
		return listCSYT;
	}

	public void setListCSYT(List<BaiViet> listCSYT) {
		this.listCSYT = listCSYT;
	}
	
}
