package server;

import java.net.*;
import java.io.*;

class ServiceHandler implements Runnable{

	public static final String CRLF = "\r\n";

	private Socket client;

	public ServiceHandler(Socket client){
		this.client = client;
		Thread t = new Thread(this);
		t.start();
	}

	public void run(){

		InputStream is = null;
		OutputStream os = null;
		PrintStream out = null;
		BufferedReader reader = null;
		try{
			is = client.getInputStream();
			os = client.getOutputStream();

			reader = new BufferedReader(new InputStreamReader(is));
			String str = null;
			System.out.println(CRLF);
			while (!(str = reader.readLine()).equals("")){
				System.out.println(str);
			}

			out = new PrintStream(os);

			out.print("HTTP/1.0 200 OK" + CRLF);
			out.print("Server: Mini-Httpd/1.0" + CRLF);
			out.print("Content-Type: text/html;charset=euc-kr"+CRLF+CRLF);

			out.println("<html>");
			out.println("	<head>");
			out.println("		<title>Mini-Httpd</title>");
			out.println("	</head>");
			out.println("	<body>");
			out.println("		<h4>Welcome to Mini-Htttpd!</h4>");
			out.println("	</body>");
			out.println("</html>");
			out.close();
		}catch (IOException ex){
			ex.printStackTrace();
		}finally{
			if(reader!=null) try{reader.close();}catch(Exception e){};
			if(out!=null) try{out.close();}catch(Exception e){};
			if(os!=null) try{os.close();}catch(Exception e){};
			if(is!=null) try{is.close();}catch(Exception e){};
			if(client!=null) try{client.close();}catch(Exception e){};
		}
	}
}
/*
			out.println("HTTP/1.1 302 Object moved");
			out.println("Connection: close");
			out.println("Date: Thu, 14 Feb 2008 10:04:21 GMT");
			out.println("Server: Microsoft-IIS/6.0");
			out.println("Pragma: no-cache");
			out.println("cache-control: no-store");
			out.println("Location: main.asp");
			out.println("Content-Length: 129");
			out.println("Content-Type: text/html");
			out.println("Expires: Thu, 14 Feb 2008 10:04:21 GMT");
			out.println("Set-Cookie: KTFMENUURL=; path=/");
			out.println("Set-Cookie: SHOWCAST=USER%5FIDX=2577&USER%5FAPP%5FID=0103A11F&PHONE%5FNUMBER=01051169930&USER%5FPHONE%5FLCD=240X320&USER%5FPHONE%5FNUMBER=01051169930&USER%5FPHONE%5FTYPE=3; path=/");
			out.println("Set-Cookie: ASPSESSIONIDQSBDBATD=ABHFIOOBAFIHFOLHBGPKFCLL; path=/");
			out.println("Cache-control: private");
			out.println("");
			out.println("<head><title>Object moved</title></head>");
			out.println("<body><h1>Object Moved</h1>This object may be found <a HREF=\"main.asp\">here</a>.</body>");
*/