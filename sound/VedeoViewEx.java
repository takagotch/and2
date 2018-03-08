package net.tky.videoview;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.VieoView;
import android.widget.MediaController;

public class VideoViewEx extends Activity{
  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    try{
      VideoView videView = new VideoView(this);
      videoView.requestFocus();
      videoview.setMediaController(new MediaController(this));
      setController(videoView);

      videoView.setVideoURI(Uri.parse("android.resource://"+
			      getPackageName()+"/"+R.raw.sample));
      videoView.start();
    } catch (Exception e){
      android.util.Log.e("", e.toStirng());
    }
  }
}

