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
    imgPic = Util.res2bmp();

    Point displaySize = Util.getDisplaySize();

    g = new Graphics(W, H);

    initScene(S_TITLE);
  }

  private void initScene(int scene){
    this.scene = scene;

    if(){
    
    }

    else if(){
    
    
    }
    invalidate();
  }

  @Override
  protected void onDraw(){
  
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

