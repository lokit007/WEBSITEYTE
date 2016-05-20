package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.bean.DanhMuc;
import model.bean.HoTroOnline;

public class HoTroDAO {
	private DataBaseDAO db = new DataBaseDAO();

	public void closeConnect() {
		db.closeConnection();
	}

	public String getMemu() {
		return db.getMenu();
	}
	
	private static String getTimeHoTro(){
		String times = "Ngoài giờ hành chính";
		Calendar cal = Calendar.getInstance();
		int thu = cal.get(Calendar.DAY_OF_WEEK);
		int gio = cal.get(Calendar.HOUR_OF_DAY);
		if(thu>1 && thu<7 && ((gio>8 && gio<12) || (gio>13 && gio<17))) times = "Trong giờ hành chính";
		return times;
	}
	
	public List<HoTroOnline> getListHoTro() throws SQLException {
		String sql = "select IdHoTro, ThoiGianHoTro, EmailLienHe, DienThoaiLienHe, NickYahooChat, "
				+ "NickSkypeChat, DANHMUC.IdDanhMuc, TenDanhMuc, NHACUNGCAP.IdNhaCungCap, "
				+ "HoTen, DanhGia from HOTROONLINE "
				+"inner join DANHMUC on DANHMUC.IdDanhMuc=HOTROONLINE.IdDanhMuc "
				+"inner join NHACUNGCAP on NHACUNGCAP.IdNhaCungCap=HOTROONLINE.NhaCungCap "
				+"inner join TAIKHOAN on NHACUNGCAP.TaiKhoan=TAIKHOAN.TaiKhoan "
				+"where ThoiGianHoTro like N'"+getTimeHoTro()+"' or ThoiGianHoTro like N'Liên hệ mọi lúc'";
		ArrayList<HoTroOnline> list = new ArrayList<HoTroOnline>();
		ResultSet rs = db.getResultSet(sql);
		while(rs.next()){
			HoTroOnline hoTro = new HoTroOnline();
			hoTro.setIdHoTro(rs.getInt("IdHoTro")+"");
			hoTro.setThoiGianHoTro(rs.getString("ThoiGianHoTro"));
			hoTro.setEmailLienHe(rs.getString("EmailLienHe"));
			hoTro.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			hoTro.setNickYahooChat(rs.getString("NickYahooChat"));
			hoTro.setNickSkypeChat(rs.getString("NickSkypeChat"));
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc"));
			danhMuc.setTenDanhMuc(rs.getString("TenDanhMuc"));
			hoTro.setDanhMuc(danhMuc);
			hoTro.setNhaCungCap(rs.getString("HoTen"));
			list.add(hoTro);
		}
		return list;
	}

	public List<HoTroOnline> getListHoTro(String txtFind, int page) throws SQLException {
		txtFind = FormatData.FormatInputData(txtFind);
		String sql = "select IdHoTro, ThoiGianHoTro, EmailLienHe, DienThoaiLienHe, NickYahooChat, "
				+ "NickSkypeChat, DANHMUC.IdDanhMuc, TenDanhMuc, NHACUNGCAP.IdNhaCungCap, "
				+ "HoTen, DanhGia, ROW_NUMBER() OVER (ORDER BY IdHoTro desc) AS Row  from HOTROONLINE "
				+ "inner join DANHMUC on DANHMUC.IdDanhMuc=HOTROONLINE.IdDanhMuc "
				+ "inner join NHACUNGCAP on NHACUNGCAP.IdNhaCungCap=HOTROONLINE.NhaCungCap "
				+ "inner join TAIKHOAN on NHACUNGCAP.TaiKhoan=TAIKHOAN.TaiKhoan "
				+ "where TenDanhMuc like N'%"+txtFind+"%' or HoTen like N'%"+txtFind
				+ "%' or ThoiGianHoTro like N'%"+txtFind+"%' or EmailLienHe like '"+txtFind+"%'";
		db.setRecord(10);
		db.createMenu("ho-tro.action?txtFind="+txtFind, page, sql);
		ArrayList<HoTroOnline> list = new ArrayList<HoTroOnline>();
		ResultSet rs = db.getResultSet("select * from ( "+sql+" ) as tam where Row between "
				+((db.getPage()-1)*db.getRecord()+1)+" and "+(db.getPage()*db.getRecord()));
		while(rs.next()){
			HoTroOnline hoTro = new HoTroOnline();
			hoTro.setIdHoTro(rs.getInt("IdHoTro")+"");
			hoTro.setThoiGianHoTro(rs.getString("ThoiGianHoTro"));
			hoTro.setEmailLienHe(rs.getString("EmailLienHe"));
			hoTro.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			hoTro.setNickYahooChat(rs.getString("NickYahooChat"));
			hoTro.setNickSkypeChat(rs.getString("NickSkypeChat"));
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc"));
			danhMuc.setTenDanhMuc(rs.getString("TenDanhMuc"));
			hoTro.setDanhMuc(danhMuc);
			hoTro.setNhaCungCap(rs.getString("HoTen"));
			list.add(hoTro);
		}
		return list;
	}

	public boolean DungHoTro(int idHoTro) {
		return db.updateData("delete from HOTROONLINE where IdHoTro='"+idHoTro+"'");
	}
	
}
