package controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.bo.HeThongBO;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;

/**
 * SessionCounter.java
 *
 * Version 1.0
 *
 * Date: 10-04-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 10-04-2016        	NhanHV          Create
 */

public class SessionCounter implements HttpSessionListener {
	@SuppressWarnings("rawtypes")
	private static List sessions = new ArrayList();
	private static int view = 0;

	/**
	 * Đếm số người truy cập trong hệ thống khi có truy cập mới
	 * @param
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.add(session.getId());
		view++;
		session.setAttribute("counter", this);
	}
	
	/**
	 * Đếm số người truy cập trong hệ thống khi hủy một truy cập
	 * @param
	 * @return
	 */
	
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.remove(session.getId());
		view--;
		session.setAttribute("counter", this);
	}

	/**
	 * Trả về số lược view hệ thống
	 * @param
	 * @return int
	 */
	
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

	/**
	 * Trả về số người đang online trên hệ thống
	 * @param
	 * @return int
	 */
	
	public int getOnline() {
		return sessions.size();
	}
	
}