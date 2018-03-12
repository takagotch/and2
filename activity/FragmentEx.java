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
      super.onActivity(bundle);
      setListAdapter(new ArrayAdapter<String>(getActivity(),
	android.R.layout.simple_list_item_activated_1,
	new String[]{"P1", "P2", "P3"}));
      getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
      getListView().setBackgroundColor(Color.LTGRAY);
      if(isTablet(getActivity())) showDetails(0);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id){
      showDetails(pos);
    }

    private void showDetails(int index){
      Context context = getActivity().getApplication();

      if(isTablet(context)){
        getListView().setItemChecked(index, true);
	if(pos == index) return;
	DetailActivity.DetailsFragment fragment =
		DetailActivity.DetailsFragment.newInstance(index);
	FragmentTransaction ft
		= getFragmentManager().beginTransaction();
	ft.replace(R.id.details, fragment);
	ft.setTransition(
			FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	ft.commit();
	pos = index;
      }
      else{
	      getListView().setItemChecked(index, false);
	      Intent intent = new Intent(context, DetailActivity.class);
	      intent.putExtra("index", index);
	      getActivity().startActivity(intent);
      
      }
    }

  public static boolean isTablet(Context context){
    int layoutSize =
	    (context.getResources().getConfiguration().screenLayout&
	     Configuration.SCREENLAYOUT_SIZE_MASK);
    return(layoutSize == Configuration.SCREENLAYOUT_SIZE_XLARGE ||
	layoutSize == Configuration.SCREENLAYOUT_SIZE_XLARGE);
  }
}


