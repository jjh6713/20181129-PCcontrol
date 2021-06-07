
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

public class ManagerPage extends JFrame{

	//기본 창
	public ManagerPage() {
		setTitle("Test main login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		      
		cp.setLayout(new BorderLayout());
		      
		JPanel jp1 = new JPanel();
		      
		jp1.setLayout(new GridLayout(4,0));
		      
		JButton btnUser = new JButton("유저 관리");
		JButton btnStock = new JButton("재고 관리");
		JButton btnLog = new JButton("매출 내역");
		JButton btnChat = new JButton("서비스 시작");

		jp1.add(btnStock);
		jp1.add(btnUser);
		jp1.add(btnLog);
		jp1.add(btnChat);
		      
		JPanel jp2 = new JPanel();
		      
		jp2.setLayout(new GridLayout(2, 4, 0, 100));
  
		cp.add(jp1,BorderLayout.WEST);
		cp.add(jp2,BorderLayout.EAST);
		      
		setVisible(true);
		setSize(300,300);
		      
		
		//유저
		btnUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_user();
			}
		});

		//재고
		btnStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_stock();
			}
		});
		
		//매출내역
		btnLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_log();
			}
		});
		
		//채팅 및 소켓서비스 시작
		btnChat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ManagerPage_chat();
			}
		});
	}

	//소켓 이용 이후
	public ManagerPage(ArrayList<String> nameArr) {
		setTitle("Test main login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		      
		cp.setLayout(new BorderLayout());
		      
		JPanel jp1 = new JPanel();
		      
		jp1.setLayout(new GridLayout(4,0));
		      
		JButton btnUser = new JButton("유저 관리");
		JButton btnStock = new JButton("재고 관리");
		JButton btnLog = new JButton("매출 내역");

		jp1.add(btnStock);
		jp1.add(btnUser);
		jp1.add(btnLog);
		      
		JPanel jp2 = new JPanel();
		      
		jp2.setLayout(new GridLayout(2, 4, 0, 100));
		     
		System.out.println(nameArr.size());
		System.out.println(nameArr.get(0));
		//array 돌릴 j st 돌릴 x      
		int j=0,x=0;
		//액션
		ArrayList<pc_member> member = ManagerPage_method.getMember();
		while(j<member.size()) {
		   pc_member temp = member.get(j);
		   x=0;

			
		   while(x<nameArr.size()) {
			   if((temp.getMember_id()).equals(nameArr.get(x))) {
				   JButton btnCostAdd = new JButton("유저"+nameArr.get(x));
				   JLabel jl = new JLabel();
				   JPanel p1 = new JPanel();
				   p1.setLayout(new BorderLayout());
				   
				   new getTime2(jl,temp.getMember_id()).start();
				   p1.add(btnCostAdd,BorderLayout.CENTER);
				   p1.add(jl,BorderLayout.SOUTH);
				   jp2.add(p1);
				   
				   //유저 시간 추가
				   btnCostAdd.addActionListener(new ActionListener() {
					   @Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							new ManagerPage_costUpdate(temp.getMember_id());
						}
				   });
			   }
			   x++;
		   }
		   j++;
		}

		
		cp.add(jp1,BorderLayout.WEST);
		cp.add(jp2,BorderLayout.EAST);
		      
		setVisible(true);
		setSize(500,300);
		      
		
		//유저 관리
		btnUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_user();
			}
		});

		//재고 관리
		btnStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_stock();
			}
		});
		
		//매출 내역
		btnLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_log();
			}
		});
		
	}

}