package controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.HoTroOnline;
import model.bo.EmailUtility;
import model.bo.ValidateBO;

public class LienHeActionSupport extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<HoTroOnline> list;
	private String tieuDe;
	private String noiDung;
	private String urlPage;
	private String email;
	
	public String execute(){
		if(ValidateBO.CheckEmpty(tieuDe)||ValidateBO.CheckEmpty(noiDung)||ValidateBO.CheckEmpty(email)){
			addActionError("Bạn chưa nhập đầy đủ thông tin");
		} else {
			ServletContext context = ServletActionContext.getRequest().getServletContext();
			final String host = context.getInitParameter("host");
			final String port = context.getInitParameter("port");
			final String user = context.getInitParameter("user");
			final String pass = context.getInitParameter("pass");
			try {
				EmailUtility.sendEmailThread("smtp.gmail.com", "587", user, pass, email, tieuDe, noiDung + "<br>" + urlPage);
	        } catch (Exception ex) {
	        	System.out.println("Lỗi : " + ex.toString());
	        }
		}
		return "thanh-cong";
	}

	public List<HoTroOnline> getList() {
		return list;
	}

	public void setList(List<HoTroOnline> list) {
		this.list = list;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getUrlPage() {
		return urlPage;
	}

	public void setUrlPage(String urlPage) {
		this.urlPage = urlPage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
