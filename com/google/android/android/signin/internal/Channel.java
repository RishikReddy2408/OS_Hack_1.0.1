package com.google.android.android.signin.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.android.common.internal.IAccountAccessor;

public abstract interface Channel
  extends IInterface
{
  public abstract void write(int paramInt)
    throws RemoteException;
  
  public abstract void write(IAccountAccessor paramIAccountAccessor, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void write(OperationResult paramOperationResult, Session paramSession)
    throws RemoteException;
}
