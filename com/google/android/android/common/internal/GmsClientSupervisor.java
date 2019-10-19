package com.google.android.android.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class GmsClientSupervisor
{
  private static final Object zzdp = new Object();
  private static GmsClientSupervisor zzdq;
  
  public GmsClientSupervisor() {}
  
  public static GmsClientSupervisor getInstance(Context paramContext)
  {
    Object localObject = zzdp;
    try
    {
      if (zzdq == null) {
        zzdq = new MainActivity(paramContext.getApplicationContext());
      }
      return zzdq;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public boolean bindService(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    return start(new zza(paramComponentName, 129), paramServiceConnection, paramString);
  }
  
  public boolean bindService(String paramString1, ServiceConnection paramServiceConnection, String paramString2)
  {
    return start(new zza(paramString1, 129), paramServiceConnection, paramString2);
  }
  
  protected abstract void init(zza paramZza, ServiceConnection paramServiceConnection, String paramString);
  
  public final void put(String paramString1, String paramString2, int paramInt, ServiceConnection paramServiceConnection, String paramString3)
  {
    init(new zza(paramString1, paramString2, paramInt), paramServiceConnection, paramString3);
  }
  
  protected abstract boolean start(zza paramZza, ServiceConnection paramServiceConnection, String paramString);
  
  public void unbindService(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    init(new zza(paramComponentName, 129), paramServiceConnection, paramString);
  }
  
  public void unbindService(String paramString1, ServiceConnection paramServiceConnection, String paramString2)
  {
    init(new zza(paramString1, 129), paramServiceConnection, paramString2);
  }
  
  public final class zza
  {
    private final ComponentName mComponentName;
    private final String zzdr;
    private final String zzds;
    private final int zzdt;
    
    public zza(int paramInt)
    {
      zzdr = null;
      zzds = null;
      mComponentName = ((ComponentName)Preconditions.checkNotNull(this$1));
      zzdt = 129;
    }
    
    public zza(int paramInt)
    {
      zzdr = Preconditions.checkNotEmpty(this$1);
      zzds = "com.google.android.gms";
      mComponentName = null;
      zzdt = 129;
    }
    
    public zza(String paramString, int paramInt)
    {
      zzdr = Preconditions.checkNotEmpty(this$1);
      zzds = Preconditions.checkNotEmpty(paramString);
      mComponentName = null;
      zzdt = paramInt;
    }
    
    public final Intent createIntent(Context paramContext)
    {
      if (zzdr != null) {
        return new Intent(zzdr).setPackage(zzds);
      }
      return new Intent().setComponent(mComponentName);
    }
    
    public final boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof zza)) {
        return false;
      }
      paramObject = (zza)paramObject;
      return (Objects.equal(zzdr, zzdr)) && (Objects.equal(zzds, zzds)) && (Objects.equal(mComponentName, mComponentName)) && (zzdt == zzdt);
    }
    
    public final ComponentName getComponentName()
    {
      return mComponentName;
    }
    
    public final int getDefaultAccount()
    {
      return zzdt;
    }
    
    public final String getPackage()
    {
      return zzds;
    }
    
    public final int hashCode()
    {
      return Objects.hashCode(new Object[] { zzdr, zzds, mComponentName, Integer.valueOf(zzdt) });
    }
    
    public final String toString()
    {
      if (zzdr == null) {
        return mComponentName.flattenToString();
      }
      return zzdr;
    }
  }
}
