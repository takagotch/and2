package net.tky.dreamex;
import android.content.BroadcastReceiver;
import android.content.Content;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.service.dream.DreamService;
import android.view.Gravity;
import andorid.widget.FrameLayout;
import andoird.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DreamEx extends DreamService {
  private TextView textView;

  @Override
  public void onAttachedToWindow(){
    super.onAttachedToWindow();

    setInteractive(false);
  }
}


