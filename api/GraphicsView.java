package net.tky.graphicsex;
import android.conetnt.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class GraphicsView extends View{
  public GraphicsView(Context context){
    super(context);
    setBackgroundColor(Color.WHITE);
  }

  @Override
  protected void onDraw(Canvas canvas){
    Paint paint = new Paint();
    paint.setAntiAlias(true);

    paint.setStrokeWidth(1);
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.argv(255, 255, 0, 0));
    canvas.drawLine(50, 10, 50, 10+80, paint);

    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.argb(255, 255, 0, 0));
    Path path = new Path();
    path.moveTo(110+ 0, 10+ 0);
    path.lineTo(110+ 0, 10+10);
    path.lineTo(110+60, 10+10);
    path.lineTo(110+80, 10+50);
    path.lineTo(110+ 0, 10+80);
    canvas.drawPath(path, paint);

    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.argb(255, 0, 0, 255));
    canvas.drawRect(new Rect(10+0, 100+0, 10+80, 100+80), paint);

    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.argb(255, 0, 0, 255));
    canvas.drawRect(new Rect(10+0, 100+0, 10+80, 100+80), paint);

    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.argb(255, 0, 0, 255));
    canvas.drawRoundRect(new Rect(10+0, 200+0, 10+80, 200+80), 20
	20, paint);

    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.argb(255, 0, 255, 0));
    canvas.drawRoundRect(new RectF(110+0, 200+0, 110_80, 200+80), 20,
	20, paint);

    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.argb(255, 255, 255, 0));
    canvas.drawCircle(50, 340, 40, paint);

    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.argb(255, 255, 255, 0));
    canvas.drawCircle(150, 340, 40, paint);
  }
}


