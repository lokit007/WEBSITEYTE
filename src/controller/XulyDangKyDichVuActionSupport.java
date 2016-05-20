package controller;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DangKyDichVu;
import model.bo.DichVuBO;
import model.bo.EmailUtility;
import model.bo.ValidateBO;

public class XulyDangKyDichVuActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int idDangKy;
	private String emailBenhNhan;
	private String emailNhaCungCap;
	private String tinNhan;
	private DangKyDichVu dangKyDichVu;
	
	public String execute(){
		String result = "thanh-cong";
		DichVuBO dichVuBO = new DichVuBO();
		this.dangKyDichVu = dichVuBO.getDangKyDichVu(idDangKy);
		dichVuBO.closeConnect();
		return result;
	}

	public String ChapNhanDangKy(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "tab4");
		String result = "thanh-cong";
		if(idDangKy>0 && !ValidateBO.CheckEmpty(emailBenhNhan)){
			DichVuBO dichVuBO = new DichVuBO();
			if(dichVuBO.CapNhatDichVuDangKy(idDangKy+"", "Chấp nhận")){
				ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Đã cập nhật cơ sở dữ liệu thành công!");
				ServletContext context = ServletActionContext.getRequest().getServletContext();
				final String host = context.getInitParameter("host");
				final String port = context.getInitParameter("port");
				final String user = context.getInitParameter("user");
				final String pass = context.getInitParameter("pass");
				try {
					String html = "<p>Chào bạn,<br><b>Dịch vụ bạn đăng ký đã được phê duyệt từ nhà cung cấp</b>.<br>"
							+ "<br>Tin nhắn từ nhà cung cấp : " + tinNhan
							+ "<br>Chúc bạn tận hưởng được dịch vụ tốt nhất.<br>Nếu có vấn mắt xin hãy phản hồi cho hệ thống ngay khi có thể."
							+ "<br>Một lần nữa xin cám ơn!<br> Thân ái,<br><br>Cổng thông tin dịch vụ y tế cộng đồng - Sở Y Tế Huế</p>";;
					
					EmailUtility.sendEmailThread(host, port, user, pass, emailBenhNhan, "Xác nhận đăng ký dịch vụ từ nhà cung cấp", html);
				} catch (Exception ex) {
		        	System.out.println("Lỗi : " + ex.toString());
		        }
			} else {
				addActionError("Cập nhật dữ liệu thất bại!");
				ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Lỗi! Không thể cập nhật cơ sở dữ liệu.");
			}
			dichVuBO.closeConnect();
		} else {
			addActionError("Cập nhật dữ liệu thất bại!");
			ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Lỗi! Không thể cập nhật cơ sở dữ liệu.");
		}
		return result;
	}
	
	public String HuyDangKy(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "tab4");
		String result = "thanh-cong";
		if(idDangKy>0){
			DichVuBO dichVuBO = new DichVuBO();
			if(dichVuBO.CapNhatDichVuDangKy(idDangKy+"", "Hủy bỏ")){
				ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Đã cập nhật cơ sở dữ liệu thành công!");
				ServletContext context = ServletActionContext.getRequest().getServletContext();
				final String host = context.getInitParameter("host");
				final String port = context.getInitParameter("port");
				final String user = context.getInitParameter("user");
				final String pass = context.getInitParameter("pass");
				try {
					String noiDung = "Rất tiết! Chúng tôi không thể cung cấp dịch theo yêu cầu của bạn được. Hân hạnh đón tiếp bại với các dịch dịch vụ khác của chúng tôi.Chúc bạn luôn khỏe mạnh, an lành.";
					EmailUtility.sendEmailThread(host, port, user, pass, emailBenhNhan, "Hủy bỏ đăng ký dịch vụ",
							noiDung);
				} catch (Exception ex) {
		        	System.out.println("Lỗi : " + ex.toString());
		        }
			} else {
				addActionError("Cập nhật dữ liệu thất bại!");
				ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Lỗi!Không thể cập nhật cơ sở dữ liệu.");
			}
			dichVuBO.closeConnect();
		} else {
			addActionError("Cập nhật dữ liệu thất bại!");
			ServletActionContext.getRequest().getSession().setAttribute("ThongBao", "Lỗi!Không thể cập nhật cơ sở dữ liệu.");
		}
		return result;
	}
	
	public int getIdDangKy() {
		return idDangKy;
	}

	public void setIdDangKy(int idDangKy) {
		this.idDangKy = idDangKy;
	}

	public String getEmailBenhNhan() {
		return emailBenhNhan;
	}

	public void setEmailBenhNhan(String emailBenhNhan) {
		this.emailBenhNhan = emailBenhNhan;
	}

	public String getEmailNhaCungCap() {
		return emailNhaCungCap;
	}

	public void setEmailNhaCungCap(String emailNhaCungCap) {
		this.emailNhaCungCap = emailNhaCungCap;
	}

	public String getTinNhan() {
		return tinNhan;
	}

	public void setTinNhan(String tinNhan) {
		this.tinNhan = tinNhan;
	}

	public DangKyDichVu getDangKyDichVu() {
		return dangKyDichVu;
	}

	public void setDangKyDichVu(DangKyDichVu dangKyDichVu) {
		this.dangKyDichVu = dangKyDichVu;
	}
	
}
