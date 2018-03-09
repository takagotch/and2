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

    layout.addView(makeButton("T_S_S_P",
	TAG_SET_SKU_PURCHASES));
    layout.addView(makeButton("T_S_S_P",
	TAG_SET_SKU_PURCHASES));
    layout.addView("T_B", TAG_BUY);
    textView.setTextColor(16.0f);
    textView.setTextColor(Color.rgb(0,0,0));
    layout.addView(textView);

    bindService(getExplicitIapIntent(),
	mServiceConn, Context.BIND_AUTO_CREATE);
  }

  private Intent getExplicitIaptntent(){
    PackageManager pm = getPackageManager();
    Intent implicitIntent = new Intent("com.android.vending.billing
	.InAppBillingService.BIND");
    implicitIntent.setPackage("com.android.vending");
    List<ResolveInfo> resolveInfos
	    = pm.queryIntentService(implicitIntent, 0);
    ResolveInfo serviceInfo = resolveInfos.get(0);
    String packageName = serviceInfo.serviceInfo.packageName;
    ComponentName component
	    = new ComponentName(packageName, className);
    Intent iapIntent = new Intent();
    iapIntent.setComponent(component);
    return iapIntent;
  }

  @Override
  public void onDestroy(){
    super.onDestroy()};
    unvindService(mServiceConn);
  }

  private Button makeButton(String text, String tag){
    Button button = new Button(this);
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  public void onClick(View view){
    String tag = (String)view.getTag();

    if(TAG_GET_SKU_DEFAULTS.equals(tag)){
      new Thread(new Runnable() {public void run(){
        try{
	  ArrayList<String> itemIdList = new ArrayList<String>();
	  itemIdList.add("test");
	  Bundle bundle = new Bundle();
	  bundle.putStringArrayList("ITEM_ID_LIST", itemIdList);

	  Bundle skuDetails = mService.getSkuDetails(
		3, getPackageName(), "", bundle);
	  int response = skuDetails.getInt("RESPONCE_CODE");
	  if(response == 0){
	    String str = "";
	    List<String> detailsList =
		    skuDetails.getStringArrayList("DETAILS_LIST");
	    for(String details : detailsList){
	      JSONObject object = new JSONObject(details);
	      String itemId
		      = object.getString("productId");
	      String price
		      = object.getString("price");
	      str += itemId+":"+price+"\n";
	    }

	    final String text = str;
	    runOnUiThread(new Runnable() {public void run(){
	      textView.setText(text);
	    }});
	  }
	} catch(RemoteException e){
	  e.printStackTrace();
	} catch(JSONException e){
	  e.printStackTrace();
	}
      }}).start();
    }

    else if(TAG_GET_PURCHASES.equals(tag)){
      new Thread(new Runnable() {public void run(){
        String str = "";
	int flag = readUnlockFlag();

	if(flag == 0){
	  try{
	    Bundle ownedItems = mService.getPurchases(3,
		getPackageName(), "inapp", null);
	    int response = ownedItems.getInt("RESPONSE_CODE");

	    if(response == 0){
	      List<String> itemIdList
		     = ownedItems.getStringArrayList(
			"INAPP_PURCHASE_ITEM_LIST");
	      if(itemIdList.size()
		!= 0 && itemIdList.get(0).equals("test")){
		  str = "GET";
		  writeUnlockFlag(1);
		} else{
		  str = "NO";
		  writeUnlockFlag(2);
		}
	    }
	  } catch(RemoteException e){
	    e.printStackTrace();
	  }
	}
        else if(flag == 1){
          str = "GET";
        }
        else if(flag == 2){
          str = "NO";
        }

        final String text = str;
        runOnUiThread(new Runnable() {public void run(){
          textView.setText(text);
        }});
        }}).start();
      }
    else if(TAG_BUY.equals(tag)){
      try{
        Bundle buyIntentBundle
		= mService.getBuyIntent(3, getPackageName(),
			"test", "inapp".
			"bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf0RxJ");
	PendingIntent pendintIntent
		= buyIntentBundle.getParcelable("BUY_INTENT");
	startIntentSenderForResult(pendingIntent
		.getIntentSender(), 1001, new Intent(),
		Integer.valueOf(0), Integer.valueOf(0),
		  Integer.valueOf(0));
      } catch (RemoteException e){
        e.printStackTrace();
      } catch (IntentSender.SendIntentException e){
        e.printStackTrace();
      }
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode,
	Intent data){
    if(requestCode == 1001 && resultCode == RESULT_OK){
      int response = data.getIntExtra("RESPONSE_CODE", 0);
    if(response == 0){
      String purchaseData
	      = data.getStringExtra("INAPP_PURCHASE_DATA");
      try{
        JSONObject json = new JSONObject(purchaseData);
	String sku = json.getString("productId");
	if(sku.equals("test")){
	  textView.setText("OK");
	  textView.invalidate();
	  writeUnlockFlag(1);
	}
      } catch(JSONException e){
        textView.setText("ERR");
	textView.invalidate();
	e.printStackTrace();
      }
    }
    }
  }

  private void writeUnlockFlag(int unlockFlag){
    SharedPreferences pref = PreferenceManager.
	    getDefaultSharedPreferences(this);
    Editor editor = pref.edit();
    editor.putInt("unlockFlag", unlockFlag);
    editor.commit();
  }

  private int readUnlockFlag(){
    SharedPreferences pref = PreferenceManager.
	    getDefaultSharedPreferences(this);
    return pref.getInt("unlockFlag", 0);
  }

  private ServiceConnection mServiceConn = new ServiceConnection(){
    @Override
    public void onServiceConnected(ComponentName name, IBuilder service){
      textView.setText("OK");
      textView.invalidate();

      mService = IInAppBillingService.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name){
      mService = null;
    }
  };
}


