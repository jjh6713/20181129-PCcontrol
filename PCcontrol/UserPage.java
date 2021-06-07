package PCcontrol;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserPage extends JFrame{

	public UserPage(pc_member info) {

		UserPage_conn_socket socket = new UserPage_conn_socket(info);
		
		setTitle("Test customer Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		jp1.setLayout(new GridLayout(2,2));
		jp2.setLayout(new GridLayout(2,2));
		
		JLabel idLabel = new JLabel(info.getMember_id());
		JLabel costLabel = new JLabel("이용 시간:");
		JLabel showPayment = new JLabel(info.getMember_cost());
		
		JButton logoutBtn = new JButton("로그아웃");
		JButton orderBtn = new JButton("물품주문");
		JButton updateBtn = new JButton("정보수정");
		JButton chatBtn = new JButton("문의");

		
		jp1.add(new JLabel("아이디:"));
		jp1.add(idLabel);
		jp1.add(costLabel);
		jp1.add(showPayment);
		
		jp2.add(updateBtn);
		jp2.add(chatBtn);
		jp2.add(orderBtn);
		jp2.add(logoutBtn);
		
		cp.add(jp1,"Center");
		cp.add(jp2,"South");
		
		new getTime(showPayment,info.getMember_id()).start();
		
		setVisible(true);
		setSize(400,300);
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UserPage_update(info);
			}
		}); // 갱신
		

		chatBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UserPage_chat(info,socket);
			}
		}); // 문의
		

		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UserPage_order(info,null);
			}
		}); // 주문
		

		logoutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new LogoutPage(info.getMember_id(),costLabel.getText());
			}
		}); // 로그 아웃
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}


