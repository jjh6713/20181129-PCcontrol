package PCcontrol;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;


public class LogoutPage {

	public LogoutPage(String id,String cost) {
		// TODO Auto-generated constructor stub
		try {
			Connection conn = ConnectionDb.getConnection();
			String sql = "UPDATE pc_member SET member_cost = ? WHERE member_id = ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, cost);
			stmt.setString(2, id);
			
			stmt.executeUpdate();
			
			ConnectionDb.close(stmt);
			ConnectionDb.close(conn);
			
			new LoginPage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("cost update fail");
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
