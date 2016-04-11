package model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.bean.BaiViet;
import model.bean.DanhMuc;
import model.bean.DichVu;

public class DichVuDAO {
	DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public ArrayList<DichVu> getDanhSachDanhMuc(String txtFind, int page) throws SQLException {
		txtFind = FormatData.toUTF8(FormatData.FormatInputData(txtFind));
		String sql = "select IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, DiaDiemTrienKhai,DienThoaiLienHe, EmailLienHe, TenBaiViet,"
				+" TacGia, MoTa, NoiDung, NgayDang, HinhAnh, LuotXem,"
				+" ROW_NUMBER() OVER (ORDER BY DICHVU.IdDichVu desc) AS Row from DICHVU"
				+" inner join BAIVIET on BAIVIET.IdBaiViet = DICHVU.IdBaiViet"
				+" where NgayBatDau like '%"+txtFind+"%' or NgayKetThuc like '%"+txtFind+"%' or DienThoaiLienHe like '"+txtFind+"'"
				+" or EmailLienHe like '"+txtFind+"' or TenBaiViet like N'%"+txtFind+"%' or MoTa like N'%"+txtFind+"%'";
		db.setRecord(10); 
		db.createMenu("Dich-Vu?txtFind="+txtFind, page, sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		ResultSet rs = db.getResultSet("select * from ( "+sql+" ) as tam where Row between "
				+((db.getPage()-1)*db.getRecord()+1)+" and "+(db.getPage()*db.getRecord()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(FormatData.FormatOutputData(rs.getString("DiaDiemTrienKhai")));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public String getMemu() {
		return db.getMenu();
	}

	public boolean themDichVu(String tenDichVu, String moTa, String danhMuc, String noiDung, String hinhAnh,
			String nhaCungCap, String dienThoai, String email, String ngayBatDau, String ngayKetThuc) {
		String sql = "{call themDichVu(?,?,?,?,?,?,?,?,?,?,?,?) }";
		try {
			//Tạo đối tượng CallableStatement
			CallableStatement cstm = db.getCallableStatement(sql);
			//Cập nhật tham số đầu vào
			cstm.setString(1, tenDichVu);
			cstm.setString(2, moTa);
			cstm.setString(3, danhMuc);
			cstm.setString(4, noiDung);
			cstm.setString(5, hinhAnh);
			cstm.setString(6, "");
			cstm.setString(7, nhaCungCap);
			cstm.setString(8, dienThoai);
			cstm.setString(9, email);
			cstm.setString(10, ngayBatDau);
			cstm.setString(11, ngayKetThuc);
			//Cập nhật tham số đầu ra
			cstm.registerOutParameter(12, java.sql.Types.INTEGER);
			//Thực thi
			cstm.executeUpdate();
			//Lấy kết quả trả về
			int result = cstm.getInt(12);
			if(result > -1) {
				return true;
			}
		} catch (SQLException e) { 
			return false;
		}
		return false;
	}

	public boolean capNhatDichVu(String idDichVu, String tenDichVu, String moTa, String danhMuc, String noiDung,
			String hinhAnh, String nhaCungCap, String dienThoai, String email, String ngayBatDau, String ngayKetThuc) {
		String sql = "{call capNhatDichVu(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
		try {
			//Tạo đối tượng CallableStatement
			CallableStatement cstm = db.getCallableStatement(sql);
			//Cập nhật tham số đầu vào
			cstm.setString(1, idDichVu);
			cstm.setString(2, tenDichVu);
			cstm.setString(3, moTa);
			cstm.setString(4, danhMuc);
			cstm.setString(5, noiDung);
			cstm.setString(6, hinhAnh);
			cstm.setString(7, "Taikhoan002");
			cstm.setString(8, nhaCungCap);
			cstm.setString(9, dienThoai);
			cstm.setString(10, email);
			cstm.setString(11, FormatData.FormatDate(ngayBatDau));
			cstm.setString(12, FormatData.FormatDate(ngayKetThuc));
			//Cập nhật tham số đầu ra
			cstm.registerOutParameter(13, java.sql.Types.INTEGER);
			//Thực thi
			cstm.executeUpdate();
			//Lấy kết quả trả về
			int result = cstm.getInt(13);
			if(result > -1) {
				return true;
			}
		} catch (SQLException e) { 
			return false;
		}
		return false;
	}

	public DichVu getDichVu(String id, int quyen) throws SQLException {
		String sql = "select IdDichVu, IdNhaCungCap, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, DiaDiemTrienKhai, DienThoaiLienHe, EmailLienHe, BAIVIET.IdBaiViet, TenBaiViet,"
				+" TacGia, MoTa, NoiDung, NgayDang, HinhAnh, LuotXem, BAIVIET.IdDanhMuc, TenDanhMuc from DICHVU"
				+" inner join BAIVIET on BAIVIET.IdBaiViet = DICHVU.IdBaiViet"
				+" inner join DANHMUC on BAIVIET.IdDanhMuc = DANHMUC.IdDanhMuc"
				+" inner join NHACUNGCAP on BAIVIET.TaiKhoan = NHACUNGCAP.TaiKhoan"
				+" where IdDichVu='" + id +"' ";
		if(quyen==1) {
			sql += "and TinhTrang like N'Đăng bài'";
			db.updateData("update BAIVIET set LuotXem=LuotXem+1 where TinhTrang like N'Đăng bài' and IdBaiViet = (select top 1 IdBaiViet from DICHVU where IdDichVu='"+id+"')");
		}
		System.out.println("SQL : " + sql);
		ResultSet rs = db.getResultSet(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setIdNhaCungCap(rs.getInt("IdNhaCungCap"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(FormatData.FormatOutputData(rs.getString("DiaDiemTrienKhai")));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet(); 
			baiViet.setIdBaiViet(rs.getInt("IdBaiViet"));
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc"));
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			baiViet.setDanhMuc(danhMuc);
			dichVu.setBaiViet(baiViet);
			return dichVu;
		} else {
			return null;
		}
	}

	public boolean xoaDichVu(String id) {
		return db.updateData("delete from DichVu where IdDichVu='"+id+"'");
	}

	public boolean capNhatTinhTrang(String dichVu, String tinhTrang) {
		return db.updateData("update BAIVIET set TinhTrang=N'"
				+ FormatData.FormatInputData(tinhTrang)
				+"' where IdBaiViet in (select top 1 IdBaiViet from DICHVU"
				+ " where IdDichVu='"+dichVu+"')");
	}

	public boolean clientThemDichVu(String tenDichVu, String moTa, int danhMuc, String noiDung, String hinhAnh,
			String taiKhoan, String nhaCungCap, String dienThoai, String email, String ngayBatDau, 
			String ngayKetThuc, String loaiHinh, String diaDiem) {
		String sql = "{call themDichVu(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
		try {
			//Tạo đối tượng CallableStatement
			CallableStatement cstm = db.getCallableStatement(sql);
			//Cập nhật tham số đầu vào
			cstm.setString(1, tenDichVu);
			cstm.setString(2, moTa);
			cstm.setString(3, danhMuc+"");
			cstm.setString(4, noiDung);
			cstm.setString(5, hinhAnh);
			cstm.setString(6, taiKhoan);
			cstm.setString(7, nhaCungCap);
			cstm.setString(8, dienThoai);
			cstm.setString(9, email);
			cstm.setString(10, FormatData.FormatDate(ngayBatDau));
			cstm.setString(11, FormatData.FormatDate(ngayKetThuc));
			cstm.setString(12, loaiHinh);
			cstm.setString(13, diaDiem);
			//Cập nhật tham số đầu ra
			cstm.registerOutParameter(14, java.sql.Types.INTEGER);
			//Thực thi
			cstm.executeUpdate(); 
			//Lấy kết quả trả về
			int result = cstm.getInt(14);
			if(result > -1) {
				return true;
			}
		} catch (SQLException e) { 
			System.out.println("Thất bại tại đây!" + e.toString());
			return false;
		}
		System.out.println("Thất bại tại đây 1!");
		return false;
	}

	public boolean dangKyDichVu(String idDichVu, String hoTen, String email, String dienThoai, String tinNhan) {
		idDichVu = FormatData.FormatInputData(idDichVu);
		hoTen = FormatData.FormatInputData(hoTen);
		email = FormatData.FormatInputData(email);
		dienThoai = FormatData.FormatInputData(dienThoai);
		tinNhan = FormatData.FormatInputData(tinNhan);
		String sql = "{call dangKyDV(?,?,?,?,?,?) }";
		try {
			//Tạo đối tượng CallableStatement
			CallableStatement cstm = db.getCallableStatement(sql);
			//Cập nhật tham số đầu vào
			cstm.setString(1, idDichVu);
			cstm.setString(2, hoTen);
			cstm.setString(3, email);
			cstm.setString(4, dienThoai);
			cstm.setString(5, tinNhan);
			//Cập nhật tham số đầu ra
			cstm.registerOutParameter(6, java.sql.Types.INTEGER);
			//Thực thi
			cstm.executeUpdate();
			//Lấy kết quả trả về
			int result = cstm.getInt(6);
			if(result > -1) {
				return true;
			}
		} catch (SQLException e) { 
			System.out.println("Đăng ký thất bại 1 : " + e.toString());
			return false;
		}
		return false;
	}

	public List<DichVu> getDichVuHot() throws SQLException {
		String sql = "select top 4 IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, DiaDiemTrienKhai, DienThoaiLienHe, "
				+"EmailLienHe, BAIVIET.IdBaiViet, TenBaiViet, MoTa, NoiDung, HinhAnh, NgayDang, LuotXem, TacGia from DICHVU "
				+"inner join BAIVIET on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+"where TinhTrang like N'Đăng bài' and LoaiHinhDichVu not like N'Nhu cầu' and GETDATE() between ThoiGianBatDau and ThoiGianKetThuc "
				+"order by LuotXem desc";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public List<DichVu> getListDichVu(String txtFind, String idDanhMuc, String vitri) throws SQLException {
		txtFind = FormatData.FormatInputData(txtFind);
		idDanhMuc = FormatData.FormatInputData(idDanhMuc);
		String sql = "select top 10 * from ( select IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, "
				+ "ThoiGianKetThuc, DiaDiemTrienKhai, DienThoaiLienHe, EmailLienHe, BAIVIET.IdBaiViet, "
				+ "TenBaiViet, MoTa, NoiDung, HinhAnh, NgayDang, LuotXem, TacGia, "
				+ "ROW_NUMBER() OVER (ORDER BY NgayDang desc, LuotXem desc ) AS Row from DICHVU "
				+ "inner join BAIVIET on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where TinhTrang like N'Đăng bài' and LoaiHinhDichVu not like N'Nhu cầu' and (IdDanhMuc like '%"+idDanhMuc
				+ "%') and (ThoiGianBatDau like '%"+txtFind
				+ "%' or ThoiGianKetThuc like '%"+txtFind+"%' or DienThoaiLienHe like '"+txtFind
				+ "' or EmailLienHe like '"+txtFind+"' or TenBaiViet like N'%"+txtFind
				+ "%' or MoTa like N'%"+txtFind+"%')) as tam where Row > " + vitri;
		ResultSet rs = db.getResultSet(sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public List<DichVu> getNhuCauMoi() throws SQLException {
		String sql = "select top 4 IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, DiaDiemTrienKhai, DienThoaiLienHe, "
				+"EmailLienHe, BAIVIET.IdBaiViet, TenBaiViet, MoTa, NoiDung, HinhAnh, NgayDang, LuotXem, TacGia from DICHVU "
				+"inner join BAIVIET on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+"where TinhTrang like N'Đăng bài' and LoaiHinhDichVu like N'Nhu cầu' and GETDATE() between ThoiGianBatDau and ThoiGianKetThuc "
				+"order by LuotXem desc";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public List<DichVu> getListNhuCau(String txtFind, String idDanhMuc, String vitri) throws SQLException {
		txtFind = FormatData.FormatInputData(txtFind);
		idDanhMuc = FormatData.FormatInputData(idDanhMuc);
		String sql = "select top 10 * from ( select IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, "
				+ "ThoiGianKetThuc, DiaDiemTrienKhai, DienThoaiLienHe, EmailLienHe, BAIVIET.IdBaiViet, "
				+ "TenBaiViet, MoTa, NoiDung, HinhAnh, NgayDang, LuotXem, TacGia, "
				+ "ROW_NUMBER() OVER (ORDER BY NgayDang desc, LuotXem desc ) AS Row from DICHVU "
				+ "inner join BAIVIET on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "where TinhTrang like N'Đăng bài' and LoaiHinhDichVu like N'Nhu cầu' and (IdDanhMuc like '%"+idDanhMuc
				+ "%') and (ThoiGianBatDau like '%"+txtFind
				+ "%' or ThoiGianKetThuc like '%"+txtFind+"%' or DienThoaiLienHe like '"+txtFind
				+ "' or EmailLienHe like '"+txtFind+"' or TenBaiViet like N'%"+txtFind
				+ "%' or MoTa like N'%"+txtFind+"%')) as tam where Row > " + vitri;
		ResultSet rs = db.getResultSet(sql);
		System.out.println("SQL : " + sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public List<DichVu> getListDichVuMoi() throws SQLException {
		String sql = "select top 10 IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, DiaDiemTrienKhai, DienThoaiLienHe, EmailLienHe, TenBaiViet, "
			+ "TacGia, MoTa, NoiDung, NgayDang, HinhAnh, LuotXem, BAIVIET.IdDanhMuc, TenDanhMuc from DICHVU "
			+ "inner join BAIVIET on BAIVIET.IdBaiViet = DICHVU.IdBaiViet "
			+ "inner join DANHMUC on BAIVIET.IdDanhMuc = DANHMUC.IdDanhMuc "
			+ "where LoaiHinhDichVu not like N'Nhu cầu' and TinhTrang like N'Đăng bài' "
			+ "order by NgayDang desc";
		System.out.println("SQL : " + sql);
		ResultSet rs = db.getResultSet(sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public List<DichVu> getListDichVu(String theLoai) throws SQLException {
		String sql = "select top 10 IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, DiaDiemTrienKhai, DienThoaiLienHe, EmailLienHe, TenBaiViet, "
				+ "TacGia, MoTa, NoiDung, NgayDang, HinhAnh, LuotXem, BAIVIET.IdDanhMuc, TenDanhMuc from DICHVU "
				+ "inner join BAIVIET on BAIVIET.IdBaiViet = DICHVU.IdBaiViet "
				+ "inner join DANHMUC on BAIVIET.IdDanhMuc = DANHMUC.IdDanhMuc "
				+ "where LoaiHinhDichVu like N'"+theLoai+"' and TinhTrang like N'Đăng bài' "
				+ "order by NgayDang desc";
			System.out.println("SQL : " + sql);
			ResultSet rs = db.getResultSet(sql);
			ArrayList<DichVu> list = new ArrayList<DichVu>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			while(rs.next()){
				DichVu dichVu = new DichVu();
				dichVu.setIdDichVu(rs.getInt("IdDichVu"));
				dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
				dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
				dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
				dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
				dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
				dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
				BaiViet baiViet = new BaiViet();
				baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
				baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
				baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
				baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
				baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
				baiViet.setAnhMoTa(rs.getString("HinhAnh"));
				baiViet.setLuocXem(rs.getInt("LuotXem"));
				dichVu.setBaiViet(baiViet);
				list.add(dichVu);
			}
			return list;
	}

	public boolean dangKyNhuCau(String idNhuCau, String idTaiKhoan, String tinNhan) {
		tinNhan = FormatData.FormatInputData(tinNhan);
		String sql = "insert into DANGKYDICHVU(IdDichVu, TaiKhoan, TinNhan, TinhTrang, NgayDangKy) "
				+ "values ('"+idNhuCau+"','"+idTaiKhoan+"',N'"+tinNhan+"',N'Đăng ký', GETDATE())";
		return db.updateData(sql);
	}

	public List<DichVu> getListDichVu(String txtFind, int page) throws SQLException {
		txtFind = FormatData.FormatInputData(txtFind);
		String sql = "select IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, "
				+ "DiaDiemTrienKhai, DienThoaiLienHe, EmailLienHe, TenBaiViet, "
				+ "TacGia, MoTa, NoiDung, NgayDang, HinhAnh, TinhTrang, LuotXem, BAIVIET.IdDanhMuc, TenDanhMuc, "
				+ "ROW_NUMBER() OVER (ORDER BY DICHVU.IdDichVu desc) AS Row from DICHVU "
				+ "inner join BAIVIET on BAIVIET.IdBaiViet = DICHVU.IdBaiViet "
				+ "inner join DANHMUC on BAIVIET.IdDanhMuc = DANHMUC.IdDanhMuc "
				+ "where TinhTrang like N'%' and LoaiHinhDichVu not like N'Nhu cầu' and (DiaDiemTrienKhai like N'%"
				+ txtFind + "%' or EmailLienHe like '"+txtFind+"%' or TenBaiViet like N'%"
				+ txtFind + "%' or MoTa like N'%"+txtFind+"%' or TacGia like N'%"+txtFind+"%') ";
		System.out.println("Xem  : " + sql);
		db.setRecord(10); 
		db.createMenu("dich-vu.action?txtFind="+txtFind, page, sql);
		ResultSet rs = db.getResultSet("select * from ( "+sql+" ) as tam where Row between "
				+((db.getPage()-1)*db.getRecord()+1)+" and "+(db.getPage()*db.getRecord()));
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setTinhTrang(FormatData.FormatOutputData(rs.getString("TinhTrang")));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public List<DichVu> getListNhuCau(String txtFind, int page) throws SQLException {
		txtFind = FormatData.FormatInputData(txtFind);
		String sql = "select IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, "
				+ "DiaDiemTrienKhai, DienThoaiLienHe, EmailLienHe, TenBaiViet, "
				+ "TacGia, MoTa, NoiDung, NgayDang, HinhAnh, TinhTrang, LuotXem, BAIVIET.IdDanhMuc, TenDanhMuc, "
				+ "ROW_NUMBER() OVER (ORDER BY DICHVU.IdDichVu desc) AS Row from DICHVU "
				+ "inner join BAIVIET on BAIVIET.IdBaiViet = DICHVU.IdBaiViet "
				+ "inner join DANHMUC on BAIVIET.IdDanhMuc = DANHMUC.IdDanhMuc "
				+ "where TinhTrang like N'%' and LoaiHinhDichVu like N'Nhu cầu' and (DiaDiemTrienKhai like N'%"
				+ txtFind + "%' or EmailLienHe like '"+txtFind+"%' or TenBaiViet like N'%"
				+ txtFind + "%' or MoTa like N'%"+txtFind+"%' or TacGia like N'%"+txtFind+"%') ";
		System.out.println("Xem  : " + sql);
		db.setRecord(10); 
		db.createMenu("dich-vu.action?txtFind="+txtFind, page, sql);
		ResultSet rs = db.getResultSet("select * from ( "+sql+" ) as tam where Row between "
				+((db.getPage()-1)*db.getRecord()+1)+" and "+(db.getPage()*db.getRecord()));
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setTinhTrang(FormatData.FormatOutputData(rs.getString("TinhTrang")));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public List<DichVu> getDichVuDangKy() throws SQLException {
		String sql = "select IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, "
				+ "DiaDiemTrienKhai, DienThoaiLienHe, EmailLienHe, TenBaiViet, "
				+ "TacGia, MoTa, NoiDung, NgayDang, HinhAnh, TinhTrang, LuotXem, BAIVIET.IdDanhMuc, TenDanhMuc, "
				+ "ROW_NUMBER() OVER (ORDER BY DICHVU.IdDichVu desc) AS Row from DICHVU "
				+ "inner join BAIVIET on BAIVIET.IdBaiViet = DICHVU.IdBaiViet "
				+ "inner join DANHMUC on BAIVIET.IdDanhMuc = DANHMUC.IdDanhMuc "
				+ "where TinhTrang like N'Mới đăng' and LoaiHinhDichVu not like N'Nhu cầu'";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setTinhTrang(FormatData.FormatOutputData(rs.getString("TinhTrang")));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public List<DichVu> getNhuCauDangKy() throws SQLException {
		String sql = "select IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, "
				+ "DiaDiemTrienKhai, DienThoaiLienHe, EmailLienHe, TenBaiViet, "
				+ "TacGia, MoTa, NoiDung, NgayDang, HinhAnh, TinhTrang, LuotXem, BAIVIET.IdDanhMuc, TenDanhMuc, "
				+ "ROW_NUMBER() OVER (ORDER BY DICHVU.IdDichVu desc) AS Row from DICHVU "
				+ "inner join BAIVIET on BAIVIET.IdBaiViet = DICHVU.IdBaiViet "
				+ "inner join DANHMUC on BAIVIET.IdDanhMuc = DANHMUC.IdDanhMuc "
				+ "where TinhTrang like N'Mới đăng' and LoaiHinhDichVu like N'Nhu cầu'";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			dichVu.setDienThoaiLienHe(rs.getString("DienThoaiLienHe"));
			dichVu.setEmailLienHe(rs.getString("EmailLienHe"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTenTacGia(FormatData.FormatOutputData(rs.getString("TacGia")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNoiDung(FormatData.FormatOutputData(rs.getString("NoiDung")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setTinhTrang(FormatData.FormatOutputData(rs.getString("TinhTrang")));
			baiViet.setLuocXem(rs.getInt("LuotXem"));
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

	public boolean ChekDangKy(String idDichVu, String email) {
		String sql = "select * from DANGKYDICHVU "
				+"inner join TAIKHOAN on TAIKHOAN.TaiKhoan=DANGKYDICHVU.TaiKhoan "
				+"where IdDichVu='"+idDichVu+"' and Email='"+email+"'";
		System.out.println("SQL : " + sql);
		ResultSet rs = db.getResultSet(sql);
		try {
			if(rs.next()){
				System.out.println("có đăng ký");
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	
	public List<DichVu> getDichVuDangKy(String idTaiKhoan) throws SQLException {
		String sql = "select DICHVU.IdDichVu, IdDangKy, LoaiHinhDichVu, TenBaiViet, NgayDangKy, DANGKYDICHVU.TinhTrang from DICHVU "
				+ "inner join BAIVIET on DICHVU.IdBaiViet=BAIVIET.IdBaiViet "
				+ "inner join DANGKYDICHVU on DICHVU.IdDichVu=DANGKYDICHVU.IdDichVu "
				+ "where DANGKYDICHVU.TaiKhoan='"+idTaiKhoan+"' and LoaiHinhDichVu not like 'Nhu cầu' "
				+ "order by NgayDangKy desc";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setIdNhaCungCap(rs.getInt("IdDangKy"));
			dichVu.setLoaiHinhDichVu(FormatData.FormatOutputData(rs.getString("LoaiHinhDichVu")));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setTinhTrang(FormatData.FormatOutputData(rs.getString("TinhTrang")));
			dichVu.setBaiViet(baiViet);
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("NgayDangKy")));
			list.add(dichVu);
		}
		return list;
	}

	public List<DichVu> getNhuCauDang(String idTaiKhoan) throws SQLException {
		String sql = "select IdDichVu, LoaiHinhDichVu, ThoiGianBatDau, ThoiGianKetThuc, DiaDiemTrienKhai, BAIVIET.IdBaiViet, TenBaiViet, "
				+ "MoTa, NgayDang, HinhAnh, TinhTrang, BAIVIET.IdDanhMuc, TenDanhMuc from DICHVU "
				+ "inner join BAIVIET on BAIVIET.IdBaiViet = DICHVU.IdBaiViet "
				+ "inner join DANHMUC on BAIVIET.IdDanhMuc = DANHMUC.IdDanhMuc "
				+ "where TaiKhoan like '"+idTaiKhoan+"' and LoaiHinhDichVu like N'Nhu cầu' order by NgayDang desc";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		while(rs.next()){
			DichVu dichVu = new DichVu();
			dichVu.setIdDichVu(rs.getInt("IdDichVu"));
			dichVu.setLoaiHinhDichVu(rs.getString("LoaiHinhDichVu"));
			dichVu.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			dichVu.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			dichVu.setDiaChiTrienKhai(rs.getString("DiaDiemTrienKhai"));
			BaiViet baiViet = new BaiViet();
			baiViet.setTenBaiViet(FormatData.FormatOutputData(rs.getString("TenBaiViet")));
			baiViet.setMoTa(FormatData.FormatOutputData(rs.getString("MoTa")));
			baiViet.setNgayDang(sdf.format(rs.getTimestamp("NgayDang")));
			baiViet.setAnhMoTa(rs.getString("HinhAnh"));
			baiViet.setTinhTrang(FormatData.FormatOutputData(rs.getString("TinhTrang")));
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setIdDanhMuc(rs.getInt("IdDanhMuc"));
			danhMuc.setTenDanhMuc(FormatData.FormatOutputData(rs.getString("TenDanhMuc")));
			baiViet.setDanhMuc(danhMuc);
			dichVu.setBaiViet(baiViet);
			list.add(dichVu);
		}
		return list;
	}

}
