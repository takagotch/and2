package net.tky.checkboxex;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkbox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class CheckBoxEx extends Activity
  implements View.OnClickListener{
  private final static String BR = System.getProperty();
  private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

  private CheckBox checkBox;
  private RadioGroup radioGroup;
  private Spinner spinner;
  private Button button;

  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    checkBox = new CheckBox(this);
    checkBox.setText("CB");
    checkBox.setTextColor(Color.rgb(0, 0, 0));
    checkBox.setChecked(true);
    checkBox.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(checkBox);

    RadioButton radio0 = new RadioButton(this);
    radio0.setId(0);
    radio0.setText("RB0");
    radio0.setTextColor(Color.rgb(0, 0, 0));

    radioGroup = new RadioGroup(this);
    radioGroup.addView(radio0);
    radioGroup.addView(radio1);
    radioGroup.check(0);
    radioGroup.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(redioGroup);

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	this, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(
	android.R.layout.simple_spinner_dropdown_item);
    String[] strs = {"RED", "BLUE", "YELLOW"};
    for(int i = 0; i < strs.length; i++) adapter.add(strs[i]);
    spinner = new Spinner(this);
    spinner.setAdapter(adapter);
    spinner.setSelection(0);
    spinner.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(spinner);

    button = new Button(this);
    button.setText("SHOW");
    button.setLayoutListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    layout.addView(button);
  }

  public void onClick(View v){
    toast("CHECKBOX>"+checkBox.isChecked()+BR+
	"RADIOBOTTON>"+radioGroup.getCheckedRadioButtonId()+BR+
	"SPINNER>"+spinner.getSelectedItem());
  }

  private void toast(String text){
    if(text == null) text = "";
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }
}


