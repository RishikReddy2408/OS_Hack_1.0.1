package com.google.android.android.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.android.common.util.Clock;
import com.google.android.android.internal.measurement.zzfv;
import com.google.android.android.internal.measurement.zzfw;
import com.google.android.android.internal.measurement.zzfx;
import com.google.android.android.internal.measurement.zzfy;
import com.google.android.android.internal.measurement.zzfz;
import com.google.android.android.internal.measurement.zzgd;
import com.google.android.android.internal.measurement.zzgf;
import com.google.android.android.internal.measurement.zzgg;
import com.google.android.android.internal.measurement.zzgh;
import com.google.android.android.internal.measurement.zzgi;
import com.google.android.android.internal.measurement.zzgj;
import com.google.android.android.internal.measurement.zzgl;
import com.google.android.android.internal.measurement.zzyy;
import com.google.android.android.internal.measurement.zzzg;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class zzfg
  extends zzez
{
  zzfg(zzfa paramZzfa)
  {
    super(paramZzfa);
  }
  
  static zzgg[] delete(zzgg[] paramArrayOfZzgg, String paramString, Object paramObject)
  {
    int j = paramArrayOfZzgg.length;
    int i = 0;
    while (i < j)
    {
      localObject = paramArrayOfZzgg[i];
      if (paramString.equals(name))
      {
        zzawx = null;
        zzamp = null;
        zzauh = null;
        if ((paramObject instanceof Long))
        {
          zzawx = ((Long)paramObject);
          return paramArrayOfZzgg;
        }
        if ((paramObject instanceof String))
        {
          zzamp = ((String)paramObject);
          return paramArrayOfZzgg;
        }
        if (!(paramObject instanceof Double)) {
          return paramArrayOfZzgg;
        }
        zzauh = ((Double)paramObject);
        return paramArrayOfZzgg;
      }
      i += 1;
    }
    Object localObject = new zzgg[paramArrayOfZzgg.length + 1];
    System.arraycopy(paramArrayOfZzgg, 0, localObject, 0, paramArrayOfZzgg.length);
    zzgg localZzgg = new zzgg();
    name = paramString;
    if ((paramObject instanceof Long)) {
      zzawx = ((Long)paramObject);
    } else if ((paramObject instanceof String)) {
      zzamp = ((String)paramObject);
    } else if ((paramObject instanceof Double)) {
      zzauh = ((Double)paramObject);
    }
    localObject[paramArrayOfZzgg.length] = localZzgg;
    return localObject;
    return paramArrayOfZzgg;
  }
  
  static boolean get(long[] paramArrayOfLong, int paramInt)
  {
    if (paramInt >= paramArrayOfLong.length << 6) {
      return false;
    }
    return (1L << paramInt % 64 & paramArrayOfLong[(paramInt / 64)]) != 0L;
  }
  
  static Object getPath(zzgf paramZzgf, String paramString)
  {
    paramZzgf = getProperty(paramZzgf, paramString);
    if (paramZzgf != null)
    {
      if (zzamp != null) {
        return zzamp;
      }
      if (zzawx != null) {
        return zzawx;
      }
      if (zzauh != null) {
        return zzauh;
      }
    }
    return null;
  }
  
  static zzgg getProperty(zzgf paramZzgf, String paramString)
  {
    paramZzgf = zzawt;
    int j = paramZzgf.length;
    int i = 0;
    while (i < j)
    {
      zzgg localZzgg = paramZzgf[i];
      if (name.equals(paramString)) {
        return localZzgg;
      }
      i += 1;
    }
    return null;
  }
  
  private static void name(StringBuilder paramStringBuilder, int paramInt, String paramString, Object paramObject)
  {
    if (paramObject == null) {
      return;
    }
    toString(paramStringBuilder, paramInt + 1);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(": ");
    paramStringBuilder.append(paramObject);
    paramStringBuilder.append('\n');
  }
  
  static long[] toArray(BitSet paramBitSet)
  {
    int k = (paramBitSet.length() + 63) / 64;
    long[] arrayOfLong = new long[k];
    int i = 0;
    while (i < k)
    {
      arrayOfLong[i] = 0L;
      int j = 0;
      while (j < 64)
      {
        int m = (i << 6) + j;
        if (m >= paramBitSet.length()) {
          break;
        }
        if (paramBitSet.get(m)) {
          arrayOfLong[i] |= 1L << j;
        }
        j += 1;
      }
      i += 1;
    }
    return arrayOfLong;
  }
  
  private final void toHtml(StringBuilder paramStringBuilder, int paramInt, zzfw paramZzfw)
  {
    if (paramZzfw == null) {
      return;
    }
    toString(paramStringBuilder, paramInt);
    paramStringBuilder.append("filter {\n");
    name(paramStringBuilder, paramInt, "complement", zzavm);
    name(paramStringBuilder, paramInt, "param_name", zzgl().zzbt(zzavn));
    int j = paramInt + 1;
    zzfz localZzfz = zzavk;
    if (localZzfz != null)
    {
      toString(paramStringBuilder, j);
      paramStringBuilder.append("string_filter");
      paramStringBuilder.append(" {\n");
      Object localObject;
      if (zzavw != null)
      {
        localObject = "UNKNOWN_MATCH_TYPE";
        switch (zzavw.intValue())
        {
        default: 
          break;
        case 6: 
          localObject = "IN_LIST";
          break;
        case 5: 
          localObject = "EXACT";
          break;
        case 4: 
          localObject = "PARTIAL";
          break;
        case 3: 
          localObject = "ENDS_WITH";
          break;
        case 2: 
          localObject = "BEGINS_WITH";
          break;
        case 1: 
          localObject = "REGEXP";
        }
        name(paramStringBuilder, j, "match_type", localObject);
      }
      name(paramStringBuilder, j, "expression", zzavx);
      name(paramStringBuilder, j, "case_sensitive", zzavy);
      if (zzavz.length > 0)
      {
        toString(paramStringBuilder, j + 1);
        paramStringBuilder.append("expression_list {\n");
        localObject = zzavz;
        int k = localObject.length;
        int i = 0;
        while (i < k)
        {
          localZzfz = localObject[i];
          toString(paramStringBuilder, j + 2);
          paramStringBuilder.append(localZzfz);
          paramStringBuilder.append("\n");
          i += 1;
        }
        paramStringBuilder.append("}\n");
      }
      toString(paramStringBuilder, j);
      paramStringBuilder.append("}\n");
    }
    toHtml(paramStringBuilder, j, "number_filter", zzavl);
    toString(paramStringBuilder, paramInt);
    paramStringBuilder.append("}\n");
  }
  
  private final void toHtml(StringBuilder paramStringBuilder, int paramInt, String paramString, zzfx paramZzfx)
  {
    if (paramZzfx == null) {
      return;
    }
    toString(paramStringBuilder, paramInt);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(" {\n");
    if (zzavo != null)
    {
      paramString = "UNKNOWN_COMPARISON_TYPE";
      switch (zzavo.intValue())
      {
      default: 
        break;
      case 4: 
        paramString = "BETWEEN";
        break;
      case 3: 
        paramString = "EQUAL";
        break;
      case 2: 
        paramString = "GREATER_THAN";
        break;
      case 1: 
        paramString = "LESS_THAN";
      }
      name(paramStringBuilder, paramInt, "comparison_type", paramString);
    }
    name(paramStringBuilder, paramInt, "match_as_float", zzavp);
    name(paramStringBuilder, paramInt, "comparison_value", zzavq);
    name(paramStringBuilder, paramInt, "min_comparison_value", zzavr);
    name(paramStringBuilder, paramInt, "max_comparison_value", zzavs);
    toString(paramStringBuilder, paramInt);
    paramStringBuilder.append("}\n");
  }
  
  private static void toString(StringBuilder paramStringBuilder, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      paramStringBuilder.append("  ");
      i += 1;
    }
  }
  
  private static void toString(StringBuilder paramStringBuilder, int paramInt, String paramString, zzgj paramZzgj)
  {
    if (paramZzgj == null) {
      return;
    }
    toString(paramStringBuilder, 3);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(" {\n");
    paramString = zzayf;
    int j = 0;
    int k;
    int i;
    long l;
    if (paramString != null)
    {
      toString(paramStringBuilder, 4);
      paramStringBuilder.append("results: ");
      paramString = zzayf;
      k = paramString.length;
      i = 0;
      paramInt = 0;
      while (i < k)
      {
        l = paramString[i];
        if (paramInt != 0) {
          paramStringBuilder.append(", ");
        }
        paramStringBuilder.append(Long.valueOf(l));
        i += 1;
        paramInt += 1;
      }
      paramStringBuilder.append('\n');
    }
    if (zzaye != null)
    {
      toString(paramStringBuilder, 4);
      paramStringBuilder.append("status: ");
      paramString = zzaye;
      k = paramString.length;
      paramInt = 0;
      i = j;
      while (i < k)
      {
        l = paramString[i];
        if (paramInt != 0) {
          paramStringBuilder.append(", ");
        }
        paramStringBuilder.append(Long.valueOf(l));
        i += 1;
        paramInt += 1;
      }
      paramStringBuilder.append('\n');
    }
    toString(paramStringBuilder, 3);
    paramStringBuilder.append("}\n");
  }
  
  static boolean zzcp(String paramString)
  {
    return (paramString != null) && (paramString.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)")) && (paramString.length() <= 310);
  }
  
  final boolean add(zzad paramZzad, ApplicationInfo paramApplicationInfo)
  {
    Preconditions.checkNotNull(paramZzad);
    Preconditions.checkNotNull(paramApplicationInfo);
    if ((TextUtils.isEmpty(zzafx)) && (TextUtils.isEmpty(zzagk)))
    {
      zzgr();
      return false;
    }
    return true;
  }
  
  final byte[] compress(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.close();
      localByteArrayOutputStream.close();
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      return paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      zzgo().zzjd().append("Failed to gzip content", paramArrayOfByte);
      throw paramArrayOfByte;
    }
  }
  
  final void contains(zzgl paramZzgl, Object paramObject)
  {
    Preconditions.checkNotNull(paramObject);
    zzamp = null;
    zzawx = null;
    zzauh = null;
    if ((paramObject instanceof String))
    {
      zzamp = ((String)paramObject);
      return;
    }
    if ((paramObject instanceof Long))
    {
      zzawx = ((Long)paramObject);
      return;
    }
    if ((paramObject instanceof Double))
    {
      zzauh = ((Double)paramObject);
      return;
    }
    zzgo().zzjd().append("Ignoring invalid (type) user attribute value", paramObject);
  }
  
  final byte[] decompress(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      paramArrayOfByte = new ByteArrayInputStream(paramArrayOfByte);
      GZIPInputStream localGZIPInputStream = new GZIPInputStream(paramArrayOfByte);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte['?'];
      for (;;)
      {
        int i = localGZIPInputStream.read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      localGZIPInputStream.close();
      paramArrayOfByte.close();
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      return paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      zzgo().zzjd().append("Failed to ungzip content", paramArrayOfByte);
      throw paramArrayOfByte;
    }
  }
  
  final byte[] operate(zzgh paramZzgh)
  {
    try
    {
      int i = paramZzgh.zzvu();
      byte[] arrayOfByte = new byte[i];
      i = arrayOfByte.length;
      zzyy localZzyy = zzyy.toString(arrayOfByte, 0, i);
      paramZzgh.multiply(localZzyy);
      localZzyy.zzyt();
      return arrayOfByte;
    }
    catch (IOException paramZzgh)
    {
      zzgo().zzjd().append("Data loss. Failed to serialize batch", paramZzgh);
    }
    return null;
  }
  
  final void replace(zzgg paramZzgg, Object paramObject)
  {
    Preconditions.checkNotNull(paramObject);
    zzamp = null;
    zzawx = null;
    zzauh = null;
    if ((paramObject instanceof String))
    {
      zzamp = ((String)paramObject);
      return;
    }
    if ((paramObject instanceof Long))
    {
      zzawx = ((Long)paramObject);
      return;
    }
    if ((paramObject instanceof Double))
    {
      zzauh = ((Double)paramObject);
      return;
    }
    zzgo().zzjd().append("Ignoring invalid (type) event param value", paramObject);
  }
  
  final String toHtml(zzfv paramZzfv)
  {
    if (paramZzfv == null) {
      return "null";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\nevent_filter {\n");
    Integer localInteger = zzave;
    int i = 0;
    name(localStringBuilder, 0, "filter_id", localInteger);
    name(localStringBuilder, 0, "event_name", zzgl().zzbs(zzavf));
    toHtml(localStringBuilder, 1, "event_count_filter", zzavi);
    localStringBuilder.append("  filters {\n");
    paramZzfv = zzavg;
    int j = paramZzfv.length;
    while (i < j)
    {
      toHtml(localStringBuilder, 2, paramZzfv[i]);
      i += 1;
    }
    toString(localStringBuilder, 1);
    localStringBuilder.append("}\n}\n");
    return localStringBuilder.toString();
  }
  
  final String toString(zzfy paramZzfy)
  {
    if (paramZzfy == null) {
      return "null";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\nproperty_filter {\n");
    name(localStringBuilder, 0, "filter_id", zzave);
    name(localStringBuilder, 0, "property_name", zzgl().zzbu(zzavu));
    toHtml(localStringBuilder, 1, zzavv);
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
  
  final Parcelable unmarshall(byte[] paramArrayOfByte, Parcelable.Creator paramCreator)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    Parcel localParcel = Parcel.obtain();
    try
    {
      int i = paramArrayOfByte.length;
      zzgo().zzjd().zzbx("Failed to load parcelable from buffer");
    }
    catch (Throwable paramArrayOfByte)
    {
      try
      {
        localParcel.unmarshall(paramArrayOfByte, 0, i);
        localParcel.setDataPosition(0);
        paramArrayOfByte = paramCreator.createFromParcel(localParcel);
        paramArrayOfByte = (Parcelable)paramArrayOfByte;
        localParcel.recycle();
        return paramArrayOfByte;
      }
      catch (SafeParcelReader.ParseException paramArrayOfByte)
      {
        for (;;) {}
      }
      paramArrayOfByte = paramArrayOfByte;
    }
    localParcel.recycle();
    return null;
    localParcel.recycle();
    throw paramArrayOfByte;
  }
  
  final String updateBookmarks(zzgh paramZzgh)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\nbatch {\n");
    if (zzawy != null)
    {
      paramZzgh = zzawy;
      int m = paramZzgh.length;
      int i = 0;
      while (i < m)
      {
        zzgf[] arrayOfZzgf = paramZzgh[i];
        if ((arrayOfZzgf != null) && (arrayOfZzgf != null))
        {
          toString(localStringBuilder, 1);
          localStringBuilder.append("bundle {\n");
          name(localStringBuilder, 1, "protocol_version", zzaxa);
          name(localStringBuilder, 1, "platform", zzaxi);
          name(localStringBuilder, 1, "gmp_version", zzaxm);
          name(localStringBuilder, 1, "uploading_gmp_version", zzaxn);
          name(localStringBuilder, 1, "config_version", zzaxy);
          name(localStringBuilder, 1, "gmp_app_id", zzafx);
          name(localStringBuilder, 1, "admob_app_id", zzawj);
          name(localStringBuilder, 1, "app_id", zztt);
          name(localStringBuilder, 1, "app_version", zzts);
          name(localStringBuilder, 1, "app_version_major", zzaxu);
          name(localStringBuilder, 1, "firebase_instance_id", zzafz);
          name(localStringBuilder, 1, "dev_cert_hash", zzaxq);
          name(localStringBuilder, 1, "app_store", zzage);
          name(localStringBuilder, 1, "upload_timestamp_millis", zzaxd);
          name(localStringBuilder, 1, "start_timestamp_millis", zzaxe);
          name(localStringBuilder, 1, "end_timestamp_millis", zzaxf);
          name(localStringBuilder, 1, "previous_bundle_start_timestamp_millis", zzaxg);
          name(localStringBuilder, 1, "previous_bundle_end_timestamp_millis", zzaxh);
          name(localStringBuilder, 1, "app_instance_id", zzafw);
          name(localStringBuilder, 1, "resettable_device_id", zzaxo);
          name(localStringBuilder, 1, "device_id", zzaxx);
          name(localStringBuilder, 1, "ds_id", zzaya);
          name(localStringBuilder, 1, "limited_ad_tracking", zzaxp);
          name(localStringBuilder, 1, "os_version", zzaxj);
          name(localStringBuilder, 1, "device_model", zzaxk);
          name(localStringBuilder, 1, "user_default_language", zzaia);
          name(localStringBuilder, 1, "time_zone_offset_minutes", zzaxl);
          name(localStringBuilder, 1, "bundle_sequential_index", zzaxr);
          name(localStringBuilder, 1, "service_upload", zzaxs);
          name(localStringBuilder, 1, "health_monitor", zzagv);
          if ((zzaxz != null) && (zzaxz.longValue() != 0L)) {
            name(localStringBuilder, 1, "android_id", zzaxz);
          }
          if (zzayc != null) {
            name(localStringBuilder, 1, "retry_counter", zzayc);
          }
          Object localObject1 = zzaxc;
          int k;
          int j;
          Object localObject2;
          if (localObject1 != null)
          {
            k = localObject1.length;
            j = 0;
            while (j < k)
            {
              localObject2 = localObject1[j];
              if (localObject2 != null)
              {
                toString(localStringBuilder, 2);
                localStringBuilder.append("user_property {\n");
                name(localStringBuilder, 2, "set_timestamp_millis", zzayl);
                name(localStringBuilder, 2, "name", zzgl().zzbu(name));
                name(localStringBuilder, 2, "string_value", zzamp);
                name(localStringBuilder, 2, "int_value", zzawx);
                name(localStringBuilder, 2, "double_value", zzauh);
                toString(localStringBuilder, 2);
                localStringBuilder.append("}\n");
              }
              j += 1;
            }
          }
          localObject1 = zzaxt;
          if (localObject1 != null)
          {
            k = localObject1.length;
            j = 0;
            while (j < k)
            {
              localObject2 = localObject1[j];
              if (localObject2 != null)
              {
                toString(localStringBuilder, 2);
                localStringBuilder.append("audience_membership {\n");
                name(localStringBuilder, 2, "audience_id", zzauy);
                name(localStringBuilder, 2, "new_audience", zzawo);
                toString(localStringBuilder, 2, "current_data", zzawm);
                toString(localStringBuilder, 2, "previous_data", zzawn);
                toString(localStringBuilder, 2);
                localStringBuilder.append("}\n");
              }
              j += 1;
            }
          }
          arrayOfZzgf = zzaxb;
          if (arrayOfZzgf != null)
          {
            int n = arrayOfZzgf.length;
            j = 0;
            while (j < n)
            {
              localObject1 = arrayOfZzgf[j];
              if (localObject1 != null)
              {
                toString(localStringBuilder, 2);
                localStringBuilder.append("event {\n");
                name(localStringBuilder, 2, "name", zzgl().zzbs(name));
                name(localStringBuilder, 2, "timestamp_millis", zzawu);
                name(localStringBuilder, 2, "previous_timestamp_millis", zzawv);
                name(localStringBuilder, 2, "count", count);
                localObject1 = zzawt;
                if (localObject1 != null)
                {
                  int i1 = localObject1.length;
                  k = 0;
                  while (k < i1)
                  {
                    localObject2 = localObject1[k];
                    if (localObject2 != null)
                    {
                      toString(localStringBuilder, 3);
                      localStringBuilder.append("param {\n");
                      name(localStringBuilder, 3, "name", zzgl().zzbt(name));
                      name(localStringBuilder, 3, "string_value", zzamp);
                      name(localStringBuilder, 3, "int_value", zzawx);
                      name(localStringBuilder, 3, "double_value", zzauh);
                      toString(localStringBuilder, 3);
                      localStringBuilder.append("}\n");
                    }
                    k += 1;
                  }
                }
                toString(localStringBuilder, 2);
                localStringBuilder.append("}\n");
              }
              j += 1;
            }
          }
          toString(localStringBuilder, 1);
          localStringBuilder.append("}\n");
        }
        i += 1;
      }
    }
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
  
  final boolean verify(long paramLong1, long paramLong2)
  {
    if (paramLong1 != 0L)
    {
      if (paramLong2 <= 0L) {
        return true;
      }
      return Math.abs(zzbx().currentTimeMillis() - paramLong1) > paramLong2;
    }
    return true;
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
}
