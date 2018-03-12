package net.tky.serviceex;
import
import

public class MyService extends Service{
  private Handler handler = new Handler();
  private boolean running = false;
  private String message = "Message";

  @Override
  public void onCreate(){
    super.onCreate();
  }

  public int onStartCommand(Intent intent, int flags, int startId){
    super.onStartCommand(intent, flags, startId);

    showNotification(this,
		    "SELF_S",
		    "CTL_SELF_S");
    Thread thread = new Thread(){public void run(){
      running = true;
      while(running){
        handler.post(new Runnable(){public void run(){
	  toast(MyService.this, message);
	}});
	try{
	  Thread.sleep(3000);
	} catch(Exception e){
	}
      }
    }};
    thread.start();
    return START_NOT_STICKY;
  }

  @Override
  public void onDestroy(){
    running = false;
    super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent intent){
    return IMyServiceBinder;
  }

  private void showNotification(Context context,
		  String title, String text){
    Notification.Builder builder = new Notification.Builder(context);
    builder.setWhen(System.currentTimeMills());
    builder.setContentTitle(title);
    builder.setContentText(text);
    builder.setSmallIcon(R.mipmap.ic_launcher);

    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.setComponent("net.tky.serviceex",
		    "net.tky.serviceex.ServiceEx");
    intent.removeCategory(Intent.CATEGORY_DEFAULT);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    builder.setContentIntent(PendingIntent.getActivity(context, 0,
			    intent, PendingIntent.FLAG_CANCEL_CURRENT));

    NotificationManager nm = (NotificationManager)
	    context.getSystemService(Context.NOTIFICATION_SERVICE);
    nm.cancel(1);
    nm.notify(1, builder.build());
  }

  private static void toast(Context context, String text){
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
  }

  private final IMyService.Stub IMyServiceBinder = new IMyService.Stub(){
    public void setMessage(String msg) throws RemoteException{
      message = msg;
    }
  };
}


