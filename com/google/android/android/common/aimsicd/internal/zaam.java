package com.google.android.android.common.aimsicd.internal;

import android.os.Looper;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.GoogleApiClient;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.api.Api;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;

final class zaam
  implements BaseGmsClient.ConnectionProgressReportCallbacks
{
  private final Api<?> mApi;
  private final boolean zaeb;
  private final WeakReference<com.google.android.gms.common.api.internal.zaak> zagj;
  
  public zaam(zaak paramZaak, Sample paramSample, boolean paramBoolean)
  {
    zagj = new WeakReference(paramZaak);
    mApi = paramSample;
    zaeb = paramBoolean;
  }
  
  public final void onReportServiceBinding(ConnectionResult paramConnectionResult)
  {
    zaak localZaak = (zaak)zagj.get();
    if (localZaak == null) {
      return;
    }
    boolean bool;
    if (Looper.myLooper() == setContentTitlezaed.getLooper()) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkState(bool, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
    zaak.getLock(localZaak).lock();
    try
    {
      bool = zaak.handleResponse(localZaak, 0);
      if (!bool)
      {
        zaak.getLock(localZaak).unlock();
        return;
      }
      bool = paramConnectionResult.isSuccess();
      if (!bool) {
        zaak.sendMail(localZaak, paramConnectionResult, mApi, zaeb);
      }
      bool = zaak.reportException(localZaak);
      if (bool) {
        zaak.setEventListener(localZaak);
      }
      zaak.getLock(localZaak).unlock();
      return;
    }
    catch (Throwable paramConnectionResult)
    {
      zaak.getLock(localZaak).unlock();
      throw paramConnectionResult;
    }
  }
}
