package com.google.android.android.internal.measurement;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build.VERSION;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

public abstract class zzsl<T>
{
  private static final Object zzbqy = new Object();
  private static boolean zzbqz = false;
  private static volatile Boolean zzbra = null;
  @SuppressLint({"StaticFieldLeak"})
  private static Context zzri = null;
  private final zzsv zzbrb;
  final String zzbrc;
  private final String zzbrd;
  private final T zzbre;
  private T zzbrf = null;
  private volatile zzsi zzbrg = null;
  private volatile SharedPreferences zzbrh = null;
  
  private zzsl(zzsv paramZzsv, String paramString, Object paramObject)
  {
    if (zzsv.getAvatarUrl(paramZzsv) != null)
    {
      zzbrb = paramZzsv;
      String str1 = String.valueOf(zzsv.getSoundPath(paramZzsv));
      String str2 = String.valueOf(paramString);
      if (str2.length() != 0) {
        str1 = str1.concat(str2);
      } else {
        str1 = new String(str1);
      }
      zzbrd = str1;
      paramZzsv = String.valueOf(zzsv.lookupName(paramZzsv));
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramZzsv = paramZzsv.concat(paramString);
      } else {
        paramZzsv = new String(paramZzsv);
      }
      zzbrc = paramZzsv;
      zzbre = paramObject;
      return;
    }
    throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
  }
  
  private static zzsl f(zzsv paramZzsv, String paramString, boolean paramBoolean)
  {
    return new zzsr(paramZzsv, paramString, Boolean.valueOf(paramBoolean));
  }
  
  private static zzsl fromByteArray(zzsv paramZzsv, String paramString, int paramInt)
  {
    return new zzsq(paramZzsv, paramString, Integer.valueOf(paramInt));
  }
  
  private static Object getProvider(zzsu paramZzsu)
  {
    try
    {
      Object localObject = paramZzsu.zztj();
      return localObject;
    }
    catch (SecurityException localSecurityException)
    {
      long l;
      for (;;) {}
    }
    l = Binder.clearCallingIdentity();
    try
    {
      paramZzsu = paramZzsu.zztj();
      Binder.restoreCallingIdentity(l);
      return paramZzsu;
    }
    catch (Throwable paramZzsu)
    {
      Binder.restoreCallingIdentity(l);
      throw paramZzsu;
    }
  }
  
  private static zzsl id(zzsv paramZzsv, String paramString1, String paramString2)
  {
    return new zzst(paramZzsv, paramString1, paramString2);
  }
  
  public static void init(Context paramContext)
  {
    Object localObject = zzbqy;
    for (;;)
    {
      Context localContext;
      try
      {
        if ((Build.VERSION.SDK_INT < 24) || (!paramContext.isDeviceProtectedStorage()))
        {
          localContext = paramContext.getApplicationContext();
          if (localContext != null) {
            break label63;
          }
        }
        if (zzri != paramContext) {
          zzbra = null;
        }
        zzri = paramContext;
        zzbqz = false;
        return;
      }
      catch (Throwable paramContext)
      {
        throw paramContext;
      }
      label63:
      paramContext = localContext;
    }
  }
  
  static boolean invoke(String paramString, boolean paramBoolean)
  {
    try
    {
      paramBoolean = zzth();
      if (paramBoolean)
      {
        paramString = getProvider(new zzso(paramString, false));
        paramString = (Boolean)paramString;
        paramBoolean = paramString.booleanValue();
        return paramBoolean;
      }
      return false;
    }
    catch (SecurityException paramString)
    {
      Log.e("PhenotypeFlag", "Unable to read GServices, returning default value.", paramString);
    }
    return false;
  }
  
  private static zzsl valueOf(zzsv paramZzsv, String paramString, double paramDouble)
  {
    return new zzss(paramZzsv, paramString, Double.valueOf(paramDouble));
  }
  
  private static zzsl valueOf(zzsv paramZzsv, String paramString, long paramLong)
  {
    return new zzsp(paramZzsv, paramString, Long.valueOf(paramLong));
  }
  
  private final Object zzte()
  {
    Object localObject;
    if (!invoke("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false))
    {
      if (zzsv.getAvatarUrl(zzbrb) != null)
      {
        localObject = zztg();
        if (localObject == null) {
          break label101;
        }
        localObject = (String)getProvider(new zzsm(this, (zzsi)localObject));
        if (localObject == null) {
          break label101;
        }
        return zzfj((String)localObject);
      }
    }
    else
    {
      localObject = String.valueOf(zzbrc);
      if (((String)localObject).length() != 0) {
        localObject = "Bypass reading Phenotype values for flag: ".concat((String)localObject);
      } else {
        localObject = new String("Bypass reading Phenotype values for flag: ");
      }
      Log.w("PhenotypeFlag", (String)localObject);
    }
    label101:
    return null;
  }
  
  private final Object zztf()
  {
    if (zzth()) {
      try
      {
        localObject = getProvider(new zzsn(this));
        localObject = (String)localObject;
        if (localObject != null)
        {
          localObject = zzfj((String)localObject);
          return localObject;
        }
      }
      catch (SecurityException localSecurityException)
      {
        Object localObject = String.valueOf(zzbrc);
        if (((String)localObject).length() != 0) {
          localObject = "Unable to read GServices for flag: ".concat((String)localObject);
        } else {
          localObject = new String("Unable to read GServices for flag: ");
        }
        Log.e("PhenotypeFlag", (String)localObject, localSecurityException);
      }
    }
    return null;
  }
  
  private final zzsi zztg()
  {
    Object localObject;
    if (zzbrg == null) {
      localObject = zzri;
    }
    try
    {
      localObject = ((Context)localObject).getContentResolver();
      zzsv localZzsv = zzbrb;
      localObject = zzsi.addImage((ContentResolver)localObject, zzsv.getAvatarUrl(localZzsv));
      zzbrg = ((zzsi)localObject);
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    return zzbrg;
  }
  
  private static boolean zzth()
  {
    if (zzbra == null)
    {
      Context localContext = zzri;
      boolean bool = false;
      if (localContext != null)
      {
        if (PermissionChecker.checkSelfPermission(zzri, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
          bool = true;
        }
        zzbra = Boolean.valueOf(bool);
      }
      else
      {
        return false;
      }
    }
    return zzbra.booleanValue();
  }
  
  public final Object getDefaultValue()
  {
    return zzbre;
  }
  
  public final Object unwrap()
  {
    if (zzri != null)
    {
      Object localObject = zzte();
      if (localObject != null) {
        return localObject;
      }
      localObject = zztf();
      if (localObject != null) {
        return localObject;
      }
      return zzbre;
    }
    throw new IllegalStateException("Must call PhenotypeFlag.init() first");
  }
  
  protected abstract Object zzfj(String paramString);
}
