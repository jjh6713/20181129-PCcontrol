
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

	//�⺻ â
	public ManagerPage() {
		setTitle("Test main login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		      
		cp.setLayout(new BorderLayout());
		      
		JPanel jp1 = new JPanel();
		      
		jp1.setLayout(new GridLayout(4,0));
		      
		JButton btnUser = new JButton("���� ����");
		JButton btnStock = new JButton("��� ����");
		JButton btnLog = new JButton("���� ����");
		JButton btnChat = new JButton("���� ����");

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
		      
		
		//����
		btnUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_user();
			}
		});

		//���
		btnStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_stock();
			}
		});
		
		//���⳻��
		btnLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_log();
			}
		});
		
		//ä�� �� ���ϼ��� ����
		btnChat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ManagerPage_chat();
			}
		});
	}

	//���� �̿� ����
	public ManagerPage(ArrayList<String> nameArr) {
		setTitle("Test main login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		      
		cp.setLayout(new BorderLayout());
		      
		JPanel jp1 = new JPanel();
		      
		jp1.setLayout(new GridLayout(4,0));
		      
		JButton btnUser = new JButton("���� ����");
		JButton btnStock = new JButton("��� ����");
		JButton btnLog = new JButton("���� ����");

		jp1.add(btnStock);
		jp1.add(btnUser);
		jp1.add(btnLog);
		      
		JPanel jp2 = new JPanel();
		      
		jp2.setLayout(new GridLayout(2, 4, 0, 100));
		     
		System.out.println(nameArr.size());
		System.out.println(nameArr.get(0));
		//array ���� j st ���� x      
		int j=0,x=0;
		//�׼�
		ArrayList<pc_member> member = ManagerPage_method.getMember();
		while(j<member.size()) {
		   pc_member temp = member.get(j);
		   x=0;

			
		   while(x<nameArr.size()) {
			   if((temp.getMember_id()).equals(nameArr.get(x))) {
				   JButton btnCostAdd = new JButton("����"+nameArr.get(x));
				   JLabel jl = new JLabel();
				   JPanel p1 = new JPanel();
				   p1.setLayout(new BorderLayout());
				   
				   new getTime2(jl,temp.getMember_id()).start();
				   p1.add(btnCostAdd,BorderLayout.CENTER);
				   p1.add(jl,BorderLayout.SOUTH);
				   jp2.add(p1);
				   
				   //���� �ð� �߰�
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
		      
		
		//���� ����
		btnUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_user();
			}
		});

		//��� ����
		btnStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_stock();
			}
		});
		
		//���� ����
		btnLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ManagerPage_log();
			}
		});
		
	}

}