package com.javalec.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javalec.mysite.common.JDBCUtil;
import com.javalec.mysite.vo.UserVO;

@Repository
public class UserDAO {

	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	private ResultSet rs = null ;
	
	
	
	private String userInsert = "INSERT INTO USERS (ID, PASSWORD, NAME, ROLE) VALUES (?,?,?,?)"; 
	private String userUpdate = "UPDATE USERS SET PASSWORD=?, NAME=?, ROLE=? WHERE ID=?" ;
	private String userDelete = "DELET USERS WHERE ID=?" ;
	private String userGet = " SELECT * FROM USERS WHERE ID=? AND PASSWORD=?";
	private String user_List = "SELECT * FROM USERS" ;
	
	@Autowired
	private DataSource ds ;
	
	public UserVO getUser(UserVO vo) {
		UserVO user = null ;
		System.out.println("로그인 확인 ");
		try {
			conn = ds.getConnection() ;
			pstmt = conn.prepareStatement(userGet) ;
			pstmt.setString(1,  vo.getId());
			pstmt.setString(2,  vo.getPassword());
			
			rs = pstmt.executeQuery() ;
			
			if(rs.next()) {
				user = new UserVO() ;
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return user ;
	}
	
}
