package net.tky.camera2exl
import
import

public class Camera2Ex extends Activity {
  private final static int REQUEST_PERMISSIONS = 0;
  private final static String[] PERMISSIONS = {
    Manifest.permission.CAMERA,
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE};

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);

    getWindow().clearFlags(
		    WindowManager.Layout.FLAG_FORCE_NOT_FULLSCREEN);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    checkPermissions();
  }

  private void checkPermission(){
    if(isGranted()){
      initContentView();
    }
    else {
      ActivityCompat.requestPermissions(this, PERMISSIONS,
		      REQUEST_PERMISSIONS);
    }
  }

  private boolean isGranted(){
    for(int i = 0; i < PERMISSIONS.length; i++){
      if(PermissionChecker.checkSelfPermission(
		Camera2Ex.this, PERMISSIONS[i]) !=
		PackageManager.PERMISSION_GRANTED){
		  return false;
		}
    }
    return true;
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
	String permissions[], int[] results){
    if(requestCode == REQUEST_PERMISSIONS){
      if(isGranted()){
        initContentView();
      }
    } else{
      super.onRequestPermissionsResult(
		      requestCode, permissions, results);
    }
  }

  private void initContentView(){
    setCntentView(new Camera2View(this));
  }
}


