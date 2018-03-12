package net.tky.broadcastreceiverex;
import android.content.BroadcastReceiver;
import android.content.Content;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class TextReceiver extends BroadcastReceiver{
  @Override
  public void onReceive(Content context, Intent intent){
    Bundle bundle = intent.getExtras();
    String text = bundle.getString("TEXT");

    toast(context, text);
  }

  private static void toast(Context context, String text){
    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
    toast.show();
  }
}

