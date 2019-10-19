package com.comfortclick.bosclient;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PrivacyPolicyActivity
  extends Activity
{
  public PrivacyPolicyActivity() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131296283);
    paramBundle = new WebView(this);
    paramBundle.getSettings().setJavaScriptEnabled(true);
    paramBundle.setWebViewClient(new WebViewClient()
    {
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        Toast.makeText(jdField_this, paramAnonymousString1, 0).show();
      }
    });
    paramBundle.loadUrl("http://www.comfortclick.com/Files/Legal/Privacy.html");
    setContentView(paramBundle);
  }
}
