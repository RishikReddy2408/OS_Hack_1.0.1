package com.google.android.android.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import com.google.android.android.common.util.Clock;

public final class zzew
  extends zzez
{
  private final Alarm zzata;
  private final AlarmManager zzyt = (AlarmManager)getContext().getSystemService("alarm");
  private Integer zzyu;
  
  protected zzew(zzfa paramZzfa)
  {
    super(paramZzfa);
    zzata = new zzex(this, paramZzfa.zzmb(), paramZzfa);
  }
  
  private final int getJobId()
  {
    if (zzyu == null)
    {
      String str = String.valueOf(getContext().getPackageName());
      if (str.length() != 0) {
        str = "measurement".concat(str);
      } else {
        str = new String("measurement");
      }
      zzyu = Integer.valueOf(str.hashCode());
    }
    return zzyu.intValue();
  }
  
  private final PendingIntent zzeo()
  {
    Intent localIntent = new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementReceiver");
    localIntent.setAction("com.google.android.gms.measurement.UPLOAD");
    return PendingIntent.getBroadcast(getContext(), 0, localIntent, 0);
  }
  
  private final void zzlm()
  {
    JobScheduler localJobScheduler = (JobScheduler)getContext().getSystemService("jobscheduler");
    zzgo().zzjl().append("Cancelling job. JobID", Integer.valueOf(getJobId()));
    localJobScheduler.cancel(getJobId());
  }
  
  public final void cancel()
  {
    zzcl();
    zzyt.cancel(zzeo());
    zzata.cancel();
    if (Build.VERSION.SDK_INT >= 24) {
      zzlm();
    }
  }
  
  public final void setAlarm(long paramLong)
  {
    zzcl();
    zzgr();
    if (!zzbj.isSystemApp(getContext())) {
      zzgo().zzjk().zzbx("Receiver not registered/enabled");
    }
    zzgr();
    if (!zzfk.set(getContext(), false)) {
      zzgo().zzjk().zzbx("Service not registered/enabled");
    }
    cancel();
    long l = zzbx().elapsedRealtime();
    if ((paramLong < Math.max(0L, ((Long)zzaf.zzaka.getDefaultValue()).longValue())) && (!zzata.zzej()))
    {
      zzgo().zzjl().zzbx("Scheduling upload with DelayedRunnable");
      zzata.setAlarm(paramLong);
    }
    zzgr();
    if (Build.VERSION.SDK_INT >= 24)
    {
      zzgo().zzjl().zzbx("Scheduling upload with JobScheduler");
      Object localObject = new ComponentName(getContext(), "com.google.android.gms.measurement.AppMeasurementJobService");
      JobScheduler localJobScheduler = (JobScheduler)getContext().getSystemService("jobscheduler");
      localObject = new JobInfo.Builder(getJobId(), (ComponentName)localObject);
      ((JobInfo.Builder)localObject).setMinimumLatency(paramLong);
      ((JobInfo.Builder)localObject).setOverrideDeadline(paramLong << 1);
      PersistableBundle localPersistableBundle = new PersistableBundle();
      localPersistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
      ((JobInfo.Builder)localObject).setExtras(localPersistableBundle);
      localObject = ((JobInfo.Builder)localObject).build();
      zzgo().zzjl().append("Scheduling job. JobID", Integer.valueOf(getJobId()));
      localJobScheduler.schedule((JobInfo)localObject);
      return;
    }
    zzgo().zzjl().zzbx("Scheduling upload with AlarmManager");
    zzyt.setInexactRepeating(2, l + paramLong, Math.max(((Long)zzaf.zzajv.getDefaultValue()).longValue(), paramLong), zzeo());
  }
  
  protected final boolean zzgt()
  {
    zzyt.cancel(zzeo());
    if (Build.VERSION.SDK_INT >= 24) {
      zzlm();
    }
    return false;
  }
}
