package com.google.android.android.common.internal.service;

import android.os.IInterface;
import android.os.RemoteException;

public abstract interface LocationBackend
  extends IInterface
{
  public abstract void close(Cursor paramCursor)
    throws RemoteException;
}
