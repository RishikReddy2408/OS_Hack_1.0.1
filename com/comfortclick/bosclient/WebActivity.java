package com.comfortclick.bosclient;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.comfortclick.bosclient.helpers.Converter;
import com.comfortclick.bosclient.helpers.ProfileSettings;
import com.comfortclick.bosclient.helpers.TaskBar;
import com.comfortclick.bosclient.profiles.IProfileCallback;
import com.comfortclick.bosclient.profiles.Profile;
import com.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class WebActivity
  extends Activity
  implements TextToSpeech.OnInitListener, IProfileCallback
{
  public static final int RESULT_ADDRESS_ERROR = 2;
  public static final int RESULT_CLOSE = 0;
  public static final int RESULT_LOGIN_ERROR = 1;
  private static boolean mConnected;
  private static boolean mReconnecting;
  private static SharedPreferences mSettings;
  private int INPUT_FILE_REQUEST_CODE = 1;
  private boolean mBackground = false;
  private String mDeviceName;
  private int mDimmingLevel;
  private int mDimmingTimeout;
  private Handler mDimmingTimer;
  private final Runnable mDimmingTimerRunnable = new Runnable()
  {
    public void run()
    {
      if (mTmpDimmingTimeout > 1)
      {
        WebActivity.access$010(WebActivity.this);
        mDimmingTimer.postDelayed(mDimmingTimerRunnable, 1000L);
        return;
      }
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      screenBrightness = (mDimmingLevel / 100.0F);
      getWindow().setAttributes(localLayoutParams);
    }
  };
  private DevicePolicyManager mDpm;
  private ValueCallback<Uri[]> mFilePathCallback;
  private boolean mFullScreen;
  private boolean mPanelMode;
  private boolean mPreventLock;
  private Profile mProfile;
  private ReconnectDialog mSplashDialog;
  private int mTmpDimmingTimeout;
  private TextToSpeech mTts;
  private WebView mWebView;
  
  public WebActivity() {}
  
  private void disableKioskMode()
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    try
    {
      stopLockTask();
      return;
    }
    catch (Exception localException) {}
    TaskBar.ShowTaskBar();
    return;
  }
  
  private void enableKioskMode()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      try
      {
        ComponentName localComponentName = new ComponentName(this, DeviceAdmin.class);
        DevicePolicyManager localDevicePolicyManager = mDpm;
        boolean bool = localDevicePolicyManager.isDeviceOwnerApp(getPackageName());
        if (bool)
        {
          localDevicePolicyManager = mDpm;
          String str = getPackageName();
          localDevicePolicyManager.setLockTaskPackages(localComponentName, new String[] { str, "com.comfortclick.bosphone" });
        }
        startLockTask();
        return;
      }
      catch (Exception localException)
      {
        Log.d(localException.getMessage(), localException.toString());
        return;
      }
    }
    TaskBar.KillTaskBar(this);
  }
  
  private void hideSystemBars()
  {
    if ((!mPanelMode) && (!mFullScreen)) {
      return;
    }
    getWindow().getDecorView().setSystemUiVisibility(5894);
  }
  
  private void loadScreenSettings()
  {
    if ((!mPreventLock) && (!mPanelMode))
    {
      if (mWebView != null)
      {
        mWebView.setKeepScreenOn(false);
        getWindow().clearFlags(128);
      }
    }
    else
    {
      mWebView.setKeepScreenOn(true);
      getWindow().addFlags(128);
      if (mDimmingTimeout > 0)
      {
        mDimmingTimer = new Handler();
        mDimmingTimer.postDelayed(mDimmingTimerRunnable, 1000L);
      }
    }
  }
  
  private void onAndroidAction(String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      startActivityForResult(localIntent, 1234);
      return;
    }
    catch (Exception paramString)
    {
      Log.d(paramString.getMessage(), paramString.toString());
    }
  }
  
  private void onClose(int paramInt)
  {
    setResult(paramInt, new Intent());
    finish();
  }
  
  private void onSpeakText(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    try
    {
      ttsGreater21(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    ttsUnder20(paramString);
    return;
  }
  
  private void onStartProgram(String paramString)
  {
    try
    {
      boolean bool = paramString.equals("");
      if (!bool)
      {
        startActivity(getPackageManager().getLaunchIntentForPackage(paramString));
        return;
      }
      localObject = new Intent(getApplicationContext(), WebActivity.class);
      ((Intent)localObject).addFlags(131072);
      startActivity((Intent)localObject);
      return;
    }
    catch (Exception localException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Package ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(" does not exist.");
    Toast.makeText(this, ((StringBuilder)localObject).toString(), 0).show();
  }
  
  private void removeSplashScreen()
  {
    mSplashDialog.dismiss();
  }
  
  private void resetDimmingTimer()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (mDimmingTimer != null)
        {
          WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
          screenBrightness = -1.0F;
          getWindow().setAttributes(localLayoutParams);
          mDimmingTimer.removeCallbacks(mDimmingTimerRunnable);
          WebActivity.access$002(WebActivity.this, mDimmingTimeout);
          mDimmingTimer.postDelayed(mDimmingTimerRunnable, 1000L);
        }
      }
    });
  }
  
  private void showSplashScreen(final Boolean paramBoolean)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (!isFinishing())
        {
          mSplashDialog.showDialog(mProfile.profileName, paramBoolean);
          mSplashDialog.show();
        }
      }
    });
  }
  
  private void soundPlay(String paramString)
  {
    MediaPlayer localMediaPlayer = new MediaPlayer();
    try
    {
      boolean bool = paramString.startsWith("https://");
      if (bool)
      {
        Object localObject2 = new URL(paramString);
        paramString = new HostnameVerifier()
        {
          public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession)
          {
            return paramAnonymousString.equals(val$url.getHost());
          }
        };
        Object localObject1 = SSLContext.getInstance("TLS");
        Object localObject3 = new X509TrustManager()
        {
          public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
            throws CertificateException
          {}
          
          public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
            throws CertificateException
          {}
          
          public X509Certificate[] getAcceptedIssuers()
          {
            return new X509Certificate[0];
          }
        };
        SecureRandom localSecureRandom = new SecureRandom();
        localObject3 = (TrustManager[])new X509TrustManager[] { localObject3 };
        ((SSLContext)localObject1).init(null, (TrustManager[])localObject3, localSecureRandom);
        localObject2 = ((URL)localObject2).openConnection();
        localObject2 = (HttpsURLConnection)localObject2;
        ((HttpsURLConnection)localObject2).setHostnameVerifier(paramString);
        ((HttpsURLConnection)localObject2).setSSLSocketFactory(((SSLContext)localObject1).getSocketFactory());
        paramString = ((HttpsURLConnection)localObject2).getInputStream();
        localObject1 = new FileOutputStream(new File(getCacheDir(), "temp.mp3"));
        localObject2 = new byte['?'];
        for (;;)
        {
          int i = paramString.read((byte[])localObject2);
          if (i == -1) {
            break;
          }
          ((FileOutputStream)localObject1).write((byte[])localObject2, 0, i);
        }
        ((FileOutputStream)localObject1).close();
        paramString.close();
        paramString = new StringBuilder();
        paramString.append(getCacheDir());
        paramString.append("/temp.mp3");
        localMediaPlayer.setDataSource(paramString.toString());
      }
      else
      {
        localMediaPlayer.setDataSource(paramString);
      }
      localMediaPlayer.prepare();
      localMediaPlayer.start();
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      localMediaPlayer.release();
    }
  }
  
  private void ttsGreater21(String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(hashCode());
    ((StringBuilder)localObject).append("");
    localObject = ((StringBuilder)localObject).toString();
    mTts.speak(paramString, 0, null, (String)localObject);
  }
  
  private void ttsUnder20(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("utteranceId", "MessageId");
    mTts.speak(paramString, 0, localHashMap);
  }
  
  public void close()
  {
    final String str = mSettings.getString("pref_settings_password_key", "");
    if ((mPanelMode) && (!str.isEmpty()))
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle("Logout Password");
      final EditText localEditText = new EditText(this);
      localEditText.setInputType(129);
      localBuilder.setView(localEditText);
      localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (str.equals(localEditText.getText().toString()))
          {
            WebActivity.this.onClose(0);
            paramAnonymousDialogInterface.cancel();
          }
          Toast.makeText(getApplicationContext(), "Wrong password", 0);
          paramAnonymousDialogInterface.cancel();
        }
      });
      localBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      localBuilder.show();
      return;
    }
    onClose(0);
  }
  
  public void connect()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        mProfile.connect(jdField_this);
      }
    });
  }
  
  public void connectToAddress(String paramString)
  {
    if ((paramString != null) && (mWebView != null) && (((paramString.startsWith("http://")) && (paramString.length() > 7)) || ((paramString.startsWith("https://")) && (paramString.length() > 8))))
    {
      mReconnecting = true;
      mWebView.loadUrl(paramString);
      paramString = new LoginClass();
      mWebView.addJavascriptInterface(paramString, "LoginInfo");
      return;
    }
    disconnect();
  }
  
  public void disconnect()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (mWebView != null) {
          mWebView.loadUrl("about:blank");
        }
        WebActivity.this.showSplashScreen(Boolean.valueOf(true));
      }
    });
  }
  
  public String getCloudUrl()
  {
    return getString(2131492931);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (mSplashDialog.isShowing()) {
      mSplashDialog.showDialogOnOrientationChanged(mProfile.profileName, Boolean.valueOf(mConnected));
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    mConnected = false;
    mProfile = ((Profile)new Gson().fromJson(paramBundle.getStringExtra("profile"), Profile.class));
    mSettings = PreferenceManager.getDefaultSharedPreferences(this);
    mDeviceName = mSettings.getString("pref_device_name_key", "");
    boolean bool = mSettings.getBoolean("pref_hwaccel_key", false);
    mFullScreen = mSettings.getBoolean("pref_fullscreen_key", false);
    mPreventLock = mSettings.getBoolean("pref_prevent_screen_lock_key", false);
    mPanelMode = mSettings.getBoolean("pref_enable_panel_mode_key", false);
    mDimmingTimeout = Integer.parseInt(mSettings.getString("pref_set_dimming_time_key", "60"));
    mDimmingLevel = Integer.parseInt(mSettings.getString("pref_set_dimming_value_key", "1"));
    mDpm = ((DevicePolicyManager)getSystemService("device_policy"));
    mTmpDimmingTimeout = mDimmingTimeout;
    requestWindowFeature(1);
    hideSystemBars();
    if (mPanelMode) {
      enableKioskMode();
    }
    mTts = new TextToSpeech(this, this);
    mSplashDialog = new ReconnectDialog(this, 16973934);
    mSplashDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return paramAnonymousInt == 4;
      }
    });
    setContentView(2131296309);
    mWebView = ((WebView)findViewById(2131165422));
    mWebView.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        return true;
      }
    });
    if (bool) {
      mWebView.setLayerType(2, null);
    } else {
      mWebView.setLayerType(1, null);
    }
    paramBundle = mWebView.getSettings();
    paramBundle.setJavaScriptEnabled(true);
    paramBundle.setDatabaseEnabled(true);
    paramBundle.setDomStorageEnabled(true);
    paramBundle.setBuiltInZoomControls(false);
    paramBundle.setSupportZoom(false);
    if (Build.VERSION.SDK_INT >= 19) {
      mWebView.getSettings().setCacheMode(1);
    }
    mWebView.setWebViewClient(new MyWebViewClient());
    mWebView.setWebChromeClient(new WebChromeClient()
    {
      public boolean onJsAlert(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
      {
        new AlertDialog.Builder(WebActivity.this).setMessage(paramAnonymousString2).setPositiveButton(17039370, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
          }
        }).show();
        paramAnonymousJsResult.cancel();
        return true;
      }
      
      public boolean onJsConfirm(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, final JsResult paramAnonymousJsResult)
      {
        new AlertDialog.Builder(WebActivity.this).setTitle("").setMessage(paramAnonymousString2).setPositiveButton(17039370, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymousJsResult.confirm();
          }
        }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymousJsResult.cancel();
          }
        }).create().show();
        return true;
      }
    });
    showSplashScreen(Boolean.valueOf(false));
    connect();
  }
  
  public void onDestroy()
  {
    removeSplashScreen();
    if (mPanelMode) {
      disableKioskMode();
    }
    mWebView.loadUrl("about:blank");
    ((RelativeLayout)findViewById(2131165423)).removeView(mWebView);
    mWebView.destroy();
    mWebView = null;
    if (mDimmingTimer != null) {
      mDimmingTimer.removeCallbacks(mDimmingTimerRunnable);
    }
    if (mTts != null)
    {
      mTts.stop();
      mTts.shutdown();
    }
    super.onDestroy();
  }
  
  public void onInit(int paramInt)
  {
    if (paramInt == 0)
    {
      Locale localLocale = Locale.getDefault();
      if (mTts.isLanguageAvailable(localLocale) >= 0) {
        mTts.setLanguage(localLocale);
      }
    }
    else
    {
      Log.e("TTS", "Initilization Failed!");
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      new AlertDialog.Builder(this).setTitle("Logout").setMessage(getString(2131492888)).setPositiveButton(17039379, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (mPanelMode)
          {
            close();
            return;
          }
          WebActivity.this.onClose(-1);
        }
      }).setNegativeButton(17039369, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      }).show();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    mBackground = true;
    mWebView.loadUrl("javascript:OnPause();");
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    mBackground = false;
    WebView localWebView = mWebView;
    try
    {
      localWebView.loadUrl("javascript:OnResume();");
      loadScreenSettings();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void onUserInteraction()
  {
    if (mDimmingTimeout > 0) {
      resetDimmingTimer();
    }
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if ((!mPanelMode) && (!mFullScreen)) {
      return;
    }
    if (paramBoolean) {
      getWindow().getDecorView().setSystemUiVisibility(5894);
    }
  }
  
  public void startLockTask()
  {
    throw new Error("Unresolved compilation error: Method <com.comfortclick.bosclient.WebActivity: void startLockTask()> does not exist!");
  }
  
  public void stopLockTask()
  {
    throw new Error("Unresolved compilation error: Method <com.comfortclick.bosclient.WebActivity: void stopLockTask()> does not exist!");
  }
  
  public void updateProfile(Profile paramProfile)
  {
    ProfileSettings.updateProfile(getApplicationContext(), paramProfile);
  }
  
  class LoginClass
  {
    LoginClass() {}
    
    public void onConnected()
    {
      WebActivity.access$1402(true);
      WebActivity.this.removeSplashScreen();
    }
    
    public void onConnectionError()
    {
      disconnect();
    }
    
    public void onConnectionLost()
    {
      disconnect();
    }
    
    public void onError()
    {
      WebActivity.this.showSplashScreen(Boolean.valueOf(true));
    }
    
    public void onLogout()
    {
      if (!mPanelMode)
      {
        WebActivity.this.onClose(0);
        return;
      }
      WebActivity.this.showSplashScreen(Boolean.valueOf(true));
    }
    
    public void onReload()
    {
      connect();
    }
    
    public void onWrongCredentials()
    {
      WebActivity.this.onClose(1);
    }
    
    public void playSound(String paramString)
    {
      WebActivity.this.resetDimmingTimer();
      WebActivity.this.soundPlay(paramString);
    }
    
    public void speakText(String paramString)
    {
      WebActivity.this.resetDimmingTimer();
      WebActivity.this.onSpeakText(paramString);
    }
    
    public void startAction(String paramString)
    {
      WebActivity.this.resetDimmingTimer();
      WebActivity.this.onAndroidAction(paramString);
    }
    
    public void startProgram(String paramString)
    {
      WebActivity.this.resetDimmingTimer();
      WebActivity.this.onStartProgram(paramString);
    }
  }
  
  public class MyWebViewClient
    extends WebViewClient
  {
    public MyWebViewClient() {}
    
    private String generateUrl(String paramString)
    {
      String str = mDeviceName.replace("'", "\\'");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("javascript: {Background='");
      localStringBuilder.append(paramString);
      localStringBuilder.append("'; UserName='");
      localStringBuilder.append(mProfile.username);
      localStringBuilder.append("'; Password='");
      localStringBuilder.append(mProfile.password);
      localStringBuilder.append("'; CordovaOS='Android'; DeviceName='");
      localStringBuilder.append(str);
      localStringBuilder.append("'; PushToken='");
      localStringBuilder.append(NotificationServiceListener.Token);
      localStringBuilder.append("'; if (typeof(LoadTheme) != 'undefined') { LoadTheme(); LoginInfo.onConnected();} else { LoginInfo.onError();}}");
      return localStringBuilder.toString();
    }
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      if (WebActivity.mReconnecting)
      {
        paramString = "false";
        if (mBackground) {
          paramString = "true";
        }
        paramWebView.loadUrl(generateUrl(paramString));
        WebActivity.access$1102(false);
      }
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      WebActivity.this.showSplashScreen(Boolean.valueOf(true));
    }
    
    public void onReceivedError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceError paramWebResourceError)
    {
      onReceivedError(paramWebView, paramWebResourceError.getErrorCode(), paramWebResourceError.getDescription().toString(), paramWebResourceRequest.getUrl().toString());
    }
    
    public void onReceivedSslError(final WebView paramWebView, final SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      paramWebView = SslCertificate.saveState(paramSslError.getCertificate()).getByteArray("x509-certificate");
      if (paramWebView == null)
      {
        paramSslErrorHandler.cancel();
        return;
      }
      try
      {
        paramSslError = CertificateFactory.getInstance("X.509");
        paramWebView = paramSslError.generateCertificate(new ByteArrayInputStream(paramWebView));
        paramWebView = Converter.bytesToHex(((X509Certificate)paramWebView).getPublicKey().getEncoded(), 24);
        if (mProfile.publicKey != null)
        {
          if (paramWebView.compareTo(mProfile.publicKey) == 0)
          {
            paramSslErrorHandler.proceed();
            return;
          }
          new AlertDialog.Builder(mWebView.getContext()).setTitle("Security Alert").setMessage(getString(2131492894)).setPositiveButton(17039379, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              mProfile.publicKey = paramWebView;
              ProfileSettings.updateProfile(getApplicationContext(), mProfile);
              paramSslErrorHandler.proceed();
            }
          }).setNegativeButton(17039369, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              paramSslErrorHandler.cancel();
            }
          }).setIcon(17301543).show();
          return;
        }
        new AlertDialog.Builder(mWebView.getContext()).setTitle("Security Warning").setMessage(getString(2131492895)).setPositiveButton(17039379, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            mProfile.publicKey = paramWebView;
            ProfileSettings.updateProfile(getApplicationContext(), mProfile);
            paramSslErrorHandler.proceed();
          }
        }).setNegativeButton(17039369, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramSslErrorHandler.cancel();
          }
        }).setIcon(17301543).show();
        return;
      }
      catch (CertificateException paramWebView)
      {
        for (;;) {}
      }
      paramSslErrorHandler.cancel();
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      paramWebView = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      startActivity(paramWebView);
      return true;
    }
  }
}
