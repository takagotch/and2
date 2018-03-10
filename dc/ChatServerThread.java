import java.io*;
import java.net*;
import java.util.*;

public class ChatServerThread extends Thread {
  private static List<ChatServerThread> threads =
    new ArrayList<ChatServerThread>();
  private Socket socekt;

  public ChatServerThread(Socket socket){
    super();
    this.socket = socket;
    threads.add(this);
  }

  public void run(){
    InputStream in = null;
    String message;
    int size;
    byte[] w = new byte[10240];
    try{
      System.out.println("ChatServerThread start");
      in = socket.getInputStream();
      while(true){
        try{
	  size = in.read(w);

	  if(size <= 0) throw new IOException();

	  message = new String(w, 0, size, "UTF-8");

	  System.out.println(message);
	  sendMessageAll(message);
	} catch (IOException e){
	  System.out println("ChatServerThread stop");
	  socket.close();
	  threads.remove(this);
	  return;
	}
      }
    } catch (IOException e){
      System.err.println(e);
    }
  }

  public void sendMessageAll(String message){
    ChatServerThread thread;
    for(int i = 0; i < threads.size(); i++){
      thread = (ChatServerThread)threads.get(i);
      if(thread.isAlive()) thread.sendMessage(this, message);
    }
    System.out.println(message);
  }

  public void sendMessage(ChatServerThread talker, String message){
    try{
      OutputStrem out = socket.getOutputStream();
      byte[] w = message.getBytes("UTF8");
      out.write(w);
      out.flush();
    } catch(IOException e){
    }
  }
}

