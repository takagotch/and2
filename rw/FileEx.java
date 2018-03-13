package net.tky.fileex;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.wiget.LinearLayout;
import android.ByteArrayOutputStream;
import android.InputStream;
import android.OutputStream;
import java.io.OutputStream;

public class FileEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private final static String TAG_WRITE = "write";
  private final static String TAG_READ = "read";
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
    editText.setText("TEST");
    editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(editText);
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

    if(TAG_WRITE.equals(tag)){
      try{
        String str = editText.getText().toString();
	data2file(this, str.getBytes(), "test.txt");
      } catch(Exception e){
	      editText.setText("ERR");
      }
    }
    else if(TAG_READ.equals(tag)){
      try{
        String str = new String(file2data(this, "test.txt"));
	editText.setText(str);
      } catch(Exception e){
	      editText.setText("ERR");
    }
  }
}

private static void data2file(Context context,
  byte[] w, String fileName) throws Exception{
  OutputStream out = null;
  try{
    out = context.openFileOutput(fileName,
	Context.MODE_PRIVATE);
    out.write(w, 0, w.length);
    out.close();
  } catch(Exception e){
    try{
      if(out != null) out.close();
    } catch(Exception e2){
    }
    throw e;
  }
}

private static byte[] file2data(Context context,
  String fileName) throws Exception {
  int size;
  byte[] w = new byte[1024];
  InputStream in = null;
  ByteArrayOutputStream = null;
  try{
    in = context.openFileInput(filename);

    out = new ByteArrayOutputStream();
    while(true){
      size = in.read(w);
      if(size <= 0) break;
      out.write(w, 0, size);
    }
    out.close();

    in.close();

    return out.toByteArray();
  } catch(Exception e){
    try{
      if(in != null) in.close();
      if(out != null) out.close();
    } catch(Exception e2){
    }
    throw e;
  }
}

}



