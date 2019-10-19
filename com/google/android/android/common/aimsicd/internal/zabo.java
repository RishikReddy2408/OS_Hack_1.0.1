package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.Api.Client;
import java.util.Collections;
import java.util.Map;

final class zabo
  implements Runnable
{
  zabo(GoogleApiManager.zac paramZac, ConnectionResult paramConnectionResult) {}
  
  public final void run()
  {
    GoogleApiManager.zac localZac;
    if (zaiy.isSuccess())
    {
      GoogleApiManager.zac.updateButton(zajf, true);
      if (GoogleApiManager.zac.getLinkTarget(zajf).requiresSignIn())
      {
        GoogleApiManager.zac.Zip(zajf);
        return;
      }
      localZac = zajf;
    }
    try
    {
      GoogleApiManager.zac.getLinkTarget(localZac).getRemoteService(null, Collections.emptySet());
      return;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    ((GoogleApiManager.zaa)GoogleApiManager.isIgnored(zajf.zail).get(GoogleApiManager.zac.readMessage(zajf))).onConnectionFailed(new ConnectionResult(10));
    return;
    ((GoogleApiManager.zaa)GoogleApiManager.isIgnored(zajf.zail).get(GoogleApiManager.zac.readMessage(zajf))).onConnectionFailed(zaiy);
  }
}
