package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.QuangCaoBO;
import model.bo.ValidateBO;

public class QuangCaoActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private String viTri;
	private String logoQuangBa;
	private String linkQuangBa;
	private String hoTen;
	private String diaChi;
	private String email;
	// Nhận data file upload
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private HttpServletRequest servletRequest;
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.servletRequest = req;
	}

	public String execute(){
		return "input";
	}
	
	public String DangKyQuangCao(){
		String result = "thanh-cong";
		this.logoQuangBa = servletRequest.getSession().getServletContext().getRealPath("/").concat("images");
		try {
			File fileToCreate = new File(this.logoQuangBa, this.userImageFileName); //tạo file mới trên server
			FileUtils.copyFile(this.userImage, fileToCreate); //sao chep hinh anh trong file moi
			this.logoQuangBa = this.userImageFileName;
		} catch (IOException e) {
			this.logoQuangBa = "bridge.jpg";
		} catch (Exception e) {
			this.logoQuangBa = "bridge.jpg";
		}
		QuangCaoBO quangCaoBO = new QuangCaoBO();
		if(ValidateBO.CheckEmpty(email)||ValidateBO.CheckEmpty(linkQuangBa)||ValidateBO.CheckEmpty(logoQuangBa)){
			addActionError("Bạn chưa nhập đầy đủ dữ liệu cần thiết!");
			servletRequest.getSession().setAttribute("ThongBao", "Đăng ký quảng cáo thất bại!");
			result = "that-bai";
		} else if(quangCaoBO.DangKyQuangCao(viTri, logoQuangBa, linkQuangBa, hoTen, diaChi, email)){
			servletRequest.getSession().setAttribute("ThongBao", "Bạn đã đăng ký quảng cáo thành công! Vui lòng chờ phản hồi từ ban quản trị. Cám ơn!");
		} else {
			addActionError("Đăng ký quảng cáo thất bại.");
			servletRequest.getSession().setAttribute("ThongBao", "Lỗi!Đăng ký quảng cáo thất bại!");
			result = "that-bai";
		}
		quangCaoBO.closeConnect();
		return result;
	}
	
	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}

	public String getLogoQuangBa() {
		return logoQuangBa;
	}

	public void setLogoQuangBa(String logoQuangBa) {
		this.logoQuangBa = logoQuangBa;
	}

	public String getLinkQuangBa() {
		return linkQuangBa;
	}

	public void setLinkQuangBa(String linkQuangBa) {
		this.linkQuangBa = linkQuangBa;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
