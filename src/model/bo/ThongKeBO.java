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
	public List<ThongKeDanhMuc> getThongKeDanhMuc(String tuNgay, String denNgay) {
		try {
			return thongKeDAO.getThongKeDanhMuc(tuNgay, denNgay);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	public List<ThongKeBaiViet> getThongKeBaiViet(String tuNgay, String denNgay) {
		try {
			return thongKeDAO.getThongKeBaiViet(tuNgay, denNgay);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	public HashMap<String, Integer> getThongKeDichVu(String tuNgay, String denNgay) {
		try {
			return thongKeDAO.getThongKeDichVu(tuNgay, denNgay);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	public List<ThongKeTaiKhoan> getThongKeTaiKhoan(String tuNgay, String denNgay) {
		try {
			return thongKeDAO.getThongKeTaiKhoan(tuNgay, denNgay);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
