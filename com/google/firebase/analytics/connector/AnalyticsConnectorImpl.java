package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.measurement.AppMeasurement;
import com.google.android.android.measurement.AppMeasurement.ConditionalUserProperty;
import com.google.android.android.measurement.internal.zzak;
import com.google.android.android.measurement.internal.zzbt;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.internal.EventHandler;
import com.google.firebase.analytics.connector.internal.Volume;
import com.google.firebase.analytics.connector.internal.zza;
import com.google.firebase.events.Subscriber;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AnalyticsConnectorImpl
  implements AnalyticsConnector
{
  private static volatile AnalyticsConnector zzbsf;
  @VisibleForTesting
  private final AppMeasurement zzbsg;
  @VisibleForTesting
  final Map<String, zza> zzbsh;
  
  private AnalyticsConnectorImpl(AppMeasurement paramAppMeasurement)
  {
    Preconditions.checkNotNull(paramAppMeasurement);
    zzbsg = paramAppMeasurement;
    zzbsh = new ConcurrentHashMap();
  }
  
  public static AnalyticsConnector getInstance()
  {
    return getInstance(FirebaseApp.getInstance());
  }
  
  public static AnalyticsConnector getInstance(FirebaseApp paramFirebaseApp)
  {
    return (AnalyticsConnector)paramFirebaseApp.get(AnalyticsConnector.class);
  }
  
  public static AnalyticsConnector getInstance(FirebaseApp paramFirebaseApp, Context paramContext, Subscriber paramSubscriber)
  {
    Preconditions.checkNotNull(paramFirebaseApp);
    Preconditions.checkNotNull(paramContext);
    Preconditions.checkNotNull(paramSubscriber);
    Preconditions.checkNotNull(paramContext.getApplicationContext());
    if (zzbsf == null) {
      try
      {
        if (zzbsf == null)
        {
          Bundle localBundle = new Bundle(1);
          if (paramFirebaseApp.isDefaultApp())
          {
            paramSubscriber.subscribe(DataCollectionDefaultChange.class, PingManager.zzbsi, DomainValidator.zzbsj);
            localBundle.putBoolean("dataCollectionDefaultEnabled", paramFirebaseApp.isDataCollectionDefaultEnabled());
          }
          zzbsf = new AnalyticsConnectorImpl(zzbt.register(paramContext, zzak.getValue(localBundle)).zzki());
        }
      }
      catch (Throwable paramFirebaseApp)
      {
        throw paramFirebaseApp;
      }
    }
    return zzbsf;
  }
  
  private final boolean zzfn(String paramString)
  {
    return (!paramString.isEmpty()) && (zzbsh.containsKey(paramString)) && (zzbsh.get(paramString) != null);
  }
  
  public void clearConditionalUserProperty(String paramString1, String paramString2, Bundle paramBundle)
  {
    if ((paramString2 != null) && (!Volume.get(paramString2, paramBundle))) {
      return;
    }
    zzbsg.clearConditionalUserProperty(paramString1, paramString2, paramBundle);
  }
  
  public List getConditionalUserProperties(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    paramString1 = zzbsg.getConditionalUserProperties(paramString1, paramString2).iterator();
    while (paramString1.hasNext()) {
      localArrayList.add(Volume.insertFolder((AppMeasurement.ConditionalUserProperty)paramString1.next()));
    }
    return localArrayList;
  }
  
  public int getMaxUserProperties(String paramString)
  {
    return zzbsg.getMaxUserProperties(paramString);
  }
  
  public Map getUserProperties(boolean paramBoolean)
  {
    return zzbsg.getUserProperties(paramBoolean);
  }
  
  public void logEvent(String paramString1, String paramString2, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    if (!Volume.zzfo(paramString1)) {
      return;
    }
    if (!Volume.get(paramString2, localBundle)) {
      return;
    }
    if (!Volume.get(paramString1, paramString2, localBundle)) {
      return;
    }
    zzbsg.logEventInternal(paramString1, paramString2, localBundle);
  }
  
  public AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener(String paramString, AnalyticsConnector.AnalyticsConnectorListener paramAnalyticsConnectorListener)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a5 = a4\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public void setConditionalUserProperty(AnalyticsConnector.ConditionalUserProperty paramConditionalUserProperty)
  {
    if (!Volume.renameItem(paramConditionalUserProperty)) {
      return;
    }
    zzbsg.setConditionalUserProperty(Volume.decompress(paramConditionalUserProperty));
  }
  
  public void setUserProperty(String paramString1, String paramString2, Object paramObject)
  {
    if (!Volume.zzfo(paramString1)) {
      return;
    }
    if (!Volume.get(paramString1, paramString2)) {
      return;
    }
    zzbsg.setUserPropertyInternal(paramString1, paramString2, paramObject);
  }
}
