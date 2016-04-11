package model.bo;

import java.sql.SQLException;
import java.util.List;

import model.bean.BinhLuan;
import model.dao.BinhLuanDAO;

public class BinhLuanBO {
	private BinhLuanDAO binhLuanDAO = new BinhLuanDAO();
	public void closeConnect() {
		binhLuanDAO.closeConnect();
	}

	public boolean themBinhLuan(String taiKhoan, String id, String noiDung, String table) {
		return binhLuanDAO.themBinhLuan(taiKhoan, id, noiDung, table);
	}

	public BinhLuan getBinhLuan(String table, String id, String noiDung) {
		try {
			return binhLuanDAO.getBinhLuan(table, id, noiDung);
		} catch (SQLException e) {
			System.out.println("Lỗi BL : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Lỗi BL : " + e.toString());
			return null;
		}
	}

	public List<BinhLuan> getListBinhLuan(String id) {
		try {
			return binhLuanDAO.getListBinhLuan(id);
		} catch (SQLException e) {
			System.out.println("Lỗi list BL : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Lỗi list BL : " + e.toString());
			return null;
		}
	}

}
