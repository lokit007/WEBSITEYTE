package model.dao;

public class LoiBaiDangDAO {
	DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public boolean clientBaoLoi(String hoTen, String email, String nameTable, String idKey, String vanDe,
			String tinNhan) {
		hoTen = FormatData.FormatInputData(hoTen);
		email = FormatData.FormatInputData(email);
		tinNhan = FormatData.FormatInputData(tinNhan);
		String sql = "insert into LOIBAIDANG(HoTen, Email, TableName, IdKey, VanDe, TinNhan, XuLy) values (N'"+hoTen
				+"', '"+email+"', '"+nameTable+"', '"+idKey+"', N'"+vanDe+"', N'"+tinNhan+"', N'Chưa xử lý')";
		return db.updateData(sql);
	}

}
