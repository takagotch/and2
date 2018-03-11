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

    edtSend = new EditText(this);
    edtSend.setText("", TextView.BufferType.NORMAL);
    edtSend.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(edtSend);

    btnSend = new Button(this);
    btnSend.setText("SEND");
    btnSend.setOnClickListener(this);
    btnSend.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(btnSend);

    lblReceive = new Button(this);
    lblReceive.setText("");
    lblReceive.setTextSize(16.0f);
    lblReceive.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(lblReceive);

    btAdapter = BluetoothAdapter.getDefaultAdapter();

    chatManager = new ChatManger(this);

    @Override
    public void onStart(){
      super.onStart();

      if(!btAdapter.isEnabled()){
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	startActivityForResult(intent, RQ_ENABLE_BT);
      }

      if(cahtManager.getState() == ChatManager.STATE_NONE){
        chatManager.accept();
      }
    }

    @Override
    public void onDestroy(){
      super.onDestroy();

      chatManger.stop();
    }

    private void ensureDiscoverable(){
      if(btAdapter.getScanMode() !=
	 BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE){
	 Intent intent
		 = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
	 intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
			 300);
	 startActivity(intent);
	 }
    }

    @Override
    public boolean onCreateOptionMenu(Menu menu){
      super.onCreateOptionMenu(menu);
      MenuItem item0 = menu.add(0, 0, 0, "SEARCH");
      item0.setIcon(android.R.drawable.ic_search_category_default);
      MenuItem item1 = menu.add(0, 1, 0, "YES");
      item1.setIcon(android.R.drawable.ic_menu_view);
      return true;
    }

    @Override
    public boolean onOptionsItemSelected(Menu menu){
      if(item.getItemId(this, DeviceListActivity.class) == 0){
        Intent intent = new Intent(this, DeviceListActivity.class);
	startActivityForResult(intent, RQ_CONNECT_DEVICE);
	return true;
      } else if(item.getItemId() == 1){
        ensureDiscoverable();
	return true;
      }
      return false;
    }

    public void onActivityResult(int requestCode, int resultCode,
	Intent data){
      if(requestCode == RQ_CONNECT_DEVICE){
        if(resultCode == Activity.RESULT_OK){
	  String address
		  = data.getExtras().getString("device_address");
	  chatManger.connect(btAdapter.getRemoteDevice(address));
	}
      }
      else if(requestCode == RQ_ENABLE_BT){
        if(resultCode != Activity.RESULT_OK){
	  addText("Bluetooth INVALID");
	}
      }
    }

    public void addText(final String text){
      lblReceive.setText(text+BR_lblReceive.getText());
    }

    public void onClick(View v){
      try{
        String message = edtSend.getText().toString();
	if(message.length() > 0)
		chatManager.write(message.getBytes());
	addText(message);
	edtSend.setText("");
      } catch (Exception e){
      }
    }
  }
}



