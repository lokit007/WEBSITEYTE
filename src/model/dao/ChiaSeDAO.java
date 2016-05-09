package model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.bean.BaiViet;
import model.bean.DanhMuc;

public class ChiaSeDAO {
	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public String getMenu() {
		return db.getMenu();
	}
	
	public List<BaiViet> getListQuanTam() throws SQLException {
		String sql = "select top 10 BAIVIET.IdBaiViet, TenBaiViet, MoTa, NoiDung, HinhAnh, "
				+ "NgayDang, TinhTrang, LuotXem, TacGia, DANHMUC.IdDanhMuc, TenDanhMuc from BAIVIET "
				+ "inner join DANHMUC on BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc "
				+ "left join DICHVU on BAIVIET.IdBaiViet=DICHVU.IdBaiViet "
				+ "where TinhTrang like N'Đăng bài' and IdDichVu is null and NgayDang > DATEADD(MONTH, -1, NgayDang) "
				+ "order by LuotXem desc, NgayDang desc";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<BaiViet> list = new ArrayList<BaiViet>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			BaiViet baiViet = new BaiViet(); 
			baiViet.setIdBaiViet(rs.getInt("IdBaiViet"));
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setTinhTrang(rs.getString("TinhTrang"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc"));
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			baiViet.setDanhMuc(danhMuc);
			list.add(baiViet);
		}
		return list;
	}

	public List<BaiViet> getListMoiChiaSe() throws SQLException {
		String sql = "select top 10 BAIVIET.IdBaiViet, TenBaiViet, MoTa, NoiDung, HinhAnh, "
				+ "NgayDang, TinhTrang, LuotXem, TacGia, DANHMUC.IdDanhMuc, TenDanhMuc from BAIVIET "
				+ "inner join DANHMUC on BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc "
				+ "left join DICHVU on BAIVIET.IdBaiViet=DICHVU.IdBaiViet "
				+ "where TinhTrang like N'Đăng bài' and IdDichVu is null and NgayDang > DATEADD(MONTH, -5, NgayDang) "
				+ "order by NgayDang desc";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<BaiViet> list = new ArrayList<BaiViet>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			BaiViet baiViet = new BaiViet(); 
			baiViet.setIdBaiViet(rs.getInt("IdBaiViet"));
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setTinhTrang(rs.getString("TinhTrang"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc"));
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			baiViet.setDanhMuc(danhMuc);
			list.add(baiViet);
		}
		return list;
	}

	public List<BaiViet> getListChiaSe(String txtFind, String idDanhMuc, String viTri) throws SQLException {
		txtFind = FormatData.FormatInputData(txtFind);
		idDanhMuc = FormatData.FormatInputData(idDanhMuc);
		String sql = "select top 10 * from ( select BAIVIET.IdBaiViet, TenBaiViet, MoTa, NoiDung, HinhAnh, NgayDang, " 
				+"TinhTrang, LuotXem, TacGia, DANHMUC.IdDanhMuc, TenDanhMuc, "
				+"ROW_NUMBER() OVER (ORDER BY NgayDang desc, LuotXem desc ) as Row from BAIVIET "
				+"inner join DANHMUC on BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc "
				+"left join DICHVU on BAIVIET.IdBaiViet=DICHVU.IdBaiViet "
				+"where TinhTrang like N'Đăng bài' and IdDichVu is null and BAIVIET.IdDanhMuc like '%"+idDanhMuc+"' and (TenBaiViet like N'%"+txtFind+"%' or "
				+"MoTa like N'%"+txtFind+"%' or TacGia like N'%"+txtFind+"%')) as tam where Row > '"+viTri+"'";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<BaiViet> list = new ArrayList<BaiViet>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			BaiViet baiViet = new BaiViet(); 
			baiViet.setIdBaiViet(rs.getInt("IdBaiViet"));
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setTinhTrang(rs.getString("TinhTrang"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc"));
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			baiViet.setDanhMuc(danhMuc);
			list.add(baiViet);
		}
		return list;
	}

	public BaiViet getChiaSe(String idBaiViet) throws SQLException {
		idBaiViet = FormatData.FormatInputData(idBaiViet);
		String sql = "select BAIVIET.IdBaiViet, TenBaiViet, MoTa, NoiDung, HinhAnh, NgayDang, " 
				+"TinhTrang, LuotXem, TacGia, DANHMUC.IdDanhMuc, TenDanhMuc from BAIVIET "
				+"inner join DANHMUC on BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc "
				+"left join DICHVU on BAIVIET.IdBaiViet=DICHVU.IdBaiViet "
				+"where IdDichVu is null and BAIVIET.IdBaiViet = '"+idBaiViet+"'";
		db.updateData("update BAIVIET set LuotXem=LuotXem+1 where TinhTrang like N'Đăng bài' and IdBaiViet='"+idBaiViet+"'");
		ResultSet rs = db.getResultSet(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if(rs.next()){
			BaiViet baiViet = new BaiViet(); 
			baiViet.setIdBaiViet(rs.getInt("IdBaiViet"));
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setTinhTrang(rs.getString("TinhTrang"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc"));
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			baiViet.setDanhMuc(danhMuc);
			return baiViet;
		}
		return null;
	}

	public List<BaiViet> getListChiaSe(String txtFind, int page) throws SQLException {
		txtFind = FormatData.FormatInputData(txtFind);
		String sql = "select BAIVIET.IdBaiViet, TenBaiViet, MoTa, NoiDung, HinhAnh, NgayDang, " 
				+"TinhTrang, LuotXem, TacGia, DANHMUC.IdDanhMuc, TenDanhMuc, "
				+"ROW_NUMBER() OVER (ORDER BY NgayDang desc, LuotXem desc ) as Row from BAIVIET "
				+"inner join DANHMUC on BAIVIET.IdDanhMuc=DANHMUC.IdDanhMuc "
				+"left join DICHVU on BAIVIET.IdBaiViet=DICHVU.IdBaiViet "
				+"where IdDichVu is null and (TenBaiViet like N'%"+txtFind+"%' or "
				+"MoTa like N'%"+txtFind+"%' or TacGia like N'%"+txtFind+"%')";
		ArrayList<BaiViet> list = new ArrayList<BaiViet>();
		db.setRecord(10); 
		db.createMenu("chia-se.action?txtFind="+txtFind, page, sql);
		ResultSet rs = db.getResultSet("select * from ( "+sql+" ) as tam where Row between "
				+((db.getPage()-1)*db.getRecord()+1)+" and "+(db.getPage()*db.getRecord()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			BaiViet baiViet = new BaiViet(); 
			baiViet.setIdBaiViet(rs.getInt("IdBaiViet"));
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setTinhTrang(rs.getString("TinhTrang"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc"));
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			baiViet.setDanhMuc(danhMuc);
			list.add(baiViet);
		}
		return list;
	}

	public boolean xoaChiaSe(int idBaiViet) {
		return db.updateData("delete from BAIVIET where IdBaiViet='"+idBaiViet+"' and TinhTrang not like N'Đã đăng'");
	}

	public boolean ThemChiaSe(String tenBaiViet, String moTa, int danhMuc, String noiDung, String hinhAnh,
			String taiKhoan, String tacGia) {
		String sql = "{call themBaiViet(?,?,?,?,?,?,?,?) }";
		try {
			//Tạo đối tượng CallableStatement
			CallableStatement cstm = db.getCallableStatement(sql);
			//Cập nhật tham số đầu vào
			cstm.setString(1, tenBaiViet);
			cstm.setString(2, danhMuc+"");
			cstm.setString(3, taiKhoan);
			cstm.setString(4, tacGia);
			cstm.setString(5, moTa);
			cstm.setString(6, noiDung);
			cstm.setString(7, hinhAnh);
			//Cập nhật tham số đầu ra
			cstm.registerOutParameter(8, java.sql.Types.INTEGER);
			//Thực thi
			cstm.executeUpdate();
			//Lấy kết quả trả về
			int result = cstm.getInt(8);
			if(result > -1) {
				return true;
			}
		} catch (SQLException e) { 
			return false;
		}
		return false;
	}

	public boolean capNhatChiaSe(int idBaiViet, String tenBaiViet, String moTa, int danhMuc, String noiDung,
			String hinhAnh, String tacGia) {
		tenBaiViet = FormatData.FormatInputData(tenBaiViet);
		moTa = FormatData.FormatInputData(moTa);
		noiDung = FormatData.FormatInputData(noiDung);
		hinhAnh = FormatData.FormatInputData(hinhAnh);
		tacGia = FormatData.FormatInputData(tacGia);
		String sql = "update BAIVIET set TenBaiViet=N'"+tenBaiViet+"', MoTa=N'"+moTa
				+"', IdDanhMuc='"+danhMuc+"', NoiDung=N'"+noiDung+"', HinhAnh='"+hinhAnh
				+"', TacGia=N'"+tacGia+"' where IdBaiViet='"+idBaiViet+"' and TinhTrang like N'Mới đăng'";
		System.out.println("SQL : " + sql);
		return db.updateData(sql);
	}

	public boolean CapNhatTrangThai(String idKey, String chanState) {
		return db.updateData("update BAIVIET set TinhTrang=N'"+chanState+"' where IdBaiViet='"+idKey+"'");
	}

}
