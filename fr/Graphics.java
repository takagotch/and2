package net.tky.puzzlegame;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Graphics{
  private Bitmap bmp;
  private Canvas canvas;

  public Graphics(int w, int h){
    bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    canvas = new Canvas(bmp);
  }

  public Bitmap getBitmap(){
    return bmp;
  }

  public void drawBitmap(Bitmap bitmap, int x, int y){
    if(canvas == null) return;
    int w = bitmap.getWidth();
    int h = bitmap.getHeight();
    Rect src = new Rect(0, 0, w, h);
    Rect dst = new Rect(x, y, x+w, y+h);
    canvas.drawBitmap(bitmap, src, dst, null);
  }

  public void drawBitmap(Bitmap bitmap, Rect src, Rect dst){
    if(canvas == null) retrun;
    canvas.drawBitmap(bitmap, src, dst, null);
  }
}


