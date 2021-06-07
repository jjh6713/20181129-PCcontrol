package PCcontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RegisterMethod {
	
	//���� �޼ҵ�
	public static void registerCheck(String id, String pw, String name, String age, String num, String mail) {
		try {
			if(overlapCheck(id)) {
				Connection conn = ConnectionDb.getConnection(); //db����
				String sql = "insert into pc_member values(?, ?, ?, ?, ?, ?, ?)"; //sql��
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
				JOptionPane.showMessageDialog(null, "���� ����!! ȯ���մϴ�^^");	
				System.out.println("DB�Է� ����!!");
			}else {
				JOptionPane.showMessageDialog(null, "�ߺ��Ǵ� ID�Դϴ�.");	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("DB�Է� ����!!");
		}
	}
	
	//�ߺ� �˻� �޼ҵ�
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
				if(rs.getString("member_id").equals(id)) {//���� ���� �ߺ��Ǵ� ���̵��ִٸ�!!
					System.out.println("�ߺ�!!");
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("�ߺ� üũ fail!!");
		}
		System.out.println("�ߺ� ���� �����ϴ�!!");
    	return true;
    }
    
    //���� ���� ����
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
			JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�!");
			System.out.println("update ok!!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("update fail!!");
		}
	}
}
