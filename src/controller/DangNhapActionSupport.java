package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.TaiKhoan;
import model.bo.EmailUtility;
import model.bo.ValidateBO;

public class DangNhapActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String taiKhoan;
	private String matKhau;
	private String matKhauLai;
	private String hoTen;
	private String diaChi;
	private String location;
	private String dienThoai;
	private String email;
	
	public String execute(){
		if(this.request.getSession().getAttribute("user")==null)
			return "input";
		else return "login-ok";
	}
	
	public String DangNhapClient(){
		if(ValidateBO.CheckEmpty(this.taiKhoan)||ValidateBO.CheckEmpty(this.matKhau)){
			addActionError("Xin vui lòng nhập đầy đủ thông tin!");
			return "that-bai";
		}
		TaiKhoan user = ValidateBO.CheckAccountExist(this.taiKhoan, this.matKhau);
		if(user==null) {
			addActionError("Tài khoản hoặc mật khẩu không chính xác!");
			return "that-bai";
		} else {
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("login", "true");
		}
		return "thanh-cong";
	}
	
	public String DangNhapModal(){
		if(ValidateBO.CheckEmpty(this.taiKhoan)||ValidateBO.CheckEmpty(this.matKhau)){
			addActionError("Xin vui lòng nhập đầy đủ thông tin!");
		}
		TaiKhoan user = ValidateBO.CheckAccountExist(this.taiKhoan, this.matKhau);
		if(user==null) {
			addActionError("Tài khoản hoặc mật khẩu không chính xác!");
		} else {
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("login", "true");
		}
		return "thanh-cong";
	}
	
	public String DangKyClient(){
		if(ValidateBO.CheckEmpty(this.taiKhoan)||ValidateBO.CheckEmpty(this.matKhau)
				||ValidateBO.CheckEmpty(this.matKhauLai)||ValidateBO.CheckEmpty(this.hoTen)
				||ValidateBO.CheckEmpty(this.diaChi)||ValidateBO.CheckEmpty(this.dienThoai)
				||ValidateBO.CheckEmpty(this.email)){
			addActionError("Xin vui lòng nhập đầy đủ thông tin!");
			return "that-bai";
		} else {
			if(ValidateBO.CheckExist(this.taiKhoan, this.email)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				TaiKhoan user = new TaiKhoan(this.taiKhoan, this.matKhau, this.hoTen, this.diaChi
						, this.dienThoai, this.email, "TK mới", sdf.format(new Date()), location);
				user.setLoaiTaiKhoan("Người dùng");
				if(ValidateBO.CheckAccountExist(user)){
					ServletContext context = ServletActionContext.getRequest().getServletContext();
					final String host = context.getInitParameter("host");
					final String port = context.getInitParameter("port");
					final String userroot = context.getInitParameter("user");
					final String pass = context.getInitParameter("pass");
					try {
						String html = "Chào <b>"+hoTen+"</b>!<br>Chúc mừng bạn đã đăng ký tài khoản thành viên thành công!<br>Thông tin tài khoản của bạn là :"
								+ "<br>Tài khoản : " + taiKhoan + "<br> Mật khẩu : " + matKhau + "<br> Cám ơn bạn đã quan tâm.<br>Thân ái!<br><br>Sở Y Tế Huế";
						EmailUtility.sendEmailThread(host, port, userroot, pass, email, "CỔNG THÔNG TIN DỊCH VỤ Y TẾ CỘNG ĐỒNG - Sở Y Tế Huế", html);
					} catch (Exception ex) {
			        	System.out.println("Lỗi : " + ex.toString());
			        }
					ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Chúc mừng!Bạn đã đăng ký thành viên thành công. Vui lòng cập nhật thông tin để được trải nghiệm nhiều hơn!");
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("login", "true");
					return "thanh-cong";
				} else {
					addActionError("Lỗi cập kết nối cơ sở dữ liệu server!");
					return "that-bai";
				}
			} else {
				addActionError("Tài khoản hoặc mật khẩu không chính xác!");
				return "that-bai";
			}
		}
	}
	
	public String  DangXuatClient() {
		this.request.getSession().removeAttribute("user");
		this.request.getSession().removeAttribute("login");
		return "thanh-cong";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
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

	public String getMatKhauLai() {
		return matKhauLai;
	}

	public void setMatKhauLai(String matKhauLai) {
		this.matKhauLai = matKhauLai;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
