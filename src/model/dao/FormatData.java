package model.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * FormatData.java
 *
 * Version 1.0
 *
 * Date: 04-01-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 04-01-2016        	NhanHV          Create
 */

public class FormatData {
	
	/**
	 * Format data đầu vào
	 * @param data
	 * @return String
	 */
	
	public static String FormatInputData(String data) {
		try {
			if (data != null) {
				return data.replace("'", "!@1").replace("--", "!@2").replace(
						"\\", "!@3").replace(";", "!@4");
			} else {
				return "";
			}
		} catch (Exception e) {
			return data;
		}
	}

	/**
	 * Format data đầu ra
	 * @param data
	 * @return String
	 */
	
	public static String FormatOutputData(String data) {
		try {
			if (data != null) {
				return data.replace("!@1", "'").replace("!@2", "--").replace(
						"!@3", "\\").replace("!@4", ";");
			} else {
				return "";
			}
		} catch (Exception e) {
			return data;
		}
	}

	/**
	 * Chuẩn hóa bảo mật MD5
	 * @param msg
	 * @return String
	 */
	
	public static String FormatMD5(String msg) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(msg.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return sb.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * Format data sang UTF-8
	 * @param isoString
	 * @return String
	 */
	
	public static String toUTF8(String isoString) {
		String utf8String = null;
		try {
			if (isoString != null) {
				byte[] stringByteISO = isoString.getBytes("ISO-8859-1");
				utf8String = new String(stringByteISO, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			utf8String = isoString;
		} catch (Exception e) {
			utf8String = isoString;
		}
		return utf8String;
	}

	//Format date trước insert
	public static String FormatDate(String date){
		String result = "";
		try {
			String list[] = date.split("-");
			result = list[2] +"-"+ list[1] +"-"+ list[0];
		} catch (Exception e){
			return "";
		}
		return result;
	}
}
