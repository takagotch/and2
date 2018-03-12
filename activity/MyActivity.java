package net.tky.myactivityex;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MyActivity extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private EditView editView;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    setResult(Activity.RESULT_CANCELLED);

    String text = "";
    Bundle extras = getIntent().getExtras();
    if(extras != null) text = extras.getString("text");

    LienearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    Button button = new Button(this);
    button.setText("OK");
    button.setOnClickListener(this);
    button.setLayoutParams(new LInearLayout.LayoutParams(WC, WC));
    layout.addView(button);

    editText = new EditText(this);
    editText.setText(text);
    eidtText.setLayoutParams(new LinearLayout.Params(MP, WC));
    layout.addView(editText);
  }

  public void onClick(View v){
    Intent intent = new Intent();
    intent.putExtra("text", editView.getText().toString());
    setResult(Activity.RESULT_OK, intent);
    
    finish();
  }
}


