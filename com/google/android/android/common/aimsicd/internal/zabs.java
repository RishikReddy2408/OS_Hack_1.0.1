package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.Sample;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public abstract interface zabs
{
  public abstract ConnectionResult blockingConnect();
  
  public abstract ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit);
  
  public abstract void connect();
  
  public abstract void disconnect();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  public abstract BaseImplementation.ApiMethodImpl enqueue(BaseImplementation.ApiMethodImpl paramApiMethodImpl);
  
  public abstract BaseImplementation.ApiMethodImpl execute(BaseImplementation.ApiMethodImpl paramApiMethodImpl);
  
  public abstract ConnectionResult getConnectionResult(Sample paramSample);
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener);
  
  public abstract void maybeSignOut();
  
  public abstract void removeAccount();
}
