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
	static String uid;	// �������̵�
	
	//���� ����
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

	//���� ��ŸƮä�� ����
	public UserPage_conn_socket(pc_member info) {
		String uip = "127.0.0.1"; // ����ȣ��Ʈ ����������
		uid = info.getMember_id(); //���� ���̵�
		int port = 5000;//���� ��Ʈ��ȣ
		init(uid,uip,port);
	}
	

	//������ �Է�
	public static void init(String uid, String uip, int port){
		try {
			String serverIp = uip;
		    // ������ �����Ͽ� ������ ��û�Ѵ�.
			socket = new Socket(serverIp, port);
			out = new DataOutputStream(socket.getOutputStream());
			System.out.println("������ ����Ǿ����ϴ�.");
			//������ �̸�����
			out.writeUTF(uid);
			///////////////////////////////////////////////////////////////////
		} catch(ConnectException ce) {
			  ce.printStackTrace();
	  	} catch(Exception e) {}
	}
}


