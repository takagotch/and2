package net.tky.recognizespeechex;
import android.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import andoird.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class RecognizeSpeechEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private EditText editText;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    editText = new EditText(this);
    editText.setLayoutParams(new LinearLayout.LayoutParams(WP, WC));
    layout.addView(editText);
    layout.addView(makeButton("RECG", "recog"));
  }

  private Button makeButton(String text, String tag){
    Button button = new Button(this);
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  public void onClick(View v){
    try{
      Intent intent = new Intent(
		      RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
      intent.putExtra(
		      RecognizerIntent.EXTRA_LANGUAGE_MODEL,
		      RecognizerIntent.LANGUAGE_MODEL_FREE_FROM);
      intent.putExtra(
		      RecognizerIntent.EXTRA_PROMPT,
		      "RecognizeSpeechEx");
    } catch(ActivityNotFoundException e){
      toast(e.getMessage());
    }
  }

  protected void onActivityResult(int requestCode,
		  int resultCode, Intent data){
    if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
      String str = "";
      ArrayList<String> results =
	      data.getStringArrayListExtra(
	      RecoginizerIntent.EXTRA_RESULTS);
      for(int i = 0; i < results.size(); i++){
        str += results.get(i)+" ";
      }
      editText.setText(str);
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  private void toast(String text){
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }
}


