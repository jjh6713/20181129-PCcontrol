package PCcontrol;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemMethod extends JFrame{
	JPanel panel = new JPanel();
	JTextField num = new JTextField("0");
	String name;
	String category;
	String price;
	String img;
	
	public ItemMethod(String name, String category, String price,String img) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.img = img;
		
		panel.setLayout(new BorderLayout());
		
		ImageIcon ic = new ImageIcon(img);
		Image ie = ic.getImage();
		Image newIe = ie.getScaledInstance(150, 150,Image.SCALE_SMOOTH);
		ImageIcon newIc = new ImageIcon(newIe);
		
		JButton btn = new JButton(name,newIc);
		btn.setVerticalTextPosition(JButton.BOTTOM);
		btn.setHorizontalTextPosition(JButton.CENTER);
		
		JButton plus = new JButton("+");
		JButton minus = new JButton("-");
		
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = Integer.parseInt(num.getText());
				temp++;
				num.setText(Integer.toString(temp));
			}
		});
		
		minus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = Integer.parseInt(num.getText());
				temp--;
				num.setText(Integer.toString(temp));
			}
		});
		
		JPanel temp = new JPanel();
		
		btn.setSize(100,80);
		plus.setSize(30,20);
		num.setSize(40,20);
		minus.setSize(30,20);
		
		panel.add(btn,BorderLayout.CENTER);
		temp.add(plus);
		temp.add(num);
		temp.add(minus);
		panel.add(temp,BorderLayout.SOUTH);
		
		panel.setSize(100,100);
	}
		
	public Component getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}



	public static void main(String[] args) {
		new ManagerPage();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}



	



}
