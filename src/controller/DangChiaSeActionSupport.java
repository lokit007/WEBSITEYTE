package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DanhMuc;
import model.bean.TaiKhoan;
import model.bo.ChiaSeBO;
import model.bo.DanhMucBO;
import model.bo.ValidateBO;

public class DangChiaSeActionSupport extends ActionSupport implements ServletRequestAware  {
	private static final long serialVersionUID = 1L;
	private String tenBaiViet;
	private int danhMuc;
	private List<DanhMuc> list = new ArrayList<DanhMuc>();
	private String moTa;
	private String noiDung;
	private String hinhAnh;
	private String tacGia;
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
	
	public String DangChiaSe(){
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
		ChiaSeBO chiaSeBO = new ChiaSeBO();
		String result = "that-bai";
		TaiKhoan user = (TaiKhoan)servletRequest.getSession().getAttribute("user");
		if(ValidateBO.CheckEmpty(tenBaiViet) || ValidateBO.CheckEmpty(moTa)
				 || ValidateBO.CheckEmpty(danhMuc+"") || ValidateBO.CheckEmpty(noiDung)
				 || ValidateBO.CheckEmpty(tacGia)){
			addActionError("Bạn chưa nhập đầy đủ dữ liệu cần thiết!");
			result = "that-bai";
		} else if(chiaSeBO.ThemChiaSe(tenBaiViet, moTa, danhMuc, noiDung, hinhAnh, user.getIdTaiKhoan(), tacGia)){
			servletRequest.getSession().setAttribute("ThongBao", "Đăng dịch vụ thành công!");
			result = "thanh-cong";
		} else {
			addActionError("Đăng chia sẻ không thành công.");
			servletRequest.getSession().setAttribute("ThongBao", "Lỗi!Đăng chia sẻ không thành công!");
		}
		chiaSeBO.closeConnect();
		return result;
	}

	public String getTenBaiViet() {
		return tenBaiViet;
	}

	public void setTenBaiViet(String tenBaiViet) {
		this.tenBaiViet = tenBaiViet;
	}

	public int getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(int danhMuc) {
		this.danhMuc = danhMuc;
	}

	public List<DanhMuc> getList() {
		DanhMucBO danhMucBO = new DanhMucBO();
		this.list = danhMucBO.getListDanhMuc("Chia sẻ");
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

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
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

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	
}
