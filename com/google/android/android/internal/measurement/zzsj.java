package com.google.android.android.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzsj
  extends ContentObserver
{
  zzsj(zzsi paramZzsi, Handler paramHandler)
  {
    super(null);
  }
  
  public final void onChange(boolean paramBoolean)
  {
    zzbqx.zzta();
    zzsi.notifyAppWidgetViewDataChanged(zzbqx);
  }
}
