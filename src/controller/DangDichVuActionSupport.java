package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DanhMuc;
import model.bean.TaiKhoan;
import model.bo.DanhMucBO;
import model.bo.DichVuBO;
import model.bo.EmailUtility;
import model.bo.ValidateBO;

public class DangDichVuActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private String tenDichVu;
	private int danhMuc;
	private List<DanhMuc> list = new ArrayList<DanhMuc>();
	private String moTa;
	private String noiDung;
	private String hinhAnh;
	private String nhaCungCap;
	private String dienThoai;
	private String email;
	private String ngayBatDau;
	private String ngayKetThuc;
	private String loaiHinh;
	private String diaDiem;
	// Nhận data file upload
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private HttpServletRequest servletRequest;
	
	@Override
	public String execute() throws Exception {
		return "input";
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.servletRequest = req;
	}
	
	public String DangDichVu(){
		this.hinhAnh = servletRequest.getSession().getServletContext().getRealPath("/").concat("images");
		try {
			File fileToCreate = new File(this.hinhAnh, this.userImageFileName); //tạo file mới trên server
			FileUtils.copyFile(this.userImage, fileToCreate); //sao chep hinh anh trong file moi
			this.hinhAnh = this.userImageFileName;
		} catch (IOException e) {
			this.hinhAnh = "bridge.jpg";
		} catch (Exception e) {
			this.hinhAnh = "bridge.jpg";
		}
		DichVuBO dichVuBO = new DichVuBO();
		String result = "that-bai";
		TaiKhoan user = (TaiKhoan)servletRequest.getSession().getAttribute("user");
		if(ValidateBO.CheckEmpty(tenDichVu) || ValidateBO.CheckEmpty(moTa)
				 || ValidateBO.CheckEmpty(danhMuc+"") || ValidateBO.CheckEmpty(noiDung)
				 || ValidateBO.CheckEmpty(nhaCungCap) || ValidateBO.CheckEmpty(email)
				 || ValidateBO.CheckEmpty(ngayBatDau) || ValidateBO.CheckEmpty(ngayKetThuc)){
			addActionError("Bạn chưa nhập đầy đủ dữ liệu cần thiết!");
			result = "that-bai";
		} else if(dichVuBO.clientThemDichVu(tenDichVu, moTa, danhMuc, noiDung, hinhAnh, user.getIdTaiKhoan(), 
				nhaCungCap, dienThoai, email, ngayBatDau, ngayKetThuc, loaiHinh, diaDiem)){
			servletRequest.getSession().setAttribute("ThongBao", "Đăng dịch vụ thành công!");
			ServletContext context = ServletActionContext.getRequest().getServletContext();
			final String host = context.getInitParameter("host");
			final String port = context.getInitParameter("port");
			final String userMail = context.getInitParameter("user");
			final String pass = context.getInitParameter("pass");
			try {
				// Gửi nhà cung cấp dịch vụ
				String html = "<p>Chào "+nhaCungCap+",<br>Chúc mừng bạn đã đăng tải dịch vụ y tế "
						+ "<b>thành công</b>.<br>Vui lòng chờ xét duyệt từ hệ thống. "
						+ "Nếu đủ điêu kiện và quy định của hệ thống thì dịch vụ của bạn sẽ được đăng tải "
						+ "và quảng bá trên hệ thống thông tin Dịch vu y tế cộng đồng."
						+ "<br><b>Một lần nữa chúc bạn nhận được nhiều đăng ký dịch vụ từ người dùng.</b><br><br>Thân<br> "
						+ "Công thông tin Dịch vụ y tế - Huế.</p>";
				EmailUtility.sendEmailThread(host, port, userMail, pass, email, "Đăng ký cấp phát dịch vụ thành công", html);
			} catch (Exception ex) {
	        	System.out.println("Lỗi : " + ex.toString());
	        }
			result = "thanh-cong";
		} else {
			addActionError("Đăng dịch vụ không thành công.");
			servletRequest.getSession().setAttribute("ThongBao", "Lỗi! Đăng dịch vụ không thành công!");
		}
		dichVuBO.closeConnect();
		return result;
	}
	
	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public int getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(int danhMuc) {
		this.danhMuc = danhMuc;
	}
	
	public List<DanhMuc> getList() {
		DanhMucBO danhMucBO = new DanhMucBO();
		this.list = danhMucBO.getListDanhMucDichVu();
		danhMucBO.closeConnect();
		return list;
	}

	public void setList(List<DanhMuc> list) {
		this.list = list;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(String ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public String getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(String ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getLoaiHinh() {
		return loaiHinh;
	}

	public void setLoaiHinh(String loaiHinh) {
		this.loaiHinh = loaiHinh;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}
	
}
