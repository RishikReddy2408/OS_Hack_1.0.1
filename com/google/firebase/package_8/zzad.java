package com.google.firebase.package_8;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.stats.ConnectionTracker;
import com.google.android.android.internal.firebase_messaging.MainActivity.1;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

final class zzad
  implements ServiceConnection
{
  @GuardedBy("this")
  int state = 0;
  final Messenger zzbx = new Messenger(new MainActivity.1(Looper.getMainLooper(), new zzae(this)));
  zzai zzby;
  @GuardedBy("this")
  final Queue<com.google.firebase.iid.zzak<?>> zzbz = new ArrayDeque();
  @GuardedBy("this")
  final SparseArray<com.google.firebase.iid.zzak<?>> zzca = new SparseArray();
  
  private zzad(zzab paramZzab) {}
  
  private final void startTimer()
  {
    zzab.get(zzcb).execute(new zzag(this));
  }
  
  final void close()
  {
    try
    {
      if ((state == 2) && (zzbz.isEmpty()) && (zzca.size() == 0))
      {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
          Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
        }
        state = 3;
        ConnectionTracker.getInstance().unbindService(zzab.obtain(zzcb), this);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  final void close(int paramInt, String paramString)
  {
    for (;;)
    {
      try
      {
        Object localObject;
        if (Log.isLoggable("MessengerIpcClient", 3))
        {
          localObject = String.valueOf(paramString);
          if (((String)localObject).length() != 0) {
            localObject = "Disconnected: ".concat((String)localObject);
          } else {
            localObject = new String("Disconnected: ");
          }
          Log.d("MessengerIpcClient", (String)localObject);
        }
        switch (state)
        {
        case 4: 
          continue;
          return;
        case 3: 
          state = 4;
          return;
        case 1: 
        case 2: 
          if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Unbinding service");
          }
          state = 4;
          ConnectionTracker.getInstance().unbindService(zzab.obtain(zzcb), this);
          paramString = new zzal(paramInt, paramString);
          localObject = zzbz.iterator();
          if (((Iterator)localObject).hasNext())
          {
            ((zzak)((Iterator)localObject).next()).write(paramString);
          }
          else
          {
            zzbz.clear();
            paramInt = 0;
            if (paramInt < zzca.size())
            {
              ((zzak)zzca.valueAt(paramInt)).write(paramString);
              paramInt += 1;
            }
            else
            {
              zzca.clear();
              return;
            }
          }
          break;
        case 0: 
          throw new IllegalStateException();
          paramInt = state;
          paramString = new StringBuilder(26);
          paramString.append("Unknown state: ");
          paramString.append(paramInt);
          throw new IllegalStateException(paramString.toString());
        }
      }
      catch (Throwable paramString)
      {
        throw paramString;
      }
    }
  }
  
  final boolean execute(Message paramMessage)
  {
    int i = arg1;
    Object localObject;
    if (Log.isLoggable("MessengerIpcClient", 3))
    {
      localObject = new StringBuilder(41);
      ((StringBuilder)localObject).append("Received response to request: ");
      ((StringBuilder)localObject).append(i);
      Log.d("MessengerIpcClient", ((StringBuilder)localObject).toString());
    }
    try
    {
      localObject = (zzak)zzca.get(i);
      if (localObject == null)
      {
        paramMessage = new StringBuilder(50);
        paramMessage.append("Received response for unknown request: ");
        paramMessage.append(i);
        Log.w("MessengerIpcClient", paramMessage.toString());
        return true;
      }
      zzca.remove(i);
      close();
      paramMessage = paramMessage.getData();
      if (paramMessage.getBoolean("unsupported", false))
      {
        ((zzak)localObject).write(new zzal(4, "Not supported by GmsCore"));
        return true;
      }
      ((zzak)localObject).update(paramMessage);
      return true;
    }
    catch (Throwable paramMessage)
    {
      throw paramMessage;
    }
  }
  
  final void getImage(int paramInt)
  {
    try
    {
      zzak localZzak = (zzak)zzca.get(paramInt);
      if (localZzak != null)
      {
        StringBuilder localStringBuilder = new StringBuilder(31);
        localStringBuilder.append("Timing out request: ");
        localStringBuilder.append(paramInt);
        Log.w("MessengerIpcClient", localStringBuilder.toString());
        zzca.remove(paramInt);
        localZzak.write(new zzal(3, "Timed out waiting for response"));
        close();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  /* Error */
  public final void onServiceConnected(ComponentName paramComponentName, android.os.IBinder paramIBinder)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 100
    //   4: iconst_2
    //   5: invokestatic 106	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   8: ifeq +11 -> 19
    //   11: ldc 100
    //   13: ldc -4
    //   15: invokestatic 112	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   18: pop
    //   19: aload_2
    //   20: ifnonnull +13 -> 33
    //   23: aload_0
    //   24: iconst_0
    //   25: ldc -2
    //   27: invokevirtual 256	com/google/firebase/package_8/zzad:close	(ILjava/lang/String;)V
    //   30: aload_0
    //   31: monitorexit
    //   32: return
    //   33: new 258	com/google/firebase/package_8/zzai
    //   36: dup
    //   37: aload_2
    //   38: invokespecial 261	com/google/firebase/package_8/zzai:<init>	(Landroid/os/IBinder;)V
    //   41: astore_1
    //   42: aload_0
    //   43: aload_1
    //   44: putfield 263	com/google/firebase/package_8/zzad:zzby	Lcom/google/firebase/package_8/zzai;
    //   47: aload_0
    //   48: iconst_2
    //   49: putfield 32	com/google/firebase/package_8/zzad:state	I
    //   52: aload_0
    //   53: invokespecial 265	com/google/firebase/package_8/zzad:startTimer	()V
    //   56: aload_0
    //   57: monitorexit
    //   58: return
    //   59: astore_1
    //   60: aload_0
    //   61: iconst_0
    //   62: aload_1
    //   63: invokevirtual 270	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   66: invokevirtual 256	com/google/firebase/package_8/zzad:close	(ILjava/lang/String;)V
    //   69: aload_0
    //   70: monitorexit
    //   71: return
    //   72: astore_1
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_1
    //   76: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	77	0	this	zzad
    //   0	77	1	paramComponentName	ComponentName
    //   0	77	2	paramIBinder	android.os.IBinder
    // Exception table:
    //   from	to	target	type
    //   33	42	59	android/os/RemoteException
    //   2	19	72	java/lang/Throwable
    //   23	30	72	java/lang/Throwable
    //   33	42	72	java/lang/Throwable
    //   42	56	72	java/lang/Throwable
    //   60	69	72	java/lang/Throwable
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName)
  {
    try
    {
      if (Log.isLoggable("MessengerIpcClient", 2)) {
        Log.v("MessengerIpcClient", "Service disconnected");
      }
      close(2, "Service disconnected");
      return;
    }
    catch (Throwable paramComponentName)
    {
      throw paramComponentName;
    }
  }
  
  final boolean start(zzak paramZzak)
  {
    for (;;)
    {
      try
      {
        switch (state)
        {
        case 3: 
          break;
        case 4: 
          return false;
        case 2: 
          zzbz.add(paramZzak);
          startTimer();
          return true;
        case 1: 
          zzbz.add(paramZzak);
          return true;
        case 0: 
          zzbz.add(paramZzak);
          if (state != 0) {
            break label275;
          }
          bool = true;
          Preconditions.checkState(bool);
          if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Starting bind to GmsCore");
          }
          state = 1;
          paramZzak = new Intent("com.google.android.c2dm.intent.REGISTER");
          paramZzak.setPackage("com.google.android.gms");
          if (!ConnectionTracker.getInstance().bindService(zzab.obtain(zzcb), paramZzak, this, 1)) {
            close(0, "Unable to bind to service");
          } else {
            zzab.get(zzcb).schedule(new zzaf(this), 30L, TimeUnit.SECONDS);
          }
          return true;
          int i = state;
          paramZzak = new StringBuilder(26);
          paramZzak.append("Unknown state: ");
          paramZzak.append(i);
          throw new IllegalStateException(paramZzak.toString());
        }
      }
      catch (Throwable paramZzak)
      {
        throw paramZzak;
      }
      continue;
      label275:
      boolean bool = false;
    }
  }
  
  final void zzaa()
  {
    try
    {
      if (state == 1) {
        close(1, "Timed out while binding");
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
