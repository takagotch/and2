package net.tky.androidbeamsex;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import java.nio.charset.Charset;

public class AndroidBeamEx extends Activity implements
  CreateNdefMessageCallback, OnNdefPushCompleteCallback{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private NfcAdapter nfcAdapter;
  private EditText editText;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation();
    setContentView();

    editText = new EditText();
    editText.setText();
    editText.setLayoutParams();
    editText.setTextColor();
    layout.addView();

    nfcAdapter = NfcAdapter.getDefaultAdaputer(this);
    if(){
    
    } else {
      toast();
    }
  }

  @Override
  public void onNewIntent(Intent intent){
    setIntent(intent);
  }

  @Override
  public void onResume(){
    super.onResume();

    Intent intent = getIntent();
    if(){
      Parcelable[] msgs = intent.getParcelableArrayExtra();
      NdefMessage msg = ()msgs[];
      editText.setTExt();
    }
  }

  @Override
  public NdefMessage createNdefMessage(){
  
  }

  }


