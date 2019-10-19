package com.google.android.android.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
public final class zzdr
  extends Log
{
  private final zzef zzarz;
  private zzag zzasa;
  private volatile Boolean zzasb;
  private final Alarm zzasc;
  private final zzev zzasd;
  private final List<Runnable> zzase = new ArrayList();
  private final Alarm zzasf;
  
  protected zzdr(zzbt paramZzbt)
  {
    super(paramZzbt);
    zzasd = new zzev(paramZzbt.zzbx());
    zzarz = new zzef(this);
    zzasc = new zzds(this, paramZzbt);
    zzasf = new zzdx(this, paramZzbt);
  }
  
  private final ApplicationInfo e(boolean paramBoolean)
  {
    zzgr();
    zzaj localZzaj = zzgf();
    String str;
    if (paramBoolean) {
      str = zzgo().zzjn();
    } else {
      str = null;
    }
    return localZzaj.zzbr(str);
  }
  
  private final void onServiceDisconnected(ComponentName paramComponentName)
  {
    zzaf();
    if (zzasa != null)
    {
      zzasa = null;
      zzgo().zzjl().append("Disconnected from device MeasurementService", paramComponentName);
      zzaf();
      zzdj();
    }
  }
  
  private final void start(Runnable paramRunnable)
    throws IllegalStateException
  {
    zzaf();
    if (isConnected())
    {
      paramRunnable.run();
      return;
    }
    if (zzase.size() >= 1000L)
    {
      zzgo().zzjd().zzbx("Discarding data. Max runnable queue size reached");
      return;
    }
    zzase.add(paramRunnable);
    zzasf.setAlarm(60000L);
    zzdj();
  }
  
  private final void zzcy()
  {
    zzaf();
    zzasd.start();
    zzasc.setAlarm(((Long)zzaf.zzakj.getDefaultValue()).longValue());
  }
  
  private final void zzcz()
  {
    zzaf();
    if (!isConnected()) {
      return;
    }
    zzgo().zzjl().zzbx("Inactivity, disconnecting from the service");
    disconnect();
  }
  
  private final boolean zzld()
  {
    zzgr();
    return true;
  }
  
  private final void zzlf()
  {
    zzaf();
    zzgo().zzjl().append("Processing queued up service tasks", Integer.valueOf(zzase.size()));
    Iterator localIterator = zzase.iterator();
    while (localIterator.hasNext())
    {
      Runnable localRunnable = (Runnable)localIterator.next();
      try
      {
        localRunnable.run();
      }
      catch (Exception localException)
      {
        zzgo().zzjd().append("Task exception while flushing queue", localException);
      }
    }
    zzase.clear();
    zzasf.cancel();
  }
  
  protected final void Open(zzdn paramZzdn)
  {
    zzaf();
    zzcl();
    start(new zzdw(this, paramZzdn));
  }
  
  protected final void Open(AtomicReference paramAtomicReference, String paramString1, String paramString2, String paramString3)
  {
    zzaf();
    zzcl();
    start(new zzeb(this, paramAtomicReference, paramString1, paramString2, paramString3, e(false)));
  }
  
  protected final void Open(AtomicReference paramAtomicReference, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    zzaf();
    zzcl();
    start(new zzec(this, paramAtomicReference, paramString1, paramString2, paramString3, paramBoolean, e(false)));
  }
  
  protected final void addProperty(zzad paramZzad, String paramString)
  {
    Preconditions.checkNotNull(paramZzad);
    zzaf();
    zzcl();
    boolean bool2 = zzld();
    boolean bool1;
    if ((bool2) && (zzgi().rmdir(paramZzad))) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    start(new zzdz(this, bool2, bool1, paramZzad, e(true), paramString));
  }
  
  public final void disconnect()
  {
    zzaf();
    zzcl();
    if (class_3.zzia()) {
      zzarz.zzlg();
    }
    try
    {
      ConnectionTracker localConnectionTracker = ConnectionTracker.getInstance();
      Context localContext = getContext();
      zzef localZzef = zzarz;
      localConnectionTracker.unbindService(localContext, localZzef);
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
    zzasa = null;
  }
  
  public final boolean isConnected()
  {
    zzaf();
    zzcl();
    return zzasa != null;
  }
  
  protected final void removeEventListener(zzag paramZzag)
  {
    zzaf();
    Preconditions.checkNotNull(paramZzag);
    zzasa = paramZzag;
    zzcy();
    zzlf();
  }
  
  protected final void resetAnalyticsData()
  {
    zzaf();
    zzgb();
    zzcl();
    ApplicationInfo localApplicationInfo = e(false);
    if (zzld()) {
      zzgi().resetAnalyticsData();
    }
    start(new zzdt(this, localApplicationInfo));
  }
  
  final void sendRequest(zzag paramZzag, AbstractSafeParcelable paramAbstractSafeParcelable, ApplicationInfo paramApplicationInfo)
  {
    zzaf();
    zzgb();
    zzcl();
    boolean bool = zzld();
    int j = 0;
    int i = 100;
    while ((j < 1001) && (i == 100))
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject;
      if (bool)
      {
        localObject = zzgi().doInBackground(100);
        if (localObject != null)
        {
          localArrayList.addAll((Collection)localObject);
          i = ((List)localObject).size();
          break label95;
        }
      }
      i = 0;
      label95:
      if ((paramAbstractSafeParcelable != null) && (i < 100)) {
        localArrayList.add(paramAbstractSafeParcelable);
      }
      localArrayList = (ArrayList)localArrayList;
      int m = localArrayList.size();
      int k = 0;
      while (k < m)
      {
        localObject = localArrayList.get(k);
        k += 1;
        localObject = (AbstractSafeParcelable)localObject;
        if ((localObject instanceof zzad))
        {
          localObject = (zzad)localObject;
          try
          {
            paramZzag.handleResult((zzad)localObject, paramApplicationInfo);
          }
          catch (RemoteException localRemoteException1)
          {
            zzgo().zzjd().append("Failed to send event to the service", localRemoteException1);
          }
        }
        else if ((localRemoteException1 instanceof zzfh))
        {
          zzfh localZzfh = (zzfh)localRemoteException1;
          try
          {
            paramZzag.write(localZzfh, paramApplicationInfo);
          }
          catch (RemoteException localRemoteException2)
          {
            zzgo().zzjd().append("Failed to send attribute to the service", localRemoteException2);
          }
        }
        else if ((localRemoteException2 instanceof ComponentInfo))
        {
          ComponentInfo localComponentInfo = (ComponentInfo)localRemoteException2;
          try
          {
            paramZzag.getPackageInfo(localComponentInfo, paramApplicationInfo);
          }
          catch (RemoteException localRemoteException3)
          {
            zzgo().zzjd().append("Failed to send conditional property to the service", localRemoteException3);
          }
        }
        else
        {
          zzgo().zzjd().zzbx("Discarding data. Unrecognized parcel type.");
        }
      }
      j += 1;
    }
  }
  
  protected final void setPreference(zzfh paramZzfh)
  {
    zzaf();
    zzcl();
    boolean bool;
    if ((zzld()) && (zzgi().rmdir(paramZzfh))) {
      bool = true;
    } else {
      bool = false;
    }
    start(new zzed(this, bool, paramZzfh, e(true)));
  }
  
  public final void terminate(AtomicReference paramAtomicReference)
  {
    zzaf();
    zzcl();
    start(new zzdu(this, paramAtomicReference, e(false)));
  }
  
  protected final void terminate(AtomicReference paramAtomicReference, boolean paramBoolean)
  {
    zzaf();
    zzcl();
    start(new zzee(this, paramAtomicReference, e(false), paramBoolean));
  }
  
  protected final void toggleState(ComponentInfo paramComponentInfo)
  {
    Preconditions.checkNotNull(paramComponentInfo);
    zzaf();
    zzcl();
    zzgr();
    boolean bool;
    if (zzgi().updateRows(paramComponentInfo)) {
      bool = true;
    } else {
      bool = false;
    }
    start(new zzea(this, true, bool, new ComponentInfo(paramComponentInfo), e(true), paramComponentInfo));
  }
  
  final void zzdj()
  {
    zzaf();
    zzcl();
    if (isConnected()) {
      return;
    }
    Object localObject = zzasb;
    int k = 0;
    label84:
    int i;
    if (localObject == null)
    {
      zzaf();
      zzcl();
      localObject = zzgp().zzju();
      boolean bool2;
      if ((localObject != null) && (((Boolean)localObject).booleanValue()))
      {
        bool2 = true;
      }
      else
      {
        zzgr();
        boolean bool1;
        if (zzgf().zzjb() == 1)
        {
          bool1 = true;
          i = 1;
        }
        else
        {
          zzgo().zzjl().zzbx("Checking service availability");
          localObject = zzgm();
          i = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(((zzco)localObject).getContext(), 12451000);
          if (i != 9) {
            if (i != 18) {
              switch (i)
              {
              default: 
                zzgo().zzjg().append("Unexpected service status", Integer.valueOf(i));
              }
            }
          }
          for (;;)
          {
            for (bool1 = false;; bool1 = true)
            {
              i = 0;
              break label348;
              zzgo().zzjg().zzbx("Service disabled");
              break;
              zzgo().zzjk().zzbx("Service container out of date");
              if (zzgm().zzme() < 13000) {
                break label294;
              }
              localObject = zzgp().zzju();
              if ((localObject != null) && (!((Boolean)localObject).booleanValue())) {
                break;
              }
            }
            zzgo().zzjl().zzbx("Service missing");
            label294:
            bool1 = false;
            break label84;
            zzgo().zzjl().zzbx("Service available");
            break;
            zzgo().zzjg().zzbx("Service updating");
            break;
            zzgo().zzjg().zzbx("Service invalid");
          }
        }
        label348:
        int j = i;
        if (!bool1)
        {
          j = i;
          if (zzgq().zzib())
          {
            zzgo().zzjd().zzbx("No way to upload. Consider using the full version of Analytics");
            j = 0;
          }
        }
        bool2 = bool1;
        if (j != 0)
        {
          zzgp().setStarred(bool1);
          bool2 = bool1;
        }
      }
      zzasb = Boolean.valueOf(bool2);
    }
    if (zzasb.booleanValue())
    {
      zzarz.zzlh();
      return;
    }
    if (!zzgq().zzib())
    {
      zzgr();
      localObject = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
      i = k;
      if (localObject != null)
      {
        i = k;
        if (((List)localObject).size() > 0) {
          i = 1;
        }
      }
      if (i != 0)
      {
        localObject = new Intent("com.google.android.gms.measurement.START");
        Context localContext = getContext();
        zzgr();
        ((Intent)localObject).setComponent(new ComponentName(localContext, "com.google.android.gms.measurement.AppMeasurementService"));
        zzarz.stopAll((Intent)localObject);
        return;
      }
      zzgo().zzjd().zzbx("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
    }
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
  
  protected final void zzkz()
  {
    zzaf();
    zzcl();
    start(new zzdv(this, e(true)));
  }
  
  protected final void zzlc()
  {
    zzaf();
    zzcl();
    start(new zzdy(this, e(true)));
  }
  
  final Boolean zzle()
  {
    return zzasb;
  }
}
