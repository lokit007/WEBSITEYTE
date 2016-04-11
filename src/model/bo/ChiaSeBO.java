package model.bo;

import java.sql.SQLException;
import java.util.List;

import model.bean.BaiViet;
import model.dao.ChiaSeDAO;

public class ChiaSeBO {

	private ChiaSeDAO chiaSeDAO = new ChiaSeDAO();
	
	public void closeConnect() {
		chiaSeDAO.closeConnect();
	}

	public String getMemu() {
		return chiaSeDAO.getMenu();
	}
	
	public List<BaiViet> getListQuanTam() {
		try {
			return chiaSeDAO.getListQuanTam();
		} catch (SQLException e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		}
	}

	public List<BaiViet> getListMoiChiaSe() {
		try {
			return chiaSeDAO.getListMoiChiaSe();
		} catch (SQLException e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		}
	}

	public List<BaiViet> getListChiaSe(String txtFind, String idDanhMuc, String viTri) {
		try {
			return chiaSeDAO.getListChiaSe(txtFind, idDanhMuc, viTri);
		} catch (SQLException e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		}
	}

	public BaiViet getChiaSe(String idBaiViet) {
		try {
			return chiaSeDAO.getChiaSe(idBaiViet);
		} catch (SQLException e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		}
	}

	public List<BaiViet> getListChiaSe(String txtFind, int page) {
		try {
			return chiaSeDAO.getListChiaSe(txtFind, page);
		} catch (SQLException e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Lỗi : " + e.toString());
			return null;
		}
	}

	public boolean xoaChiaSe(int idBaiViet) {
		return chiaSeDAO.xoaChiaSe(idBaiViet);
	}

	public boolean ThemChiaSe(String tenBaiViet, String moTa, int danhMuc, String noiDung, String hinhAnh,
			String taiKhoan, String tacGia) {
		return chiaSeDAO.ThemChiaSe(tenBaiViet, moTa, danhMuc, noiDung, hinhAnh, taiKhoan, tacGia);
	}

	public boolean capNhatChiaSe(int idBaiViet, String tenBaiViet, String moTa, int danhMuc, String noiDung,
			String hinhAnh, String tacGia) {
		return chiaSeDAO.capNhatChiaSe(idBaiViet, tenBaiViet, moTa, danhMuc, noiDung, hinhAnh, tacGia);
	}

	public boolean CapNhatTrangThai(String idKey, String chanState) {
		return chiaSeDAO.CapNhatTrangThai(idKey, chanState);
	}

}
