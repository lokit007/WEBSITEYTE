package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.DanhMuc;

public class DanhMucDAO {

	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public ArrayList<DanhMuc> getDanhSachDanhMuc(String txtFind, int page) throws SQLException {
		txtFind = FormatData.toUTF8(FormatData.FormatInputData(txtFind));
		String sql = "select DANHMUC.IdDanhMuc, TenDanhMuc, HienThi, COUNT(BAIVIET.IdDanhMuc) as SoBaiViet,"
				+" ROW_NUMBER() OVER (ORDER BY DANHMUC.IdDanhMuc desc) AS Row from DANHMUC"
				+" left join BAIVIET on BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc"
				+" left join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet"
				+" where TenDanhMuc like N'%" + txtFind + "%'"
				+" group by DANHMUC.IdDanhMuc, TenDanhMuc, HienThi";
		db.setRecord(10); 
		db.createMenu("danh-muc.action?txtFind="+txtFind, page, sql);
		ArrayList<DanhMuc> list = new ArrayList<DanhMuc>();
		ResultSet rs = db.getResultSet("select * from ( "+sql+" ) as tam where Row between "
				+((db.getPage()-1)*db.getRecord()+1)+" and "+(db.getPage()*db.getRecord()));
		while(rs.next()){
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc")); 
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			danhMuc.setHienThi(rs.getString("HienThi"));
			danhMuc.setSoBaiViet(rs.getInt("SoBaiViet"));
			list.add(danhMuc);
		}
		return list;
	}

	public String getMenu() {
		return db.getMenu();
	}

	public boolean themDanhMuc(String ten) {
		return db.updateData("insert into DANHMUC(TenDanhMuc) values (N'"+FormatData.FormatInputData(ten)+"')");
	}

	public boolean capNhatDanhMuc(String idDanhMuc, String ten) {
		return db.updateData("update DANHMUC set TenDanhMuc=N'"+FormatData.FormatInputData(ten)+"' where IdDanhMuc='"+idDanhMuc+"'");
	}

	public boolean xoaDanhMuc(String idDanhMuc) {
		return db.updateData("delete from DANHMUC where IdDanhMuc='"+idDanhMuc+"'");
	}

	public ArrayList<DanhMuc> getListDanhMucDichVu() throws SQLException {
		String sql = "select DANHMUC.IdDanhMuc, TenDanhMuc, COUNT(BAIVIET.IdDanhMuc) as SoBaiViet,"
				+" ROW_NUMBER() OVER (ORDER BY DANHMUC.IdDanhMuc desc) AS Row from DANHMUC"
				+" left join BAIVIET on BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc"
				+" left join DICHVU on DICHVU.IdBaiViet=BAIVIET.IdBaiViet"
				+" group by DANHMUC.IdDanhMuc, TenDanhMuc";
		ArrayList<DanhMuc> list = new ArrayList<DanhMuc>();
		ResultSet rs = db.getResultSet(sql);
		while(rs.next()){
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc")); 
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			danhMuc.setSoBaiViet(rs.getInt("SoBaiViet"));
			list.add(danhMuc);
		}
		return list;
	}

	public boolean themDanhMuc(String tenDanhMuc, String hthi) {
		tenDanhMuc = FormatData.FormatInputData(tenDanhMuc);
		hthi = FormatData.FormatInputData(hthi);
		String sql = "insert into DANHMUC(TenDanhMuc, HienThi) values (N'"+tenDanhMuc+"', N'"+hthi+"')";
		return db.updateData(sql);
	}

	public DanhMuc getDanhMuc(int idDanhMuc) throws SQLException {
		String sql = "select top 1 * from DANHMUC where IdDanhMuc='"+idDanhMuc+"'";
		ResultSet rs = db.getResultSet(sql);
		if(rs.next()){
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc")); 
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			danhMuc.setHienThi(FormatData.FormatOutputData(rs.getString("HienThi")));
			danhMuc.setSoBaiViet(0);
			return danhMuc;
		}
		return null;
	}

	public boolean capNhatDanhMuc(int idDanhMuc, String tenDanhMuc, String hthi) {
		tenDanhMuc = FormatData.FormatInputData(tenDanhMuc);
		String sql = "update DANHMUC set TenDanhMuc=N'"+tenDanhMuc+"', HienThi=N'"+hthi+"' where IdDanhMuc='"+idDanhMuc+"'";
		return db.updateData(sql);
	}

	public List<DanhMuc> getListDanhMuc(String hienThi) throws SQLException {
		String sql = "select DANHMUC.IdDanhMuc, TenDanhMuc, COUNT(BAIVIET.IdDanhMuc) as SoBaiViet,"
				+" ROW_NUMBER() OVER (ORDER BY DANHMUC.IdDanhMuc desc) AS Row from DANHMUC"
				+" left join BAIVIET on BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc"
				+" where HienThi like N'%"+hienThi+"%' "
				+" group by DANHMUC.IdDanhMuc, TenDanhMuc";
		ArrayList<DanhMuc> list = new ArrayList<DanhMuc>();
		ResultSet rs = db.getResultSet(sql);
		while(rs.next()){
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc")); 
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			danhMuc.setSoBaiViet(rs.getInt("SoBaiViet"));
			list.add(danhMuc);
		}
		return list;
	} 

}
