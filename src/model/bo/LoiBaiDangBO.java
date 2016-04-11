package model.bo;

import model.dao.LoiBaiDangDAO;

public class LoiBaiDangBO {
	private LoiBaiDangDAO loiBaiDangDAO = new LoiBaiDangDAO();
	public void closeConnect() {
		loiBaiDangDAO.closeConnect();
	}

	public boolean clientBaoLoi(String hoTen, String email, String nameTable, String idKey, String vanDe,
			String tinNhan) {
		return loiBaiDangDAO.clientBaoLoi(hoTen, email, nameTable, idKey, vanDe, tinNhan);
	}

}
