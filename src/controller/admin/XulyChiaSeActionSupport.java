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
import model.bean.QuanTri;
import model.bo.BinhLuanBO;
import model.bo.ChiaSeBO;
import model.bo.DanhMucBO;
import model.bo.ValidateBO;

/**
 * XulyChiaSeActionSupport.java
 *
 * Version 1.0
 *
 * Date: 20-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 20-04-2016        	NhanHV          Create
 */

public class XulyChiaSeActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private int idBaiViet;
	private String tenBaiViet;
	private int danhMuc;
	private List<DanhMuc> list = new ArrayList<DanhMuc>();
	private String moTa;
	private String noiDung;
	private String hinhAnh;
	private String tacGia;
	// Nhận data file upload
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private HttpServletRequest servletRequest;
	private BaiViet baiViet;
	private List<BinhLuan> listBinhLuan;
	
	/**
	 * Lấy thông tin bài viết chia sẻ
	 * @param id bài viết
	 * @return String result
	 */
	
	public String execute(){
		String result = "thanh-cong";
		ChiaSeBO baiVietBO = new ChiaSeBO();
		this.baiViet = baiVietBO.getChiaSe(this.idBaiViet+"");
		if(this.baiViet!=null){
			BinhLuanBO binhLuanBO = new BinhLuanBO();
			this.listBinhLuan = binhLuanBO.getListBinhLuan(baiViet.getIdBaiViet()+"");
			binhLuanBO.closeConnect();
		}
		baiVietBO.closeConnect();
		if(this.baiViet==null) result = "that-bai";
		return result;
	}

	/**
	 * Xóa bài viết khỏi hệ thống
	 * @param id bài viết
	 * @return String result
	 */
	
	public String XoaBaiViet(){
		String result = "thanh-cong";
		if(idBaiViet<1) {
			addActionError("Bài viết không tồn tại!");
			result = "that-bai";
		} else {
			ChiaSeBO baiVietBO = new ChiaSeBO();
			if(!baiVietBO.xoaChiaSe(idBaiViet)) {
				addActionError("Xóa bài viết thất bại!");
				result = "that-bai";
			}
			baiVietBO.closeConnect();
		}
		return result;
	}
	
	/**
	 * Thêm bài viết chia sẻ mới vào hệ thống
	 * @param thông tin bài viết mới
	 * @return String result
	 */
	
	public String DangBaiViet(){
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
			ChiaSeBO baiVietBO = new ChiaSeBO();
			if(ValidateBO.CheckEmpty(tenBaiViet) || ValidateBO.CheckEmpty(moTa)
					 || ValidateBO.CheckEmpty(danhMuc+"") || ValidateBO.CheckEmpty(noiDung)
					 || ValidateBO.CheckEmpty(tacGia)){
				addActionError("Bạn chưa nhập đầy đủ dữ liệu cần thiết!");
				result = "that-bai";
			} else if(baiVietBO.ThemChiaSe(tenBaiViet, moTa, danhMuc, noiDung, hinhAnh, admin.getTaiKhoan().getIdTaiKhoan(), tacGia))
				result = "thanh-cong";
			else addActionError("Đăng bài viết không thành công.");
			baiVietBO.closeConnect();
		}
		return result;
	}
	
	/**
	 * Lấy thông tin bài viết chia sẻ
	 * @param id bài viết
	 * @return String result
	 */
	
	public String ThongTin(){
		String result = "thanh-cong";
		ChiaSeBO baiVietBO = new ChiaSeBO();
		BinhLuanBO binhLuanBO = new BinhLuanBO();
		this.baiViet = baiVietBO.getChiaSe(this.idBaiViet+"");
		if(this.baiViet!=null)
			this.listBinhLuan = binhLuanBO.getListBinhLuan(baiViet.getIdBaiViet()+"");
		baiVietBO.closeConnect();
		binhLuanBO.closeConnect();
		if(this.baiViet==null) result = "that-bai";
		return result;
	}
	
	/**
	 * Cập nhật thông tin bài viết chia sẻ
	 * @param thông tin bài viết
	 * @return String result
	 */
	
	public String CapNhatBaiViet(){
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
		ChiaSeBO baiVietBO = new ChiaSeBO();
		String result = "that-bai";
		if(ValidateBO.CheckEmpty(tenBaiViet) || ValidateBO.CheckEmpty(moTa)
				 || ValidateBO.CheckEmpty(danhMuc+"") || ValidateBO.CheckEmpty(noiDung)
				 || ValidateBO.CheckEmpty(tacGia)){
			addActionError("Bạn chưa nhập đầy đủ dữ liệu cần thiết!");
			result = "that-bai";
		} else if(baiVietBO.capNhatChiaSe(idBaiViet, tenBaiViet, moTa, danhMuc, noiDung, hinhAnh, tacGia))
			result = "thanh-cong";
		else addActionError("Cập nhật bài viết không thành công.");
		baiVietBO.closeConnect();
		return result;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.servletRequest = req;
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

	public int getIdBaiViet() {
		return idBaiViet;
	}

	public void setIdBaiViet(int idBaiViet) {
		this.idBaiViet = idBaiViet;
	}

	public String getTenBaiViet() {
		return tenBaiViet;
	}

	public void setTenBaiViet(String tenBaiViet) {
		this.tenBaiViet = tenBaiViet;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public int getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(int danhMuc) {
		this.danhMuc = danhMuc;
	}

	public List<DanhMuc> getList() {
		DanhMucBO danhMucBO = new DanhMucBO();
		this.list = danhMucBO.getListDanhMuc("Chia sẻ");
		danhMucBO.closeConnect();
		return list;
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
