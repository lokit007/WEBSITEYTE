package controller.admin;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.QuangCaoBO;

/**
 * XulyQuangCaoActionSupport.java
 *
 * Version 1.0
 *
 * Date: 08-05-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 08-05-2016        	NhanHV          Create
 */

public class XulyQuangCaoActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int idQuangCao;
	private String linkQuangCao;
	private String logoQuangCao;
	private String viTri;
	private int soNgayHienThi;
	private int idKhachHang;
	private String tenKhachHang;
	private String diaChi;
	private String email;
	
	/**
	 * Cập nhật trạng thái quảng cáo
	 * @param id quảng cáo, trạng thái cập nhật
	 * @return String result
	 */
	
	public String CapNhatQuangCao(){
		String result = "thanh-cong";
		if("1".equals(viTri))
			ServletActionContext.getRequest().getSession().setAttribute("selectTab", "one");
		else if("2".equals(viTri))
			ServletActionContext.getRequest().getSession().setAttribute("selectTab", "two");
		else
			ServletActionContext.getRequest().getSession().setAttribute("selectTab", "three");
		if(idQuangCao>0){
			QuangCaoBO quangCaoBO = new QuangCaoBO();
			if(!quangCaoBO.CapNhatQuangCao(idQuangCao, linkQuangCao)){
				addActionError("Lỗi ràng buột csdl!");
				result = "that-bai";
			}
			quangCaoBO.closeConnect();
		} else {
			addActionError("Quảng cáo không tồn tại!");
			result = "that-bai";
		}
		return result;
	}
	
	/**
	 * Xóa quảng cáo
	 * @param id quảng cáo
	 * @return String result
	 */
	
	public String XoaQuangCao(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "one");
		String result = "thanh-cong";
		if(idQuangCao>0){
			QuangCaoBO quangCaoBO = new QuangCaoBO();
			if(!quangCaoBO.XoaQuangCao(idQuangCao)){
				addActionError("Lỗi ràng buột csdl!");
				result = "that-bai";
			}
			quangCaoBO.closeConnect();
		} else {
			addActionError("Quảng cáo không tồn tại!");
			result = "that-bai";
		}
		return result;
	}

	/**
	 * Xóa khách hàng
	 * @param id khách hàng
	 * @return String result
	 */
	
	public String XoaKhachHang(){
		ServletActionContext.getRequest().getSession().setAttribute("selectTab", "three");
		String result = "thanh-cong";
		if(idKhachHang>0){
			QuangCaoBO quangCaoBO = new QuangCaoBO();
			if(!quangCaoBO.XoaKhachHang(idKhachHang)){
				addActionError("Lỗi ràng buột csdl!");
				result = "that-bai";
			}
			quangCaoBO.closeConnect();
		} else {
			addActionError("Quảng cáo không tồn tại!");
			result = "that-bai";
		}
		return result;
	}
	
	public int getIdQuangCao() {
		return idQuangCao;
	}

	public void setIdQuangCao(int idQuangCao) {
		this.idQuangCao = idQuangCao;
	}

	public String getLinkQuangCao() {
		return linkQuangCao;
	}

	public void setLinkQuangCao(String linkQuangCao) {
		this.linkQuangCao = linkQuangCao;
	}

	public String getLogoQuangCao() {
		return logoQuangCao;
	}

	public void setLogoQuangCao(String logoQuangCao) {
		this.logoQuangCao = logoQuangCao;
	}

	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}

	public int getSoNgayHienThi() {
		return soNgayHienThi;
	}

	public void setSoNgayHienThi(int soNgayHienThi) {
		this.soNgayHienThi = soNgayHienThi;
	}

	public int getIdKhachHang() {
		return idKhachHang;
	}

	public void setIdKhachHang(int idKhachHang) {
		this.idKhachHang = idKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
