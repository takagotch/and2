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
    layout.setBackgroundColor();
    layout.setOrientation();
    setContentView();

    edtSend = new EditText();
    edtSend.setText();
    edtSend.setLayoutParams();
    layout.addView();

    btnSend = new Button();

    lblReceive = new TextView();
  }

  @Override
  public void onStart(){}

  @Override
  public void onStop(){}
}




