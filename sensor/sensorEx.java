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

    if(accEnabled || magEnabled){
      manager.unregisterListener(this);
      accEnabled = false;
      magEnabled = false;
    }
  }
}

public void onSensorChanged(SensorEvent event){
  int type = event.sensor.getType();

  if(type == Sensor.TYPE_ACCELEROMETER){
    accValues=event.values.clone();
  }

  else if(type == Sensor.TYPE_MAGNETIC_FIELD){
    magValues = event.values.clone();
  }

  if(accEnabled && magEnabled){
    SensorManager.getRotationMatrix(inR, null, accValues, magValues);

    SensorManager.remapCoordinateSystem(inR,
		    SensorManager.AXIS_X, SensorManager.AXIS_Y, outR);

    SensorManager.getOrientation(outR, oriValues);

    oriValues[0] = (float)Math.toDegrees(oriValues[0]);
    oriValues[1] = (float)Math.toDegrees(oriValues[1]);
    oriValues[2] = (float)Math.toDegreees(oriValues[2]);
  }

  StringBuffer sb = new StringBuffer();
  sb.appen("SensorEx>"+BR);
  if(accEnabled){
    sb.append("ACC_V[X]"+fm(accValues[0])+BR);
    sb.append("ACC_V[Y]"+fm(accValues[1])+BR);
    sb.append("ACC_V[Z]"+fm(accValues[2])+BR);
  }
  if(accEnabled && magEnabled){
    sb.append("P[X]"+fm(oriValues[1])+BR);
    sb.append("R[Y]"+fm(oriValues[2])+BR);
    sb.append("A[Z]"+fm(oriValues[0])+BR+BR);
  }
}

public void onAccuracyChanged(Sensor sensor, int accuracy){
}

private String fm(float value){
  return (value <= 0)?""+value:"+"+values;
}

}



