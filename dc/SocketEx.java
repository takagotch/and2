package net.tky.socketex;
import android.app.Activity;
import
import
import
import

public class SocketEx extends Activity
  implements View.OnClickListener{
  private final static String BR = System.getProperty();
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;

  private final static String IP="192.168.11.1";

  private TextView lblReceive;
  private EditText edtSend;
  private Button btnSend;

  private Socket socket;
  private InputStream in;
  private OutputStream out;
  private boolean error;

  private final Handler handler = new Handler();

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinarLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    edtSend = new EditText(this);
    edtSend.setText("");
    edtSend.setLayoutParams(MP, WC);
    layout.addView(edtSend);

    btnSend = new Button(this);
    btnSend.setText("SEND");
    btnSend.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(btnSend);

    lblReceive = new TextView(this);
    lblReceive.setTextSize(16.0f);
    lblReceive.setTextColor(Color.BLACK);
    lblReceive.setTextColor(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(lblReceive);
  }

  @Override
  public void onStart(){
    super.onStart();

    Thread thread = new Thread(){
      public void run(){
        try{
	  connect(IP, 8081);
	} catch (Exception e){
	}
      }
    };
    thread.start();
  }

  @Override
  public void onStop(){
    super.onStop();
    disconnect();
  }

  private void addText(final String text){
    handler.post(new Runnable(){
      public void run(){
        lblReceive.setText(text+BR+
		lblReceive.getText());
      }
    });
  }

  private void connect(String ip, int port){
    int size;
    String str;
    byte[] w = new byte[1024];
    try{
      addText("On..");
      socket = new Socket(ip, port);
      in = socketInputStream();
      out = socket.getOutputStream();
      addText("COMPLETE");

      while(socket != null && socket.isConnected()){
        size = in.read(w);
	if(size <= 0) continue;
	str = new String(w, 0, size, "UTF-8");

	addText(str);
      }
    } catch (Exception e){
      addText("ERR");
    }
  }

  private void disconnect(){
    try{
      socket.close();
      socket = null;
    } catch(Exception e){
    }
  }

  public void onClick(View v){
    Thread thread = new Thread(new Runnable() {public void run(){
    error = false;
    try{
      if(socket != null && socket.isConnected()){
        byte[] w
	  = edtSend.getText().toString().getBytes("UTF8");
	out.write(w);
	out.flush();
      }
    } catch(Exception e){
      error = true;
    }
    handler.post(new Runnable() {public void run(){
      if(!error){
        edtSend.setText("");
      } else{
        addText("ERR");
      }
    }});
    }});
    thread.start();
  }
}




