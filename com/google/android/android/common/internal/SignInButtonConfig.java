package com.google.android.android.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.aimsicd.Scope;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@SafeParcelable.Class(creator="SignInButtonConfigCreator")
public class SignInButtonConfig
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<com.google.android.gms.common.internal.SignInButtonConfig> CREATOR = new PaymentIntent.Output.1();
  @SafeParcelable.VersionField(id=1)
  private final int zale;
  @Deprecated
  @SafeParcelable.Field(getter="getScopes", id=4)
  private final Scope[] zanx;
  @SafeParcelable.Field(getter="getButtonSize", id=2)
  private final int zapc;
  @SafeParcelable.Field(getter="getColorScheme", id=3)
  private final int zapd;
  
  SignInButtonConfig(int paramInt1, int paramInt2, int paramInt3, Scope[] paramArrayOfScope)
  {
    zale = paramInt1;
    zapc = paramInt2;
    zapd = paramInt3;
    zanx = paramArrayOfScope;
  }
  
  public SignInButtonConfig(int paramInt1, int paramInt2, Scope[] paramArrayOfScope)
  {
    this(1, paramInt1, paramInt2, null);
  }
  
  public int getButtonSize()
  {
    return zapc;
  }
  
  public int getColorScheme()
  {
    return zapd;
  }
  
  public Scope[] getScopes()
  {
    return zanx;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, zale);
    SafeParcelWriter.writeInt(paramParcel, 2, getButtonSize());
    SafeParcelWriter.writeInt(paramParcel, 3, getColorScheme());
    SafeParcelWriter.writeTypedArray(paramParcel, 4, getScopes(), paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}
