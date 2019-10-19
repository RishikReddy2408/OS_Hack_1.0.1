package android.support.v7.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v7.view.SupportActionModeWrapper.CallbackWrapper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode.Callback;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;

@RequiresApi(14)
class AppCompatDelegateImplV14
  extends AppCompatDelegateImplV9
{
  private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
  private boolean mApplyDayNightCalled;
  private AutoNightModeManager mAutoNightModeManager;
  private boolean mHandleNativeActionModes = true;
  private int mLocalNightMode = -100;
  
  AppCompatDelegateImplV14(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    super(paramContext, paramWindow, paramAppCompatCallback);
  }
  
  private void ensureAutoNightModeManager()
  {
    if (mAutoNightModeManager == null) {
      mAutoNightModeManager = new AutoNightModeManager(TwilightManager.getInstance(mContext));
    }
  }
  
  private int getNightMode()
  {
    if (mLocalNightMode != -100) {
      return mLocalNightMode;
    }
    return getDefaultNightMode();
  }
  
  private boolean shouldRecreateOnNightModeChange()
  {
    boolean bool2 = mApplyDayNightCalled;
    boolean bool1 = false;
    if ((bool2) && ((mContext instanceof Activity)))
    {
      PackageManager localPackageManager = mContext.getPackageManager();
      try
      {
        int i = getActivityInfoComponentNamemContext, mContext.getClass()), 0).configChanges;
        if ((i & 0x200) == 0) {
          bool1 = true;
        }
        return bool1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", localNameNotFoundException);
        return true;
      }
    }
    return false;
  }
  
  private boolean updateForNightMode(int paramInt)
  {
    Resources localResources = mContext.getResources();
    Configuration localConfiguration = localResources.getConfiguration();
    int i = uiMode;
    if (paramInt == 2) {
      paramInt = 32;
    } else {
      paramInt = 16;
    }
    if ((i & 0x30) != paramInt)
    {
      if (shouldRecreateOnNightModeChange())
      {
        ((Activity)mContext).recreate();
      }
      else
      {
        localConfiguration = new Configuration(localConfiguration);
        DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
        uiMode = (paramInt | uiMode & 0xFFFFFFCF);
        localResources.updateConfiguration(localConfiguration, localDisplayMetrics);
        if (Build.VERSION.SDK_INT < 26) {
          ResourcesFlusher.flush(localResources);
        }
      }
      return true;
    }
    return false;
  }
  
  public boolean applyDayNight()
  {
    int i = getNightMode();
    int j = mapNightMode(i);
    boolean bool;
    if (j != -1) {
      bool = updateForNightMode(j);
    } else {
      bool = false;
    }
    if (i == 0)
    {
      ensureAutoNightModeManager();
      mAutoNightModeManager.setup();
    }
    mApplyDayNightCalled = true;
    return bool;
  }
  
  View callActivityOnCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return null;
  }
  
  @VisibleForTesting
  final AutoNightModeManager getAutoNightModeManager()
  {
    ensureAutoNightModeManager();
    return mAutoNightModeManager;
  }
  
  public boolean hasWindowFeature(int paramInt)
  {
    return (super.hasWindowFeature(paramInt)) || (mWindow.hasFeature(paramInt));
  }
  
  public boolean isHandleNativeActionModesEnabled()
  {
    return mHandleNativeActionModes;
  }
  
  int mapNightMode(int paramInt)
  {
    if (paramInt != -100)
    {
      if (paramInt != 0) {
        return paramInt;
      }
      ensureAutoNightModeManager();
      return mAutoNightModeManager.getApplyableNightMode();
    }
    return -1;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((paramBundle != null) && (mLocalNightMode == -100)) {
      mLocalNightMode = paramBundle.getInt("appcompat:local_night_mode", -100);
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (mAutoNightModeManager != null) {
      mAutoNightModeManager.cleanup();
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (mLocalNightMode != -100) {
      paramBundle.putInt("appcompat:local_night_mode", mLocalNightMode);
    }
  }
  
  public void onStart()
  {
    super.onStart();
    applyDayNight();
  }
  
  public void onStop()
  {
    super.onStop();
    if (mAutoNightModeManager != null) {
      mAutoNightModeManager.cleanup();
    }
  }
  
  public void setHandleNativeActionModesEnabled(boolean paramBoolean)
  {
    mHandleNativeActionModes = paramBoolean;
  }
  
  public void setLocalNightMode(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
      return;
    }
    if (mLocalNightMode != paramInt)
    {
      mLocalNightMode = paramInt;
      if (mApplyDayNightCalled) {
        applyDayNight();
      }
    }
  }
  
  Window.Callback wrapWindowCallback(Window.Callback paramCallback)
  {
    return new AppCompatWindowCallbackV14(paramCallback);
  }
  
  class AppCompatWindowCallbackV14
    extends AppCompatDelegateImplBase.AppCompatWindowCallbackBase
  {
    AppCompatWindowCallbackV14(Window.Callback paramCallback)
    {
      super(paramCallback);
    }
    
    public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback)
    {
      if (isHandleNativeActionModesEnabled()) {
        return startAsSupportActionMode(paramCallback);
      }
      return super.onWindowStartingActionMode(paramCallback);
    }
    
    final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback paramCallback)
    {
      paramCallback = new SupportActionModeWrapper.CallbackWrapper(mContext, paramCallback);
      android.support.v7.view.ActionMode localActionMode = startSupportActionMode(paramCallback);
      if (localActionMode != null) {
        return paramCallback.getActionModeWrapper(localActionMode);
      }
      return null;
    }
  }
  
  @VisibleForTesting
  final class AutoNightModeManager
  {
    private BroadcastReceiver mAutoTimeChangeReceiver;
    private IntentFilter mAutoTimeChangeReceiverFilter;
    private boolean mIsNight;
    private TwilightManager mTwilightManager;
    
    AutoNightModeManager(TwilightManager paramTwilightManager)
    {
      mTwilightManager = paramTwilightManager;
      mIsNight = paramTwilightManager.isNight();
    }
    
    final void cleanup()
    {
      if (mAutoTimeChangeReceiver != null)
      {
        mContext.unregisterReceiver(mAutoTimeChangeReceiver);
        mAutoTimeChangeReceiver = null;
      }
    }
    
    final void dispatchTimeChanged()
    {
      boolean bool = mTwilightManager.isNight();
      if (bool != mIsNight)
      {
        mIsNight = bool;
        applyDayNight();
      }
    }
    
    final int getApplyableNightMode()
    {
      mIsNight = mTwilightManager.isNight();
      if (mIsNight) {
        return 2;
      }
      return 1;
    }
    
    final void setup()
    {
      cleanup();
      if (mAutoTimeChangeReceiver == null) {
        mAutoTimeChangeReceiver = new BroadcastReceiver()
        {
          public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
          {
            dispatchTimeChanged();
          }
        };
      }
      if (mAutoTimeChangeReceiverFilter == null)
      {
        mAutoTimeChangeReceiverFilter = new IntentFilter();
        mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
        mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
      }
      mContext.registerReceiver(mAutoTimeChangeReceiver, mAutoTimeChangeReceiverFilter);
    }
  }
}
