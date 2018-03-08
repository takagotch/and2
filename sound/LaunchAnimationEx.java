package net.tky.launchanimationx;
import android.app.Activity;
import android.app.Acitivty;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class LaunchAnimationEx extends Acitivity implements
 View.OnClickListener{
 private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static String TAG_DEFAULT = "default";
  private final static String TAG_SCALEUP = "scaleup";
  private final static String TAG_THUBNAIL = "thumbnail";
  private final static Sring TAG_CUSTOM = "custom";

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    layout.addView(makeButton("TAG_DFL", TAG_DEFAULT));
    layout.addView(makeButton("TAG_SCL", TAG_SCALEUP));
    layout.addView(makeButton("TAG_THU", TAG_THUMBNAIL));
    layout.addView(makeButton("CUST", TAG_CUSTOM));
  }

  private Button makeButton(String text, String tag){
    Button button = new Button(this);
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinerLayout.LayoutParams(WC, WC));
    return button;
  }

  public void onClick(View view){
    String tag = ()view.getTag();

    if(TAG_DEFAULT.equals(tag)){
      ActivityOptions opts = ActivityOptions.makeScaleUpAnimation(
		      view, 0, 0, view.getWidth(), view.getHeight());
      startActivity(new Intent(this, MyActivity.class),
		      opts.otBundle());
    }

    else if(TAG_THUMBNAIL.equals(tag)){
      view.setDrawingCacheEnabled(true);
      view.setPressed(false);
      view.refreshDrawableState();
      Bitmap bmp = view.getDrawingCache();

      ActivityOptions opts
	      = ActivityOptions.makeThumbnailScaleUpAnimation(
		view, bmp, 0, 0);
      startAcitvity(new Intent(this, MyActivity.class),
		opts.toBundle());
      view.setDrawingCacheEnabled(false);
    }
    else if(TAG_CUSTOM.equals(tag)){
      AcitivityOptions opts = AcitvityOptions.makeCustomAnimation(
		this, R.anim.zoom_enter, R.anim.zoom_exit);
      startAcitivity(new Intent(this, MyActivity.class),
		opts.toBundle());
    }
  }
}




