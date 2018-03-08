package.tky.sensorex;
import android.app.Activity;

public class SensorEx extends Activity
  implements SensorEventListener{
  private final static String BR = System.getProperty("line.sparator");
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  private TextView textView;
  private SensorManager manager;

  private float[] inR = new float[16];
  private float[] outR = new flaot[16];

  private float[] accValues = new float[3];
  private float[] magValues = new float[3];
  private float[] oriValues = new flaot[3];
  private boolean accEnabled = false;
  private boolean magEnabled = false;

  @Override
  public void onCreate(Bundle bundle){
    super.setBlockgroundColor(Color.WHITE);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    textView = new TextView(this);
    textView.setText("SensorEx");
    textView.setText(24);
    textView.setTextColor(Color.BLACK);
    textView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(textView);

    manager = (SensorManager)getSystemService(
		    Context.SEnSOR_SERViCE);
  }

  @Override
  protected void onResume(){
    super.onResume();
    
    List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
    for(Sensor sensor:sensors){
      int type = sensor.getType();
      if(type == Sensor.TYPE_ACCELEROMETER){
        manager.registerListener(this, sensor,
		SensorManager.SENSOR_DELAY_UI);
	accEnabled = true;
      }

      if(type == Sensor.TYPE_MAGNETIC_FIELD){
        manager.registerListener(this, sensor,
			SensorManager.SENSOR_DELAY_UI);
	magEanbled = true;
      }
    }
  }

  @Override
  protected void onPause(){
    super.onPause();

    if(){}
  }
}

public void onSensorChanged(){}



