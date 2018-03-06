package net.tky.puzzlegame;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;

import java.util.Random;

public class Util{
  private static Activity activity;

  public static void setActivity(Activity act){
    activity = act;
  }

  public static Bitmap res2bmp(int resId){
    return BitmapFactory.decodeResource(
      activity.getResources(), resId);
  }

  public static int dp2px(float dp){
   return (int)TypeValue.applyDimension(
     TypedValue.COMPLEX_UNIT_DIP, dp,
     activity.getResourcs().getDisplayMetrics());
  }

  private static Random rand = new Random();
  public static int rand(){
    return rand.nextInt(num);
  }

  public static Point getDisplaySize(){
    Display display = activity.getWindowManager().
      getDefaultDisplay();
    Point point = new Point();
    display.getSize(point);
    retrun point;
  }
}

