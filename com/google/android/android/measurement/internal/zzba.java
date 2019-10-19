package com.google.android.android.measurement.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.android.common.util.Clock;
import com.google.android.android.wifi.identifier.AdvertisingIdClient;
import com.google.android.android.wifi.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

final class zzba
  extends zzcp
{
  @VisibleForTesting
  static final Pair<String, Long> zzanc = new Pair("", Long.valueOf(0L));
  private SharedPreferences zzabr;
  public zzbe zzand;
  public final zzbd zzane = new zzbd(this, "last_upload", 0L);
  public final zzbd zzanf = new zzbd(this, "last_upload_attempt", 0L);
  public final zzbd zzang = new zzbd(this, "backoff", 0L);
  public final zzbd zzanh = new zzbd(this, "last_delete_stale", 0L);
  public final zzbd zzani = new zzbd(this, "midnight_offset", 0L);
  public final zzbd zzanj = new zzbd(this, "first_open_time", 0L);
  public final zzbd zzank = new zzbd(this, "app_install_time", 0L);
  public final zzbf zzanl = new zzbf(this, "app_instance_id", null);
  private String zzanm;
  private boolean zzann;
  private long zzano;
  public final zzbd zzanp = new zzbd(this, "time_before_start", 10000L);
  public final zzbd zzanq = new zzbd(this, "session_timeout", 1800000L);
  public final zzbc zzanr = new zzbc(this, "start_new_session", true);
  public final zzbf zzans = new zzbf(this, "allow_ad_personalization", null);
  public final zzbd zzant = new zzbd(this, "last_pause_time", 0L);
  public final zzbd zzanu = new zzbd(this, "time_active", 0L);
  public boolean zzanv;
  
  zzba(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private final SharedPreferences zzjr()
  {
    zzaf();
    zzcl();
    return zzabr;
  }
  
  final boolean getBool(boolean paramBoolean)
  {
    zzaf();
    return zzjr().getBoolean("measurement_enabled", paramBoolean);
  }
  
  final void setMeasurementEnabled(boolean paramBoolean)
  {
    zzaf();
    zzgo().zzjl().append("Setting measurementEnabled", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor localEditor = zzjr().edit();
    localEditor.putBoolean("measurement_enabled", paramBoolean);
    localEditor.apply();
  }
  
  final void setStarred(boolean paramBoolean)
  {
    zzaf();
    zzgo().zzjl().append("Setting useService", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor localEditor = zzjr().edit();
    localEditor.putBoolean("use_service", paramBoolean);
    localEditor.apply();
  }
  
  final void startService(boolean paramBoolean)
  {
    zzaf();
    zzgo().zzjl().append("Updating deferred analytics collection", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor localEditor = zzjr().edit();
    localEditor.putBoolean("deferred_analytics_collection", paramBoolean);
    localEditor.apply();
  }
  
  final Pair zzby(String paramString)
  {
    zzaf();
    long l = zzbx().elapsedRealtime();
    if ((zzanm != null) && (l < zzano)) {
      return new Pair(zzanm, Boolean.valueOf(zzann));
    }
    zzano = (l + zzgq().parseAndAdd(paramString, zzaf.zzaje));
    AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
    try
    {
      paramString = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
      if (paramString != null)
      {
        String str = paramString.getId();
        zzanm = str;
        boolean bool = paramString.isLimitAdTrackingEnabled();
        zzann = bool;
      }
      if (zzanm == null) {
        zzanm = "";
      }
    }
    catch (Exception paramString)
    {
      zzgo().zzjk().append("Unable to get advertising id", paramString);
      zzanm = "";
    }
    AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
    return new Pair(zzanm, Boolean.valueOf(zzann));
  }
  
  final String zzbz(String paramString)
  {
    zzaf();
    paramString = (String)zzbyfirst;
    MessageDigest localMessageDigest = zzfk.getMessageDigest();
    if (localMessageDigest == null) {
      return null;
    }
    return String.format(Locale.US, "%032X", new Object[] { new BigInteger(1, localMessageDigest.digest(paramString.getBytes())) });
  }
  
  final void zzca(String paramString)
  {
    zzaf();
    SharedPreferences.Editor localEditor = zzjr().edit();
    localEditor.putString("gmp_app_id", paramString);
    localEditor.apply();
  }
  
  final void zzcb(String paramString)
  {
    zzaf();
    SharedPreferences.Editor localEditor = zzjr().edit();
    localEditor.putString("admob_app_id", paramString);
    localEditor.apply();
  }
  
  protected final boolean zzgt()
  {
    return true;
  }
  
  protected final void zzgu()
  {
    zzabr = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
    zzanv = zzabr.getBoolean("has_been_opened", false);
    if (!zzanv)
    {
      SharedPreferences.Editor localEditor = zzabr.edit();
      localEditor.putBoolean("has_been_opened", true);
      localEditor.apply();
    }
    zzand = new zzbe(this, "health_monitor", Math.max(0L, ((Long)zzaf.zzajf.getDefaultValue()).longValue()), null);
  }
  
  final String zzjs()
  {
    zzaf();
    return zzjr().getString("gmp_app_id", null);
  }
  
  final String zzjt()
  {
    zzaf();
    return zzjr().getString("admob_app_id", null);
  }
  
  final Boolean zzju()
  {
    zzaf();
    if (!zzjr().contains("use_service")) {
      return null;
    }
    return Boolean.valueOf(zzjr().getBoolean("use_service", false));
  }
  
  final void zzjv()
  {
    zzaf();
    zzgo().zzjl().zzbx("Clearing collection preferences.");
    boolean bool2 = zzjr().contains("measurement_enabled");
    boolean bool1 = true;
    if (bool2) {
      bool1 = getBool(true);
    }
    SharedPreferences.Editor localEditor = zzjr().edit();
    localEditor.clear();
    localEditor.apply();
    if (bool2) {
      setMeasurementEnabled(bool1);
    }
  }
  
  protected final String zzjw()
  {
    zzaf();
    String str1 = zzjr().getString("previous_os_version", null);
    zzgk().zzcl();
    String str2 = Build.VERSION.RELEASE;
    if ((!TextUtils.isEmpty(str2)) && (!str2.equals(str1)))
    {
      SharedPreferences.Editor localEditor = zzjr().edit();
      localEditor.putString("previous_os_version", str2);
      localEditor.apply();
    }
    return str1;
  }
  
  final boolean zzjx()
  {
    zzaf();
    return zzjr().getBoolean("deferred_analytics_collection", false);
  }
  
  final boolean zzjy()
  {
    return zzabr.contains("deferred_analytics_collection");
  }
}
