package PCcontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.fabric.xmlrpc.base.Data;

public class getTime extends Thread{

	String id = "";
	JLabel target1;	//시간 변화

	public static void updateCost(String name,String cost){
		try {
			Connection conn = ConnectionDb.getConnection();
			String sql = "UPDATE pc_member SET member_cost = ? WHERE member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cost);
			pstmt.setString(2, name);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int getCost(String userid){
		 int userCost = 0;
		try {
		     Connection conn = ConnectionDb.getConnection();
		     String sql = "SELECT member_cost FROM pc_member WHERE member_id = ?";
		     PreparedStatement pstmt = conn.prepareStatement(sql);
		     pstmt.setString(1, userid);
		     ResultSet rs = pstmt.executeQuery();

		     while(rs.next()) {
		    	 userCost = Integer.parseInt(rs.getString(1));
		     }
		} catch (SQLException e) {
		     // TODO Auto-generated catch block
		     System.out.println(e.getMessage());
		}
		return userCost;
	}
	
	
	public getTime(JLabel f, String id2)
	{
	  target1 = f;
	  id = id2;
	}
	@Override
	public void run() {
		int i;
		while(true) {
			i = getCost(id);
			target1.setText(Integer.toString(i));
			if(Integer.parseInt(target1.getText())>0) { 
				i--; 
				updateCost(id,Integer.toString(i));	
			}
			else if(Integer.parseInt(target1.getText())==0) { 
	            JOptionPane.showMessageDialog(null, "시간이 다되셨습니다.");
	            try {sleep(5000);} catch (InterruptedException e) {}
				System.out.println("No time");
				System.exit(0);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("실패");
			}
		}
	}
	
}
