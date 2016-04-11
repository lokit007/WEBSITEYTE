package model.dao;

import java.sql.*;

/**
 * TaiKhoanDAO.java
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

public class DataBaseDAO {

	private Connection con;
	private Statement stm;
	private int page = 1;
	private int maxPage = 1;
	private String menu = "";
	private int record = 5;

	/**
	 * Khởi tạo Connection
	 * @param 
	 * @return 
	 */
	
	public DataBaseDAO() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager
					.getConnection(
							"jdbc:sqlserver://localhost:1433;useUnicode=true;characterEncoding='UTF-8';databaseName=DBDATN",
							"sa", "12345678");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (con != null) {
			System.out.println("Connection is established !");
		} else
			System.out.println("Connection isn't established !");
		try {
			stm = con.createStatement();
			System.out.println("Statement is established !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Đóng Connection
	 * @param 
	 * @return 
	 */
	
	public void closeConnection() {
		try {
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Truy vấn csdl
	 * @param sql
	 * @return ResultSet
	 */
	
	public ResultSet getResultSet(String sql) {
		try {
			return stm.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn : " + sql);
			return null;
		}
	}

	/**
	 * Cập nhật csdl 
	 * @param sql
	 * @return boolean
	 */
	
	public boolean updateData(String sql) {
		try {
			return stm.executeUpdate(sql) != 0;
		} catch (Exception e) {
			System.out.println("Execute update error!");
			return false;
		}
	}
	
	/**
	 * Cập nhật csdl dùng CallableStatement
	 * @param sql
	 * @return boolean
	 * @throws SQLException 
	 */
	
	public CallableStatement getCallableStatement(String sql) throws SQLException {
		return con.prepareCall(sql);
	}
	
	/**
	 * Tạo menu
	 * @param url, page, sql
	 * @return String menu
	 */
	
	public String createMenu(String url, int page, String sql){
		String menu = "<ul class='pagination pagination-sm'>";
		this.page = page;
		int nRecord = countRecord(sql);
		int start = 1, end = 0;
		this.maxPage = (nRecord-1)/this.record+1;
		start = (this.page-1)/5*5+1;
		end = start;
		menu += "<li><a title='Click nhập trang' id='statePage' onclick='Chen("+this.page+","+this.maxPage+");'> Trang "
				+this.page+"/"+this.maxPage+" </a></li>";
		if((start-1)>0) menu += "<li><a href='"+url+"&page="+(start-1)+"'>&laquo;</a></li>";
		else menu += "<li class='disabled'><a>&laquo;</a></li>";
		for(int i=0; i<5 && start<=this.maxPage; i++, start++){
			if(start==this.page) menu += "<li class='active'><a>"+start+"</a></li>";
			else menu += "<li><a href='"+url+"&page="+start+"'>"+start+"</a></li>";
		}
		if((end+5)<this.maxPage) menu += "<li><a href='"+url+"&page="+(end+5)+"'>&raquo;</a></li>";
		else menu += "<li class='disabled'><a>&raquo;</a></li>";
		menu += "</ul>";
		this.menu = menu; 
		return menu; 
	}

	private int countRecord(String sql){
		ResultSet rs = getResultSet("select count(*) from ( "+sql+" ) as tableCount");
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			return 0;
		}
		return 0;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getRecord() {
		return record;
	}

	public void setRecord(int record) {
		this.record = record;
	}

	public static void main(String[] args) {
		DataBaseDAO db = new DataBaseDAO();
		try {
			CallableStatement cal = db.getCallableStatement("{call capNhatDichVu(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			//Cập nhật tham số đầu vào
			cal.setString(1, "1");
			cal.setString(2, "Dịch vụ mới");
			cal.setString(3, "Mô tả");
			cal.setString(4, "1");
			cal.setString(5, "Nội dung");
			cal.setString(6, "Hình");
			cal.setString(7, "Taikhoan02");
			cal.setString(8, "Cung cấp");
			cal.setString(9, "012");
			cal.setString(10, "email");
			cal.setString(11, "2016-02-10");
			cal.setString(12, "2016-02-15");
			//Cập nhật tham số đầu ra
			cal.registerOutParameter(13, java.sql.Types.INTEGER);
			//Thực thi
			cal.executeUpdate();
			//Lấy kết quả trả về
			int result = cal.getInt(13);
			if(result > -1) {
				System.out.println("Cập nhật thành công");
			}
			System.out.println("Cập nhật thất bại");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
