package net.tky.bluetoothex;
import android.app.Activity;

public class BluetoothEx extends AppCompatActivity
  implements View.OnClickListener{
  private final static int BR = System.getProperty("line.separator");
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;

  private static final int RQ_CONNECT_DEVICE = 1;
  private static final int RQ_ENABLE_BT = 2;

  private BluetoothAdapter btAdapter;
  private ChatManager chatManager;

  private EditText edtSend;
  private Button btnSend;
  private TextView lblReceive;

  @Override
  public voie onCreate(Bundle bundle){
    super.onCreate(bundle);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    edtSend = new EditText();
    edtSend.setText();
    edtSend.setLayoutParams();
    layout.addView();

    btnSend = new Button();
    btnSend.setText();
    btnSend.setOnClickListener();
    btnSend.setLayoutParams();
    layout.addView(btnSend);

    lblReceive = new Button(this);
    lblReceive.setText("");
    lblReceive.setTextSize(16.0f);
    lblReceive.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(lblReceive);

    btAdapter = BluetoothAdapter.getDefaultAdapter();

    chatManager = new ChatManger(this);
  }
}



