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
		JPanel jp1 = new JPanel();//입력창 판넬
		JPanel jp2 = new JPanel();//버튼 판넬
		
		cp.setLayout(new BorderLayout());
		
		JTextField idField = new JTextField();//로그인
		JTextField passField = new JTextField();//pwd
		JButton loginBtn = new JButton("로그인");
		JButton registerBtn = new JButton("회원가입");
		JLabel loginTitle = new JLabel("TEST PC방"); // pc방 타이틀
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
			
		//로그인
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//manager은 일단 매니저 창 접속
				if(idField.getText().equals("manager") && passField.getText().equals("1234")){
		            JOptionPane.showMessageDialog(null, "Welcome Manager");
					dispose();
		            new ManagerPage();
		            // 로그인 성공이라면 매니져창 뛰우기
				}
				
				//db이용 로그인체크 간단하게 구현
				else if(LoginMethod.loginCheck(idField.getText(),passField.getText())&&LoginMethod.costCheck(idField.getText())) {
		            JOptionPane.showMessageDialog(null, "Welcome Customer");
		            pc_member info = new pc_member();
		            info = LoginMethod.loginInfo(idField.getText());
					dispose();
		            new UserPage(info);
				}else {
		            JOptionPane.showMessageDialog(null, "로그인 실패 / 저희 매장은 선불입니다. ");
				}
				
			}
		});
		
		//회원가입
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
