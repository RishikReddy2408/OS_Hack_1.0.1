package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zzzg;
import java.io.IOException;

public abstract class zzza<M extends com.google.android.gms.internal.measurement.zzza<M>>
  extends zzzg
{
  protected zzzc zzcfc;
  
  public zzza() {}
  
  protected int multiply()
  {
    zzzc localZzzc = zzcfc;
    int i = 0;
    int j;
    if (localZzzc != null)
    {
      j = 0;
      while (i < zzcfc.size())
      {
        j += zzcfc.zzcc(i).multiply();
        i += 1;
      }
    }
    return 0;
    return j;
  }
  
  public void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzcfc == null) {
      return;
    }
    int i = 0;
    while (i < zzcfc.size())
    {
      zzcfc.zzcc(i).multiply(paramZzyy);
      i += 1;
    }
  }
  
  protected final boolean read(zzyx paramZzyx, int paramInt)
    throws IOException
  {
    int i = paramZzyx.getPosition();
    if (!paramZzyx.zzao(paramInt)) {
      return false;
    }
    int j = paramInt >>> 3;
    zzzi localZzzi = new zzzi(paramInt, paramZzyx.add(i, paramZzyx.getPosition() - i));
    paramZzyx = null;
    if (zzcfc == null) {
      zzcfc = new zzzc();
    } else {
      paramZzyx = zzcfc.zzcb(j);
    }
    Object localObject = paramZzyx;
    if (paramZzyx == null)
    {
      localObject = new zzzd();
      zzcfc.ensureCapacity(j, (zzzd)localObject);
    }
    ((zzzd)localObject).sign(localZzzi);
    return true;
  }
  
  public final Object readLong(zzzb paramZzzb)
  {
    if (zzcfc == null) {
      return null;
    }
    zzzd localZzzd = zzcfc.zzcb(size >>> 3);
    if (localZzzd == null) {
      return null;
    }
    return localZzzd.readLong(paramZzzb);
  }
}
