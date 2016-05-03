package model.bo;

import java.sql.SQLException;
import java.util.List;

import model.bean.KhachHang;
import model.bean.QuangCao;
import model.dao.QuangCaoDAO;

public class QuangCaoBO {

	private QuangCaoDAO quangCaoDAO = new QuangCaoDAO();
	
	public void closeConnect() {
		quangCaoDAO.closeConnect();
	}

	public List<QuangCao> getDanhSachHienThi() {
		try {
			return quangCaoDAO.getDanhSachHienThi();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public List<QuangCao> getDanhSachCho(int page) {
		try {
			return quangCaoDAO.getDanhSachCho(page);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public List<KhachHang> getDanhSachKhachHang(int page) {
		try {
			return quangCaoDAO.getDanhSachKhachHang(page);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean XoaQuangCao(int idQuangCao) {
		return quangCaoDAO.XoaQuangCao(idQuangCao);
	}

	public boolean XoaKhachHang(int idKhachHang) {
		return quangCaoDAO.XoaKhachHang(idKhachHang);
	}

}
