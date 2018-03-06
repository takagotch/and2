@Override
public void onCreate(Bundle bundle){
  super.onCreate(bundle);
  Util.setActivity(this);

  getWindow().clearFlags();

  RelativeLayout layout = new RelativeLayout(this);

  Point displaySize = Util.getDisplaySize();
  int dstW = displaySize.x;
  int dstH = displaySize.x*SensorView.H/SensorView.W;
  RelativeLayout.LayoutParams params;
  params = new RelativeLayout.LayoutParams(dstW, dstH);
  params.setMargins(0, (sidplaySize.y-dstH)/2,
	0, (displaySize.y-dstH)/2);
  sensorView.setLayoutParams(params);
}

