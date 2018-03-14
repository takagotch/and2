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

    canvas.drawText("TouchEx>", 0, 60*1, paint);
    Object[] keys = points.keySet().toArray();
    for(int i = 0; i < keys.length; i++){
      PointF pos = (PointF)pints.get(keys[i]);
      canvas.drawText((int)pos.x+","+(int)pos.y, 0, 120+60*i,
	paint);
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event){
    int action = event.getAction();
    int count = event.getPointerCount();

    int index = event.getActionIndex();
    int pointerID = event.getPointerId(index);

    switch(action&MotionEvent.ACTION_MASK){
      case MotionEvent.ACTION_DOWN:
      case MotionEvent.ACTION_PINTER_DOWN:
	      pints.put(""+pointerID, new PointF(event.getX(),
			event.getY()));
           break;
      case MotionEvent.ACTION_MOVE:
	   for(int i = 0; i < count; i++){
	     PointF pos = points.get(""+event.getPointerId(i));
	     pos.x = event.getX(i);
	     pos.y = event.getY(i);
	   }
	   break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_POINTER_UP:
      case MotionEvent.ACTION_CANCEL:
	   points.remove(""+pointerID);
	   break;
    }
    invalidate();
    return true;
  }


}


