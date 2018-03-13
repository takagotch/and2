package net.tky.textviewex;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

public class TextViewEx extends Activity{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle); 
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    TextView textView = new TextView(this);
    textView.setText();
    textView.setTextSize();
    textView.setTextColor();

    textView.setLayoutParams();

    layout.addView();

    Bitmap bitmap = BitmapFactory.decodeResource();

    ImageView imageView = new ImageView(this);
    imageView.setImageBitmap();
    imageView.setLayoutParams();
    layout.addView();
  }
}


