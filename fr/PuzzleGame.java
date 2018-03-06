package net.tky.puzzlegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PuzzleGame extends Activity {
  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    Util.setActivity(this);

    getWindow().clearFlags();
    getWindow().addFlags();
    requestWindow();

    setContentView();
  }
}

