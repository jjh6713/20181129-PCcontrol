package PCcontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RegisterMethod {
	
	//가입 메소드
	public static void registerCheck(String id, String pw, String name, String age, String num, String mail) {
		try {
			if(overlapCheck(id)) {
				Connection conn = ConnectionDb.getConnection(); //db연결
				String sql = "insert into pc_member values(?, ?, ?, ?, ?, ?, ?)"; //sql문
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setString(4, age);
				pstmt.setString(5, num);
				pstmt.setString(6, mail);
				pstmt.setString(7, "0"); //cost
					
				pstmt.executeUpdate();
				
				ConnectionDb.close(pstmt);
				ConnectionDb.close(conn);
				JOptionPane.showMessageDialog(null, "가입 성공!! 환영합니다^^");	
				System.out.println("DB입력 성공!!");
			}else {
				JOptionPane.showMessageDialog(null, "중복되는 ID입니다.");	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("DB입력 실패!!");
		}
	}
	
	//중복 검사 메소드
    public static boolean overlapCheck(String id) {
    	Connection conn = null;
    	PreparedStatement stmt = null;
		ResultSet rs = null;
    	
    	try {
        	conn = ConnectionDb.getConnection();
        	String sql = "SELECT member_id FROM pc_member";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("member_id").equals(id)) {//만약 같은 중복되는 아이디가있다면!!
					System.out.println("중복!!");
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("중복 체크 fail!!");
		}
		System.out.println("중복 계정 없습니다!!");
    	return true;
    }
    
    //유저 정보 갱신
    public static void registerUpdate(String id, String pw, String name, String age, String phone, String mail) {
		try {
			Connection conn = ConnectionDb.getConnection();
			String sql = "UPDATE pc_member SET member_pw = ?, member_name = ?, member_age = ?, member_Phone = ?, member_Mail = ?"
					+ " WHERE member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, age);
			pstmt.setString(4, phone);
			pstmt.setString(5, mail);
			pstmt.setString(6, id);
			
			pstmt.executeUpdate();

			ConnectionDb.close(pstmt);
			ConnectionDb.close(conn);
			JOptionPane.showMessageDialog(null, "수정 되었습니다!");
			System.out.println("update ok!!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("update fail!!");
		}
	}
}
