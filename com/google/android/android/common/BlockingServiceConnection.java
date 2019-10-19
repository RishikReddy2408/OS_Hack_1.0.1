package com.google.android.android.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@KeepForSdk
public class BlockingServiceConnection
  implements ServiceConnection
{
  private boolean service = false;
  private final BlockingQueue<IBinder> this$0 = new LinkedBlockingQueue();
  
  public BlockingServiceConnection() {}
  
  public IBinder getService()
    throws InterruptedException
  {
    Preconditions.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
    if (!service)
    {
      service = true;
      return (IBinder)this$0.take();
    }
    throw new IllegalStateException("Cannot call get on this connection more than once");
  }
  
  public IBinder getServiceWithTimeout(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, TimeoutException
  {
    Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
    if (!service)
    {
      service = true;
      paramTimeUnit = (IBinder)this$0.poll(paramLong, paramTimeUnit);
      if (paramTimeUnit != null) {
        return paramTimeUnit;
      }
      throw new TimeoutException("Timed out waiting for the service connection");
    }
    throw new IllegalStateException("Cannot call get on this connection more than once");
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    this$0.add(paramIBinder);
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {}
}
