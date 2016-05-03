package model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.NhaCungCap;
import model.bean.TaiKhoan;
import model.dao.TaiKhoanDAO;

public class TaiKhoanBO {

	TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	
	public void closeConnect() {
		taiKhoanDAO.closeConnect();
	}

	public String getMemu() {
		return taiKhoanDAO.getMemu();
	}

	public ArrayList<TaiKhoan> getDanhSachTaiKhoan(String listSelect, String txtFind, int page) {
		try {
			return taiKhoanDAO.getDanhSachTaiKhoan(listSelect, txtFind, page);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean khoaNguoiDung(String idTaiKhoan, String tinhTrang) {
		return taiKhoanDAO.khoaNguoiDung(idTaiKhoan, tinhTrang);
	}

	public List<NhaCungCap> getDanhSachNCC(String txtFind, int page) {
		try {
			return taiKhoanDAO.getDanhSachNCC(txtFind, page);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public NhaCungCap getNhaCungCap(String idNhaCungCap) {
		try {
			return taiKhoanDAO.getNhaCungCap(idNhaCungCap);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean ThemNhaCungCap(String taiKhoan, String hoTen, String diaChi, String dienThoai, String email,
			String gioiThieu, String chungChi, String hinhAnh) {
		return taiKhoanDAO.ThemNhaCungCap(taiKhoan, hoTen, diaChi, dienThoai, email, gioiThieu, chungChi, hinhAnh);
	}

	public boolean ThemNhaCungCap(String taiKhoan, String hoTen, String diaChi, String dienThoai, String email,
			String gioiThieu, String chungChi, String hinhAnh, String thoiGian, int danhMuc, String dienThoaiLH,
			String emailLH, String nickYahoo, String nickSkype) {
		return taiKhoanDAO.ThemNhaCungCap(taiKhoan, hoTen, diaChi, dienThoai, email, gioiThieu, chungChi,
				hinhAnh, thoiGian, danhMuc, dienThoaiLH, emailLH, nickYahoo, nickSkype);
	}

	public boolean CapNhatNhaCungCap(String id, String taiKhoan, String hoTen, String diaChi, String dienThoai, String email,
			String gioiThieu, String chungChi, String hinhAnh, String thoiGian, int danhMuc, String dienThoaiLH,
			String emailLH, String nickYahoo, String nickSkype) {
		return taiKhoanDAO.CapNhatNhaCungCap(id, taiKhoan, hoTen, diaChi, dienThoai, email, gioiThieu, chungChi,
				hinhAnh, thoiGian, danhMuc, dienThoaiLH, emailLH, nickYahoo, nickSkype);
	}

	public boolean CapNhatNhaCungCap(String id, String taiKhoan, String hoTen, String diaChi, String dienThoai, String email,
			String gioiThieu, String chungChi, String hinhAnh) {
		return taiKhoanDAO.CapNhatNhaCungCap(id, taiKhoan, hoTen, diaChi, dienThoai, email, gioiThieu, chungChi, hinhAnh);
	}

	public boolean XoaNhaCungCap(String idNhaCungCap) {
		return taiKhoanDAO.XoaNhaCungCap(idNhaCungCap);
	}

	public boolean ThemTaiKhoan(String idTaiKhoan, String matKhau, String hoTen, String diaChi, String dienThoai, String email, String loaiTaiKhoan) {
		return taiKhoanDAO.ThemTaiKhoan(idTaiKhoan, matKhau, hoTen, diaChi, dienThoai, email,loaiTaiKhoan);
	}

	public boolean XoaQuanTri(String idTaiKhoan) {
		return taiKhoanDAO.XoaQuanTri(idTaiKhoan);
	}

	public TaiKhoan getTaiKhoan(String idTaiKhoan) {
		return taiKhoanDAO.getTaiKhoan(idTaiKhoan);
	}

	public boolean CapNhatQuanTri(String idTaiKhoan, String hoTen, String diaChi, String dienThoai, String email) {
		return taiKhoanDAO.CapNhatQuanTri(idTaiKhoan, hoTen, diaChi, dienThoai, email);
	}

	public List<NhaCungCap> getNCCMoi() {
		try {
			return taiKhoanDAO.getNCCMoi();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean setDanhGia(String idNhaCungCap, int danhGia) {
		return taiKhoanDAO.setDanhGia(idNhaCungCap, danhGia);
	}

	public boolean CapNhatTaiKhoan(String idTaiKhoan, String hoTen, String diaChi, String dienThoai, String email,
			String matKhau) {
		return taiKhoanDAO.CapNhatTaiKhoan(idTaiKhoan, hoTen, diaChi, dienThoai, email, matKhau);
	}

	public boolean CapNhatTrangThai(String idKey, String chanState) {
		return taiKhoanDAO.CapNhatTrangThai(idKey, chanState);
	}

	public List<NhaCungCap> getListNhaCungCap() {
		try {
			return taiKhoanDAO.getListNhaCungCap();
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
