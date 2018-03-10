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

  private void addText(){}

  private void connect(){}

  private void disconnect(){}

  public void onClick(){}

}




