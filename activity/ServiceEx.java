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
  }
}

