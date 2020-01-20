import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable {
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(8080);
		for (int i = 0; i < 19; i++) {
			new Connection(socket);
		}
	}

	static class Connection extends Thread {
		private ServerSocket serverSock;

		public Connection(ServerSocket serverSock) {
			this.serverSock = serverSock;
			start();
		}

		public void run() {

			Socket acp_sock = null;
			String recv_str = null;
			String result = "";

			try {
				while (true) {
					acp_sock = serverSock.accept();
//					InputStreamReader in = new InputStreamReader(acp_sock.getInputStream());
//					OutputStreamWriter out = new OutputStreamWriter(acp_sock.getOutputStream());
					
					DataInputStream in = new DataInputStream(acp_sock.getInputStream()); 
					DataOutputStream out = new DataOutputStream(acp_sock.getOutputStream()); 
					
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("클라이언트 접속 ");
					String str = "";
					while(true) {
						recv_str = in.readUTF();
						System.out.println("recv : "+recv_str);
						
						str= br.readLine();
						if(str!="") {
							out.writeUTF(str);
						}else {
							continue;
						}
						
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 접속 수락 소켓

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}