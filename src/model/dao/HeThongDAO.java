package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.bean.TaiNguyen;

public class HeThongDAO {

	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public TaiNguyen getTaiNguyen() throws SQLException {
		String sql = "select * from TAINGUYEN";
		ResultSet rs = db.getResultSet(sql);
		TaiNguyen taiNguyen = new TaiNguyen();
		if(rs.next()){
			taiNguyen.setBanQuyen(FormatData.FormatOutputData(rs.getString("GiaTri")));
			if(rs.next()){
				taiNguyen.setDiaChi(FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			if(rs.next()){
				taiNguyen.setDienThoai(FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			if(rs.next()){
				taiNguyen.setEmail(FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			if(rs.next()){
				taiNguyen.setFax(FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			if(rs.next()){
				taiNguyen.setNoiQuyThanhVien(FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			if(rs.next()){
				taiNguyen.setQuyDinhDangNhuCau(FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			if(rs.next()){
				taiNguyen.setQuyDinhDangDichVu(FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			if(rs.next()){
				taiNguyen.setTenHeThong(FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			if(rs.next()){
				taiNguyen.setSoLuongTruyCap(FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			HashMap<String, String> list = new HashMap<String, String>();
			while(rs.next()){
				list.put(FormatData.FormatOutputData(rs.getString("TaiNguyen"))
						,FormatData.FormatOutputData(rs.getString("GiaTri")));
			}
			taiNguyen.setLienKet(list);
		}
		return taiNguyen;
	}

	public boolean CapNhatThongTin(String tenHeThong, String banQuyen, String diaChi, String dienThoai, String fax,
			String email, String soLuongTruyCap) {
		tenHeThong = FormatData.FormatInputData(tenHeThong);
		banQuyen = FormatData.FormatInputData(banQuyen);
		diaChi = FormatData.FormatInputData(diaChi);
		email = FormatData.FormatInputData(email);
		boolean result = false;
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+tenHeThong+"' where TaiNguyen=N'Tên Hệ Thống'");
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+banQuyen+"' where TaiNguyen=N'Bản quyền'");
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+diaChi+"' where TaiNguyen=N'Địa chỉ'");
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+dienThoai+"' where TaiNguyen=N'Điện thoại'");
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+fax+"' where TaiNguyen=N'Fax'");
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+email+"' where TaiNguyen=N'Email'");
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+soLuongTruyCap+"' where TaiNguyen=N'Tổng truy cập'");
		return result;
	}

	public boolean CapNhatNoiQuy(String noiQuyThanhVien, String quyDinhDangDichVu, String quyDinhDangNhuCau) {
		noiQuyThanhVien = FormatData.FormatInputData(noiQuyThanhVien);
		quyDinhDangDichVu = FormatData.FormatInputData(quyDinhDangDichVu);
		quyDinhDangNhuCau = FormatData.FormatInputData(quyDinhDangNhuCau);
		boolean result = false;
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+noiQuyThanhVien+"' where TaiNguyen like N'Nội Quy Đăng Ký Thành Viên'");
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+quyDinhDangDichVu+"' where TaiNguyen like N'Quy Định Đăng Nhu Cầu'");
		result = result|db.updateData("update TAINGUYEN set GiaTri=N'"+quyDinhDangNhuCau+"' where TaiNguyen like N'Quy Định Phát Hành Dịch Vụ'");
		return result;
	}

	public boolean ThemLienKet(String tenWebsite, String diaChiWeb) {
		tenWebsite = FormatData.FormatInputData(tenWebsite);
		diaChiWeb = FormatData.FormatInputData(diaChiWeb);
		String sql = "insert into TAINGUYEN values (N'"+tenWebsite+"', N'"+diaChiWeb+"')";
		return db.updateData(sql);
	}

	public boolean CapNhatLienKet(String tenWebsite, String diaChiWeb) {
		tenWebsite = FormatData.FormatInputData(tenWebsite);
		diaChiWeb = FormatData.FormatInputData(diaChiWeb);
		String sql = "update TAINGUYEN set GiaTri=N'"+diaChiWeb+"' where TaiNguyen like N'"+tenWebsite+"'";
		return db.updateData(sql);
	}

	public boolean XoaLienKet(String tenWebsite) {
		tenWebsite = FormatData.FormatInputData(tenWebsite);
		String sql = "delete from TAINGUYEN where TaiNguyen like N'"+tenWebsite+"'";
		return db.updateData(sql);
	}

}
