

import java.io.*;
import java.net.*;

public class Master {
	public static void main(String[] args) throws IOException
	{
		String s = "slave에게 작업을 요청합니다.";
		
		//포트번호 50000번으로 서버에 연결 시도
		Socket csoc = new Socket("203.153.146.131", 50000);
		System.out.println("203.153.146.131 주소와 50000포트로 접속 시도");

		System.out.println("클라이언트(송신): " +s);
		PrintWriter cpw = new PrintWriter(csoc.getOutputStream(), true);
		cpw.println(s); //클라이언트에서 서버로 메세지 보냄

		//클라이언트에서 서버의 답변 메세지를 받기위해 스트림을 구성
		BufferedReader cbr = new BufferedReader(new InputStreamReader(csoc.getInputStream())); //클라이언트에서 서버로부터 수신받은 메세지 받아들임
		
		String reading=cbr.readLine(); // 서버로부터 온 메세지 수신
		//int sum = Integer.parseInt(reading);
		System.out.println("클라이언트(수신): "+reading);
		
		cbr.close();
		cpw.close();
		csoc.close();
	}
}
