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
import java.util.ArrayList;

import PCcontrol.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class LoginPage extends JFrame{
	public LoginPage() {
		Container cp = getContentPane();
		JPanel jp1 = new JPanel();//�Է�â �ǳ�
		JPanel jp2 = new JPanel();//��ư �ǳ�
		
		cp.setLayout(new BorderLayout());
		
		JTextField idField = new JTextField();//�α���
		JTextField passField = new JTextField();//pwd
		JButton loginBtn = new JButton("�α���");
		JButton registerBtn = new JButton("ȸ������");
		JLabel loginTitle = new JLabel("TEST PC��"); // pc�� Ÿ��Ʋ
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		jp1.setLayout(new GridLayout(7,1));
		jp1.add(new JLabel("ID "));
		jp1.add(idField);
		jp1.add(new JLabel("PW "));
		jp1.add(passField);
		
		jp2.add(loginBtn);
		jp2.add(registerBtn);
		
		cp.add(loginTitle,BorderLayout.NORTH);
		cp.add(jp1,BorderLayout.CENTER);
		cp.add(jp2,BorderLayout.SOUTH);
			
		//�α���
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//manager�� �ϴ� �Ŵ��� â ����
				if(idField.getText().equals("manager") && passField.getText().equals("1234")){
		            JOptionPane.showMessageDialog(null, "Welcome Manager");
					dispose();
		            new ManagerPage();
		            // �α��� �����̶�� �Ŵ���â �ٿ��
				}
				
				//db�̿� �α���üũ �����ϰ� ����
				else if(LoginMethod.loginCheck(idField.getText(),passField.getText())&&LoginMethod.costCheck(idField.getText())) {
		            JOptionPane.showMessageDialog(null, "Welcome Customer");
		            pc_member info = new pc_member();
		            info = LoginMethod.loginInfo(idField.getText());
					dispose();
		            new UserPage(info);
				}else {
		            JOptionPane.showMessageDialog(null, "�α��� ���� / ���� ������ �����Դϴ�. ");
				}
				
			}
		});
		
		//ȸ������
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new RegisterPage();
			}
		});

		setTitle("Test main login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400, 300);
		setVisible(true);
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginPage();
	}

}
