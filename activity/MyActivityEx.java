package net.tky.myactivityex;
import android.app.Activity;
import andorid.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyActivityEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParmas.WRAP_CONTENT;
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
    button.setLayoutParams(new LinearLayout.LayoutPrams(WC, WC));
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
	  if(extras != null) text = extras.getString("text");
	  
	  textView.setText(text);
	}
  }
}

