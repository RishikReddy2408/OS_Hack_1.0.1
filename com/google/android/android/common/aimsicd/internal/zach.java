package com.google.android.android.common.aimsicd.internal;

import android.support.annotation.WorkerThread;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.internal.IAccountAccessor;
import java.util.Set;

@WorkerThread
public abstract interface zach
{
  public abstract void ignore(ConnectionResult paramConnectionResult);
  
  public abstract void startSession(IAccountAccessor paramIAccountAccessor, Set paramSet);
}
