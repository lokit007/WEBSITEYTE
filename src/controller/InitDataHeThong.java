package controller;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.TaiNguyen;
import model.bo.HeThongBO;

public class InitDataHeThong extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private TaiNguyen heThong;
	
	public String execute(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		this.heThong = (TaiNguyen)session.getAttribute("TaiNguyen");
		if(heThong==null){
			HeThongBO heThongBO = new HeThongBO();
			this.heThong = heThongBO.getTaiNguyen();
			session.setAttribute("TaiNguyen", this.heThong);
			heThongBO.closeConnect();
		}
		return "thanh-cong";
	}

	public TaiNguyen getHeThong() {
		return heThong;
	}

	public void setHeThong(TaiNguyen heThong) {
		this.heThong = heThong;
	}
	
}
