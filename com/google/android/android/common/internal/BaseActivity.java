package com.google.android.android.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.google.android.android.common.stats.ConnectionTracker;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class BaseActivity
  implements ServiceConnection
{
  private ComponentName mComponentName;
  private int mState;
  private IBinder zzcy;
  private final Set<ServiceConnection> zzdz;
  private boolean zzea;
  private final GmsClientSupervisor.zza zzeb;
  
  public BaseActivity(MainActivity paramMainActivity, GmsClientSupervisor.zza paramZza)
  {
    zzeb = paramZza;
    zzdz = new HashSet();
    mState = 2;
  }
  
  public final void bind(ServiceConnection paramServiceConnection, String paramString)
  {
    MainActivity.access$getContext(zzec);
    MainActivity.load(zzec);
    zzdz.remove(paramServiceConnection);
  }
  
  public final boolean bind(ServiceConnection paramServiceConnection)
  {
    return zzdz.contains(paramServiceConnection);
  }
  
  public final IBinder getBinder()
  {
    return zzcy;
  }
  
  public final ComponentName getComponentName()
  {
    return mComponentName;
  }
  
  public final int getState()
  {
    return mState;
  }
  
  public final boolean isBound()
  {
    return zzea;
  }
  
  public final boolean isEmpty()
  {
    return zzdz.isEmpty();
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    HashMap localHashMap = MainActivity.access$getRunning(zzec);
    try
    {
      MainActivity.access$getMHandler(zzec).removeMessages(1, zzeb);
      zzcy = paramIBinder;
      mComponentName = paramComponentName;
      Iterator localIterator = zzdz.iterator();
      while (localIterator.hasNext()) {
        ((ServiceConnection)localIterator.next()).onServiceConnected(paramComponentName, paramIBinder);
      }
      mState = 1;
      return;
    }
    catch (Throwable paramComponentName)
    {
      throw paramComponentName;
    }
  }
  
  public final void onServiceConnected(String paramString)
  {
    MainActivity.access$getMHandler(zzec).removeMessages(1, zzeb);
    MainActivity.access$getContext(zzec).unbindService(MainActivity.load(zzec), this);
    zzea = false;
    mState = 2;
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName)
  {
    HashMap localHashMap = MainActivity.access$getRunning(zzec);
    try
    {
      MainActivity.access$getMHandler(zzec).removeMessages(1, zzeb);
      zzcy = null;
      mComponentName = paramComponentName;
      Iterator localIterator = zzdz.iterator();
      while (localIterator.hasNext()) {
        ((ServiceConnection)localIterator.next()).onServiceDisconnected(paramComponentName);
      }
      mState = 2;
      return;
    }
    catch (Throwable paramComponentName)
    {
      throw paramComponentName;
    }
  }
  
  public final void onStart(ServiceConnection paramServiceConnection, String paramString)
  {
    MainActivity.access$getContext(zzec);
    MainActivity.load(zzec);
    zzeb.createIntent(MainActivity.load(zzec));
    zzdz.add(paramServiceConnection);
  }
  
  public final void onStart(String paramString)
  {
    mState = 3;
    zzea = MainActivity.access$getContext(zzec).update(MainActivity.load(zzec), paramString, zzeb.createIntent(MainActivity.load(zzec)), this, zzeb.getDefaultAccount());
    if (zzea)
    {
      paramString = MainActivity.access$getMHandler(zzec).obtainMessage(1, zzeb);
      MainActivity.access$getMHandler(zzec).sendMessageDelayed(paramString, MainActivity.access$getMLastTouch(zzec));
      return;
    }
    mState = 2;
    paramString = zzec;
    try
    {
      paramString = MainActivity.access$getContext(paramString);
      MainActivity localMainActivity = zzec;
      paramString.unbindService(MainActivity.load(localMainActivity), this);
      return;
    }
    catch (IllegalArgumentException paramString) {}
  }
}
