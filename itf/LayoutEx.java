package net.tky.layoutex;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class Layout extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static String TAG_LINEAR = "linear";
  private final static String TAG_RELATIVE = "relative";
  private final static String TAG_TABLE = "table";
  private final static String TAG_GRID = "grid";
  private final static String TAG_FRAME = "frame";

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.FEATURE_NO_TITLE);
    setContentView(layout);

    layout.addView(makeButton("T_L", TAG_LINEAR));
    layout.addView(makeButton("T_R", TAG_RELATIVE));
    layout.addView(makeButton("T_G", TAG_GRID));
    layout.addView(makeButton("T_F", TAG_FRAME));
  }

  private Button makeButton(String text, String tag){
    Button button = new Button(this);
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  public void onClick(View v){
    String tag = (String)v.getTag();
    if(TAG_LINEAR.equals(tag)){
      Intent intent = new Intent(this, LinearLayoutActivity.class);
      startActivity(intent);
    } else if(TAG_RELATIVE.equals(tag)){
      Intent intent
	      = new Intent(this, RelativeLayoutActivity.class);
      startActivity(intent);
    } else if(TAG_TABLE.equals(tag)){
      Intent intent = new Intent(this, TableLayoutActivity.class);
      startActivity(intent);
    } else if(TAG_TABLE.equals(tag)){
      Intent intent = new Intent(this, GridLaoutActivity.class);
      startActivity(intent);
    } else if(TAG_FRAME.equals(tag)){
      Intent intent = new Intent(this, FrameLayoutActivity.class);
      startActivity(intent);
    }
  }
}


