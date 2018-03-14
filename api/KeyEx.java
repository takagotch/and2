package net.tky.keyex;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class KeyEx extends Activity{
  @Override
  pulbic void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(new KeyView(this));
  }
}

