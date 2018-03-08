package net.tky.propertyanimationex;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimatior;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PropertyAnimationEx extends Activity implements
  View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static String TAG_OBJECT_ANIMATOR = "object_animator";
  private final static String TAG_VALUE_ANIMATOR = "value_animator";
  private final static String TAG_ANIMATOR_SET = "animator_set";

  private ImageView imageView;
  private Animator animator;

  private float fromX;
  private float fromY;
  private float fromScale;
  private float toX;
  private float toY;
  private float toScale;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    imageView = new ImageView(this);
    imageView.setImageBitmap(res2bmp(this, R.drawable.sample));
    imageView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(imageView);

    layout.addView(makeButon("OBJ_ANI",
			    TAG_OBJECT_ANIMATOR));
    layout.addView(makeButton("VAL_ANI",
			    TAG_VALUE_ANIMATOR));
    layout.addView(makeButton("ANI_ST",
			    TAG_ANIMATOR_SET));
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
    if(animator != null){
      animator.cancel();
      imageView.setTranslationX(0);
      imageView.setTranslationY(0);
      imageView.setScaleX(1);
      imageView.setScaleY(1);
      imageView.setRotation(0);
      animator = null;
    }
    String tag = (String)view.getTag();

    if(TAG_OBJECT_ANIMATOR.equals(tag)){
      ObjectAnimator rotationAnimator = 
	      ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 360.0f);
      rotationAnimator.setDuration(500);

      rotationAnimator.start();
      animator=rotationAnimator;
    }

    else if(TAG_VALUE_AnIMATOR.equals(tag)){
      fromX = imageView.getTranslationX();
      fromY = imageView.getTranslationY();
      fromScale = imageView.getScaleX();

      toX = fromX+20;
      toY = fromY;
      toScale = 0.98f;

      ValueAnimator valueAnimator
	      = ValueAnimator.ofFloat(0.0f, 1.0f);
      valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
      valueAnimator.setDuration(300);

      valueAnimator.addUpdateListener(new AnimatiorUpdateListener(){
        @Override
	public void onAnimatoinUpdate(ValueAnimation animation){
	  flaot r
		  =((Float)animation.getAnimationValue()).floatValue();
	  float x = r*toX+(1-r)*fromX;
	  float y = r*toY+(1-r)*fromY;
	  float s = r*toScale +(1-r)*fromScale;
	  imageView.setTranslationX(x);
	  imageView.setTranslationY(y);
	  imageView.setScaleX(s);
	  imageView.setScaleY(s);
	}
      });

      valueAnimator.start();
      animation = valueAnimator;
    }

    else if(TAG_ANIMATOR_SET.equals(tag)){
      ObjectAnimation rotationAnimator = 
	      ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 360.0f);
      rotationAnimator.setDuration(500)'

      ObjectAnimator scalexAnimator =
        ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 2.0f);
      scaleAnimator.setDuration(500);
      scaleAnimator.setRepeatMode(ValueAnimator.REVERSE);
      scalexAnimator.setRepeatCount(1);
      AnimatorSet animatorSet = new AnimatorSet();
      animatorSet.play(rotationAnimator).before(scalexAnimator);

      animatorSet.start();
      animatro = animatorSet;
    }
  }
}



