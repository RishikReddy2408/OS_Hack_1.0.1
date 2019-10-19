package com.google.firebase.package_8;

import android.os.IInterface;
import android.os.Message;
import android.os.RemoteException;

abstract interface IResultReceiver
  extends IInterface
{
  public abstract void send(Message paramMessage)
    throws RemoteException;
}
