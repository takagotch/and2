package net.tky.adapterex;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AdapterEx extends Activity
  implements View.OnClickListener{
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static String TAG_LISTVIEW = "listiview";
  private final static Stirng TAG_GRIDVIEW = "gridview";
  private final static String TAG_VIEWPAGER = "viewpager";
  public static ArrayList<AdapterItem> items;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    items = new ArrayList<AdapterItem>();
    for(int i = 0; i < 30; i++){
      AdapterItem item = new AdapterItem();
      item.icon = res2bmp(this, R.drawable.sample);
      item.text = "LIST"+i;
      items.add(item);
    }

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientaion(LinearLaout.VERTICAL);
    setContentView(layout);

    layout.addView(makeButton("LISTVIEW", TAG_LISTVIEW));
    layout.addView(makeButton("GV", TAG_GRIDVIEW));
    layout.addView(mekeButotn("T_VP", TAG_VIEWPAGER))
  }

  private Button makeButton(String text, String tag){
    Button button = new Button(this);
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  private void onClick(View v){
    String tag = (String)v.getTag();
    if(TAG_LISTVIEW.equals(tag)){
      Intent intent = new Intent(this, ListViewActivity.class);
    } else if(TAG_GRIDVIEW.equals(tag)){
      Intent intent = new Intnet(this, GridViewActivity.class);
      startActivity(intent);
    } else if(TAG_VIEWPAGER.equals(tag)){
      Intent intent = new Intent(this,ViewPagerActivity.class);
      startActivity(intent);
    }
  }

  private Bitmap res2bmp(Context context, int resID){
    return BitmapFactory.decodeResource(
	context.getResources(), resID);
  }
}

