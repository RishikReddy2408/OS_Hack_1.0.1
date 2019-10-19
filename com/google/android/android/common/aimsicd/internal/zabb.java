package com.google.android.android.common.aimsicd.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.android.internal.base.Credentials;

final class zabb
  extends Credentials
{
  zabb(zaaw paramZaaw, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      int i = what;
      paramMessage = new StringBuilder(31);
      paramMessage.append("Unknown message id: ");
      paramMessage.append(i);
      Log.w("GoogleApiClientImpl", paramMessage.toString());
      return;
    case 2: 
      zaaw.access$1500(zahg);
      return;
    }
    zaaw.onResultReceived(zahg);
  }
}
