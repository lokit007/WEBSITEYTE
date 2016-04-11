package model.dao;

public class NhanMailDAO {
	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}
	
	public boolean DangKyNhanMail(String loaiTin, String email) {
		loaiTin = FormatData.FormatInputData(loaiTin);
		email = FormatData.FormatInputData(email);
		String sql = "insert into NHANMAIL values ('"+email+"', N'"+loaiTin+"')";
		return db.updateData(sql);
	}

}
