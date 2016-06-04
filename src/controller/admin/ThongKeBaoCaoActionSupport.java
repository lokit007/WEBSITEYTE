package controller.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.ThongKeBaiViet;
import model.bean.ThongKeDanhMuc;
import model.bean.ThongKeTaiKhoan;
import model.bo.ThongKeBO;
import model.bo.ValidateBO;

/**
 * ThongKeBaoCaoActionSupport.java
 *
 * Version 1.0
 *
 * Date: 28-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 28-04-2016        	NhanHV          Create
 */

public class ThongKeBaoCaoActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String tuNgay;
	private String denNgay;
	private List<ThongKeDanhMuc> listDM;
	private List<ThongKeBaiViet> listBV;
	private HashMap<String, Integer> listDV;
	private List<ThongKeTaiKhoan> listTK;
	
	/**
	 * Lấy thông tin thống kê hệ thống
	 * @param ngày bắt đầu, ngày kết thúc
	 * @return String result
	 */
	
	public String execute(){
		ThongKeBO thongKeBO = new ThongKeBO();
		if(ValidateBO.CheckEmpty(tuNgay)||ValidateBO.CheckEmpty(tuNgay)) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			this.denNgay = sdf.format(cal.getTime());
			cal.add(Calendar.MONTH, -2);
			this.tuNgay = sdf.format(cal.getTime());
		}
		this.listDM = thongKeBO.getThongKeDanhMuc(tuNgay, denNgay);
		this.listBV = thongKeBO.getThongKeBaiViet(tuNgay, denNgay);
		this.listDV = thongKeBO.getThongKeDichVu(tuNgay, denNgay);
		this.listTK = thongKeBO.getThongKeTaiKhoan(tuNgay, denNgay);
		thongKeBO.closeConnect();
		return "thanh-cong";
	}
	
	public List<ThongKeDanhMuc> getListDM() {
		return listDM;
	}
	
	public void setListDM(List<ThongKeDanhMuc> listDM) {
		this.listDM = listDM;
	}
	
	public List<ThongKeBaiViet> getListBV() {
		return listBV;
	}
	
	public void setListBV(List<ThongKeBaiViet> listBV) {
		this.listBV = listBV;
	}
	
	public HashMap<String, Integer> getListDV() {
		return listDV;
	}
	
	public void setListDV(HashMap<String, Integer> listDV) {
		this.listDV = listDV;
	}
	
	public List<ThongKeTaiKhoan> getListTK() {
		return listTK;
	}
	
	public void setListTK(List<ThongKeTaiKhoan> listTK) {
		this.listTK = listTK;
	}

	public String getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}

	public String getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}
	
}
