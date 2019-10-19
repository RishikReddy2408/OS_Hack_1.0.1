package com.google.android.android.common.aimsicd.internal;

import android.os.Handler;

final class zabi
  implements BackgroundDetector.BackgroundStateChangeListener
{
  zabi(GoogleApiManager paramGoogleApiManager) {}
  
  public final void onBackgroundStateChanged(boolean paramBoolean)
  {
    GoogleApiManager.access$getHandler(zail).sendMessage(GoogleApiManager.access$getHandler(zail).obtainMessage(1, Boolean.valueOf(paramBoolean)));
  }
}
