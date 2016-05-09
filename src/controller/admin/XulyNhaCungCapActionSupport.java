package controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.BaiViet;
import model.bean.BinhLuan;
import model.bean.DanhMuc;
import model.bean.NhaCungCap;
import model.bo.DanhMucBO;
import model.bo.TaiKhoanBO;
import model.bo.ValidateBO;

public class XulyNhaCungCapActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private String idNhaCungCap;
	private NhaCungCap nhaCungCap;
	private String taiKhoan;
	private String hoTen;
	private String diaChi;
	private String location;
	private String dienThoai;
	private String email;
	private String gioiThieu;
	private String chungChi;
	private String loaiNCC;
	private boolean check;
	private String thoiGian;
	private int danhMuc;
	private String dienThoaiLH;
	private String emailLH;
	private String nickYahoo;
	private String nickSkype;
	private List<DanhMuc> list = new ArrayList<DanhMuc>();
	private String hinhAnh;
	// Nhận data file upload
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private HttpServletRequest servletRequest;
	private BaiViet baiViet;
	private List<BinhLuan> listBinhLuan;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.servletRequest = arg0;
	}
	
	public String ChiTiet(){
		if(ValidateBO.CheckEmpty(idNhaCungCap)){
			addActionError("Nhà cung cấp không tồn tại trong hệ thống! Vui lòng chọn thao tác khác.");
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			this.nhaCungCap = taiKhoanBO.getNhaCungCap(idNhaCungCap);
			if(this.nhaCungCap==null) addActionError("Nhà cung cấp không tồn tại trong hệ thống! Vui lòng chọn thao tác khác.");
			taiKhoanBO.closeConnect();
		}
		return "thanh-cong";
	}

	public String ThemNhaCungCap(){
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
		String result = "that-bai";
		if(ValidateBO.CheckEmpty(taiKhoan)||ValidateBO.CheckEmpty(hoTen)||ValidateBO.CheckEmpty(diaChi)||
				ValidateBO.CheckEmpty(dienThoai)||ValidateBO.CheckEmpty(email))
		{
			result = "that-bai";
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			if(check){
				if(taiKhoanBO.ThemNhaCungCap(taiKhoan, hoTen, diaChi, location, dienThoai, email, gioiThieu, chungChi,
						loaiNCC, hinhAnh, thoiGian, danhMuc, dienThoaiLH, emailLH, nickYahoo, nickSkype)){
					result = "thanh-cong";
				}
			} else {
				if(taiKhoanBO.ThemNhaCungCap(taiKhoan, hoTen, diaChi, location, dienThoai, email, gioiThieu, chungChi,
						loaiNCC, hinhAnh)){
					result = "thanh-cong";
				}
			}
			taiKhoanBO.closeConnect();
		}
		return result;
	}
	
	public String ChiTietNCC(){
		if(ValidateBO.CheckEmpty(idNhaCungCap)){
			addActionError("Tài khoản không tồn tại!");
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			this.nhaCungCap = taiKhoanBO.getNhaCungCap(idNhaCungCap);
			taiKhoanBO.closeConnect();
		}
		return "thanh-cong";
	}
	
	public String CapNhatNhaCungCap(){
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
		String result = "that-bai";
		if(ValidateBO.CheckEmpty(taiKhoan)||ValidateBO.CheckEmpty(hoTen)||ValidateBO.CheckEmpty(diaChi)||
				ValidateBO.CheckEmpty(dienThoai)||ValidateBO.CheckEmpty(email))
		{
			result = "that-bai";
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			if(check){
				if(taiKhoanBO.CapNhatNhaCungCap(idNhaCungCap, taiKhoan, hoTen, diaChi, dienThoai, email, gioiThieu, chungChi,
						hinhAnh, thoiGian, danhMuc, dienThoaiLH, emailLH, nickYahoo, nickSkype)){
					result = "thanh-cong";
				}
			} else {
				if(taiKhoanBO.CapNhatNhaCungCap(idNhaCungCap, taiKhoan, hoTen, diaChi, dienThoai, email, gioiThieu, chungChi,
						hinhAnh)){
					result = "thanh-cong";
				}
			}
			taiKhoanBO.closeConnect();
		}
		return result;
	}

	public String XoaNhaCungCap(){
		if(ValidateBO.CheckEmpty(idNhaCungCap)){
			addActionError("Nhà cung cấp không tồn tại");
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			if(!taiKhoanBO.XoaNhaCungCap(idNhaCungCap)){
				addActionError("Bạn không thể xóa nhà cung cấp này!");
			}
			taiKhoanBO.closeConnect();
		}
		return "thanh-cong";
	}
	
	public String getIdNhaCungCap() {
		return idNhaCungCap;
	}

	public void setIdNhaCungCap(String idNhaCungCap) {
		this.idNhaCungCap = idNhaCungCap;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
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

	public String getGioiThieu() {
		return gioiThieu;
	}

	public void setGioiThieu(String gioiThieu) {
		this.gioiThieu = gioiThieu;
	}

	public String getChungChi() {
		return chungChi;
	}

	public void setChungChi(String chungChi) {
		this.chungChi = chungChi;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLoaiNCC() {
		return loaiNCC;
	}

	public void setLoaiNCC(String loaiNCC) {
		this.loaiNCC = loaiNCC;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	public int getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(int danhMuc) {
		this.danhMuc = danhMuc;
	}

	public String getDienThoaiLH() {
		return dienThoaiLH;
	}

	public void setDienThoaiLH(String dienThoaiLH) {
		this.dienThoaiLH = dienThoaiLH;
	}

	public String getEmailLH() {
		return emailLH;
	}

	public void setEmailLH(String emailLH) {
		this.emailLH = emailLH;
	}

	public String getNickYahoo() {
		return nickYahoo;
	}

	public void setNickYahoo(String nickYahoo) {
		this.nickYahoo = nickYahoo;
	}

	public String getNickSkype() {
		return nickSkype;
	}

	public void setNickSkype(String nickSkype) {
		this.nickSkype = nickSkype;
	}

	public List<DanhMuc> getList() {
		DanhMucBO danhMucBO = new DanhMucBO();
		this.list = danhMucBO.getListDanhMuc("Tư vấn");
		danhMucBO.closeConnect();
		return list;
	}

	public void setList(List<DanhMuc> list) {
		this.list = list;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
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

	public BaiViet getBaiViet() {
		return baiViet;
	}

	public void setBaiViet(BaiViet baiViet) {
		this.baiViet = baiViet;
	}

	public List<BinhLuan> getListBinhLuan() {
		return listBinhLuan;
	}

	public void setListBinhLuan(List<BinhLuan> listBinhLuan) {
		this.listBinhLuan = listBinhLuan;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
}
