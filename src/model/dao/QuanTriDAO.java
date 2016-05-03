package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.bean.QuanTri;
import model.bean.TaiKhoan;

public class QuanTriDAO {

	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public List<QuanTri> getDanhSachQuanTri() throws SQLException {
		String sql = "select QUANTRI.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, "
				+ "NgayThamGia, LoaiTaiKhoan, Location, DanhMuc, DichVu, NhuCau, ChiaSe, NhaCungCap, "
				+ "TaiNguyen, ThongKe from QUANTRI inner join TAIKHOAN on QUANTRI.TaiKhoan=TAIKHOAN.TaiKhoan";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<QuanTri> list = new ArrayList<QuanTri>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(rs.next()){
			QuanTri quanTri = new QuanTri();
			TaiKhoan taiKhoan = new TaiKhoan();
			taiKhoan.setIdTaiKhoan(rs.getString("TaiKhoan"));
			taiKhoan.setMatKhau(rs.getString("MatKhau"));
			taiKhoan.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
			taiKhoan.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
			taiKhoan.setDienThoai(rs.getString("DienThoai"));
			taiKhoan.setEmail(rs.getString("Email"));
			taiKhoan.setTinhTrang(rs.getString("TinhTrang"));
			taiKhoan.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
			quanTri.setTaiKhoan(taiKhoan);
			quanTri.setDanhMuc(rs.getBoolean("DanhMuc"));
			quanTri.setDichVu(rs.getBoolean("DichVu"));
			quanTri.setNhuCau(rs.getBoolean("NhuCau"));
			quanTri.setChiaSe(rs.getBoolean("ChiaSe"));
			quanTri.setNhaCungCap(rs.getBoolean("NhaCungCap"));
			quanTri.setTaiNguyen(rs.getBoolean("TaiNguyen"));
			quanTri.setThongKe(rs.getBoolean("ThongKe"));
			list.add(quanTri);
		}
		return list;
	}

	public List<TaiKhoan> getTaiKhoanThem() throws SQLException {
		String sql = "select TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, "
				+ "NgayThamGia, LoaiTaiKhoan, Location from TAIKHOAN "
				+ "left join QUANTRI on QUANTRI.TaiKhoan=TAIKHOAN.TaiKhoan "
				+ "where QUANTRI.TaiKhoan is null and LoaiTaiKhoan not like N'Vãng lai' "
				+ "and TinhTrang like N'TK mới'";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
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

	public boolean CapQuyenQuanTri(String taiKhoan, boolean danhMuc, boolean dichVu, boolean nhuCau, boolean chiaSe,
			boolean nhaCungCap, boolean taiNguyen, boolean thongKe) {
		taiKhoan = FormatData.FormatInputData(taiKhoan);
		String sql = "insert into QUANTRI values ('"+taiKhoan+"', '"+danhMuc+"', '"+dichVu+"', '"+nhuCau
				+"', '"+chiaSe+"', '"+nhaCungCap+"', '"+taiNguyen+"', '"+thongKe+"')";
		return db.updateData(sql);
	}

	public boolean HuyQuyenQuanTri(String taiKhoan) {
		taiKhoan = FormatData.FormatInputData(taiKhoan);
		String sql = "delete from QUANTRI where TaiKhoan like '"+taiKhoan+"'";
		return db.updateData(sql);
	}

	public boolean CapNhatQuyenHan(String taiKhoan, String nameCol, boolean valCheck) {
		taiKhoan = FormatData.FormatInputData(taiKhoan);
		String sql = "update QUANTRI set "+nameCol+"='"+valCheck+"' where TaiKhoan like '"+taiKhoan+"'";
		return db.updateData(sql);
	}

	public QuanTri getQuanTri(String taiKhoan, String matKhau) throws SQLException {
		taiKhoan = FormatData.FormatInputData(taiKhoan);
		matKhau = FormatData.FormatInputData(matKhau);
		String sql = "select QUANTRI.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, TinhTrang, "
				+ "NgayThamGia, LoaiTaiKhoan, Location, DanhMuc, DichVu, NhuCau, ChiaSe, NhaCungCap, "
				+ "TaiNguyen, ThongKe from QUANTRI inner join TAIKHOAN on QUANTRI.TaiKhoan=TAIKHOAN.TaiKhoan "
				+ "where TAIKHOAN.TaiKhoan like '"+taiKhoan+"' and MatKhau like '"+matKhau+"' ";
		ResultSet rs = db.getResultSet(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if(rs.next()){
			QuanTri quanTri = new QuanTri();
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
			quanTri.setTaiKhoan(user);
			quanTri.setDanhMuc(rs.getBoolean("DanhMuc"));
			quanTri.setDichVu(rs.getBoolean("DichVu"));
			quanTri.setNhuCau(rs.getBoolean("NhuCau"));
			quanTri.setChiaSe(rs.getBoolean("ChiaSe"));
			quanTri.setNhaCungCap(rs.getBoolean("NhaCungCap"));
			quanTri.setTaiNguyen(rs.getBoolean("TaiNguyen"));
			quanTri.setThongKe(rs.getBoolean("ThongKe"));
			return quanTri;
		}
		return null;
	}

}
