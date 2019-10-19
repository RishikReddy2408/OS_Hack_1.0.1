package com.google.android.android.common.internal.service;

import android.os.IInterface;
import android.os.RemoteException;

public abstract interface Cursor
  extends IInterface
{
  public abstract void read(int paramInt)
    throws RemoteException;
}
