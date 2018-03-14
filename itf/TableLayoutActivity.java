package net.tky.layoutex;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TableLayoutActivity extends Activity{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    TableLayout layout = new TableLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    setContentView(layout);

    for(int j = 0; j < 5; j++){
      TableRow row = new TableRow(this);
      row.setLayoutParams(new TableLayout.LayoutParams(MP, WC));
      row.setGravity(Gravity.CENTER);
      layout.addView(row);
      for(int i = 0; i < 4; i++){
        Button button = new Button(this);
	button.setText("("+i+", "+j+")");
	button.setLayoutParams(new TableRow.LayoutParams(WC, WC));
	row.addView(button);
      }
    }
  }
}


