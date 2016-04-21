package model.bo;

import java.sql.SQLException;
import java.util.List;

import model.bean.NhaCungCap;
import model.dao.BanDoDAO;

public class BanDoBO {

	private BanDoDAO banDoDAO = new BanDoDAO();
	
	public void closeConnect() {
		banDoDAO.closeConnect();
	}

	public List<NhaCungCap> getListNCC(String loai) {
		try {
			return banDoDAO.getListNCC(loai);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
