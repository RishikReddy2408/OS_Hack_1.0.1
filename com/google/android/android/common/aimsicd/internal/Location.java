package com.google.android.android.common.aimsicd.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.util.PlatformVersion;

public abstract class Location
{
  private final int type;
  
  public Location(int paramInt)
  {
    type = paramInt;
  }
  
  private static Status parse(RemoteException paramRemoteException)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((PlatformVersion.isAtLeastIceCreamSandwichMR1()) && ((paramRemoteException instanceof TransactionTooLargeException))) {
      localStringBuilder.append("TransactionTooLargeException: ");
    }
    localStringBuilder.append(paramRemoteException.getLocalizedMessage());
    return new Status(8, localStringBuilder.toString());
  }
  
  public abstract void readFrom(GoogleApiManager.zaa paramZaa)
    throws DeadObjectException;
  
  public abstract void readFrom(zaab paramZaab, boolean paramBoolean);
  
  public abstract void toString(Status paramStatus);
  
  public abstract void toString(RuntimeException paramRuntimeException);
}
