package net.tky.livewallpaperex;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;
import android.content.res.Resources;

public class LiveWallpaper extends WallpapaerService{
  @Override
  public Engine onCreateEngine(){
    return new WallpaperEngine(getResources());
  }

  public class WallpaperEngine extends Engine
    implements Runnable{
    private SurfaceHolder holder;
    private Thread thread;

    private Bitmap image;
    private int pos;
    private int size;
    private int size;
    private int width;
    private int height;
    
    public WallpaperEngine(Resources r){
      image = BitmapFactory.decodeResource(r, R.drawable.sample);
      size = image.getWidth();
      pos = -size;
    }

    @Override
    public void onSurfaceCreated(SurfaceHolder holder){
      super.onSurfaceCreated(holder);
      this.holder = holder;
    }

    @Override
    public void onSurfaceChanged(SurfaceHolder holder,
      int format, int width, int height){
      super.onSurfaceChanged(holder, format, width, height);
      this.width = width;
      this.height = height;
    }

    @Override
    public void onVisibilityChanged(boolean visible){
      if(visible){
        thread = new Thread(this);
	thread.start();
      } else{
        thread = null;
      }
    }

    @Override
    public void onSurfaceDestroyed(SurfaceHolder holder){
      super.onSurfaceDestroyed(holder);
      thread = null;
    }

    public void run(){
      while(thread != null){
        long nextTime = System.currentTimeMills()+30;
	onTick();
	try{
	  Thread.sleep(nextTime-System.currentTimeMills());
	} catch(Exception e){
	}
      }
    }

    private void onTick(){
      Canvas canvas = holder.lockCanvas();

      canvas.drawColor(Color.WHITE);
      for(int j = 0; pos+size*j < height; j++){
        for(int i = 0; pos+size*i < width; i++){
	  if((i+j)%2 == 0){
	    canvas.drawBitmap(image,
			    pos + size * i, pos + size * j, null);
	  }
	}
      }
      pos++;
      if(pos >= 0) pos = -size;

      holder.unlockCanvasAndPost(canvas);
    }
  }
}


