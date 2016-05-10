package controller.admin;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.QuanTri;
import model.bo.TaiKhoanBO;
import model.bo.ValidateBO;

public class TrangCaNhanActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String matKhauXacNhan;
	private String matKhauMoi;
	private String idTaiKhoan;
	private String hoTen;
	private String diaChi;
	private String dienThoai;
	private String email;
	
	public String execute(){
		QuanTri admin = (QuanTri)ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(admin!=null){
			this.idTaiKhoan = admin.getTaiKhoan().getIdTaiKhoan();
			return "thanh-cong";
		} else {
			addActionError("Bạn chưa đăng nhập tài khoản thành viên!");
			return "that-bai";
		}
	}

	public String CapNhatThongTin(){
		String result = "thanh-cong";
		QuanTri admin = (QuanTri)ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(admin==null){
			addActionError("Bạn không có quyền thực hiện chức năng này!");
			result = "that-bai";
		} else if(!idTaiKhoan.equals(admin.getTaiKhoan().getIdTaiKhoan())){
			addActionError("Bạn không có quyền thực hiện chức năng này!");
			result = "that-bai";
		} else if(ValidateBO.CheckEmpty(diaChi)||ValidateBO.CheckEmpty(dienThoai)
				||ValidateBO.CheckEmpty(email)||ValidateBO.CheckEmpty(hoTen)){
			addActionError("Bạn chưa nhập đầy đủ dữ liệu đầu vào!");
			result = "that-bai";
		} else {
			if(!matKhauMoi.equals(matKhauXacNhan)){
				addActionError("Mật khẩu xác nhận không chính xác!");
				result = "that-bai";
			} else {
				if("".equals(matKhauMoi)){
					matKhauMoi = admin.getTaiKhoan().getMatKhau();
				}
				TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
				if(taiKhoanBO.CapNhatTaiKhoan(idTaiKhoan, hoTen, diaChi, dienThoai, email, matKhauMoi)){
					admin.getTaiKhoan().setHoTen(hoTen);
					admin.getTaiKhoan().setDiaChi(diaChi);
					admin.getTaiKhoan().setDienThoai(dienThoai);
					admin.getTaiKhoan().setEmail(email);
					admin.getTaiKhoan().setMatKhau(matKhauMoi);
					ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
				} else {
					addActionError("Lỗi cập nhật csdl!");
					result = "that-bai";
				}
				taiKhoanBO.closeConnect();
			}
		}
		return result;
	}
	
	public String getMatKhauXacNhan() {
		return matKhauXacNhan;
	}

	public void setMatKhauXacNhan(String matKhauXacNhan) {
		this.matKhauXacNhan = matKhauXacNhan;
	}

	public String getMatKhauMoi() {
		return matKhauMoi;
	}

	public void setMatKhauMoi(String matKhauMoi) {
		this.matKhauMoi = matKhauMoi;
	}

	public String getIdTaiKhoan() {
		return idTaiKhoan;
	}

	public void setIdTaiKhoan(String idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
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

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	
}
