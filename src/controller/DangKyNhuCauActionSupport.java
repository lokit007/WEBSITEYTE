package controller;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DichVu;
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
					// Gửi người báo giá
					String html = "<p>Chào "+userLogin.getHoTen()+",<br>Chúc mừng bạn đã báo giá dịch vụ "
							+ "<b>thành công</b>.<br>Vui lòng chờ xét duyệt từ hệ thống và sự phản hồi từ người đăng tải. "
							+ "<br><a href='http://localhost:8080/WEBSITEYTE/chi-tiet-nhu-cau.action?idNhuCau="+idNhuCau
							+ "' >Hãy xem lại nhu cầu dịch vụ mà bạn đã báo giá.</a>"
							+ "<br><b>Một lần nữa chúc bạn nhận được các hợp đồng cung cấp dịch vụ tốt nhất từ hệ thống.</b><br><br>Thân<br> "
							+ "Công thông tin Dịch vụ y tế - Huế.</p>";
					EmailUtility.sendEmailThread(host, port, user, pass, userLogin.getEmail(), "Báo giá dịch vụ thành công!",html);
					// Gửi người đăng tải
					DichVu nhuCau = dichVuBO.getNhuCau(idNhuCau, 0);
					String html1 = "<p>Chào "+nhuCau.getBaiViet().getTenTacGia()+",<br>Bạn nhận được một báo giá mới từ "+userLogin.getHoTen()+". "
							+ "<br>Hãy <a href='http://localhost:8080/WEBSITEYTE/chi-tiet-nhu-cau.action?idNhuCau="+idNhuCau
							+ "' >click đây.</a> để xem chi tiết."
							+ "<br><b>Một lần nữa chúc bạn nhận được các dịch vụ tốt nhất từ hệ thống.</b><br><br>Thân<br> "
							+ "Công thông tin Dịch vụ y tế - Huế.</p>";
					EmailUtility.sendEmailThread(host, port, user, pass, nhuCau.getEmailLienHe(), "Báo giá dịch vụ mới từ nhà cung cấp",html1);
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
