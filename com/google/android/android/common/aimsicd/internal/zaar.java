package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.signin.internal.Server;
import com.google.android.android.signin.internal.Signature;
import java.lang.ref.WeakReference;

final class zaar
  extends Signature
{
  private final WeakReference<com.google.android.gms.common.api.internal.zaak> zagj;
  
  zaar(zaak paramZaak)
  {
    zagj = new WeakReference(paramZaak);
  }
  
  public final void notifyProgress(Server paramServer)
  {
    zaak localZaak = (zaak)zagj.get();
    if (localZaak == null) {
      return;
    }
    zaak.setContentTitle(localZaak).notify(new zaas(this, localZaak, localZaak, paramServer));
  }
}
