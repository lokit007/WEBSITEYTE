package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.bean.BinhLuan;
import model.bean.TaiKhoan;

public class BinhLuanDAO {
	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public boolean themBinhLuan(String taiKhoan, String id, String noiDung, String table) {
		String sql = "";
		if("BINHLUAN".equals(table)) sql = "insert into BINHLUAN(IdBaiViet, NoiDungBinhLuan, NgayBinhLuan, TaiKhoan) values ('"+id+"', N'"+noiDung+"', GETDATE(), '"+taiKhoan+"')";
		else sql = "insert into TRALOI(IdBinhLuan, NoiDungTraLoi, NgayTraLoi, TaiKhoan) values ('"+id+"', N'"+noiDung+"', GETDATE(), '"+taiKhoan+"')";
		return db.updateData(sql);
	}

	public BinhLuan getBinhLuan(String table, String id, String noiDung) throws SQLException {
		String sql = "";
		noiDung = FormatData.FormatInputData(noiDung);
		if("BINHLUAN".equals(table)){
			sql = "select IdBinhLuan, IdBaiViet, NoiDungBinhLuan, NgayBinhLuan, "
					+"TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, "
					+"TinhTrang, NgayThamGia, LoaiTaiKhoan from BINHLUAN "
					+"inner join TAIKHOAN on BINHLUAN.TaiKhoan=TAIKHOAN.TaiKhoan "
					+"where IdBaiViet like '"+id+"' and NoiDungBinhLuan like N'"+noiDung+"'";
		} else {
			sql = "select IdTraLoi, IdBinhLuan, NoiDungTraLoi, NgayTraLoi, "
					+ "TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, "
					+ "TinhTrang, NgayThamGia, LoaiTaiKhoan from TRALOI "
					+ "inner join TAIKHOAN on TRALOI.TaiKhoan=TAIKHOAN.TaiKhoan "
					+ "where IdBinhLuan like '"+id+"' and NoiDungTraLoi like N'"+noiDung+"'";
		}
		ResultSet rs = db.getResultSet(sql);
		BinhLuan binhLuan = new BinhLuan();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm"); 
		if(rs.next()){
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
		}
		return binhLuan;
	}

	public ArrayList<BinhLuan> getListBinhLuan(String id) throws SQLException{
		String sql = "select IdBinhLuan, IdBaiViet, NoiDungBinhLuan, NgayBinhLuan, "
					+"TAIKHOAN.TaiKhoan, MatKhau, HoTen, DiaChi, DienThoai, Email, "
					+"TinhTrang, NgayThamGia, LoaiTaiKhoan from BINHLUAN "
					+"inner join TAIKHOAN on BINHLUAN.TaiKhoan=TAIKHOAN.TaiKhoan "
					+"where IdBaiViet like '"+id+"' order by NgayBinhLuan desc";
		ResultSet rs = db.getResultSet(sql);
		System.out.println("SQl : " + sql);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm"); 
		ArrayList<BinhLuan> list = new ArrayList<BinhLuan>();
		TraLoiDAO traLoiDAO = new TraLoiDAO();
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
			binhLuan.setListTraLoi(traLoiDAO.getListTraLoi(binhLuan.getId()+""));
			list.add(binhLuan);
		}
		traLoiDAO.closeConnect();
		return list;
	}

	public boolean XoaBinhLuan(String id, String nameTable) {
		String sql = "";
		if(nameTable.equals("CAUHOI"))
			sql = "delete from BINHLUAN where IdBinhLuan='"+id+"'";
		else
			sql = "delete from TRALOI where IdTraLoi='"+id+"'";
		return db.updateData(sql);
	}
}
