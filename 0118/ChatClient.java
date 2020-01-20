import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Socket socket = null;//접속할 소켓 
		
		DataInputStream in; 
		DataOutputStream out; 
		
	
		String send_str = "", recv_string="";
		
		try {
			socket = new Socket("127.0.0.1",8080);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			System.out.println("접속성공");
			
			while(true) {
				send_str = br.readLine();
				try {
					out.writeUTF(send_str);
					System.out.println("client: "+ send_str);
				}catch (Exception e) {
					System.out.println("Ex2");
				}
				
				try {
					recv_string =in.readUTF();
					if(recv_string==null)continue;
					System.out.println(recv_string);
				}catch (Exception e) {
					System.out.println("ex1");
					// TODO: handle exception
				}
//				System.out.println(recv_string);
			}
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("ex3");
		}
	}
}
