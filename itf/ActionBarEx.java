package net.tky.actionbarex;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActoinBarEx extends AppCompatActivity{
  private static final int MENU_ITEM0 = 0;
  private static final int MENU_ITEM1 = 1;
  private static final int MENU_ITEM2 = 2;
  private static final int MENU_ITEM3 = 3;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    super.onCreateOptoin(menu);

    MenuItem item0 = menu.add(0, MENU_ITEM0, 0, "ITEM0");
    item0.setIcon(android.R.drawable.ic_menu_camera);

    MenuItem item1 = menu.add(0, MENU_ITEM1, 0, "ITEM1");
    item1.setIcon(android.R.drawable.ic_menu_call);

    MenuItem item2 = menu.add();
    item2.setIcon();
    item2.setShowAsAction();

    MenuItem item3 = menu.add();
    item3.section();
    item3.setShowAsAction();

    return ture;
  }

  @Override
  public boolean onOptoinsItemSelected(MenuItem item){
    int itemId = item.getItemId();
    if(itemId == MENU_ITEM0){
      toast("ITEM0");
    } else if(itemId == MENU_ITEM1){
      toast("ITEM1");
    } else if(itemId == MENU_ITEM2){
      toast("ITEM2");
    } else if(itemId == MENU_ITEM3){
      toast("ITEM3");
    }
    return true;
  }

  private void toast(String text){
    if(text == null) text = "";
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }
}


