package com.google.android.android.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

class zzay
  extends BroadcastReceiver
{
  @VisibleForTesting
  private static final String zzabi = "com.google.android.gms.measurement.internal.zzay";
  private boolean zzabj;
  private boolean zzabk;
  private final zzfa zzamz;
  
  zzay(zzfa paramZzfa)
  {
    Preconditions.checkNotNull(paramZzfa);
    zzamz = paramZzfa;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    zzamz.zzlr();
    paramContext = paramIntent.getAction();
    zzamz.zzgo().zzjl().append("NetworkBroadcastReceiver received action", paramContext);
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramContext))
    {
      boolean bool = zzamz.zzlo().zzfb();
      if (zzabk != bool)
      {
        zzabk = bool;
        zzamz.zzgn().get(new zzaz(this, bool));
      }
    }
    else
    {
      zzamz.zzgo().zzjg().append("NetworkBroadcastReceiver received unknown action", paramContext);
    }
  }
  
  public final void unregister()
  {
    zzamz.zzlr();
    zzamz.zzgn().zzaf();
    zzamz.zzgn().zzaf();
    if (!zzabj) {
      return;
    }
    zzamz.zzgo().zzjl().zzbx("Unregistering connectivity change receiver");
    zzabj = false;
    zzabk = false;
    Context localContext = zzamz.getContext();
    try
    {
      localContext.unregisterReceiver(this);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      zzamz.zzgo().zzjd().append("Failed to unregister the network broadcast receiver", localIllegalArgumentException);
    }
  }
  
  public final void zzey()
  {
    zzamz.zzlr();
    zzamz.zzgn().zzaf();
    if (zzabj) {
      return;
    }
    zzamz.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    zzabk = zzamz.zzlo().zzfb();
    zzamz.zzgo().zzjl().append("Registering connectivity change receiver. Network connected", Boolean.valueOf(zzabk));
    zzabj = true;
  }
}
