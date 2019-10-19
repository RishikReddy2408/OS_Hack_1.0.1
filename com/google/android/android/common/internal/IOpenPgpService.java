package com.google.android.android.common.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.android.common.Command;
import com.google.android.android.dynamic.IObjectWrapper;

public abstract interface IOpenPgpService
  extends IInterface
{
  public abstract boolean execute(Command paramCommand, IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
}
