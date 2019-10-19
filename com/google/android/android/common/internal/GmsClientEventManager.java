package com.google.android.android.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.internal.base.Credentials;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class GmsClientEventManager
  implements Handler.Callback
{
  private final Handler mHandler;
  private final Object mLock = new Object();
  private final GmsClientEventState zaok;
  private final ArrayList<com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks> zaol = new ArrayList();
  @VisibleForTesting
  private final ArrayList<com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks> zaom = new ArrayList();
  private final ArrayList<com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener> zaon = new ArrayList();
  private volatile boolean zaoo = false;
  private final AtomicInteger zaop = new AtomicInteger(0);
  private boolean zaoq = false;
  
  public GmsClientEventManager(Looper paramLooper, GmsClientEventState paramGmsClientEventState)
  {
    zaok = paramGmsClientEventState;
    mHandler = new Credentials(paramLooper, this);
  }
  
  public final boolean areCallbacksEnabled()
  {
    return zaoo;
  }
  
  public final void disableCallbacks()
  {
    zaoo = false;
    zaop.incrementAndGet();
  }
  
  public final void enableCallbacks()
  {
    zaoo = true;
  }
  
  public final boolean handleMessage(Message paramMessage)
  {
    if (what == 1)
    {
      com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks localConnectionCallbacks = (com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks)obj;
      paramMessage = mLock;
      try
      {
        if ((zaoo) && (zaok.isConnected()) && (zaol.contains(localConnectionCallbacks))) {
          localConnectionCallbacks.onConnected(zaok.getConnectionHint());
        }
        return true;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    int i = what;
    paramMessage = new StringBuilder(45);
    paramMessage.append("Don't know how to handle message: ");
    paramMessage.append(i);
    Log.wtf("GmsClientEvents", paramMessage.toString(), new Exception());
    return false;
  }
  
  public final boolean isConnectionCallbacksRegistered(com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    Preconditions.checkNotNull(paramConnectionCallbacks);
    Object localObject = mLock;
    try
    {
      boolean bool = zaol.contains(paramConnectionCallbacks);
      return bool;
    }
    catch (Throwable paramConnectionCallbacks)
    {
      throw paramConnectionCallbacks;
    }
  }
  
  public final boolean isConnectionFailedListenerRegistered(com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    Preconditions.checkNotNull(paramOnConnectionFailedListener);
    Object localObject = mLock;
    try
    {
      boolean bool = zaon.contains(paramOnConnectionFailedListener);
      return bool;
    }
    catch (Throwable paramOnConnectionFailedListener)
    {
      throw paramOnConnectionFailedListener;
    }
  }
  
  public final void onConnectionFailure(ConnectionResult paramConnectionResult)
  {
    Object localObject1 = Looper.myLooper();
    Object localObject2 = mHandler.getLooper();
    int i = 0;
    boolean bool;
    if (localObject1 == localObject2) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkState(bool, "onConnectionFailure must only be called on the Handler thread");
    mHandler.removeMessages(1);
    localObject1 = mLock;
    try
    {
      localObject2 = new ArrayList(zaon);
      int k = zaop.get();
      localObject2 = (ArrayList)localObject2;
      int m = ((ArrayList)localObject2).size();
      while (i < m)
      {
        Object localObject3 = ((ArrayList)localObject2).get(i);
        int j = i + 1;
        localObject3 = (com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener)localObject3;
        if ((zaoo) && (zaop.get() == k))
        {
          i = j;
          if (zaon.contains(localObject3))
          {
            ((com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener)localObject3).onConnectionFailed(paramConnectionResult);
            i = j;
          }
        }
        else
        {
          return;
        }
      }
      return;
    }
    catch (Throwable paramConnectionResult)
    {
      throw paramConnectionResult;
    }
  }
  
  protected final void onConnectionSuccess()
  {
    Object localObject = mLock;
    try
    {
      onConnectionSuccess(zaok.getConnectionHint());
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final void onConnectionSuccess(Bundle paramBundle)
  {
    Object localObject1 = Looper.myLooper();
    Object localObject2 = mHandler.getLooper();
    boolean bool2 = true;
    boolean bool1;
    if (localObject1 == localObject2) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    Preconditions.checkState(bool1, "onConnectionSuccess must only be called on the Handler thread");
    localObject1 = mLock;
    for (;;)
    {
      try
      {
        Preconditions.checkState(zaoq ^ true);
        mHandler.removeMessages(1);
        zaoq = true;
        if (zaom.size() == 0)
        {
          bool1 = bool2;
          Preconditions.checkState(bool1);
          localObject2 = new ArrayList(zaol);
          int k = zaop.get();
          localObject2 = (ArrayList)localObject2;
          int m = ((ArrayList)localObject2).size();
          int i = 0;
          if (i < m)
          {
            Object localObject3 = ((ArrayList)localObject2).get(i);
            int j = i + 1;
            localObject3 = (com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks)localObject3;
            if ((zaoo) && (zaok.isConnected()) && (zaop.get() == k))
            {
              i = j;
              if (zaom.contains(localObject3)) {
                continue;
              }
              ((com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks)localObject3).onConnected(paramBundle);
              i = j;
              continue;
            }
          }
          zaom.clear();
          zaoq = false;
          return;
        }
      }
      catch (Throwable paramBundle)
      {
        throw paramBundle;
      }
      bool1 = false;
    }
  }
  
  public final void onUnintentionalDisconnection(int paramInt)
  {
    boolean bool;
    if (Looper.myLooper() == mHandler.getLooper()) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkState(bool, "onUnintentionalDisconnection must only be called on the Handler thread");
    mHandler.removeMessages(1);
    Object localObject1 = mLock;
    try
    {
      zaoq = true;
      ArrayList localArrayList = new ArrayList(zaol);
      int k = zaop.get();
      localArrayList = (ArrayList)localArrayList;
      int m = localArrayList.size();
      int i = 0;
      while (i < m)
      {
        Object localObject2 = localArrayList.get(i);
        int j = i + 1;
        localObject2 = (com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks)localObject2;
        if ((!zaoo) || (zaop.get() != k)) {
          break;
        }
        i = j;
        if (zaol.contains(localObject2))
        {
          ((com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks)localObject2).onConnectionSuspended(paramInt);
          i = j;
        }
      }
      zaom.clear();
      zaoq = false;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final void registerConnectionCallbacks(com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    Preconditions.checkNotNull(paramConnectionCallbacks);
    Object localObject = mLock;
    try
    {
      if (zaol.contains(paramConnectionCallbacks))
      {
        String str = String.valueOf(paramConnectionCallbacks);
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 62);
        localStringBuilder.append("registerConnectionCallbacks(): listener ");
        localStringBuilder.append(str);
        localStringBuilder.append(" is already registered");
        Log.w("GmsClientEvents", localStringBuilder.toString());
      }
      else
      {
        zaol.add(paramConnectionCallbacks);
      }
      if (zaok.isConnected())
      {
        mHandler.sendMessage(mHandler.obtainMessage(1, paramConnectionCallbacks));
        return;
      }
    }
    catch (Throwable paramConnectionCallbacks)
    {
      throw paramConnectionCallbacks;
    }
  }
  
  public final void registerConnectionFailedListener(com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    Preconditions.checkNotNull(paramOnConnectionFailedListener);
    Object localObject = mLock;
    try
    {
      if (zaon.contains(paramOnConnectionFailedListener))
      {
        paramOnConnectionFailedListener = String.valueOf(paramOnConnectionFailedListener);
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramOnConnectionFailedListener).length() + 67);
        localStringBuilder.append("registerConnectionFailedListener(): listener ");
        localStringBuilder.append(paramOnConnectionFailedListener);
        localStringBuilder.append(" is already registered");
        Log.w("GmsClientEvents", localStringBuilder.toString());
      }
      else
      {
        zaon.add(paramOnConnectionFailedListener);
      }
      return;
    }
    catch (Throwable paramOnConnectionFailedListener)
    {
      throw paramOnConnectionFailedListener;
    }
  }
  
  public final void unregisterConnectionCallbacks(com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    Preconditions.checkNotNull(paramConnectionCallbacks);
    Object localObject = mLock;
    try
    {
      if (!zaol.remove(paramConnectionCallbacks))
      {
        paramConnectionCallbacks = String.valueOf(paramConnectionCallbacks);
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramConnectionCallbacks).length() + 52);
        localStringBuilder.append("unregisterConnectionCallbacks(): listener ");
        localStringBuilder.append(paramConnectionCallbacks);
        localStringBuilder.append(" not found");
        Log.w("GmsClientEvents", localStringBuilder.toString());
      }
      else if (zaoq)
      {
        zaom.add(paramConnectionCallbacks);
      }
      return;
    }
    catch (Throwable paramConnectionCallbacks)
    {
      throw paramConnectionCallbacks;
    }
  }
  
  public final void unregisterConnectionFailedListener(com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    Preconditions.checkNotNull(paramOnConnectionFailedListener);
    Object localObject = mLock;
    try
    {
      if (!zaon.remove(paramOnConnectionFailedListener))
      {
        paramOnConnectionFailedListener = String.valueOf(paramOnConnectionFailedListener);
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramOnConnectionFailedListener).length() + 57);
        localStringBuilder.append("unregisterConnectionFailedListener(): listener ");
        localStringBuilder.append(paramOnConnectionFailedListener);
        localStringBuilder.append(" not found");
        Log.w("GmsClientEvents", localStringBuilder.toString());
      }
      return;
    }
    catch (Throwable paramOnConnectionFailedListener)
    {
      throw paramOnConnectionFailedListener;
    }
  }
  
  @VisibleForTesting
  public abstract interface GmsClientEventState
  {
    public abstract Bundle getConnectionHint();
    
    public abstract boolean isConnected();
  }
}
