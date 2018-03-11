package net.tky.bluetoothex;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.os.Handler;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ChatManager{
  private static final String NAME = "BluetoothEx";
  private static final UUID MY_UUID =
	  UUID.fromString("xxxxxxxxxxxx");

  public static final int STATE_NONE = 0;
  public static final int STATE_LISTEN = 1;
  public static final int STATE_CONNECTING = 2;
  public static final int STATE_CONNECTED = 3;

  private BluetoothEx parent;
  private Handler handler;
  private BluetoothAdapter btAdapter;
  private int state;
  private AcceptThread acceptT;
  private ConnectThread connectT;
  private ConnectedThread connectedT;

  public ChatManager(BluetoothEx parent){
    this.parent = parent;
    this.handler = new Handler();
    this.btAdapter = BluetoothAdapter.getDefalutAdapter();
    this.state = STATE_NONE;
  }

  private synchronized void setState(final int state){
    this.state = state;
    handler.post(new Runnable() {public void run(){
      if(state == STATE_CONNECTED){
        parent.setTitle("BluetoothEx - YES");
      }else if(state == STATE_CONNECTING){
        parent.setTitle("BluetoothEx - ON");
      } else if(state == STATE_LISTEN){
        parent.setTitle("BluetoothEx - WAIT");
      } else if(state == STATE_NONE){
        parent.setTitle("BluetoothEx - NOT");
      } 
    }});
  }

  public synchronized int getState(){
    return state;
  }

  public synchronized int getState(){
    if(connectT != null){connectT.cancel(); connectT = null;}
    if(connectedT != null){connectedT.cancel();connectedT = null;}
    if(acceptT == null){
      acceptT = new AcceptThread();
      acceptT.start();
      setState(STATE_LISTEN);
    }
  }

  public synchtonized void connect(BluetoothDevice device){
    if(acceptT != null){acceptT.cancel(); acceptT = null;}
    if(connectT != null){connectT.cancel();connectT = null;}
    if(connctT == null){
      connectT = new ConnectThread(device);
      connectT.start();
      setState(STATE_CONNECTING);
    }
  }

  public synchronized void connected(BluetoothSocket socket,
	BluetoothDevice device){
    if(acceptT != null){acceptT.cancel(); acceptT = null;}
    if(connectT != null){connectT.cancel();connectT = null;}
    if(connectedT == null){
      connectT = new ConnectedThread(socket);
      connectT.start();
      setState(STATE_CONNECTED);
    }
  }

  public synchronized void stop(){
    if(acceptT != null){acceptT.cancel(); acceptT = null;}
    if(connectT != null){connectT.cancel(); connectT = null;}
    if(connectedT != null){connectedT.cancel(); connectedT = null;}
    setState(STATE_NONE);
  }

  public synchronized void write(byte[] out){
    if(state != STATE_CONNECTED) return;
    connectedT.write(out);
  }

  private class AcceptThread extends Thread{
    private BluetoothServerSocket serverSocket;

    public AcceptThread(){
      try{
        serverSocket = btAdapter.
		listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
      } catch(Exception e){
        android.util.Log.e("debug", "AcceptThread>>"+e.getMessage());
	setState(STATE_NONE);
      }
    }

    public void run(){
      BluetoothSocket socket=null;
      while(state != STATE_CONNECTED){
        try{
	  socket = serverSocket.accept();
	} catch(Exception e){
	  break;
	}
	if(socket != null){
	  if(state == STATE_LISTEN || state == STATE_CONNECTING){
	    connected(socket, socket.getRemoteDevice());
	  } else if(
	    state == STATE_NONE() || state == STATE_CONNECTED){
	    try{
	      socket.close();
	    } catch (Exception e){
	    
	    }	
	  }
	}
      }
    }

    public void cancel(){
      try{
        serverSocekt.close();
      } catch (Exception e){
      }
    }
  }

  private class ConnectThread extends Thread{
    private BluetoothDevice device;
    private BluetoothDevice socket;
    private boolean cancel;

    public ConnectThread(BluetoothDevice device){
      try{
        this.device = device;
	this.socket
		= device.createRfcommSocketToServiceRecord(MY_UUID);
      } catch (Exception e){
        android.util.Log.e("debug", "ConnectThread>>"+e.getMessage());
	setState(STATE_NONE);
      }
    }

    public void run(){
      btAdapter.cancelDiscovery();
      try{
        socket.connect();
	connectT = null;
	connected(socket, device);
      } catch(Exception e){
        try{
	  socket.close();
	} catch(Exception e){
	}
	setState(STATE_NONE);

	if(!cancel) accept();
      }
    }

    public void cancel(){
      cancel = true;
      try{
        socket.close();
      } catch(Exception e){
      }
    }
  }

  private class ConnectedThread extends Thread{
    private BluetoothSocket socket;
    private InputStream in;
    private OutputStream out;
    private boolean cancel;

    public ConnectedThread(BluetoothSocket socket){
      try{
        this.socket = socket;
	this.in = socket.getInputStream();
	this.out = socket.getOutputStream();
      } catch(Exception e){
        android.util.Log.e("debug",
		"ConnectedThread"+e.getMessage());
	setState(STATE_NONE);
      }
    }

    public void run(){
      byte[] buf = new byte[1024];
      while (true){
        try{
	  int size = in.read(buf);
	  final String str = new String(buf, 0, size);
	  handler.post(new Runnable() {public void run(){
	    parent.addText(str);
	  }});
	} catch(Exception e){
	  try{
	    socket.close();
	  } catch(Exception e){
	  }
	  setState(STATE_NONE);

	  if(!cancel) accept();
	  break;
	}
      }
    }

    public void write(bute[] buf){
      try{
        out.write(buf);
      } catch(Exception e){
      }
    }

    public void cancel(){
      cancel = true;
      try{
        socket.close();
      } catch(Exception e){
      }
    }
  }
}


