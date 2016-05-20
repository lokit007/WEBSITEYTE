package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.NhaCungCap;
import model.bean.TaiKhoan;

public class BanDoDAO {

	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public List<NhaCungCap> getListNCC(String loai) throws SQLException {
		if("all".equals(loai)) loai = "";
		String sql = "select IdNhaCungCap, HoTen, DiaChi, Location, LoaiNhaCungCap from NHACUNGCAP "
				+ "inner join TAIKHOAN on NHACUNGCAP.TaiKhoan=TAIKHOAN.TaiKhoan "
				+ "where LoaiNhaCungCap like '%"+loai+"%'";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
		while(rs.next()){
			NhaCungCap ncc = new NhaCungCap();
			ncc.setIdNhaCungCap(rs.getInt("IdNhaCungCap"));
			TaiKhoan taiKhoan = new TaiKhoan();
			taiKhoan.setHoTen(FormatData.FormatOutputData(rs.getString("HoTen")));
			taiKhoan.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChi")));
			taiKhoan.setLocation(rs.getString("Location"));
			ncc.setTaiKhoan(taiKhoan);
			ncc.setLoaiNCC(rs.getString("LoaiNhaCungCap"));
			list.add(ncc);
		}
		return list;
	}

}
