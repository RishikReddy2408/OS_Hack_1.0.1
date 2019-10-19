package com.google.android.android.common.aimsicd.internal;

import android.os.Bundle;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.android.signin.Client;
import java.util.concurrent.locks.Lock;

final class zaat
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  private zaat(zaak paramZaak) {}
  
  public final void onConnected(Bundle paramBundle)
  {
    zaak.fromId(zagi).execute(new zaar(zagi));
  }
  
  public final void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    zaak.getLock(zagi).lock();
    try
    {
      boolean bool = zaak.changed(zagi, paramConnectionResult);
      if (bool)
      {
        zaak.ignore(zagi);
        zaak.setEventListener(zagi);
      }
      else
      {
        zaak.setEventListener(zagi, paramConnectionResult);
      }
      zaak.getLock(zagi).unlock();
      return;
    }
    catch (Throwable paramConnectionResult)
    {
      zaak.getLock(zagi).unlock();
      throw paramConnectionResult;
    }
  }
  
  public final void onConnectionSuspended(int paramInt) {}
}
