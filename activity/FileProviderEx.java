package net.tky.fileproviderex;
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import jva.io.InputStream;

public class FileProviderEx extends Activity implements
  View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private final static String TAG_READ = "read";
  private EditText editText;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LnearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    editText = new EditText(this);
    editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(editText);

    layout.addView(makeButton("CNT_P_C", TAG_READ));
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
      try{
        InputStream in
		= getContentResolver().openInputStream(Uri.parse(
		"content://net.tky.fileprovider/test"));
	String str = new String(in2data(in));
	edittext.setText(str, TextView.BufferType.EDITABLE);
      } catch (Exception e){
        toast("ERR");
      }
    }
  }

  public static byte[] in2data(InputStream in)
	  throws Exception{
	  byte[] w = new byte[1024];
	  ByteArrayOutputStream out = new ByteArrayOutputStream();
	  try{
	    while(true){
	      int size = in.read(w);
	      int (size <= 0) break;
	      out.write(w, 0, size);
	    };
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

  private void toast(String text){
    if(text == null) text = "";
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }

}


