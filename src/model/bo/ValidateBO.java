package model.bo;

import model.bean.QuanTri;
import model.bean.TaiKhoan;
import model.dao.DichVuDAO;
import model.dao.NhanMailDAO;
import model.dao.TaiKhoanDAO;

public class ValidateBO {
	//Kiểm tra đối tượng null
	public static boolean CheckNull(Object object){
		if(object==null) return true;
		else return false;
	}
	//Kiểm tra xâu rỗng
	public static boolean CheckEmpty(String object){
		if(object==null) return true;
		else if("".equals(object.trim())) return true;
		else return false;
	}
	//Kiểm tra độ dài
	public static boolean CheckStringLen(String object, int len){
		if(object==null) return false;
		if(len==object.trim().length()) return true;
		else return false;
	}
	//Kiểm tra chuổi min
	public static boolean CheckStringMin(String object, int min){
		if(object==null) return false;
		if(min>object.trim().length()) return false;
		else return true;
	}
	//Kiểm tra chuổi max
	public static boolean CheckStringMax(String object, int max){
		if(object==null) return false;
		if(max<object.trim().length()) return false;
		else return true;
	}
	//Kiểm tra tài khoản tồn tại không
	public static TaiKhoan CheckAccountExist(String taiKhoan, String matKhau) {
		TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
		TaiKhoan user = taiKhoanDAO.getTaiKhoan(taiKhoan, matKhau);
		taiKhoanDAO.closeConnect();
		return user;
	}
	//Kiểm tra tài khoản, mail đã đăng ký chưa
	public static boolean CheckExist(String taiKhoan, String email) {
		TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
		boolean result = !taiKhoanDAO.checkTaiKhoan(taiKhoan)|!taiKhoanDAO.checkMail(email);
		taiKhoanDAO.closeConnect();
		return result;
	}
	//Thêm tài khoản thành công hay không
	public static boolean CheckAccountExist(TaiKhoan user) {
		TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
		boolean result = taiKhoanDAO.themTaiKhoan(user);
		taiKhoanDAO.closeConnect();
		return result;
	}
	public static boolean DangKyMail(String loaiTin, String email) {
		NhanMailDAO nhanMailDAO = new NhanMailDAO();
		boolean result = nhanMailDAO.DangKyNhanMail(loaiTin, email);
		nhanMailDAO.closeConnect();
		return result;
	}
	public static boolean ChekDangKy(String idDichVu, String email) {
		DichVuDAO dichVuDAO = new DichVuDAO();
		boolean result = dichVuDAO.ChekDangKy(idDichVu, email);
		dichVuDAO.closeConnect();
		return result;
	}
	public static QuanTri CheckAdminLogin(String taiKhoan, String matKhau) {
		QuanTriBO quanTriBO = new QuanTriBO();
		QuanTri admin = quanTriBO.getQuanTri(taiKhoan, matKhau);
		quanTriBO.closeConnect();
		return admin;
	}
	
}
