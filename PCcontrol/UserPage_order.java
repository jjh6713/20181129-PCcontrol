package PCcontrol;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UserPage_order extends JFrame{
	public UserPage_order(pc_member info,String category) {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout()); 

	    JPanel jp1 = new JPanel();	 
		JPanel jp2 = new JPanel();	 
		jp1.setLayout(new BorderLayout());
		
		JPanel jp3 = new JPanel(); 
		JPanel jptest = new JPanel();
		JScrollPane jp4 = new JScrollPane(jptest); 
		jp4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);;
		
		jp3.setLayout(new GridLayout(5,0));
		
		JButton btn1 = new JButton("상품");
		JButton btn2 = new JButton("라면");
		JButton btn3 = new JButton("햄버거");
		JButton btn4 = new JButton("과자");
		JButton btn5 = new JButton("음료");
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserPage_order(info,null);

			}
		});
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserPage_order(info,"ramen");

			}
		});
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserPage_order(info,"hamburger");

			}
		});
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserPage_order(info,"snack");

			}
		});
		btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserPage_order(info,"drink");

			}
		});
		
		jp3.add(btn1);
		jp3.add(btn2);
		jp3.add(btn3);
		jp3.add(btn4);
		jp3.add(btn5);
	
		jptest.setLayout(new GridLayout(4,12,15,15));
		
		jp1.add(jp3,BorderLayout.WEST);
		jp1.add(jp4,BorderLayout.CENTER);

		
		try {
			Connection conn = ConnectionDb.getConnection();
			String sql = "SELECT * FROM pc_stock";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				ItemMethod a = new ItemMethod (rs.getString("stock_name"), rs.getString("stock_category"), rs.getString("stock_price"),rs.getString("stock_img"));
				JPanel temp = (JPanel) a.getPanel();
				
				if(a.getCategory().equals(category) || category==null)jptest.add(temp);
			}
			ConnectionDb.close(rs);
			ConnectionDb.close(stmt);
			ConnectionDb.close(conn);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		} 
		
		jp2.setLayout(new BorderLayout());
		
		JPanel jp5 = new JPanel();
		
		jp5.add(new JLabel("상품이름 "));
		jp5.add(new JLabel("  상품가격  "));
		jp5.add(new JLabel("  상품갯수  "));
		jp5.add(new JLabel("  총액"));
		
		JPanel jp6 = new JPanel();
		JButton okBtn = new JButton("주문하기");
		jp6.add(okBtn);

		jp2.add(jp5,BorderLayout.WEST);
		jp2.add(jp6,BorderLayout.EAST);
		
		jp1.setSize(700,400);
		jp2.setSize(700,100);
		
		cp.add(jp1,BorderLayout.CENTER);
		cp.add(jp2,BorderLayout.SOUTH);
		
		setTitle("Test User order Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(700, 500);
		setVisible(true);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UserPage_order(new pc_member(),null);
	}

}
