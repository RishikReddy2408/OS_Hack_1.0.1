package com.google.android.android.internal.ads_identifier;

import android.os.Parcel;
import com.google.android.gms.internal.ads_identifier.zzc;

public class Storage
{
  private static final ClassLoader thisClassLoader = zzc.class.getClassLoader();
  
  private Storage() {}
  
  public static void writeString(Parcel paramParcel, boolean paramBoolean)
  {
    paramParcel.writeInt(1);
  }
  
  public static boolean writeString(Parcel paramParcel)
  {
    return paramParcel.readInt() != 0;
  }
}
