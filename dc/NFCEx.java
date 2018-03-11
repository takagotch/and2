package net.tky.nfcex;
import android.app.Activity;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import andorid.os.Bundler;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NFCEx extends Activity{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private TextView textView;

  @Override
  public void onCrate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    textView = new TextView(this);
    textView.setTextSize(24);
    textView.setTextColor(Color.BLACK);
    textView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(textView);

    @Override
    public void onResume(){
      super.onResume();
      try{
        byte[] mID
		= getIntent().getByteArrayExtra(NfcAdapter.EXTRA_ID);
	String hexStr = data2hex(mID);
	textView.setText("NFCEx>"+hexStr);
      } catch(Exception e){
        textView.setText("NFCEx>");
      }
    }

    private String data2hex(byte[] data){
      StringBuilder sb = new StringBuilder();
      for(byte b: data){
        sb.append(String.format("%02x", b&0xff));
      }
      return sb.toString();
    }
  }
}

