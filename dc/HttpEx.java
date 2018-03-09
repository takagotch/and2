package net.tky.httpex;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.HttpURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class HttpEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private final static String TAG_READ = "read";
  private EditText editText;
  private String text;
  private Handler handler = new Handler();

  private final static String URL =
	  "http://tky.net/android/test.txt";

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    editText = new EditText(this);
    editText.setText("");
    editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(editText);

    layout.addView(makeButton("HTTP DC", TAG_READ));
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
    String tag = (String)v.getTag();
    if(TAG_READ.equals(tag)){
      Thread thread = new Thread(new Runnable() {public void run(){
        try{
	  text = new String(http2data(URL));
	} catch(Exception e){
	  text = null;
	}
	handler.post(new Runnable() {public void run(){
	  if(text != null){
	    editText.setText(text);
	  } else{
	    editText.setText("ERR");
	  }
	}});
      }});
      thread.start();
    }
  }

  public static byte[] http2data(String path) throws Exception{
    byte[] w=new byte[1024];
    HttpURLConnection c = null;
    InputStream in = null;
    ByteArrayOutputStream out = null;
    try{
      URL url = new URL(path);
      c = ()url.openConnection();
      c.setRequestMethod();
      c.connect();
      in = c.getInputStream();
    }
    out.close();

    in.close();
    c.disconnect();
    return out.toByteArray();
  } catch(Exception e){
    try{
    
    } catch(){
    }

  }
}


