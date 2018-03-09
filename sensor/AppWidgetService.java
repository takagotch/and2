package net.tky.appwidgetex;
import android.app.PendingIntent; 
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.util.Random;

public class AppWidgetService extends Service{
  private static final String ACTION_BTNCLICK =
    "net.tky.appwidget.ACTION_BTNCLICK";

  @Override
  public int onStartCommand(Intent intent, int flags, int startId){
    super.onStartCommand(intent, flags, startId);

    RemoteViews view
	    = new RemoteViews(getPackgeName(), R.layout.appwidget);

    Intent newintent = new Intent();
    newintent.setAction(ACTION_BTNCLICK);
    PendingIntent pending
	    = PendingIntent.getService(this, 0, newintent, 0);
    view.setOnClickPendingIntent(R.id.button1, pending);

    if(ACTION_BTNCLICK.equals(intent.getAction())){
      btnClicked(view);
    }

    AppWidgetManager manager = AppWidgetManager.getInstance(this);
    ComponentName widget = new ComponetName(
	"net.tky.appwidgetex",
	"net.tky.appwidgetex.AppWidgetEx");
    manager.updateAppWidget(widget, view);
    return START_STICKY;
  }

  @Override
  public IBinder onBind(Intent intent){
    return null;
  }

  public void btnClicked(RemoteViews view){
    int[] ids = {
      R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
      R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    int idx = rand(6);
    view.setImageViewResource(R.id.imageview1, ids[idx]);
  }

  private static Random rand = new Random();
  public static int rand(int num){
    return (rand.nextInt()>>>1)%num;
  }
}



