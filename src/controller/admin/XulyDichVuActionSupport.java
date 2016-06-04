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
import model.bean.NhaCungCap;
import model.bean.QuanTri;
import model.bo.BinhLuanBO;
import model.bo.DanhMucBO;
import model.bo.DichVuBO;
import model.bo.TaiKhoanBO;
import model.bo.ValidateBO;

/**
 * XulyDichVuActionSupport.java
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

public class XulyDichVuActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private int idDichVu;
	private String tenDichVu;
	private int danhMuc;
	private List<DanhMuc> list = new ArrayList<DanhMuc>();
	private List<NhaCungCap> listNCC = new ArrayList<NhaCungCap>();
	private String moTa;
	private String noiDung;
	private String hinhAnh;
	private String nhaCungCap;
	private String ngayBatDau;
	private String ngayKetThuc;
	private String loaiHinh;
	private String diaDiem;
	// Nhận data file upload
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private HttpServletRequest servletRequest;
	private DichVu dichVu;
	private List<BinhLuan> listBinhLuan;
	private List<DangKyDichVu> listDangKy;

	/**
	 * Lấy thông tin dịch vụ cần xem
	 * @param id dịch vụ
	 * @return String result
	 */
	
	public String execute(){
		String result = "thanh-cong";
		DichVuBO dichVuBO = new DichVuBO();
		this.dichVu = dichVuBO.getDichVu(this.idDichVu+"", 0);
		if(this.dichVu!=null){
			BinhLuanBO binhLuanBO = new BinhLuanBO();
			this.listBinhLuan = binhLuanBO.getListBinhLuan(dichVu.getBaiViet().getIdBaiViet()+"");
			this.listDangKy = dichVuBO.getDanhSachDangKy(this.dichVu.getIdDichVu());
			binhLuanBO.closeConnect();
		}
		dichVuBO.closeConnect();
		if(this.dichVu==null) result = "that-bai";
		return result;
	}

	/**
	 * Xóa dịch vụ đăng tải
	 * @param id dịch vụ
	 * @return String result
	 */
	
	public String XoaDichVu(){
		String result = "thanh-cong";
		if(idDichVu<1) {
			addActionError("Dịch vụ không tồn tại!");
			result = "that-bai";
		} else {
			DichVuBO dichVuBO = new DichVuBO();
			if(!dichVuBO.xoaDichVu(idDichVu+"")) {
				addActionError("Xóa dịch vụ thất bại!");
				result = "that-bai";
			}
			dichVuBO.closeConnect();
		}
		return result;
	}

	/**
	 * Đăng dịch vụ mới
	 * @param thông tin dịch vụ mới
	 * @return String result
	 */
	
	public String DangDichVu(){
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
		QuanTri user = (QuanTri)servletRequest.getSession().getAttribute("admin");
		if(user==null || (user!=null&&!user.isDichVu())){
			addActionError("Bạn không đủ quyền hạn thực hiện thao tác này!");
			result = "that-bai";
		} else if(ValidateBO.CheckEmpty(tenDichVu) || ValidateBO.CheckEmpty(moTa)
				 || ValidateBO.CheckEmpty(danhMuc+"") || ValidateBO.CheckEmpty(noiDung)
				 || ValidateBO.CheckEmpty(nhaCungCap)
				 || ValidateBO.CheckEmpty(ngayBatDau) || ValidateBO.CheckEmpty(ngayKetThuc)){
			addActionError("Bạn chưa nhập đầy đủ dữ liệu cần thiết!");
			result = "that-bai";
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			NhaCungCap ncc = taiKhoanBO.getNhaCungCap(nhaCungCap);
			if(dichVuBO.themDichVu(tenDichVu, moTa, danhMuc, noiDung, hinhAnh, ncc.getTaiKhoan().getIdTaiKhoan(), ncc.getTaiKhoan().getHoTen(), 
					ncc.getTaiKhoan().getDienThoai(), ncc.getTaiKhoan().getEmail(), ngayBatDau, ngayKetThuc, loaiHinh, diaDiem))
				result = "thanh-cong";
			else addActionError("Đăng dịch vụ không thành công.");
			taiKhoanBO.closeConnect();
		}
		dichVuBO.closeConnect();
		return result;
	}

	/**
	 * Lấy thông tin dịch vị đăng
	 * @param id dịch vụ
	 * @return String result
	 */
	
	public String ThongTin(){
		String result = "thanh-cong";
		DichVuBO dichVuBO = new DichVuBO();
		BinhLuanBO binhLuanBO = new BinhLuanBO();
		this.dichVu = dichVuBO.getDichVu(this.idDichVu+"", 0);
		if(this.dichVu!=null)
			this.listBinhLuan = binhLuanBO.getListBinhLuan(dichVu.getBaiViet().getIdBaiViet()+"");
		dichVuBO.closeConnect();
		binhLuanBO.closeConnect();
		if(this.dichVu==null) result = "that-bai";
		return result;
	}

	/**
	 * Cập nhật thông tin dịch vụ
	 * @param thông tin dịch vụ cập nhật
	 * @return String result
	 */
	
	public String CapNhatDichVu(){
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
		if(ValidateBO.CheckEmpty(tenDichVu) || ValidateBO.CheckEmpty(moTa)
				 || ValidateBO.CheckEmpty(danhMuc+"") || ValidateBO.CheckEmpty(noiDung)
				 || ValidateBO.CheckEmpty(nhaCungCap)
				 || ValidateBO.CheckEmpty(ngayBatDau) || ValidateBO.CheckEmpty(ngayKetThuc)){
			addActionError("Bạn chưa nhập đầy đủ dữ liệu cần thiết!");
			result = "that-bai";
		} else {
			TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
			NhaCungCap ncc = taiKhoanBO.getNhaCungCap(nhaCungCap);
			if(dichVuBO.capNhatDichVu(idDichVu+"", tenDichVu, moTa, danhMuc+"", noiDung, hinhAnh,
					ncc.getTaiKhoan().getIdTaiKhoan(), ncc.getTaiKhoan().getHoTen(), 
					ncc.getTaiKhoan().getDienThoai(), ncc.getTaiKhoan().getEmail(),
					ngayBatDau, ngayKetThuc))
				result = "thanh-cong";
			else addActionError("Cập nhật dịch vụ không thành công.");
		}
		dichVuBO.closeConnect();
		return result;
	}
	
	/**
	 * Đăng dịch vụ lên hệ thống
	 * @param id dịch vụ
	 * @return String result
	 */
	
	public String CapPhatDichVu(){
		String result = "thanh-cong";
		if(idDichVu>0){
			DichVuBO dichVuBO = new DichVuBO();
			if(!dichVuBO.capNhatTinhTrang(idDichVu+"", "Đăng bài")){
				addActionError("Cấp phát dịch vụ không thành công!");
				result = "that-bai";
			}
			dichVuBO.closeConnect();
		} else {
			addActionError("Cấp phát dịch vụ không thành công!");
			result = "that-bai";
		}
		return result;
	}

	/**
	 * Báo vi phạm dịch vụ
	 * @param id dịch vụ
	 * @return String result
	 */
	
	public String BaoViPham(){
		String result = "thanh-cong";
		if(idDichVu>0){
			DichVuBO dichVuBO = new DichVuBO();
			if(!dichVuBO.capNhatTinhTrang(idDichVu+"", "Khóa bài đăng")){
				addActionError("Báo vi phạm không thành công!");
				result = "that-bai";
			}
			dichVuBO.closeConnect();
		} else {
			addActionError("Báo vi phạm không thành công!");
			result = "that-bai";
		}
		return result;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.servletRequest = req;
	}
	
	public int getIdDichVu() {
		return idDichVu;
	}

	public void setIdDichVu(int idDichVu) {
		this.idDichVu = idDichVu;
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public List<BinhLuan> getListBinhLuan() {
		return listBinhLuan;
	}

	public void setListBinhLuan(List<BinhLuan> listBinhLuan) {
		this.listBinhLuan = listBinhLuan;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public int getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(int danhMuc) {
		this.danhMuc = danhMuc;
	}

	public List<DanhMuc> getList() {
		DanhMucBO danhMucBO = new DanhMucBO();
		this.list = danhMucBO.getListDanhMucDichVu();
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

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
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
	public List<NhaCungCap> getListNCC() {
		TaiKhoanBO taiKhoanBO = new TaiKhoanBO();
		this.listNCC = taiKhoanBO.getListNhaCungCap();
		taiKhoanBO.closeConnect();
		return listNCC;
	}
	public void setListNCC(List<NhaCungCap> listNCC) {
		this.listNCC = listNCC;
	}
	public List<DangKyDichVu> getListDangKy() {
		return listDangKy;
	}
	public void setListDangKy(List<DangKyDichVu> listDangKy) {
		this.listDangKy = listDangKy;
	}

}
