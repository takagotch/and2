package net.tky.touchex;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class TouchEx extends Activity{
  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(new TouchView(this));
  }
}


