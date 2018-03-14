package net.tky.gestureex;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GestureView extends View implements
  GestureDetector.OnGestureListener,
  GestureDetector.OnDoubleTapListener{
  private ArrayList<String> info;
  private GestureDetector gestureDetector;

  public GestureView(Context context){
    super(context);
    setBackgroundColor(Color.WHITE);

    info = new ArrayList<String>();
    info.add("GestureEx>");

    gestureDetector = new GestureDetector(context, this);
  }

  @Override
  protected void onDraw(Canvas canvas){
    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setTextSize(48);

    for(int i = 0; i < info.size(); i++){
      canvas.drawText((String)info.get(i), 0, 60+60*i, paint);
    }
  }

  private void addInfo(String str){
    info.add(1, str);
    while(info.size() > 30) info.remove(info.size()-1);
    invalidate();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event){
    gestureDetector.onTouchEvent(event);
    return true;
  }

  public boolean onDown(MotionEvent e){
    addInfo("Down");
    return false;
  }

  public void onShowPress(MotionEvent e){
    addInfo("ShowPress");
  }

  public boolean onSingle(MotionEvent e){
    addInfo("Up");
    return false;
  }

  public void onLongPress(MotionEvent e){
    addInfo("LongPress");
  }

  public boolean onFling(MotionEvent e0, MotionEvent e1,
	float velocityX, float velocityY){
        addInfo("Fling("+(int)velocityX+", "+(int)velocityY+")");
	return false;
  }

  public boolean onScroll(MotionEvent e0, MotionEvent e1,
	float distanceX, flaot distanceY){
        addInfo("Scroll("+(int)distanceX+", "+(int)distanceY+")");
	return fasle;
  }

  public boolean onSingleTapConfirmed(MotionEvent e){
    addInfo("SingleTap");
    return false;
  }

  public boolean onDoubleTap(MotionEvent e){
    addInfo("DoubleTap");
    return false;
  }

  public boolean onDoubleTapEvent(MotionEvent e){
    addInfo("DoubletapEvnet");
    return false;
  }
}


