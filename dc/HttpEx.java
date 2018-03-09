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

  }
}

