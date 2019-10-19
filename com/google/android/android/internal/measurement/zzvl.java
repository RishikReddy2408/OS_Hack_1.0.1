package com.google.android.android.internal.measurement;

final class zzvl
  implements zzws
{
  private static final zzvl zzbyl = new zzvl();
  
  private zzvl() {}
  
  public static zzvl zzwb()
  {
    return zzbyl;
  }
  
  public final zzwr getText(Class paramClass)
  {
    if (!com.google.android.gms.internal.measurement.zzvm.class.isAssignableFrom(paramClass))
    {
      paramClass = String.valueOf(paramClass.getName());
      if (paramClass.length() != 0) {
        paramClass = "Unsupported message type: ".concat(paramClass);
      } else {
        paramClass = new String("Unsupported message type: ");
      }
      throw new IllegalArgumentException(paramClass);
    }
    try
    {
      Object localObject = zzvm.getData(paramClass.asSubclass(com.google.android.gms.internal.measurement.zzvm.class));
      int i = zzvm.zze.zzbyv;
      localObject = ((zzvm)localObject).get(i, null, null);
      return (zzwr)localObject;
    }
    catch (Exception localException)
    {
      paramClass = String.valueOf(paramClass.getName());
      if (paramClass.length() != 0) {
        paramClass = "Unable to get message info for ".concat(paramClass);
      } else {
        paramClass = new String("Unable to get message info for ");
      }
      throw new RuntimeException(paramClass, localException);
    }
  }
  
  public final boolean isAssignableFrom(Class paramClass)
  {
    return com.google.android.gms.internal.measurement.zzvm.class.isAssignableFrom(paramClass);
  }
}
