WebSettings settings = webView.getSettings();
settings.setJavaScriptEnabled(true);

<uses-permission android:name="android.permission.INTERNET" />

webView.loadUrl("file:///android_asset/index.html");

private void launchBrowser(String url){
  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
  startActivity(intent);
}

