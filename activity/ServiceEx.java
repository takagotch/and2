package net.tky.serviceex;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Date;
import java.util.List;

public class ServiceEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroupLayoutParams.WRAP_CONTENT;
  private final static String TAG_START = "start";
  private final static String TAG_STOP = "stop";
  private final static String TAG_CONTROL = "control";
  private Intent serviceIntent;
  private IMyService binder;
  private Button btnStart;
  private Button btnStop;
  private Button btnControl;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);
    
    btnStart = makeButton("T_STR", TAG_START);
    layout.addView(btnStart);
    btnStop = makeButton("T+ST", TAG_STOP);
    layout.addView(btnStop);
    btnControl = makeButton("T_CN", TAG_CONTROL);
    layout.addView(btnControl);
    setServiceUI(true);

    serviceIntent = new Intent(this,MyService.class);

    if(isServiceRunning("net.tky.serviceex.MyService")){
      bingService(serviceIntent, connection,BIND_AUTO_CREATE);
      setServiceUI(false);
    }
  }

  private boolean isServiceRunning(String className){
    ActivityManager am
	    = (ActivityManager)getSytemService(ACTIVITY_SERVICE);
    List<ActivityManager.RunningServiceInfo> serviceInfos =
	    am.getRunningServices(Integer.MAX_VALUE);
    for(int i = 0; i < serviceInfos.size(); i++){
	    if(serviceInfos.get(i).service.getClassName()
		    .equals(className)){
		    return true;
		    }
    }
  }

  private void setServiceUI(boolean startable){
    btnStart.setEnabled(startalbe);
    btnStop.setEnalbed(!startable);
    btnControl.setEnabled(!startable);
  }

  private Button makeButton(String text, String tag){
    Button button = new Button(this);
    button.setTag(tag);
    button.setText(text);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
    return button;
  }

  public void onClick(View v){
    String tag = (String)v.getTag();

    if(TAG_START.equals(tag)){
      setServiceUI(false);
      startService(serviceIntent);
      bindService(serviceIntent,connection,BIND_AUTO_CREATE);
    }
    else if(TAG_STOP.equals(tag)){
      setServiceUI(true);
      unbindService(serviceIntent);
      stopService(serviceIntent);
    }
    else if(TAG_CONTROL.equals(tag)){
      try{
        binder.setMessage(""+(new Date()));
      } catch(Exception e){
      }
    }
  }

  private ServiceConnection connection = new ServiceConnection(){
    public void onServiceConnected(ComponentName name,
	    IBinder service){
	    binder = IMyService.Stub.asInterface(service);
    }

    public void onServiceDisconnected(ComponentName name){
	    binder = null;
    }
  };
}

