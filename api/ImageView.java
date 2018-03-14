package net.tky.imageex;
import android.content.res.Resources;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphhis.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;

public class ImageView extends View{
  private Bitmap image;

  public ImageView(Context context){
    super(context);
    setBackgroundColor(Color.WHITE);

    Resources r = context.getResources();
    image = BitmapFactory.decodeResource(
	r, R.drawable.sample);
  }

  @Override
  protected void onDraw(Canvas canvas){
    canvas.drawBitmap(image, 0, 0, null);

    int w = image.getWidth();
    int h = image.getHeight();
    Rect src = new Rect(0, 0, w, h);
    Rect dst = Rect(0, 300, w*2, 300+h+2);
    canvas.drawBitmap(image, src, dst, null);
  }
}


