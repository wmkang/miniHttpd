package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import server.MiniHttpd;

public class HttpClient{

	public static final String CRLF = "\r\n";

	public static void main(String[] args) throws Exception{

		String ip = args[0];
		String uri = args[1];

		Socket socket = new Socket(ip, MiniHttpd.HTTPD_PORT);
		InputStream is = null;
		OutputStream os = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		try{
			is = socket.getInputStream();
			os = socket.getOutputStream();
			writer = new PrintWriter(os, true);
			reader = new BufferedReader(new InputStreamReader(is));

			writer.print("GET "+uri+" HTTP/1.0" + CRLF+CRLF);
			writer.print("I'm Mini-HttpClient!"+CRLF);
			writer.flush();

			String resLine = null;
			while((resLine=reader.readLine())!=null){
				System.out.println(resLine);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(reader!=null) try{reader.close();}catch(Exception e){};
			if(writer!=null) try{writer.close();}catch(Exception e){};
			if(os!=null) try{os.close();}catch(Exception e){};
			if(is!=null) try{is.close();}catch(Exception e){};
			if(socket!=null) try{socket.close();}catch(Exception e){};
		}

	}
}