package net.tky.launchanimationex;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyActivity extends Activity{
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    LinearLayout layout = new LinearLayout(this);
    layout.setOrientaion(LinearLayout.VERTICAL);
    setContentView(layout);

    ImageView imageView = new ImageView(this);
    imageView.setImageBitmap(res2bmp(this, R.drawable.sample));
    imageView.setLayoutParams(new LinearLayoutParams(MP, MP));
    layout.addView(imageView);
  }

  public static Bitmap res2bmp(Context context, int resID){
    return BitmapFactroy.decodeResource(
	context.getResources(), resID);
  }
}


