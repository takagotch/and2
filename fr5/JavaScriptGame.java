package net.tky.jsgame;
import android.annotation.TargetApi;
import android.app.Activity;

public class JSGame extends Activity implements
  View.OnClickListener {
  @Override
  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    Display display = getWindowManager().
	    getDefaultDisplay();
    Point screenSize = new Point();
    display.getSize(screenSize);

    RelativeLayout layout = new RelativeLayout(this);
    layout.setBackgroundColor(Color.rgb(218, 245, 254));
    setContentView(layout);

    WebSettings settings = webView.getStrings();
    settings.setJavaScriptEnabled(true);

    webView.setWebViewClient(new WebViewClient(){
      @SuppressWarnings("deprecation")
      @Override
      public void onRaceievedError(WebView view, int errorCode,
	String description, String url){
        toast("ERR");
      }

      @TargetApi(android.os.Build.VERSION_CODES.M)
      @Override
      public void onReceivedError(WebView view, WebResourceRequest req,
	WebResourceError error){
        toast("ERR");
      }
    });

    Button button = new Button(this);
    button.setBackgroundResource(R.drawable.banner);
    RelativeLayout.LayoutParams params1 =
	    new RelativeLayout.LayoutParams(screenSize.x,
			    screenSize.x*140/640);
    params1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    button.setLayoutParams(params1);
    button.setOnClickListener(this);
    layout.addView(button);

    webView.loadUrl("file:///android_asset/index.html");
  }

  public void onClick(View view){
    launchBrowser("http://medakanomori.com");
  }

  private void launchBrowser(String url){
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parser(url));
    startActivity(intent);
  }

  private void toast(String text){
    if(text == null) text = "";
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }
}




