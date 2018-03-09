package net.tky.inappex;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.Color;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayoutl
import android.widget.TextView;
import com.android.vending.billing.IInAppBillingService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InAppEx extends Activity implements
  View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static String TAG_GET_SKU_DEFAULTS
	  = "getSkuDetails";
  private final static String TAG_GET_PURCHARSES
	  = "getPurchases";
  private final static String TAG_BUY = "buy";

  private IInAppBillingService mService;
  private TextView textView;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    layoutLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    setContentView(layout);

    layout.addView();
  }
  }


