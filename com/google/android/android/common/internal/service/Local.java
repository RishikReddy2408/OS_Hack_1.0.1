package com.google.android.android.common.internal.service;

import android.os.RemoteException;

final class Local
  extends AbsTransport
{
  private final com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder<com.google.android.gms.common.api.Status> mResultHolder;
  
  public Local(com.google.android.android.common.aimsicd.internal.BaseImplementation.ResultHolder paramResultHolder)
  {
    mResultHolder = paramResultHolder;
  }
  
  public final void read(int paramInt)
    throws RemoteException
  {
    mResultHolder.setResult(new com.google.android.android.common.aimsicd.Status(paramInt));
  }
}
