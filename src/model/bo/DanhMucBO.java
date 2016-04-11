package model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.DanhMuc;
import model.dao.DanhMucDAO;

public class DanhMucBO {

	private DanhMucDAO danhMucDAO = null;
	
	public DanhMucBO() {
		danhMucDAO = new DanhMucDAO();
	}

	public void closeConnect() {
		danhMucDAO.closeConnect();
	}

	public ArrayList<DanhMuc> getDanhSachDanhMuc(String txtFind, int page) {
		try {
			return danhMucDAO.getDanhSachDanhMuc(txtFind, page);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public String getMemu() {
		return danhMucDAO.getMenu();
	}

	public boolean themDanhMuc(String ten) {
		return danhMucDAO.themDanhMuc(ten);
	}

	public boolean capNhatDanhMuc(String idDanhMuc, String ten) {
		return danhMucDAO.capNhatDanhMuc(idDanhMuc, ten);
	}

	public boolean xoaDanhMuc(String idDanhMuc) {
		return danhMucDAO.xoaDanhMuc(idDanhMuc);
	}

	public ArrayList<DanhMuc> getListDanhMucDichVu() {
		try {
			return danhMucDAO.getListDanhMucDichVu();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean themDanhMuc(String tenDanhMuc, String hthi) {
		return danhMucDAO.themDanhMuc(tenDanhMuc, hthi);
	}

	public DanhMuc getDanhMuc(int idDanhMuc) {
		try {
			return danhMucDAO.getDanhMuc(idDanhMuc);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean capNhatDanhMuc(int idDanhMuc, String tenDanhMuc, String hthi) {
		return danhMucDAO.capNhatDanhMuc(idDanhMuc, tenDanhMuc, hthi);
	}

	public List<DanhMuc> getListDanhMuc(String hienThi) {
		try {
			return danhMucDAO.getListDanhMuc(hienThi);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
