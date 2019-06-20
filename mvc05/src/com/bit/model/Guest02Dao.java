package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Guest02Dao {
	// //////////////////////////////////////////////////////////////////
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String password = "hr";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// /////////////////////////////////////////////////////////////////
	// 생성자를 호출 할 때 마다 드라이브연결
	// 호출시 필드 초기화
	public Guest02Dao() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// /////////////////////////////////////////////////////////////////////
	public Connection getConnection() {
		return this.conn;
	}

	// /////////////////////////////////////////////////////////////////////
	public ArrayList<Guest02Dto> getList() {
		ArrayList<Guest02Dto> list = null;
		list = new ArrayList<Guest02Dto>();
		String sql = "select num, sub, unum, (select name from user02 where num=unum) as name, nalja, pay from guest02 order by num desc";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Guest02Dto bean = new Guest02Dto();
				bean.setNum(rs.getInt(1));
				bean.setSub(rs.getString(2));
				bean.setUnum(rs.getInt(3));
				bean.setName(rs.getString(4));
				bean.setNalja(rs.getDate(5));
				bean.setPay(rs.getInt(6));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public void addList(String sub, int unum, int pay) {
		String sql = "insert into guest02(num, sub, unum, nalja, pay) values (guest02_seq.nextval, ?, ?, sysdate, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sub);
			pstmt.setInt(2, unum);
			pstmt.setInt(3, pay);
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Guest02Dto getListOne(int num) {
		Guest02Dto bean = new Guest02Dto();
		String sql = "select num, sub, unum, (select name from user02 where num=unum) as name, nalja, pay from guest02 where num=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setSub(rs.getString(2));
				bean.setUnum(rs.getInt(3));
				bean.setName(rs.getString(4));
				bean.setNalja(rs.getDate(5));
				bean.setPay(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bean;
	}

	public int editOne(String sub, int pay, int num) {
		int result = 0;
		String sql = "update guest02 set sub=?, pay=? where num=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sub);
			pstmt.setInt(2, pay);
			pstmt.setInt(3, num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// /////////////////////////////////////////////////////////////////////////////

}
