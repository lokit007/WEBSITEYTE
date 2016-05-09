package controller;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.DichVuBO;
import model.bo.EmailUtility;
import model.bo.ValidateBO;

public class DangKyDichVuActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String idDichVu;
	private String hoTen;
	private String email;
	private String dienThoai;
	private String tinNhan;
	
	public String execute(){
		if(ValidateBO.CheckEmpty(hoTen) || ValidateBO.CheckEmpty(email) || ValidateBO.CheckEmpty(dienThoai)){
			addActionError("Bạn chưa nhập đầy đủ dữ liệu!");
		} else if(ValidateBO.ChekDangKy(idDichVu, email)){
			addActionError("Bạn đã đăng ký dịch vụ này!");
			ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Bạn đã đăng ký dịch vụ này rồi!");
		} else {
			DichVuBO dichVuBO = new DichVuBO();
			if(!dichVuBO.dangKyDichVu(idDichVu, hoTen, email, dienThoai, tinNhan)){
				addActionError("Không thể cập nhật cơ sở dữ liệu!");
				ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Lỗi!Không thể cập nhật cơ sở dữ liệu.");
			} else {
				ServletContext context = ServletActionContext.getRequest().getServletContext();
				final String host = context.getInitParameter("host");
				final String port = context.getInitParameter("port");
				final String user = context.getInitParameter("user");
				final String pass = context.getInitParameter("pass");
				try {
					EmailUtility.sendEmailThread("smtp.gmail.com", "587", user, pass, email, "Đăng ký dịch vụ",
							"Đã đăng ký dịch vụ thành công. Vui lòng chờ xác nhận từ nhà cung cấp dịch vụ. Xin cám ơn!");
				} catch (Exception ex) {
		        	System.out.println("Lỗi : " + ex.toString());
		        }
				ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Chúc mừng!Bạn đã đăng ký dịch vụ thành công. Vui lòng chờ xác nhận từ nhà cung cấp. Cám ơn!");
			}
			dichVuBO.closeConnect();
		}
		return "thanh-cong";
	}

	public String CheckDangKy(){
		if(ValidateBO.ChekDangKy(idDichVu, email)){
			addActionError("Bạn đã đăng ký dịch vụ này!");
			ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Bạn đã đăng ký dịch vụ này rồi!");
		}
		return "thanh-cong";
	}
	
	public String getIdDichVu() {
		return idDichVu;
	}

	public void setIdDichVu(String idDichVu) {
		this.idDichVu = idDichVu;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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

	public String getTinNhan() {
		return tinNhan;
	}

	public void setTinNhan(String tinNhan) {
		this.tinNhan = tinNhan;
	}
	
}
