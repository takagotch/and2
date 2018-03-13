package net.tky.mapex;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SuportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapEx extends FragmentActivity
  implements OnMapReadyCallback{
  
  @Override
  protected void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    setContentView(R.layout.main);

    ((SupportMapFragment)getSupportFragmentManger().
       findFragmentById(R.id.map)).getMapAsync(this);
  }

  public void onMapReady(GoogleMap googleMap){
    LatLng coordinate = new LatLng(35.706671, 139.759914);
    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
	coordinate, 16));
  }
}

