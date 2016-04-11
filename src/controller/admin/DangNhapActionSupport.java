package controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.TaiKhoan;
import model.bo.ValidateBO;

public class DangNhapActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String taiKhoan;
	private String matKhau;
	
	public static boolean CheckLogin(){
		if(ServletActionContext.getRequest().getSession().getAttribute("admin")!=null)
			return true;
		else
			return false;
	}
	
	public String execute(){
		if(this.request.getSession().getAttribute("admin")==null)
			return "input";
		else return "login-ok";
	}

	public String DangNhapAdmin(){
		if(ValidateBO.CheckEmpty(this.taiKhoan)||ValidateBO.CheckEmpty(this.matKhau)){
			addActionError("Xin vui lòng nhập đầy đủ thông tin!");
			return "that-bai";
		}
		TaiKhoan admin = ValidateBO.CheckAccountExist(this.taiKhoan, this.matKhau);
		if(admin==null) {
			addActionError("Tài khoản hoặc mật khẩu không chính xác!");
			return "that-bai";
		} else if(admin.getLoaiTaiKhoan().equals("admin")||admin.getLoaiTaiKhoan().equals("root")) {
			request.getSession().setAttribute("admin", admin);
			request.getSession().setAttribute("login", "true");
		} else {
			addActionError("Tài khoản hoặc mật khẩu không chính xác!");
			return "that-bai";
		}
		return "thanh-cong";
	}
	
	public String  DangXuatClient() {
		this.request.getSession().removeAttribute("admin");
		this.request.getSession().removeAttribute("login");
		this.request.getSession().invalidate();
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
