package model.bo;

import java.sql.SQLException;
import java.util.List;

import model.bean.QuanTri;
import model.bean.TaiKhoan;
import model.dao.QuanTriDAO;

public class QuanTriBO {

	private QuanTriDAO quanTriDAO = new QuanTriDAO();
	
	public void closeConnect() {
		quanTriDAO.closeConnect();
	}

	public List<QuanTri> getDanhSachQuanTri() {
		try {
			return quanTriDAO.getDanhSachQuanTri();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public List<TaiKhoan> getTaiKhoanThem() {
		try {
			return quanTriDAO.getTaiKhoanThem();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean CapQuyenQuanTri(String taiKhoan, boolean danhMuc, boolean dichVu, boolean nhuCau, boolean chiaSe,
			boolean nhaCungCap, boolean taiNguyen, boolean thongKe) {
		return quanTriDAO.CapQuyenQuanTri(taiKhoan, danhMuc, dichVu, nhuCau, chiaSe, nhaCungCap, taiNguyen, thongKe);
	}

	public boolean HuyQuyenQuanTri(String taiKhoan) {
		// TODO Auto-generated method stub
		return quanTriDAO.HuyQuyenQuanTri(taiKhoan);
	}

}
