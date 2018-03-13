package net.tky.webviewex;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewEx extends Activity{
  @Override
  public void onCreate(Bundle bundle){
   super.onCreate(bundle);
   requestWindowFeature(Window.FEATURE_NO_TITLE);

   WebView webView = new WebView(this);
   WebSettings settings = webView.getSettings();
   settings.setJavaScriptEnabled(true);
   settings.setSaveFormData(false);
   settings.setSupportZoom(false);

   webView.setWebViewClient(new WebViewClient(){
     @SuppressWarnings("deprecation")
     @Override
     public void onReceivedError(WebView view, int errorCode,
	String description, String url){
        toast("ERR");
     }

     @TargetApi(android.os.Bundle.VERSION_CODES.M)
     @Override
     public void onReceivedError(WebView view,
	WebResourceRequest req, WebResourceError rerr){
        toast("ERR");
     }
   });
   setContentView(webView);

   webView.loadUrl("https://www.google.co.jp/intl/ja_jp/nexus/");
  }

  private void toast(String text){
    if(text == null) text = "";
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }
}


