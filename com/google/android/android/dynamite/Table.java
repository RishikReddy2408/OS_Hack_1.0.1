package com.google.android.android.dynamite;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.android.dynamic.IObjectWrapper;

public abstract interface Table
  extends IInterface
{
  public abstract int get(IObjectWrapper paramIObjectWrapper, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract IObjectWrapper get(IObjectWrapper paramIObjectWrapper, String paramString, int paramInt)
    throws RemoteException;
  
  public abstract int register(IObjectWrapper paramIObjectWrapper, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract IObjectWrapper set(IObjectWrapper paramIObjectWrapper, String paramString, int paramInt)
    throws RemoteException;
  
  public abstract int zzaj()
    throws RemoteException;
}
