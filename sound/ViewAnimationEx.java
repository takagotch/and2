package net.tky.viewanimationex;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class ViewAnimationEx extends Activity implements
  View.OnClickListener{
  private final static int WC = viewGroup.LayoutParams.WRAP_CONTENT;
  private final static String TAG_TRANSLATE = "translate";

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    imageVew = new ImageView(this);
    imageView.setImageBitmap(res2bmp(this, R.drawable.sample));
    imageView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(imageView);

    layout.addView(makeButton("T_TR", TAG_TRANSLATE));
    layout.addView(makeButton("T_SC", TAG_SCALE));
    layout.addView(makeButton("T_RT", TAG_ROTATE));
    layout.addView(makeButton("T_AL", TAG_ALPHA));
    layout.addView(makeButton("T_ST", TAG_SET));

  }

  public static Bitmap res2bmp(Context context, int resID){
    return BitmapFactory.decodeResource(
		    context.getResources(), resID);
  }

  private Button makeButton(String text, String tag){
    Button button = new Button(this);
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  public void onClick(View view){
    String tag = (String)view.getTag();

    if(TAG_TRANSLATE.equals(tag)){
      TranslateAnimation trans
	      = new TranslateAnimation(0, 320, 0, 0);
      trans.setDuration(500);
      trans.setInterpolator(new OvershootInterpolater);
      imageView.starAnimation(trans);
    }

    else if(TAG_SCALE.equals(tag)){
      ScaleAnimation scale
	      = new ScaleAnimation(1, 2, 1, 2, 120, 120);
      scale.setDuration(500);
      scale.setInterpolator(new AnimationOvershootInterpolator());
      imageView.startAnimation(scale);
    }

    else if(TAG_ROTATE.equals(tag)){
      RotateAnimation rotate
	      = new RotateAnimation(0, 360, 120, 120);
      rotate.setDuration(500);
      rotate.setIneterpolator(new LinearInterpolator());
      imageView.startAnimation(rotate);
    }

    else if(TAG_ALPHA.equals(tag)){
     AlphaAnimation alpha = new AlphaAnimation(1, 0);
     alpha.setDuration(500);
     alpha.setInterpolator(new LinearInterpolator());
     imageView.startAnimation(alpha);
    }

    else if(TAG_SET.equals(tag)){
      AnimationSet set = new AnimationSet(true);
      set.setInterpolator(new AnticipateOvershootInterpolator());

      ScaleAnimationSet scale
	      = new ScaleAnimation(1, 2, 1, 2, 120, 120);
      scale.setDuration(500);
      set.addAnimation(scale);

      TranslateAnimation trans
	      = new TranslateAnimation(0, 320, 0, 0);
      trans.setDuration(500);
      set.addAnimation(trans);

      imageView.startAnimation(set);
    }
  }

  }



