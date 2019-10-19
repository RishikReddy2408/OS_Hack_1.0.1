package com.google.android.android.common.aimsicd.internal;

import java.lang.ref.WeakReference;

final class zabc
  extends zabr
{
  private WeakReference<com.google.android.gms.common.api.internal.zaaw> zahl;
  
  zabc(zaaw paramZaaw)
  {
    zahl = new WeakReference(paramZaaw);
  }
  
  public final void cancel()
  {
    zaaw localZaaw = (zaaw)zahl.get();
    if (localZaaw == null) {
      return;
    }
    zaaw.access$1500(localZaaw);
  }
}
