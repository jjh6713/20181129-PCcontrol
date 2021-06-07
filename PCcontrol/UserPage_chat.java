package PCcontrol;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserPage_chat extends JFrame{

	public UserPage_chat(pc_member info,UserPage_conn_socket socket) {
		Container cp = getContentPane();
		JPanel bottom = new JPanel();
		
		cp.setLayout(new BorderLayout());
		bottom.setLayout(new FlowLayout());
		
		List list = new List();
		list.add("-안녕하세요 고객님-");
		JButton endBtn = new JButton("종료");
		JTextField chatField = new JTextField(20);
		
		bottom.add(chatField);
		bottom.add(endBtn);

		UserPage_chat_socket uc = new UserPage_chat_socket();
		uc.setSocket(socket.getSocket());
		uc.setUid(socket.getUid());
		uc.setOut(socket.getOut());
		uc.startChat(list);//문의 시스템 스타트
		uc.keyEnter(list,chatField);
		
		
		//종료 버튼 누를시
		endBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		cp.add(list, BorderLayout.CENTER);
		cp.add(bottom,BorderLayout.SOUTH);
		
		setTitle("User Chat Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400, 300);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
