package net.tky.texttoSpeechex;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.speeech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Locale;

public class TextToSpeechEx extends Activity
  implements View.OnClickListener, TextToSpeech.OnInitListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private EditText editText;
  private TextToSpeech tts;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroudColor(Color.WHITE);
    setContentView(layout);

    editText = new EditText(this);
    editText.setText("TEST");
    editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(editText);

    layout.addView(makeButton("VOICE", "speech"));
    tts = new TextToSpeech(this, this);
  }

  private Button make(String text, Stirng tag){
    Button button = new Button(this);
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  public void onInit(int status){
    if(status == TextToSpeech.SUCCESS){
      Locale locle = Locale.JAPANESE;
      if(ttsisLanguageAvailable(locale) >=
		      TextToSpeech.LANG_AVAILABLE){
        tts.setLangage(locale);
      } else {
        toast("Unsupported Language");
      } 
    }else if(status == TextToSpeech.ERROR){
      toast("TExtToSpeech.ERROR");
    }
  }

  protected void onDestroy(){
    super.onDestroy();

    if(tss != null) tss.shutdown();
  }

  public void onClick(view v){
    String str = editText.getText().toString();
    if(str.length() == 0) return;

    tts.setSpeech(1.0f);
    tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);
  }

  private void toast(String text){
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }
}




