package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DanhMuc;
import model.bean.NhaCungCap;
import model.bo.DanhMucBO;
import model.bo.TaiKhoanBO;
import model.bo.ValidateBO;

public class NhaCungCapActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private String idNhaCungCap;
	private NhaCungCap nhaCungCap;
	private int danhGia;
	private String taiKhoan;
	private String gioiThieu;
	private String chungChi;
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
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.servletRequest = arg0;
	}
	
	public String execute(){
		String result = "thanh-cong";
		if(!"".equals(this.idNhaCungCap)){
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			this.nhaCungCap = taiKhoanBO.getNhaCungCap(idNhaCungCap);
			taiKhoanBO.closeConnect();
			if(this.nhaCungCap==null) {
				addActionError("Nhà cung cấp không tồn tại!");
				result="that-bai";
			}
		} else {
			addActionError("Nhà cung cấp không tồn tại!");
			result="that-bai";
		}
		return result;
	}

	public String DanhGia(){
		String result = "thanh-cong";
		if(!"".equals(this.idNhaCungCap)){
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			taiKhoanBO.setDanhGia(idNhaCungCap, danhGia);
			taiKhoanBO.closeConnect();
		} else {
			addActionError("Nhà cung cấp không tồn tại!");
			result="that-bai";
		}
		return result;
	}
	
	public String ShowDangKy(){
		if(servletRequest.getSession().getAttribute("user")==null)
			return "that-bai";
		else return "thanh-cong";
	}
	
	public String DangKy(){
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
		if(ValidateBO.CheckEmpty(taiKhoan)||ValidateBO.CheckEmpty(gioiThieu)||ValidateBO.CheckEmpty(chungChi))
		{
			result = "that-bai";
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			if(check){
				if(taiKhoanBO.ThemNhaCungCap(taiKhoan, "", "", "", "", gioiThieu, chungChi, hinhAnh, 
						thoiGian, danhMuc, dienThoaiLH, emailLH, nickYahoo, nickSkype)){
					result = "thanh-cong";
				}
			} else {
				if(taiKhoanBO.ThemNhaCungCap(taiKhoan, "", "", "", "", gioiThieu, chungChi, hinhAnh)){
					result = "thanh-cong";
				}
			}
			taiKhoanBO.closeConnect();
		}
		return result;
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

	public int getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(int danhGia) {
		this.danhGia = danhGia;
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

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
}
