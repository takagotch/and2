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
  private

  @Override
}
