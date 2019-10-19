package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.android.measurement.AppMeasurement.OnEventListener;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;

final class PushService
  implements AppMeasurement.OnEventListener
{
  public PushService(FileStore paramFileStore) {}
  
  public final void onEvent(String paramString1, String paramString2, Bundle paramBundle, long paramLong)
  {
    if ((paramString1 != null) && (!paramString1.equals("crash")) && (Volume.zzfp(paramString2)))
    {
      paramString1 = new Bundle();
      paramString1.putString("name", paramString2);
      paramString1.putLong("timestampInMillis", paramLong);
      paramString1.putBundle("params", paramBundle);
      FileStore.access$getTAG$p(zzbsx).onMessageTriggered(3, paramString1);
    }
  }
}
