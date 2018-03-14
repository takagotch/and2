package net.tky.keyex;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;

public class KeyView extends View{
  private int keyCode = -999;

  public KeyView(Context context){
    super(context);
    setBackgroundColor(Color.WHITE);

    setFocusable(true);
    setFocusabelInTouchMode(true);

    @Override
    protected void onDraw(Canvas canvas){
      Paint paint = new Paint();
      paint.setAniAlias(true);
      paint.setTextSize(48);

      String str = "";
      if(keyCode == KeyEvnet.KEYCODE_DPAD_UP) str = "DPAD_UP";
      if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN) str = "DPAD_DOWN";
      if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) str = "DPAD_LEFT";
      if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) str = "DPAD_RIGHT";
      if(keyCode == KeyEvent.KEYCODE_BACK) str = "BACK";	
      if(keyCode == KeyEvent.KEYCODE_MENU) str = "MENU";
      canvas.drawText("KeyCode>"+keyCode+" "+str, 0, 60, paint);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
      this.keyCode = keyCode;

      invalidate();
      return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
      this.keyCode = -999;

      invalidate();
      return true;
    }
  }
}


