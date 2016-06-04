package controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.QuanTri;
import model.bo.ValidateBO;

/**
 * DangNhapActionSupport.java
 *
 * Version 1.0
 *
 * Date: 10-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 10-04-2016        	NhanHV          Create
 */

public class DangNhapActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String taiKhoan;
	private String matKhau;
	
	/**
	 * Kiểm tra đã đăng nhập quản trị viên chưa?
	 * @param
	 * @return String result
	 */
	
	public static boolean CheckLogin(){
		if(ServletActionContext.getRequest().getSession().getAttribute("admin")!=null)
			return true;
		else
			return false;
	}
	
	/**
	 * Hiển thị trang đăng nhập nếu đã đăng nhập
	 * @param
	 * @return String result
	 */
	
	public String execute(){
		if(this.request.getSession().getAttribute("admin")==null)
			return "input";
		else return "login-ok";
	}

	/**
	 * Thực hiện đăng nhập hệ thống quản trị
	 * @param
	 * @return String result
	 */
	
	public String DangNhapAdmin(){
		if(ValidateBO.CheckEmpty(this.taiKhoan)||ValidateBO.CheckEmpty(this.matKhau)){
			addActionError("Xin vui lòng nhập đầy đủ thông tin!");
			return "that-bai";
		}
		QuanTri admin = ValidateBO.CheckAdminLogin(this.taiKhoan, this.matKhau);
		if(admin==null) {
			addActionError("Tài khoản hoặc mật khẩu không chính xác!");
			return "that-bai";
		} else if(admin.getTaiKhoan().getLoaiTaiKhoan().equals("admin")
				||admin.getTaiKhoan().getLoaiTaiKhoan().equals("root")
				||admin.getTaiKhoan().getLoaiTaiKhoan().equals("Nhà cung cấp")) {
			request.getSession().setAttribute("admin", admin);
			/*request.getSession().setAttribute("login", "true");*/
		} else {
			addActionError("Tài khoản hoặc mật khẩu không chính xác!");
			return "that-bai";
		}
		return "thanh-cong";
	}
	
	/**
	 * Đăng xuất khỏi hệ thống
	 * @param
	 * @return String result
	 */
	
	public String  DangXuatAdmin() {
		this.request.getSession().removeAttribute("admin");
		/*this.request.getSession().removeAttribute("login");*/
		return "thanh-cong";
	}
	
	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}
	
}
