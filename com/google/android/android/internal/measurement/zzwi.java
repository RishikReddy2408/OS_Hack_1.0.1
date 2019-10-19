package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;
import java.lang.reflect.Method;

final class zzwi
  implements zzxk
{
  private static final zzws zzcap = new zzwj();
  private final zzws zzcao;
  
  public zzwi()
  {
    this(new zzwk(new zzws[] { zzvl.zzwb(), zzwz() }));
  }
  
  private zzwi(zzws paramZzws)
  {
    zzcao = ((zzws)zzvo.attribute(paramZzws, "messageInfoFactory"));
  }
  
  private static boolean accepts(zzwr paramZzwr)
  {
    return paramZzwr.zzxg() == zzvm.zze.zzbzb;
  }
  
  private static zzws zzwz()
  {
    try
    {
      Object localObject = Class.forName("com.google.protobuf.DescriptorMessageInfoFactory");
      localObject = ((Class)localObject).getDeclaredMethod("getInstance", new Class[0]);
      localObject = ((Method)localObject).invoke(null, new Object[0]);
      return (zzws)localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return zzcap;
  }
  
  public final zzxj getAttributeValue(Class paramClass)
  {
    zzxl.setObjectClass(paramClass);
    zzwr localZzwr = zzcao.getText(paramClass);
    if (localZzwr.zzxh())
    {
      if (zzvm.class.isAssignableFrom(paramClass)) {
        return zzwy.cast(zzxl.zzxt(), zzvc.zzvr(), localZzwr.zzxi());
      }
      return zzwy.cast(zzxl.zzxr(), zzvc.zzvs(), localZzwr.zzxi());
    }
    if (zzvm.class.isAssignableFrom(paramClass))
    {
      if (accepts(localZzwr)) {
        return zzwx.decode(paramClass, localZzwr, zzxc.zzxl(), zzwd.zzwy(), zzxl.zzxt(), zzvc.zzvr(), zzwq.zzxe());
      }
      return zzwx.decode(paramClass, localZzwr, zzxc.zzxl(), zzwd.zzwy(), zzxl.zzxt(), null, zzwq.zzxe());
    }
    if (accepts(localZzwr)) {
      return zzwx.decode(paramClass, localZzwr, zzxc.zzxk(), zzwd.zzwx(), zzxl.zzxr(), zzvc.zzvs(), zzwq.zzxd());
    }
    return zzwx.decode(paramClass, localZzwr, zzxc.zzxk(), zzwd.zzwx(), zzxl.zzxs(), null, zzwq.zzxd());
  }
}
