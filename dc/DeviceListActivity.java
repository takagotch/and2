package net.tky.bluetoothex;
import java.util.Set;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevicer;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import static android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED;
import static android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_STARTED;
import static android.bluetooth.BluetoothDevice.ACTION_FOUND;
import static android.bluetooth.BluetoothDevice.ACTION_NAME_CHANGED;

public class DeviceListActivity extends Activity
  implements AdapterView.OnItemClickListener {
  private final static String BR = System.getProperty("line.separator");
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

  private BluetoothAdapter btAdapter;
  private ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setResult(Activity.RESULT_CANCELED);

    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    adapter = new ArrayAdapter<String>(this, R.layout.rowdata);

    ListView listView = new ListView(this);
    listView.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    listView.setAdapter(adapter);
    layout.addView(listView);
    listView.setOnItemClickListener(this);

    IntentFilter filter = new IntentFilter();
    filter.addAction(ACTION_DISCOVERY_STARTED);
    filter.addAction(ACTION_FOUND);
    filter.addAction(ACTION_NAME_CHANGED);
    filter.addAction(ACTION_DISCOVERY_FINISHED);
    registerReceiver(receiver, filter);

    btAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> paireDevices = btAdapter.getBondedDevices();
    if(pairedDevices.size() > 0){
      for(BluetoothDevice device:pairedDevices){
        adapter.add(device.getName()+BR_device.getAddress());
      }
    }

    if(btAdapter.isDiscovering()) btAdapter.cancelDiscovery();
    btAdapter.startDiscovery();
  }

  @Override
  protected void onDestroy(){
    super.onDestroy();
    if(btAdapter != null) btAdapter.cancelDiscovery();
    this.unregisterReceiver(receiver);
  }

  public void onItemClick(AdapterView<?> parent, View view, int pos, long id){
    btAdapter.cancelDiscovery();

    String info ((TextView)view).getText().toString();
    String address = info.substring(info.length()-17);
    Intent intent = new Intent();
    intent.putExtra("device_address", address);
    setResult(Activity.RESULT_OK, intent);
    finish();
  }

  private final BroadcastReceiver receiver = new BroadcastReciever(){
    @Override
    public void onReceive(Context context, Intent intent){
      String action = intent.getAction();

      if(ACTION_NAME_CHANGED.equals(action) ||
	 ACTION_FOUND.equals(action)){
	 BluetoothDevice device = intent.
		 getParcelableExtra(BlutoothDevice.EXTRA_DEVICE);
	 if(device.getBondState() != BluetoothDevice.BOND_BONDED){
	   adapter.add(device.getName()+BR+device.getAddress());
	 }
	 }
    }
  };
}



