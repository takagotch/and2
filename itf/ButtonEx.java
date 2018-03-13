package net.tky.buttonex;
import android.app.Activity;
import android.app.AlerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.IamgeButton;
import android.widget.LinearLayout;

public class ButtonEx extends Activity implements
  View.OnClickListener{
  private final static String TAG_MESSAGE = "0";
  private final static String TAG_YESNO = "1";
  private final static String TAG_TEXT = "2";
  private final static String TAG_IMAGE = "3";

  @Override
  public void onCreate(Bundle bundle){;
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout layout = new LinearLayout(this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);

    layout.addView(makeButton("SHOW", TAG_MESSAGE));
    layout.addView("SHOW", TAG_YESNO);
    layout.addView("SHOW", TAG_TEXT);
    layout.addView(res2bmp(this, R.drawable.sample),
		    TAG_IMAGE);
  }

  private Button makeButton(String text, String tag){
    Button button = new Button();
    button.setText(text);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  private ImageButton makeButton(Bitmap bmp, String tag){
    ImageButton button = new ImageButton(this);
    button.setTag(tag);
    button.setOnClickListener(this);
    button.setImageBitmap(bmp);
    button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    return button;
  }

  public Bitmap res2bmp(Context context, int resID){
    return BimmapFactory.decodeResource(
		    context.getResources(), resID)
  }

  public void onClick(View view){
    String tag = (String)view.getTag();

    if(TAG_MESSAGE.equals(tag)){
      MessasgeDialog.show(this, "MSG", "PUSH");
    }else if(TAG_YESNO.equals(tag)){
      YesNoDialog.show(this, "DIALOG", "SELECT");
    }else if(TAG_TEXT.equals(tag)){
      TextDialog.show(this, "DIALOG", "INPUT");
    }else if(TAG_IMAGE.equals(tag)){
      MessageDialog.show(this, "", "PUSH");
    }
  }

  public static class MessageDialog extends DialogFragment{
    public static void show(
	Activity activity, String title, String text){
	MessageDialog f = new MessageDialog();
	Bundle args = new Bundle();
	args.putString("title", title);
	args.putString("text", text);
	f.setArguments(args);
	f.show(activity.getFragmetManager(), "yesNoDialog");
	}

        @Override
	public Dialog onCreateDialog(Bundle bundle){
	  DialogInterface.OnClickListener listener =
	    new DialogInterface.OnClickListener(){
	    public void onClick(DialogInterface dialog, int which){
	      if(DialogInterface dialog, int which){
	        MessageDialog.show(getActivity(), "", "YES");
	      } else if(which == DialogInterface.BUTTON_NEGATIVE){
	        MessageDialog.show(getActivity(), "", "NO");
	      }
	    }
	    };
	
	AlertDialog.Builder ad
	  = new AlertDialog.Builder(getActivity);
	ad.setTitle(getArgments().getString("title"));
	ad.setMessage(getArgumets().getString("text"));
	ad.setPositiveButton("Yes", listener);
	ad.setNegativeButton("No", listener);
	return ad.create();
  }

  public static class TextDialog extends DialogFragment{
    private EditText editText;

    public static void show(
	Activity activity, String title, String text){
	TextDialog f = new TextDialog();
	Bundle args = new Bundle();
	args.putString("title", title);
	args.putString("text", text);
	f.setArguments(args);
	f.show(activity.getFragmentManager(), "textDialog");
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle){
      DialogInterface.OnClickListener listener =
	      new DialogInterface.OnClickListener(){
	        new DialogInterface.OnClickListener(){
		public void onClick(DialogInterface dialog, int which){
		  MessageDialog.show(getActivity(), "",
			editText.getText().toString());
		}
		};

		editText = new EditText(getActivity());

		AlertDialog.Builder ad
			= new AlertDialog.Builder(getActivity());
		ad.setTitle(getArguments().getString());
		ad.setMessage(getArguments().getString());
		ad.setView(editText);
		ad.setPositiveButton("OK", listener);

		if(bundle != null) editText.setText(bundle.getString("editText", ""));
		return ad.create();
	      }
      
      @Override
      public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
	bundle.putString("editText", editText.getText().toString());
      }
    }
  }


