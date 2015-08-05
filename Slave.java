import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Slave {
	public static void main(String[] args) throws IOException
	{
		//포트번호 50000번을 사용하는 소켓
		ServerSocket ss = new ServerSocket(50000);
		System.out.println("master의 요청을 기다립니다.");

		//마스터 접속을 기다림
		Socket ssoc = ss.accept(); //마스터와 연결될 때 까지 여기서 대기
		System.out.println("slave: "+ssoc.getInetAddress()+" master와 "+ssoc.getLocalPort()+"포트로 연결");

		BufferedReader sbr = new BufferedReader(new InputStreamReader(ssoc.getInputStream()));

		PrintWriter spw = new PrintWriter(ssoc.getOutputStream(), true);
		String reading = sbr.readLine(); //마스터에게서 받은 메세지를 읽어들임
		System.out.println("수신: "+reading);

		String fileName = "/home/hadoop/datanode/current/BP-895877057-127.0.0.1-1437666734298/current/finalized/subdir0/subdir0/blk_1073741862";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		System.out.println("buffer reader 생성완료.");

		//ArrayList<String> a = new ArrayList<String>();
		//ArrayList<String> b = new ArrayList<String>();
		ArrayList<String> c = new ArrayList<String>();

	   	int sum = 0;
	    
	   	String temp;
	    	
		int line = 0;
	//	System.out.println("Arraylist에 파일 내용 넣기");
		while( (temp = br.readLine()) != null ) {
			sum += Integer.parseInt(temp.split("\t")[2]);
		}
	//	System.out.println("arraylist 생성 완료.");

		System.out.println("sum계산 완료.");

		System.out.println("complete!");
		System.out.println("result: "+sum);

		String sending = String.valueOf(sum);
		spw.println(sending); 
		System.out.println("송신: "+sending);

		br.close();
		spw.close();
		sbr.close();
		ssoc.close();
		ss.close();
	}
}
