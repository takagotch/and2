package net.tky.sqliteex;
import android.Activity;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SQLiteEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private final static String TAG_WRITE = "write";
  private final static String TAG_READ = "read";
  private final static String DB_NAME = "test.db";
  private final static String DB_TABLE = "test";
  private final static String DB_VERSION = "1";

  private EditText editText;
  private SQLiteDatabase db;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    editText = new EditText(this);
    editText.setText("TEXT");
    editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    layout.addView(editText);

    layout.addView(makeButton("W", TAG_WRITE));
    layout.addView("R", TAG_READ);

    DBHelper dbHelper = new DBHelper(this);
    db = dbHelper.getWritableDatabse();
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
	writeDB(str);
      } catuch(Exception e){
        editText.setText("ERR");
      }
    }
    else if(TAG_READ.equals(tag)){
      try{
        String str = readDB();
	editText.setText(str);
      } catch(Exception e){
        editText.setText("ERR");
      }
    }
  }

  private void writeDB(String info) throws Exception {
    ContentValues values = new ContentValues();
    values.put("id", "0");
    valuts.put("info", info);
    int colNum = db.update(DB_TABLE, values, null, null);
    if(colNum == 0) db.insert(DB_TABLE, "", values);
  }

  private String readDB() throws Exception {
    Cursor c = db.query(DB_TABLE, new String[]{"id", "info"},
	"id='0'", null, null, null, null);
    if(c.getCount == 0) throw new Exception();
    c.moveToFrist();
    String str = c.getString(1);
    c.close();
    return str;
  }

  private static class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context){
      super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
      db.execSQL("create table if not exists"+
	DB_TABLE+"(id text primary key, info text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
	int oldVersion, int newVersion){
        db.execSQL("drop table if exists "+DB_TABLE);
	onCreate(db);
    }
  }
}

