import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer{
  public void start(int port){
    ServerSocket server;
    Socket socket;
    ChatServerThread thread;

    try{
      server = new ServerSocket(port);
      System.err.println("ChatServer start"+
	"\nIP Address:"+InetAddress.getLocalHost()
	  .getHostAddress()+
	"\nPort:"+port);
      whille(true){
        try{
	  socket = server.accept();

	  thread = new ChatServerThread(socket);
	  thread.start();
	}catch (IOException e){
	}
      } 
    } catch(Exception e){
      System.err.println(e);
    }
  }

  public static void main(String[] args){
    ChatServer server = new ChatServer();
    server.start(8081);
  }
}

