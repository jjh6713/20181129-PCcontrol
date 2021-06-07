package PCcontrol;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class UserPage_update extends JFrame{
	public UserPage_update(pc_member info){
		String member_id = info.getMember_id();
		String member_pw = info.getMember_pw();
		String member_name = info.getMember_name();
		String member_age = info.getMember_age();
		String member_phone = info.getMember_Phone();
		String member_mail = info.getMember_Mail();
		
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(7, 2, 5, 10));
		
		
		JLabel idField = new JLabel(member_id);//���̵�
		JTextField passField = new JTextField(30);//�н�����
		JTextField nameField = new JTextField(30);//�̸�
		JTextField ageField = new JTextField(30);//����
		JTextField phoneField = new JTextField(30);//����ȣ
		JTextField mailField = new JTextField(30);//����
		
		passField.setText(member_pw);
		nameField.setText(member_name);
		ageField.setText(member_age);
		phoneField.setText(member_phone);
		mailField.setText(member_mail);
		
		JButton insertBtn = new JButton("���� �Ϸ�");
		JButton returnBtn = new JButton("���� ����");
		
		cp.add(new JLabel("���̵�"));
		cp.add(idField);
		cp.add(new JLabel("�н�����"));
		cp.add(passField);
		cp.add(new JLabel("�̸�"));
		cp.add(nameField);
		cp.add(new JLabel("����"));
		cp.add(ageField);
		cp.add(new JLabel("����ȣ"));
		cp.add(phoneField);
		cp.add(new JLabel("����"));
		cp.add(mailField);//null ����
		
		cp.add(insertBtn);
		cp.add(returnBtn);
		
		//�Է¿Ϸ�
		insertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(idField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "ID�� �Է��� �ּ���");
				}else if(passField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��� �ּ���");	
				}else if(nameField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ּ���");	
				}else if(ageField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "���̸� �Է��� �ּ���");	
				}else if(phoneField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "�ڵ�����ȣ�� �Է��� �ּ���");	
				}else {//������� �ʴٸ�
					String id = idField.getText();
					String pw = passField.getText();
					String name = nameField.getText();
					String age = ageField.getText();
					String phone = phoneField.getText();
					String mail = mailField.getText();//null���!!
					
					RegisterMethod.registerUpdate(id,pw,name,age,phone,mail);
					dispose();
				}
			}
		});
		
		//���ư���
		returnBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new LoginPage();
			}
		});

		setTitle("Update Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300,400);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
