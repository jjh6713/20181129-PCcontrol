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


public class UserPage_conn_socket {
	static Socket socket = null;
	static DataOutputStream out;
	static String uid;	// 유저아이디
	
	//소켓 리턴
	public static Socket getSocket() {
		return socket;
	}
	//out
	public static DataOutputStream getOut() {
		return out;
	}
	//uid
	public static String getUid() {
		return uid;
	}

	//유저 스타트채팅 연결
	public UserPage_conn_socket(pc_member info) {
		String uip = "127.0.0.1"; // 로컬호스트 유저아이피
		uid = info.getMember_id(); //유저 아이디
		int port = 5000;//유저 포트번호
		init(uid,uip,port);
	}
	

	//데이터 입력
	public static void init(String uid, String uip, int port){
		try {
			String serverIp = uip;
		    // 소켓을 생성하여 연결을 요청한다.
			socket = new Socket(serverIp, port);
			out = new DataOutputStream(socket.getOutputStream());
			System.out.println("서버에 연결되었습니다.");
			//접속자 이름전송
			out.writeUTF(uid);
			///////////////////////////////////////////////////////////////////
		} catch(ConnectException ce) {
			  ce.printStackTrace();
	  	} catch(Exception e) {}
	}
}


