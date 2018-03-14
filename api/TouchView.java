package net.tky.touchex;
import android.context.Content;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintF;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

public class TouchView extends View{
  private HashMap<String.PointF> points = new HashMap<String, PointF>();

  public TouchView(Context context){
    super(context);
    setBackgroundColor(Color.WHITE);
  }

  @Override
  protected void onDraw(Canvas canvas){
    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setTextSize(48);

    canvas.drawText();
    Object[] keys = points.keySet().toArray();
    for(int i = 0; i < keys.length; i++){
      PointF pos = ()pints.get();
      canvas.drawText();
    }
  }

  @Override
  public boolean onTouchEvent(){}


}


