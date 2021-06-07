package PCcontrol;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.fabric.xmlrpc.base.Data;

public class getTime2 extends Thread{

	JLabel target1;	//시간 변화
	String name;
	
	public static ArrayList<pc_member> getCost(){
		ArrayList<pc_member> temp = new ArrayList<pc_member>();
		try {
			Connection conn = ConnectionDb.getConnection();
			String sql = "SELECT member_id,member_cost FROM pc_member";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pc_member temp2 = new pc_member();
				temp2.setMember_id(rs.getString(1));
				temp2.setMember_cost(rs.getString(2));
				
				temp.add(temp2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	public getTime2(JLabel t,String n)
	{
	  target1 = t;
      name = n;
	}
	
	@Override
	public void run() {
		while(true) {
			ArrayList<pc_member> temp = getCost();
			int i = 0;
			while(i<temp.size()) {
				if((temp.get(i).getMember_id()).equals(name)) {
					break;
				}
			}
			target1.setText("남은 시간:"+temp.get(i).getMember_cost());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("실패");
			}
		}
	}
	
}
