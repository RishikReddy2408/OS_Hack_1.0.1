package com.google.android.android.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.aimsicd.Scope;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SafeParcelable.Class(creator="AuthAccountRequestCreator")
public class AuthAccountRequest
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<com.google.android.gms.common.internal.AuthAccountRequest> CREATOR = new AddressAndLabel.1();
  @SafeParcelable.Field(id=6, type="android.accounts.Account")
  private Account mItemType;
  @SafeParcelable.VersionField(id=1)
  private final int zale;
  @Deprecated
  @SafeParcelable.Field(id=2)
  private final IBinder zanw;
  @SafeParcelable.Field(id=3)
  private final Scope[] zanx;
  @SafeParcelable.Field(id=4)
  private Integer zany;
  @SafeParcelable.Field(id=5)
  private Integer zanz;
  
  AuthAccountRequest(int paramInt, IBinder paramIBinder, Scope[] paramArrayOfScope, Integer paramInteger1, Integer paramInteger2, Account paramAccount)
  {
    zale = paramInt;
    zanw = paramIBinder;
    zanx = paramArrayOfScope;
    zany = paramInteger1;
    zanz = paramInteger2;
    mItemType = paramAccount;
  }
  
  public AuthAccountRequest(Account paramAccount, Set paramSet)
  {
    this(3, null, (Scope[])paramSet.toArray(new Scope[paramSet.size()]), null, null, (Account)Preconditions.checkNotNull(paramAccount));
  }
  
  public AuthAccountRequest(IAccountAccessor paramIAccountAccessor, Set paramSet)
  {
    this(3, paramIAccountAccessor.asBinder(), (Scope[])paramSet.toArray(new Scope[paramSet.size()]), null, null, null);
  }
  
  public Account getAccount()
  {
    if (mItemType != null) {
      return mItemType;
    }
    if (zanw != null) {
      return AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(zanw));
    }
    return null;
  }
  
  public Integer getOauthPolicy()
  {
    return zany;
  }
  
  public Integer getPolicyAction()
  {
    return zanz;
  }
  
  public Set getScopes()
  {
    return new HashSet(Arrays.asList(zanx));
  }
  
  public AuthAccountRequest setOauthPolicy(Integer paramInteger)
  {
    zany = paramInteger;
    return this;
  }
  
  public AuthAccountRequest setPolicyAction(Integer paramInteger)
  {
    zanz = paramInteger;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, zale);
    SafeParcelWriter.writeIBinder(paramParcel, 2, zanw, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 3, zanx, paramInt, false);
    SafeParcelWriter.writeIntegerObject(paramParcel, 4, zany, false);
    SafeParcelWriter.writeIntegerObject(paramParcel, 5, zanz, false);
    SafeParcelWriter.writeParcelable(paramParcel, 6, mItemType, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}
