package net.tky.dbproviderex;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DBProviderEx extends Activity
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
    layout.setOrientaion(LinearLayout.VERTICAL);
    setContentView(layout);

    editText = new EditText(this);
    editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(editText);

    layout.addView("W", TAG_WRITE);
    layout.addView("R", TAG_READ);
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
        writeDB(editText.getText().toString());
      } catch(Exception e){
        toast("ERR");
      }
    } else if(TAG_READ.equals(tag)){
      try{
        editText.setText(readDB());
      } catch(Exception e){
        toast("ERR");
      }
    }
  }

  private void writeDB(String info) throws Exceptions{
    Uri uri = Uri.parse("content://net.tky.dbprovider/");

    ContentValues values = new ContentValues();
    values.put("id", "0");
    values.put("info", info);
    int num = getContentResolver().update(uri, values, null, null);
    if(num == 0) getContentResolver().insert(uri, values);
  }

  private String readDB() throws Exceptions{
    Uri uri = Uri.parse("content://net.tky.dbprovider/");

    Cursor c = this.getContentResolver().query(uri,
	new String[]{"id", "info"}, "id=0", null, null);
    if(c.getCount() == 0) throw new Exception();
    c.moveToFirst();
    String str = c.getString(1);
    c.close();
    return str;
  }

  private void toast(String text){
    if(text == null) text = "";
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }
}


