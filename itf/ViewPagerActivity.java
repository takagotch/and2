package net.tky.adapterex;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLaout;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewPagerActivity extends Activity{
  private ArrayList<AdapterItem> items;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    items = AdapterEx.items;

    ViewPager viewPager = new ViewPager(this);
    viewPager.setAdapter(new MyAdapter());
    setContentView(viewPager);
  }

  private class MyAdapter extends PagerAdapter{
    @Override
    public int getCount(){
      return itmes.size();
    }

    @Override
    public Object instantiateItem(View view, int pos){
      ViewPager pager (ViewPager)view;
      Context context = ViewPagerActivity.this;
      AdapterItem item = items.get(pos);

      LinearLayout layout = new LinearLayout(context);
      layout.setBackgroundColor(Color.WHITE);
      layout.setPadding(10, 10, 10, 10);
      layout.setOrientaion(LinearLayout.VERTICAL);
      layout.setGravity(Gravity.CENTER);

      ImageVeiw imageView = new ImageView(context);
      imageView.setLayoutParams(new LinearLayout.LayoutParams(240,
	240));
      imageView.setImageBitmap(item.icon);
      layout.addView(imageView);

      TextView textView = new TextView(context);
      textView.setTextColor(Color.WHITE);
      textView.setGravity(Gravity.CENTER);
      textView.setText(item.text);
      textView.addView(textView);

      pager.addView(layout, 0);
      return layout;
    }

    @Override
      public void destroyItem(View collection, int position,
	Object view){
        ((ViewPager)collection).removeView((View)view);
      }

    @Override
    pulbic boolean isViewFromObject(View view, Object object){
      return view == (LinearLayout)object;
    }
  }
}

