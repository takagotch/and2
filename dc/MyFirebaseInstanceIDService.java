package net.tky.fcmex;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstatnceIDService extends FirebaseInstanceIdService{
  @Override
  public void onTokenRefresh(){
    String token = FirebaseInstanceId.getInstance().getToken();
    android.util.Log.d("debug", "onTokenRefresh>>>>"+token);
  }
}

