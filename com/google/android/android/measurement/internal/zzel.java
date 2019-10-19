package com.google.android.android.measurement.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PersistableBundle;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzep;

public final class zzel<T extends Context,  extends zzep>
{
  private final T zzaby;
  
  public zzel(Context paramContext)
  {
    Preconditions.checkNotNull(paramContext);
    zzaby = paramContext;
  }
  
  private final void queueEvent(Runnable paramRunnable)
  {
    zzfa localZzfa = zzfa.add(zzaby);
    localZzfa.zzgn().get(new zzeo(this, localZzfa, paramRunnable));
  }
  
  private final zzap zzgo()
  {
    return zzbt.register(zzaby, null).zzgo();
  }
  
  public final IBinder onBind(Intent paramIntent)
  {
    if (paramIntent == null)
    {
      zzgo().zzjd().zzbx("onBind called with null intent");
      return null;
    }
    paramIntent = paramIntent.getAction();
    if ("com.google.android.gms.measurement.START".equals(paramIntent)) {
      return new zzbv(zzfa.add(zzaby));
    }
    zzgo().zzjg().append("onBind received unknown action", paramIntent);
    return null;
  }
  
  public final void onCreate()
  {
    zzbt localZzbt = zzbt.register(zzaby, null);
    zzap localZzap = localZzbt.zzgo();
    localZzbt.zzgr();
    localZzap.zzjl().zzbx("Local AppMeasurementService is starting up");
  }
  
  public final void onDestroy()
  {
    zzbt localZzbt = zzbt.register(zzaby, null);
    zzap localZzap = localZzbt.zzgo();
    localZzbt.zzgr();
    localZzap.zzjl().zzbx("Local AppMeasurementService is shutting down");
  }
  
  public final void onRebind(Intent paramIntent)
  {
    if (paramIntent == null)
    {
      zzgo().zzjd().zzbx("onRebind called with null intent");
      return;
    }
    paramIntent = paramIntent.getAction();
    zzgo().zzjl().append("onRebind called. action", paramIntent);
  }
  
  public final int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    zzbt localZzbt = zzbt.register(zzaby, null);
    zzap localZzap = localZzbt.zzgo();
    if (paramIntent == null)
    {
      localZzap.zzjg().zzbx("AppMeasurementService started with null intent");
      return 2;
    }
    String str = paramIntent.getAction();
    localZzbt.zzgr();
    localZzap.zzjl().append("Local AppMeasurementService called. startId, action", Integer.valueOf(paramInt2), str);
    if ("com.google.android.gms.measurement.UPLOAD".equals(str)) {
      queueEvent(new zzem(this, paramInt2, localZzap, paramIntent));
    }
    return 2;
  }
  
  public final boolean onStartJob(JobParameters paramJobParameters)
  {
    zzbt localZzbt = zzbt.register(zzaby, null);
    zzap localZzap = localZzbt.zzgo();
    String str = paramJobParameters.getExtras().getString("action");
    localZzbt.zzgr();
    localZzap.zzjl().append("Local AppMeasurementJobService called. action", str);
    if ("com.google.android.gms.measurement.UPLOAD".equals(str)) {
      queueEvent(new zzen(this, localZzap, paramJobParameters));
    }
    return true;
  }
  
  public final boolean onUnbind(Intent paramIntent)
  {
    if (paramIntent == null)
    {
      zzgo().zzjd().zzbx("onUnbind called with null intent");
      return true;
    }
    paramIntent = paramIntent.getAction();
    zzgo().zzjl().append("onUnbind called for intent. action", paramIntent);
    return true;
  }
}
