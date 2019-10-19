package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzzd
  implements Cloneable
{
  private Object value;
  private com.google.android.gms.internal.measurement.zzzb<?, ?> zzcfj;
  private List<com.google.android.gms.internal.measurement.zzzi> zzcfk = new ArrayList();
  
  zzzd() {}
  
  private final byte[] toByteArray()
    throws IOException
  {
    byte[] arrayOfByte = new byte[multiply()];
    multiply(zzyy.readFully(arrayOfByte));
    return arrayOfByte;
  }
  
  private final zzzd zzyv()
  {
    zzzd localZzzd = new zzzd();
    zzcfj = zzcfj;
    Object localObject1;
    Object localObject2;
    if (zzcfk == null)
    {
      zzcfk = null;
    }
    else
    {
      localObject1 = zzcfk;
      localObject2 = zzcfk;
    }
    try
    {
      ((List)localObject1).addAll((Collection)localObject2);
      if (value != null)
      {
        if ((value instanceof zzzg))
        {
          localObject1 = (zzzg)value;
          localObject1 = ((zzzg)localObject1).clone();
          value = ((zzzg)localObject1);
          return localZzzd;
        }
        if ((value instanceof byte[]))
        {
          localObject1 = (byte[])value;
          localObject1 = localObject1.clone();
          value = localObject1;
          return localZzzd;
        }
        boolean bool = value instanceof byte[][];
        int j = 0;
        int i = 0;
        Object localObject3;
        if (bool)
        {
          localObject1 = (byte[][])value;
          localObject2 = new byte[localObject1.length][];
          value = localObject2;
          while (i < localObject1.length)
          {
            localObject3 = localObject1[i];
            localObject3 = localObject3.clone();
            localObject2[i] = ((byte[])localObject3);
            i += 1;
          }
        }
        if ((value instanceof boolean[]))
        {
          localObject1 = (boolean[])value;
          localObject1 = localObject1.clone();
          value = localObject1;
          return localZzzd;
        }
        if ((value instanceof int[]))
        {
          localObject1 = (int[])value;
          localObject1 = localObject1.clone();
          value = localObject1;
          return localZzzd;
        }
        if ((value instanceof long[]))
        {
          localObject1 = (long[])value;
          localObject1 = localObject1.clone();
          value = localObject1;
          return localZzzd;
        }
        if ((value instanceof float[]))
        {
          localObject1 = (float[])value;
          localObject1 = localObject1.clone();
          value = localObject1;
          return localZzzd;
        }
        if ((value instanceof double[]))
        {
          localObject1 = (double[])value;
          localObject1 = localObject1.clone();
          value = localObject1;
          return localZzzd;
        }
        if ((value instanceof zzzg[]))
        {
          localObject1 = (zzzg[])value;
          localObject2 = new zzzg[localObject1.length];
          value = localObject2;
          i = j;
          while (i < localObject1.length)
          {
            localObject3 = localObject1[i];
            localObject3 = localObject3.clone();
            localObject2[i] = ((zzzg)localObject3);
            i += 1;
          }
        }
      }
      else
      {
        return localZzzd;
      }
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
    return localCloneNotSupportedException;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzzd)) {
      return false;
    }
    paramObject = (zzzd)paramObject;
    if ((value != null) && (value != null))
    {
      if (zzcfj != zzcfj) {
        return false;
      }
      if (!zzcfj.zzcfd.isArray()) {
        return value.equals(value);
      }
      if ((value instanceof byte[])) {
        return Arrays.equals((byte[])value, (byte[])value);
      }
      if ((value instanceof int[])) {
        return Arrays.equals((int[])value, (int[])value);
      }
      if ((value instanceof long[])) {
        return Arrays.equals((long[])value, (long[])value);
      }
      if ((value instanceof float[])) {
        return Arrays.equals((float[])value, (float[])value);
      }
      if ((value instanceof double[])) {
        return Arrays.equals((double[])value, (double[])value);
      }
      if ((value instanceof boolean[])) {
        return Arrays.equals((boolean[])value, (boolean[])value);
      }
      return Arrays.deepEquals((Object[])value, (Object[])value);
    }
    if ((zzcfk != null) && (zzcfk != null)) {
      return zzcfk.equals(zzcfk);
    }
    try
    {
      boolean bool = Arrays.equals(toByteArray(), paramObject.toByteArray());
      return bool;
    }
    catch (IOException paramObject)
    {
      throw new IllegalStateException(paramObject);
    }
  }
  
  public final int hashCode()
  {
    try
    {
      int i = Arrays.hashCode(toByteArray());
      return i + 527;
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }
  
  final int multiply()
  {
    Object localObject1 = value;
    int i = 0;
    Object localObject2;
    int j;
    if (localObject1 != null)
    {
      localObject1 = zzcfj;
      localObject2 = value;
      if (zzcfe)
      {
        int m = Array.getLength(localObject2);
        int k;
        for (j = 0; i < m; j = k)
        {
          k = j;
          if (Array.get(localObject2, i) != null) {
            k = j + ((zzzb)localObject1).zzak(Array.get(localObject2, i));
          }
          i += 1;
        }
      }
      return ((zzzb)localObject1).zzak(localObject2);
    }
    localObject1 = zzcfk.iterator();
    i = 0;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (zzzi)((Iterator)localObject1).next();
      i += zzyy.zzbj(columns) + 0 + zzbug.length;
    }
    return j;
    return i;
  }
  
  final void multiply(zzyy paramZzyy)
    throws IOException
  {
    Object localObject2;
    if (value != null)
    {
      localObject1 = zzcfj;
      localObject2 = value;
      if (zzcfe)
      {
        int j = Array.getLength(localObject2);
        int i = 0;
        while (i < j)
        {
          Object localObject3 = Array.get(localObject2, i);
          if (localObject3 != null) {
            ((zzzb)localObject1).add(localObject3, paramZzyy);
          }
          i += 1;
        }
        return;
      }
      ((zzzb)localObject1).add(localObject2, paramZzyy);
      return;
    }
    Object localObject1 = zzcfk.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (zzzi)((Iterator)localObject1).next();
      paramZzyy.zzca(columns);
      paramZzyy.add(zzbug);
    }
  }
  
  final Object readLong(zzzb paramZzzb)
  {
    if (value != null)
    {
      if (!zzcfj.equals(paramZzzb)) {
        throw new IllegalStateException("Tried to getExtension with a different Extension.");
      }
    }
    else
    {
      zzcfj = paramZzzb;
      value = paramZzzb.zzah(zzcfk);
      zzcfk = null;
    }
    return value;
  }
  
  final void sign(zzzi paramZzzi)
    throws IOException
  {
    if (zzcfk != null)
    {
      zzcfk.add(paramZzzi);
      return;
    }
    Object localObject;
    if ((value instanceof zzzg))
    {
      paramZzzi = zzbug;
      localObject = zzyx.get(paramZzzi, 0, paramZzzi.length);
      int i = ((zzyx)localObject).zzuy();
      if (i == paramZzzi.length - zzyy.zzbc(i)) {
        paramZzzi = ((zzzg)value).digest((zzyx)localObject);
      } else {
        throw zzzf.zzyw();
      }
    }
    else if ((value instanceof zzzg[]))
    {
      localObject = (zzzg[])zzcfj.zzah(Collections.singletonList(paramZzzi));
      zzzg[] arrayOfZzzg = (zzzg[])value;
      paramZzzi = (zzzg[])Arrays.copyOf(arrayOfZzzg, arrayOfZzzg.length + localObject.length);
      System.arraycopy(localObject, 0, paramZzzi, arrayOfZzzg.length, localObject.length);
    }
    else
    {
      paramZzzi = zzcfj.zzah(Collections.singletonList(paramZzzi));
    }
    zzcfj = zzcfj;
    value = paramZzzi;
    zzcfk = null;
  }
}
