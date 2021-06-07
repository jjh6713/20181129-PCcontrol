package PCcontrol;

import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerPage_method {
	
	//��� �� �ڽ�Ʈ �������� �޼ҵ�
	public static ArrayList<pc_member> getMember(){
		ArrayList<pc_member> temp = new ArrayList<pc_member>();
		
		try {
			Connection conn = ConnectionDb.getConnection();
			String sql = "SELECT member_id,member_name,member_cost FROM pc_member";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pc_member temp2 = new pc_member();
				temp2.setMember_id(rs.getString(1));
				temp2.setMember_name(rs.getString(2));
				temp2.setMember_cost(rs.getString(3));
				
				temp.add(temp2);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	//�ڽ�Ʈ ���� �޼ҵ�
	public static void updateCost(String name,String cost){
		try {
			Connection conn = ConnectionDb.getConnection();
			
			String sql = "SELECT member_cost FROM pc_member WHERE member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			String costtemp = "";
			
			while(rs.next()) {
				costtemp = rs.getString(1);//������ �ִ� �ð� ������
			}		
			
			sql = "UPDATE pc_member SET member_cost = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			int sum = 0;

			sum = Integer.parseInt(cost)+Integer.parseInt(costtemp); //�׿ܴ� ���ϱ�			

			pstmt.setString(1, Integer.toString(sum));
			pstmt.setString(2, name);
	
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
