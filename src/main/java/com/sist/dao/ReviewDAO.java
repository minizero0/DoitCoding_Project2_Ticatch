package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.ReviewVO;

public class ReviewDAO {
	//싱글톤 방식
	private static ReviewDAO reviewDAO;
	private ReviewDAO() {	}
	public static ReviewDAO getInstance() {
		if(reviewDAO == null) {
			reviewDAO = new ReviewDAO();
		}
		return reviewDAO;
	}
	
	
	//후기 추가
	public int insertReview(ReviewVO r) {
		int re = -1;
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
		
	//후기 수정
	public int updateReview(ReviewVO r) {
		int re = -1;
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//후기 삭제
	public int deleteReview(int reviewid) {
		int re = -1;
		String sql = "delete review where reviewid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewid);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//전체 후기 목록 출력
	public ArrayList<ReviewVO> listReview(){
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		String sql = "select * from review";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ReviewVO r = new ReviewVO();
				r.setReviewid(rs.getInt("reviewid"));
				r.setCustid(rs.getString("custid"));
				r.setTicketid(rs.getInt("ticketid"));
				r.setScore(rs.getInt("score"));
				r.setReview_content(rs.getString("review_content"));
				list.add(r);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return list;
	}
	
	
	//특정 후기 목록 출력
	public ReviewVO findById(int reviewid) {
		ReviewVO r = null;
		String sql = "select * from review where reviewid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				r = new ReviewVO();
				r.setReviewid(rs.getInt("reviewid"));
				r.setCustid(rs.getString("custid"));
				r.setTicketid(rs.getInt("ticketid"));
				r.setScore(rs.getInt("score"));
				r.setReview_content(rs.getString("review_content"));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return r;
	}
}
