package com.google.android.android.internal.measurement;

import java.lang.reflect.Type;

public enum zzvg
{
  private static final zzvg[] zzbxx;
  private static final Type[] zzbxy;
  private final int rpos;
  private final zzvv zzbxt;
  private final zzvi zzbxu;
  private final Class<?> zzbxv;
  private final boolean zzbxw;
  
  static
  {
    Object localObject1 = zzbvu;
    int i = 0;
    zzbxz = new zzvg[] { localObject1, zzbvv, zzbvw, zzbvx, zzbvy, zzbvz, zzbwa, zzbwb, zzbwc, zzbwd, zzbwe, zzbwf, zzbwg, zzbwh, zzbwi, zzbwj, zzbwk, zzbwl, zzbwm, zzbwn, zzbwo, zzbwp, zzbwq, zzbwr, zzbws, zzbwt, zzbwu, zzbwv, zzbww, zzbwx, zzbwy, zzbwz, zzbxa, zzbxb, zzbxc, zzbxd, zzbxe, zzbxf, zzbxg, zzbxh, zzbxi, zzbxj, zzbxk, zzbxl, zzbxm, zzbxn, zzbxo, zzbxp, zzbxq, zzbxr, zzbxs };
    zzbxy = new Type[0];
    localObject1 = values();
    zzbxx = new zzvg[localObject1.length];
    int j = localObject1.length;
    while (i < j)
    {
      Object localObject2 = localObject1[i];
      zzbxx[rpos] = localObject2;
      i += 1;
    }
  }
  
  private zzvg(int paramInt, zzvi paramZzvi, zzvv paramZzvv)
  {
    rpos = paramInt;
    zzbxu = paramZzvi;
    zzbxt = paramZzvv;
    switch (zzvh.zzbya[paramZzvi.ordinal()])
    {
    default: 
      zzbxv = null;
      break;
    case 2: 
      zzbxv = paramZzvv.zzws();
      break;
    case 1: 
      zzbxv = paramZzvv.zzws();
    }
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramZzvi == zzvi.zzbyc)
    {
      bool1 = bool2;
      switch (zzvh.zzbyb[paramZzvv.ordinal()])
      {
      default: 
        bool1 = true;
      }
    }
    zzbxw = bool1;
  }
  
  public final int rpos()
  {
    return rpos;
  }
}
