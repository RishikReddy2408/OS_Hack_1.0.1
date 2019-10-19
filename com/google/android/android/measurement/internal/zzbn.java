package com.google.android.android.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.internal.measurement.zzfu;
import com.google.android.android.internal.measurement.zzfv;
import com.google.android.android.internal.measurement.zzfw;
import com.google.android.android.internal.measurement.zzfy;
import com.google.android.android.internal.measurement.zzga;
import com.google.android.android.internal.measurement.zzgc;
import com.google.android.android.internal.measurement.zzyx;
import com.google.android.android.internal.measurement.zzyy;
import com.google.android.android.internal.measurement.zzzg;
import com.google.android.android.measurement.AppMeasurement.Event;
import com.google.android.android.measurement.AppMeasurement.Param;
import com.google.android.android.measurement.AppMeasurement.UserProperty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.util.Map;

public final class zzbn
  extends zzez
  implements Context
{
  @VisibleForTesting
  private static int zzaon;
  @VisibleForTesting
  private static int zzaoo;
  private final Map<String, Map<String, String>> zzaop = new ArrayMap();
  private final Map<String, Map<String, Boolean>> zzaoq = new ArrayMap();
  private final Map<String, Map<String, Boolean>> zzaor = new ArrayMap();
  private final Map<String, com.google.android.gms.internal.measurement.zzgb> zzaos = new ArrayMap();
  private final Map<String, Map<String, Integer>> zzaot = new ArrayMap();
  private final Map<String, String> zzaou = new ArrayMap();
  
  zzbn(zzfa paramZzfa)
  {
    super(paramZzfa);
  }
  
  private final com.google.android.android.internal.measurement.zzgb hash(String paramString, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return new com.google.android.android.internal.measurement.zzgb();
    }
    Object localObject = zzyx.get(paramArrayOfByte, 0, paramArrayOfByte.length);
    paramArrayOfByte = new com.google.android.android.internal.measurement.zzgb();
    try
    {
      paramArrayOfByte.digest((zzyx)localObject);
      localObject = zzgo().zzjl();
      Long localLong = zzawe;
      String str = zzafx;
      ((zzar)localObject).append("Parsed config. version, gmp_app_id", localLong, str);
      return paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      zzgo().zzjg().append("Unable to merge remote config. appId", zzap.zzbv(paramString), paramArrayOfByte);
    }
    return new com.google.android.android.internal.measurement.zzgb();
  }
  
  private static Map multiply(com.google.android.android.internal.measurement.zzgb paramZzgb)
  {
    ArrayMap localArrayMap = new ArrayMap();
    if ((paramZzgb != null) && (zzawg != null))
    {
      paramZzgb = zzawg;
      int j = paramZzgb.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramZzgb[i];
        if (localObject != null) {
          localArrayMap.put(zzoj, value);
        }
        i += 1;
      }
    }
    return localArrayMap;
  }
  
  private final void multiply(String paramString, com.google.android.android.internal.measurement.zzgb paramZzgb)
  {
    ArrayMap localArrayMap1 = new ArrayMap();
    ArrayMap localArrayMap2 = new ArrayMap();
    ArrayMap localArrayMap3 = new ArrayMap();
    if ((paramZzgb != null) && (zzawh != null))
    {
      paramZzgb = zzawh;
      int j = paramZzgb.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramZzgb[i];
        if (TextUtils.isEmpty(name))
        {
          zzgo().zzjg().zzbx("EventConfig contained null event name");
        }
        else
        {
          String str = AppMeasurement.Event.zzal(name);
          if (!TextUtils.isEmpty(str)) {
            name = str;
          }
          localArrayMap1.put(name, zzawb);
          localArrayMap2.put(name, zzawc);
          if (zzawd != null) {
            if ((zzawd.intValue() >= zzaoo) && (zzawd.intValue() <= zzaon)) {
              localArrayMap3.put(name, zzawd);
            } else {
              zzgo().zzjg().append("Invalid sampling rate. Event name, sample rate", name, zzawd);
            }
          }
        }
        i += 1;
      }
    }
    zzaoq.put(paramString, localArrayMap1);
    zzaor.put(paramString, localArrayMap2);
    zzaot.put(paramString, localArrayMap3);
  }
  
  private final void zzce(String paramString)
  {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    if (zzaos.get(paramString) == null)
    {
      Object localObject = zzjq().zzbn(paramString);
      if (localObject == null)
      {
        zzaop.put(paramString, null);
        zzaoq.put(paramString, null);
        zzaor.put(paramString, null);
        zzaos.put(paramString, null);
        zzaou.put(paramString, null);
        zzaot.put(paramString, null);
        return;
      }
      localObject = hash(paramString, (byte[])localObject);
      zzaop.put(paramString, multiply((com.google.android.android.internal.measurement.zzgb)localObject));
      multiply(paramString, (com.google.android.android.internal.measurement.zzgb)localObject);
      zzaos.put(paramString, localObject);
      zzaou.put(paramString, null);
    }
  }
  
  final boolean copy(String paramString1, String paramString2)
  {
    zzaf();
    zzce(paramString1);
    if ("ecommerce_purchase".equals(paramString2)) {
      return true;
    }
    paramString1 = (Map)zzaor.get(paramString1);
    if (paramString1 != null)
    {
      paramString1 = (Boolean)paramString1.get(paramString2);
      if (paramString1 == null) {
        return false;
      }
      return paramString1.booleanValue();
    }
    return false;
  }
  
  public final String get(String paramString1, String paramString2)
  {
    zzaf();
    zzce(paramString1);
    paramString1 = (Map)zzaop.get(paramString1);
    if (paramString1 != null) {
      return (String)paramString1.get(paramString2);
    }
    return null;
  }
  
  protected final boolean putAll(String paramString1, byte[] paramArrayOfByte, String paramString2)
  {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString1);
    com.google.android.android.internal.measurement.zzgb localZzgb = hash(paramString1, paramArrayOfByte);
    if (localZzgb == null) {
      return false;
    }
    multiply(paramString1, localZzgb);
    zzaos.put(paramString1, localZzgb);
    zzaou.put(paramString1, paramString2);
    zzaop.put(paramString1, multiply(localZzgb));
    paramString2 = zzjp();
    Object localObject1 = zzawi;
    Preconditions.checkNotNull(localObject1);
    int m = localObject1.length;
    int i = 0;
    while (i < m)
    {
      zzfy[] arrayOfZzfy = localObject1[i];
      Object localObject2 = zzava;
      int n = localObject2.length;
      int j = 0;
      Object localObject3;
      while (j < n)
      {
        localObject3 = localObject2[j];
        String str1 = AppMeasurement.Event.zzal(zzavf);
        if (str1 != null) {
          zzavf = str1;
        }
        localObject3 = zzavg;
        int i1 = localObject3.length;
        k = 0;
        while (k < i1)
        {
          str1 = localObject3[k];
          String str2 = AppMeasurement.Param.zzal(zzavn);
          if (str2 != null) {
            zzavn = str2;
          }
          k += 1;
        }
        j += 1;
      }
      arrayOfZzfy = zzauz;
      int k = arrayOfZzfy.length;
      j = 0;
      while (j < k)
      {
        localObject2 = arrayOfZzfy[j];
        localObject3 = AppMeasurement.UserProperty.zzal(zzavu);
        if (localObject3 != null) {
          zzavu = ((String)localObject3);
        }
        j += 1;
      }
      i += 1;
    }
    paramString2.zzjq().trim(paramString1, (zzfu[])localObject1);
    zzawi = null;
    try
    {
      i = localZzgb.zzvu();
      localObject1 = new byte[i];
      paramString2 = (String)localObject1;
      i = localObject1.length;
      localZzgb.multiply(zzyy.toString(paramString2, 0, i));
    }
    catch (IOException paramString2)
    {
      zzgo().zzjg().append("Unable to serialize reduced-size config. Storing full config instead. appId", zzap.zzbv(paramString1), paramString2);
      paramString2 = paramArrayOfByte;
    }
    paramArrayOfByte = zzjq();
    Preconditions.checkNotEmpty(paramString1);
    paramArrayOfByte.zzaf();
    paramArrayOfByte.zzcl();
    localObject1 = new ContentValues();
    ((ContentValues)localObject1).put("remote_config", paramString2);
    try
    {
      paramString2 = paramArrayOfByte.getWritableDatabase();
      i = paramString2.update("apps", (ContentValues)localObject1, "app_id = ?", new String[] { paramString1 });
      if (i == 0L)
      {
        paramArrayOfByte.zzgo().zzjd().append("Failed to update remote config (got 0). appId", zzap.zzbv(paramString1));
        return true;
      }
    }
    catch (SQLiteException paramString2)
    {
      paramArrayOfByte.zzgo().zzjd().append("Error storing remote config. appId", zzap.zzbv(paramString1), paramString2);
    }
    return true;
  }
  
  final int size(String paramString1, String paramString2)
  {
    zzaf();
    zzce(paramString1);
    paramString1 = (Map)zzaot.get(paramString1);
    if (paramString1 != null)
    {
      paramString1 = (Integer)paramString1.get(paramString2);
      if (paramString1 == null) {
        return 1;
      }
      return paramString1.intValue();
    }
    return 1;
  }
  
  final boolean trim(String paramString1, String paramString2)
  {
    zzaf();
    zzce(paramString1);
    if ((zzck(paramString1)) && (zzfk.zzcv(paramString2))) {
      return true;
    }
    if ((zzcl(paramString1)) && (zzfk.zzcq(paramString2))) {
      return true;
    }
    paramString1 = (Map)zzaoq.get(paramString1);
    if (paramString1 != null)
    {
      paramString1 = (Boolean)paramString1.get(paramString2);
      if (paramString1 == null) {
        return false;
      }
      return paramString1.booleanValue();
    }
    return false;
  }
  
  protected final com.google.android.android.internal.measurement.zzgb zzcf(String paramString)
  {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    zzce(paramString);
    return (com.google.android.android.internal.measurement.zzgb)zzaos.get(paramString);
  }
  
  protected final String zzcg(String paramString)
  {
    zzaf();
    return (String)zzaou.get(paramString);
  }
  
  protected final void zzch(String paramString)
  {
    zzaf();
    zzaou.put(paramString, null);
  }
  
  final void zzci(String paramString)
  {
    zzaf();
    zzaos.remove(paramString);
  }
  
  final long zzcj(String paramString)
  {
    String str = get(paramString, "measurement.account.time_zone_offset_minutes");
    if (!TextUtils.isEmpty(str)) {
      try
      {
        long l = Long.parseLong(str);
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        zzgo().zzjg().append("Unable to parse timezone offset. appId", zzap.zzbv(paramString), localNumberFormatException);
      }
    }
    return 0L;
  }
  
  final boolean zzck(String paramString)
  {
    return "1".equals(get(paramString, "measurement.upload.blacklist_internal"));
  }
  
  final boolean zzcl(String paramString)
  {
    return "1".equals(get(paramString, "measurement.upload.blacklist_public"));
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
}
