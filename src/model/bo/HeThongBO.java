package model.bo;

import java.sql.SQLException;

import model.bean.TaiNguyen;
import model.dao.HeThongDAO;

public class HeThongBO {

	private HeThongDAO heThongDAO = new HeThongDAO();
	
	public void closeConnect() {
		heThongDAO.closeConnect();
	}

	public TaiNguyen getTaiNguyen() {
		try {
			return heThongDAO.getTaiNguyen();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean CapNhatThongTin(String tenHeThong, String banQuyen, String diaChi, String dienThoai, String fax,
			String email, String soLuongTruyCap) {
		return heThongDAO.CapNhatThongTin(tenHeThong, banQuyen, diaChi, dienThoai, fax, email, soLuongTruyCap);
	}

	public boolean CapNhatNoiQuy(String noiQuyThanhVien, String quyDinhDangDichVu, String quyDinhDangNhuCau) {
		return heThongDAO.CapNhatNoiQuy(noiQuyThanhVien, quyDinhDangDichVu, quyDinhDangNhuCau);
	}

	public boolean ThemLienKet(String tenWebsite, String diaChiWeb) {
		return heThongDAO.ThemLienKet(tenWebsite, diaChiWeb);
	}

	public boolean CapNhatLienKet(String tenWebsite, String diaChiWeb) {
		return heThongDAO.CapNhatLienKet(tenWebsite, diaChiWeb);
	}

	public boolean XoaLienKet(String tenWebsite) {
		return heThongDAO.XoaLienKet(tenWebsite);
	}

}
