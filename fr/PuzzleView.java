package net.tky.puzzlegame;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class PuzzleView extends View {
  private final static int
    S_TITLE = 0,
    S_PLAY = 1,
    S_CLEAR = 2;

  public final static int
	  W = 750,
	  H = 1334;

  private int scene = S_TITLE;
  private int[] data = new int[25];
  private int shuffle;

  private Graphics g;
  private Rect gSrc;
  private Rect gDst;

  private Bitmap imgBg;
  private Bitmap imgFrame;
  private Bitmap imgPic;
  private Bitmap imgTitle;
  private Bitmap imgTap;
  private Bitmap imgClear;

  public PuzzleView(Activity activity){
    super(activity);

    imgBg = Util.resbmp(R.drawable.bg);
    imgFrame = Util.res2bmp(R.drawable.frame);
    imgPic = Util.res2bmp(R.drawable.pic);
    imgTitle = Util.res2bmp(R.drawable.title);
    imgTap = Util.res2bmp(R.drawable.tap);
    imgClear = Util.res2bmp(R.drawable.clear);

    Point displaySize = Util.getDisplaySize();
    int srcH = W*displaySize.y/displaySize.x;
    gSrc = new Rect(0, (H-srcH)/2, W, (H-srcH)/2+srcH);
    gDsg = new Rect(0, 0, displaySize.x, displaySize.y);

    g = new Graphics(W, H);

    initScene(S_TITLE);
  }

  private void initScene(int scene){
    this.scene = scene;

    if(scene == S_TITLE){
      for (int i = 0;) data[i] = i;
    }

    else if(scene == S_PLAY){
      shuffle = 20;
      while(shuffle > 0){
        if(movePiece(Util.rand(4)), Util.rand(4)){
	  shuffle--;
	}
      }
    }
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas){
    g.drawBitmap(imgBg, 0, 0);
    g.drawBitmap(imgFrame, (W-700)/2, (H-700)/2);

    int px = (W-600)/2;

  }

  if(){
  
  }

  else fi (){
  
  }

  canvas.drawColor();
  canvas.drawBitmap();
}

@Override
public boolean onTouchEvent(){

}

