package PCcontrol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class ConnectionDb {
	public static Connection getConnection(){
		Connection conn = null;
		try {
			//JDBC����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");
			//useSSL �Ƚ��ָ� warn �ߴµ� ���࿡�� ū ������ ����. ã�ƺ��� ssl ����� ����? �ϴ� �ǹ��� �����ε�
			String url = "jdbc:mysql://localhost:3306/pccontrol?useSSL=false"; //��Ʈ�� ���� �̿뵥���ͺ��̽��� 
			String userId ="root";
			String userPw ="root";
			conn = DriverManager.getConnection(url,userId,userPw); //Ŀ�ؼ� ��ü ����
			System.out.println("Ŀ�ؼ� ����");
		
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Ŀ�ؼ� ����");
		}
		return conn;
	}
	//connection close
	public static void close(Connection conn) {
		try {
			if(conn!=null) {conn.close();}
		} catch(Exception e) {
			System.out.println("Connection close");
		}
	}
	//preparedstatement close
	public static void close(PreparedStatement stmt) {
		try {
			if(stmt!=null) {stmt.close();}
		} catch(Exception e) {
			System.out.println("PreparedStatement close");
		}
	}
	//resultset close
	public static void close(ResultSet rs) {
		try {
			if(rs!=null) {rs.close();}
		} catch(Exception e) {
			System.out.println("Connection close");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
