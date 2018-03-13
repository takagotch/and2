package net.tky.layoutex;
import android.app.Activity;

public class LinearLayoutActivity extends Activity{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setGravity(Gravity.LEFT|Gravity.TOP);
    setContentView(layout);

    int[] width = new int[]{MP, 300, 300, 300, 300, 300};
    for(int i = 0; i < 6; i++){
      Button button = new Button(this);
      button.setText("("+i+")");
      button.setLayout(
	new LinearLayout.LayoutParams(width[i], WC));
      layout.addView(button);
    }
  }
}


