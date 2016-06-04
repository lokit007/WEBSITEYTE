package model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.bean.NhaCungCap;
import model.bean.TaiKhoan;

public class TaiKhoanDAO {

	DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}
	
	public String getMemu() {
		return db.getMenu();
	}
	
	public ArrayList<TaiKhoan> getDanhSachTaiKhoan(String listSelect, String txtFind, int page) throws SQLException {
		txtFind = FormatData.FormatInputData(txtFind);
		String sql = "";
		String url = "";
		if("Admin".equals(listSelect)){
			url = "thanh-vien-quantri.action?txtFind=";
			sql = "select TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, NgayThamGia, "
					+"ROW_NUMBER() OVER (ORDER BY TAIKHOAN.TaiKhoan desc) AS Row from TAIKHOAN "
					+"where LoaiTaiKhoan like 'admin' and ("
					+"TAIKHOAN.TaiKhoan like '"+txtFind+"' or HoTen like N'%"+txtFind+"%' or DiaChi like N'%"+txtFind
					+"%' or DienThoai like '"+txtFind+"%' or Email like '"+txtFind+"%')";
		} else if("Nha-Cung-Cap".equals(listSelect)){
			url = "thanh-vien-nhacungcap.action?txtFind=";
			sql = "select TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, NgayThamGia, "
					+"ROW_NUMBER() OVER (ORDER BY TAIKHOAN.TaiKhoan desc) AS Row from TAIKHOAN "
					+"inner join NHACUNGCAP on TAIKHOAN.TaiKhoan=NHACUNGCAP.TaiKhoan "
					+"where TAIKHOAN.TaiKhoan like '"+txtFind+"' or HoTen like N'%"+txtFind+"%' or DiaChi like N'%"+txtFind
					+"%' or DienThoai like '"+txtFind+"%' or Email like '"+txtFind+"%'";
		} else {
			url = "thanh-vien-nguoidung.action?txtFind=";
			sql = "select TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, NgayThamGia, "
					+"ROW_NUMBER() OVER (ORDER BY TAIKHOAN.TaiKhoan desc) AS Row from TAIKHOAN "
					+"where LoaiTaiKhoan like N'Người dùng' and ("
					+"TAIKHOAN.TaiKhoan like '"+txtFind+"' or HoTen like N'%"+txtFind+"%' or DiaChi like N'%"+txtFind
					+"%' or DienThoai like '"+txtFind+"%' or Email like '"+txtFind+"%')";
		}
		ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
		db.setRecord(10); 
		db.createMenu(url+txtFind, page, sql);
		ResultSet rs = db.getResultSet("select * from ( "+sql+" ) as tam where Row between "
				+((db.getPage()-1)*db.getRecord()+1)+" and "+(db.getPage()*db.getRecord()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			TaiKhoan taiKhoan = new TaiKhoan();
			taiKhoan.setIdTaiKhoan(rs.getString("TaiKhoan"));
			taiKhoan.setMatKhau(rs.getString("MatKhau"));
			taiKhoan.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
			taiKhoan.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
			taiKhoan.setDienThoai(rs.getString("DienThoai"));
			taiKhoan.setEmail(rs.getString("Email"));
			taiKhoan.setTinhTrang(rs.getString("TinhTrang"));
			taiKhoan.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
			list.add(taiKhoan);
		}
		return list;
	}

	public TaiKhoan getTaiKhoan(String taiKhoan, String matKhau) {
		taiKhoan = FormatData.FormatInputData(taiKhoan);
		matKhau = FormatData.FormatMD5(matKhau);
		String sql = "select TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, "
				+ "DienThoai, Email, TinhTrang, NgayThamGia, LoaiTaiKhoan from TAIKHOAN "
				+ "where TAIKHOAN.TaiKhoan like '"+taiKhoan+"' and MatKhau like '"+matKhau+"' ";
		ResultSet rs = db.getResultSet(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if(rs.next()){
				TaiKhoan user = new TaiKhoan();
				user.setIdTaiKhoan(rs.getString("TaiKhoan"));
				user.setMatKhau(rs.getString("MatKhau"));
				user.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
				user.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
				user.setDienThoai(rs.getString("DienThoai"));
				user.setEmail(rs.getString("Email"));
				user.setTinhTrang(rs.getString("TinhTrang"));
				user.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
				user.setLoaiTaiKhoan(rs.getString("LoaiTaiKhoan"));
				return user;
			}
		} catch (SQLException e) {
			return null;
		}
		return null;
	}

	public boolean checkMail(String email) {
		String sql = "select 1 from TAIKHOAN where Email like '" + email + "'";
		ResultSet rs = db.getResultSet(sql);
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	public boolean checkTaiKhoan(String taiKhoan) {
		String sql = "select 1 from TAIKHOAN where TaiKhoan like '" + taiKhoan + "'";
		ResultSet rs = db.getResultSet(sql);
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	public boolean themTaiKhoan(TaiKhoan user) {
		String sql = "insert into TAIKHOAN values ('"+FormatData.FormatInputData(user.getIdTaiKhoan())
			+"', '"+FormatData.FormatMD5(user.getMatKhau())
			+"', N'"+FormatData.FormatInputData(user.getHoTen())
			+"', N'"+FormatData.FormatInputData(user.getDiaChi())
			+"', '"+FormatData.FormatInputData(user.getDienThoai())
			+"', '"+FormatData.FormatInputData(user.getEmail())
			+"', N'TK mới', GETDATE(), N'Người dùng', '"+user.getLocation()+"')";
		return db.updateData(sql);
	}

	public boolean khoaNguoiDung(String idTaiKhoan, String tinhTrang) {
		return db.updateData("update TAIKHOAN set TinhTrang=N'"+tinhTrang+"' where TaiKhoan like '"+idTaiKhoan+"'");
	}

	public List<NhaCungCap> getDanhSachNCC(String txtFind, int page) throws SQLException {
		txtFind = FormatData.FormatInputData(txtFind);
		String sql = "select IdNhaCungCap, GioiThieuChuyenMon, ChungChi, FileKem, DanhGia, TAIKHOAN.TaiKhoan, "
			+"MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, NgayThamGia, LoaiTaiKhoan, "
			+"ROW_NUMBER() OVER (ORDER BY TAIKHOAN.TaiKhoan desc) AS Row from NHACUNGCAP "
			+"inner join TAIKHOAN on NHACUNGCAP.TaiKhoan=TAIKHOAN.TaiKhoan "
			+"where LoaiTaiKhoan like N'Nhà cung cấp' and "
			+"(TAIKHOAN.TaiKhoan like '"+txtFind+"' or HoTen like N'%"+txtFind+"%' or DiaChi like N'%"+txtFind
			+"%' or DienThoai like '"+txtFind+"%' or Email like '"+txtFind+"%')";
		ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
		db.setRecord(10); 
		db.createMenu("thanh-vien-nhacungcap.action?txtFind="+txtFind, page, sql);
		ResultSet rs = db.getResultSet("select * from ( "+sql+" ) as tam where Row between "
				+((db.getPage()-1)*db.getRecord()+1)+" and "+(db.getPage()*db.getRecord()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			NhaCungCap ncc = new NhaCungCap();
			ncc.setIdNhaCungCap(rs.getInt("IdNhaCungCap"));
			ncc.setGioiThieu(FormatData.FormatOutputData(rs.getString("GioiThieuChuyenMon")));
			ncc.setChungChi(FormatData.FormatOutputData(rs.getString("ChungChi")));
			ncc.setFileKem(FormatData.FormatOutputData(rs.getString("FileKem")));
			ncc.setDanhGia(rs.getInt("DanhGia"));
			TaiKhoan taiKhoan = new TaiKhoan();
			taiKhoan.setIdTaiKhoan(rs.getString("TaiKhoan"));
			taiKhoan.setMatKhau(rs.getString("MatKhau"));
			taiKhoan.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
			taiKhoan.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
			taiKhoan.setDienThoai(rs.getString("DienThoai"));
			taiKhoan.setEmail(rs.getString("Email"));
			taiKhoan.setTinhTrang(rs.getString("TinhTrang"));
			taiKhoan.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
			ncc.setTaiKhoan(taiKhoan);
			list.add(ncc);
		}
		return list;
	}

	public NhaCungCap getNhaCungCap(String idNhaCungCap) throws SQLException {
		String sql = "select top 1 IdNhaCungCap, GioiThieuChuyenMon, ChungChi, FileKem, DanhGia, TAIKHOAN.TaiKhoan, "
				+"MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, NgayThamGia, LoaiTaiKhoan from NHACUNGCAP "
				+"inner join TAIKHOAN on NHACUNGCAP.TaiKhoan=TAIKHOAN.TaiKhoan "
				+"where LoaiTaiKhoan like N'Nhà cung cấp' and IdNhaCungCap='"+idNhaCungCap+"'";
		ResultSet rs = db.getResultSet(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if(rs.next()){
			NhaCungCap ncc = new NhaCungCap();
			ncc.setIdNhaCungCap(rs.getInt("IdNhaCungCap"));
			ncc.setGioiThieu(FormatData.FormatOutputData(rs.getString("GioiThieuChuyenMon")));
			ncc.setChungChi(FormatData.FormatOutputData(rs.getString("ChungChi")));
			ncc.setFileKem(FormatData.FormatOutputData(rs.getString("FileKem")));
			ncc.setDanhGia(rs.getInt("DanhGia"));
			TaiKhoan taiKhoan = new TaiKhoan();
			taiKhoan.setIdTaiKhoan(rs.getString("TaiKhoan"));
			taiKhoan.setMatKhau(rs.getString("MatKhau"));
			taiKhoan.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
			taiKhoan.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
			taiKhoan.setDienThoai(rs.getString("DienThoai"));
			taiKhoan.setEmail(rs.getString("Email"));
			taiKhoan.setTinhTrang(rs.getString("TinhTrang"));
			taiKhoan.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
			ncc.setTaiKhoan(taiKhoan);
			return ncc;
		}
		return null;
	}

	public boolean ThemNhaCungCap(String taiKhoan, String hoTen, String diaChi, String location, String dienThoai, String email,
			String gioiThieu, String chungChi, String loaiNCC, String hinhAnh) {
		String sql = "{call themNCC(?,?,?,?,?,?,?,?,?,?,?,?) }";
		try {
			//Tạo đối tượng CallableStatement
			CallableStatement cstm = db.getCallableStatement(sql);
			//Cập nhật tham số đầu vào
			cstm.setString(1, taiKhoan);
			cstm.setString(2, FormatData.FormatMD5(taiKhoan));
			cstm.setString(3, hoTen);
			cstm.setString(4, diaChi);
			cstm.setString(5, location);
			cstm.setString(6, dienThoai);
			cstm.setString(7, email);
			cstm.setString(8, gioiThieu);
			cstm.setString(9, chungChi);
			cstm.setString(10, loaiNCC);
			cstm.setString(11, hinhAnh);
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

	public boolean ThemNhaCungCap(String taiKhoan, String hoTen, String diaChi, String location, String dienThoai, String email,
			String gioiThieu, String chungChi, String loaiNCC, String hinhAnh, String thoiGian, int danhMuc, String dienThoaiLH,
			String emailLH, String nickYahoo, String nickSkype) {
		String sql = "{call themNCCHT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
		try {
			//Tạo đối tượng CallableStatement
			CallableStatement cstm = db.getCallableStatement(sql);
			//Cập nhật tham số đầu vào
			cstm.setString(1, taiKhoan);
			cstm.setString(2, FormatData.FormatMD5(taiKhoan));
			cstm.setString(3, hoTen);
			cstm.setString(4, diaChi);
			cstm.setString(5, location);
			cstm.setString(6, dienThoai);
			cstm.setString(7, email);
			cstm.setString(8, gioiThieu);
			cstm.setString(9, chungChi);
			cstm.setString(10, loaiNCC);
			cstm.setString(11, hinhAnh);
			cstm.setString(12, dienThoaiLH);
			cstm.setString(13, emailLH);
			cstm.setString(14, thoiGian);
			cstm.setInt(15, danhMuc);
			cstm.setString(16, nickYahoo);
			cstm.setString(17, nickSkype);
			//Cập nhật tham số đầu ra
			cstm.registerOutParameter(18, java.sql.Types.INTEGER);
			//Thực thi
			cstm.executeUpdate();
			//Lấy kết quả trả về
			int result = cstm.getInt(18);
			if(result > -1) {
				return true;
			}
		} catch (SQLException e) { 
			return false;
		}
		return false;
	}

	public boolean CapNhatNhaCungCap(String id, String taiKhoan, String hoTen, String diaChi, String dienThoai, String email,
			String gioiThieu, String chungChi, String hinhAnh) {
		String sql = "{call capNhatNCC(?,?,?,?,?,?,?,?,?,?) }";
		try {
			//Tạo đối tượng CallableStatement
			CallableStatement cstm = db.getCallableStatement(sql);
			//Cập nhật tham số đầu vào
			cstm.setString(1, id);
			cstm.setString(2, taiKhoan);
			cstm.setString(3, hoTen);
			cstm.setString(4, diaChi);
			cstm.setString(5, dienThoai);
			cstm.setString(6, email);
			cstm.setString(7, gioiThieu);
			cstm.setString(8, chungChi);
			cstm.setString(9, hinhAnh);
			//Cập nhật tham số đầu ra
			cstm.registerOutParameter(10, java.sql.Types.INTEGER);
			//Thực thi
			cstm.executeUpdate();
			//Lấy kết quả trả về
			int result = cstm.getInt(10);
			if(result > -1) {
				return true;
			}
		} catch (SQLException e) { 
			return false;
		}
		return false;
	}

	public boolean CapNhatNhaCungCap(String id, String taiKhoan, String hoTen, String diaChi, String dienThoai, String email,
			String gioiThieu, String chungChi, String hinhAnh, String thoiGian, int danhMuc, String dienThoaiLH,
			String emailLH, String nickYahoo, String nickSkype) {
		String sql = "{call capNhatNCCHT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
		try {
			//Tạo đối tượng CallableStatement
			CallableStatement cstm = db.getCallableStatement(sql);
			//Cập nhật tham số đầu vào
			cstm.setString(1, id);
			cstm.setString(2, taiKhoan);
			cstm.setString(3, hoTen);
			cstm.setString(4, diaChi);
			cstm.setString(5, dienThoai);
			cstm.setString(6, email);
			cstm.setString(7, gioiThieu);
			cstm.setString(8, chungChi);
			cstm.setString(9, hinhAnh);
			cstm.setString(10, dienThoaiLH);
			cstm.setString(11, emailLH);
			cstm.setString(12, thoiGian);
			cstm.setInt(13, danhMuc);
			cstm.setString(14, nickYahoo);
			cstm.setString(15, nickSkype);
			//Cập nhật tham số đầu ra
			cstm.registerOutParameter(16, java.sql.Types.INTEGER);
			//Thực thi
			cstm.executeUpdate();
			//Lấy kết quả trả về
			int result = cstm.getInt(16);
			if(result > -1) {
				return true;
			}
		} catch (SQLException e) { 
			return false;
		}
		return false;
	}

	public boolean XoaNhaCungCap(String idNhaCungCap) {
		return db.updateData("delete from NHACUNGCAP where IdNhaCungCap='"+idNhaCungCap+"'");
	}

	public boolean ThemTaiKhoan(String idTaiKhoan, String matKhau, String hoTen, String diaChi, 
			String dienThoai, String email,String loaiTaiKhoan, String location) {
		String sql = "";
		idTaiKhoan = FormatData.FormatInputData(idTaiKhoan);
		matKhau = FormatData.FormatMD5(matKhau);
		hoTen = FormatData.FormatInputData(hoTen);
		diaChi = FormatData.FormatInputData(diaChi);
		dienThoai = FormatData.FormatInputData(dienThoai);
		email = FormatData.FormatInputData(email);
		if("admin".equals(loaiTaiKhoan)){
			sql = "insert into TAIKHOAN values ('"+idTaiKhoan+"', '"+matKhau
					+"', N'"+hoTen+"', N'"+diaChi+"', '"+dienThoai+"', '"+email+"', N'TK mới', GETDATE(), N'admin', '"+location+"')";
		} else {
			sql = "insert into TAIKHOAN values ('"+idTaiKhoan+"', '"+matKhau
					+"', N'"+hoTen+"', N'"+diaChi+"', '"+dienThoai+"', '"+email+"', N'TK mới', GETDATE(), N'"+loaiTaiKhoan+"', '"+location+"')";
		}
		return db.updateData(sql);
	}

	public boolean XoaQuanTri(String idTaiKhoan) {
		return db.updateData("delete from TAIKHOAN where TaiKhoan='"+idTaiKhoan+"'");
	}

	public TaiKhoan getTaiKhoan(String idTaiKhoan) {
		String sql = "select TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, "
				+ "DienThoai, Email, TinhTrang, NgayThamGia, LoaiTaiKhoan from TAIKHOAN "
				+ "where TAIKHOAN.TaiKhoan like '"+idTaiKhoan+"'";
		ResultSet rs = db.getResultSet(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if(rs.next()){
				TaiKhoan user = new TaiKhoan();
				user.setIdTaiKhoan(rs.getString("TaiKhoan"));
				user.setMatKhau(rs.getString("MatKhau"));
				user.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
				user.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
				user.setDienThoai(rs.getString("DienThoai"));
				user.setEmail(rs.getString("Email"));
				user.setTinhTrang(rs.getString("TinhTrang"));
				user.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
				user.setLoaiTaiKhoan(rs.getString("LoaiTaiKhoan"));
				return user;
			}
		} catch (SQLException e) {
			return null;
		}
		return null;
	}

	public boolean CapNhatQuanTri(String idTaiKhoan, String hoTen, String diaChi, String dienThoai, String email) {
		return db.updateData("update TAIKHOAN set HoTen=N'"+hoTen+"', DiaChi=N'"+diaChi
				+"', DienThoai='"+dienThoai+"', Email='"+email+"' where TaiKhoan='"+idTaiKhoan+"'");
	}

	public List<NhaCungCap> getNCCMoi() throws SQLException {
		String sql = "select IdNhaCungCap, GioiThieuChuyenMon, ChungChi, FileKem, DanhGia, TAIKHOAN.TaiKhoan, "
				+"MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, NgayThamGia, LoaiTaiKhoan, "
				+"ROW_NUMBER() OVER (ORDER BY TAIKHOAN.TaiKhoan desc) AS Row from NHACUNGCAP "
				+"inner join TAIKHOAN on NHACUNGCAP.TaiKhoan=TAIKHOAN.TaiKhoan "
				+"where LoaiTaiKhoan like N'Nhà cung cấp' and TinhTrang like N'TK mới'";
			ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
			ResultSet rs = db.getResultSet(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			while(rs.next()){
				NhaCungCap ncc = new NhaCungCap();
				ncc.setIdNhaCungCap(rs.getInt("IdNhaCungCap"));
				ncc.setGioiThieu(FormatData.FormatOutputData(rs.getString("GioiThieuChuyenMon")));
				ncc.setChungChi(FormatData.FormatOutputData(rs.getString("ChungChi")));
				ncc.setFileKem(FormatData.FormatOutputData(rs.getString("FileKem")));
				ncc.setDanhGia(rs.getInt("DanhGia"));
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setIdTaiKhoan(rs.getString("TaiKhoan"));
				taiKhoan.setMatKhau(rs.getString("MatKhau"));
				taiKhoan.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
				taiKhoan.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
				taiKhoan.setDienThoai(rs.getString("DienThoai"));
				taiKhoan.setEmail(rs.getString("Email"));
				taiKhoan.setTinhTrang(rs.getString("TinhTrang"));
				taiKhoan.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
				ncc.setTaiKhoan(taiKhoan);
				list.add(ncc);
			}
		return list;
	}

	public boolean setDanhGia(String idNhaCungCap, int danhGia) {
		return db.updateData("update NHACUNGCAP set DanhGia=Round((DanhGia+'"+danhGia+"')/2+0.5, 0) where IdNhaCungCap='"+idNhaCungCap+"';");
	}
	
	public boolean CapNhatTaiKhoan(String idTaiKhoan, String hoTen, String diaChi, String dienThoai, String email,
			String matKhau) {
		hoTen = FormatData.FormatInputData(hoTen);
		diaChi = FormatData.FormatInputData(diaChi);
		email = FormatData.FormatInputData(email);
		dienThoai = FormatData.FormatInputData(dienThoai);
		matKhau = FormatData.FormatMD5(matKhau);
		String sql = "update TAIKHOAN set HoTen=N'"+hoTen+"', DiaChi=N'"+diaChi+"', DienThoai='"+dienThoai
				+"', Email='"+email+"', MatKhau='"+matKhau+"' where TaiKhoan='"+idTaiKhoan+"'";
		return db.updateData(sql);
	}
	
	public boolean CapNhatTrangThai(String idKey, String chanState) {
		return db.updateData("update TAIKHOAN set TinhTrang=N'"+chanState+"' where TaiKhoan='"+idKey+"'");
	}

	public List<NhaCungCap> getListNhaCungCap() throws SQLException {
		String sql = "select IdNhaCungCap, GioiThieuChuyenMon, ChungChi, FileKem, DanhGia, TAIKHOAN.TaiKhoan, "
				+"MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, NgayThamGia, LoaiTaiKhoan, "
				+"ROW_NUMBER() OVER (ORDER BY TAIKHOAN.TaiKhoan desc) AS Row from NHACUNGCAP "
				+"inner join TAIKHOAN on NHACUNGCAP.TaiKhoan=TAIKHOAN.TaiKhoan "
				+"where ( LoaiTaiKhoan like N'Nhà cung cấp' or LoaiTaiKhoan like N'admin' ) and TinhTrang like N'TK mới'";
			ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
			ResultSet rs = db.getResultSet(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			while(rs.next()){
				NhaCungCap ncc = new NhaCungCap();
				ncc.setIdNhaCungCap(rs.getInt("IdNhaCungCap"));
				ncc.setGioiThieu(FormatData.FormatOutputData(rs.getString("GioiThieuChuyenMon")));
				ncc.setChungChi(FormatData.FormatOutputData(rs.getString("ChungChi")));
				ncc.setFileKem(FormatData.FormatOutputData(rs.getString("FileKem")));
				ncc.setDanhGia(rs.getInt("DanhGia"));
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setIdTaiKhoan(rs.getString("TaiKhoan"));
				taiKhoan.setMatKhau(rs.getString("MatKhau"));
				taiKhoan.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
				taiKhoan.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
				taiKhoan.setDienThoai(rs.getString("DienThoai"));
				taiKhoan.setEmail(rs.getString("Email"));
				taiKhoan.setTinhTrang(rs.getString("TinhTrang"));
				taiKhoan.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
				ncc.setTaiKhoan(taiKhoan);
				list.add(ncc);
			}
		return list;
	}

	public List<NhaCungCap> getListNCC() throws SQLException {
		String sql = "select IdNhaCungCap, GioiThieuChuyenMon, ChungChi, FileKem, DanhGia, TAIKHOAN.TaiKhoan, "
				+ "MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, NgayThamGia, LoaiTaiKhoan, "
				+ "ROW_NUMBER() OVER (ORDER BY TAIKHOAN.TaiKhoan desc) AS Row from NHACUNGCAP "
				+ "inner join TAIKHOAN on NHACUNGCAP.TaiKhoan=TAIKHOAN.TaiKhoan "
				+ "where ( LoaiTaiKhoan like N'Nhà cung cấp' or LoaiTaiKhoan like N'admin' ) "
				+ "and TinhTrang not like N'TK mới' and TinhTrang not like N'Khóa tài khoản'";
			ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
			ResultSet rs = db.getResultSet(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			while(rs.next()){
				NhaCungCap ncc = new NhaCungCap();
				ncc.setIdNhaCungCap(rs.getInt("IdNhaCungCap"));
				ncc.setGioiThieu(FormatData.FormatOutputData(rs.getString("GioiThieuChuyenMon")));
				ncc.setChungChi(FormatData.FormatOutputData(rs.getString("ChungChi")));
				ncc.setFileKem(FormatData.FormatOutputData(rs.getString("FileKem")));
				ncc.setDanhGia(rs.getInt("DanhGia"));
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setIdTaiKhoan(rs.getString("TaiKhoan"));
				taiKhoan.setMatKhau(rs.getString("MatKhau"));
				taiKhoan.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
				taiKhoan.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
				taiKhoan.setDienThoai(rs.getString("DienThoai"));
				taiKhoan.setEmail(rs.getString("Email"));
				taiKhoan.setTinhTrang(rs.getString("TinhTrang"));
				taiKhoan.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
				ncc.setTaiKhoan(taiKhoan);
				list.add(ncc);
			}
		return list;
	}
 
}
