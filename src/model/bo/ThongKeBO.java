package model.bo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import model.bean.ThongKeBaiViet;
import model.bean.ThongKeDanhMuc;
import model.bean.ThongKeTaiKhoan;
import model.dao.ThongKeDAO;

public class ThongKeBO {

	private ThongKeDAO thongKeDAO = new ThongKeDAO();
	public void closeConnect() {
		thongKeDAO.closeConnect();
	}
	public List<ThongKeDanhMuc> getThongKeDanhMuc() {
		try {
			return thongKeDAO.getThongKeDanhMuc();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	public List<ThongKeBaiViet> getThongKeBaiViet() {
		try {
			return thongKeDAO.getThongKeBaiViet();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	public HashMap<String, Integer> getThongKeDichVu() {
		try {
			return thongKeDAO.getThongKeDichVu();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	public List<ThongKeTaiKhoan> getThongKeTaiKhoan() {
		try {
			return thongKeDAO.getThongKeTaiKhoan();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
