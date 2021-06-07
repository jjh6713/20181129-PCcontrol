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
		JLabel costLabel = new JLabel("�̿� �ð�:");
		JLabel showPayment = new JLabel(info.getMember_cost());
		
		JButton logoutBtn = new JButton("�α׾ƿ�");
		JButton orderBtn = new JButton("��ǰ�ֹ�");
		JButton updateBtn = new JButton("��������");
		JButton chatBtn = new JButton("����");

		
		jp1.add(new JLabel("���̵�:"));
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
		}); // ����
		

		chatBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UserPage_chat(info,socket);
			}
		}); // ����
		

		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UserPage_order(info,null);
			}
		}); // �ֹ�
		

		logoutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new LogoutPage(info.getMember_id(),costLabel.getText());
			}
		}); // �α� �ƿ�
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}


