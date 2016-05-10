package controller.admin;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BaiViet;
import model.bean.DangKyDichVu;
import model.bean.DichVu;
import model.bean.NhaCungCap;
import model.bo.ChiaSeBO;
import model.bo.DichVuBO;
import model.bo.TaiKhoanBO;

public class CapNhatTrangThaiActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<DichVu> listDichVu;
	private List<DichVu> listNhuCau;
	private List<DangKyDichVu> listDangKyDichVu;
	private List<DangKyDichVu> listBaoGiaNhuCau;
	private List<BaiViet> listChiaSe;
	private List<NhaCungCap> listNhaCungCap;
	
	public String execute(){
		DichVuBO dichVuBO = new DichVuBO();
		ChiaSeBO chiaSeBO = new ChiaSeBO();
		TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
		this.listDichVu = dichVuBO.getDichVuDangKy();
		this.listNhuCau = dichVuBO.getNhuCauDangKy();
		this.listDangKyDichVu = dichVuBO.getDanhSachDangKy();
		this.listBaoGiaNhuCau = dichVuBO.getDanhSachBaoGia();
		this.listChiaSe = chiaSeBO.getListChiaSe();
		this.listNhaCungCap = taiKhoanBO.getListNCC();
		dichVuBO.closeConnect();
		chiaSeBO.closeConnect();
		taiKhoanBO.closeConnect();
		return "thanh-cong";
	}

	public List<DichVu> getListDichVu() {
		return listDichVu;
	}

	public void setListDichVu(List<DichVu> listDichVu) {
		this.listDichVu = listDichVu;
	}

	public List<DichVu> getListNhuCau() {
		return listNhuCau;
	}

	public void setListNhuCau(List<DichVu> listNhuCau) {
		this.listNhuCau = listNhuCau;
	}

	public List<DangKyDichVu> getListDangKyDichVu() {
		return listDangKyDichVu;
	}

	public void setListDangKyDichVu(List<DangKyDichVu> listDangKyDichVu) {
		this.listDangKyDichVu = listDangKyDichVu;
	}

	public List<DangKyDichVu> getListBaoGiaNhuCau() {
		return listBaoGiaNhuCau;
	}

	public void setListBaoGiaNhuCau(List<DangKyDichVu> listBaoGiaNhuCau) {
		this.listBaoGiaNhuCau = listBaoGiaNhuCau;
	}

	public List<BaiViet> getListChiaSe() {
		return listChiaSe;
	}

	public void setListChiaSe(List<BaiViet> listChiaSe) {
		this.listChiaSe = listChiaSe;
	}

	public List<NhaCungCap> getListNhaCungCap() {
		return listNhaCungCap;
	}

	public void setListNhaCungCap(List<NhaCungCap> listNhaCungCap) {
		this.listNhaCungCap = listNhaCungCap;
	}
	
}
