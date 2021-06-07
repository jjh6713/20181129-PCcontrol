package PCcontrol;

import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JTextField;

public class ManagerPage_chat_socket {
	static ServerSocket serverSocket = null;
	Socket socket = null;
	HashMap clients; //����
	static List list;
	DataInputStream in;
	DataOutputStream out;
	ArrayList<String> nameArr = new ArrayList<>(); // �����̸� �������̶� �迭������
	 

	//���� �̸�
	public ArrayList<String> getName(){
		return nameArr;
	}
	
	//����ȭ ó�� ������
	public ManagerPage_chat_socket(List list){
		this.list = list;
		clients = new HashMap();
		Collections.synchronizedMap(clients);//����ȭ ó��
		startSocket();
	}
	
	//start ��ư�� ������ ���� ���� accept ȣ��� startthread ����
	public void startSocket() {
		try {
			int portNume = 5000; //��Ʈ��ȣ
			serverSocket = new ServerSocket(portNume);
			list.add("�ֹ� �ý��� ����!");
			Thread td = new StartThread();
			td.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			list.add("�ֹ� �ý��� ����!");
			e.printStackTrace();
		}
	}

	class StartThread extends Thread{
		public void run(){
			while(true){
				try {//accept�� ��� ���� ��û ����
					socket = serverSocket.accept();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		}
	 }
	 
	class ServerReceiver extends Thread {
		  Socket socket;
		  
		  ServerReceiver(Socket socket) {
			  this.socket = socket;
			  try {
				  in = new DataInputStream(socket.getInputStream());
				  out = new DataOutputStream(socket.getOutputStream());
			  } catch(IOException e) {}
		  }
		  
		  //��
		  public void run() {
			  String name = "";
			  try {
				  name = in.readUTF(); //writeUTF �޾ƿ�
				  list.add(name+"���� �α��� �ϼ̽��ϴ�.");
				  clients.put(name, out);
				  nameArr.add(name); //�̸� �迭�� �Է�
				  while(in != null) {
					  list.add(in.readUTF());
				  }
			  } catch(IOException e) {
				  // ignore
			  } finally {
				  nameArr.remove(name);//�̸� �迭���� ����
				  clients.remove(name);
				  list.add("["+name+"]"+"���� ������ �����Ͽ����ϴ�.");
				  list.add("���� �α��� ���� " + clients.size() + "�Դϴ�.");
			  }
		  }
	}
	

	public void keyEnter(JTextField chatField) {
		chatField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						list.add("[manager]"+chatField.getText());
						out.writeUTF("[manager]"+chatField.getText());
					} catch (IOException e1) {
					    e1.printStackTrace();
					}
					chatField.setText("");
				}
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
