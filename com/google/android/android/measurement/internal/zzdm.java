package com.google.android.android.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import com.google.android.android.common.util.Clock;

@TargetApi(14)
@MainThread
final class zzdm
  implements Application.ActivityLifecycleCallbacks
{
  private zzdm(zzcs paramZzcs) {}
  
  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
    Object localObject1 = zzarc;
    try
    {
      ((zzco)localObject1).zzgo().zzjl().zzbx("onActivityCreated");
      localObject1 = paramActivity.getIntent();
      if (localObject1 != null)
      {
        Object localObject2 = ((Intent)localObject1).getData();
        if (localObject2 != null)
        {
          boolean bool = ((Uri)localObject2).isHierarchical();
          if (bool)
          {
            if (paramBundle == null)
            {
              Object localObject3 = zzarc;
              localObject3 = ((zzco)localObject3).zzgm().parse((Uri)localObject2);
              zzcs localZzcs = zzarc;
              localZzcs.zzgm();
              bool = zzfk.loadData((Intent)localObject1);
              if (bool) {
                localObject1 = "gs";
              } else {
                localObject1 = "auto";
              }
              if (localObject3 != null)
              {
                localZzcs = zzarc;
                localZzcs.logEvent((String)localObject1, "_cmp", (Bundle)localObject3);
              }
            }
            localObject1 = ((Uri)localObject2).getQueryParameter("referrer");
            bool = TextUtils.isEmpty((CharSequence)localObject1);
            if (bool) {
              return;
            }
            bool = ((String)localObject1).contains("gclid");
            if (bool)
            {
              bool = ((String)localObject1).contains("utm_campaign");
              if (!bool)
              {
                bool = ((String)localObject1).contains("utm_source");
                if (!bool)
                {
                  bool = ((String)localObject1).contains("utm_medium");
                  if (!bool)
                  {
                    bool = ((String)localObject1).contains("utm_term");
                    if (!bool)
                    {
                      bool = ((String)localObject1).contains("utm_content");
                      if (!bool) {
                        break label244;
                      }
                    }
                  }
                }
              }
              i = 1;
              break label246;
            }
            label244:
            int i = 0;
            label246:
            if (i == 0)
            {
              localObject1 = zzarc;
              ((zzco)localObject1).zzgo().zzjk().zzbx("Activity created with data 'referrer' param without gclid and at least one utm field");
              return;
            }
            localObject2 = zzarc;
            ((zzco)localObject2).zzgo().zzjk().append("Activity created with referrer", localObject1);
            bool = TextUtils.isEmpty((CharSequence)localObject1);
            if (!bool)
            {
              localObject2 = zzarc;
              ((zzcs)localObject2).open("auto", "_ldl", localObject1, true);
            }
          }
        }
      }
    }
    catch (Exception localException)
    {
      zzarc.zzgo().zzjd().append("Throwable caught in onActivityCreated", localException);
    }
    zzarc.zzgh().onActivityCreated(paramActivity, paramBundle);
  }
  
  public final void onActivityDestroyed(Activity paramActivity)
  {
    zzarc.zzgh().onActivityDestroyed(paramActivity);
  }
  
  public final void onActivityPaused(Activity paramActivity)
  {
    zzarc.zzgh().onActivityPaused(paramActivity);
    paramActivity = zzarc.zzgj();
    long l = paramActivity.zzbx().elapsedRealtime();
    paramActivity.zzgn().get(new zzeu(paramActivity, l));
  }
  
  public final void onActivityResumed(Activity paramActivity)
  {
    zzarc.zzgh().onActivityResumed(paramActivity);
    paramActivity = zzarc.zzgj();
    long l = paramActivity.zzbx().elapsedRealtime();
    paramActivity.zzgn().get(new zzet(paramActivity, l));
  }
  
  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
  {
    zzarc.zzgh().onActivitySaveInstanceState(paramActivity, paramBundle);
  }
  
  public final void onActivityStarted(Activity paramActivity) {}
  
  public final void onActivityStopped(Activity paramActivity) {}
}
