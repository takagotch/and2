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

    Intent intent = new Intent();
    intent.setComponent();
    intent.removeCategory();
    intent.addCategory();
    intent.setFlags();
    builder.setContentIntent();

    NotificationManager nm = ();
    nm.cancel();
    nm.notify();
  }

  private static void toast();

  private final IMyService.Stub IMyServiceBinder = new IMyService.Stub(){
  
  };
}


