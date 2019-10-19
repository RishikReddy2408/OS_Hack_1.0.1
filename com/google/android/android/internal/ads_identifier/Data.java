package com.google.android.android.internal.ads_identifier;

import android.os.IInterface;
import android.os.RemoteException;

public abstract interface Data
  extends IInterface
{
  public abstract String getId()
    throws RemoteException;
  
  public abstract boolean getValue()
    throws RemoteException;
  
  public abstract boolean getValue(boolean paramBoolean)
    throws RemoteException;
}
