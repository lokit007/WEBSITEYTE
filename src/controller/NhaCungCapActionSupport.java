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
import model.bean.TaiKhoan;
import model.bo.DanhMucBO;
import model.bo.TaiKhoanBO;
import model.bo.ValidateBO;

/**
 * NhaCungCapActionSupport.java
 *
 * Version 1.0
 *
 * Date: 17-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 17-04-2016        	NhanHV          Create
 */

public class NhaCungCapActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private String idNhaCungCap;
	private NhaCungCap nhaCungCap;
	private int danhGia;
	private String taiKhoan;
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
	
	/**
	 * Lấy thông tin nhà cung cấp muốn xem chi tiết
	 * @param id nhà cung cấp
	 * @return String result
	 */
	
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

	/**
	 * Cập nhật đánh giá về nhà cung cấp lên hệ thống
	 * @param id dịch vụ, đánh giá
	 * @return String result
	 */
	
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
	
	/**
	 * Hiển thị trang đăng ký nhà cung cấp trên hệ thống
	 * @param
	 * @return String result
	 */
	
	public String ShowDangKy(){
		if(servletRequest.getSession().getAttribute("user")==null)
			return "that-bai";
		else return "thanh-cong";
	}
	
	/**
	 * Cập nhật dữ liệu nhà cung cấp lên hệ thống
	 * @param thông tin nhà cung cấp
	 * @return String result
	 */
	
	public String DangKy(){
		this.hinhAnh = servletRequest.getSession().getServletContext().getRealPath("/").concat("images");
		try {
			File fileToCreate = new File(this.hinhAnh, this.userImageFileName);
			FileUtils.copyFile(this.userImage, fileToCreate);
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
				if(taiKhoanBO.ThemNhaCungCap(taiKhoan, "", "", "", "", "", gioiThieu, chungChi, loaiNCC, hinhAnh, 
						thoiGian, danhMuc, dienThoaiLH, emailLH, nickYahoo, nickSkype)){
					result = "thanh-cong";
				}
			} else {
				if(taiKhoanBO.ThemNhaCungCap(taiKhoan, "", "", "", "", "", gioiThieu, chungChi, loaiNCC, hinhAnh)){
					result = "thanh-cong";
					TaiKhoan user = taiKhoanBO.getTaiKhoan(taiKhoan);
					servletRequest.getSession().setAttribute("user", user);
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

	public String getLoaiNCC() {
		return loaiNCC;
	}

	public void setLoaiNCC(String loaiNCC) {
		this.loaiNCC = loaiNCC;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.servletRequest = arg0;
	}
}
