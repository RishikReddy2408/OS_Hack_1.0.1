package com.google.android.android.common.aimsicd.internal;

import java.util.concurrent.locks.Lock;

abstract class zaau
  implements Runnable
{
  private zaau(zaak paramZaak) {}
  
  public void run()
  {
    zaak.getLock(zagi).lock();
    try
    {
      boolean bool = Thread.interrupted();
      if (bool)
      {
        zaak.getLock(zagi).unlock();
        return;
      }
      zaan();
      zaak.getLock(zagi).unlock();
      return;
    }
    catch (Throwable localThrowable) {}catch (RuntimeException localRuntimeException)
    {
      zaak.setContentTitle(zagi).onFutureDone(localRuntimeException);
      zaak.getLock(zagi).unlock();
      return;
    }
    zaak.getLock(zagi).unlock();
    throw localRuntimeException;
  }
  
  protected abstract void zaan();
}
