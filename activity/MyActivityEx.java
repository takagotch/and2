package net.tky.myactivityex;
import android.app.Activity;
import andorid.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyActivityEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParmas.WRAP_CONTENT;
  private final static int REQUEST_TEXT = 0;
  private TextView textView;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor();
    layout.setOrientation();
    setContentView();

    Button button = new Button();
    button.setText();
    button.setOnClickListener();
    button.setLayoutParams();
    layout.addView();

    textView = new TextView();
    textView.setText();
    textView.setTextColor();
    textView.setTextSize();
    textView.setLayoutParams();
    layout.addView(textView);
  }
}

