package com.google.android.android.measurement.internal;

import android.support.annotation.GuardedBy;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzap
  extends zzcp
{
  private long zzadt = -1L;
  private char zzalw = '\000';
  @GuardedBy("this")
  private String zzalx;
  private final zzar zzaly = new zzar(this, 6, false, false);
  private final zzar zzalz = new zzar(this, 6, true, false);
  private final zzar zzama = new zzar(this, 6, false, true);
  private final zzar zzamb = new zzar(this, 5, false, false);
  private final zzar zzamc = new zzar(this, 5, true, false);
  private final zzar zzamd = new zzar(this, 5, false, true);
  private final zzar zzame = new zzar(this, 4, false, false);
  private final zzar zzamf = new zzar(this, 3, false, false);
  private final zzar zzamg = new zzar(this, 2, false, false);
  
  zzap(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private static String format(boolean paramBoolean, Object paramObject)
  {
    if (paramObject == null) {
      return "";
    }
    Object localObject1 = paramObject;
    if ((paramObject instanceof Integer)) {
      localObject1 = Long.valueOf(((Integer)paramObject).intValue());
    }
    boolean bool = localObject1 instanceof Long;
    int i = 0;
    Object localObject2;
    if (bool)
    {
      if (!paramBoolean) {
        return String.valueOf(localObject1);
      }
      localObject2 = (Long)localObject1;
      if (Math.abs(((Long)localObject2).longValue()) < 100L) {
        return String.valueOf(localObject1);
      }
      if (String.valueOf(localObject1).charAt(0) == '-') {
        paramObject = "-";
      } else {
        paramObject = "";
      }
      localObject1 = String.valueOf(Math.abs(((Long)localObject2).longValue()));
      long l1 = Math.round(Math.pow(10.0D, ((String)localObject1).length() - 1));
      long l2 = Math.round(Math.pow(10.0D, ((String)localObject1).length()) - 1.0D);
      localObject1 = new StringBuilder(String.valueOf(paramObject).length() + 43 + String.valueOf(paramObject).length());
      ((StringBuilder)localObject1).append(paramObject);
      ((StringBuilder)localObject1).append(l1);
      ((StringBuilder)localObject1).append("...");
      ((StringBuilder)localObject1).append(paramObject);
      ((StringBuilder)localObject1).append(l2);
      return ((StringBuilder)localObject1).toString();
    }
    if ((localObject1 instanceof Boolean)) {
      return String.valueOf(localObject1);
    }
    if ((localObject1 instanceof Throwable))
    {
      Object localObject3 = (Throwable)localObject1;
      if (paramBoolean) {
        paramObject = localObject3.getClass().getName();
      } else {
        paramObject = ((Throwable)localObject3).toString();
      }
      paramObject = new StringBuilder(paramObject);
      localObject1 = zzbw(AppMeasurement.class.getCanonicalName());
      localObject2 = zzbw(com.google.android.gms.measurement.internal.zzbt.class.getCanonicalName());
      localObject3 = ((Throwable)localObject3).getStackTrace();
      int j = localObject3.length;
      while (i < j)
      {
        Object localObject4 = localObject3[i];
        if (!localObject4.isNativeMethod())
        {
          String str = localObject4.getClassName();
          if (str != null)
          {
            str = zzbw(str);
            if ((str.equals(localObject1)) || (str.equals(localObject2)))
            {
              paramObject.append(": ");
              paramObject.append(localObject4);
              break;
            }
          }
        }
        i += 1;
      }
      return paramObject.toString();
    }
    if ((localObject1 instanceof zzas)) {
      return zzas.formatString((zzas)localObject1);
    }
    if (paramBoolean) {
      return "-";
    }
    return String.valueOf(localObject1);
  }
  
  static String getDisplayTitle(boolean paramBoolean, String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    String str1 = paramString;
    if (paramString == null) {
      str1 = "";
    }
    String str2 = format(paramBoolean, paramObject1);
    paramObject2 = format(paramBoolean, paramObject2);
    paramObject3 = format(paramBoolean, paramObject3);
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = "";
    if (!TextUtils.isEmpty(str1))
    {
      localStringBuilder.append(str1);
      paramString = ": ";
    }
    paramObject1 = paramString;
    if (!TextUtils.isEmpty(str2))
    {
      localStringBuilder.append(paramString);
      localStringBuilder.append(str2);
      paramObject1 = ", ";
    }
    paramString = paramObject1;
    if (!TextUtils.isEmpty(paramObject2))
    {
      localStringBuilder.append(paramObject1);
      localStringBuilder.append(paramObject2);
      paramString = ", ";
    }
    if (!TextUtils.isEmpty(paramObject3))
    {
      localStringBuilder.append(paramString);
      localStringBuilder.append(paramObject3);
    }
    return localStringBuilder.toString();
  }
  
  protected static Object zzbv(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return new zzas(paramString);
  }
  
  private static String zzbw(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    int i = paramString.lastIndexOf('.');
    if (i == -1) {
      return paramString;
    }
    return paramString.substring(0, i);
  }
  
  private final String zzjm()
  {
    try
    {
      if (zzalx == null) {
        if (zzadj.zzkm() != null) {
          zzalx = zzadj.zzkm();
        } else {
          zzalx = class_3.zzht();
        }
      }
      String str = zzalx;
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected final boolean isLoggable(int paramInt)
  {
    return Log.isLoggable(zzjm(), paramInt);
  }
  
  protected final void put(int paramInt, String paramString)
  {
    Log.println(paramInt, zzjm(), paramString);
  }
  
  protected final void transform(int paramInt, boolean paramBoolean1, boolean paramBoolean2, String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if ((!paramBoolean1) && (isLoggable(paramInt))) {
      put(paramInt, getDisplayTitle(false, paramString, paramObject1, paramObject2, paramObject3));
    }
    if ((!paramBoolean2) && (paramInt >= 5))
    {
      Preconditions.checkNotNull(paramString);
      zzbo localZzbo = zzadj.zzkh();
      if (localZzbo == null)
      {
        put(6, "Scheduler not set. Not logging error/warn");
        return;
      }
      if (!localZzbo.isInitialized())
      {
        put(6, "Scheduler not initialized. Not logging error/warn");
        return;
      }
      int i = paramInt;
      if (paramInt < 0) {
        i = 0;
      }
      paramInt = i;
      if (i >= 9) {
        paramInt = 8;
      }
      localZzbo.get(new zzaq(this, paramInt, paramString, paramObject1, paramObject2, paramObject3));
    }
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
  
  public final zzar zzjd()
  {
    return zzaly;
  }
  
  public final zzar zzje()
  {
    return zzalz;
  }
  
  public final zzar zzjf()
  {
    return zzama;
  }
  
  public final zzar zzjg()
  {
    return zzamb;
  }
  
  public final zzar zzjh()
  {
    return zzamc;
  }
  
  public final zzar zzji()
  {
    return zzamd;
  }
  
  public final zzar zzjj()
  {
    return zzame;
  }
  
  public final zzar zzjk()
  {
    return zzamf;
  }
  
  public final zzar zzjl()
  {
    return zzamg;
  }
  
  public final String zzjn()
  {
    Object localObject = zzgpzzand.zzfm();
    if ((localObject != null) && (localObject != zzba.zzanc))
    {
      String str = String.valueOf(second);
      localObject = (String)first;
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(localObject).length());
      localStringBuilder.append(str);
      localStringBuilder.append(":");
      localStringBuilder.append((String)localObject);
      return localStringBuilder.toString();
    }
    return null;
  }
}
