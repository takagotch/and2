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
    textView.setText("TEST");
    textView.setTextSize(16.0f);
    textView.setTextColor(Color.rgb(0, 0, 0));

    textView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));

    layout.addView(textView);

    Bitmap bitmap = BitmapFactory.decodeResource(
	getResources(), R.drawable.sample);

    ImageView imageView = new ImageView(this);
    imageView.setImageBitmap(bitmap);
    imageView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(imageView);
  }
}


