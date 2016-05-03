package model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.DichVu;
import model.dao.DichVuDAO;

public class DichVuBO {
	DichVuDAO dichVuDAO = new DichVuDAO();
	
	public void closeConnect() {
		dichVuDAO.closeConnect();
	}

	public ArrayList<DichVu> getDanhSachDanhMuc(String txtFind, int page) {
		try {
			return dichVuDAO.getDanhSachDanhMuc(txtFind, page);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public String getMemu() {
		return dichVuDAO.getMemu();
	}

	public String getNameNewFile() {
		return "file";
	}

	public boolean themDichVu(String tenDichVu, String moTa, int danhMuc, String noiDung, String hinhAnh,
			String taiKhoan, String nhaCungCap, String dienThoai, String email, String ngayBatDau, 
			String ngayKetThuc, String loaiHinh, String diaDiem) {
		return dichVuDAO.themDichVu(tenDichVu, moTa, danhMuc, noiDung, hinhAnh, taiKhoan, nhaCungCap, 
				dienThoai, email, ngayBatDau, ngayKetThuc, loaiHinh, diaDiem);
	}

	public boolean capNhatDichVu(String idDichVu, String tenDichVu, String moTa, String danhMuc, String noiDung,
			String hinhAnh, String nhaCungCap, String dienThoai, String email, String ngayBatDau, String ngayKetThuc) {
		return dichVuDAO.capNhatDichVu(idDichVu, tenDichVu, moTa, danhMuc, noiDung, hinhAnh, nhaCungCap, dienThoai, email, ngayBatDau, ngayKetThuc);
	}

	public DichVu getDichVu(String id, int quyen) {
		try {
			return dichVuDAO.getDichVu(id, quyen);
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean xoaDichVu(String id) {
		return dichVuDAO.xoaDichVu(id);
	}

	public boolean capNhatTinhTrang(String dichVu, String tinhTrang) {
		return dichVuDAO.capNhatTinhTrang(dichVu, tinhTrang);
	}

	public boolean clientThemDichVu(String tenDichVu, String moTa, int danhMuc, String noiDung, String hinhAnh,
			String taiKhoan, String nhaCungCap, String dienThoai, String email, String ngayBatDau, String ngayKetThuc, String loaiHinh, String diaDiem) {
		return dichVuDAO.clientThemDichVu(tenDichVu, moTa, danhMuc, noiDung, hinhAnh, taiKhoan, nhaCungCap, 
				dienThoai, email, ngayBatDau, ngayKetThuc, loaiHinh, diaDiem);
	}

	public boolean dangKyDichVu(String idDichVu, String hoTen, String email, String dienThoai, String tinNhan) {
		return dichVuDAO.dangKyDichVu(idDichVu, hoTen, email, dienThoai, tinNhan);
	}

	public List<DichVu> getDichVuHot() {
		try {
			return dichVuDAO.getDichVuHot();
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getListDichVu(String txtFind, String idDanhMuc, String viTri) {
		try {
			return dichVuDAO.getListDichVu(txtFind, idDanhMuc, viTri);
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getNhuCauMoi() {
		try {
			return dichVuDAO.getNhuCauMoi();
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getListNhuCau(String txtFind, String idDanhMuc, String viTri) {
		try {
			return dichVuDAO.getListNhuCau(txtFind, idDanhMuc, viTri);
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getListDichVuMoi() {
		try {
			return dichVuDAO.getListDichVuMoi();
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getListDichVu(String theLoai) {
		try {
			return dichVuDAO.getListDichVu(theLoai);
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public boolean dangKyNhuCau(String idNhuCau, String idTaiKhoan, String tinNhan) {
		return dichVuDAO.dangKyNhuCau(idNhuCau, idTaiKhoan, tinNhan);
	}

	public List<DichVu> getListDichVu(String txtFind, int page) {
		try {
			return dichVuDAO.getListDichVu(txtFind, page);
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getListNhuCau(String txtFind, int page) {
		try {
			return dichVuDAO.getListNhuCau(txtFind, page);
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getDichVuDangKy() {
		try {
			return dichVuDAO.getDichVuDangKy();
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getNhuCauDangKy() {
		try {
			return dichVuDAO.getNhuCauDangKy();
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getDichVuDangKy(String idTaiKhoan) {
		try {
			return dichVuDAO.getDichVuDangKy(idTaiKhoan);
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}

	public List<DichVu> getNhuCauDang(String idTaiKhoan) {
		try {
			return dichVuDAO.getNhuCauDang(idTaiKhoan);
		} catch (SQLException e) {
			System.out.println("Loi 1 : " + e.toString());
			return null;
		} catch (Exception e) {
			System.out.println("Loi 2 : " + e.toString());
			return null;
		}
	}
	
}
