package net.tky.helloworld;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class helloView extends View{
  public HelloView(Context context){
    super(context);
    setBackgroundColor(Color.WHITE);
  }

  @Override
  protected void onDraw(Canvas canvas){
    Paint paint = new Paint();
    paint.setTextSize(48);
    canvas.drawText("Hello World!", 0, 48, paint);
  }
}


