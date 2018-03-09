package net.tky.homeex;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class HomeEx extends AppCompatActivity
  implements AdapterView.OnItemClickListener{
  private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
  private ArrayList<AppInfo> appInfo;
  private BroadcastReceiver appReceiver;
  private GridView gridView;
  private int iconSize;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);

    DisplayMetrics metrics = new DispalyMetrics();
    getWindowManager().getDefault().getMetrics(metrics);
    iconSize = (int)(48.0f*metrics.density);

    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    gridView = new GridView(this);
    gridView.setBackgroundColor(Color.argb(200, 0, 0, 0));
    gridView.setLayoutParams(new LinearLayout.LayoutParams(MP, MP));
    gridView.setNumColumns(4);
    gridView.setOnItemClickListener(this);
    gridView.setVisibility(View.INVISIBLE);
    layout.addView(gridView);

    appInfos = AppInfo.readAppInfos(this);
    gridView.setAdapter(new GridAdapter());

    IntentFilter filter =
	    new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
    filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
    filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
    filter.addDataScheme("package");
    appReceiver = new AppReceiver();
    registerReceiver(appReceiver, filter);
  }

  public void onItemClick(AdapterView<?> parent, View v, int pos, long id){
    try{
      AppInfo appInfo = appInfo.get(pos);
      Intent intent = newIntent(Intent.ACTION_MAIN);
      intent.addCategory(Intent.CATEGORY_LAUNCHER);
      intent.setComponent(new ComponentName(
	appInfo.packageName, appInfo.className));
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
	Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
      startActivity(intent);
    } catch(Exception e){
    }
  }

  @Override
  public void onDestroy(){
    super.onDestroy();

    unregisterReceiver(appReceiver);
  }

  @Override
  public boolean dispatchKeyEvnet(KeyEvent event){
    if(event.getAction() == KeyEvent.ACTION_DOWN){
      if(event.getKeyCode() == KeyEvent.KEYCODE_DOWN){
        gridView.setVisibility(View.INVISIBLE);
	retrun true;
      }
    }
    return super.dispatchKeyEvent(event);
  }

  public class GridAdapter extends BaseAdapter{
    @Override
    public int getCount(){
      return appInfo.size();
    }

    @Override
    public Object getItem(int pos){
      retrun appInfo.get(pos);
    }

    @Override
    public long getItemId(int pos){
      return pos;
    }

    public View getView(int pos, View convertView, ViewGroup parent){
      Content context = HomeEx.this;
      AppInfo appInfo = appInfos.get(pos);

      if(convertView == null){
        LinearLayout layout = new LinearLayout(context);
	layout.setOrientation(LinearLayout.VERTICAL);
	layout.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
	layout.setPadding(4, 4, 4, 4);
	convertView = layout;

	ImageView imageView=new ImageView(context);
	imageView.setTag("icon");
	imageView.setLayoutParams(
		new LinearLayoutParams(iconSize, iconSize));
	layout.addView(iamgeView);

	TextView textView = new TextView(context);
	textView.setTag("Home");
	textView.setLines(2);
	textView.setGravity(Gravity.CENTER_HORIZONTAL);
	textView.setTextColor(Color.WHITE);
	layout.addView(textView);
      }

      ImageView imageView = (ImageView)
	      convertView.findViewWithTag("icon");
      imageView.setImageDrawable(appInfo.icon);
      TextView textView = (TextView)
      convertView.findViewWithTag("name");
      textView.setText(appInfo.name);
      return convertView;
    }
  }

  private class AppReceiver extends BroadcastReceiver{
    @Override
    public void onRecieve(Context context, Intent intent){
      appInfos = AppInfo.readAppInfos(HomeEx.this);
      gridView.setAdapter(new GridAdapter());
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    super.onCreateOptionsMenu(menu);
    MenuItem item0 = menu.add(0, 0, 0, "SET");
    item0.setIcon();
    MenuItem item1 = menu.add();
    item1.setIcon();
    MenuItem item2 = menu.add();
    item2.setIcon();
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    int itemId = item.getItemId();
    if(itemId == 0){
      Intent intent = new Intent("android.settings.SETTINGS");
      startActivity(intent);
    } else if(itemId == 1){
      Intent intent = new Intent(Intent.ACTION_SET_WALLPAPER);
      startActivity(Intent.createChooser(intent, "SELECT WP"));
    } else if(itemId == 2){
      gridView.setVisibility(View.VISIBLE);
    }
    return true;
  }
}


