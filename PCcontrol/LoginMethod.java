package PCcontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginMethod {

	//로그인 확인
	public static boolean loginCheck(String id, String pw) {
		boolean ck = false;
		try {
			Connection conn = ConnectionDb.getConnection();
			String sql = "SELECT member_pw FROM pc_member WHERE member_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String pwCheck = rs.getString("member_pw");
				if(pw.equals(pwCheck)) {
					ck = true;
				}else {
					ck = false;
				}
			}
			
			ConnectionDb.close(rs);
			ConnectionDb.close(stmt);
			ConnectionDb.close(conn);
			System.out.println("loginck ok!!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loginck false!!");
		} 
		
		return ck;
	}
	

	//선불 확인
	public static boolean costCheck(String id) {
		boolean ck = false;
		try {
			Connection conn = ConnectionDb.getConnection();
			String sql = "SELECT member_cost FROM pc_member WHERE member_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String cost = rs.getString("member_cost");
				if(cost.equals("0")) {
					ck = false;
				}else {
					ck = true;
				}
			}
			
			ConnectionDb.close(rs);
			ConnectionDb.close(stmt);
			ConnectionDb.close(conn);
			System.out.println("loginck ok!!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loginck false!!");
		} 
		
		return ck;
	}
	
	//로그인 정보
	public static pc_member loginInfo(String id){
		pc_member temp = new pc_member();
		try {
			Connection conn = ConnectionDb.getConnection();
			String sql = "SELECT * FROM pc_member WHERE member_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				temp.setMember_id(rs.getString("member_id"));
				temp.setMember_pw(rs.getString("member_pw"));
				temp.setMember_name(rs.getString("member_name"));
				temp.setMember_age(rs.getString("member_age"));
				temp.setMember_Phone(rs.getString("member_Phone"));
				temp.setMember_Mail(rs.getString("member_Mail"));
				temp.setMember_cost(rs.getString("member_cost"));
			}
			
			ConnectionDb.close(rs);
			ConnectionDb.close(stmt);
			ConnectionDb.close(conn);
			System.out.println("info ok!!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("info false!!");
		} 
		
		return temp;
	}
	
}
