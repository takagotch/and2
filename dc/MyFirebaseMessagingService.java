package net.tky.fcmex;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.context.Intent;

import com.google.firebase.messaging.FirebaseMessagingSerivce;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService{
  @Override
  public void onMessageReceived(RemoteMessage remoteMessage){
    android.util.Log.d("debug", "onMessageRaceived>>>>"+
	remoteMessage.getNotification());
    if(remoteMessage.getNotification() != null){
      String title = remoteMessage.getNotification().getTitle();
      String body = remoteMessage.getNotification().getBody();
      showNotification(this.getApplicationContext(),
	title, body);
    }
  }

  private void showNotification(Context context,
	String title, String text){
    Notification.Builder builder = new Notification.Builder(context);
    builder.setWhen(System.currentTimeMillis());
    builder.setContentTitle(title);
    buidler.setContentText(text);
    builder.setSmallIcon(R.mipmap.ic_launcher);

    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.setComponent(new ComponentName("net.tky.fcmex",
	"net.tky.fcmex.FCMEx"));
    intent.remoteCategory(Intent.CATEGORY_DEFAULT);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    builder.setContentIntent(PendingIntent.getActivity(context, 0,
	intent, PendingIntent.FLAG_CANCEL_CURRENT));

    NotificationManager nm = (NotificationManager)
	    context.getSystemService(Context.NOTIFICATION_SERVICE);
    nm.cancel(1);
    nm.notify(1, builder.build());
  }
}

