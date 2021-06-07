package PCcontrol;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagerPage_chat extends JFrame{
	ArrayList<String> nameArr = new ArrayList<String>();
	public ManagerPage_chat() {
		
		Container cp = getContentPane();
		JPanel bottom = new JPanel();
		JPanel bottom2 = new JPanel();
		
		cp.setLayout(new BorderLayout());
		bottom.setLayout(new BorderLayout());
		bottom2.setLayout(new BorderLayout());
		
		List list = new List();
		list.add("안녕하세요 매니저님●◎○");
		JButton startBtn = new JButton("서비스 시작");
		JButton endBtn = new JButton("매니저 관리창");
		JTextField chatField = new JTextField(20);
		bottom.add(chatField,BorderLayout.NORTH);
		
		bottom2.add(startBtn,BorderLayout.WEST);
		bottom2.add(endBtn,BorderLayout.EAST);
		
		bottom.add(bottom2,BorderLayout.SOUTH);
		
		ManagerPage_chat_socket st = new ManagerPage_chat_socket(list); //소켓 셋팅
		
		//시작 버튼 누를시
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				st.keyEnter(chatField);
			}
		});
		
		endBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nameArr = st.getName();
				new ManagerPage(nameArr);
			}
		});

		
		cp.add(list, BorderLayout.CENTER);
		cp.add(bottom,BorderLayout.SOUTH);
		
		setTitle("User chat Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400, 300);
		setVisible(true);
	}
	
}
