package PCcontrol;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class RegisterPage extends JFrame{
	public RegisterPage(){
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(7, 2, 5, 10));
		
		JTextField idField = new JTextField(30);//아이디
		JTextField passField = new JTextField(30);//패스워드
		JTextField nameField = new JTextField(30);//이름
		JTextField ageField = new JTextField(30);//나이
		JTextField phoneField = new JTextField(30);//폰번호
		JTextField mailField = new JTextField(30);//메일
		
		JButton insertBtn = new JButton("입력 완료");
		JButton returnBtn = new JButton("돌아 가기");
		
		cp.add(new JLabel("아이디"));
		cp.add(idField);
		cp.add(new JLabel("패스워드"));
		cp.add(passField);
		cp.add(new JLabel("이름"));
		cp.add(nameField);
		cp.add(new JLabel("나이"));
		cp.add(ageField);
		cp.add(new JLabel("폰번호"));
		cp.add(phoneField);
		cp.add(new JLabel("메일"));
		cp.add(mailField);//null 가능
		
		cp.add(insertBtn);
		cp.add(returnBtn);
		
		//입력완료
		insertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(idField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "ID를 입력해 주세요");
				}else if(passField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요");	
				}else if(nameField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "이름을 입력해 주세요");	
				}else if(ageField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "나이를 입력해 주세요");	
				}else if(phoneField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "핸드폰번호를 입력해 주세요");	
				}else {//비어있지 않다면
					String id = idField.getText();
					String pw = passField.getText();
					String name = nameField.getText();
					String age = ageField.getText();
					String num = phoneField.getText();
					String mail = mailField.getText();//null허용!!
					
					RegisterMethod.registerCheck(id,pw,name,age,num,mail);
					dispose();
					new LoginPage();
				}
			}
		});
		
		//돌아가기
		returnBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new LoginPage();
			}
		});

		setTitle("Register Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300,400);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
