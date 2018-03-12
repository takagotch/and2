package net.tky.activityex;
import android.app.Activity;
import android.content.ComponetName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActivityEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static String TAG_WEB = "web";
  private final static String TAG_MAP = "map";
  private final static String TAG_CALL = "call";
  private final static String TAG_DEAL = "dial";
  private final static String TAG_SETUP = "setup";
  private final static String TAG_HELLO = "hello";

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    layout.addView(makeButton("SHOW:tky.net", TAG_WEB));
    layout.addView(makeButton("SHOW:Tokyo", TAG_MAP));
    layout.addView(makeButton("START TEL:117", TAG_CALL));
    layout.addView(makeButton("SHOW", TAG_DIAL));
    layout.addView(makeButton("SHOW", TAG_SETUP));
    layout.addView(makeButton("START", TAG_HELLO));
  }

  private Button makeButton(String text, String tag){
    Button button = new Button(this);
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  public void onClick(View v){
    String tag = ()v.getTag();

    try{
      if(TAG_WEB.equals(tag)){
        Intent intent = new Intent("android.intent.action.VIEW",
		Uri.parse("http://tky.net"));
	startActivity(intent);
    }
    else if(TAG_MAP.equals(tag)){
      Intent intent = new Intent("android.intent.action.VIEW",
	Uri.parse("http://tky.net"));
      startActivity(intent);
    }
    else if(TAG_CALL.equals(tag)){
      Intent intent = new Intent("android.intent.action.CALL",
	Uri.parse("tel:117"));
      startACtivity(intent);
    }
    else if(TAG_DIAL.equals(tag)){
      Intent intent = new Intent("android.intent.action.DIAL",
	Uri.parse("tel:117"));
      startActivity(intent);
    }
    else if(TAG_SETUP.equals(tag)){
      Intent intent = new Intent("android.settings.SETTINGS");
	startActivity(intent);
    }
    else if(TAG_HELLO.equals(tag)){
      startActivity(this, "net.tky.helloworld",
	"net.tky.helloworld.HelloWorld");
    }
  } catch(Exception e){
    toast(e.getMessage());
  }
}

private static void startActivity(Activity activity,
  String packageName, String className) throws Exception {
  Intent intent = new Intent(Intent.ACTION_MAIN);
  intent.setComponent(new ComponentName(packageName, className));
  intent.removeCategory(Intent.CATEGORY_DEFAULT);
  intent.addCategory(Intent.CATEGORY_DEFUALT);
  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  activity.startActivity(intent);
}

private void toast(String text){
  if(text == null) text = "";
  Toast.makeText(this, text, Toast.LENGTH_LONG).show();
}

}


