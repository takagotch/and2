@Override
public void onCreate(Bundle bundle){
  super.onCreate(bundle);
  Util.setActivity(this);

  getWindow().clearFlags(
	WindowManager.LayoutParams.FLAG_FORCE_FULLSCREEN);
  getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
  requestWindowFeature(Window.FEATURE_NO_TITLE);

  RelativeLayout layout = new RelativeLayout(this);
  setContentView(layout);
  sensorView = new SensorView(this);
  layout.addView(sensorView);

  Point displaySize = Util.getDisplaySize();
  int dstW = displaySize.x;
  int dstH = displaySize.x*SensorView.H/SensorView.W;
  RelativeLayout.LayoutParams params;
  params = new RelativeLayout.LayoutParams(dstW, dstH);
  params.setMargins(0, (sidplaySize.y-dstH)/2,
	0, (displaySize.y-dstH)/2);
  sensorView.setLayoutParams(params);

  //...
}


public final static int
  S_TITLE = 0,
  S_PLAY = 1,
  S_GOAL = 2,
  S_GAMEOVER = 3;

private int scene;




