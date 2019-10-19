package com.google.android.android.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.internal.BaseGmsClient;
import com.google.android.android.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.android.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzef
  implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener
{
  private volatile boolean zzasm;
  private volatile zzao zzasn;
  
  protected zzef(zzdr paramZzdr) {}
  
  public final void onConnected(Bundle paramBundle)
  {
    Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
    try
    {
      paramBundle = zzasn;
      boolean bool;
      Object localObject;
      zzasn = null;
    }
    catch (Throwable paramBundle)
    {
      try
      {
        paramBundle = paramBundle.getService();
        paramBundle = (zzag)paramBundle;
        bool = class_3.zzia();
        if (!bool) {
          zzasn = null;
        }
        localObject = zzasg;
        localObject = ((zzco)localObject).zzgn();
        ((zzbo)localObject).get(new zzei(this, paramBundle));
      }
      catch (DeadObjectException paramBundle)
      {
        for (;;) {}
      }
      catch (IllegalStateException paramBundle)
      {
        for (;;) {}
      }
      paramBundle = paramBundle;
    }
    zzasm = false;
    return;
    throw paramBundle;
  }
  
  public final void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
    zzap localZzap = zzasg.zzadj.zzkf();
    if (localZzap != null) {
      localZzap.zzjg().append("Service connection failed", paramConnectionResult);
    }
    try
    {
      zzasm = false;
      zzasn = null;
      zzasg.zzgn().get(new zzek(this));
      return;
    }
    catch (Throwable paramConnectionResult)
    {
      throw paramConnectionResult;
    }
  }
  
  public final void onConnectionSuspended(int paramInt)
  {
    Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
    zzasg.zzgo().zzjk().zzbx("Service connection suspended");
    zzasg.zzgn().get(new zzej(this));
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a10 = a9\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName)
  {
    Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
    zzasg.zzgo().zzjk().zzbx("Service disconnected");
    zzasg.zzgn().get(new zzeh(this, paramComponentName));
  }
  
  public final void stopAll(Intent paramIntent)
  {
    zzasg.zzaf();
    Context localContext = zzasg.getContext();
    ConnectionTracker localConnectionTracker = ConnectionTracker.getInstance();
    try
    {
      if (zzasm)
      {
        zzasg.zzgo().zzjl().zzbx("Connection attempt already in progress");
        return;
      }
      zzasg.zzgo().zzjl().zzbx("Using local app measurement service");
      zzasm = true;
      localConnectionTracker.bindService(localContext, paramIntent, zzdr.logDebug(zzasg), 129);
      return;
    }
    catch (Throwable paramIntent)
    {
      throw paramIntent;
    }
  }
  
  public final void zzlg()
  {
    if ((zzasn != null) && ((zzasn.isConnected()) || (zzasn.isConnecting()))) {
      zzasn.disconnect();
    }
    zzasn = null;
  }
  
  public final void zzlh()
  {
    zzasg.zzaf();
    Context localContext = zzasg.getContext();
    try
    {
      if (zzasm)
      {
        zzasg.zzgo().zzjl().zzbx("Connection attempt already in progress");
        return;
      }
      if ((zzasn != null) && ((!class_3.zzia()) || (zzasn.isConnecting()) || (zzasn.isConnected())))
      {
        zzasg.zzgo().zzjl().zzbx("Already awaiting connection attempt");
        return;
      }
      zzasn = new zzao(localContext, Looper.getMainLooper(), this, this);
      zzasg.zzgo().zzjl().zzbx("Connecting to remote service");
      zzasm = true;
      zzasn.checkAvailabilityAndConnect();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
