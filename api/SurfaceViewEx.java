package net.tky.surfaceviewex;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SurfaceViewEx extends Activity{
  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(new SurfaceViewView(this));
  }
}


