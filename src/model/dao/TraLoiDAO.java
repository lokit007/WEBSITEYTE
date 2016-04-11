package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.bean.BinhLuan;
import model.bean.TaiKhoan;

public class TraLoiDAO {
	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}
	
	public ArrayList<BinhLuan> getListTraLoi(String id) throws SQLException{
		String sql = "select IdTraLoi, IdBinhLuan, NoiDungTraLoi, NgayTraLoi, "
					+ "TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, "
					+ "TinhTrang, NgayThamGia, LoaiTaiKhoan from TRALOI "
					+ "inner join TAIKHOAN on TRALOI.TaiKhoan=TAIKHOAN.TaiKhoan "
					+ "where IdBinhLuan like '"+id+"'";
		ResultSet rs = db.getResultSet(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm"); 
		ArrayList<BinhLuan> list = new ArrayList<BinhLuan>();
		while(rs.next()){
			BinhLuan binhLuan = new BinhLuan();
			binhLuan.setId(rs.getInt(1));
			binhLuan.setKey(rs.getInt(2));
			binhLuan.setNoiDung(FormatData.FormatOutputData(rs.getString(3)));
			binhLuan.setNgayDang(sdf.format(rs.getTimestamp(4)));
			TaiKhoan taiKhoan = new TaiKhoan();
			taiKhoan.setIdTaiKhoan(rs.getString("TaiKhoan"));
			taiKhoan.setMatKhau(rs.getString("MatKhau"));
			taiKhoan.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
			taiKhoan.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
			taiKhoan.setDienThoai(rs.getString("DienThoai"));
			taiKhoan.setEmail(rs.getString("Email"));
			taiKhoan.setTinhTrang(rs.getString("TinhTrang"));
			taiKhoan.setNgayThamGia(sdf.format(rs.getTimestamp("NgayThamGia")));
			binhLuan.setTaiKhoan(taiKhoan);
			list.add(binhLuan);
		}
		return list;
	}
}
