package controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.bo.HeThongBO;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;

public class SessionCounter implements HttpSessionListener {
	@SuppressWarnings("rawtypes")
	private static List sessions = new ArrayList();
	private static int view = 0;

	@SuppressWarnings("unchecked")
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.add(session.getId());
		view++;
		session.setAttribute("counter", this);
	}
	
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.remove(session.getId());
		view--;
		session.setAttribute("counter", this);
	}

	public int getView() {
		if(view<2){
			HeThongBO heThongBO = new HeThongBO();
			try {
				SessionCounter.view = Integer.parseInt(heThongBO.getTaiNguyen().getSoLuongTruyCap());
			} catch (Exception e){
				SessionCounter.view = 0;
			}
			heThongBO.closeConnect();
		}
		return view;
	}

	public int getOnline() {
		return sessions.size();
	}
	
}