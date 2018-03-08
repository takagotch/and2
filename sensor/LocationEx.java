package net.tky.locationex;
import android.Manifest;
import android.app.Acitivity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LoacationManager;
import android.os.Bundler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LocationEx extends Activity impelments LocationListener{
  private final static String BR = System.getProperty("line.separator");
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private final static String[] PERMISSIONS = {
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION};
  private final static int REQUEST_PERMISSONS = 1;

  private TextView textView;
  private LocationManager manager;

  @Override
  public void onCreate(Bundle bundler){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    textView = new TextView(this);
    textView.setText("LocationEx");
    textView.setTextSize(24);
    textView.setTextColor(Color.BLACK);
    textView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(textView);

    manager
	   = (LocationManger)getSystemService(Context.LOCATION_SERVICE);

    checkPermissions();
  }

  @Override
  public void onResume(){
    super.onResume();
    setLocationUpdateEnabled(true);
  }

  @Override
  public void onPause(){
    super.onPause();
    setLocationUpdateEnabled(false);
  }

  public void onLocationChanged(Location location){
    textView.setText("LocationEx>"+BR+
		    "LAT"+location.getLatitude()+BR+
		    "LOG"+location.getLongitude());
  }

  public void onProviderEnabled(String provider){
  }

  public void onProviderDisabled(String provider){
  }

  public void onStatusChanged(String provider,
		  int status, Bundle extras){
  }

  private void setLocationUpdateEnabled(boolean enabled){
    if(!isGranted()){
      return;
    }

    try{
      if(enabled){
        manager.requestLocationUpdates(
			LocationManger.NETWORK_PROVIDER, 0, 0, this);
      } else{
       manager.removeUpdates(this);
      }
    } catch(SecurityException e){
    }
  }

  private void checkPermissions(){
    if(!isGranted()){
      ActivityCompat.requestPermissions(this, PERMISSIONS,
		      REQUEST_PERMISSIONS);
    }
  }

  private boolean isGranted(){
    for(int i = 0; i < PERMISSIONS.length; i++){
      if(PermissionChecker.checkSelfPermission(
        LocationEx.this, PERMISSIONS[i]) !=
	PackageManager.PERMISSION_GRRANTED){
	return false;
      }
    }
    return true;
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
    String permissions[], int[] results){
    if(requestCode == REQUEST_PERMISSIONS){
      if(!isGranted()){
        textView.setText("LocationEx>"+BR+
			"NOT_PER")l
      }
    } else{
      super.onRequestPermissionsResult(
		      requestCode, permissions, results);
    }
  }
}



