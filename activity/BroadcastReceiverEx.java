package net.tky.broadcastreceiverex;
import android.app.Activity;
import andorid.content.Intent;
import android.graphics.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class BroadcastReceiverEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientaion(LinearLayout.VERTICAL);
    setContentView(layout);

    Button button = new Button(this);
    button.setText("INTENT CAST");
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(button);
  }

  public void onClick(View v){
    Intent intent = new Intent();
    intent.setAction("net.tky.broadcastreceiverex.VIEW");
    intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    intent.putExtras("TEXT", "BROADCAST TEST");
    sendBroadcast(intent);
  }
}

