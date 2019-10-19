package com.google.android.android.common.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.android.dynamic.IObjectWrapper;

public abstract interface Property
  extends IInterface
{
  public abstract IObjectWrapper get()
    throws RemoteException;
  
  public abstract int getType()
    throws RemoteException;
}
