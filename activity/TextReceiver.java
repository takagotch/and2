package net.tky.broadcastreceiverex;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Colors;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class BroadcastReceiverEx extends Activity
  implement View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle); 
    requestWindowFeature();
  }
}



