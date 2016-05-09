package controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BinhLuan;
import model.bean.DangKyDichVu;
import model.bean.DanhMuc;
import model.bean.DichVu;
import model.bean.QuanTri;
import model.bo.BinhLuanBO;
import model.bo.DanhMucBO;
import model.bo.DichVuBO;
import model.bo.ValidateBO;

public class XulyNhuCauActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private int idNhuCau;
	private String tenNhuCau;
	private int danhMuc;
	private List<DanhMuc> list = new ArrayList<DanhMuc>();
	private String moTa;
	private String noiDung;
	private String hinhAnh;
	private String nhaCungCap;
	private String dienThoai;
	private String email;
	private String ngayBatDau;
	private String ngayKetThuc;
	private String loaiHinh;
	private String diaDiem;
	// Nhận data file upload
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private HttpServletRequest servletRequest;
	private DichVu nhuCau;
	private List<BinhLuan> listBinhLuan;
	private List<DangKyDichVu> listBaoGia;
	
	public String execute(){
		String result = "thanh-cong";
		DichVuBO dichVuBO = new DichVuBO();
		this.nhuCau = dichVuBO.getNhuCau(this.idNhuCau+"", 0);
		if(this.nhuCau!=null){
			BinhLuanBO binhLuanBO = new BinhLuanBO();
			this.listBinhLuan = binhLuanBO.getListBinhLuan(nhuCau.getBaiViet().getIdBaiViet()+"");
			this.listBaoGia = dichVuBO.getDanhSachDangKy(nhuCau.getIdDichVu());
			binhLuanBO.closeConnect();
		}
		dichVuBO.closeConnect();
		if(this.nhuCau==null) result = "that-bai";
		return result;
	}

	public String XoaNhuCau(){
		String result = "thanh-cong";
		if(idNhuCau<1) {
			addActionError("Dịch vụ không tồn tại!");
			result = "that-bai";
		} else {
			DichVuBO dichVuBO = new DichVuBO();
			if(!dichVuBO.xoaDichVu(idNhuCau+"")) {
				addActionError("Xóa nhu cầu thất bại!");
				result = "that-bai";
			}
			dichVuBO.closeConnect();
		}
		return result;
	}
	
	public String DangNhuCau(){
		String result = "that-bai";
		QuanTri admin = (QuanTri)servletRequest.getSession().getAttribute("admin");
		if(admin==null || (admin!=null && !admin.isNhuCau())){
			addActionError("Bạn không đủ quyền hạn để thực hiện thao tác này!");
		} else {
			this.hinhAnh = servletRequest.getSession().getServletContext().getRealPath("/").concat("images");
			try {
				File fileToCreate = new File(this.hinhAnh, this.userImageFileName); //tạo file mới trên server
				FileUtils.copyFile(this.userImage, fileToCreate); //sao chep hinh anh trong file moi
				this.hinhAnh = this.userImageFileName;
			} catch (IOException e) {
				this.hinhAnh = "bridge.jpg";
			} catch (Exception e) {
				this.hinhAnh = "bridge.jpg";
			}
			DichVuBO dichVuBO = new DichVuBO();
			if(ValidateBO.CheckEmpty(tenNhuCau) || ValidateBO.CheckEmpty(moTa)
					 || ValidateBO.CheckEmpty(danhMuc+"")
					 || ValidateBO.CheckEmpty(nhaCungCap) || ValidateBO.CheckEmpty(email)
					 || ValidateBO.CheckEmpty(ngayBatDau) || ValidateBO.CheckEmpty(ngayKetThuc)){
				addActionError("Bạn chưa nhập đầy đủ dữ liệu cần thiết!");
			} else { 
				if(dichVuBO.themDichVu(tenNhuCau, moTa, danhMuc, null, hinhAnh, admin.getTaiKhoan().getIdTaiKhoan(), 
					nhaCungCap, dienThoai, email, ngayBatDau, ngayKetThuc, "Nhu cầu", diaDiem))
					result = "thanh-cong";
				else addActionError("Đăng nhu cầu không thành công.");
			}
			dichVuBO.closeConnect();
		}
		return result;
	}
	
	public String ThongTin(){
		String result = "thanh-cong";
		DichVuBO dichVuBO = new DichVuBO();
		this.nhuCau = dichVuBO.getNhuCau(this.idNhuCau+"", 0);
		dichVuBO.closeConnect();
		if(this.nhuCau==null) result = "that-bai";
		return result;
	}
	
	public String CapNhatNhuCau(){
		this.hinhAnh = servletRequest.getSession().getServletContext().getRealPath("/").concat("images");
		try {
			File fileToCreate = new File(this.hinhAnh, this.userImageFileName); //tạo file mới trên server
			FileUtils.copyFile(this.userImage, fileToCreate); //sao chep hinh anh trong file moi
			this.hinhAnh = this.userImageFileName;
		} catch (IOException e) {
			this.hinhAnh = "bridge.jpg";
		} catch (Exception e) {
			this.hinhAnh = "bridge.jpg";
		}
		DichVuBO dichVuBO = new DichVuBO();
		String result = "that-bai";
		if(ValidateBO.CheckEmpty(tenNhuCau) || ValidateBO.CheckEmpty(moTa)
				 || ValidateBO.CheckEmpty(danhMuc+"")
				 || ValidateBO.CheckEmpty(nhaCungCap) || ValidateBO.CheckEmpty(email)
				 || ValidateBO.CheckEmpty(ngayBatDau) || ValidateBO.CheckEmpty(ngayKetThuc)){
			addActionError("Bạn chưa nhập đầy đủ dữ liệu cần thiết!");
			result = "that-bai";
		} else if(dichVuBO.capNhatDichVu(idNhuCau+"", tenNhuCau, moTa, danhMuc+"", null, 
				hinhAnh, null, nhaCungCap, dienThoai, email, ngayBatDau, ngayKetThuc))
			result = "thanh-cong";
		else addActionError("Cập nhật dịch vụ không thành công.");
		dichVuBO.closeConnect();
		return result;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.servletRequest = req;
	}
	
	public int getIdNhuCau() {
		return idNhuCau;
	}

	public void setIdNhuCau(int idNhuCau) {
		this.idNhuCau = idNhuCau;
	}

	public DichVu getNhuCau() {
		return nhuCau;
	}

	public void setNhuCau(DichVu nhuCau) {
		this.nhuCau = nhuCau;
	}

	public List<BinhLuan> getListBinhLuan() {
		return listBinhLuan;
	}

	public void setListBinhLuan(List<BinhLuan> listBinhLuan) {
		this.listBinhLuan = listBinhLuan;
	}

	public String getTenNhuCau() {
		return tenNhuCau;
	}

	public void setTenNhuCau(String tenNhuCau) {
		this.tenNhuCau = tenNhuCau;
	}

	public int getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(int danhMuc) {
		this.danhMuc = danhMuc;
	}

	public List<DanhMuc> getList() {
		DanhMucBO danhMucBO = new DanhMucBO();
		this.list = danhMucBO.getListDanhMuc("Dịch vụ");
		danhMucBO.closeConnect();
		return list;
	}

	public List<DangKyDichVu> getListBaoGia() {
		return listBaoGia;
	}

	public void setListBaoGia(List<DangKyDichVu> listBaoGia) {
		this.listBaoGia = listBaoGia;
	}

	public void setList(List<DanhMuc> list) {
		this.list = list;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
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

	public String getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(String ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public String getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(String ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getLoaiHinh() {
		return loaiHinh;
	}

	public void setLoaiHinh(String loaiHinh) {
		this.loaiHinh = loaiHinh;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
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
	
}
