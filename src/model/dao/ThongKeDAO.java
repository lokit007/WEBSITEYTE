package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.ThongKeBaiViet;
import model.bean.ThongKeDanhMuc;
import model.bean.ThongKeTaiKhoan;

public class ThongKeDAO {

	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public List<ThongKeDanhMuc> getThongKeDanhMuc(String tuNgay, String denNgay) throws SQLException {
		tuNgay = FormatData.FormatDate(tuNgay);
		denNgay = FormatData.FormatDate(denNgay);
		String sql = "select * from ( select DANHMUC.IdDanhMuc, TenDanhMuc, "
				+ "(select COUNT(*) from BAIVIET inner join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc and LoaiHinhDichVu like N'Từ thiện' "
				+ "and NgayDang between '"+tuNgay+"' and '"+denNgay+"') as SoDVTT, "
				+ "(select COUNT(*) from BAIVIET inner join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc and LoaiHinhDichVu like N'Dịch vụ công' "
				+ "and NgayDang between '"+tuNgay+"' and '"+denNgay+"') as SoDVC, "
				+ "(select COUNT(*) from BAIVIET left join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc and LoaiHinhDichVu like N'Dịch vụ tư' "
				+ "and NgayDang between '"+tuNgay+"' and '"+denNgay+"') as SoDVTN, "
				+ "(select COUNT(*) from BAIVIET left join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc and LoaiHinhDichVu like N'Nhu cầu' "
				+ "and NgayDang between '"+tuNgay+"' and '"+denNgay+"') as SoNCDV "
				+ "from DANHMUC) as tam where SoDVTT>0 or SoDVC>0 or SoDVTN>0 or SoNCDV>0";
		ArrayList<ThongKeDanhMuc> list = new ArrayList<ThongKeDanhMuc>();
		ResultSet rs = db.getResultSet(sql);
		while(rs.next()){
			ThongKeDanhMuc tk = new ThongKeDanhMuc();
			tk.setId(rs.getInt("IdDanhMuc"));
			tk.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			tk.setSoDVTT(rs.getInt("SoDVTT"));
			tk.setSoDVC(rs.getInt("SoDVC"));
			tk.setSoDVTN(rs.getInt("SoDVTN"));
			tk.setSoNCDV(rs.getInt("SoNCDV"));
			list.add(tk);
		}
		return list;
	}

	public List<ThongKeBaiViet> getThongKeBaiViet(String tuNgay, String denNgay) throws SQLException {
		tuNgay = FormatData.FormatDate(tuNgay);
		denNgay = FormatData.FormatDate(denNgay);
		String sql = "select top 10 BAIVIET.IdBaiViet, TenBaiViet, HinhAnh, LuotXem from BAIVIET "
				+ "inner join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where LoaiHinhDichVu not like N'Nhu cầu' and NgayDang between '"+tuNgay+"' and '"+denNgay+"' "
				+ "order by LuotXem desc;"; 
		ArrayList<ThongKeBaiViet> list = new ArrayList<ThongKeBaiViet>();
		ResultSet rs = db.getResultSet(sql);
		while(rs.next()){
			ThongKeBaiViet tk = new ThongKeBaiViet();
			tk.setId(rs.getInt("IdBaiViet"));
			tk.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			tk.setHinhAnh(FormatData.FormatOutputData(rs.getString("HinhAnh")));
			tk.setLuocXem(rs.getInt("LuotXem"));
			list.add(tk);
		}
		return list;
	}

	public HashMap<String, Integer> getThongKeDichVu(String tuNgay, String denNgay) throws SQLException {
		tuNgay = FormatData.FormatDate(tuNgay);
		denNgay = FormatData.FormatDate(denNgay);
		String sql = "select LoaiHinhDichVu, COUNT(IdDichVu) as SoLuong from DICHVU "
				+ "inner join BAIVIET on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where NgayDang between '"+tuNgay+"' and '"+denNgay+"' "
				+ "group by LoaiHinhDichVu;";
		HashMap<String, Integer> maps = new HashMap<String, Integer>();
		ResultSet rs = db.getResultSet(sql);
		while(rs.next()){
			maps.put(rs.getString("LoaiHinhDichVu"), rs.getInt("SoLuong"));
		}
		return maps;
	}

	public List<ThongKeTaiKhoan> getThongKeTaiKhoan(String tuNgay, String denNgay) throws SQLException {
		tuNgay = FormatData.FormatDate(tuNgay);
		denNgay = FormatData.FormatDate(denNgay);
		String sql = "select * from (select TAIKHOAN.TaiKhoan, HoTen, (select COUNT(*) from BAIVIET "
				+ "inner join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where BAIVIET.TaiKhoan=TAIKHOAN.TaiKhoan and LoaiHinhDichVu not like N'Nhu cầu' "
				+ "and NgayDang between '"+tuNgay+"' and '"+denNgay+"') as SoDichVu, "
				+ "(select COUNT(*) from BAIVIET inner join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where BAIVIET.TaiKhoan=TAIKHOAN.TaiKhoan and LoaiHinhDichVu not like N'Nhu cầu' "
				+ "and NgayDang between '"+tuNgay+"' and '"+denNgay+"') as SoNhuCau, "
				+ "(select COUNT(*) from BAIVIET left join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where BAIVIET.TaiKhoan=TAIKHOAN.TaiKhoan and IdDichVu is null "
				+ "and NgayDang between '"+tuNgay+"' and '"+denNgay+"') as SoBaiViet "
				+ "from TAIKHOAN inner join NHACUNGCAP on NHACUNGCAP.TaiKhoan=TAIKHOAN.TaiKhoan) "
				+ "as tam where SoDichVu>0 or SoNhuCau>0 or SoBaiViet>0";
		ArrayList<ThongKeTaiKhoan> list = new ArrayList<ThongKeTaiKhoan>();
		ResultSet rs = db.getResultSet(sql);
		while(rs.next()){
			ThongKeTaiKhoan tk = new ThongKeTaiKhoan();
			tk.setTaiKhoan(FormatData.FormatOutputData(rs.getString("TaiKhoan")));
			tk.setTenNhaCungCap(FormatData.FormatOutputData(rs.getString("HoTen")));
			tk.setSoDichVu(rs.getInt("SoDichVu"));
			tk.setSoNhuCau(rs.getInt("SoNhuCau"));
			tk.setSoBaiViet(rs.getInt("SoBaiViet"));
			list.add(tk);
		}
		return list;
	}

}
