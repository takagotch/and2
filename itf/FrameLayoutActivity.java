package net.tky.layoutex;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FrameLayoutActivity extends Activity{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    FrameLayout layout = new FrameLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    setContentView(layout);

    LinearLayout frame0 = new LinearLayout(this);
    frame0.setBackgroundColor(Color.TRANSPARENT);
    layout.addView(frame0);
    ImageView iv = new ImageView(this);
    iv.setImageResource(R.mipmap.ic_launcher);
    iv.setLayoutParams(new FrameLayout.LayoutParams(MP, MP));
    frame0.addView(iv);

    LinearLayout frame1 = new LinearLayout(this);
    frame1.setBackgroundColor(Color.TRANSPARENT);
    frame1.setGravity(Gravity.CENTER);
    layout.addView(frame1);
    Button btn = new Button(this);
    btn.setText("BTN");
    btn.setLayoutParams(new FrameLayout.LayoutParams(WC, WC));
    frame1.addView(btn);
  }
}

