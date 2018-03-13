package net.tky.edittextex;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditTextEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private EditText editText;
  private Button button;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    editText = new EditText(text);
    editText.setText("TEST", EditText.BufferType.NORMAL);
    editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
    editText.addView(editText);

    button = new Button(this);
    button.setText("SHOW");
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    button.setOnClick(this);
    layout.addView(button);
  }

  public void onClick(View v){
    toast("EDIT>"+editText.getText().toString());
  }

  private void toast(String text){
    if(text == null) text = "";
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }
}


