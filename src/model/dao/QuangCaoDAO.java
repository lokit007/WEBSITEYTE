package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.bean.KhachHang;
import model.bean.QuangCao;
import model.bean.ViTriQuangCao;

public class QuangCaoDAO {

	private DataBaseDAO db = new DataBaseDAO();
	
	public void closeConnect() {
		db.closeConnection();
	}

	public List<QuangCao> getDanhSachHienThi() throws SQLException {
		String sql = "select IdQuangCao, LogoQuangCao, LinkLienKet, ThoiGianBatDau, ThoiGianKetThuc, "
				+ "ChiPhiQuangCao, TinhTrang, VITRIQUANGCAO.ViTri, VITRIQUANGCAO.GiaDangTai, IdKhachHang, "
				+ "TenKhachHang, DiaChiLienHe, EmailLienHe from QUANGCAO "
				+ "inner join KHACHHANGQUANGCAO on KHACHHANGQUANGCAO.IdKhachHang=QUANGCAO.KhachHang "
				+ "inner join VITRIQUANGCAO on VITRIQUANGCAO.ViTri=QUANGCAO.ViTri "
				+ "where TinhTrang=1";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<QuangCao> list = new ArrayList<QuangCao>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		while(rs.next()){
			QuangCao quangCao = new QuangCao();
			quangCao.setIdQuangCao(rs.getInt("IdQuangCao"));
			quangCao.setLogoQuangBa(FormatData.FormatOutputData(rs.getString("LogoQuangCao")));
			quangCao.setLinkQuangBa(FormatData.FormatOutputData(rs.getString("LinkLienKet")));
			quangCao.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			quangCao.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			quangCao.setChiPhiQuangCao(rs.getFloat("ChiPhiQuangCao"));
			quangCao.setTinhTrang(rs.getInt("TinhTrang"));
			ViTriQuangCao viTri = new ViTriQuangCao(FormatData.FormatOutputData(rs.getString("ViTri")), rs.getFloat("GiaDangTai"));
			quangCao.setViTri(viTri);
			KhachHang khachHang = new KhachHang();
			khachHang.setIdKhachHang(rs.getInt("IdKhachHang"));
			khachHang.setTenKhachHang(FormatData.FormatOutputData(rs.getString("TenKhachHang")));
			khachHang.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChiLienHe")));
			khachHang.setEmail(FormatData.FormatOutputData(rs.getString("EmailLienHe")));
			quangCao.setKhachHang(khachHang);
			list.add(quangCao);
		}
		return list;
	}

	public List<QuangCao> getDanhSachCho(int page) throws SQLException {
		String sql = "select IdQuangCao, LogoQuangCao, LinkLienKet, ThoiGianBatDau, ThoiGianKetThuc, "
				+ "ChiPhiQuangCao, TinhTrang, VITRIQUANGCAO.ViTri, VITRIQUANGCAO.GiaDangTai, IdKhachHang, "
				+ "TenKhachHang, DiaChiLienHe, EmailLienHe from QUANGCAO "
				+ "inner join KHACHHANGQUANGCAO on KHACHHANGQUANGCAO.IdKhachHang=QUANGCAO.KhachHang "
				+ "inner join VITRIQUANGCAO on VITRIQUANGCAO.ViTri=QUANGCAO.ViTri "
				+ "where TinhTrang=2 order by ThoiGianBatDau desc";
		ResultSet rs = db.getResultSet(sql);
		ArrayList<QuangCao> list = new ArrayList<QuangCao>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		while(rs.next()){
			QuangCao quangCao = new QuangCao();
			quangCao.setIdQuangCao(rs.getInt("IdQuangCao"));
			quangCao.setLogoQuangBa(FormatData.FormatOutputData(rs.getString("LogoQuangCao")));
			quangCao.setLinkQuangBa(FormatData.FormatOutputData(rs.getString("LinkLienKet")));
			quangCao.setNgayBatDau(sdf.format(rs.getTimestamp("ThoiGianBatDau")));
			quangCao.setNgayKetThuc(sdf.format(rs.getTimestamp("ThoiGianKetThuc")));
			quangCao.setChiPhiQuangCao(rs.getFloat("ChiPhiQuangCao"));
			quangCao.setTinhTrang(rs.getInt("TinhTrang"));
			ViTriQuangCao viTri = new ViTriQuangCao(FormatData.FormatOutputData(rs.getString("ViTri")), rs.getFloat("GiaDangTai"));
			quangCao.setViTri(viTri);
			KhachHang khachHang = new KhachHang();
			khachHang.setIdKhachHang(rs.getInt("IdKhachHang"));
			khachHang.setTenKhachHang(FormatData.FormatOutputData(rs.getString("TenKhachHang")));
			khachHang.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChiLienHe")));
			khachHang.setEmail(FormatData.FormatOutputData(rs.getString("EmailLienHe")));
			quangCao.setKhachHang(khachHang);
			list.add(quangCao);
		}
		return list;
	}

	public List<KhachHang> getDanhSachKhachHang(int page) throws SQLException {
		String sql = "select IdKhachHang, TenKhachHang, DiaChiLienHe, EmailLienHe, "
				+ "isnull(SUM(ChiPhiQuangCao), 0) as TongChiPhi from KHACHHANGQUANGCAO "
				+ "left join QUANGCAO on QUANGCAO.KhachHang=KHACHHANGQUANGCAO.IdKhachHang "
				+ "group by IdKhachHang, TenKhachHang, DiaChiLienHe, EmailLienHe";
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		ResultSet rs = db.getResultSet(sql);
		while(rs.next()){
			KhachHang khachHang = new KhachHang();
			khachHang.setIdKhachHang(rs.getInt("IdKhachHang"));
			khachHang.setTenKhachHang(FormatData.FormatOutputData(rs.getString("TenKhachHang")));
			khachHang.setDiaChi(FormatData.FormatOutputData(rs.getString("DiaChiLienHe")));
			khachHang.setEmail(FormatData.FormatOutputData(rs.getString("EmailLienHe")));
			khachHang.setChiPhiDauTu(rs.getFloat("TongChiPhi")+"");
			list.add(khachHang);
		}
		return list;
	}

	public boolean XoaQuangCao(int idQuangCao) {
		return db.updateData("delete from QUANGCAO where IdQuangCao='"+idQuangCao+"'");
	}

	public boolean XoaKhachHang(int idKhachHang) {
		return db.updateData("delete from KHACHHANGQUANGCAO where IdKhachHang='"+idKhachHang+"'");
	}

}
