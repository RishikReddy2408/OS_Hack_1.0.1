package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zztx;
import com.google.android.gms.internal.measurement.zzty;
import com.google.android.gms.internal.measurement.zzux;
import com.google.android.gms.internal.measurement.zzwv;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzvm<MessageType extends com.google.android.gms.internal.measurement.zzvm<MessageType, BuilderType>, BuilderType extends com.google.android.gms.internal.measurement.zzvm.zza<MessageType, BuilderType>>
  extends com.google.android.gms.internal.measurement.zztw<MessageType, BuilderType>
{
  private static Map<Object, com.google.android.gms.internal.measurement.zzvm<?, ?>> zzbyo = new ConcurrentHashMap();
  protected zzyc zzbym = zzyc.zzyf();
  private int zzbyn = -1;
  
  public zzvm() {}
  
  protected static void cancelRequests(Class paramClass, zzvm paramZzvm)
  {
    zzbyo.put(paramClass, paramZzvm);
  }
  
  static Object get(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
    try
    {
      paramMethod = paramMethod.invoke(paramObject, paramVarArgs);
      return paramMethod;
    }
    catch (InvocationTargetException paramMethod)
    {
      paramMethod = paramMethod.getCause();
      if (!(paramMethod instanceof RuntimeException))
      {
        if ((paramMethod instanceof Error)) {
          throw ((Error)paramMethod);
        }
        throw new RuntimeException("Unexpected exception thrown by generated accessor method.", paramMethod);
      }
      throw ((RuntimeException)paramMethod);
    }
    catch (IllegalAccessException paramMethod)
    {
      throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", paramMethod);
    }
  }
  
  static zzvm getData(Class paramClass)
  {
    zzvm localZzvm2 = (zzvm)zzbyo.get(paramClass);
    zzvm localZzvm1 = localZzvm2;
    if (localZzvm2 == null) {
      try
      {
        Class.forName(paramClass.getName(), true, paramClass.getClassLoader());
        localZzvm1 = (zzvm)zzbyo.get(paramClass);
      }
      catch (ClassNotFoundException paramClass)
      {
        throw new IllegalStateException("Class initialization cannot fail.", paramClass);
      }
    }
    if (localZzvm1 == null)
    {
      paramClass = String.valueOf(paramClass.getName());
      if (paramClass.length() != 0) {
        paramClass = "Unable to get default instance for: ".concat(paramClass);
      } else {
        paramClass = new String("Unable to get default instance for: ");
      }
      throw new IllegalStateException(paramClass);
    }
    return localZzvm1;
  }
  
  protected static Object safeGet(zzwt paramZzwt, String paramString, Object[] paramArrayOfObject)
  {
    return new zzxh(paramZzwt, paramString, paramArrayOfObject);
  }
  
  static zzvm subtract(zzvm paramZzvm, zzuo paramZzuo, zzuz paramZzuz)
    throws zzvt
  {
    paramZzvm = (zzvm)paramZzvm.get(zze.zzbyw, null, null);
    try
    {
      zzxf.zzxn().zzag(paramZzvm).toFile(paramZzvm, zzur.subtract(paramZzuo), paramZzuz);
      zzxf.zzxn().zzag(paramZzvm).multiply(paramZzvm);
      return paramZzvm;
    }
    catch (RuntimeException paramZzvm)
    {
      if ((paramZzvm.getCause() instanceof zzvt)) {
        throw ((zzvt)paramZzvm.getCause());
      }
      throw paramZzvm;
    }
    catch (IOException paramZzuo)
    {
      if ((paramZzuo.getCause() instanceof zzvt)) {
        throw ((zzvt)paramZzuo.getCause());
      }
      throw new zzvt(paramZzuo.getMessage()).xor(paramZzvm);
    }
  }
  
  protected static final boolean writeValue(zzvm paramZzvm, boolean paramBoolean)
  {
    int i = ((Byte)paramZzvm.get(zze.zzbyt, null, null)).byteValue();
    if (i == 1) {
      return true;
    }
    if (i == 0) {
      return false;
    }
    return zzxf.zzxn().zzag(paramZzvm).zzaf(paramZzvm);
  }
  
  protected static zzvs zzwc()
  {
    return zzxg.zzxo();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!((zzvm)get(zze.zzbyy, null, null)).getClass().isInstance(paramObject)) {
      return false;
    }
    return zzxf.zzxn().zzag(this).equals(this, (zzvm)paramObject);
  }
  
  protected abstract Object get(int paramInt, Object paramObject1, Object paramObject2);
  
  public int hashCode()
  {
    if (zzbtr != 0) {
      return zzbtr;
    }
    zzbtr = zzxf.zzxn().zzag(this).hashCode(this);
    return zzbtr;
  }
  
  public final boolean isInitialized()
  {
    boolean bool1 = Boolean.TRUE.booleanValue();
    int i = ((Byte)get(zze.zzbyt, null, null)).byteValue();
    if (i == 1) {
      return true;
    }
    if (i == 0) {
      return false;
    }
    boolean bool2 = zzxf.zzxn().zzag(this).zzaf(this);
    if (bool1)
    {
      i = zze.zzbyu;
      zzvm localZzvm;
      if (bool2) {
        localZzvm = this;
      } else {
        localZzvm = null;
      }
      get(i, localZzvm, null);
    }
    return bool2;
  }
  
  public String toString()
  {
    return zzww.safeString(this, super.toString());
  }
  
  public final void writeValue(zzut paramZzut)
    throws IOException
  {
    zzxf.zzxn().getAttributeValue(getClass()).a(this, zzuv.elementAt(paramZzut));
  }
  
  final void zzah(int paramInt)
  {
    zzbyn = paramInt;
  }
  
  final int zztu()
  {
    return zzbyn;
  }
  
  public final int zzvu()
  {
    if (zzbyn == -1) {
      zzbyn = zzxf.zzxn().zzag(this).zzae(this);
    }
    return zzbyn;
  }
  
  public class zza<MessageType extends com.google.android.gms.internal.measurement.zzvm<MessageType, BuilderType>, BuilderType extends com.google.android.gms.internal.measurement.zzvm.zza<MessageType, BuilderType>>
    extends zztx<MessageType, BuilderType>
  {
    private MessageType zzbyq = (zzvm)get(zzvm.zze.zzbyw, null, null);
    private boolean zzbyr = false;
    
    protected zza() {}
    
    private static void visitFieldInsn(zzvm paramZzvm1, zzvm paramZzvm2)
    {
      zzxf.zzxn().zzag(paramZzvm1).a(paramZzvm1, paramZzvm2);
    }
    
    public final boolean isInitialized()
    {
      return zzvm.writeValue(zzbyq, false);
    }
    
    public final zza multiply(zzvm paramZzvm)
    {
      if (zzbyr)
      {
        zzvm localZzvm = (zzvm)zzbyq.get(zzvm.zze.zzbyw, null, null);
        visitFieldInsn(localZzvm, zzbyq);
        zzbyq = localZzvm;
        zzbyr = false;
      }
      visitFieldInsn(zzbyq, paramZzvm);
      return this;
    }
    
    public zzvm zzwg()
    {
      if (zzbyr) {
        return zzbyq;
      }
      zzvm localZzvm = zzbyq;
      zzxf.zzxn().zzag(localZzvm).multiply(localZzvm);
      zzbyr = true;
      return zzbyq;
    }
    
    public final zzvm zzwh()
    {
      zzvm localZzvm2 = (zzvm)zzwi();
      boolean bool3 = Boolean.TRUE.booleanValue();
      int i = ((Byte)localZzvm2.get(zzvm.zze.zzbyt, null, null)).byteValue();
      int j = 1;
      if (i != 1) {
        if (i == 0)
        {
          j = 0;
        }
        else
        {
          boolean bool2 = zzxf.zzxn().zzag(localZzvm2).zzaf(localZzvm2);
          boolean bool1 = bool2;
          j = bool1;
          if (bool3)
          {
            i = zzvm.zze.zzbyu;
            zzvm localZzvm1;
            if (bool2) {
              localZzvm1 = localZzvm2;
            } else {
              localZzvm1 = null;
            }
            localZzvm2.get(i, localZzvm1, null);
            j = bool1;
          }
        }
      }
      if (j != 0) {
        return localZzvm2;
      }
      throw new zzya(localZzvm2);
    }
  }
  
  public final class zzb<T extends com.google.android.gms.internal.measurement.zzvm<T, ?>>
    extends zzty<T>
  {
    public zzb() {}
  }
  
  public abstract class zzc<MessageType extends com.google.android.gms.internal.measurement.zzvm.zzc<MessageType, BuilderType>, BuilderType>
    extends com.google.android.gms.internal.measurement.zzvm<MessageType, BuilderType>
    implements zzwv
  {
    protected com.google.android.gms.internal.measurement.zzvd<Object> zzbys = zzvd.zzvt();
    
    public zzc() {}
  }
  
  public final class zzd<ContainingType extends com.google.android.gms.internal.measurement.zzwt, Type>
    extends zzux<ContainingType, Type>
  {}
  
  public final class zze
  {
    public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0()
    {
      return (int[])zzbza.clone();
    }
  }
}
