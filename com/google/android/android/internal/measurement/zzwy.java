package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zzxj;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzwy<T>
  implements zzxj<T>
{
  private final zzwt zzcbd;
  private final boolean zzcbe;
  private final com.google.android.gms.internal.measurement.zzyb<?, ?> zzcbn;
  private final com.google.android.gms.internal.measurement.zzva<?> zzcbo;
  
  private zzwy(zzyb paramZzyb, zzva paramZzva, zzwt paramZzwt)
  {
    zzcbn = paramZzyb;
    zzcbe = paramZzva.accepts(paramZzwt);
    zzcbo = paramZzva;
    zzcbd = paramZzwt;
  }
  
  static zzwy cast(zzyb paramZzyb, zzva paramZzva, zzwt paramZzwt)
  {
    return new zzwy(paramZzyb, paramZzva, paramZzwt);
  }
  
  public final void a(Object paramObject, zzyw paramZzyw)
    throws IOException
  {
    Object localObject = zzcbo.getName(paramObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      zzvf localZzvf = (zzvf)localEntry.getKey();
      if ((localZzvf.zzvx() == zzyv.zzcet) && (!localZzvf.zzvy()) && (!localZzvf.zzvz()))
      {
        if ((localEntry instanceof zzvy)) {
          paramZzyw.a(localZzvf.getValue(), ((zzvy)localEntry).zzwu().zztt());
        } else {
          paramZzyw.a(localZzvf.getValue(), localEntry.getValue());
        }
      }
      else {
        throw new IllegalStateException("Found invalid MessageSet item.");
      }
    }
    localObject = zzcbn;
    ((zzyb)localObject).remainder(((zzyb)localObject).zzah(paramObject), paramZzyw);
  }
  
  public final void a(Object paramObject1, Object paramObject2)
  {
    zzxl.div(zzcbn, paramObject1, paramObject2);
    if (zzcbe) {
      zzxl.setTitle(zzcbo, paramObject1, paramObject2);
    }
  }
  
  public final boolean equals(Object paramObject1, Object paramObject2)
  {
    if (!zzcbn.zzah(paramObject1).equals(zzcbn.zzah(paramObject2))) {
      return false;
    }
    if (zzcbe) {
      return zzcbo.getName(paramObject1).equals(zzcbo.getName(paramObject2));
    }
    return true;
  }
  
  public final int hashCode(Object paramObject)
  {
    int j = zzcbn.zzah(paramObject).hashCode();
    int i = j;
    if (zzcbe) {
      i = j * 53 + zzcbo.getName(paramObject).hashCode();
    }
    return i;
  }
  
  public final void multiply(Object paramObject)
  {
    zzcbn.setEntry(paramObject);
    zzcbo.setEntry(paramObject);
  }
  
  public final Object newInstance()
  {
    return zzcbd.zzwe().zzwi();
  }
  
  public final void toFile(Object paramObject, zzxi paramZzxi, zzuz paramZzuz)
    throws IOException
  {
    zzyb localZzyb = zzcbn;
    zzva localZzva = zzcbo;
    Object localObject3 = localZzyb.zzai(paramObject);
    zzvd localZzvd = localZzva.get(paramObject);
    try
    {
      boolean bool;
      label257:
      do
      {
        int i = paramZzxi.zzve();
        if (i == Integer.MAX_VALUE)
        {
          localZzyb.operate(paramObject, localObject3);
          return;
        }
        i = paramZzxi.getTag();
        Object localObject1;
        if (i != 11)
        {
          if ((i & 0x7) == 2)
          {
            localObject1 = zzcbd;
            localObject1 = localZzva.read(paramZzuz, (zzwt)localObject1, i >>> 3);
            if (localObject1 != null)
            {
              localZzva.read(paramZzxi, localObject1, paramZzuz, localZzvd);
            }
            else
            {
              bool = localZzyb.next(localObject3, paramZzxi);
              continue;
            }
          }
          else
          {
            bool = paramZzxi.zzvf();
            continue;
          }
        }
        else
        {
          Object localObject2 = null;
          localObject1 = null;
          i = 0;
          do
          {
            for (;;)
            {
              j = paramZzxi.zzve();
              if (j == Integer.MAX_VALUE) {
                break label257;
              }
              j = paramZzxi.getTag();
              if (j == 16)
              {
                j = paramZzxi.zzup();
                i = j;
                localObject2 = localZzva.read(paramZzuz, zzcbd, j);
              }
              else
              {
                if (j != 26) {
                  break;
                }
                if (localObject2 != null) {
                  localZzva.read(paramZzxi, localObject2, paramZzuz, localZzvd);
                } else {
                  localObject1 = paramZzxi.zzuo();
                }
              }
            }
            bool = paramZzxi.zzvf();
          } while (bool);
          int j = paramZzxi.getTag();
          if (j != 12) {
            break;
          }
          if (localObject1 != null) {
            if (localObject2 != null) {
              localZzva.read((zzud)localObject1, localObject2, paramZzuz, localZzvd);
            } else {
              localZzyb.read(localObject3, i, (zzud)localObject1);
            }
          }
        }
        bool = true;
      } while (bool);
      localZzyb.operate(paramObject, localObject3);
      return;
      throw zzvt.zzwn();
    }
    catch (Throwable paramZzxi)
    {
      localZzyb.operate(paramObject, localObject3);
      throw paramZzxi;
    }
  }
  
  public final int zzae(Object paramObject)
  {
    zzyb localZzyb = zzcbn;
    int j = localZzyb.zzaj(localZzyb.zzah(paramObject)) + 0;
    int i = j;
    if (zzcbe) {
      i = j + zzcbo.getName(paramObject).zzvv();
    }
    return i;
  }
  
  public final boolean zzaf(Object paramObject)
  {
    return zzcbo.getName(paramObject).isInitialized();
  }
}
