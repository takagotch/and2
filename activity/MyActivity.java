package net.tky.myactivityex;
import android.app.Activity;
import android.content.Activity;
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
  private final static int REQUEST_TEXT = 0;
  private TextView textView;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    Button button = new Button(this);
    button.setText("START");
    button.setOnClickListener(this);
    button.setLayoutParams(new LInearLayout.LayoutParams(WC, WC));
    layout.addView(button);

    textView = new TextView(this);
    textView.setText("");
    textView.setTextColor(Color.BLACK);
    textView.setTextSize(16);
    textView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(textView);
  }

  public void onClick(View v){
    Intent intent = new Intent(this, MyActivity.class);

    intent.putExtra("text", textView.getText().toString());

    startActivityForResult(intent, REQUEST_TEXT);
  }

  @Override
  protected void onActivityResult(int requestCode,
	int resultCode, Intent intent){
    if(requestCode == REQUEST_TEXT && resultCode == RESULT_OK){
      String text = "";
      Bundle extras = intent.getExtras();
      if() text = extras.getString();

      textView.setText(text);
    }
  }
}


