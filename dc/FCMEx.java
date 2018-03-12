package net.tky.fcmex;

import android.app.Activity;

public class FCMEx extends Activity{
  private TextView textView;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    textView = new TextView(this);
    textView.setTextColor(Color.BLACK);
    textView.setText("FCMEx>");
    layout.addView(textView);
  }
}

