package PCcontrol;

import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JTextField;


public class UserPage_chat_socket {
	static Socket socket = null;
	static DataOutputStream out;
	static String uid;	// 유저아이디

	public static void setOut(DataOutputStream out) {
		UserPage_chat_socket.out = out;
	}
	public static void setUid(String uid) {
		UserPage_chat_socket.uid = uid;
	}
	public static void setSocket(Socket socket) {
		UserPage_chat_socket.socket = socket;
	}

	//데이터 입력
	public static void startChat(List list){
		///////////////////////////////////////////////////////////////////
		Thread receiver = new Thread(new ClientReceiver(socket, list));
		receiver.start();
	}
	
	
	//유저 채팅 키프레스
	public void keyEnter(List list,JTextField chatField) {
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
						list.add("["+uid+"]"+chatField.getText());
						out.writeUTF("["+uid+"]"+chatField.getText());
					} catch (IOException e1) {
					    e1.printStackTrace();
					}
					chatField.setText("");
				}
			}
		});
	}

}

//유저 채팅 클라이언트 리시버
class ClientReceiver extends Thread {
	  Socket socket;
	  DataInputStream in; //인풋
	  List list;

	  public ClientReceiver(Socket socket,List list) {
		  this.socket = socket;
		  this.list = list;
		  try {
			  in = new DataInputStream(socket.getInputStream());
		  } catch(IOException e) {}
	  }

	  public void run() {
		  while(in!=null) {
			  try {
				  String receive = in.readUTF();
				  list.add(receive);
			  } catch(IOException e) {}
		  }
	  }
}


