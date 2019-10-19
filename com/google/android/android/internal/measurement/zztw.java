package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zztx;
import java.io.IOException;

public abstract class zztw<MessageType extends com.google.android.gms.internal.measurement.zztw<MessageType, BuilderType>, BuilderType extends zztx<MessageType, BuilderType>>
  implements com.google.android.gms.internal.measurement.zzwt
{
  private static boolean zzbts;
  protected int zzbtr = 0;
  
  public zztw() {}
  
  void zzah(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public final zzud zztt()
  {
    try
    {
      Object localObject = zzud.zzam(zzvu());
      writeValue(((zzuk)localObject).zzuf());
      localObject = ((zzuk)localObject).zzue();
      return localObject;
    }
    catch (IOException localIOException)
    {
      String str = getClass().getName();
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 62 + String.valueOf("ByteString").length());
      localStringBuilder.append("Serializing ");
      localStringBuilder.append(str);
      localStringBuilder.append(" to a ");
      localStringBuilder.append("ByteString");
      localStringBuilder.append(" threw an IOException (should never happen).");
      throw new RuntimeException(localStringBuilder.toString(), localIOException);
    }
  }
  
  int zztu()
  {
    throw new UnsupportedOperationException();
  }
}
