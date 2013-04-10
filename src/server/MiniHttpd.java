package server;

import java.io.IOException;
import java.net.ServerSocket;

public class MiniHttpd extends Thread{
	
	public static final int HTTPD_PORT = 6666;

	private ServerSocket server;

	public MiniHttpd(int port) throws IOException{
		server = new ServerSocket(port);
	}

	public void run(){
		while (true){
			try{
				new ServiceHandler(server.accept());
			}catch (Exception ex){
				System.out.println(ex);
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) throws IOException{
		MiniHttpd mini = new MiniHttpd(HTTPD_PORT);
		mini.start();
		System.out.println("# Mini-Httpd Started!");
	}
}