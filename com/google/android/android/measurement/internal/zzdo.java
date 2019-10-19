package com.google.android.android.measurement.internal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

public final class zzdo
  extends Log
{
  @VisibleForTesting
  protected zzdn zzaro;
  private volatile zzdn zzarp;
  private zzdn zzarq;
  private final Map<Activity, com.google.android.gms.measurement.internal.zzdn> zzarr = new ArrayMap();
  private zzdn zzars;
  private String zzart;
  
  public zzdo(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private final zzdn e(Activity paramActivity)
  {
    Preconditions.checkNotNull(paramActivity);
    zzdn localZzdn2 = (zzdn)zzarr.get(paramActivity);
    zzdn localZzdn1 = localZzdn2;
    if (localZzdn2 == null)
    {
      localZzdn1 = new zzdn(null, zzcn(paramActivity.getClass().getCanonicalName()), zzgm().zzmc());
      zzarr.put(paramActivity, localZzdn1);
    }
    return localZzdn1;
  }
  
  private final void persist(Activity paramActivity, zzdn paramZzdn, boolean paramBoolean)
  {
    zzdn localZzdn1;
    if (zzarp == null) {
      localZzdn1 = zzarq;
    } else {
      localZzdn1 = zzarp;
    }
    zzdn localZzdn2 = paramZzdn;
    if (zzarl == null) {
      localZzdn2 = new zzdn(zzuw, zzcn(paramActivity.getClass().getCanonicalName()), zzarm);
    }
    zzarq = zzarp;
    zzarp = localZzdn2;
    zzgn().get(new zzdp(this, paramBoolean, localZzdn1, localZzdn2));
  }
  
  public static void set(zzdn paramZzdn, Bundle paramBundle, boolean paramBoolean)
  {
    if ((paramBundle != null) && (paramZzdn != null) && ((!paramBundle.containsKey("_sc")) || (paramBoolean)))
    {
      if (zzuw != null) {
        paramBundle.putString("_sn", zzuw);
      } else {
        paramBundle.remove("_sn");
      }
      paramBundle.putString("_sc", zzarl);
      paramBundle.putLong("_si", zzarm);
      return;
    }
    if ((paramBundle != null) && (paramZzdn == null) && (paramBoolean))
    {
      paramBundle.remove("_sn");
      paramBundle.remove("_sc");
      paramBundle.remove("_si");
    }
  }
  
  private final void setAlarm(zzdn paramZzdn)
  {
    zzgd().upload(zzbx().elapsedRealtime());
    if (zzgj().setAlarm(zzarn)) {
      zzarn = false;
    }
  }
  
  private static String zzcn(String paramString)
  {
    paramString = paramString.split("\\.");
    if (paramString.length > 0) {
      paramString = paramString[(paramString.length - 1)];
    } else {
      paramString = "";
    }
    String str = paramString;
    if (paramString.length() > 100) {
      str = paramString.substring(0, 100);
    }
    return str;
  }
  
  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
    if (paramBundle == null) {
      return;
    }
    paramBundle = paramBundle.getBundle("com.google.app_measurement.screen_service");
    if (paramBundle == null) {
      return;
    }
    paramBundle = new zzdn(paramBundle.getString("name"), paramBundle.getString("referrer_name"), paramBundle.getLong("id"));
    zzarr.put(paramActivity, paramBundle);
  }
  
  public final void onActivityDestroyed(Activity paramActivity)
  {
    zzarr.remove(paramActivity);
  }
  
  public final void onActivityPaused(Activity paramActivity)
  {
    paramActivity = e(paramActivity);
    zzarq = zzarp;
    zzarp = null;
    zzgn().get(new zzdq(this, paramActivity));
  }
  
  public final void onActivityResumed(Activity paramActivity)
  {
    persist(paramActivity, e(paramActivity), false);
    paramActivity = zzgd();
    long l = paramActivity.zzbx().elapsedRealtime();
    paramActivity.zzgn().get(new IonBitmapRequestBuilder.1(paramActivity, l));
  }
  
  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
  {
    if (paramBundle == null) {
      return;
    }
    paramActivity = (zzdn)zzarr.get(paramActivity);
    if (paramActivity == null) {
      return;
    }
    Bundle localBundle = new Bundle();
    localBundle.putLong("id", zzarm);
    localBundle.putString("name", zzuw);
    localBundle.putString("referrer_name", zzarl);
    paramBundle.putBundle("com.google.app_measurement.screen_service", localBundle);
  }
  
  public final void purchaseBook(String paramString, zzdn paramZzdn)
  {
    zzaf();
    try
    {
      if ((zzart == null) || (zzart.equals(paramString)) || (paramZzdn != null))
      {
        zzart = paramString;
        zzars = paramZzdn;
      }
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public final void setCurrentScreen(Activity paramActivity, String paramString1, String paramString2)
  {
    if (zzarp == null)
    {
      zzgo().zzjg().zzbx("setCurrentScreen cannot be called while no activity active");
      return;
    }
    if (zzarr.get(paramActivity) == null)
    {
      zzgo().zzjg().zzbx("setCurrentScreen must be called with an activity in the activity lifecycle");
      return;
    }
    String str = paramString2;
    if (paramString2 == null) {
      str = zzcn(paramActivity.getClass().getCanonicalName());
    }
    boolean bool1 = zzarp.zzarl.equals(str);
    boolean bool2 = zzfk.verifySignature(zzarp.zzuw, paramString1);
    if ((bool1) && (bool2))
    {
      zzgo().zzji().zzbx("setCurrentScreen cannot be called with the same class and name");
      return;
    }
    if ((paramString1 != null) && ((paramString1.length() <= 0) || (paramString1.length() > 100)))
    {
      zzgo().zzjg().append("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(paramString1.length()));
      return;
    }
    if ((str != null) && ((str.length() <= 0) || (str.length() > 100)))
    {
      zzgo().zzjg().append("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
      return;
    }
    zzar localZzar = zzgo().zzjl();
    if (paramString1 == null) {
      paramString2 = "null";
    } else {
      paramString2 = paramString1;
    }
    localZzar.append("Setting current screen to name, class", paramString2, str);
    paramString1 = new zzdn(paramString1, str, zzgm().zzmc());
    zzarr.put(paramActivity, paramString1);
    persist(paramActivity, paramString1, true);
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
  
  public final zzdn zzla()
  {
    zzcl();
    zzaf();
    return zzaro;
  }
  
  public final zzdn zzlb()
  {
    zzgb();
    return zzarp;
  }
}
