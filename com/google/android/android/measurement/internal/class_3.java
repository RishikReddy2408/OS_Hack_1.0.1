package com.google.android.android.measurement.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.ProcessUtils;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class class_3
  extends zzco
{
  private Boolean zzahf;
  @NonNull
  private Context zzahg = Source.zzahh;
  private Boolean zzyk;
  
  class_3(zzbt paramZzbt)
  {
    super(paramZzbt);
    zzaf.parseChildren(paramZzbt);
  }
  
  static String zzht()
  {
    return (String)zzaf.zzajd.getDefaultValue();
  }
  
  public static long zzhw()
  {
    return ((Long)zzaf.zzakg.getDefaultValue()).longValue();
  }
  
  public static long zzhx()
  {
    return ((Long)zzaf.zzajg.getDefaultValue()).longValue();
  }
  
  public static boolean zzhz()
  {
    return ((Boolean)zzaf.zzajc.getDefaultValue()).booleanValue();
  }
  
  static boolean zzia()
  {
    return ((Boolean)zzaf.zzalb.getDefaultValue()).booleanValue();
  }
  
  static boolean zzic()
  {
    return ((Boolean)zzaf.zzald.getDefaultValue()).booleanValue();
  }
  
  public final boolean attribute(String paramString, zzaf.zza paramZza)
  {
    return toBoolean(paramString, paramZza);
  }
  
  public final double getValue(String paramString, zzaf.zza paramZza)
  {
    if (paramString == null) {
      return ((Double)paramZza.getDefaultValue()).doubleValue();
    }
    paramString = zzahg.get(paramString, paramZza.getKey());
    if (TextUtils.isEmpty(paramString)) {
      return ((Double)paramZza.getDefaultValue()).doubleValue();
    }
    try
    {
      paramString = paramZza.get(Double.valueOf(Double.parseDouble(paramString)));
      paramString = (Double)paramString;
      double d = paramString.doubleValue();
      return d;
    }
    catch (NumberFormatException paramString)
    {
      for (;;) {}
    }
    return ((Double)paramZza.getDefaultValue()).doubleValue();
  }
  
  public final boolean isTrue(zzaf.zza paramZza)
  {
    return toBoolean(null, paramZza);
  }
  
  public final int next(String paramString, zzaf.zza paramZza)
  {
    if (paramString == null) {
      return ((Integer)paramZza.getDefaultValue()).intValue();
    }
    paramString = zzahg.get(paramString, paramZza.getKey());
    if (TextUtils.isEmpty(paramString)) {
      return ((Integer)paramZza.getDefaultValue()).intValue();
    }
    try
    {
      paramString = paramZza.get(Integer.valueOf(Integer.parseInt(paramString)));
      paramString = (Integer)paramString;
      int i = paramString.intValue();
      return i;
    }
    catch (NumberFormatException paramString)
    {
      for (;;) {}
    }
    return ((Integer)paramZza.getDefaultValue()).intValue();
  }
  
  public final long parseAndAdd(String paramString, zzaf.zza paramZza)
  {
    if (paramString == null) {
      return ((Long)paramZza.getDefaultValue()).longValue();
    }
    paramString = zzahg.get(paramString, paramZza.getKey());
    if (TextUtils.isEmpty(paramString)) {
      return ((Long)paramZza.getDefaultValue()).longValue();
    }
    try
    {
      paramString = paramZza.get(Long.valueOf(Long.parseLong(paramString)));
      paramString = (Long)paramString;
      long l = paramString.longValue();
      return l;
    }
    catch (NumberFormatException paramString)
    {
      for (;;) {}
    }
    return ((Long)paramZza.getDefaultValue()).longValue();
  }
  
  final void setImageResource(Context paramContext)
  {
    zzahg = paramContext;
  }
  
  public final boolean toBoolean(String paramString, zzaf.zza paramZza)
  {
    if (paramString == null) {
      return ((Boolean)paramZza.getDefaultValue()).booleanValue();
    }
    paramString = zzahg.get(paramString, paramZza.getKey());
    if (TextUtils.isEmpty(paramString)) {
      return ((Boolean)paramZza.getDefaultValue()).booleanValue();
    }
    return ((Boolean)paramZza.get(Boolean.valueOf(Boolean.parseBoolean(paramString)))).booleanValue();
  }
  
  public final int zzat(String paramString)
  {
    return next(paramString, zzaf.zzajr);
  }
  
  final Boolean zzau(String paramString)
  {
    Preconditions.checkNotEmpty(paramString);
    try
    {
      Object localObject = getContext().getPackageManager();
      if (localObject == null)
      {
        zzgo().zzjd().zzbx("Failed to load metadata: PackageManager is null");
        return null;
      }
      localObject = Wrappers.packageManager(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
      if (localObject == null)
      {
        zzgo().zzjd().zzbx("Failed to load metadata: ApplicationInfo is null");
        return null;
      }
      if (metaData == null)
      {
        zzgo().zzjd().zzbx("Failed to load metadata: Metadata bundle is null");
        return null;
      }
      Bundle localBundle = metaData;
      boolean bool = localBundle.containsKey(paramString);
      if (!bool) {
        return null;
      }
      localObject = metaData;
      bool = ((Bundle)localObject).getBoolean(paramString);
      return Boolean.valueOf(bool);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      zzgo().zzjd().append("Failed to load metadata: Package name not found", paramString);
    }
    return null;
  }
  
  public final boolean zzav(String paramString)
  {
    return "1".equals(zzahg.get(paramString, "gaia_collection_enabled"));
  }
  
  public final boolean zzaw(String paramString)
  {
    return "1".equals(zzahg.get(paramString, "measurement.event_sampling_enabled"));
  }
  
  final boolean zzax(String paramString)
  {
    return toBoolean(paramString, zzaf.zzakp);
  }
  
  final boolean zzay(String paramString)
  {
    return toBoolean(paramString, zzaf.zzakr);
  }
  
  final boolean zzaz(String paramString)
  {
    return toBoolean(paramString, zzaf.zzaks);
  }
  
  final boolean zzba(String paramString)
  {
    return toBoolean(paramString, zzaf.zzakk);
  }
  
  final String zzbb(String paramString)
  {
    zzaf.zza localZza = zzaf.zzakl;
    if (paramString == null) {
      return (String)localZza.getDefaultValue();
    }
    return (String)localZza.get(zzahg.get(paramString, localZza.getKey()));
  }
  
  final boolean zzbc(String paramString)
  {
    return toBoolean(paramString, zzaf.zzakt);
  }
  
  final boolean zzbd(String paramString)
  {
    return toBoolean(paramString, zzaf.zzaku);
  }
  
  final boolean zzbe(String paramString)
  {
    return toBoolean(paramString, zzaf.zzakx);
  }
  
  final boolean zzbf(String paramString)
  {
    return toBoolean(paramString, zzaf.zzaky);
  }
  
  final boolean zzbg(String paramString)
  {
    return toBoolean(paramString, zzaf.zzala);
  }
  
  final boolean zzbh(String paramString)
  {
    return toBoolean(paramString, zzaf.zzakz);
  }
  
  final boolean zzbi(String paramString)
  {
    return toBoolean(paramString, zzaf.zzale);
  }
  
  final boolean zzbj(String paramString)
  {
    return toBoolean(paramString, zzaf.zzalf);
  }
  
  public final boolean zzdw()
  {
    if (zzyk == null) {}
    for (;;)
    {
      try
      {
        if (zzyk == null)
        {
          Object localObject = getContext().getApplicationInfo();
          String str = ProcessUtils.getMyProcessName();
          if (localObject != null)
          {
            localObject = processName;
            if ((localObject == null) || (!((String)localObject).equals(str))) {
              break label107;
            }
            bool = true;
            zzyk = Boolean.valueOf(bool);
          }
          if (zzyk == null)
          {
            zzyk = Boolean.TRUE;
            zzgo().zzjd().zzbx("My process not in the list of running processes");
          }
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      return zzyk.booleanValue();
      label107:
      boolean bool = false;
    }
  }
  
  public final long zzhc()
  {
    zzgr();
    return 13001L;
  }
  
  public final boolean zzhu()
  {
    zzgr();
    Boolean localBoolean = zzau("firebase_analytics_collection_deactivated");
    return (localBoolean != null) && (localBoolean.booleanValue());
  }
  
  public final Boolean zzhv()
  {
    zzgr();
    return zzau("firebase_analytics_collection_enabled");
  }
  
  public final String zzhy()
  {
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = ((Class)localObject).getMethod("get", new Class[] { String.class, String.class });
      localObject = ((Method)localObject).invoke(null, new Object[] { "debug.firebase.analytics.app", "" });
      return (String)localObject;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      zzgo().zzjd().append("SystemProperties.get() threw an exception", localInvocationTargetException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      zzgo().zzjd().append("Could not access SystemProperties.get()", localIllegalAccessException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      zzgo().zzjd().append("Could not find SystemProperties.get() method", localNoSuchMethodException);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      zzgo().zzjd().append("Could not find SystemProperties class", localClassNotFoundException);
    }
    return "";
  }
  
  final boolean zzib()
  {
    if (zzahf == null)
    {
      zzahf = zzau("app_measurement_lite");
      if (zzahf == null) {
        zzahf = Boolean.valueOf(false);
      }
    }
    return (zzahf.booleanValue()) || (!zzadj.zzkn());
  }
}
