package controller;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.TaiKhoan;
import model.bo.DichVuBO;
import model.bo.EmailUtility;

public class DangKyNhuCauActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String giaDichVu;
	private String tinNhan;
	private String idNhuCau;
	
	public String execute(){
		TaiKhoan userLogin = (TaiKhoan)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(userLogin==null){
			addActionError("Bạn chưa đăng nhập hệ thống!");
		} else {
			DichVuBO dichVuBO = new DichVuBO();
			if(!dichVuBO.dangKyNhuCau(idNhuCau, userLogin.getIdTaiKhoan(), giaDichVu + "@" + tinNhan)){
				addActionError("Không thể cập nhật cơ sở dữ liệu!");
			} else {
				ServletContext context = ServletActionContext.getRequest().getServletContext();
				final String host = context.getInitParameter("host");
				final String port = context.getInitParameter("port");
				final String user = context.getInitParameter("user");
				final String pass = context.getInitParameter("pass");
				try {
					EmailUtility.sendEmailThread("smtp.gmail.com", "587", user, pass, userLogin.getEmail(), "Đăng ký dịch vụ",
							"Đã đăng ký dịch vụ thành công. Vui lòng chờ xác nhận từ nhà cung cấp dịch vụ. Xin cám ơn!");
		        } catch (Exception ex) {
		        	System.out.println("Lỗi : " + ex.toString());
		        }
			}
			dichVuBO.closeConnect();
		}
		return "thanh-cong";
	}

	public String getIdNhuCau() {
		return idNhuCau;
	}

	public void setIdNhuCau(String idNhuCau) {
		this.idNhuCau = idNhuCau;
	}

	public String getGiaDichVu() {
		return giaDichVu;
	}

	public void setGiaDichVu(String giaDichVu) {
		this.giaDichVu = giaDichVu;
	}

	public String getTinNhan() {
		return tinNhan;
	}

	public void setTinNhan(String tinNhan) {
		this.tinNhan = tinNhan;
	}
	
}
