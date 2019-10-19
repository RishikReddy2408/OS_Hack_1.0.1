package com.comfortclick.bosclient;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ReconnectDialog
  extends Dialog
{
  private static Button mBtnConnect;
  private static String mProfileName;
  private static int mReconnectCountDown;
  private static Handler mReconnectTimer;
  private final Context mContext;
  private final Runnable mDelayedConnect = new Runnable()
  {
    public void run()
    {
      if (ReconnectDialog.mReconnectCountDown > 1)
      {
        ReconnectDialog.access$210();
        ReconnectDialog localReconnectDialog = ReconnectDialog.this;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(mContext.getString(2131492972));
        localStringBuilder.append(" ");
        localStringBuilder.append(ReconnectDialog.mReconnectCountDown);
        localStringBuilder.append(" s");
        localReconnectDialog.setStatusText(localStringBuilder.toString());
        ReconnectDialog.mReconnectTimer.postDelayed(mDelayedConnect, 1000L);
        return;
      }
      ReconnectDialog.this.StopCountDown();
      ((WebActivity)mContext).connect();
    }
  };
  
  ReconnectDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    requestWindowFeature(1);
    mReconnectTimer = new Handler();
    mContext = paramContext;
    setContentView(2131296307);
    setCancelable(false);
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    boolean bool = paramContext.getBoolean("pref_enable_panel_mode_key", false);
    if ((!paramContext.getBoolean("pref_prevent_screen_lock_key", false)) && (!bool))
    {
      getWindow().clearFlags(128);
      return;
    }
    getWindow().addFlags(128);
  }
  
  private void StopCountDown()
  {
    mReconnectTimer.removeCallbacks(mDelayedConnect);
  }
  
  private void copySystemUiVisibility()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      getWindow().getDecorView().setSystemUiVisibility(((WebActivity)mContext).getWindow().getDecorView().getSystemUiVisibility());
    }
  }
  
  private void hideBtnConnect()
  {
    mBtnConnect = (Button)findViewById(2131165232);
    mBtnConnect.setVisibility(8);
  }
  
  private void setStatusText(String paramString)
  {
    ((TextView)findViewById(2131165326)).setText(paramString);
  }
  
  private void showBtnConnect()
  {
    mBtnConnect = (Button)findViewById(2131165232);
    mBtnConnect.setVisibility(0);
  }
  
  private void startCountDown()
  {
    mReconnectCountDown = 10;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(mContext.getString(2131492972));
    localStringBuilder.append(" ");
    localStringBuilder.append(mReconnectCountDown);
    localStringBuilder.append(" s");
    setStatusText(localStringBuilder.toString());
    mReconnectTimer.removeCallbacks(mDelayedConnect);
    mReconnectTimer.postDelayed(mDelayedConnect, 1000L);
  }
  
  public void onContentChanged()
  {
    super.onContentChanged();
    mBtnConnect = (Button)findViewById(2131165232);
    mBtnConnect.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ReconnectDialog.this.StopCountDown();
        ((WebActivity)mContext).connect();
      }
    });
    ((Button)findViewById(2131165231)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((WebActivity)mContext).close();
      }
    });
    ((TextView)findViewById(2131165404)).setText(mProfileName);
  }
  
  public void show()
  {
    getWindow().setFlags(8, 8);
    copySystemUiVisibility();
    super.show();
    getWindow().clearFlags(8);
  }
  
  public void showDialog(String paramString, Boolean paramBoolean)
  {
    mProfileName = paramString;
    setContentView(2131296307);
    if (paramBoolean.booleanValue())
    {
      startCountDown();
      showBtnConnect();
      return;
    }
    setStatusText(mContext.getString(2131492869));
    hideBtnConnect();
  }
  
  public void showDialogOnOrientationChanged(String paramString, Boolean paramBoolean)
  {
    mProfileName = paramString;
    setContentView(2131296307);
    if (paramBoolean.booleanValue())
    {
      showBtnConnect();
      return;
    }
    setStatusText(mContext.getString(2131492869));
    hideBtnConnect();
  }
}
