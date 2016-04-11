package model.bo;

import java.sql.SQLException;
import java.util.List;

import model.bean.HoTroOnline;
import model.dao.HoTroDAO;

public class HoTroBO {
	private HoTroDAO hoTroDAO = new HoTroDAO();
	
	public void closeConnect() {
		hoTroDAO.closeConnect();
	}

	public String getMemu() {
		return hoTroDAO.getMemu();
	}
	
	public List<HoTroOnline> getListHoTro() {
		try {
			return hoTroDAO.getListHoTro();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public List<HoTroOnline> getListHoTro(String txtFind, int page) {
		try {
			return hoTroDAO.getListHoTro(txtFind, page);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean DungHoTro(int idHoTro) {
		return hoTroDAO.DungHoTro(idHoTro);
	}

}
