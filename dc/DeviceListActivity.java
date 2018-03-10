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
    requestWindowFeature();
    setResult();

    LinearLayout layout = new LinearLayout();
    layout.setOrientation();
    setContentView();

    adapter = new ArrayAdapter<>();

    ListView listView = new ListView();

    IntentFilter filter = new IntentFilter();

    btAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> paireDevices = btAdapter.getBondedDevices();
    if(BluetoothDevice device:pairedDevices){
      for(BluetoothDevice device:pairedDevices){
        adapter.add(device.getName()+BR_device.getAddress());
      }
    }

    if() btAdapter.cancelDiscovery();
    btAdapter.startDiscovery();
  }

  @Override
  protected void onDestroy(){
  
  }

  public void onItemClick(AdapterView<?> parent, View view, int pos, long id){
  
  }
}



