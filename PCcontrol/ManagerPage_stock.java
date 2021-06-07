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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ManagerPage_stock extends JFrame{
	String img;
	
	public ManagerPage_stock(){
		Container cp = getContentPane();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		cp.setLayout(new FlowLayout());
		jp1.setLayout(new GridLayout(1, 2,10,10));
		jp2.setLayout(new GridLayout(5, 2));
		
		Vector columnNames = new Vector<String>();
		columnNames.add("��ǰ�̸�");
		columnNames.add("��ǰ����");
		columnNames.add("��ǰ����");
		columnNames.add("��ǰ���");

		Vector stock = getStock();

		JTable jTable = new JTable(stock, columnNames);
		JScrollPane jScollPane = new JScrollPane(jTable);
		
		JTextField nameField = new JTextField(25);
		JTextField categoryField = new JTextField(25);
		JTextField priceField = new JTextField(25);
		JTextField stockField = new JTextField(25);
		JButton btn1 = new JButton("���ư���");
		JButton btnAdd = new JButton("����߰�");
		JButton btnUpdate = new JButton("������");
		JButton btnDelete = new JButton("������");
		JButton btnFile = new JButton("�̹���");

		
		//������ �� ���������� �̾ƿ;���
		
		jp2.add(new JLabel("��ǰ �̸�"));
		jp2.add(nameField);
		jp2.add(new JLabel("��ǰ ����"));
		jp2.add(categoryField);
		jp2.add(new JLabel("��ǰ ����"));
		jp2.add(priceField);
		jp2.add(new JLabel("��ǰ ���"));
		jp2.add(stockField);
		jp2.add(new JLabel("��ǰ �̹���"));
		jp2.add(btnFile);
		
		
		jp1.add(btn1);
		jp1.add(btnAdd);
		jp1.add(btnUpdate);
		jp1.add(btnDelete);
		
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
				
				nameField.setText(temp1);
				categoryField.setText(temp2);
				priceField.setText(temp3);
				stockField.setText(temp4);
			}
		});
		
		btn1.addActionListener(new ActionListener() {
			//���ư���
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ManagerPage();
			}
		});
		

		btnAdd.addActionListener(new ActionListener() {
			//����߰�
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			try {
					Connection conn = ConnectionDb.getConnection();						
					String sql = "insert into pc_stock(stock_name, stock_category, stock_price, stock_stock, stock_img) values(?, ?, ?, ?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, nameField.getText());
					pstmt.setString(2, categoryField.getText());
					pstmt.setString(3, priceField.getText());
					pstmt.setString(4, stockField.getText());
					pstmt.setString(5, img);
					pstmt.executeUpdate();
					
					ConnectionDb.close(pstmt);
					ConnectionDb.close(conn);
					System.out.println("stock insert ok!!");
					JOptionPane.showMessageDialog(null, "��� �Է� �Ϸ�^^");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
				dispose();
				new ManagerPage_stock();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Connection conn = ConnectionDb.getConnection();						
					String sql = "UPDATE pc_stock SET stock_category = ?, stock_price = ?, stock_stock = ? WHERE stock_name = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, categoryField.getText());
					pstmt.setString(2, priceField.getText());
					pstmt.setString(3, stockField.getText());
					pstmt.setString(4, nameField.getText());
					pstmt.executeUpdate();
					
					ConnectionDb.close(pstmt);
					ConnectionDb.close(conn);
					System.out.println("stock update ok!!");
					JOptionPane.showMessageDialog(null, "��� ���� �Ϸ�^^");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
				dispose();
				new ManagerPage_stock();
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Connection conn = ConnectionDb.getConnection();						
					String sql = "DELETE FROM pc_stock WHERE stock_name = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, nameField.getText());
					pstmt.executeUpdate();
					
					ConnectionDb.close(pstmt);
					ConnectionDb.close(conn);
					System.out.println("stock delete ok!!");
					JOptionPane.showMessageDialog(null, "��� ���� �Ϸ�^^");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
				dispose();
				new ManagerPage_stock();
			}
		});

		btnFile.addActionListener(new ActionListener() {
			JFileChooser cs = new JFileChooser();
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int ck = cs.showOpenDialog(null);
				if(ck!=JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�.");
					return;
				}
				String filePath = cs.getSelectedFile().getPath();
				img = filePath;
			}
		});
		
	    setSize(600,650);
	    setTitle("���");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);

	}
	
	
	public Vector getStock(){
		try {
			Vector stock1=new Vector(); //JTable ������ 2���� ����
			
			String name;
			String category;
			String price;
			String stock;
			
			Connection conn = ConnectionDb.getConnection();						
			String sql = "SELECT stock_name,stock_category,stock_price,stock_stock FROM pc_stock ORDER BY stock_num ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				//category �־���� 
				name = rs.getString("stock_name");
				category = rs.getString("stock_category");
				price = rs.getString("stock_price");
				stock = rs.getString("stock_stock");

				Vector stock2=new Vector();
				stock2.add(name);
				stock2.add(category);
				stock2.add(price);
				stock2.add(stock);
				
				stock1.add(stock2);
			}

			ConnectionDb.close(pstmt);
			ConnectionDb.close(conn);
			ConnectionDb.close(rs);
			System.out.println("stock get ok!!");
			return stock1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("stock get fail!!");
			return null;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ManagerPage_stock();
	}

}
