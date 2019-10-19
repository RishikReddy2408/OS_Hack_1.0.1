package com.google.android.android.common.aimsicd.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class zabq
  extends BroadcastReceiver
{
  private Context mContext;
  private final zabr zajh;
  
  public zabq(zabr paramZabr)
  {
    zajh = paramZabr;
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getData();
    if (paramContext != null) {
      paramContext = paramContext.getSchemeSpecificPart();
    } else {
      paramContext = null;
    }
    if ("com.google.android.gms".equals(paramContext))
    {
      zajh.cancel();
      unregister();
    }
  }
  
  public final void startListening(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public final void unregister()
  {
    try
    {
      if (mContext != null) {
        mContext.unregisterReceiver(this);
      }
      mContext = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
