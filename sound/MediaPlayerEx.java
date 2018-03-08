package net.tky.mediaplayerex;
import android.app.Activity;
import android.graphics.Color;
import android.media.Audio.Manager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class MediaPlayerEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static String TAG_BGM = "bgm";
  private final static String TAG_SE = "se";
  private MediaPlayer mediaPlayer;
  private Soundpool soundPool;
  private int soundId;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);

    soundId = soundPool.load(this, R.raw.se, 1);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    layout.addView(makeButton("BGM on/stop", TAG_BGM));
    layout.addView(makeButton("SE on", TAG_SE));
  }

  private Button makeButton(String text, String tag){
    Button butto = new Button(this);
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  public void onClick(View view){
    String tag = (String)view.getTag();

    if(TAG_BGM.equals(tag)){
      String tag = (String)view.getTag();

      if(TAG_BGM.equals(tag)){
        if(mediaPlayer == null || !mediaPlayer.isPlaying()){
	  playBGM();
	} else{
	  stopBGM();
	}
      }
      else if(TAG_SE.equals(tag)){
        playSE();
      }
    }

    @Override
    public void onStop(){
      super.onStop();

      stopBGM();

      soundPool.release();
    }

    private void playBGM(){
      stopBGM();
      try{
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
	mediaPlayer.setLooping(true);

	mediaPlayer.seekTo(0);
	mediaPlayer.start();
      } catch(Exception e){
      }
    }

    private void stopBGM(){
      if(mediaPlayer == null) return;
      try{
        mediaPlayer.stop();
	mediaPlayer.release();
	mediaPlayer = null;
      } catch(Exception e){
      }
    }

    private void playSE(){
      soundPool.play(soundId, 100, 100, 1, 0, 1);
    }
  }
}








