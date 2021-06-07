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
	HashMap clients; //유저
	static List list;
	DataInputStream in;
	DataOutputStream out;
	ArrayList<String> nameArr = new ArrayList<>(); // 유저이름 가변적이라 배열에담음
	 

	//유저 이름
	public ArrayList<String> getName(){
		return nameArr;
	}
	
	//동기화 처리 생성자
	public ManagerPage_chat_socket(List list){
		this.list = list;
		clients = new HashMap();
		Collections.synchronizedMap(clients);//동기화 처리
		startSocket();
	}
	
	//start 버튼에 넣을것 소켓 시작 accept 호출용 startthread 생성
	public void startSocket() {
		try {
			int portNume = 5000; //포트번호
			serverSocket = new ServerSocket(portNume);
			list.add("주문 시스템 시작!");
			Thread td = new StartThread();
			td.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			list.add("주문 시스템 실패!");
			e.printStackTrace();
		}
	}

	class StartThread extends Thread{
		public void run(){
			while(true){
				try {//accept로 계속 서비스 요청 받음
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
		  
		  //런
		  public void run() {
			  String name = "";
			  try {
				  name = in.readUTF(); //writeUTF 받아옴
				  list.add(name+"님이 로그인 하셨습니다.");
				  clients.put(name, out);
				  nameArr.add(name); //이름 배열에 입력
				  while(in != null) {
					  list.add(in.readUTF());
				  }
			  } catch(IOException e) {
				  // ignore
			  } finally {
				  nameArr.remove(name);//이름 배열에서 삭제
				  clients.remove(name);
				  list.add("["+name+"]"+"님이 접속을 종료하였습니다.");
				  list.add("현재 로그인 수는 " + clients.size() + "입니다.");
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
