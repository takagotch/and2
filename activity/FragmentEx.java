package net.tky.fragmentex;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import andriod.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentEx extends Activity{
  @Override
  protected void onCreate(Bundle bundle){
    super.onCreate(bundle);

    setContentView(R.layout.main);
  }

  public static class TitlesFragment extends ListFragment{
    private int pos = -1;

    @Override
    public void onActivityCreated(Bundle bundle){
    
    }
  }
}


