package PCcontrol;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ManagerPage_costUpdate extends JFrame{
	
	public ManagerPage_costUpdate(String name) {
		// TODO Auto-generated constructor stub
		
		setTitle("Test customer Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		jp1.setLayout(new GridLayout(2,2,5,5));
		jp2.setLayout(new GridLayout(2,2,10,10));
		
		JLabel idLabel = new JLabel(name);
		
		JButton cost30Btn = new JButton("30분 충전");
		JButton cost1Btn = new JButton("1시간 충전");
		JButton cost2Btn = new JButton("2시간 충전");
		JButton cost10Btn = new JButton("10분 감소");
		
		jp1.add(new JLabel("아이디: "));
		jp1.add(idLabel);
		jp1.add(new JLabel("시간 충전 선택"));
			
		jp2.add(cost30Btn);
		jp2.add(cost1Btn);
		jp2.add(cost2Btn);
		jp2.add(cost10Btn);
		
		cp.add(jp1,"North");
		cp.add(jp2,"Center");
		
		setVisible(true);
		setSize(400,300);

	      cost30Btn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
	            ManagerPage_method.updateCost(name,"30");
	            try {
	               Connection conn = ConnectionDb.getConnection();                  
	               String sql = "INSERT INTO pc_sale values(null,?,?,?,?)";
	               PreparedStatement pstmt = conn.prepareStatement(sql);
	               pstmt.setString(1, name);
	               pstmt.setString(2, "30min charged");
	               pstmt.setString(3, "500won");
	               pstmt.setString(4, "a");
	               pstmt.executeUpdate();
	               
	               ConnectionDb.close(pstmt);
	               ConnectionDb.close(conn);
	               System.out.println("log insert ok!!");
	               JOptionPane.showMessageDialog(null, "충전완료^^");
	            } catch (SQLException e1) {
	               // TODO Auto-generated catch block
	               System.out.println(e1.getMessage());
	            }
	         }
	      });
	      cost1Btn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
	            ManagerPage_method.updateCost(name,"60");
	            try {
	               Connection conn = ConnectionDb.getConnection();                  
	               String sql = "INSERT INTO pc_sale values(null,?,?,?,?)";
	               PreparedStatement pstmt = conn.prepareStatement(sql);
	               pstmt.setString(1, name);
	               pstmt.setString(2, "1hour charged");
	               pstmt.setString(3, "1000won");
	               pstmt.setString(4, "a");
	               pstmt.executeUpdate();
	               
	               ConnectionDb.close(pstmt);
	               ConnectionDb.close(conn);
	               System.out.println("log insert ok!!");
	               JOptionPane.showMessageDialog(null, "충전완료^^");
	            } catch (SQLException e1) {
	               // TODO Auto-generated catch block
	               System.out.println(e1.getMessage());
	            }
	         }
	      });
	      cost2Btn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
	            ManagerPage_method.updateCost(name,"120");
	            try {
	               Connection conn = ConnectionDb.getConnection();                  
	               String sql = "INSERT INTO pc_sale values(null,?,?,?,?)";
	               PreparedStatement pstmt = conn.prepareStatement(sql);
	               pstmt.setString(1, name);
	               pstmt.setString(2, "2hour charged");
	               pstmt.setString(3, "2000won");
	               pstmt.setString(4, "a");
	               pstmt.executeUpdate();
	               
	               ConnectionDb.close(pstmt);
	               ConnectionDb.close(conn);
	               System.out.println("log insert ok!!");
	               JOptionPane.showMessageDialog(null, "충전완료^^");
	            } catch (SQLException e1) {
	               // TODO Auto-generated catch block
	               System.out.println(e1.getMessage());
	            }
	         }
	      });
	      cost10Btn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
	            ManagerPage_method.updateCost(name,"10");
	         }
	      });
	}
}
