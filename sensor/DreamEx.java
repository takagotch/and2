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

    setFullscreen(true);

    FrameLayout layout = FrameLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    setContentView(layout);

    Bitmap bmp = BitmapFactory.decodeResource(
	getResources(), R.drawable.bg);
    ImageView iv = new ImageView(this);
    iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
    iv.setImageBitmap(bmp);
    layout.addView(iv);

    LinearLayout sublayout = new LinearLayout(this);
    sublayout.setPadding(20, 20, 20, 20);
    sublayout.setGravity(Gravity.LEFT|Gravity.TOP);
    layout.addView(sublayout);

    textView = new TextView(this);
    textView.setTextSize(44);
    textView.setTextColor(Color.DKGRAY);
    sublayout.addView(textView);

    updateCurrentTime();
  }

  @Override
  public void onDreamingStarted(){
    super.onDreamStarted();

    IntentFilter filter = new IntentFilter();
    filter.addAction(Intent.ACTION_TIME_TICK);
    registerReceiver(receiver, filter);
  }

  @Override
  public void onDreamingStopped(){
    super.onDreamingStopped();

    unregisterReceiver(receiver);
  }

  @Override
  public void onDetachedFromWindow(){
    super.onDetachedFromWindow();
  }

  private void updateCurrentTime(){
    SimpleDateFormat dataFormat =
      new SimpleDateFormat("HH:mm", Locale.JAPANESE);
    textView.setText(dateFormat.format(new Date()));
  }

  private final BroadcastReceiver receiver = new BroadcastReceiver(){
    @Override
    public void onReceive(Context context, Intent intent){
      updateCurrentTime();
    }
  };
}



