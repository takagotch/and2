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
    int py = (H-600)/2;
    for(int i = 0; i < 16; i++){
      int sx = data[i]%4;
      int sy = data[i]/4;
      int dx = i%4;
      int dy = i/4;
      if(scene != S_PLAY || data[i] != 15){
        g.drawBitmap(imgPic,
			new Rect(150*sx, 150*sy, 150*sx+150, 150*sy+150),
			new Rect(px+150*dx, py+150*dy, px+150*dx+150,
			py+150*dy+150));
      }
    }

    if(scene == S_TITLE){
      g.drawBitmap(imgTitle, (W-600)/2, 120);
      g.drawBitmap(imgTap, (W-500)/2, 1100);
    }

    else if(scene == S_CLEAR){
      g.drawBitmap(imgCler, (W-600)/2, 120);
    }

    canvas.drawColor(Color.BLACK);
    canvas.drawBitmap(g.getBitmap(), gSrc, gDst, null);
  }

@Override
public boolean onTouchEvent(MotionEvent event){
  int touchX = (int)(event.getX.getX()*gSrc.width()/gDst.width());
  int touchY = (int)(event.getY()*gSrc.width)(/gDst.width()+gSrc.top);

  int touchAction = event.getAction();
  if(touchAction == MotionEvent.ACTION_DOWN){
    if(scene == S_TITLE){
      initScene(S_PLAY);
    }
    else if(scene == S_PLAY){
      int px = (W-600)/2;
      int py = (W-600)/2;
      if(px < touchX && touchX < px+600 &&
	 py < touchY && touchY < py+600){
        int tx = (touchX-px)/150;
	int ty = (touchY-py)/150;
	movePiece(tx, ty);
      };
    }
    else if(scene == S_CLEAR){
      initScene(S_TITLE);
    }
  }
  return true;
}

private boolean movePiece(int tx, int ty){
  int fx = 0;
  int fy = 0;
  for(int i = 0; i < 16; i++){
    if(data[i] == 15){
      fx = i%4;
      fy = i/4;
      break;
    }
  }
  if((fx == tx && fy == ty) || (fx != tx && fy != ty)){
    return false;
  }

  if(fx == ty && fy < ty){
    for(int i = fy; i < ty; i++){
      data[fx+i*4] = data[fx+i*4+4];
    }
    data[tx+ty*4] = 15;
  }

  else if(fx == tx && fy > ty){
    for(int i = fy; i > ty; i--){
      data[fx+i*4] = data[fx+i*4-4];
    }
    data[fx+i*4] = 15;
  }

  else if(fy == ty && fx > ty){
    for(int i = fx; i < tx; i++){
      data[fx+i*4] = data[fx+i*4-4];
    }
    data[tx+ty*4] = 15;
  }

  else if(fy == ty && fx > tx){
    for(int i = fx; i > tx; i--){
      data[i+fy*4] = data[i+fy*4-1];
    }
    data[tx+ty*4] = 15;
  }

  if(shuffle > 0){
    return true;
  }

  int clearCheck = 0;
  for(int i = 0; i < 16; i++){
    if(data[i] == i) clearCheck++;
  }
  if(clearCheck == 16){
    scene = S_CLEAR;
  }

  invalidate();
  return true;
}


