package com.google.android.android.common.aimsicd.internal;

import android.os.Bundle;
import com.google.android.android.common.ConnectionResult;
import java.util.concurrent.locks.Lock;

final class StateMachine
  implements zabt
{
  private StateMachine(SocketIOClient paramSocketIOClient) {}
  
  public final void execute(Bundle paramBundle)
  {
    SocketIOClient.disconnect(zaep).lock();
    try
    {
      SocketIOClient.connect(zaep, paramBundle);
      SocketIOClient.access$setMClient(zaep, ConnectionResult.RESULT_SUCCESS);
      SocketIOClient.connect(zaep);
      SocketIOClient.disconnect(zaep).unlock();
      return;
    }
    catch (Throwable paramBundle)
    {
      SocketIOClient.disconnect(zaep).unlock();
      throw paramBundle;
    }
  }
  
  public final void quit(int paramInt, boolean paramBoolean)
  {
    SocketIOClient.disconnect(zaep).lock();
    try
    {
      boolean bool = SocketIOClient.isConnected(zaep);
      if (!bool)
      {
        ConnectionResult localConnectionResult = SocketIOClient.access$getMClient(zaep);
        if (localConnectionResult != null)
        {
          bool = SocketIOClient.access$getMClient(zaep).isSuccess();
          if (bool)
          {
            SocketIOClient.disconnect(zaep, true);
            SocketIOClient.emitRaw(zaep).onConnectionSuspended(paramInt);
            SocketIOClient.disconnect(zaep).unlock();
            return;
          }
        }
      }
      SocketIOClient.disconnect(zaep, false);
      SocketIOClient.disconnect(zaep, paramInt, paramBoolean);
      SocketIOClient.disconnect(zaep).unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      SocketIOClient.disconnect(zaep).unlock();
      throw localThrowable;
    }
  }
  
  public final void removeAccount(ConnectionResult paramConnectionResult)
  {
    SocketIOClient.disconnect(zaep).lock();
    try
    {
      SocketIOClient.access$setMClient(zaep, paramConnectionResult);
      SocketIOClient.connect(zaep);
      SocketIOClient.disconnect(zaep).unlock();
      return;
    }
    catch (Throwable paramConnectionResult)
    {
      SocketIOClient.disconnect(zaep).unlock();
      throw paramConnectionResult;
    }
  }
}
