package controller;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DichVu;
import model.bean.TaiKhoan;
import model.bo.DichVuBO;
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
	private List<DichVu> listDV;
	private List<DichVu> listNC;
	
	public String execute(){
		TaiKhoan user = (TaiKhoan)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user!=null){
			this.idTaiKhoan = user.getIdTaiKhoan();
			DichVuBO dichVuBO = new DichVuBO();
			this.listDV = dichVuBO.getDichVuDangKy(idTaiKhoan);
			this.listNC = dichVuBO.getNhuCauDang(idTaiKhoan);
			dichVuBO.closeConnect();
			return "thanh-cong";
		} else {
			addActionError("Bạn chưa đăng nhập tài khoản thành viên!");
			return "that-bai";
		}
	}

	public String CapNhatThongTin(){
		String result = "thanh-cong";
		TaiKhoan user = (TaiKhoan)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null){
			addActionError("Bạn không có quyền thực hiện chức năng này!");
			result = "that-bai";
		} else if(!idTaiKhoan.equals(user.getIdTaiKhoan())){
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
					matKhauMoi = user.getMatKhau();
				}
				TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
				if(taiKhoanBO.CapNhatTaiKhoan(idTaiKhoan, hoTen, diaChi, dienThoai, email, matKhauMoi)){
					user.setHoTen(hoTen);
					user.setDiaChi(diaChi);
					user.setDienThoai(dienThoai);
					user.setEmail(email);
					user.setMatKhau(matKhauMoi);
					ServletActionContext.getRequest().getSession().setAttribute("user", user);
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

	public List<DichVu> getListDV() {
		return listDV;
	}

	public void setListDV(List<DichVu> listDV) {
		this.listDV = listDV;
	}

	public List<DichVu> getListNC() {
		return listNC;
	}

	public void setListNC(List<DichVu> listNC) {
		this.listNC = listNC;
	}
	
}
