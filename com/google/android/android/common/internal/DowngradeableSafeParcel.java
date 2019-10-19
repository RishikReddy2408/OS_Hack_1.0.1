package com.google.android.android.common.internal;

import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class DowngradeableSafeParcel
  extends AbstractSafeParcelable
  implements ReflectedParcelable
{
  private static final Object zzdb = new Object();
  private static ClassLoader zzdc = null;
  private static Integer zzdd = null;
  private boolean zzde = false;
  
  public DowngradeableSafeParcel() {}
  
  protected static boolean canUnparcelSafely(String paramString)
  {
    combine();
    return true;
  }
  
  private static ClassLoader combine()
  {
    Object localObject = zzdb;
    try
    {
      return null;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected static Integer getUnparcelClientVersion()
  {
    Object localObject = zzdb;
    try
    {
      return null;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected abstract boolean prepareForClientVersion(int paramInt);
  
  public void setShouldDowngrade(boolean paramBoolean)
  {
    zzde = paramBoolean;
  }
  
  protected boolean shouldDowngrade()
  {
    return zzde;
  }
}
