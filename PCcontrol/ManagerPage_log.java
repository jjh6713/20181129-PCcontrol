package PCcontrol;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ManagerPage_log extends JFrame{
	public ManagerPage_log(){
		Container cp = getContentPane();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		cp.setLayout(new FlowLayout());
		jp1.setLayout(new GridLayout(1, 2,10,10));
		jp2.setLayout(new GridLayout(5, 2));
		
		Vector columnNames = new Vector<String>();
		columnNames.add("판매번호");
		columnNames.add("구매자");
		columnNames.add("판매내역");
		columnNames.add("판매금액");
		columnNames.add("판매시간");
		
		Vector stock = getStock();

		JTable jTable = new JTable(stock, columnNames);
		JScrollPane jScollPane = new JScrollPane(jTable);
		
		cp.add(jScollPane);
		
	    setSize(600,650);
	    setTitle("판매내역");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);

	}
	
	public Vector getStock(){
		try {
			Vector stock1=new Vector(); //JTable 용으로 2차원 백터
			
			int sale_num;
			String sale_id;
			String sale_content;
			String sale_cost;
			String sale_date;
			
			Connection conn = ConnectionDb.getConnection();						
			String sql = "SELECT sale_num,sale_id,sale_content,sale_cost,sale_date FROM pc_sale ORDER BY sale_num ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				sale_num = rs.getInt("sale_num");
				sale_id = rs.getString("sale_id");
				sale_content = rs.getString("sale_content");
				sale_cost = rs.getString("sale_cost");
				sale_date= rs.getString("sale_date");

				Vector stock2=new Vector();
				stock2.add(sale_num);
				stock2.add(sale_id);
				stock2.add(sale_content);
				stock2.add(sale_cost);
				stock2.add(sale_date);
				
				stock1.add(stock2);
			}

			ConnectionDb.close(pstmt);
			ConnectionDb.close(conn);
			ConnectionDb.close(rs);
			System.out.println("log get ok!!");
			return stock1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("log get fail!!");
			return null;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ManagerPage_log();
	}

}
