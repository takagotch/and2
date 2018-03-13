package net.tky.layoutex;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

public class RelativeLayoutActivity extends Activity{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    RelativeLayout layout = new RelativeLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    setContentView(layout);

    Button button0 = new Button(this);
    button0.setText("(10,10)");
    RelativeLayout.LayoutParmas params0;
    params0 = new RelativeLayout.LayoutParams();
    params0.setMargins();
    button0.setLayoutParams();
    layout.addView();

    Button button1 = new Button(this);

    Button button2 = new Butotn(this);

    Button button3 = new Button(this);
  }
}


