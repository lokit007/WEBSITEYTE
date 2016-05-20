package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.QuangCao;
import model.bean.TaiNguyen;
import model.bo.HeThongBO;
import model.bo.QuangCaoBO;
import model.bo.ValidateBO;

public class InitDataHeThong extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private TaiNguyen heThong;
	private List<QuangCao> listQuangCao;
	private QuangCao topMenu;
	private QuangCao menuBar;
	private QuangCao content;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		this.heThong = (TaiNguyen)session.getAttribute("TaiNguyen");
		if(heThong==null){
			HeThongBO heThongBO = new HeThongBO();
			this.heThong = heThongBO.getTaiNguyen();
			session.setAttribute("TaiNguyen", this.heThong);
			heThongBO.closeConnect();
		}
		this.listQuangCao = (List<QuangCao>)session.getAttribute("QuangCao");
		if(this.listQuangCao==null){
			QuangCaoBO quangCaoBO = new QuangCaoBO();
			this.listQuangCao = quangCaoBO.getDanhSachHienThi();
			quangCaoBO.closeConnect();
			session.setAttribute("QuangCao", this.listQuangCao);
			this.topMenu = getQuangCao("TopMenu");
			this.menuBar = getQuangCao("MenuBar");
			this.content = getQuangCao("Content");
			session.setAttribute("TopMenu", this.topMenu);
			session.setAttribute("MenuBar", this.menuBar);
			session.setAttribute("Content", this.content);
		}
		return "thanh-cong";
	}

	public QuangCao getQuangCao(String vitri){
		if(ValidateBO.CheckEmpty(vitri)) vitri = "TopMenu";
		for(int i=0; this.listQuangCao!=null && i<this.listQuangCao.size(); i++){
			if(this.listQuangCao.get(i).getViTri().getTenViTri().equals(vitri)){
				return listQuangCao.get(i);
			}
		}
		return null;
	}
	
	public TaiNguyen getHeThong() {
		return heThong;
	}

	public void setHeThong(TaiNguyen heThong) {
		this.heThong = heThong;
	}

	public List<QuangCao> getListQuangCao() {
		return listQuangCao;
	}

	public void setListQuangCao(List<QuangCao> listQuangCao) {
		this.listQuangCao = listQuangCao;
	}

	public QuangCao getTopMenu() {
		return topMenu;
	}

	public void setTopMenu(QuangCao topMenu) {
		this.topMenu = topMenu;
	}

	public QuangCao getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(QuangCao menuBar) {
		this.menuBar = menuBar;
	}

	public QuangCao getContent() {
		return content;
	}

	public void setContent(QuangCao content) {
		this.content = content;
	}
	
}
