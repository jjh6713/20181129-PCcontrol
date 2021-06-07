package PCcontrol;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Vector;

import javax.naming.InterruptedNamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ManagerPage_user extends JFrame{

	public ManagerPage_user(){
		Container cp = getContentPane();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		cp.setLayout(new FlowLayout());
		jp1.setLayout(new GridLayout(3, 4,10,10));
		jp2.setLayout(new GridLayout(1, 3,10,10));
		
		Vector columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("PW");
		columnNames.add("이름");
		columnNames.add("나이");
		columnNames.add("전화번호");
		columnNames.add("이메일");
		columnNames.add("남은시간");

		Vector stock = getUser();

		JTable jTable = new JTable(stock, columnNames);
		JScrollPane jScollPane = new JScrollPane(jTable);
		
		JButton btnReturn = new JButton("돌아가기");
		JButton btnUpdate = new JButton("계정수정");
		JButton btnDelete = new JButton("계정삭제");
		JButton btnCost = new JButton("시간추가");
		
		JTextField idField = new JTextField(10);
		JTextField pwField = new JTextField(10);
		JTextField nameField = new JTextField(10);
		JTextField ageField = new JTextField(10);
		JTextField numField = new JTextField(10);
		JTextField mailField = new JTextField(10);
		
		
		jp1.add(new JLabel("ID"));
		jp1.add(idField);
		jp1.add(new JLabel("PW"));
		jp1.add(pwField);
		jp1.add(new JLabel("이름"));
		jp1.add(nameField);
		jp1.add(new JLabel("나이"));
		jp1.add(ageField);
		jp1.add(new JLabel("전화번호"));
		jp1.add(numField);
		jp1.add(new JLabel("이메일"));
		jp1.add(mailField);
		
		jp2.add(btnReturn);
		jp2.add(btnUpdate);
		jp2.add(btnDelete);
		jp2.add(btnCost);
		
		cp.add(jScollPane);
		cp.add(jp1);
		cp.add(jp2);
		
		jTable.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = jTable.getSelectedRow();
				String temp1 = (String) jTable.getValueAt(row, 0);
				String temp2 = (String) jTable.getValueAt(row, 1);
				String temp3 = (String) jTable.getValueAt(row, 2);
				String temp4 = (String) jTable.getValueAt(row, 3);
				String temp5 = (String) jTable.getValueAt(row, 4);
				String temp6 = (String) jTable.getValueAt(row, 5);
				idField.setText(temp1);
				pwField.setText(temp2);
				nameField.setText(temp3);
				ageField.setText(temp4);
				numField.setText(temp5);
				mailField.setText(temp6);
			}
		});
		
		btnReturn.addActionListener(new ActionListener() {
			//돌아가기
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ManagerPage();
				
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			//계정수정
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Connection conn = ConnectionDb.getConnection();
					String sql = "UPDATE pc_member SET member_pw = ?, member_name = ?, member_age = ?, member_Phone = ?, member_Mail = ?"
							+ " WHERE member_id = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pwField.getText());
					pstmt.setString(2, nameField.getText());
					pstmt.setString(3, ageField.getText());
					pstmt.setString(4, numField.getText());
					pstmt.setString(5, mailField.getText());
					pstmt.setString(6, idField.getText());
					
					pstmt.executeUpdate();

					ConnectionDb.close(pstmt);
					ConnectionDb.close(conn);
					System.out.println("update ok!!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("update fail!!");
				}
				//갱신 추가해야함
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			//계정삭제
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Connection conn = ConnectionDb.getConnection();
					String sql = "DELETE FROM pc_member WHERE member_id=?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, idField.getText());
					pstmt.executeUpdate();

					ConnectionDb.close(pstmt);
					ConnectionDb.close(conn);
					System.out.println("delete ok!!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("delete fail!!");
				}
				//갱신 추가해야함
			}
		});

		btnCost.addActionListener(new ActionListener() {
			//계정삭제
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_costUpdate(idField.getText());
			}
		});
	    setSize(600,600);
	    setTitle("유저관리");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);

	}
	
	public Vector getUser(){
		try {
			Vector stock1=new Vector(); //JTable 용으로 2차원 백터
			
			String id;
			String pw;
			String name;
			String age;
			String phone;
			String mail;
			String cost;
			
			Connection conn = ConnectionDb.getConnection();
			//유저 정보 id 로 정렬해서 가져옴
			String sql = "SELECT member_id,member_pw,member_name,member_age,member_Phone,member_Mail,member_cost FROM pc_member ORDER BY member_id ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				id = rs.getString("member_id");
				pw = rs.getString("member_pw");
				name = rs.getString("member_name");
				age = rs.getString("member_age");
				phone = rs.getString("member_Phone");
				mail = rs.getString("member_Mail");
				cost= rs.getString("member_cost");

				Vector stock2=new Vector();
				stock2.add(id);
				stock2.add(pw);
				stock2.add(name);
				stock2.add(age);
				stock2.add(phone);
				stock2.add(mail);
				stock2.add(cost);
				
				stock1.add(stock2);
			}

			ConnectionDb.close(pstmt);
			ConnectionDb.close(conn);
			ConnectionDb.close(rs);
			System.out.println("user get ok!!");
			return stock1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("user get fail!!");
			return null;
		}
	}
}
